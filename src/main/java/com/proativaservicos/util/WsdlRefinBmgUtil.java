package com.proativaservicos.util;

import com.github.underscore.lodash.U;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.bmg.ContratosSimulacaoRefinBMG;
import com.proativaservicos.service.asynchronous.bmg.consultaContrato.Contrato;
import com.proativaservicos.service.asynchronous.bmg.consultaContrato.*;
import com.proativaservicos.service.asynchronous.bmg.refinanciamento.BancoParameter;
import com.proativaservicos.service.asynchronous.bmg.refinanciamento.ConsigBusinessException;
import com.proativaservicos.service.asynchronous.bmg.refinanciamento.*;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.DadosCartaoParameter;
import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno;
import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.*;
import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
import com.proativaservicos.util.constantes.ParamentroFormaPagamentoEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import com.thoughtworks.xstream.XStream;
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
public class WsdlRefinBmgUtil implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private RegistroSistemaUtil registro;

    public static String URL_CONSULTA_CONTRATO = "/ConsultaContratoRefinanciamento?wsdl";

    public static String URL_SIMULACAO_PRESTACAO = "/SimulacaoPrestacao?wsdl";

    public static String URL_REFIN = "/Refinanciamento?wsdl";

    public static Integer COD_REFIN_PRODUTO = Integer.valueOf(9085); // NORMAL = 9083 | FLEX 2 9085

    public static Integer COD_FORMA_ENVIO = Integer.valueOf(21);

    public static Integer COD_FORMA_CREDITO = Integer.valueOf(7);

    public List<ContratoRefin> retornarContratosRefin(String Urlsdl, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Long idCampanha, Usuario usuario, boolean gravarLog, boolean verificarLink)
            throws ProativaException, Exception {

        ConsultaContratoRefinanciamentoParameter contratoParameter = null;

        ConsultaContratoRefinanciamentoRetorno retornoContrato = null;

        ConsultaContratoRefinanciamentoWebService servico = null;

        String logErro = null;

        String logErroRefinContrato = "";

        VerificarLinkUtil.verificarLink(Urlsdl);

        try {

            contratoParameter = new ConsultaContratoRefinanciamentoParameter();

            contratoParameter.setLogin(usrWsdl);

            contratoParameter.setSenha(senhaWsdl);

            contratoParameter.setCpf(atendimento.getCpf());

            contratoParameter.setCodigoEntidade(atendimento.getEntidadePrincipal() == null ? null : Integer.parseInt(atendimento.getEntidadePrincipal()));

            contratoParameter.setSequencialOrgao((StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) ? Integer.valueOf(atendimento.getOrgaoPrincipal()) : null));

            servico = (new ConsultaContratoRefinanciamentoWebServiceServiceLocator()).getConsultaContratoRefinanciamento(new URL(Urlsdl + URL_CONSULTA_CONTRATO));

            retornoContrato = servico.consultaContratoRefinanciamento(contratoParameter);

            if (retornoContrato == null || retornoContrato.getContratos() == null || retornoContrato.getContratos().length == 0)
                throw new ProativaException("Nenhum contrato disponivel");

            return Arrays.asList(retornoContrato.getContratos());

        } catch (ProativaException e) {

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage() + " - " + logErroRefinContrato;

            throw e;

        } catch (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException e) {

            e.printStackTrace();
            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            if (gravarLog)
                finalizarConsulta(usuario);

            throw new ProativaException("Houve erro na Webservice BMG: " + tratarErro(e.getFaultString() + logErroRefinContrato));

        } catch (Exception e) {

            e.printStackTrace();

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            if (e.getMessage() == null)
                throw new ProativaException("Ocorreu um erro inesperado");

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices Contrato Refin do consig : " + tratarErro(e.getMessage()) + logErroRefinContrato);

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Servidor inoperante : " + usrWsdl);
            }

            e.printStackTrace();
            throw new ProativaException("Ocorreu um erro inesperado no web services refin contrato do consig - " + tratarErro(e.getMessage()) + logErroRefinContrato);

        } finally {

            if (gravarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (logErro != null) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_REFIN, "retornarContratosRefin", (contratoParameter == null) ? null : retornarXML(ConsultaContratoRefinanciamentoParameter.class, contratoParameter), logErro);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_REFIN,
                            "retornarContratosRefin",
                            (contratoParameter == null) ? null
                                    : retornarXML(DadosCartaoParameter.class, contratoParameter),
                            (retornoContrato == null) ? null
                                    : retornarXML(ConsultaContratoRefinanciamentoRetorno.class, retornoContrato));
                }

            }

        }

    }

    /*
     * Consulta REFIN
     */

    public List<SimulacaoRetorno> retornarSimulacao(String Urlsdl, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Contrato contrato, Long idCampanha, Usuario usuario, String codLoja,
                                                    Integer codPoduto, boolean gravarLog, boolean verificarLink) throws ProativaException, Exception {

        ParametrosSimulacaoPrestacao simulacaoParameter = null;

        SimulacaoRetorno simulacoesRetorno[] = null;

        SimulacaoPrestacaoWebService servico = null;

        String logErro = null;

        String logErroSimulacao = "";

        VerificarLinkUtil.verificarLink(Urlsdl);

        try {
            System.out.println(contrato.getNumeroDaAdesao() + " - " + contrato.getNumeroContrato() + " - ");

            simulacaoParameter = new ParametrosSimulacaoPrestacao();

            simulacaoParameter.setLogin(usrWsdl);

            simulacaoParameter.setSenha(senhaWsdl);

            simulacaoParameter.setCpfAsNumber(Long.valueOf(atendimento.getCpf()));

            simulacaoParameter.setAssociado(true);

            if (StringUtils.isNotBlank(atendimento.getEntidadePrincipal()) && StringUtils.isNumeric(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim())) {

                simulacaoParameter.setCodigoEntidade(Integer.valueOf(Integer.parseInt(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim())));

            }

            if (StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) && StringUtils.isNumeric(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim())) {

                simulacaoParameter.setSequencialOrgao(Integer.parseInt(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim()));

            } else {

                simulacaoParameter.setSequencialOrgao(null);
            }

            System.out.println(codPoduto);

            simulacaoParameter.setCodigoProduto(codPoduto == null ? Integer.valueOf(COD_REFIN_PRODUTO) : Integer.valueOf(codPoduto));

            simulacaoParameter.setTipoFormaEnvio(Integer.valueOf(21));

            simulacaoParameter.setFormaCredito(Integer.valueOf(COD_FORMA_CREDITO));

            simulacaoParameter.setServico("040");

            simulacaoParameter.setDataFator(Calendar.getInstance());
            simulacaoParameter.setNumeroContrato(contrato.getNumeroContrato());

            simulacaoParameter.setDataAdmissao(Calendar.getInstance());

            simulacaoParameter.setNumeroAdesao(contrato.getNumeroDaAdesao());

            // simulacaoParameter.setQuantidadePrestacoes(contrato.getQuantidadeTotalParcela()
            // ==null? 0 : contrato.getQuantidadeTotalParcela().intValue());

            simulacaoParameter.setValorPrestacao(contrato.getValorParcela() == null ? 0.0 : contrato.getValorParcela().doubleValue());

            simulacaoParameter.setMargem(contrato.getValorParcela() == null ? 0.0 : Double.valueOf(contrato.getValorParcela()).doubleValue());

            simulacaoParameter.setIndicadorApenasParcelamentoIdealMaximo(true);

            simulacaoParameter.setValorSaldoDevedor(contrato.getSaldoDevedor());

            simulacaoParameter.setValorRenda(Double.valueOf("5000.0").doubleValue());

            Calendar dataNascimento = new GregorianCalendar(1962, Calendar.JULY, 5);

            if (atendimento.getDataNascimento() != null)
                dataNascimento.setTime(atendimento.getDataNascimento());

            simulacaoParameter.setDataNascimento(dataNascimento);

            servico = (new SimulacaoPrestacaoWebServiceServiceLocator())
                    .getSimulacaoPrestacao(new URL(Urlsdl + URL_SIMULACAO_PRESTACAO));

            simulacaoParameter.setLoja(Integer.valueOf(codLoja));

            ((Stub) servico).setTimeout(10000);

            simulacoesRetorno = servico.geraSimulacao(simulacaoParameter);

            List<SimulacaoRetorno> listSimulacaoDisponivelRefin = new ArrayList<>();

            if (simulacoesRetorno == null || simulacoesRetorno.length == 0)
                throw new ProativaException("Nehuma Simulação disponível.");


            listSimulacaoDisponivelRefin = new ArrayList<SimulacaoRetorno>();

            for (SimulacaoRetorno simulacaoRetorno : simulacoesRetorno) {

                if (simulacaoRetorno != null && simulacaoRetorno.getHabilitado().booleanValue()) {
                    listSimulacaoDisponivelRefin.add(simulacaoRetorno);
                }

            }


            return CollectionUtils.isEmpty(listSimulacaoDisponivelRefin) ? null : listSimulacaoDisponivelRefin;

        } catch (ProativaException e) {

            finalizarConsulta(usuario);

            logErro = e.getMessage() + " - " + logErroSimulacao;

            throw e;

        } catch (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException |
                 LimiteOperacionalException | RiscoOperacionalException e) {
            e.printStackTrace();
            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            if (gravarLog)
                finalizarConsulta(usuario);

            if (logErro.contains("não foram encontrados ou não são passíveis de refinanciamento")) {

                logErro = "O(s) contrato(s) informado(s) não foram encontrados ou não são passíveis de refinanciamento.";
                throw new ProativaException(logErro);
            }

            throw new ProativaException("Ocorreu um erro na Webservice BMG: " + tratarErro(e.getFaultString() + logErroSimulacao));

        } catch (Exception e) {

            e.printStackTrace();

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices Produtos Seguro do consig : " + tratarErro(e.getMessage())
                        + logErroSimulacao);

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")

                    || e.getMessage().toUpperCase().contains("Service Unavailable")

                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Link inoperante - Link : " + Urlsdl);
            }

            e.printStackTrace();

            throw new ProativaException("Ocorreu um erro inesperado no web services Refin do consig - " + tratarErro(e.getMessage()) + logErroSimulacao);

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();
            if (gravarLog)
                this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_REFIN, "consultarContratos", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

        }

    }

    public List<SimulacaoRetorno> retornarSimulacao(String Urlsdl, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Contrato contrato, Long idCampanha, Usuario usuario, String codLoja,
                                                    ProdutoReturn produto, FormaEnvioBmg formaEnvio, boolean gravarLog, boolean verificarLink) throws ProativaException, Exception {

        ParametrosSimulacaoPrestacao simulacaoParameter = null;

        SimulacaoRetorno simulacoesRetorno[] = null;

        SimulacaoPrestacaoWebService servico = null;

        String logErro = null;

        String logErroSimulacao = "";

        try {

            if (produto == null)
                throw new ProativaException("Produto não informado");

            VerificarLinkUtil.verificarLink(Urlsdl);

            simulacaoParameter = new ParametrosSimulacaoPrestacao();

            simulacaoParameter.setLogin(usrWsdl);

            simulacaoParameter.setSenha(senhaWsdl);

            simulacaoParameter.setCpfAsNumber(Long.valueOf(atendimento.getCpf().replaceAll("\\D+", "").trim()));

            simulacaoParameter.setAssociado(true);

            simulacaoParameter.setCodigoEntidade(Integer.valueOf(atendimento.getEntidadePrincipal()));

            simulacaoParameter.setSequencialOrgao((StringUtils.isNoneBlank(atendimento.getOrgaoPrincipal()) ? Integer.valueOf(atendimento.getOrgaoPrincipal()) : null));

            simulacaoParameter.setCodigoProduto(produto == null ? Integer.valueOf(COD_REFIN_PRODUTO) : Integer.valueOf(produto.getCodigo()));

            simulacaoParameter.setTipoFormaEnvio(formaEnvio == null ? Integer.valueOf(21) : formaEnvio.getCodigo());

            simulacaoParameter.setFormaCredito(Integer.valueOf(COD_FORMA_CREDITO));
            simulacaoParameter.setIndicadorPesquisaCet(false);
            simulacaoParameter.setServico("040");

            simulacaoParameter.setDataFator(Calendar.getInstance());

            simulacaoParameter.setDataAdmissao(Calendar.getInstance());

            simulacaoParameter.setNumeroAdesao(contrato.getNumeroDaAdesao());

            // simulacaoParameter.setQuantidadePrestacoes(contrato.getQuantidadeTotalParcela()
            // ==null? 0 : contrato.getQuantidadeTotalParcela().intValue());

            simulacaoParameter.setValorPrestacao(contrato.getValorParcela() == null ? 0.0 : contrato.getValorParcela().doubleValue());

            simulacaoParameter.setMargem(contrato.getValorParcela() == null ? 0.0 : Double.valueOf(contrato.getValorParcela()).doubleValue());

            simulacaoParameter.setIndicadorApenasParcelamentoIdealMaximo(false);

            simulacaoParameter.setValorSaldoDevedor(contrato.getSaldoDevedor());

            simulacaoParameter.setValorRenda(Double.valueOf("5000.0").doubleValue());

            Calendar dataNascimento = new GregorianCalendar(1962, Calendar.JULY, 5);

            if (atendimento.getDataNascimento() != null)
                dataNascimento.setTime(atendimento.getDataNascimento());

            simulacaoParameter.setDataNascimento(dataNascimento);

            servico = (new SimulacaoPrestacaoWebServiceServiceLocator()).getSimulacaoPrestacao(new URL(Urlsdl + URL_SIMULACAO_PRESTACAO));

            simulacaoParameter.setLoja(StringUtils.isBlank(codLoja) ? null : Integer.valueOf(Integer.parseInt(codLoja)));

            ((Stub) servico).setTimeout(10000);

            simulacoesRetorno = servico.geraSimulacao(simulacaoParameter);

            List<SimulacaoRetorno> listSimulacaoDisponivelRefin = new ArrayList<>();

            if (simulacoesRetorno == null || simulacoesRetorno.length == 0)
                throw new ProativaException("Nehuma Simulação disponível.");

            listSimulacaoDisponivelRefin = new ArrayList<SimulacaoRetorno>();

            for (SimulacaoRetorno simulacaoRetorno : simulacoesRetorno) {

                try {

                    if (simulacaoRetorno != null) {

                        listSimulacaoDisponivelRefin.add(simulacaoRetorno);

                    }

                } catch (NullPointerException e) {

                }

            }

            if (CollectionUtils.isEmpty(listSimulacaoDisponivelRefin)) {

                throw new ProativaException("Nehuma Simulação disponível.");
            }

            return listSimulacaoDisponivelRefin;

        } catch (ProativaException e) {

            finalizarConsulta(usuario);

            logErro = e.getMessage() + " - " + logErroSimulacao;


            throw e;

        } catch (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException e) {

            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            if (gravarLog)
                finalizarConsulta(usuario);

            if (logErro.contains("não foram encontrados ou não são passíveis de refinanciamento")) {

                logErro = "O(s) contrato(s) informado(s) não foram encontrados ou não são passíveis de refinanciamento.";
                throw new ProativaException(logErro);
            }

            throw new ProativaException(
                    "Ocorreu um erro na Webservice BMG: " + tratarErro(e.getFaultString() + logErroSimulacao));

        } catch (Exception e) {

            e.printStackTrace();

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices Produtos Seguro do consig : " + tratarErro(e.getMessage())
                        + logErroSimulacao);

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")

                    || e.getMessage().toUpperCase().contains("Service Unavailable")

                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Link inoperante - Link : " + Urlsdl);
            }

            e.printStackTrace();

            throw new ProativaException("Ocorreu um erro inesperado no web services Refin do consig - "
                    + tratarErro(e.getMessage()) + logErroSimulacao);

        } finally {

            if (gravarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (logErro != null) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarSimulacao", (simulacaoParameter == null) ? null : retornarXML(ParametrosSimulacaoPrestacao.class, simulacaoParameter), logErro);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarSimulacao", (simulacaoParameter == null) ? null : retornarXML(ParametrosSimulacaoPrestacao.class, simulacaoParameter), (simulacaoParameter == null) ? null
                            : retornarXML(SimulacaoRetorno.class, simulacoesRetorno));
                }

            }

        }

    }


    public List<ProdutoReturn> retornarProdutosRefin(String Urlsdl, String usrWsdl, String senhaWsdl, Usuario usuario, boolean registrarLog) throws ProativaException, Exception {

        ParametrosProduto parametro = null;

        ProdutoReturn[] retornoProdutos = null;

        VerificarLinkUtil.verificarLink(Urlsdl);

        try {

            parametro = new ParametrosProduto();
            parametro.setLogin(usrWsdl);
            parametro.setSenha(senhaWsdl);
            parametro.setCodigoEntidade(Integer.valueOf(1581));
            //	parametro.set(Integer.valueOf(1581));
            parametro.setCodigoServico("040");

            SimulacaoPrestacaoWebService servico;

            servico = (new SimulacaoPrestacaoWebServiceServiceLocator()).getSimulacaoPrestacao(new URL(Urlsdl + URL_SIMULACAO_PRESTACAO));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            retornoProdutos = servico.obterProduto(parametro);

            if (retornoProdutos == null || retornoProdutos.length == 0)
                throw new ProativaException("Nenhum Produto Refin retornado.");

            return Arrays.asList(retornoProdutos);

        } catch (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException e) {

            e.printStackTrace();

            if (registrarLog)
                finalizarConsulta(usuario);

            throw new ProativaException("Erro webservice BMG : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            if (registrarLog)
                finalizarConsulta(usuario);

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices Refin BMG : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("link inoperante - Link : " + Urlsdl);

            }

            e.printStackTrace();

            throw new ProativaException(
                    "Ocorreu um erro inesperado no web services Refin do consig - " + tratarErro(e.getMessage()));

        } finally {

            if (registrarLog) {

                this.registro.registrarLog(usuario.getId(), TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarProdutosRefin", usuario.getIp());

            }

        }

    }

    public List<ProdutoReturn> retornarProdutosRefin(String url, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Long campanha, Usuario usuario, Boolean carencia, boolean registrarLog) throws ProativaException, Exception {

        ParametrosProduto parametro = null;

        ProdutoReturn[] retornoProdutos = null;

        SimulacaoPrestacaoWebService servico = null;


        try {

            VerificarLinkUtil.verificarLink(url);

            if (atendimento == null || StringUtils.isEmpty(atendimento.getEntidadePrincipal()))
                throw new ProativaException("Campo entidade deve ser informado.");

            parametro = new ParametrosProduto();

            if (StringUtils.isNotBlank(atendimento.getEntidadePrincipal()) && StringUtils.isNumeric(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim())) {

                parametro.setCodigoEntidade(Integer.valueOf(Integer.parseInt(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim())));

            }

            if (StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) && StringUtils.isNumeric(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim())) {

                parametro.setSequencialOrgao(Integer.parseInt(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim()));

            } else {

                parametro.setSequencialOrgao(null);
            }

            if (carencia != null)
                parametro.setIndicativoCarencia(carencia);


            parametro.setLogin(usrWsdl);

            parametro.setSenha(senhaWsdl);

            parametro.setCodigoServico("040");


            servico = (new SimulacaoPrestacaoWebServiceServiceLocator()).getSimulacaoPrestacao(new URL(url + URL_SIMULACAO_PRESTACAO));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            retornoProdutos = servico.obterProduto(parametro);

            if (retornoProdutos == null || retornoProdutos.length == 0)
                throw new ProativaException("Nenhum Produto Refin retornado.");

            return Arrays.asList(retornoProdutos);


        } catch (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException e) {

            e.printStackTrace();

            if (registrarLog)
                finalizarConsulta(usuario);

            throw new ProativaException("Erro webservice BMG : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            if (registrarLog)
                finalizarConsulta(usuario);

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices Refin BMG : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT") || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO") || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("link inoperante - Link : " + url);

            }

            e.printStackTrace();

            throw new ProativaException("Ocorreu um erro inesperado no web services Refin do consig - " + tratarErro(e.getMessage()));

        } finally {

            if (registrarLog) {
                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                this.registro.registrarLog(idAtendimento, campanha, usuario, TipoEventoEnum.CONSULTA_REFIN, "consultarContratos", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

            }

        }

    }

    private String retornarXML(Class<?> classe, Object entidade) {

        XStream xstream = new XStream();
        xstream.processAnnotations(classe);

        return xstream.toXML(entidade);
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

        this.registro.registrarLog(usuario.getId(), TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "finalizou consulta refin", usuario.getIp());

    }

    private String tratarErro(String erro) {

        return (erro == null) ? null
                : erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "")
                .replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
                .replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
                .replaceAll("com.bmg.econsig.consignacao.exception.PropostaConsignacaoListException:", "")
                .trim();
    }


    public ContratosSimulacaoRefinBMG retornarContratoSimulacaoRefin(String urlWsBMG, String usuarioWsBMG, String senhaWsBMG, Atendimento atendimento, Long idCampanha, Usuario usuario, String codLoja,
                                                                     Integer codProduto, boolean gravarLog) throws ProativaException, Exception {

        VerificarLinkUtil.verificarLink(urlWsBMG);

        ContratosSimulacaoRefinBMG contratosSimulacao = null;

        List<ContratoRefin> listContratos = null;

        listContratos = retornarContratosRefin(urlWsBMG, usuarioWsBMG, senhaWsBMG, atendimento, idCampanha, usuario, gravarLog, false);

        String LogErro = "";

        List<SimulacaoRetorno> listSmulacaoFinal = null;

        Double valorLiquido = 0.0D;

        boolean primeiroContrato = true;

        int indiceContrato = 0;

        for (int i = 0; i < listContratos.size(); i++) {

            if (StringUtils.isNotBlank(listContratos.get(i).getContrato().getNumeroDaAdesao())) {

                try {

                    List<SimulacaoRetorno> listSmulacao = retornarSimulacao(urlWsBMG, usuarioWsBMG, senhaWsBMG, atendimento, listContratos.get(i).getContrato(), idCampanha, usuario, codLoja, codProduto, gravarLog, false);

                    if (CollectionUtils.isNotEmpty(listSmulacao)) {
                        LogErro = "";

                        SimulacaoRetorno simula = listSmulacao.get(listSmulacao.size() - 1);

                        if (simula.getHabilitado()) {

                            if (primeiroContrato) {

                                valorLiquido = simula.getValorLiquido();
                                listSmulacaoFinal = listSmulacao;
                                indiceContrato = i;

                            } else if (valorLiquido < simula.getValorLiquido()) {

                                valorLiquido = simula.getValorLiquido();
                                listSmulacaoFinal = listSmulacao;
                                indiceContrato = i;
                                primeiroContrato = false;
                            }

                        } else {

                            LogErro = StringUtils.isNotBlank(simula.getMsgBloqueioParcela())
                                    ? simula.getMsgBloqueioParcela()
                                    : "";

                            if (CollectionUtils.isEmpty(listSmulacaoFinal) || primeiroContrato) {
                                listSmulacaoFinal = listSmulacao;
                                indiceContrato = i;
                            }

                        }
                    } else {
                        LogErro = "Nenhum simulação disponivel";
                    }

                    primeiroContrato = false;

                } catch (ProativaException e) {

                    if (primeiroContrato) {
                        LogErro = e.getMessage();

                    } else if (StringUtils.isNotBlank(LogErro)) {

                        LogErro = e.getMessage();

                    }

                    primeiroContrato = false;

                } catch (Exception e) {

                    e.printStackTrace();

                    if (primeiroContrato) {
                        if (StringUtils.isNotBlank(e.getMessage()))
                            LogErro = e.getMessage();

                    } else if (StringUtils.isNotBlank(LogErro)) {

                        LogErro = e.getMessage();

                    }

                    primeiroContrato = false;

                }
            }

        }

        if (StringUtils.isNotBlank(LogErro)) {

            if (LogErro.contains("Valor da operação não pode ser menor que o saldo devedor")) {

                // throw new ProativaException("Valor da operação não pode ser menor que o saldo
                // devedor");

            } else {

                // throw new ProativaException(LogErro);

            }

        }
        // ERRO NULL POINT CORRIGIR.....
        contratosSimulacao = new ContratosSimulacaoRefinBMG(LogErro.contains("Valor da operação não pode ser menor que o saldo devedor") ? "Valor da operação não pode ser menor que o saldo devedor" : LogErro,
                listContratos.get(indiceContrato), CollectionUtils.isEmpty(listSmulacaoFinal) ? null : listSmulacaoFinal.get(listSmulacaoFinal.size() - 1));

        return contratosSimulacao;

    }

    public List<ContratosSimulacaoRefinBMG> retornarContratoSimulacaoRefin(String urlWsBMG, String usr, String psw, GenericAtendimento atendimento, Long idCampanha, Usuario usuario) throws ProativaException, Exception {

        ConsultaContratoRefinanciamentoWebService servico = null;

        try {

            VerificarLinkUtil.verificarLink(urlWsBMG);

            if (atendimento == null || StringUtils.isBlank(atendimento.getCpf()) || StringUtils.isBlank(atendimento.getEntidadePrincipal()))
                throw new ProativaException("Campos cpf e entidade deve ser informados.");

            ConsultaContratoRefinanciamentoParameter parametros = new ConsultaContratoRefinanciamentoParameter();

            parametros.setLogin(usr);
            parametros.setSenha(psw);
            parametros.setCpf(atendimento.getCpf());

            if (StringUtils.isNotBlank(atendimento.getEntidadePrincipal()) && StringUtils.isNumeric(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim())) {

                parametros.setCodigoEntidade(Integer.valueOf(Integer.parseInt(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim())));

            }

            if (StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) && StringUtils.isNumeric(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim())) {

                parametros.setSequencialOrgao(Integer.parseInt(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim()));

            } else {

                parametros.setSequencialOrgao(null);
            }


            servico = (new ConsultaContratoRefinanciamentoWebServiceServiceLocator()).getConsultaContratoRefinanciamento(new URL(urlWsBMG + URL_CONSULTA_CONTRATO));
            ((Stub) servico).setTimeout(10000);

            ConsultaContratoRefinanciamentoRetorno contratos = servico.consultaContratoRefinanciamento(parametros);

            if (contratos != null && contratos.getContratos().length > 0) {

                List<ContratosSimulacaoRefinBMG> listContratos = new ArrayList<ContratosSimulacaoRefinBMG>();

                for (int i = 0; i < contratos.getContratos().length; i++) {

                    listContratos.add(new ContratosSimulacaoRefinBMG(Integer.valueOf(i), contratos.getContratos()[i]));

                }
                return listContratos;

            }

            return null;

        } catch (ProativaException e) {

            finalizarConsulta(usuario);

            throw e;

        } catch (com.proativaservicos.service.asynchronous.bmg.consultaContrato.ServiceException e) {

            e.printStackTrace();

            finalizarConsulta(usuario);

            throw new ProativaException("Houve erro na Webservice BMG: " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            e.printStackTrace();

            finalizarConsulta(usuario);

            if (e.getMessage() == null)
                throw new ProativaException("Ocorreu um erro inesperado");

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices Contrato Refin do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().contains("com.bmg.econsig.consignacao.exception"))
                throw new ProativaException("Erro WebServices Contrato Refin do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Servidor inoperante : " + urlWsBMG);

            }
            throw new ProativaException("Ocorreu um erro inesperado no web services refin contrato do consig - "
                    + tratarErro(e.getMessage()));

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_REFIN, "consultarContratos", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

        }

    }


    public PropostaGerada[] gravarProposta(String urlWsBMG, String usrBmg, String senhaBmg, String loginConsig, String senhaConsig,
                                           String codLoja, GenericAtendimento atendimento, List<ContratosSimulacaoRefinBMG> listContratosRefin,
                                           ProdutoReturn produtoBmgRefin, GenericTelefone telefone, GenericEndereco endereco, GenericDadosBancarios dadosBancarios, String cpfAgent,
                                           boolean clienteCadastro, Long idCampanha, Usuario usuario, boolean invalidarStatusTel,
                                           FormaEnvioBmg formaEnvio, String token) throws ProativaException, Exception {

        PropostaGerada[] propostaGeradas = null;

        RefinanciamentoWebService servico = null;


        try {

            VerificarLinkUtil.verificarLink(urlWsBMG);

            PropostaRefinanciamentoParameter parameter = new PropostaRefinanciamentoParameter();

            if (StringUtils.isBlank(codLoja))
                throw new ProativaException("Código da loja deve ser informado.");

            if (atendimento == null || StringUtils.isBlank(atendimento.getCpf().trim()) || StringUtils.isBlank(atendimento.getEntidadePrincipal().trim()))
                throw new ProativaException("Campos entidade e cpf devem ser informados.");

            if (CollectionUtils.isEmpty(listContratosRefin))
                throw new ProativaException("Nenhum contrato foi selecionado.");

            //ENDERECO E TEL E DADOS BANCARIOS
            if (dadosBancarios != null) {

                if (atendimento.getFormaPagamento().getParametro().equals(ParamentroFormaPagamentoEnum.ORDEM_PAGAMENTO)) {

                    if (dadosBancarios == null || dadosBancarios.getBanco() == null || StringUtils.isBlank(dadosBancarios.getAgencia())) {
                        throw new ProativaException("Ordem de pagamento as informações de agencia e banco são dados obrigatórios.");
                    }


                    if (dadosBancarios.getBanco() != null && dadosBancarios.getBanco().getNumeroBanco() != null)
                        parameter.setBanco(new BancoParameter(Integer.parseInt(dadosBancarios.getBanco().numeroBanco)));

                    if (StringUtils.isNotBlank(dadosBancarios.getAgencia()))
                        parameter.setAgencia(new AgenciaParameter(dadosBancarios.getDigitoAgencia(), dadosBancarios.getAgencia()));


                    parameter.setConta(null);


                } else {

                    if (StringUtils.isBlank(dadosBancarios.getUf()))
                        throw new ProativaException("O campo estado dos dados bancários deve ser informado.");

                    if (dadosBancarios.getBanco() != null && dadosBancarios.getBanco().getNumeroBanco() != null)
                        parameter.setBanco(new BancoParameter(Integer.parseInt(dadosBancarios.getBanco().numeroBanco)));

                    if (StringUtils.isNotBlank(dadosBancarios.getAgencia()))
                        parameter.setAgencia(new AgenciaParameter(dadosBancarios.getDigitoAgencia(), dadosBancarios.getAgencia()));

                    if (StringUtils.isNotBlank(dadosBancarios.getConta()))
                        parameter.setConta(new ContaParameter(dadosBancarios.getDigitoConta(), dadosBancarios.getConta(), null));

                    parameter.setUfContaBeneficio(dadosBancarios.getUf());

                }

                parameter.setBancoOrdemPagamento(0);
            }


            parameter.setAssociado(false);
            parameter.setIndicativoCarencia(Boolean.FALSE.booleanValue());
            parameter.setCargo(null);

            ClienteParameter cliente = new ClienteParameter();
            setarTelefones(atendimento, telefone, false, cliente);
            cliente.setCpf(atendimento.getCpf());

            Calendar dataNascimento = new GregorianCalendar(1962, Calendar.JULY, 5);

            if (atendimento.getDataNascimento() != null)
                dataNascimento.setTime(atendimento.getDataNascimento());

            cliente.setDataNascimento(dataNascimento);


            if (endereco != null) {

                EnderecoParamter enderecoParameter = new EnderecoParamter();
                enderecoParameter.setLogradouro(endereco.getLogradouro());
                enderecoParameter.setNumero(endereco.getNumero() == null ? null : endereco.getNumero().trim());
                enderecoParameter.setComplemento(endereco.getComplemento());
                enderecoParameter.setBairro(endereco.getBairro());
                enderecoParameter.setCidade(endereco.getCidade());
                enderecoParameter.setCep(endereco.getCep());
                enderecoParameter.setUf(endereco.getUf());
                cliente.setEndereco(enderecoParameter);

            }

            cliente.setEstadoCivil(atendimento.getEstadoCivil() == null ? null : atendimento.getEstadoCivil().getSigla());
            cliente.setGrauInstrucao("5");

            Calendar dataEmissao = null;

            if (atendimento.getDataEmissaoDocumento() != null) {
                dataEmissao = new GregorianCalendar();
                dataEmissao.setTime(atendimento.getDataEmissaoDocumento());

            }

            IdentidadeParameter identidade = new IdentidadeParameter(dataEmissao, atendimento.getOrgaoDocumento(), atendimento.getNumeroDocumento(), "Carteira de Identidade", atendimento.getUfDocumento());
            cliente.setIdentidade(identidade);
            cliente.setNome(atendimento.getNome());
            cliente.setNomeMae(atendimento.getNomeMae());
            cliente.setNomePai(atendimento.getNomePai());
            cliente.setNomeConjuge(atendimento.getNomeConjuge());
            cliente.setNacionalidade(atendimento.getNacionalidade());
            cliente.setSexo(atendimento.getSexo() == null ? null : atendimento.getSexo().name().substring(0, 1).toUpperCase());

            cliente.setCidadeNascimento(atendimento.getCidadeNascimento());
            cliente.setUfNascimento(atendimento.getUfNascimento());

            parameter.setCliente(cliente);
            parameter.setClientePreCadastrado(clienteCadastro);

            parameter.setCodEnt(Integer.valueOf(Integer.parseInt(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim())));

            if (StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) && StringUtils.isNumeric(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim())) {

                parameter.setSequencialOrgao(Integer.parseInt(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim()));

            } else {

                parameter.setSequencialOrgao(null);
            }

            if (produtoBmgRefin != null)
                parameter.setProduto(produtoBmgRefin.getCodigo());

            if (formaEnvio != null) {

                parameter.setCodigoFormaEnvioTermo(String.valueOf(formaEnvio.getCodigo()));

                if (formaEnvio.getCodigo().intValue() == 21)
                    parameter.setToken(token);

            }

            parameter.setCodigoLoja(StringUtils.isBlank(codLoja) ? null : Integer.valueOf(Integer.parseInt(codLoja)));
            parameter.setCodigoServico("040");
            parameter.setCodigoSituacaoServidor(null);
            parameter.setCpf(atendimento.getCpf());
            parameter.setCpfAgente(cpfAgent);
            parameter.setDataFator(Calendar.getInstance());
            parameter.setDataAdmissao(null);
            parameter.setCriterioTac(null);
            parameter.setCriterioIof(null);
            parameter.setCriterioTlf(null);

            Calendar dataRenda = Calendar.getInstance();
            dataRenda.set(5, -1);
            parameter.setDataRenda(dataRenda);
            parameter.setDescontoAdicional(0.0D);
            parameter.setDescontoCompulsorio(atendimento.getDescontoCompulsorio() == null ? 0.0D : atendimento.getDescontoCompulsorio().doubleValue());
            parameter.setDescontoOutro(atendimento.getDescontoFacultativo() == null ? 0.0D : atendimento.getDescontoFacultativo().doubleValue());
            parameter.setDescontoPossuiCartao(false);
            parameter.setDescontoVoluntario(0.0D);

            if (dadosBancarios.getTipoConta() == null) {
                parameter.setFinalidadeCredito(1);
            } else {
                parameter.setFinalidadeCredito(dadosBancarios.getTipoConta().getCodigo().intValue());
            }

            ContratosSimulacaoRefinBMG contratoRefin = listContratosRefin.get(0);
            parameter.setCodigoTabela(contratoRefin.getSimulacao().getTabelaFator());
            parameter.setFormaCredito((atendimento.getFormaPagamento() == null || atendimento.getFormaPagamento().getParametro() == null) ? null : atendimento.getFormaPagamento().getParametro().getCodigo().intValue());
            parameter.setIdentificadorMargem(null);
            parameter.setIgnorarInconsistenciasPN(false);
            parameter.setIncluiSeguroVidaFederal(Boolean.FALSE.booleanValue());
            parameter.setIndSeguroAderente(null);
            parameter.setIndicacao(null);
            parameter.setInformacoesAdicionais(null);
            parameter.setInserirAtendimentoPlusoft(false);
            parameter.setIpUsuario(null);
            parameter.setLatitude(null);
            parameter.setLatitude(null);
            parameter.setMatriculaInstituidor(null);
            parameter.setNumeroApolice(null);
            parameter.setNumeroCartao(null);
            parameter.setNumeroPeculio(null);
            parameter.setUtilizaUserConsig(null);
            //OUTROS null

            parameter.setMargem(atendimento.getMargem() == null ? contratoRefin.getSimulacao().getValorParcela() : atendimento.getMargem().doubleValue());
            parameter.setMatricula(atendimento.getBeneficioPrincipal());
            parameter.setNumeroPrestacoes(contratoRefin.getSimulacao().getParcelas().shortValue());
            parameter.setNumeroPropostaExterna("0");
            parameter.setPossuiCartao(false);
            //NULL
            parameter.setTipoBeneficio(atendimento.getTipoBeneficio());
            parameter.setTipoDomicilioBancario(Short.valueOf("1").shortValue());

            //null
            parameter.setValidouSenha(Boolean.FALSE.booleanValue());

            parameter.setValorIof(0.0D);
            parameter.setValorPrestacao(contratoRefin.getSimulacao().getValorParcela().doubleValue());
            parameter.setValorRenda(atendimento.getSalarioCliente() == null ? Double.valueOf("5000.0").doubleValue() : atendimento.getSalarioCliente().doubleValue());
            parameter.setValorSolicitado(contratoRefin.getSimulacao().getValorLiberado());
            parameter.setVinculoMatricula(StringUtils.isBlank(atendimento.getVinculoMatricula()) ? null : atendimento.getVinculoMatricula());
            parameter.setAgregacaoDeMargemParaSaqueComplementar(Boolean.FALSE.booleanValue());
            parameter.setValorAgregacaoDeMargemParaSaqueComplementar(0.0D);
            parameter.setAberturaContaPagamento(Boolean.TRUE.equals(Boolean.valueOf(atendimento.isAbrirContaPagamento())) ? 1 : 0);

            parameter.setTipoDomicilioBancario(Short.valueOf("1"));

            //null
            parameter.setValorCapitalSegurado(null);
            parameter.setValorResidual(null);
            parameter.setUnidadePagadora(null);
            parameter.setRetornoCetPn(null);
            parameter.setSenhaSME(null);
            parameter.setNumeroSorteio(null);
            parameter.setIdentificadorMargem(null);

            com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato[] contratos = new com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato[1];

            com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato contrato = new com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato();

            contrato.setDataCancelamento(contratoRefin.getContratoRefin().getContrato().getDataCancelamento());
            contrato.setDataDesagio(contratoRefin.getContratoRefin().getContrato().getDataDesagio());
            contrato.setDataInicio(contratoRefin.getContratoRefin().getContrato().getDataInicio());
            contrato.setDataFim(contratoRefin.getContratoRefin().getContrato().getDataFim());
            contrato.setNumeroDaAdesao(contratoRefin.getContratoRefin().getContrato().getNumeroDaAdesao());
            contrato.setNumeroInternoContrato(contratoRefin.getContratoRefin().getContrato().getNumeroInternoContrato());
            contrato.setNumeroExterno(contratoRefin.getContratoRefin().getContrato().getNumeroExterno());
            contrato.setDataInicioContrato(contratoRefin.getContratoRefin().getContrato().getDataInicioContrato());
            contrato.setMargemMetabusca(contratoRefin.getContratoRefin().getContrato().getMargemMetabusca());
            contrato.setMinimoParcelasRefinanciamento(contratoRefin.getContratoRefin().getContrato().getNumeroCalculo());
            contrato.setNumeroCalculo(contratoRefin.getContratoRefin().getContrato().getMinimoParcelasRefinanciamento());
            contrato.setNumeroContrato(contratoRefin.getContratoRefin().getContrato().getNumeroContrato());
            contrato.setNumeroTEC(contratoRefin.getContratoRefin().getContrato().getNumeroTEC());
            contrato.setPercentualParcelasPagas(contratoRefin.getContratoRefin().getContrato().getPercentualParcelasPagas());

            contrato.setQuantidadeParcelaFinal(contratoRefin.getContratoRefin().getContrato().getQuantidadeParcelaFinal());
            contrato.setQuantidadeParcelaInicial(contratoRefin.getContratoRefin().getContrato().getQuantidadeParcelaInicial());
            contrato.setQuantidadeParcelasAberto(contratoRefin.getContratoRefin().getContrato().getQuantidadeParcelasAberto());

            contrato.setQuantidadeParcelasPagas(contratoRefin.getContratoRefin().getContrato().getQuantidadeParcelasPagas());
            contrato.setQuantidadeParcelasRefin(contratoRefin.getContratoRefin().getContrato().getQuantidadeParcelasRefin());
            contrato.setQuantidadeTotalParcela(contratoRefin.getContratoRefin().getContrato().getQuantidadeTotalParcela());
            contrato.setSaldoDevedor(contratoRefin.getContratoRefin().getContrato().getSaldoDevedor());
            contrato.setTimestamp(contratoRefin.getContratoRefin().getContrato().getTimestamp());
            contrato.setTipoRefin(contratoRefin.getContratoRefin().getContrato().getTipoRefin());
            contrato.setValorParcela(contratoRefin.getContratoRefin().getContrato().getValorParcela());
            contratos[0] = contrato;
            parameter.setListaContrato(contratos);
            parameter.setLogin(usrBmg);
            parameter.setSenha(senhaBmg);
            parameter.setLoginConsig(loginConsig);
            parameter.setSenhaConsig(senhaConsig);

            servico = (new RefinanciamentoWebServiceServiceLocator()).getRefinanciamento(new URL(urlWsBMG + URL_REFIN));
            ((Stub) servico).setTimeout(30000);

            propostaGeradas = servico.gravaPropostaRefinanciamento(parameter);


        } catch (ProativaException e) {

            finalizarConsulta(usuario);

            throw e;

        } catch (PropostaConsignacaoListException | ConsigBusinessException e) {

            e.printStackTrace();

            finalizarConsulta(usuario);

            throw new ProativaException("Houve erro na Webservice BMG: " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            e.printStackTrace();

            finalizarConsulta(usuario);

            if (e.getMessage() == null)
                throw new ProativaException("Ocorreu um erro inesperado");

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices Contrato Refin do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().contains("com.bmg.econsig.consignacao.exception"))
                throw new ProativaException("Erro WebServices Contrato Refin do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Servidor inoperante : " + urlWsBMG);

            }
            throw new ProativaException("Ocorreu um erro inesperado no web services refin contrato do consig - "
                    + tratarErro(e.getMessage()));

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_REFIN, "gravarProposta", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

        }

        return propostaGeradas;

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

        WsdlRefinBmgUtil refin = new WsdlRefinBmgUtil();

        try {

            Atendimento atendimento = new Atendimento();

            atendimento.setCpf("25767851824");
            // 47277009215
            atendimento.setEntidadePrincipal("4195");
            atendimento.setOrgaoPrincipal("1");

            List<ProdutoReturn> produtors = refin.retornarProdutosRefin("https://ws1.bmgconsig.com.br/webservices", "robô.proativa.39540", "77b5871#", null, false);

            /*
             * for (ProdutoReturn produtoReturn : produtors) {
             *
             * // System.out.println(produtoReturn.getDescricao()+" - //
             * "+produtoReturn.getCodigo());
             *
             * for (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.
             * FormaEnvioRetorno envio : produtoReturn.getFormasEnvio()) { //
             * System.out.println("\t"+envio.getDescricao()+" "+envio.getCodigo()); }
             *
             * }
             */

            // List<ContratoRefin> list =
            // refin.retornarContratosRefin("https://ws1.bmgconsig.com.br/webservices",
            // "robo.53360", "39c67b2%",atendimento,null,null,false,false);

            ContratosSimulacaoRefinBMG c = refin.retornarContratoSimulacaoRefin("https://ws1.bmgconsig.com.br/webservices", "pamelagra", "416a253@", atendimento, null, null, "38746", 9615, false);

            // List<ContratoRefin> list =
            // refin.retornarContratosRefin("https://ws1.bmgconsig.com.br/webservices",
            // "robô.proativa.39540", "77b5871#",atendimento,null,null,false,false);

            if (c.getSimulacao() != null)
                System.out.println("Adesao: " + c.getContratoRefin().getContrato().getNumeroDaAdesao() + " : Valor Liberado: " + c.getSimulacao().getValorLiquido() + "- PARCELAR: " + c.getSimulacao().getParcelas());

        } catch (ProativaException e) {

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
