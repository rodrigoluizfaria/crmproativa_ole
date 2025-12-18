package com.proativaservicos.util;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.*;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import com.thoughtworks.xstream.XStream;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Named
public class WsdlCartaoBeneficioUtil implements Serializable {


    private static final long serialVersionUID = 1L;

    @Inject
    private RegistroSistemaUtil registro;

    public static String URL_CARTAO_BENEFICIO = "/CartaoBeneficio?wsdl";


    public String validarSePossuiCartaoBeneficio(String Urlsdl, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Long idCampanha, Usuario usuario, boolean gravarLog, boolean verificarLink)
            throws ProativaException {


        ValidaSeJaPossuiContaCartaoParameter param = null;
        ValidaSeJaPossuiContaCartaoRetorno retorno = null;
        CartaoBeneficioWebService service = null;
        String strRetorno = null;
        String logErro = null;
        VerificarLinkUtil.verificarLink(Urlsdl);


        param = new ValidaSeJaPossuiContaCartaoParameter();
        param.setLogin(usrWsdl);
        param.setSenha(senhaWsdl);
        param.setCpf(atendimento.getCpf());
        param.setCodigoEntidade(atendimento.getEntidadePrincipal() == null ? null : Integer.parseInt(atendimento.getEntidadePrincipal()));
        param.setSequencialOrgao((StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) ? Integer.valueOf(atendimento.getOrgaoPrincipal()) : null));
        param.setMatricula(atendimento.getBeneficioPrincipal().trim());

        try {

            service = (new CartaoBeneficioWebServiceServiceLocator()).getCartaoBeneficio(new URL(Urlsdl + URL_CARTAO_BENEFICIO));

            retorno = service.validaSeJaPossuiContaCartao(param);


            if (retorno != null) {
                System.out.println(retorno.getNumeroCartao());
                if (retorno.getMensagemDeErro() == null || StringUtils.isBlank(retorno.getMensagemDeErro().trim())) {

                    atendimento.setObservacao("sem_retorno_de_erro_ws_para_proposta_no_bmg");


                } else {

                    atendimento.setObservacao(retorno.getMensagemDeErro().trim());
                }

            } else {
                throw new ProativaException("NENHUM RETORNO");
            }

            strRetorno = atendimento.getObservacao();

        } catch (ProativaException e) {

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            throw e;

        } catch (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException e) {

            e.printStackTrace();
            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            if (gravarLog)
                finalizarConsulta(usuario);

            throw new ProativaException("Houve erro na Webservice BMG: " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            e.printStackTrace();

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            if (e.getMessage() == null)
                throw new ProativaException("Ocorreu um erro inesperado");

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices Cartão beneficio do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Servidor inoperante : " + usrWsdl);
            }

            e.printStackTrace();
            throw new ProativaException("Ocorreu um erro inesperado no web services refin contrato do consig - " + tratarErro(e.getMessage()));

        } finally {

            if (gravarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (logErro != null) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_CARTAO_BENEFICIO, "retornarContratosRefin", (param == null) ? null : retornarXML(ValidaSeJaPossuiContaCartaoParameter.class, param), logErro);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_CARTAO_BENEFICIO, "retornarContratosRefin", (param == null) ? null : retornarXML(ValidaSeJaPossuiContaCartaoParameter.class, param), (retorno == null) ? null : retornarXML(ValidaSeJaPossuiContaCartaoRetorno.class, retorno));
                }

            }

        }

        return strRetorno;

    }

    public String retornarLimiteCartaoBeneficio(String Urlsdl, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Long idCampanha, Usuario usuario, boolean gravarLog, boolean verificarLink)
            throws ProativaException {


        LimiteSaqueParameter param = null;
        LimiteSaqueRetorno retorno = null;
        CartaoBeneficioWebService service = null;
        String strRetorno = null;
        String logErro = null;
        VerificarLinkUtil.verificarLink(Urlsdl);

        param = new LimiteSaqueParameter();
        param.setLogin(usrWsdl);
        param.setSenha(senhaWsdl);
        param.setCpf(atendimento.getCpf());
        param.setCodigoEntidade(atendimento.getEntidadePrincipal() == null ? null : Integer.parseInt(atendimento.getEntidadePrincipal()));
        param.setSequencialOrgao((StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) ? Integer.valueOf(atendimento.getOrgaoPrincipal()) : null));
        param.setMatricula(atendimento.getBeneficioPrincipal().trim());
        TelefoneParameter tel = new TelefoneParameter(atendimento.getListaTelefones().get(0).getDdd().toString(), atendimento.getListaTelefones().get(0).getNumero(), null);
        param.setCelular1(tel);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(atendimento.getDataNascimento());

        param.setDataNascimento(calendar);
        System.out.println(retornarXML(LimiteSaqueParameter.class, param));

        try {

            service = (new CartaoBeneficioWebServiceServiceLocator()).getCartaoBeneficio(new URL(Urlsdl + URL_CARTAO_BENEFICIO));

            retorno = service.buscarLimiteSaque(param);

            if (retorno != null) {

                System.out.println(retorno.getValorSaqueMaximo());
                System.out.println(retorno.getValorSaqueParaMargemComplementarComAgregacaoDeMargem());
                System.out.println(retorno.getLimiteCartao());
                System.out.println(retorno.isExcecaoDeRegraDeNegocio());

                System.out.println(retorno.getMensagemDeErro());

                if (retorno.getMensagemDeErro() == null || StringUtils.isBlank(retorno.getMensagemDeErro().trim())) {

                    atendimento.setObservacao("sem_retorno_de_erro_ws_para_proposta_no_bmg");

                } else {

                    atendimento.setObservacao(retorno.getMensagemDeErro().trim());
                }


            } else {

                throw new ProativaException("NENHUM RETORNO");
            }

            strRetorno = atendimento.getObservacao();

        } catch (ProativaException e) {

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            throw e;

        } catch (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException e) {

            e.printStackTrace();
            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            if (gravarLog)
                finalizarConsulta(usuario);

            throw new ProativaException("Houve erro na Webservice BMG: " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            e.printStackTrace();

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            if (e.getMessage() == null)
                throw new ProativaException("Ocorreu um erro inesperado");

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices Cartão beneficio do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Servidor inoperante : " + usrWsdl);
            }

            e.printStackTrace();
            throw new ProativaException("Ocorreu um erro inesperado no web services refin contrato do consig - " + tratarErro(e.getMessage()));

        } finally {

            if (gravarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (logErro != null) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_CARTAO_BENEFICIO, "retornarContratosRefin", (param == null) ? null : retornarXML(ValidaSeJaPossuiContaCartaoParameter.class, param), logErro);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_CARTAO_BENEFICIO, "retornarContratosRefin", (param == null) ? null : retornarXML(ValidaSeJaPossuiContaCartaoParameter.class, param), (retorno == null) ? null : retornarXML(ValidaSeJaPossuiContaCartaoRetorno.class, retorno));
                }

            }

        }

        return strRetorno;

    }


    private String retornarXML(Class<?> classe, Object entidade) {

        XStream xstream = new XStream();
        xstream.processAnnotations(classe);

        return xstream.toXML(entidade);
    }

    public void finalizarConsulta(Usuario usuario) {

        this.registro.registrarLog(usuario.getId(), TipoEventoEnum.CONSULTA_CARTAO_BENEFICIO, "finalizou consulta cartão beneficio", usuario.getIp());

    }

    private String tratarErro(String erro) {

        return (erro == null) ? null
                : erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "")
                .replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
                .replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
                .replaceAll("com.bmg.econsig.consignacao.exception.PropostaConsignacaoListException:", "")
                .trim();
    }


    public static void main(String[] args) {

        WsdlCartaoBeneficioUtil ws = new WsdlCartaoBeneficioUtil();
        Atendimento atendimento = new Atendimento();
        atendimento.setCpf(StringUtils.leftPad("25202308", 11, "0"));
        System.out.println(atendimento.getCpf());
        atendimento.setBeneficioPrincipal("5511209844");
        atendimento.setEntidadePrincipal("4277");
        atendimento.setOrgaoPrincipal(null);
        short t = 98;
        GenericTelefone tel = new Telefone(t, "981200356");
        java.util.List<GenericTelefone> listTel = new ArrayList<GenericTelefone>();
        listTel.add(tel);
        atendimento.setListaTelefones(listTel);
        atendimento.setDataNascimento(DateUtil.builder("06/07/1954").formatarStringParaData("dd/MM/yyyy").getData());

        System.out.println(atendimento.getDataNascimento());

        try {

            String retorno = ws.validarSePossuiCartaoBeneficio("https://ws1.bmgconsig.com.br/webservices", "pamelagra", "416a253@", atendimento, 1L, new Usuario(12L, ""), false, false);
            String retorno2 = ws.retornarLimiteCartaoBeneficio("https://ws1.bmgconsig.com.br/webservices", "pamelagra", "416a253@", atendimento, 1L, new Usuario(12L, ""), false, false);

            System.out.println("VALIDA SE POSSUI CARTÃO: " + retorno);
            System.out.println("BUSCAR LIMITE: " + retorno2);

        } catch (ProativaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
