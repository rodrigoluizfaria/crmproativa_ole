package com.proativaservicos.service.asynchronous;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.util.ArquivoUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.NumeroUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.*;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AtendimentosExtrator implements Serializable {

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private ExtracaoImportacaoService serviceExtrator;

    @Inject
    private AtendimentoBackofficeService serviceAtendimentoBackoffice;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private SubMotivoService serviceSubmotivo;

    @Inject
    private MotivoService serviceMotivo;

    private List<Consistencia> listConsistencias;

    @Inject
    private ConsistenciaService serviceConsistencias;

    private List<Loja> listLojas;

    @Inject
    private LojaService serviceLoja;

    private List<StatusAtendimento> listStatusAtendimento;

    private Map<String, AtendimentoBackoffice> mapsAtendimentos;

    @Asynchronous
    public void importarArquivoExtrator(ExtratorImportacao extrator) {

        BufferedReader scanner = null;

        String logErro = "";

        try {

            if (extrator != null && extrator.getArquivo() != null) {

                JSONObject json = new JSONObject(extrator.getHeader());

                extrator.setStatusImportacao(StatusImportacaoExtratorEnum.IMPORTANDO);

                this.serviceExtrator.alterar(extrator);

                scanner = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(extrator.getArquivo()), "ISO-8859-1"));

                String[] cabecalho = scanner.readLine().split("[;]");

                String linha = null;

                inicializarVariaveis(extrator.getEmpresa().getId());

                this.mapsAtendimentos = new HashMap<String, AtendimentoBackoffice>();

                while ((linha = scanner.readLine()) != null) {

                    String[] dados = linha.replaceAll(";[ ]*", "; ").split("[;]");

                    AtendimentoBackoffice atendimentoBackoffice = new AtendimentoBackoffice();
                    atendimentoBackoffice.setListConsistencias(new ArrayList<Consistencia>());
                    atendimentoBackoffice.setInstituicaoFinanceira(extrator.getInstituicaoFinanceira());

                    JSONObject jsonComplementar = new JSONObject();

                    String cpf = "";

                    for (String keyCabe : json.keySet()) {

                        DadosBaseImportacaoExtratorEnum retornoEnum = DadosBaseImportacaoExtratorEnum.getBaseImportacaoEnum(json.getString(keyCabe));

                        int indice = Integer.valueOf(keyCabe).intValue();

                        if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.ADE)) {

                            atendimentoBackoffice.setAdesao(StringUtils.deleteWhitespace(dados[Integer.valueOf(indice)]));

                        } else if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.RESP_CORBAN)) {

                            atendimentoBackoffice.setConsistenciaCoban(StringUtils.deleteWhitespace(dados[indice]));

                        } else if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.COD_CONSISTENCIA)) {

                            atendimentoBackoffice.addCodConsistencia(StringUtils.deleteWhitespace(dados[indice]));

                        } else if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.CPF)) {


                            cpf = StringUtils.leftPad(dados[indice].replaceAll("\\D+", "").trim(), 11, "0");

                            atendimentoBackoffice.setCpf(cpf);

                        } else if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.ENTIDADE)) {

                            if (StringUtils.isNotBlank(dados[indice].trim()) && dados[indice].trim().contains("-")) {

                                atendimentoBackoffice.setEntidade(dados[indice].split("-")[0].trim());

                            } else {

                                atendimentoBackoffice.setEntidade(dados[indice].trim());
                            }

                        } else if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.LOJA)) {

                            String loja = StringUtils.deleteWhitespace(dados[indice]);

                            atendimentoBackoffice.setCodLoja(loja);

                        } else if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.DDD)) {

                            Telefone telefone = new Telefone();
                            converterDados(telefone, StringUtils.deleteWhitespace(dados[indice]), DadosBaseImportacaoExtratorEnum.DDD);
                            converterDados(telefone, StringUtils.deleteWhitespace(dados[indice + 1]), DadosBaseImportacaoExtratorEnum.TELEFONE);

                            if (telefone != null && telefone.getDdd() != null && StringUtils.isNotBlank(telefone.getNumero()))
                                atendimentoBackoffice.adicionarTelefone(telefone);

                        } else if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.DDD_TELEFONE)) {

                            try {

                                if (StringUtils.isNotBlank(dados[indice])) {

                                    Telefone telefone = new Telefone();
                                    converterDados((Serializable) telefone, StringUtils.deleteWhitespace(dados[indice]), DadosBaseImportacaoExtratorEnum.DDD_TELEFONE);

                                    if (telefone.getNumero() != null && !telefone.getNumero().isEmpty()) {

                                        String numero = telefone.getNumero().trim().replaceAll("[(]", "").replaceAll("[)]", "").replaceAll("[-]", "").replaceAll(" ", "").replaceAll("\\[0]", "");

                                        telefone.setNumero(StringUtils.substring(numero, 2));

                                        telefone.setDdd(Short.valueOf(Short.parseShort(StringUtils.substring(numero, 0, 2))));

                                    }


                                    atendimentoBackoffice.adicionarTelefone(telefone);
                                }
                            } catch (Exception e) {

                            }

                        } else if (retornoEnum.equals(DadosBaseImportacaoExtratorEnum.INFORMACOES_COMPLEMENTARES)) {

                            jsonComplementar.put(cabecalho[indice], dados[indice].trim());

                        } else if (!retornoEnum.equals(DadosBaseImportacaoExtratorEnum.TELEFONE)) {

                            converterDados(atendimentoBackoffice, dados[indice].trim(), retornoEnum);

                        }

                    }

                    if (this.mapsAtendimentos.containsKey(atendimentoBackoffice.getAdesao())) {

                        if (StringUtils.isNotBlank(this.mapsAtendimentos.get(atendimentoBackoffice.getAdesao()).getCodConsistencia())) {

                            this.mapsAtendimentos.get(atendimentoBackoffice.getAdesao()).setCodConsistencia(StringUtils.isNotBlank(this.mapsAtendimentos.get(atendimentoBackoffice.getAdesao()).getCodConsistencia()) ? (mapsAtendimentos.get(atendimentoBackoffice.getAdesao()).getCodConsistencia() + "," + atendimentoBackoffice.getCodConsistencia()) : atendimentoBackoffice.getCodConsistencia());

                        } else
                            this.mapsAtendimentos.get(atendimentoBackoffice.getAdesao()).setConsistenciaCoban(this.mapsAtendimentos.get(atendimentoBackoffice.getAdesao()).getConsistenciaCoban() + "," + atendimentoBackoffice.getConsistenciaCoban());

                        // criarConsistecias(mapsAtendimentos.get(atendimentoBackoffice.getAdesao()),
                        // extrator.getEmpresa().getId());

                    } else if (StringUtils.isNotBlank(atendimentoBackoffice.getAdesao())) {

                        atendimentoBackoffice.setExtrator(extrator);
                        validarLojas(atendimentoBackoffice, extrator);

                        if (!jsonComplementar.isEmpty())
                            atendimentoBackoffice.setInformacoesComplementares(jsonComplementar.toString());

                        this.mapsAtendimentos.put(atendimentoBackoffice.getAdesao(), atendimentoBackoffice);

                    }

                }

                // ARQUIVOS LIDOS INSERIR BANCO
                for (String adesao : this.mapsAtendimentos.keySet()) {

                    boolean inserir = true;

                    if (StringUtils.isNotBlank(adesao)) {

                        Atendimento proposta = this.serviceAtendimento.pesquisarPropostasPorAdesao(adesao.trim());

                        AtendimentoBackoffice atendimentoPesquisa = this.serviceAtendimentoBackoffice.pesquisarAtendimentoPorAdesao(adesao.trim(), extrator.getEmpresa().getId(), true);

                        if (atendimentoPesquisa == null && validarExtrator(extrator.getHeader()))
                            continue;

                        copiarPropiedades(atendimentoPesquisa, adesao, extrator);


                        if (proposta != null && proposta.getId() != null) {

                            this.mapsAtendimentos.get(adesao).setAtendimento(proposta);

                            this.mapsAtendimentos.get(adesao).setIntegrada(Boolean.TRUE);

                            extrator.setQtidadeImportadoIntegrado(extrator.getQtidadeImportadoIntegrado() + 1);

                        } else if (StringUtils.isNotBlank(mapsAtendimentos.get(adesao).getConsistenciaCoban())) {

                            this.mapsAtendimentos.get(adesao).setAtendimento(null);
                            this.mapsAtendimentos.get(adesao).setIntegrada(Boolean.FALSE);
                            extrator.setQtidadeImportadoIntegrado(extrator.getQtidadeImportadoIntegrado() + 1);
                            criarAtendimentoIntegrado(mapsAtendimentos.get(adesao), extrator.getUsuario());

                        }

                        //this.mapsAtendimentos.get(adesao).setExtrator(extrator);
                        //System.out.println("Validando....");

                        if (StringUtils.isNotBlank(this.mapsAtendimentos.get(adesao).getCodConsistencia())) {

                            separarConsistencias(this.mapsAtendimentos.get(adesao), this.mapsAtendimentos.get(adesao).getCodConsistencia());

                        } else {

                            criarConsistecias(this.mapsAtendimentos.get(adesao), this.mapsAtendimentos.get(adesao).getConsistenciaCoban(), extrator.getEmpresa().getId());
                        }

                        if (atendimentoPesquisa != null && StringUtils.isNotBlank(mapsAtendimentos.get(adesao).getStatusExtrator()) && mapsAtendimentos.get(adesao).getStatusExtrator().equalsIgnoreCase("Cancelada")) {

                            //validarStatusCancelada(mapsAtendimentos.get(adesao), extrator, "Cancelada",	"Não solucionado", "Cancelada", "Cancelada");

                            if (validarStatusMotivo(atendimentoPesquisa)) {

                                validarStatusExtratorCancelada(atendimentoPesquisa, extrator, "Tratativas Encerradas", "Cancelada", null);
                                //alterarUsuarioEmAtendimento(atendimentoPesquisa.getUsuarioAlteracao(),adesao);
                                atendimentoPesquisa.setSubmotivo(null);
                                inserir = true;

                            } else {

                                inserir = false;
                            }

                        } else if (atendimentoPesquisa != null && StringUtils.isNotBlank(mapsAtendimentos.get(adesao).getStatusExtrator()) && (mapsAtendimentos.get(adesao).getStatusExtrator().equalsIgnoreCase("Crédito Enviado") || mapsAtendimentos.get(adesao).getStatusExtrator().equalsIgnoreCase("Crdito Enviado"))) {


                            if (validarConsistencias(atendimentoPesquisa.getListConsistencias())) {

                                validarStatusExtratorCancelada(atendimentoPesquisa, extrator, "Tratativas Encerradas", "Crédito Enviado", "Solucionado via Proativa");

                                //	if(validarConsistenciasUsuarioPendente(listConsistencias))
                                //	alterarUsuarioEmAtendimento(atendimentoPesquisa.getUsuarioAlteracao(),adesao);

                            } else {

                                validarStatusExtratorCancelada(atendimentoPesquisa, extrator, "Tratativas Encerradas", "Crédito Enviado", "Não Solucionado via Proativa");
                                //this.mapsAtendimentos.get(adesao).setUsuarioAlteracao(extrator.getUsuario());
                            }

                            //	this.mapsAtendimentos.get(adesao).setDataAlteracao(new Date());

                        } else if (atendimentoPesquisa != null && StringUtils.isNotBlank(this.mapsAtendimentos.get(adesao).getStatusExtrator()) && (this.mapsAtendimentos.get(adesao).getStatusExtrator().equalsIgnoreCase("Integrado") || this.mapsAtendimentos.get(adesao).getStatusExtrator().equalsIgnoreCase("Integrada"))) {


                            if (validarConsistenciasUsuarioPendente(atendimentoPesquisa.getListConsistencias())) {

                                alterarUsuarioEmAtendimento(atendimentoPesquisa.getUsuarioAlteracao(), adesao);
                                this.mapsAtendimentos.get(adesao).setStatus(atendimentoPesquisa.getStatus());

                            }

                        } else if (StringUtils.isBlank(this.mapsAtendimentos.get(adesao).getConsistenciaCoban()) && atendimentoPesquisa != null) {

                            //validarStatusCancelada(mapsAtendimentos.get(adesao), extrator, "RESOLVIDO", "Resolvido","Sem Consistências", "Sem Consistências");
                        }


                        if (StringUtils.isNotBlank(this.mapsAtendimentos.get(adesao).getConsistenciaCoban()) || atendimentoPesquisa != null) {

                            if (mapsAtendimentos.get(adesao).getAtendimento() == null || this.mapsAtendimentos.get(adesao).getAtendimento().getId() == null)
                                criarAtendimentoIntegrado(this.mapsAtendimentos.get(adesao), extrator.getUsuario());

                            if (atendimentoPesquisa != null) {

                                this.mapsAtendimentos.get(adesao).setStatus(atendimentoPesquisa.getStatus());
                                this.mapsAtendimentos.get(adesao).setMotivo(atendimentoPesquisa.getMotivo());
                                this.mapsAtendimentos.get(adesao).setSubmotivo(atendimentoPesquisa.getSubmotivo());
                                this.mapsAtendimentos.get(adesao).setUsuarioAlteracao(atendimentoPesquisa.getUsuarioAlteracao());
                            }

                            if (inserir) {
                                this.mapsAtendimentos.get(adesao).setDataModificacaoExtrator(new Date());
                                this.serviceAtendimentoBackoffice.inserirAtendimentoConsistencias(this.mapsAtendimentos.get(adesao), extrator.getEmpresa().getId(), this.mapsAtendimentos.get(adesao).getListConsistencias(), ((this.mapsAtendimentos.get(adesao).getUsuarioAlteracao() != null && mapsAtendimentos.get(adesao).getUsuarioAlteracao().getId() != null) ? this.mapsAtendimentos.get(adesao).getUsuarioAlteracao().getId() : null));
                                extrator.setQtidadeImportado(extrator.getQtidadeImportado() + 1);
                            }
                        }

                    }

                }

            }

            extrator.setDataFim(new Date());
            System.out.println("finalizando.....");
            extrator.setStatusImportacao(StatusImportacaoExtratorEnum.FINALIZADA);
            extrator.setLog("Sucesso.");

            ArquivoUtil.escreverByteParaArquivo(extrator.getNomeArquivo(), "extrator", extrator.getArquivo());
            extrator.setArquivo(null);
            this.serviceExtrator.alterar(extrator);
            System.out.println("EXTRATOR FINALIZADO!");

        } catch (Exception e) {

            e.printStackTrace();

            if (extrator != null && extrator.getId() != null) {

                extrator.setStatusImportacao(StatusImportacaoExtratorEnum.FINALIZADA_ERRO);
                extrator.setDataFim(new Date());
                extrator.setLog(logErro);

                try {

                    this.serviceExtrator.alterar(extrator);

                } catch (ProativaException e1) {

                    e1.printStackTrace();
                }

            }

        } finally {

            if (scanner != null) {

                try {

                    scanner.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    private boolean validarStatusMotivo(AtendimentoBackoffice atendimento) {

        if (atendimento != null && (atendimento.getStatus() == null || atendimento.getStatus().getId() == null))
            return true;

        if (!atendimento.getStatus().getDescricao().toUpperCase().equalsIgnoreCase("TRATATIVAS ENCERRADAS")) {
            return true;
        }

        if (atendimento.getMotivo() == null || atendimento.getMotivo().getId() == null) {
            return true;
        }

        if (!atendimento.getMotivo().getDescricao().toUpperCase().equalsIgnoreCase("CANCELADA")) {
            return true;
        }

        if (atendimento.getSubmotivo() == null || atendimento.getSubmotivo().getId() == null) {
            return true;
        }

        return false;

    }

    private void copiarPropiedades(AtendimentoBackoffice atendimentoPesquisa, String adesao, ExtratorImportacao extrator) {

        boolean copiar = false;

        if (atendimentoPesquisa != null) {

            try {

                if (validarExtrator(extrator.getHeader())) {

                    if (StringUtils.isNotBlank(this.mapsAtendimentos.get(adesao).getStatusExtrator()))
                        atendimentoPesquisa.setStatusExtrator(this.mapsAtendimentos.get(adesao).getStatusExtrator());

                    BeanUtils.copyProperties(this.mapsAtendimentos.get(adesao), atendimentoPesquisa);

                } else {
                    copiar = true;
                }

            } catch (Exception e) {
                copiar = true;

            }

            if (copiar) {

                //System.out.println("AD "+atendimentoPesquisa.getAdesao()+" - "+com.proativaservicos.util.CollectionUtils.retornarStringNaoDuplicadas(atendimentoPesquisa.getConsistenciaCoban() ,",")   +" -- "+this.mapsAtendimentos.get(adesao).getConsistenciaCoban());

                this.mapsAtendimentos.get(adesao).setId(atendimentoPesquisa.getId());
                this.mapsAtendimentos.get(adesao).setEquipe(atendimentoPesquisa.getEquipe());
                this.mapsAtendimentos.get(adesao).setProtocolo(atendimentoPesquisa.getProtocolo());
                this.mapsAtendimentos.get(adesao).setUsuarioAlteracao(atendimentoPesquisa.getUsuarioAlteracao());
                this.mapsAtendimentos.get(adesao).setUsuarioCadastro(atendimentoPesquisa.getUsuarioCadastro());
                this.mapsAtendimentos.get(adesao).setInstituicaoFinanceira(extrator.getInstituicaoFinanceira());
                this.mapsAtendimentos.get(adesao).setDataCadastro(atendimentoPesquisa.getDataCadastro());
                this.mapsAtendimentos.get(adesao).setProspect(atendimentoPesquisa.getProspect());

                if (this.mapsAtendimentos.get(adesao).getLoja() == null && atendimentoPesquisa.getLoja() != null)
                    this.mapsAtendimentos.get(adesao).setLoja(atendimentoPesquisa.getLoja());

                if (atendimentoPesquisa.getExtrator() != null && atendimentoPesquisa.getExtrator().getId() != null)
                    this.mapsAtendimentos.get(adesao).setExtrator(atendimentoPesquisa.getExtrator());

                if (StringUtils.isBlank(this.mapsAtendimentos.get(adesao).getCpf()))
                    this.mapsAtendimentos.get(adesao).setCpf(atendimentoPesquisa.getCpf());

                if (StringUtils.isBlank(this.mapsAtendimentos.get(adesao).getCodLoja()))
                    this.mapsAtendimentos.get(adesao).setCodLoja(atendimentoPesquisa.getCodLoja());

                if (StringUtils.isBlank(this.mapsAtendimentos.get(adesao).getNome()))
                    this.mapsAtendimentos.get(adesao).setNome(atendimentoPesquisa.getNome());

                if (StringUtils.isBlank(this.mapsAtendimentos.get(adesao).getUsuarioArquivo()))
                    this.mapsAtendimentos.get(adesao).setNome(atendimentoPesquisa.getUsuarioArquivo());

                if (StringUtils.isBlank(this.mapsAtendimentos.get(adesao).getConsistenciaCoban()))
                    this.mapsAtendimentos.get(adesao).setConsistenciaCoban(com.proativaservicos.util.CollectionUtils.retornarStringNaoDuplicadas(atendimentoPesquisa.getConsistenciaCoban(), ","));

                if (StringUtils.isBlank(this.mapsAtendimentos.get(adesao).getConsistenciaBanco()))
                    this.mapsAtendimentos.get(adesao).setConsistenciaBanco(com.proativaservicos.util.CollectionUtils.retornarStringNaoDuplicadas(atendimentoPesquisa.getConsistenciaBanco(), ","));

                if (this.mapsAtendimentos.get(adesao).getValor() == null)
                    this.mapsAtendimentos.get(adesao).setValor(atendimentoPesquisa.getValor());


                if (atendimentoPesquisa.getStatus() != null && atendimentoPesquisa.getStatus().getId() != null) {

                    this.mapsAtendimentos.get(adesao).setStatus(atendimentoPesquisa.getStatus());

                    if (atendimentoPesquisa.getMotivo() != null && atendimentoPesquisa.getMotivo().getId() != null) {

                        this.mapsAtendimentos.get(adesao).setMotivo(atendimentoPesquisa.getMotivo());

                        if (atendimentoPesquisa.getSubmotivo() != null && atendimentoPesquisa.getSubmotivo().getId() != null)
                            this.mapsAtendimentos.get(adesao).setSubmotivo(atendimentoPesquisa.getSubmotivo());
                    }

                }

                if (StringUtils.isBlank(this.mapsAtendimentos.get(adesao).getStatusExtrator())) {
                    this.mapsAtendimentos.get(adesao).setStatusExtrator(atendimentoPesquisa.getStatusExtrator());
                }

                if (atendimentoPesquisa.getProduto() != null && atendimentoPesquisa.getProduto().getId() != null)
                    this.mapsAtendimentos.get(adesao).setProduto(atendimentoPesquisa.getProduto());

                this.mapsAtendimentos.get(adesao).setDataAlteracao(atendimentoPesquisa.getDataAlteracao());
                this.mapsAtendimentos.get(adesao).setUsuarioAlteracao(atendimentoPesquisa.getUsuarioAlteracao());
                this.mapsAtendimentos.get(adesao).setUsuarioCadastro(atendimentoPesquisa.getUsuarioCadastro());

                if (CollectionUtils.isNotEmpty(atendimentoPesquisa.getListConsistencias()))
                    this.mapsAtendimentos.get(adesao).setListConsistencias(atendimentoPesquisa.getListConsistencias());

            }

            extrator.setQuantidadeAdesaoEncontrada(extrator.getQuantidadeAdesaoEncontrada() + 1);


        } else {

            extrator.setQuantidadeAdesaoNaoEncontrada(extrator.getQuantidadeAdesaoNaoEncontrada() + 1);
            this.mapsAtendimentos.get(adesao).setDataCadastro(new Date());
            this.mapsAtendimentos.get(adesao).setUsuarioCadastro(extrator.getUsuario());
        }


    }

    public boolean validarExtrator(String jsonStr) {

        try {

            JSONObject jso = new JSONObject(jsonStr);

            if (jsonStr.contains("ADE") && jsonStr.contains("STATUS") && jso.length() == 2) {
                return true;
            }

        } catch (Exception e) {
        }

        return false;

    }

    private void alterarUsuarioEmAtendimento(Usuario usuario, String adesao) {

        if (usuario != null && usuario.getId() != null) {
            this.mapsAtendimentos.get(adesao).setUsuarioEmAtendimento(usuario);

        }
    }

    public boolean validarConsistencias(List<Consistencia> listaConsistencias) {

        if (CollectionUtils.isEmpty(listaConsistencias))
            return false;


        return listaConsistencias.stream().anyMatch(c -> (c.getTratada() != null && c.getTratada()) && !c.getResponsabilidade().equalsIgnoreCase("BMG"));


    }

    public boolean validarConsistenciasUsuarioPendente(List<Consistencia> listaConsistencias) {

        if (CollectionUtils.isEmpty(listaConsistencias))
            return false;

        return listaConsistencias.stream().anyMatch(c -> (c.getTratada() == null || !c.getTratada()) && !c.getResponsabilidade().equalsIgnoreCase("BMG"));


    }

    private void inicializarVariaveis(Long idEmpresa) {

        this.listStatusAtendimento = this.serviceStatusAtendimento.pesquisarStatusAtendimentosBackoffice(idEmpresa, null, true);

        this.listConsistencias = this.serviceConsistencias.pesquisarConsistenciaPorEmpresa(idEmpresa);

    }


    private void separarConsistencias(AtendimentoBackoffice atendimentoBackoffice, String resp) {

        if (StringUtils.isNotEmpty(resp)) {

            List<Integer> listaConsistencias = new ArrayList<Integer>();

            if (StringUtils.contains(resp, ",")) {

                String[] arrayConsistencias = resp.split("[,]");

                for (String concistencia : arrayConsistencias) {

                    String aux = StringUtils.deleteWhitespace(concistencia);

                    if (StringUtils.isNumeric(aux.trim())) {

                        listaConsistencias.add(Integer.valueOf(aux.trim()));
                    }
                }

            } else if (StringUtils.isNumeric(StringUtils.deleteWhitespace(resp))) {

                listaConsistencias.add(Integer.valueOf(StringUtils.deleteWhitespace(resp)));

            }

            if (CollectionUtils.isNotEmpty(listaConsistencias)) {

                atendimentoBackoffice.setListConsistencias(validarCodigosConcistencias(com.proativaservicos.util.CollectionUtils.removeDuplicates(listaConsistencias), atendimentoBackoffice));

            }
        }


    }

    private void validarStatusExtratorCancelada(AtendimentoBackoffice atendimentoBackoffice, ExtratorImportacao extrator, String status, String motivo, String subMotivo) {

        try {

            //System.out.println("SUBMOTIVO "+subMotivo+" - MOTIVO: "+motivo);

            atendimentoBackoffice.setStatus(criarStatus(status, motivo, subMotivo, null, extrator));

            atendimentoBackoffice.setMotivo(atendimentoBackoffice.getStatus().getMotivo());

            atendimentoBackoffice.setSubmotivo(atendimentoBackoffice.getStatus().getSubmotivo());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private StatusAtendimento criarStatus(String status, String strMotivo, String submotivo, AcaoStatusAtendimentoEnum acao, ExtratorImportacao extrator) throws ProativaException {

        StatusAtendimento statusAtendimento = null;

        if (StringUtils.isNotBlank(status)) {

            Optional<StatusAtendimento> op = this.listStatusAtendimento.stream().filter(s -> s.getDescricao().equalsIgnoreCase(status)).findFirst();

            if (op.isPresent()) {

                statusAtendimento = op.get();

            } else {

                statusAtendimento = new StatusAtendimento();
                statusAtendimento.setDescricao(status);
                statusAtendimento.setEmpresa(extrator.getEmpresa());
                statusAtendimento.setAtivo(TipoAcessoEnum.ATIVO);
                statusAtendimento.setAcao(AcaoStatusAtendimentoEnum.CONCLUIR);
                statusAtendimento.setUsuarioAlteracao(extrator.getUsuario());
                statusAtendimento.setUsuarioCadastro(extrator.getUsuario());
                statusAtendimento.setDataCadastro(new Date());
                statusAtendimento.setDataAlteracao(new Date());
                statusAtendimento.setBackoffice(Boolean.TRUE);
                this.serviceStatusAtendimento.inserir(statusAtendimento);
                this.listStatusAtendimento.add(statusAtendimento);
            }

            statusAtendimento.setMotivo(null);
            statusAtendimento.setSubmotivo(null);
            //System.out.println("SUBMOTIVO: "+submotivo);

            if (CollectionUtils.isNotEmpty(statusAtendimento.getListMotivos()) && StringUtils.isNotBlank(strMotivo)) {

                Optional<Motivo> opMotivo = statusAtendimento.getListMotivos().stream().filter(m -> m.getDescricao().equalsIgnoreCase(strMotivo)).findFirst();
                statusAtendimento.setMotivo(opMotivo.isPresent() ? opMotivo.get() : null);

                if (opMotivo.isPresent() && StringUtils.isNotBlank(submotivo)) {

                    Optional<SubMotivo> opSubMotivo = null;

                    if (CollectionUtils.isNotEmpty(opMotivo.get().getListSubMotivos()))
                        opSubMotivo = opMotivo.get().getListSubMotivos().stream().filter(sb -> sb.getDescricao().equalsIgnoreCase(submotivo)).findFirst();

                    if (opSubMotivo.isPresent()) {

                        statusAtendimento.setSubmotivo(opSubMotivo.get());

                    } else {

                        statusAtendimento.setSubmotivo(criarSubMotivo(extrator, submotivo, opMotivo.get()));
                        opMotivo.get().getListSubMotivos().add(statusAtendimento.getSubmotivo());
                    }

                }

            }

            if (statusAtendimento.getMotivo() == null && StringUtils.isNotBlank(strMotivo)) {

                statusAtendimento.setMotivo(criarMotivo(extrator, strMotivo, submotivo, statusAtendimento));
                statusAtendimento.setSubmotivo(CollectionUtils.isNotEmpty(statusAtendimento.getMotivo().getListSubMotivos()) ? statusAtendimento.getMotivo().getListSubMotivos().get(0) : null);
                //statusAtendimento.getListMotivos().add(statusAtendimento.getMotivo());

            }


        }

        return statusAtendimento;
    }

    private void validarStatusCancelada(AtendimentoBackoffice atendimentoBackoffice, ExtratorImportacao extrator, String... l) {

        try {

            if (atendimentoBackoffice != null && atendimentoBackoffice.getStatusExtrator() != null && (atendimentoBackoffice.getStatusExtrator().equalsIgnoreCase(l[0]) || l[0].equalsIgnoreCase("RESOLVIDO"))) {

                StatusAtendimento status = this.serviceStatusAtendimento.pesquisarStatusAtendimentosBackoffice(extrator.getEmpresa().getId(), l[1]);

                if (status == null) {

                    status = new StatusAtendimento();
                    status.setDescricao(l[1]);
                    status.setEmpresa(extrator.getEmpresa());
                    status.setAtivo(TipoAcessoEnum.ATIVO);
                    status.setAcao(AcaoStatusAtendimentoEnum.CONCLUIR);
                    status.setUsuarioAlteracao(extrator.getUsuario());
                    status.setUsuarioCadastro(extrator.getUsuario());
                    status.setDataCadastro(new Date());
                    status.setDataAlteracao(new Date());
                    status.setBackoffice(Boolean.TRUE);
                    this.serviceStatusAtendimento.inserir(status);

                    if (StringUtils.isNotBlank(l[2])) {
                        Motivo motivo = criarMotivo(extrator, l[2], status, true);
                        status.setListMotivos(new ArrayList<Motivo>());
                        status.getListMotivos().add(motivo);
                    }
                }

                if (status != null && status.getId() != null) {

                    atendimentoBackoffice.setStatus(status);

                    if (CollectionUtils.isNotEmpty(status.getListMotivos())) {

                        for (Motivo motivo : status.getListMotivos()) {

                            if (motivo.getDescricao().equalsIgnoreCase(l[2])) {

                                atendimentoBackoffice.setMotivo(motivo);
                                break;

                            }

                        }

                        if (atendimentoBackoffice.getMotivo() == null)
                            atendimentoBackoffice.setMotivo(criarMotivo(extrator, l[2], status, true));

                        if (atendimentoBackoffice.getMotivo() != null) {

                            List<SubMotivo> listSubmo = this.serviceSubmotivo.pesquisarSubMotivosPorMotivo(atendimentoBackoffice.getMotivo().getId(), null);

                            for (SubMotivo subMotivo : listSubmo) {

                                if (subMotivo.getDescricao().equalsIgnoreCase(l[3])) {
                                    atendimentoBackoffice.setSubmotivo(subMotivo);
                                    break;
                                }

                            }

                            if (atendimentoBackoffice.getSubmotivo() == null) {
                                atendimentoBackoffice.setSubmotivo(criarSubMotivo(extrator, l[3], atendimentoBackoffice.getMotivo()));
                            }

                        }

                    } else {

                        atendimentoBackoffice.setMotivo(criarMotivo(extrator, l[2], status, true));
                        atendimentoBackoffice.setSubmotivo(criarSubMotivo(extrator, l[3], atendimentoBackoffice.getMotivo()));
                        atendimentoBackoffice.getStatus().setListMotivos(new ArrayList<Motivo>());
                        atendimentoBackoffice.getStatus().getListMotivos().add(atendimentoBackoffice.getMotivo());
                        this.serviceStatusAtendimento.alterar(atendimentoBackoffice.getStatus());

                    }

                    //atendimentoBackoffice.setUsuarioAlteracao(extrator.getUsuario());

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private Motivo criarMotivo(ExtratorImportacao extrator, String strMotivo, String strSubmotivo, StatusAtendimento status) {

        try {

            Motivo motivo = criarMotivo(extrator, strMotivo, status, false);

            SubMotivo subMotivo = criarSubMotivo(extrator, strSubmotivo, motivo);

            motivo.getListSubMotivos().add(subMotivo);

            this.listStatusAtendimento = this.serviceStatusAtendimento.pesquisarStatusAtendimentosBackoffice(extrator.getEmpresa().getId(), null, true);

            return motivo;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private Motivo criarMotivo(ExtratorImportacao extrator, String descricao, StatusAtendimento status, boolean pesquisarStatus) throws ProativaException {

        Motivo motivo = new Motivo();
        motivo.setDescricao(descricao);
        motivo.setEmpresa(extrator.getEmpresa());
        motivo.setDataCadastro(new Date());
        motivo.setDataAlteracao(new Date());
        motivo.setUsuarioCadastro(extrator.getUsuario());
        motivo.setListSubMotivos(new ArrayList<SubMotivo>());

        this.serviceMotivo.inserir(motivo);

        this.serviceStatusAtendimento.inserirStatusAtendimentoMotivo(status.getId(), motivo.getId());

        if (pesquisarStatus)
            this.listStatusAtendimento = this.serviceStatusAtendimento.pesquisarStatusAtendimentosBackoffice(extrator.getEmpresa().getId(), null, true);

        return motivo;

    }

    private SubMotivo criarSubMotivo(ExtratorImportacao extrator, String descricao, Motivo motivo) throws ProativaException {

        SubMotivo subMotivo = new SubMotivo();
        subMotivo.setDescricao(descricao);
        subMotivo.setAcao(AcaoStatusAtendimentoEnum.CONCLUIR);
        subMotivo.setDataCadastro(new Date());
        subMotivo.setDataAlteracao(new Date());
        subMotivo.setUsuarioCadastro(extrator.getUsuario());
        subMotivo.setUsuarioAlteracao(extrator.getUsuario());
        subMotivo.setEmpresa(extrator.getEmpresa());
        subMotivo.setMotivo(motivo);

        this.serviceSubmotivo.inserir(subMotivo);
        this.listStatusAtendimento = this.serviceStatusAtendimento.pesquisarStatusAtendimentosBackoffice(extrator.getEmpresa().getId(), null, true);

        return subMotivo;

    }

    private void criarAtendimentoIntegrado(AtendimentoBackoffice atendimento, com.proativaservicos.model.Usuario usuario) {

        try {

            if (atendimento != null) {

                Atendimento propostas = new Atendimento();
                propostas.setAdesao(atendimento.getAdesao());
                propostas.setUsuarioCadastro(usuario);
                propostas.setCodigoLoja(atendimento.getCodLoja());
                propostas.setObservacao("NOVO ATENDIMENTO BACKOFFICE: ");
                propostas.setInstituicaoFinanceira(atendimento.getInstituicaoFinanceira());
                propostas.setDataCadastro(new Date());
                propostas.setBko(Boolean.TRUE);
                propostas.setCpf(atendimento.getCpf());
                propostas.setNome(atendimento.getNome());
                propostas.setEntidadePrincipal(atendimento.getEntidade());
                propostas.setValorLiberado(atendimento.getValor());

                if (CollectionUtils.isNotEmpty(atendimento.getListTelefones())) {

                    for (GenericTelefone tel : atendimento.getListTelefones()) {

                        propostas.adicionarTelefone(tel);

                    }

                }

                this.serviceAtendimento.inserir(propostas);
                atendimento.setAtendimento(propostas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void validarLojas(AtendimentoBackoffice atendimento, ExtratorImportacao extrator) {

        if (StringUtils.isNotBlank(atendimento.getCodLoja())) {

            if (CollectionUtils.isEmpty(this.listLojas))
                this.listLojas = new ArrayList<Loja>();

            Optional<Loja> op = this.listLojas.stream().filter(l -> l.getCodigoLoja().trim().equalsIgnoreCase(atendimento.getCodLoja().trim())).findFirst();

            Loja lojaAux = null;

            if (!op.isPresent()) {

                lojaAux = this.serviceLoja.pesquisarLojasPorCodigo(atendimento.getCodLoja(), extrator.getInstituicaoFinanceira(), extrator.getEmpresa().getId());

                if (lojaAux != null)
                    this.listLojas.add(lojaAux);

            } else {

                lojaAux = op.get();
            }

            if (lojaAux == null) {

                Loja l = new Loja(atendimento.getCodLoja());
                l.setIdEmpresa(extrator.getEmpresa().getId());
                l.setInstituicaoFinanceira(extrator.getInstituicaoFinanceira());
                l.setAtivo(TipoAcessoEnum.INATIVO);
                l = this.serviceLoja.inserirLojaExtrator(l);

                if (l != null) {

                    this.listLojas.add(l);
                    atendimento.setLoja(l);

                }

            } else {

                atendimento.setLoja(lojaAux);

            }

        }
    }

    private void criarConsistecias(AtendimentoBackoffice atendimento, String resp, Long empresa) {

        if (StringUtils.isNotEmpty(resp)) {

            List<Integer> listaConsistencias = new ArrayList<Integer>();

            if (StringUtils.contains(resp, ",")) {

                String[] arrayConsistencias = resp.split("[,]");

                for (String concistencia : arrayConsistencias) {

                    String aux = StringUtils.deleteWhitespace(concistencia);

                    if (StringUtils.isNumeric(aux.trim())) {

                        listaConsistencias.add(Integer.valueOf(aux.trim()));
                    }
                }

            } else if (StringUtils.isNumeric(StringUtils.deleteWhitespace(resp))) {

                listaConsistencias.add(Integer.valueOf(StringUtils.deleteWhitespace(resp)));

            }

            if (CollectionUtils.isNotEmpty(listaConsistencias)) {

                atendimento.setListConsistencias(validarConcistencias(listaConsistencias, atendimento));

            }
        }

    }

    private List<Consistencia> validarConcistencias(List<Integer> listCodigosConcistencias, AtendimentoBackoffice atendimento) {

        List<Consistencia> listRetornoConsistencias = new ArrayList<Consistencia>();

        for (Integer codigo : listCodigosConcistencias) {

            for (Consistencia consistencia : this.listConsistencias) {

                if (consistencia.getCodigoConcistencia().equals(codigo)) {

                    listRetornoConsistencias.add(consistencia);
                }

            }

        }

        if (CollectionUtils.isNotEmpty(listRetornoConsistencias) && atendimento != null && atendimento.getId() != null) {

            List<Consistencia> consistenciasAux = this.serviceConsistencias.pesquisarConsistenciasNaoExistentes(listRetornoConsistencias.stream().map(Consistencia::getId).collect(Collectors.toList()), atendimento.getId());

            if (CollectionUtils.isNotEmpty(consistenciasAux)) {

                listRetornoConsistencias.removeAll(consistenciasAux);

            }

        }

        if (CollectionUtils.isEmpty(atendimento.getListConsistencias())) {
            return listRetornoConsistencias;
        }

        for (Consistencia consistencia : listRetornoConsistencias) {

            if (!(atendimento.getListConsistencias().stream().anyMatch(c -> c.getId() == consistencia.getId()))) {
                atendimento.getListConsistencias().add(consistencia);
            }

        }

        //atendimento.getListConsistencias().add
        return atendimento.getListConsistencias();

    }

    private List<Consistencia> validarCodigosConcistencias(List<Integer> listCodigosConcistencias, AtendimentoBackoffice atendimento) {

        List<Consistencia> listRetornoConsistencias = new ArrayList<Consistencia>();

        for (Integer codigo : listCodigosConcistencias) {

            for (Consistencia consistencia : this.listConsistencias) {

                if (consistencia.getCodigoConcistencia().equals(codigo)) {

                    if (consistencia.getResponsabilidade().equalsIgnoreCase("CORBAN") || consistencia.getResponsabilidade().equalsIgnoreCase("Cliente")) {

                        listRetornoConsistencias.add(consistencia);
                        if (StringUtils.isBlank(atendimento.getConsistenciaCoban()) || (!atendimento.getConsistenciaCoban().contains(consistencia.getCodigoConcistencia().toString())))
                            atendimento.setConsistenciaCoban(StringUtils.isNotBlank(atendimento.getConsistenciaCoban()) ? (atendimento.getConsistenciaCoban() + "," + consistencia.getCodigoConcistencia().toString()) : consistencia.getCodigoConcistencia().toString());

                    } else {
                        if (StringUtils.isBlank(atendimento.getConsistenciaBanco()) || (!atendimento.getConsistenciaBanco().contains(consistencia.getCodigoConcistencia().toString())))
                            atendimento.setConsistenciaBanco(StringUtils.isNotBlank(atendimento.getConsistenciaBanco()) ? (atendimento.getConsistenciaBanco() + "," + consistencia.getCodigoConcistencia().toString()) : consistencia.getCodigoConcistencia().toString());

                    }
                }

            }

        }

        if (CollectionUtils.isNotEmpty(listRetornoConsistencias) && atendimento != null && atendimento.getId() != null) {

            List<Consistencia> consistenciasAux = this.serviceConsistencias.pesquisarConsistenciasNaoExistentes(listRetornoConsistencias.stream().map(Consistencia::getId).collect(Collectors.toList()), atendimento.getId());

            if (CollectionUtils.isNotEmpty(consistenciasAux)) {

                listRetornoConsistencias.removeAll(consistenciasAux);

            }

        }

//// ALTERAR AQUI
        return listRetornoConsistencias;

    }

    private Method criarMetodo(Serializable entidade, DadosBaseImportacaoExtratorEnum tipoDadosImportacao,
                               Class<?> classe) throws Exception {

        try {

            return entidade.getClass().getDeclaredMethod(tipoDadosImportacao.getMetodo(), new Class[]{classe});

        } catch (NoSuchMethodException e) {

            try {

                return entidade.getClass().getSuperclass().getDeclaredMethod(tipoDadosImportacao.getMetodo(),
                        new Class[]{classe});

            } catch (Exception e1) {

                return entidade.getClass().getSuperclass().getSuperclass()
                        .getDeclaredMethod(tipoDadosImportacao.getMetodo(), new Class[]{classe});
            }
        }
    }

    private void converterDados(Serializable entidade, String informacao, DadosBaseImportacaoExtratorEnum tipoDadosImportacao) throws Exception {

        String valor = (informacao.trim().length() <= tipoDadosImportacao.getTamanho()) ? informacao.trim()
                : informacao.trim().substring(0, tipoDadosImportacao.getTamanho()).trim();

        if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.STRING)) {

            criarMetodo(entidade, tipoDadosImportacao, String.class).invoke(entidade, new Object[]{valor.isEmpty() ? null : valor});
        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.INTEGER)) {

            criarMetodo(entidade, tipoDadosImportacao, Integer.class).invoke(entidade,
                    new Object[]{Utils.isInteiro(valor) ? Integer.valueOf(Integer.parseInt(valor)) : null});

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.DOUBLE)) {

            criarMetodo(entidade, tipoDadosImportacao, BigDecimal.class).invoke(entidade, new Object[]{
                    validarNumero(valor) ? NumeroUtil.builder(valor).formatarBigDecimal().getBigdecimal() : null});

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.SHORT)) {

            criarMetodo(entidade, tipoDadosImportacao, Short.class).invoke(entidade, new Object[]{validarNumero(valor) ? Short.valueOf(Short.parseShort(valor)) : null});

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.DATE)) {

            criarMetodo(entidade, tipoDadosImportacao, Date.class).invoke(entidade, new Object[]{
                    valor.isEmpty() ? null : DateUtil.builder(valor.trim()).formatarStringParaData().getData()});

        }
    }

    private boolean validarNumero(String valor) {

        try {

            if (valor.contains("$")) {
                valor = valor.replaceAll("R$", "").trim();
            }

            if (valor.contains("R")) {
                valor = valor.replaceAll("R$", "").trim();
            }
            return valor.trim().isEmpty() ? false
                    : NumberUtils.isCreatable(
                    valor.replaceAll("[.]", "").replaceAll("[,]", ".").replaceAll("[R$]", "").trim());

        } catch (java.lang.NumberFormatException e) {

            return false;
        }
    }

    public static void main(String[] args) {

        String tel = "(95)3626-2829[0]";

        System.out
                .println(tel.replaceAll("[(]", "").replaceAll("[)]", "").replaceAll("[-]", "").replaceAll("\\[0]", ""));


        ////	String str ="15,15,23,17,52,15,23";
        //AtendimentosExtrator ex = new AtendimentosExtrator();


        /*
         * String arquivoCSV =
         * "C:\\Users\\Rodrigo\\Documents\\AcompanhamentoConsignacao_23_05_2022_11_54.csv";
         *
         * BufferedReader br = null; String linha = ""; String csvDivisor = ",";
         *
         * try {
         *
         * File csvSaida = new File("C:\\Users\\Rodrigo\\Documents\\loja_sp_final.csv");
         * FileInputStream fis = new FileInputStream(arquivoCSV); br = new
         * BufferedReader(new InputStreamReader(fis,"ISO-8859-1")); br.readLine();
         *
         * // Map<String, String> mapTelCpf = new HashMap<String, String>(); String head
         * = br.readLine();
         *
         * while ((linha = br.readLine()) != null) {
         *
         * int indiceCorban = linha.indexOf("\"");
         *
         * if(indiceCorban > 0) {
         *
         * String returno = linha.substring(indiceCorban);
         *
         * String arrayRetorno[] = StringUtils.split(returno,"\"");
         *
         *
         * System.out.println("------------------------------");
         *
         * //System.out.println(returno);
         *
         * //System.out.println(returno.substring(1,
         * returno.substring(1).indexOf("\""))); int indice = 0;
         *
         * for(int i=linha.length() ;i > 0;i--) {
         *
         * if(StringUtils.isAlpha(String.valueOf(linha.charAt(i-1))) &&
         * linha.charAt(i-1) !='\"' && linha.charAt(i-1) != ',' ){
         * System.out.println(linha.charAt(i-1)); System.out.println(linha.substring(0,
         * i)); indice = i; break;
         *
         * }
         *
         *
         * } // System.out.println(indice); String strConsistencias =
         * linha.substring(indice+1);
         *
         * System.out.println(strConsistencias);
         *
         * for(int i=0 ;i < strConsistencias.length() ;i++) {
         *
         * int qtdade = StringUtils.countMatches(strConsistencias, '\"');
         *
         * if(qtdade==2 && strConsistencias.startsWith("\"")) {
         *
         * // System.out.println("Somente valor: "+strConsistencias);
         *
         *
         * }else {
         *
         * }
         *
         *
         * }
         */

        /*
         * for(String str: arrayRetorno) {
         *
         * if(!str.startsWith(",") &&
         * StringUtils.isNotBlank(StringUtils.trimToEmpty(str))) {
         * //System.out.println(str);
         *
         * }else {
         *
         * //System.out.println("\nOUTRO: >>>>>>> "+str+"\n"); } }
         */

        // System.out.println(linha.substring(0, indiceCorban-1));
        System.out.println("------------------------------");

        /*
         * }
         *
         * }
         *
         * } catch (Exception e) {
         *
         * e.printStackTrace(); }
         */

        /*
         * Consistencia concistencia1 = new Consistencia(); concistencia1.setId(3L);
         *
         * Consistencia concistencia2 = new Consistencia(); concistencia2.setId(2L);
         *
         * Consistencia concistencia3 = new Consistencia(); concistencia3.setId(3L);
         *
         * Consistencia concistencia4 = new Consistencia(); concistencia4.setId(4L);
         *
         * List<Consistencia> listConsistencias = new ArrayList<Consistencia>();
         * listConsistencias.add(concistencia1); listConsistencias.add(concistencia2);
         *
         * List<Consistencia> listConsistenciasPesquisa = new ArrayList<Consistencia>();
         *
         * listConsistenciasPesquisa.add(concistencia3);
         * listConsistenciasPesquisa.add(concistencia4);
         *
         * List<Consistencia> listFinal = listConsistenciasPesquisa.stream().filter(f ->
         * !listConsistencias.contains(f)) .collect(Collectors.toList());
         *
         * for (Consistencia consistencia : listFinal) {
         *
         * System.out.println(consistencia.getId());
         *
         * }
         *
         * String resp = "150,208,270";
         *
         * if (StringUtils.isNotEmpty(resp)) {
         *
         * if (StringUtils.contains(resp, ",")) {
         *
         * String[] arrayConsistencias = resp.split("[,]");
         *
         * List<String> listaConsistencias = new ArrayList<String>();
         *
         * for (String concistencia : arrayConsistencias) {
         *
         * if (StringUtils.isNumeric(concistencia)) {
         *
         * listaConsistencias.add(concistencia); } }
         *
         * for (String string : listaConsistencias) { System.out.println(string); }
         *
         * } else if (StringUtils.isNumeric(resp)) { System.err.println(resp);
         *
         * }
         *
         * }
         */
    }

}
