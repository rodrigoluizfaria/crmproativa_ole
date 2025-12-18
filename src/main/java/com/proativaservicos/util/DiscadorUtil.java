package com.proativaservicos.util;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.pwd.CampanhaRetornoDiscagem;
import com.proativaservicos.model.trescplus.ResponseMailing;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.service.VonixService;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

@Named
public class DiscadorUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private VonixService serviceVonix;

    @Inject
    private VirtualDiscadorPoweDial discadorPwd;

    @Inject
    private TresCPlusServiceUtil tresCPlusServiceUtil;

    @Inject
    private ArgusService argusService;


    public String subirCargaDiscador(Empresa empresa, Campanha campanha, Collection<Atendimento> listAtendimentos) {


        if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {

            IntegracaoWs integra = this.serviceIntegracao.pesquisaIngracaoDiscador(empresa.getId());

            if (integra != null) {

                if (integra.getTipoIntegracao().equals(TipoIntegracaoEnum.VONIX)) {

                    System.out.println("enviado lotes para o discador. VONIX");
                    this.serviceVonix.alimentarDiscadorVonix(integra, campanha, new ArrayList<Atendimento>(listAtendimentos));

                    return ", enviado lotes para o discador.";

                } else if (integra.getTipoIntegracao().equals(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER)) {

                    integra = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_POWER_DIALER, empresa.getId(), TipoAcessoEnum.ATIVO);

                    try {

                        this.discadorPwd.enviarContatosCampanha(integra, campanha, new ArrayList<Atendimento>(listAtendimentos));

                    } catch (ProativaException e) {
                        e.printStackTrace();
                    }

                    return ", importada discador.";

                } else {

                    return "Integração ainda não foi implementada..";
                }

            }

        } else if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C)) {

            IntegracaoWs integra = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, empresa.getId(), TipoAcessoEnum.ATIVO);

            if (integra == null)
                return "Nenhum serviço de integração 3C+ ativo.";

            printar(listAtendimentos.size(),"3C+", new ArrayList<>(listAtendimentos).get(0).getImportacao().getNomeArquivo());

            return this.tresCPlusServiceUtil.alimentarDiscador(integra, campanha, new ArrayList<Atendimento>(listAtendimentos));

        } else if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_ARGUS)) {

            IntegracaoWs integra = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.ARGUS, empresa.getId(), TipoAcessoEnum.ATIVO);

            if (integra == null)
                return "Nenhum serviço de integração Argus ativo.";

            printar(listAtendimentos.size(),"Argus", new ArrayList<>(listAtendimentos).get(0).getImportacao().getNomeArquivo());

            return this.argusService.alimentarDiscador(integra, campanha, new ArrayList<Atendimento>(listAtendimentos));

        }

        return "";

    }

    public void printar(int quantidade, String discador,String nomeArquivo){


        System.out.println("enviado atendimentos discador "+discador+": "+ quantidade);
        System.out.println("ARQUIVO: "+nomeArquivo);
    }

    public void entrarEmPausa(Usuario usuario, Pausa pausa, boolean isEntrarPausa) throws ProativaException {

        if (usuario != null && usuario.getEmpresa() != null) {

            IntegracaoWs integracao = this.serviceIntegracao.pesquisaIngracaoDiscador(usuario.getEmpresa().getId());

            if (integracao != null) {

                iniciarPausa(integracao, usuario, pausa, isEntrarPausa);
            }

        }

    }

    private void iniciarPausa(IntegracaoWs integracao, Usuario usuario, Pausa pausa, boolean isEntrarPausa) throws ProativaException {

        if (integracao != null) {

            if (integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.VONIX)) {

                this.serviceVonix.entrarEmpausa(integracao, usuario, pausa);

            } else if (integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER)) {

                this.discadorPwd.entrarEmPausa(integracao, usuario, pausa == null ? "" : pausa.getDescricao());

            } else {

                System.out.println("Servico ainda não implementado.....");

            }

        }

    }

    public void finalizarContatosDiscador(Usuario usuario, List<Long> listIds) {

        if (usuario != null && usuario.getEmpresa() != null) {

            IntegracaoWs integracao = this.serviceIntegracao.pesquisaIngracaoDiscador(usuario.getEmpresa().getId());

            if (integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.VONIX)) {

                this.serviceVonix.deletarContatosDiscadorVonix(integracao, listIds);

            }

        }

    }

    public String consultarContatos(Usuario usuario, Campanha campanha) throws ProativaException {

        if (usuario != null && usuario.getEmpresa() != null) {


            if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C) && campanha.getCampanha3c() != null) {

                IntegracaoWs integracao = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);

                if (integracao == null)
                    throw new ProativaException("Nenhuma integração com discador encontrada.");

                ResponseMailing responseMailing = this.tresCPlusServiceUtil.pesquisarMailingCampanha(integracao, campanha.getCampanha3c());

                int total = responseMailing.getData().stream().map(p -> (p.total)).reduce(0, Integer::sum);
                int totalDiscado = responseMailing.getData().stream().map(p -> (p.dialed)).reduce(0, Integer::sum);

                return "Total: " + total + ", Discado: " + totalDiscado;


            } else {

                IntegracaoWs integracao = this.serviceIntegracao.pesquisaIngracaoDiscador(usuario.getEmpresa().getId());

                if (integracao == null)
                    throw new ProativaException("Nenhuma integração com discador encontrada.");


                if (integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.VONIX) && campanha.getFila() != null && StringUtils.isNotBlank(campanha.getFila().getNome())) {

                    return this.serviceVonix.consultarContatosVonix(integracao, campanha.getFila().getNome());

                } else if (integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER)) {


                    CampanhaRetornoDiscagem campanhaRetorno = this.discadorPwd.consultarContatosDiscador(retornarIntegracao(integracao, usuario), campanha, usuario);

                    Integer totalClientes = campanhaRetorno.getTotalClientes() != null ? campanhaRetorno.getTotalClientes() : 0;

                    Integer totalTrabalhado = campanhaRetorno.getTotalClientesTrabalhados() != null ? campanhaRetorno.getTotalClientesTrabalhados() : 0;

                    if (totalClientes == 0 && totalTrabalhado == 0) {

                        return "0";

                    } else if (totalTrabalhado > totalClientes) {

                        Integer totalClientesRetra = campanhaRetorno.getTotalClientesPorcentagemRetrabalhados() != null ? campanhaRetorno.getTotalClientesPorcentagemRetrabalhados() : 0;

                        if (totalClientesRetra > 0) {

                            return "Retrabalhando clientes: " + String.valueOf(campanhaRetorno.getTotalClientesPorcentagemRetrabalhados());

                        } else {

                            return "0";
                        }

                    } else {

                        return String.valueOf(totalClientes - totalTrabalhado);

                    }

                } else {

                    throw new ProativaException("Integração não implementada, consulte o suporte.");
                }
            }
        }
        return null;
    }

    public boolean limparContatosFila(Usuario usuario, String nomeFila) throws ProativaException {


        if (usuario != null && usuario.getEmpresa() != null) {

            IntegracaoWs integracao = this.serviceIntegracao.pesquisaIngracaoDiscador(usuario.getEmpresa().getId());

            if (integracao == null)
                throw new ProativaException("Nehuma integração com discador disponível.");

            if (integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.VONIX)) {

                return this.serviceVonix.limparContatosFila(integracao, nomeFila);

            }

        }


        return false;
    }

    public void deslogar(Usuario usuario) throws ProativaException {

        if (usuario.getEmpresa() == null)
            return;

        if (usuario != null && usuario.getEmpresa() != null) {

            IntegracaoWs integracao = this.serviceIntegracao.pesquisaIngracaoDiscador(usuario.getEmpresa().getId());

            if (integracao != null) {

                if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracao.getTipoIntegracao())) {

                    this.discadorPwd.deslogarAgent(integracao, usuario);

                } else if (TipoIntegracaoEnum.VONIX.equals(integracao.getTipoIntegracao())) {


                }

            }

        }
    }

    public Map<String, String> logar(Usuario usuario) throws ProativaException {

        if (validarUsuario(usuario)) {
            return null;

        }

        IntegracaoWs integracao = this.serviceIntegracao.pesquisaIngracaoDiscador(usuario.getEmpresa().getId());

        if (integracao != null) {

            if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracao.getTipoIntegracao())) {

                return this.discadorPwd.logarAgent(integracao, usuario);
            }

        }

        return null;

    }

    public void ficarIndisponivelDiscador(IntegracaoWs integracaoContactCenterVirtual, Usuario usuario) throws ProativaException {

        if (integracaoContactCenterVirtual != null) {

            if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracaoContactCenterVirtual.getTipoIntegracao())) {

                this.discadorPwd.entrarEmPausa(retornarIntegracao(integracaoContactCenterVirtual, usuario), usuario, "");

            }

        }

    }

    public void ficarIndisponivelDiscador(IntegracaoWs integracaoContactCenterVirtual, Usuario usuario, String pausa) throws ProativaException {

        if (integracaoContactCenterVirtual != null) {

            if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracaoContactCenterVirtual.getTipoIntegracao())) {

                this.discadorPwd.entrarEmPausa(retornarIntegracao(integracaoContactCenterVirtual, usuario), usuario, pausa);

            }

        }

    }

    public Map<String, String> ficarDisponivelDiscador(IntegracaoWs integracaoContactCenter, Usuario usuario) throws ProativaException {


        if (integracaoContactCenter != null) {

            if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracaoContactCenter.getTipoIntegracao())) {

                Map<String, String> retorno = new HashMap<String, String>();

                String retornoDisponivel = this.discadorPwd.ficarDisponivelDiscadora(retornarIntegracao(integracaoContactCenter, usuario), usuario);

                if (StringUtils.isNotBlank(retornoDisponivel)) {

                    retorno = new HashMap<String, String>();

                    retorno.put("retornoDisponivel", retornoDisponivel);

                    return retorno;

                }

                return this.discadorPwd.unPauseDiscador(retornarIntegracao(integracaoContactCenter, usuario), usuario);

            }

        }
        return null;

    }

    public Map<String, String> ficarDisponivelDiscador(Usuario usuario) throws ProativaException {

        IntegracaoWs integracaoContactCenter = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_POWER_DIALER, usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);

        if (integracaoContactCenter != null) {

            if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracaoContactCenter.getTipoIntegracao())) {

                Map<String, String> retorno = new HashMap<String, String>();

                String retornoDisponivel = this.discadorPwd.ficarDisponivelDiscadora(retornarIntegracao(integracaoContactCenter, usuario), usuario);

                if (StringUtils.isNotBlank(retornoDisponivel)) {

                    retorno = new HashMap<String, String>();

                    retorno.put("retornoDisponivel", retornoDisponivel);

                    return retorno;

                }

                return this.discadorPwd.unPauseDiscador(retornarIntegracao(integracaoContactCenter, usuario), usuario);

            }

        }
        return null;

    }

    private IntegracaoWs retornarIntegracao(IntegracaoWs integracaoContactCenter, Usuario usuario) {

        if (TipoIntegracaoEnum.VIRTUAL_POWER_DIALER.equals(integracaoContactCenter.getTipoIntegracao())) {
            return this.serviceIntegracao.pesquisarIntegracao(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER, usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        }

        return integracaoContactCenter;
    }

    public Map<String, String> desassociarFilas(Usuario usuario, boolean removerFilaAtivo) throws ProativaException {

        if (validarUsuario(usuario))
            return null;


        IntegracaoWs integracao = this.serviceIntegracao.pesquisaIngracaoDiscador(usuario.getEmpresa().getId());

        if (integracao != null) {

            if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracao.getTipoIntegracao())) {


                return this.discadorPwd.desassociarFilasDiscadora(integracao, usuario, removerFilaAtivo);
            }

        }

        return null;
    }

    private boolean validarUsuario(Usuario usuario) {

        if (usuario == null || usuario.getEmpresa() == null || usuario.getPontoAtendimento() == null || StringUtils.isBlank(usuario.getPontoAtendimento().getRamal()) || usuario.getPerfil().equals(PerfilUsuarioEnum.ADMIN)) {
            return true;

        }

        return false;
    }

    public void iniciarPausaAtendimento(IntegracaoWs integracao, Usuario usuario, Pausa pausa, boolean isPausa) throws ProativaException {

        if (integracao != null) {

            if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracao.getTipoIntegracao())) {

                this.discadorPwd.entrarEmPausa(retornarIntegracao(integracao, usuario), usuario, pausa == null ? "" : pausa.getDescricao());
            }
        }


    }


}
