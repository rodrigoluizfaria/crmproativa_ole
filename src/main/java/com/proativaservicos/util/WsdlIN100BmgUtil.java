package com.proativaservicos.util;

import com.github.underscore.lodash.U;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.GenericEndereco;
import com.proativaservicos.model.GenericTelefone;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.model.bmg.ConsultaIn100;
import com.proativaservicos.service.asynchronous.bmg.in100.*;
import com.proativaservicos.service.asynchronous.bmg.refinanciamento.ClienteParameter;
import com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter;
import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Stub;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class WsdlIN100BmgUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private RegistroSistemaUtil registro;

    public static String URL_CONSULTA_IN100 = "/ConsultaMargemIN100?wsdl";

    public ConsultaIn100 retornarPesquisaIn100(String Urlsdl, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Long idCampanha, Usuario usuario, boolean gravarLog, boolean verificarLink) throws ProativaException, Exception {


        FiltroConsultaIN100 filtro = null;

        ConsultaMargemIN100WebService servico = null;

        String logErro = null;

        String logErroRefinContrato = "";

        VerificarLinkUtil.verificarLink(Urlsdl);

        DetalheConsultaIN100[] detalhesConsulta = null;

        boolean semResultado = false;

        ConsultaIn100 consulta = null;

        try {

            if (StringUtils.isBlank(atendimento.getCpf()))
                throw new ProativaException("Por favor informe o cpf para consulta");

            consulta = new ConsultaIn100();

            filtro = new FiltroConsultaIN100();

            filtro.setLogin(usrWsdl);

            filtro.setSenha(senhaWsdl);

            filtro.setCpf(atendimento.getCpf());

            Calendar inicio = new GregorianCalendar();

            Calendar fim = new GregorianCalendar();

            inicio.setTime(DateUtil.builder().retornarDataPrimeiroDiaMes().getData());

            fim.setTime(DateUtil.builder().retornarDataUltimoDiaMes().getData());

            filtro.setPeriodoInicial(inicio);

            filtro.setPeriodoFinal(fim);

            servico = (new ConsultaMargemIN100WebServiceServiceLocator()).getConsultaMargemIN100(new URL(Urlsdl + URL_CONSULTA_IN100));

            detalhesConsulta = servico.pesquisar(filtro);

            if (detalhesConsulta == null || detalhesConsulta.length == 0)
                return null;

            consulta.setSemResultado(Boolean.FALSE);
            consulta.setListDetalhesConsulta(Arrays.asList(detalhesConsulta));

            return consulta;

        } catch (ProativaException e) {

            if (gravarLog)
                finalizarConsulta(usuario);
            logErro = e.getMessage() + " - " + logErroRefinContrato;

            throw e;

        } catch (ConsigBusinessException e) {

            e.printStackTrace();
            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            if (gravarLog)
                finalizarConsulta(usuario);

            throw new ProativaException("Aconteceu um erro ao consultar Webservice BMG: " + tratarErro(e.getFaultString() + logErroRefinContrato));

        } catch (Exception e) {

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            if (e.getMessage() == null)
                throw new ProativaException("Ocorreu um erro inesperado");

            if (e.getMessage().contains("Nenhum resultado foi encontrado na pesquisa") || e.getMessage().contains("com.bmg.consig.autorizacaodesconto.exception.RegistroNaoEncontradoException")) {
                semResultado = true;
                logErro = "Nenhum resultado foi encontrado na pesquisa!";
                //throw new ProativaException("Nenhum resultado foi encontrado na pesquisa!");
            }

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices IN100 : " + tratarErro(e.getMessage()) + logErroRefinContrato);

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Não foi possivel conectar : " + usrWsdl);
            }

            e.printStackTrace();
            throw new ProativaException("Erro inesperado web services IN100 - " + tratarErro(e.getMessage()) + logErroRefinContrato);

        } finally {

            if (gravarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (logErro != null) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_IN100, "retornarPesquisaIn100", retornarXmlEnvio(servico), logErro);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_IN100, "retornarPesquisaIn100", retornarXmlEnvio(servico), retornarXmlRetorno(servico));
                }

            }

            if (semResultado) {

                consulta.setMenssagem(logErro);
                consulta.setSemResultado(Boolean.TRUE);
                //return consulta;
                return consulta;
            }

        }

    }


    public String enviarSolicitacaoToken(String Urlsdl, String usrWsdl, String senhaWsdl, Usuario usuario, GenericAtendimento atendimento, GenericTelefone telefone, GenericEndereco ufIn100, Long idCampanha, boolean registrarLog) throws ProativaException, Exception {

        SolicitacaoIN100Parameter solicitacao = null;
        ConsultaMargemIN100WebService servico = null;
        VerificarLinkUtil.verificarLink(Urlsdl);
        String logErro = null;
        String resposta = null;

        try {


            if (telefone == null)
                throw new ProativaException("Necessario informar telefone do cliente");

            if (!PhoneNumberUtil.isCelularNumber(telefone.getDdd().toString() + telefone.getNumero()))
                throw new ProativaException("Deve ser informado um numero de celular valido.");

            if (ufIn100 != null && StringUtils.isEmpty(ufIn100.getCidade()) && StringUtils.isEmpty(ufIn100.getUf()))
                throw new ProativaException("O campo estado de ser informado.");

            solicitacao = new SolicitacaoIN100Parameter();
            solicitacao.setLogin(usrWsdl);
            solicitacao.setNome(atendimento.getNome());
            solicitacao.setSenha(senhaWsdl);
            solicitacao.setCpf(atendimento.getCpf());
            solicitacao.setDdd(telefone.getDdd().toString());
            solicitacao.setTelefone(telefone.getNumero());
            solicitacao.setMatricula(atendimento.getBeneficioPrincipal());
            solicitacao.setEstado(ufIn100.getUf());

            System.out.println(ufIn100.getCidade());
            solicitacao.setCidade(ufIn100.getCidade());

            Calendar dataCalendar = new GregorianCalendar(1980, 10, 12);

            if (atendimento.getDataNascimento() != null)
                dataCalendar.setTime(atendimento.getDataNascimento());

            solicitacao.setDataNascimento(dataCalendar);

            servico = (new ConsultaMargemIN100WebServiceServiceLocator()).getConsultaMargemIN100(new URL(Urlsdl + URL_CONSULTA_IN100));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            resposta = servico.inserirSolicitacao(solicitacao);

            if (StringUtils.isEmpty(resposta))
                throw new ProativaException("Nenhuma resposta.");


        } catch (ConsultaIn100Exception e) {

            e.printStackTrace();

            String logErroTmp = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            if (registrarLog)
                finalizarConsulta(usuario);


            if (!logErroTmp.contains("Celular já usado em outro CPF"))
                throw new ProativaException("Erro webservice BMG : " + tratarErro(e.getFaultString()));

            else
                logErro = "Celular já usado em outro CPF!";

        } catch (Exception e) {

            if (registrarLog)
                finalizarConsulta(usuario);

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices IN100 BMG : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT") || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO") || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("link inoperante - Link : " + Urlsdl);

            }

            if (e.getMessage().contains("Celular já usado em outro CPF!")) {
                logErro = "Celular já usado em outro CPF!";
            }

            e.printStackTrace();

            if (StringUtils.isBlank(logErro))
                throw new ProativaException("Retorno web services IN100 : " + tratarErro(e.getMessage()));

        } finally {

            Long idAtendimento = atendimento == null ? null : atendimento.getId();

            if (registrarLog) {

                this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_IN100, "enviarSolicitacaoToken", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

            }

            if (StringUtils.isNotBlank(logErro)) {
                return logErro;
            }

        }

        return resposta;
    }

    private String retornarXmlEnvio(Object servico) throws AxisFault {

        if (servico != null)
            return U.formatXml(((Stub) servico)._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString());
        return "";
    }

    private String retornarXmlRetorno(Object servico) throws AxisFault {

        if (servico != null)
            return U.formatXml(((Stub) servico)._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString());
        return "";
    }

    public void finalizarConsulta(Usuario usuario) {

        this.registro.registrarLog(usuario.getId(), TipoEventoEnum.CONSULTA_IN100, "finalizou consulta in100", usuario.getIp());

    }

    private String tratarErro(String erro) {

        return (erro == null) ? null
                : erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "")
                .replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
                .replaceAll("com.bmg.consig.autorizacaodesconto.exception.ConsultaIn100Exception:", "")
                .replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
                .replaceAll("com.bmg.econsig.consignacao.exception.PropostaConsignacaoListException:", "")
                .trim();

    }


    public String validarToken(String Urlsdl, String usr, String psw, Usuario usuario, GenericAtendimento atendimento, Long idCampanha, String token, Long numeroSolicitacao, boolean registrarLog) throws ProativaException, Exception {

        ConsultaMargemIN100WebService servico = null;
        VerificarLinkUtil.verificarLink(Urlsdl);
        String logErro = null;
        String resposta = null;
        DadosConsultaIN100 dadosConsulta = null;

        try {

            dadosConsulta = new DadosConsultaIN100();

            dadosConsulta.setLogin(usr);
            dadosConsulta.setSenha(psw);
            dadosConsulta.setNumeroSolicitacao(numeroSolicitacao);
            dadosConsulta.setToken(token);

            servico = (new ConsultaMargemIN100WebServiceServiceLocator()).getConsultaMargemIN100(new URL(Urlsdl + URL_CONSULTA_IN100));
            resposta = servico.validarToken(dadosConsulta);
            return resposta;

        } catch (Exception e) {

            if (registrarLog)
                finalizarConsulta(usuario);

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices IN100 BMG : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT") || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO") || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("link inoperante - Link : " + Urlsdl);

            }

            e.printStackTrace();

            if (StringUtils.isBlank(logErro))
                throw new ProativaException("Retorno web services IN100 : " + tratarErro(e.getMessage()));

        } finally {

            Long idAtendimento = atendimento == null ? null : atendimento.getId();

            if (registrarLog) {

                this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_IN100, "validarToken", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

            }

            if (StringUtils.isNotBlank(logErro)) {
                return logErro;
            }

        }

        return resposta;


    }

    private void setarTelefones(GenericAtendimento atendimento, GenericTelefone celular, boolean invalidarStatusTel, ClienteParameter parametar) throws ProativaException {
        List<GenericTelefone> listTelefones = null;

        if (celular == null)
            throw new ProativaException("Necessario informar telefone do cliente");

        if (!PhoneNumberUtil.isCelularNumber(celular.getDdd().toString() + celular.getNumero()))
            throw new ProativaException("Deve ser informado um numero de celular valido.");

        parametar.setCelular1(new TelefoneParameter(celular.getDdd().toString(), celular.getNumero(), null));

        if (CollectionUtils.isNotEmpty(atendimento.getListaTelefones()))
            listTelefones = new ArrayList<GenericTelefone>((Collection<? extends GenericTelefone>) atendimento.getListaTelefones().stream().filter(t -> (t.getId().longValue() != celular.getId().longValue())).collect(Collectors.toList()));

        if (CollectionUtils.isNotEmpty(listTelefones)) {

            for (GenericTelefone genericTelefone : listTelefones) {


                if (invalidarStatusTel || (genericTelefone.getStatusTelefone() != null && AcaoStatusTelefoneEnum.CONTATO_CLIENTE.equals(genericTelefone.getStatusTelefone().getParametro()))) {

                    if (parametar.getTelefone() == null && !PhoneNumberUtil.isCelularNumber(genericTelefone.getDdd().toString() + genericTelefone.getNumero())) {
                        parametar.setTelefone(new TelefoneParameter(genericTelefone.getDdd().toString(), genericTelefone.getNumero(), null));
                        continue;
                    }

                    if (parametar.getCelular1() == null && !PhoneNumberUtil.isCelularNumber(genericTelefone.getDdd().toString() + genericTelefone.getNumero())) {
                        parametar.setCelular1(new TelefoneParameter(genericTelefone.getDdd().toString(), genericTelefone.getNumero(), null));
                        continue;
                    }

                    if (parametar.getCelular2() == null && !PhoneNumberUtil.isCelularNumber(genericTelefone.getDdd().toString() + genericTelefone.getNumero())) {
                        parametar.setCelular2(new TelefoneParameter(genericTelefone.getDdd().toString(), genericTelefone.getNumero(), null));
                        continue;
                    }

                }


            }

        }

    }


    public static void main(String[] args) {

    }


}
