package com.proativaservicos.util;

import com.github.underscore.lodash.U;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.bmg.CartaoProdutoSeguroBMG;
import com.proativaservicos.model.bmg.ClienteProdutoSeguroBMG;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.*;
import com.proativaservicos.service.asynchronous.produtoseguros.*;
import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
import com.proativaservicos.util.constantes.ParamentroFormaPagamentoEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import com.proativaservicos.util.constantes.TipoFormaEnvioEnum;
import com.thoughtworks.xstream.XStream;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Stub;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

@Named
public class WsdlBmgUtil implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private RegistroSistemaUtil registro;

    public static String URL_SAQUE_COMPLEMENTAR = "/SaqueComplementar?wsdl";
    public static String URL_PRODUTO_SEGURO = "/ProdutoSeguroWebService?wsdl";
    public static Integer COD_SEGURO_PAPCARD = 20;
    public static Integer COD_TIPO_PAGAMENTO_PAPCARD = 2;

    public List<CartaoSaqueComplementarBmg> retornarCartoesDisponiveisBmg(String Urlsdl, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Long idCampanha, Usuario usuario,
                                                                          boolean validarPropostaGravada, boolean validarSeguro, boolean gravarLog, Integer indiceCartao)
            throws ProativaException, Exception {

        CartaoDisponivelParameter cartaoParamiter = null;
        CartaoDisponivelRetorno cartaoRetorno = null;
        SaqueComplementarWebService servico = null;

        String logErro = "";
        String logErroCartao = "";
        VerificarLinkUtil.verificarLink(Urlsdl);

        try {

            cartaoParamiter = new CartaoDisponivelParameter();
            cartaoParamiter.setLogin(usrWsdl);
            cartaoParamiter.setSenha(senhaWsdl);
            cartaoParamiter.setCpf(atendimento.getCpf());

            cartaoParamiter.setCodigoEntidade((StringUtils.isBlank(atendimento.getEntidadePrincipal())) ? 1581 : Integer.parseInt(atendimento.getEntidadePrincipal().trim()));
            cartaoParamiter.setSequencialOrgao((StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) ? Integer.valueOf(atendimento.getOrgaoPrincipal().trim()) : null));

            servico = (new SaqueComplementarWebServiceServiceLocator()).getSaqueComplementar(new URL(Urlsdl + URL_SAQUE_COMPLEMENTAR));

            cartaoRetorno = servico.buscarCartoesDisponiveis(cartaoParamiter);

            List<CartaoSaqueComplementarBmg> listCartoesBmg = new ArrayList<CartaoSaqueComplementarBmg>();

            if (cartaoRetorno != null && cartaoRetorno.getCartoesRetorno() != null && (cartaoRetorno.getCartoesRetorno().length > 0)) {

                if (indiceCartao == null) {

                    indiceCartao = 0;
                }

                String erroCartaoLiberado = null;

                for (CartaoRetorno retorno : cartaoRetorno.getCartoesRetorno()) {

                    if (retorno != null && retorno.isLiberado()) {

                        LimiteSaqueRetorno limite = retornarLimiteSaqueRetorno(Urlsdl, usrWsdl, senhaWsdl, atendimento, idCampanha, usuario, retorno, true, gravarLog);

                        PlanosContratacaoSeguroStandAlone plano = null;

                        if (limite != null) {

                            Integer index1 = indiceCartao;

                            if (validarSeguro && limite.isElegivelSeguros()) {

                                CartaoClienteAtivoVendaSeguro cartaoSeguro = new CartaoClienteAtivoVendaSeguro();

                                cartaoSeguro.setNumeroInternoConta(Integer.valueOf(retorno.getNumeroContaInterna().toString()));
                                cartaoSeguro.setCodigoEntidade(atendimento.getEntidadePrincipal() == null ? null : atendimento.getEntidadePrincipal());
                                cartaoSeguro.setSequencialOrgao(StringUtils.isBlank(atendimento.getOrgaoPrincipal()) ? null : Short.valueOf(atendimento.getOrgaoPrincipal()));
                                cartaoSeguro.setLimiteCartao(BigDecimal.valueOf(limite.getLimiteCartao()));
                                ListaPlanosSeguroReturn planosRetorno = retornarPlanosSeguroSaque(Urlsdl, usrWsdl, senhaWsdl, atendimento, idCampanha, usuario, cartaoSeguro, false);

                                if (planosRetorno != null && !planosRetorno.isExcecaoDeRegraDeNegocio() && !planosRetorno.isExcecaoGenerica() && planosRetorno.getPlanos() != null && planosRetorno.getPlanos().length >= 1) {

                                    plano = planosRetorno.getPlanos()[planosRetorno.getPlanos().length - 1];

                                }

                            }

                            listCartoesBmg.add(new CartaoSaqueComplementarBmg(index1, retorno, limite, plano));

                        }

                    } else if (retorno != null && !retorno.isLiberado()) {
                        // ERRO IMPEDIDO....

                        erroCartaoLiberado = retorno.getMensagemImpedimento();
                    }

                }

                if (erroCartaoLiberado != null && listCartoesBmg.isEmpty()) {

                    throw new ProativaException(erroCartaoLiberado);
                }
            }

            return listCartoesBmg;

        } catch (ProativaException e) {

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage() + " - " + logErroCartao;
            //e.printStackTrace();

            throw e;

        } catch (com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException e) {

            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            throw new ProativaException("OCORREU UM ERRO Webservice BMG: " + tratarErro(e.getFaultString() + logErroCartao));

        } catch (Exception e) {

            //e.printStackTrace();

            if (gravarLog)
                finalizarConsulta(usuario);

            logErro = e.getMessage();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("Erro WebServices SaqueComplementar do consig : " + tratarErro(e.getMessage()) + logErroCartao);

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Webservice indisponivel - " + usrWsdl);
            }

            //e.printStackTrace();

            throw new Exception("Ocorreu um erro inesperado no web services saque complementar do consig - "
                    + tratarErro(e.getMessage()) + logErroCartao);

        } finally {

            if (gravarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (atendimento!=null && StringUtils.isNotBlank( atendimento.getCpf()))
                    System.out.println("CONSULTA CARTAO REALIZADA: " + atendimento.getCpf());

                if (StringUtils.isNotBlank(logErro)) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarCartoesDisponiveisBmg", retornarXmlEnvio(servico), logErro);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarCartoesDisponiveisBmg", retornarXmlEnvio(servico), retornarXmlRetorno(servico));
                }

            }

        }

    }

    /*
     * Consulta Seguro Pap Card
     */

    public ClienteProdutoSeguroBMG retornarCartoesDisponiveisSeguroBmg(String Urlsdl, String usrWsdl, String senhaWsdl,
                                                                       GenericAtendimento atendimento, Long idCampanha, Usuario usuario,Integer codigoSeguro, boolean gravarLog)
            throws ProativaException, Exception {

        ListaCartoesSeguroParameter cartaoParamiter = null;
        ListaCartoesSeguroReturn cartaoRetorno = null;
        ProdutoSeguroWebService servico = null;

        String logErro = "";
        String logErroCartao = "";

        //	VerificarLinkUtil.verificarLink(Urlsdl);
        ClienteProdutoSeguroBMG clienteProdutoSeguroBMG = null;

        try {

            cartaoParamiter = new ListaCartoesSeguroParameter();
            cartaoParamiter.setLogin(usrWsdl);
            cartaoParamiter.setSenha(senhaWsdl);
            cartaoParamiter.setCpf(atendimento.getCpf());
            cartaoParamiter.setTipoPagamento(COD_TIPO_PAGAMENTO_PAPCARD);
            cartaoParamiter.setCodigoSeguro(codigoSeguro==null? COD_SEGURO_PAPCARD:codigoSeguro);

            servico = (ProdutoSeguroWebService) (new ProdutoSeguroWebServiceServiceLocator()).getProdutoSeguroWebService(new URL(Urlsdl + URL_PRODUTO_SEGURO));
            ((Stub) servico).setTimeout(10000);

            cartaoRetorno = servico.obterCartoesDisponiveis(cartaoParamiter);

            if (StringUtils.isBlank(cartaoRetorno.getMensagemDeErro()) && cartaoRetorno != null && cartaoRetorno.getCartaoClienteAtivoVendaSeguro() != null && (cartaoRetorno.getCartaoClienteAtivoVendaSeguro().length > 0)) {

                clienteProdutoSeguroBMG = new ClienteProdutoSeguroBMG();
                clienteProdutoSeguroBMG.setListaCartaoPlanos(new ArrayList<CartaoProdutoSeguroBMG>());
                clienteProdutoSeguroBMG.setMensagemDeErro(cartaoRetorno.getMensagemDeErro());
                clienteProdutoSeguroBMG.setExcecaoDeRegraDeNegocio(cartaoRetorno.isExcecaoDeRegraDeNegocio());
                clienteProdutoSeguroBMG.setExcecaoGenerica(cartaoRetorno.isExcecaoGenerica());

                int cod = 0;

                for (CartaoClienteAtivoVendaSeguro retornoCartaoAtivoVendaSeguro : cartaoRetorno.getCartaoClienteAtivoVendaSeguro()) {

                    if (retornoCartaoAtivoVendaSeguro != null && StringUtils.isNotBlank(retornoCartaoAtivoVendaSeguro.getMotivoElegibilidade())) {

                        // CONTEN ELETIVIDADE
                        clienteProdutoSeguroBMG.getListaCartaoPlanos().add(new CartaoProdutoSeguroBMG(cod++, retornoCartaoAtivoVendaSeguro, null));

                    } else {

                        if (retornoCartaoAtivoVendaSeguro != null) {

                            ListaPlanosSeguroReturn planos = retornarPlanosSeguro(Urlsdl, usrWsdl, senhaWsdl, atendimento, idCampanha, usuario, retornoCartaoAtivoVendaSeguro, codigoSeguro, false);

                            if (planos != null) {

                                if (planos.getPlanos() != null && planos.getPlanos().length >= 1)
                                    planos.setPlanos(planos.getPlanos());

                                clienteProdutoSeguroBMG.getListaCartaoPlanos().add(new CartaoProdutoSeguroBMG(cod++, retornoCartaoAtivoVendaSeguro, planos));

                            }

                        }

                    }

                }

            } else {

                clienteProdutoSeguroBMG = new ClienteProdutoSeguroBMG();

                clienteProdutoSeguroBMG.setExcecaoDeRegraDeNegocio(cartaoRetorno.isExcecaoDeRegraDeNegocio());
                clienteProdutoSeguroBMG.setExcecaoGenerica(cartaoRetorno.isExcecaoGenerica());

                clienteProdutoSeguroBMG.setMensagemDeErro(StringUtils.isNotBlank(cartaoRetorno.getMensagemDeErro()) ? cartaoRetorno.getMensagemDeErro() : "Nenhum cartão disponivel.");

            }

            return clienteProdutoSeguroBMG;

        } catch (ProativaException e) {

            finalizarConsulta(usuario);
            logErro = e.getMessage() + " - " + logErroCartao;
            //e.printStackTrace();

            throw e;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {

            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            throw new ProativaException("ERRO WEBSERVICES BMG: " + tratarErro(e.getFaultString() + logErroCartao));

        } catch (Exception e) {


            finalizarConsulta(usuario);

            logErro = e.getMessage();

            //	e.printStackTrace();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("ERRO WEBSERVICES Produtos Seguro do consig : " + tratarErro(e.getMessage()) + logErroCartao);

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("Service Unavailable")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Webservice indisponivel - Link : " + Urlsdl);
            }

            // e.printStackTrace();

            throw new ProativaException("Ocorreu erro inesperado no web services Produtos seguro do consig - "
                    + tratarErro(e.getMessage()) + logErroCartao);

        } finally {

            if (gravarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (StringUtils.isNotBlank(logErro)) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario,
                            TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarCartoesDisponiveisSeguroBmg",
                            (cartaoParamiter == null) ? null : retornarXmlEnvio(servico), logErro);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario,
                            TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarCartoesDisponiveisSeguroBmg",
                            retornarXML(ListaCartoesSeguroParameter.class, cartaoParamiter), retornarXML(ListaCartoesSeguroReturn.class, cartaoRetorno));


                }

            }

        }

    }


    /*
     * Consulta Seguro BMG MED
     */

    public ClienteProdutoSeguroBMG retornarCartoesDisponiveisSeguroBmgMed(String Urlsdl, String usrWsdl, String senhaWsdl,
                                                                       GenericAtendimento atendimento, Long idCampanha, Usuario usuario,Integer codigoSeguro, boolean gravarLog)
            throws ProativaException, Exception {

        ListaCartoesSeguroParameter cartaoParamiter = null;
        ListaCartoesSeguroReturn cartaoRetorno = null;
        ProdutoSeguroWebService servico = null;

        String logErro = "";
        String logErroCartao = "";

        //	VerificarLinkUtil.verificarLink(Urlsdl);
        ClienteProdutoSeguroBMG clienteProdutoSeguroBMG = null;

        try {

            cartaoParamiter = new ListaCartoesSeguroParameter();
            cartaoParamiter.setLogin(usrWsdl);
            cartaoParamiter.setSenha(senhaWsdl);
            cartaoParamiter.setCpf(atendimento.getCpf());
            cartaoParamiter.setTipoPagamento(COD_TIPO_PAGAMENTO_PAPCARD);
            cartaoParamiter.setCodigoSeguro(COD_SEGURO_PAPCARD);

            servico = (ProdutoSeguroWebService) (new ProdutoSeguroWebServiceServiceLocator()).getProdutoSeguroWebService(new URL(Urlsdl + URL_PRODUTO_SEGURO));
            ((Stub) servico).setTimeout(10000);

            cartaoRetorno = servico.obterCartoesDisponiveis(cartaoParamiter);

            if (StringUtils.isBlank(cartaoRetorno.getMensagemDeErro()) && cartaoRetorno != null && cartaoRetorno.getCartaoClienteAtivoVendaSeguro() != null && (cartaoRetorno.getCartaoClienteAtivoVendaSeguro().length > 0)) {

                clienteProdutoSeguroBMG = new ClienteProdutoSeguroBMG();
                clienteProdutoSeguroBMG.setListaCartaoPlanos(new ArrayList<CartaoProdutoSeguroBMG>());
                clienteProdutoSeguroBMG.setMensagemDeErro(cartaoRetorno.getMensagemDeErro());
                clienteProdutoSeguroBMG.setExcecaoDeRegraDeNegocio(cartaoRetorno.isExcecaoDeRegraDeNegocio());
                clienteProdutoSeguroBMG.setExcecaoGenerica(cartaoRetorno.isExcecaoGenerica());

                int cod = 0;

                for (CartaoClienteAtivoVendaSeguro retornoCartaoAtivoVendaSeguro : cartaoRetorno.getCartaoClienteAtivoVendaSeguro()) {

                    if (retornoCartaoAtivoVendaSeguro != null && StringUtils.isNotBlank(retornoCartaoAtivoVendaSeguro.getMotivoElegibilidade())) {

                        // CONTEN ELETIVIDADE
                        clienteProdutoSeguroBMG.getListaCartaoPlanos().add(new CartaoProdutoSeguroBMG(cod++, retornoCartaoAtivoVendaSeguro, null));

                    } else {

                        if (retornoCartaoAtivoVendaSeguro != null) {

                            ListaPlanosSeguroReturn planos = retornarPlanosSeguro(Urlsdl, usrWsdl, senhaWsdl, atendimento, idCampanha, usuario, retornoCartaoAtivoVendaSeguro,codigoSeguro, false);

                            if (planos != null) {

                                if (planos.getPlanos() != null && planos.getPlanos().length >= 1)
                                    planos.setPlanos(planos.getPlanos());

                                clienteProdutoSeguroBMG.getListaCartaoPlanos().add(new CartaoProdutoSeguroBMG(cod++, retornoCartaoAtivoVendaSeguro, planos));

                            }

                        }

                    }

                }

            } else {

                clienteProdutoSeguroBMG = new ClienteProdutoSeguroBMG();

                clienteProdutoSeguroBMG.setExcecaoDeRegraDeNegocio(cartaoRetorno.isExcecaoDeRegraDeNegocio());
                clienteProdutoSeguroBMG.setExcecaoGenerica(cartaoRetorno.isExcecaoGenerica());

                clienteProdutoSeguroBMG.setMensagemDeErro(StringUtils.isNotBlank(cartaoRetorno.getMensagemDeErro()) ? cartaoRetorno.getMensagemDeErro() : "Nenhum cartão disponivel.");

            }

            return clienteProdutoSeguroBMG;

        } catch (ProativaException e) {

            finalizarConsulta(usuario);
            logErro = e.getMessage() + " - " + logErroCartao;
            //e.printStackTrace();

            throw e;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {

            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            throw new ProativaException("ERRO WEBSERVICES BMG: " + tratarErro(e.getFaultString() + logErroCartao));

        } catch (Exception e) {


            finalizarConsulta(usuario);

            logErro = e.getMessage();

            //	e.printStackTrace();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException("ERRO WEBSERVICES Produtos Seguro do consig : " + tratarErro(e.getMessage()) + logErroCartao);

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("Service Unavailable")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Webservice indisponivel - Link : " + Urlsdl);
            }

            // e.printStackTrace();

            throw new ProativaException("Ocorreu erro inesperado no web services Produtos seguro do consig - "
                    + tratarErro(e.getMessage()) + logErroCartao);

        } finally {

            if (gravarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (StringUtils.isNotBlank(logErro)) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario,
                            TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarCartoesDisponiveisSeguroBmg",
                            (cartaoParamiter == null) ? null : retornarXmlEnvio(servico), logErro);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario,
                            TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarCartoesDisponiveisSeguroBmg",
                            retornarXML(ListaCartoesSeguroParameter.class, cartaoParamiter), retornarXML(ListaCartoesSeguroReturn.class, cartaoRetorno));


                }

            }

        }

    }


    private LimiteSaqueRetorno retornarLimiteSaqueRetorno(String Urlsdl, String usrWsdl, String senhaWsdl,
                                                          GenericAtendimento atendimento, Long idCampanha, Usuario usuario, CartaoRetorno cartao,
                                                          boolean consultarSeguro, boolean registrarLog) throws ProativaException, Exception {

        DadosCartaoParameter parametro = null;
        LimiteSaqueRetorno limiteSaqueRetorno = null;
        VerificarLinkUtil.verificarLink(Urlsdl);
        String errorMsg = null;
        SaqueComplementarWebService servico = null;

        try {

            parametro = new DadosCartaoParameter();
            parametro.setCpf(atendimento.getCpf().trim());
            parametro.setLogin(usrWsdl);
            parametro.setSenha(senhaWsdl);
            parametro.setMatricula(cartao.getMatricula().trim());
            parametro.setNumeroContaInterna(cartao.getNumeroContaInterna());
            parametro.setTipoSaque(1);

            parametro.setCodigoEntidade(StringUtils.isBlank(atendimento.getEntidadePrincipal()) ? 1581
                    : Integer.valueOf(atendimento.getEntidadePrincipal().trim()));
            parametro.setSequencialOrgao((StringUtils.isNotBlank(atendimento.getOrgaoPrincipal())
                    ? Integer.valueOf(atendimento.getOrgaoPrincipal().trim())
                    : null));

            servico = (new SaqueComplementarWebServiceServiceLocator())
                    .getSaqueComplementar(new URL(Urlsdl + URL_SAQUE_COMPLEMENTAR));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            limiteSaqueRetorno = servico.buscarLimiteSaque(parametro);

            return limiteSaqueRetorno;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {
            // TODO Auto-generated catch block
            if (registrarLog)
                finalizarConsulta(usuario);

            errorMsg = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();
            throw new ProativaException("Erro webservice BMG : " + tratarErro(e.getFaultString().trim()));

        } catch (Exception e) {

            if (registrarLog)
                finalizarConsulta(usuario);

            errorMsg = e.getMessage();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices SaqueComplementar BMG : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("Webservice indisponivel : " + Urlsdl);

            }

            // e.printStackTrace();

            throw new ProativaException("Ocorreu um erro inesperado no web services saque complementar do consig - "
                    + tratarErro(e.getMessage().trim()));

        } finally {

            if (registrarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (errorMsg != null) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario,
                            TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "buscarLimiteSaque", retornarXmlEnvio(servico),
                            errorMsg);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario,
                            TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "buscarLimiteSaque", retornarXmlEnvio(servico),
                            retornarXmlRetorno(servico));

                }

            }

        }

    }

    private ListaPlanosSeguroReturn retornarPlanosSeguro(String Urlsdl, String usrWsdl, String senhaWsdl,
                                                         GenericAtendimento atendimento, Long idCampanha, Usuario usuario, CartaoClienteAtivoVendaSeguro cartao,Integer codigoPlano,
                                                         boolean registrarLog) throws ProativaException, Exception {

        DadosCartaoParameter parametro = null;
        ListaPlanosSeguroReturn listaPlanosSeguro = null;
        VerificarLinkUtil.verificarLink(Urlsdl);
        String errorMsg = null;

        ProdutoSeguroWebService servico = null;

        try {

            PlanosSeguroParameter planos = new PlanosSeguroParameter();
            planos.setLogin(usrWsdl);
            planos.setSenha(senhaWsdl);

            planos.setNumeroInternoConta(cartao.getNumeroInternoConta());

            planos.setCodigoProdutoSeguro( codigoPlano==null? COD_SEGURO_PAPCARD: codigoPlano);
            planos.setEntidade(cartao.getCodigoEntidade());
            planos.setDataNascimento(cartao.getDataNascimento());
            planos.setSequencialOrgao(String.valueOf(cartao.getSequencialOrgao()));
            planos.setTipoPagamentoSeguro(2);
            planos.setRenda(cartao.getLimiteCartao().doubleValue());

            servico = (new ProdutoSeguroWebServiceServiceLocator()).getProdutoSeguroWebService(new URL(Urlsdl + URL_PRODUTO_SEGURO));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            listaPlanosSeguro = servico.listaPlanosRating(planos);


            return listaPlanosSeguro;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {
            // TODO Auto-generated catch block

            finalizarConsulta(usuario);
            //e.printStackTrace();
            errorMsg = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            throw new ProativaException("Erro webservice BMG : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            errorMsg = e.getMessage();
            //e.printStackTrace();
            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices SaqueComplementar BMG : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("Webservice indisponivel - Link : " + Urlsdl);

            }

            // e.printStackTrace();

            throw new ProativaException("Ocorreu um erro inesperado no web services produtos seguro do consig - " + tratarErro(e.getMessage()));

        } finally {

            if (registrarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (StringUtils.isNotBlank(errorMsg)) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarPlanosSeguro", (parametro == null) ? null : retornarXML(DadosCartaoParameter.class, parametro), errorMsg);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarPlanosSeguro", retornarXML(DadosCartaoParameter.class, parametro), retornarXML(ListaPlanosSeguroReturn.class, listaPlanosSeguro));
                }

            }

        }

    }


    private ListaPlanosSeguroReturn retornarPlanosSeguroRating(String Urlsdl, String usrWsdl, String senhaWsdl,
                                                         GenericAtendimento atendimento, Long idCampanha, Usuario usuario, CartaoClienteAtivoVendaSeguro cartao,
                                                         boolean registrarLog) throws ProativaException, Exception {

        DadosCartaoParameter parametro = null;
        ListaPlanosSeguroReturn listaPlanosSeguro = null;
        VerificarLinkUtil.verificarLink(Urlsdl);
        String errorMsg = null;

        ProdutoSeguroWebService servico = null;
        try {

            PlanosSeguroParameter planos = new PlanosSeguroParameter();
            planos.setLogin(usrWsdl);
            planos.setSenha(senhaWsdl);

            planos.setNumeroInternoConta(Integer.valueOf(cartao.getNumeroInternoConta()));

            planos.setCodigoProdutoSeguro(1007);
            planos.setEntidade(cartao.getCodigoEntidade());
            planos.setSequencialOrgao(String.valueOf(cartao.getSequencialOrgao()));
            planos.setTipoPagamentoSeguro(2);
            planos.setRenda(cartao.getLimiteCartao().doubleValue());

            servico = (new ProdutoSeguroWebServiceServiceLocator()).getProdutoSeguroWebService(new URL(Urlsdl + URL_PRODUTO_SEGURO));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            listaPlanosSeguro = servico.listaPlanosRating(planos);



            return listaPlanosSeguro;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {
            // TODO Auto-generated catch block

            finalizarConsulta(usuario);
            //e.printStackTrace();
            errorMsg = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            throw new ProativaException("Erro webservice BMG : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            errorMsg = e.getMessage();
            //e.printStackTrace();
            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices SaqueComplementar BMG : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("Webservice indisponivel - Link : " + Urlsdl);

            }

            // e.printStackTrace();

            throw new ProativaException("Ocorreu um erro inesperado no web services produtos seguro do consig - " + tratarErro(e.getMessage()));

        } finally {

            if (registrarLog) {

                Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

                if (StringUtils.isNotBlank(errorMsg)) {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarPlanosSeguro", (parametro == null) ? null : retornarXML(DadosCartaoParameter.class, parametro), errorMsg);

                } else {

                    this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarPlanosSeguro", retornarXML(DadosCartaoParameter.class, parametro), retornarXML(ListaPlanosSeguroReturn.class, listaPlanosSeguro));
                }

            }

        }

    }

    private ListaPlanosSeguroReturn retornarPlanosSeguroSaque(String Urlsdl, String usrWsdl, String senhaWsdl,
                                                              GenericAtendimento atendimento, Long idCampanha, Usuario usuario, CartaoClienteAtivoVendaSeguro cartao,
                                                              boolean registrarLog) {

        ListaPlanosSeguroReturn listaPlanosSeguro = null;

        try {

            PlanosSeguroParameter planos = new PlanosSeguroParameter();
            planos.setLogin(usrWsdl);
            planos.setSenha(senhaWsdl);

            planos.setNumeroInternoConta(Integer.valueOf(cartao.getNumeroInternoConta()));

            planos.setCodigoProdutoSeguro(Integer.valueOf(1007));
            planos.setEntidade(cartao.getCodigoEntidade());
            planos.setSequencialOrgao(String.valueOf(cartao.getSequencialOrgao()));
            planos.setTipoPagamentoSeguro(Integer.valueOf(2));
            planos.setRenda(cartao.getLimiteCartao().doubleValue());

            ProdutoSeguroWebService servico;

            servico = (new ProdutoSeguroWebServiceServiceLocator()).getProdutoSeguroWebService(new URL(Urlsdl + URL_PRODUTO_SEGURO));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            listaPlanosSeguro = servico.listaPlanos(planos);

            return listaPlanosSeguro;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

    private String retornarXML(Class<?> classe, Object entidade) {

        XStream xstream = new XStream();
        xstream.processAnnotations(classe);

        return xstream.toXML(entidade);
    }

    public void finalizarConsulta(Usuario usuario) {

        this.registro.registrarLog(usuario.getId(), TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE,
                "finalizou consulta saque", usuario.getIp());

    }

    private String tratarErro(String erro) {

        return (erro == null) ? null
                : erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "")
                .replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
                .replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
                .replaceAll("com.bmg.econsig.consignacao.exception.PropostaConsignacaoListException:", "").replaceAll("\\r\\n|\\n", "")
                .trim();
    }

    public String retornarScriptVenda(String urlWS, String usuarioWS, String senhaWS, String loginConsig,
                                      String senhaConsig, GenericAtendimento atendimento, Long campanha, Usuario usuario)
            throws ProativaException, Exception {

        String scriptAtendimento = null;

        String erro = null;

        ScriptIdentificacaoParameter parametro = null;

        VerificarLinkUtil.verificarLink(urlWS);
        SaqueComplementarWebService servico = null;

        try {

            if (atendimento == null) {

                throw new ProativaException("Dados Atendimento não informado.");
            }

            if (StringUtils.isEmpty(atendimento.getEntidadePrincipal())) {

                throw new ProativaException("Entidade não Informada.");

            }

            parametro = new ScriptIdentificacaoParameter();

            parametro.setLogin(usuarioWS);
            parametro.setSenha(senhaWS);
            parametro.setLoginConsig(loginConsig);
            parametro.setSenhaConsig(senhaConsig);
            parametro.setCpf(atendimento.getCpf());
            parametro.setNomeCliente(atendimento.getNome());

            parametro.setCodigoEntidade(Integer.parseInt(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim()));

            if (StringUtils.isEmpty(atendimento.getOrgaoPrincipal())) {

                parametro.setSequencialOrgao(null);

            } else {

                parametro.setSequencialOrgao(Integer.valueOf(Integer.parseInt(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim())));
            }

            servico = (new SaqueComplementarWebServiceServiceLocator()).getSaqueComplementar(new URL(urlWS));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            scriptAtendimento = servico.geraScriptIdentificacao(parametro);

            return scriptAtendimento.replaceAll("\\|{2}", "<br/>");

        } catch (ProativaException e) {

            finalizarConsulta(usuario);

            erro = e.getMessage();
            throw e;

        } catch (ServiceException e) {

            finalizarConsulta(usuario);

            erro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();
            throw new ProativaException("Erro WebServices SaqueComplementar do consig : " + tratarErro(e.getFaultString()));

        } catch (PropostaConsignacaoListException e) {

            finalizarConsulta(usuario);

            erro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();
            throw new ProativaException(
                    "Erro WebServices SaqueComplementar do consig : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            erro = e.getMessage();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException(
                        "Erro WebServices SaqueComplementar do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("Webservice indisponivel. Link : " + urlWS);

            }

            e.printStackTrace();
            throw new Exception("Ocorreu um erro inesperado no web services saque complementar do consig - "
                    + tratarErro(e.getMessage()));

        } finally {

            Long codigoAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            if (StringUtils.isNotBlank(erro)) {

                this.registro.registraLog(codigoAtendimento, campanha, usuario,
                        TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "geraScriptIdentificacao", retornarXmlEnvio(servico),
                        erro);

            } else {

                this.registro.registraLog(codigoAtendimento, campanha, usuario,
                        TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "geraScriptIdentificacao",
                        retornarXmlRetorno(servico), retornarXmlRetorno(servico));
            }
        }
    }

    public String retornarScriptVendaPapCard(String urlWS, String usuarioWS, String senhaWS, String loginConsig,
                                             String senhaConsig, GenericAtendimento atendimento, Long campanha, Usuario usuario,
                                             PlanosContratacaoSeguroStandAlone plano, CartaoProdutoSeguroBMG cartaoSeguro,
                                             FormaPagamentoProdutoSeguro formaPagamento) throws ProativaException, Exception {

        String scriptAtendimento = null;

        String erro = null;

        ScriptSeguroParameter parametro = null;

        VerificarLinkUtil.verificarLink(urlWS);
        ScriptAdesaoReturn retorno = null;
        ProdutoSeguroWebService servico = null;

        try {

            if (atendimento == null) {

                throw new ProativaException("Dados Atendimento não informado.");
            }

            parametro = new ScriptSeguroParameter();
            parametro.setLogin(usuarioWS);
            parametro.setSenha(senhaWS);
            parametro.setLoginConsig(loginConsig);
            parametro.setSenhaConsig(senhaConsig);
            parametro.setCpf(atendimento.getCpf());

            parametro.setRenda(cartaoSeguro.getCartaoClienteAtivoVendaSeguro().getLimiteCartao().doubleValue());
            parametro.setFormaPagamentoProdutoSeguro(0);

            if (formaPagamento != null)
                parametro.setFormaPagamentoProdutoSeguro(formaPagamento.getCodigo());

            parametro.setCodigoTipoPagamento(Integer.valueOf(COD_TIPO_PAGAMENTO_PAPCARD));
            parametro.setCodigoSeguro(COD_SEGURO_PAPCARD);

            parametro.setNumeroInternoConta(cartaoSeguro.getCartaoClienteAtivoVendaSeguro().getNumeroInternoConta());

            parametro.setCodLoja(38746);

            parametro.setCodigoPlano(plano.getCodigoPlano());

            servico = (new ProdutoSeguroWebServiceServiceLocator()).getProdutoSeguroWebService(new URL(urlWS + URL_PRODUTO_SEGURO));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            retorno = servico.geraScriptVenda(parametro);

            scriptAtendimento = retorno.getScript();

            if (!retorno.isExecutadoComSucessso())
                throw new ProativaException("Erro Webservice Consig: " + retorno.getMensagemDeErro());

            return scriptAtendimento.replaceAll("\\|{2}", "<br/>");

        } catch (ProativaException e) {

            finalizarConsulta(usuario);

            erro = e.getMessage();
            throw e;

        } catch (ServiceException e) {

            finalizarConsulta(usuario);

            erro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();
            throw new ProativaException("Erro WebServices ProdutoSeguro do consig : " + tratarErro(e.getFaultString()));

        } catch (PropostaConsignacaoListException e) {

            finalizarConsulta(usuario);

            erro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();
            throw new ProativaException("Erro WebServices ProdutoSeguro do consig : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            erro = e.getMessage();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices ProdutoSeguro do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("Webservice indisponivel. Link : " + urlWS);

            }

            e.printStackTrace();
            throw new Exception("Ocorreu um erro inesperado no web services produto seguro do consig - " + tratarErro(e.getMessage()));

        } finally {

            Long codigoAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            if (StringUtils.isBlank(erro)) {

                this.registro.registraLog(codigoAtendimento, campanha, usuario, TipoEventoEnum.CONSULTA_PAP_CARD, "retornarScriptVendaPapCard", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

            } else {

                this.registro.registraLog(codigoAtendimento, campanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "retornarScriptVendaPapCard", retornarXmlEnvio(servico), retornarXmlRetorno(servico));
            }
        }
    }

    public List<DadosCadastroBasicoTipoBeneficio> retornarTipoBeneficiosSeguro(String urlWsdl, String usrWsdl,
                                                                               String passWsdl, GenericAtendimento atendimento, Long idCampanha, Usuario usuario)
            throws ProativaException, Exception {

        ProdutoSeguroWebService service = null;

        VerificarLinkUtil.verificarLink(urlWsdl);

        try {

            TipoBeneficioParameter parametro = new TipoBeneficioParameter();

            parametro.setLogin(usrWsdl);

            parametro.setSenha(passWsdl);

            service = (new ProdutoSeguroWebServiceServiceLocator())
                    .getProdutoSeguroWebService(new URL(urlWsdl + URL_PRODUTO_SEGURO));

            ((Stub) service).setTimeout(10000);

            ListaTipoBeneficiosReturn tipoBeneficios = service.listaTipoBeneficio(parametro);

            if (tipoBeneficios != null && tipoBeneficios.getTipoBeneficios() != null) {

                if (tipoBeneficios.isExcecaoDeRegraDeNegocio() || tipoBeneficios.isExcecaoGenerica())
                    throw new ProativaException("Erro webservice Produto Seguro consig: " + tipoBeneficios.getMensagemDeErro());

                return Arrays.asList(tipoBeneficios.getTipoBeneficios());

            }

            return null;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {

            finalizarConsulta(usuario);
            throw new ProativaException("Erro Webservive Produto Seguro consig: " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException(
                        "Erro WebServices Produto Seguro do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().contains("com.bmg.econsig.consignacao.exception"))
                throw new ProativaException("Erro WebServices Produto Seguro consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT"))
                throw new ProativaException("Erro WebServices do consig : Consulta não realizada por timeout");

            if (e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT"))
                throw new ProativaException("Webservice indisponivel : " + urlWsdl);

            e.printStackTrace();

            throw new ProativaException("Erro WebServices Seguro do consig : motivo desconhecido");

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_PAP_CARD,
                    "retornarTipoBeneficiosSeguro", retornarXmlEnvio(service), retornarXmlRetorno(service));

        }

    }

    public List<FormaPagamentoProdutoSeguro> retornarFormaPagamentosSeguro(String urlWsdl, String usrWsdl,
                                                                           String passWsdl, GenericAtendimento atendimento, Integer codSeguro, Long idCampanha, Usuario usuario)
            throws ProativaException, Exception {

        ProdutoSeguroWebService service = null;

        VerificarLinkUtil.verificarLink(urlWsdl);

        try {

            FormaPagamentoSeguroParameter parametro = new FormaPagamentoSeguroParameter();

            parametro.setLogin(usrWsdl);

            parametro.setSenha(passWsdl);
            parametro.setCodigoSeguro(codSeguro);

            service = (new ProdutoSeguroWebServiceServiceLocator())
                    .getProdutoSeguroWebService(new URL(urlWsdl + URL_PRODUTO_SEGURO));

            ((Stub) service).setTimeout(10000);

            ListaFormaPagamentoSeguroReturn formaPagamento = service.listaFormaPagamentoProdutoSeguro(parametro);

            if (formaPagamento != null && formaPagamento.getFormaPagamentos() != null) {

                if (formaPagamento.isExcecaoDeRegraDeNegocio() || formaPagamento.isExcecaoGenerica())
                    throw new ProativaException(
                            "Erro webservice Produto Seguro consig: " + formaPagamento.getMensagemDeErro());

                return Arrays.asList(formaPagamento.getFormaPagamentos());

            }

            return null;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {

            finalizarConsulta(usuario);
            throw new ProativaException("Erro Webservive Produto Seguro consig: " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException(
                        "Erro WebServices Produto Seguro do consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().contains("com.bmg.econsig.consignacao.exception"))
                throw new ProativaException("Erro WebServices Produto Seguro consig : " + tratarErro(e.getMessage()));

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT"))
                throw new ProativaException("Erro WebServices do consig : Consulta não realizada por timeout");

            if (e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT"))
                throw new ProativaException("Webservice indisponivel : " + urlWsdl);

            e.printStackTrace();

            throw new ProativaException("Erro WebServices Seguro do consig : motivo desconhecido");

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_PAP_CARD,
                    "retornarFormaPagamentosSeguro", retornarXmlEnvio(service), retornarXmlRetorno(service));

        }
    }

    /*
     * GERAR PROPOSTA WS PAP CARD
     */
    public String gravarPropostaSeguro(String urlWsdl, String usrWsdl, String passWsdl, String loginConsig,
                                       String senhaConsig, String loja, GenericAtendimento atendimento, Long idCampanha, Usuario usuario,
                                       GenericTelefone telefone, GenericDadosBancarios dadosBancarios, GenericEmail email,
                                       GenericEndereco endereco, String cpfAgent, TipoFormaEnvioEnum tipoEnvio,
                                       CartaoProdutoSeguroBMG cartaoProdutoSeguroBmg, FormaPagamentoProdutoSeguro formaPagamentoSeguro,
                                       Integer codSeguro) throws ProativaException, Exception {

        ProdutoSeguroWebService servico = null;

        VerificarLinkUtil.verificarLink(urlWsdl);

        try {

            PropostaSeguroParameter parametro = criarParametroSeguro(urlWsdl, usrWsdl, passWsdl, loginConsig, senhaConsig, loja, tipoEnvio, atendimento, telefone, email, endereco, cartaoProdutoSeguroBmg, formaPagamentoSeguro, codSeguro, cpfAgent, idCampanha, usuario);

            servico = (new ProdutoSeguroWebServiceServiceLocator())
                    .getProdutoSeguroWebService(new URL(urlWsdl + URL_PRODUTO_SEGURO));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            GravaPropostaSeguroReturn proposta = servico.gravaPropostaSeguro(parametro);

            if (!proposta.isExecutadoComSucessso() || StringUtils.isNotBlank(proposta.getMensagemDeErro()))
                throw new ProativaException("Erro Web Service Consig: " + proposta.getMensagemDeErro());

            if (proposta.getNumero() != null) {

                if (!proposta.isExecutadoComSucessso() || StringUtils.isNotBlank(proposta.getMensagemDeErro()))
                    throw new ProativaException("Erro Web Service Consig: " + proposta.getMensagemDeErro());

                atendimento.setAdesao(proposta.getNumero());

                return proposta.getNumero();

            }

            throw new ProativaException("Sem Retorno WebService Produto Seguro.");

        } catch (ProativaException e) {

            finalizarConsulta(usuario);
            throw e;

        } catch (ServiceException e) {

            finalizarConsulta(usuario);

            throw new ProativaException("Erro WebServices ProdutoSeguro do consig : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            e.printStackTrace();

            if (e != null) {
                if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                    throw new ProativaException("Erro WebServices Produto Seguro do consig : " + tratarErro(e.getMessage()));

                if (e.getMessage().contains("com.bmg.econsig.consignacao.exception"))
                    throw new ProativaException("Erro WebServices Produto Seguro consig : " + tratarErro(e.getMessage()));

                if (e.getMessage().toUpperCase().contains("READ TIMED OUT"))
                    throw new ProativaException("Erro WebServices do consig : Consulta não realizada por timeout");

                if (e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO") || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT"))
                    throw new ProativaException("Webservice indisponivel : " + urlWsdl);
            }

            e.printStackTrace();

            throw new ProativaException("Erro WebServices Seguro do consig : motivo desconhecido");

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_PAP_CARD, "gravarPropostaSeguro", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

        }

    }

    private PropostaSeguroParameter criarParametroSeguro(String urlWsdl, String usrWsdl, String passWsdl,
                                                         String loginConsig, String senhaConsig, String loja, TipoFormaEnvioEnum tipoEnvio,
                                                         GenericAtendimento atendimento, GenericTelefone telefone, GenericEmail email, GenericEndereco endereco,
                                                         CartaoProdutoSeguroBMG cartaoProdutoSeguroBmg, FormaPagamentoProdutoSeguro formaPagamentoSeguro,
                                                         Integer codSeguro, String cpfAgent, Long idCampanha, Usuario usuario) throws ProativaException {

        if (atendimento == null || atendimento.getCpf() == null || StringUtils.isBlank(atendimento.getCpf()))
            throw new ProativaException("Campo Cpf é obrigatório");

        if (cartaoProdutoSeguroBmg == null || cartaoProdutoSeguroBmg.getCartaoClienteAtivoVendaSeguro() == null
                || cartaoProdutoSeguroBmg.getPlano() == null || formaPagamentoSeguro == null)
            throw new ProativaException("Cartão BMG, Plano Seguro e a Forma Pagamente são obrigatórios.");

        if (StringUtils.isBlank(loja))
            throw new ProativaException("Campo loja é Obrigatório");

        PropostaSeguroParameter parametro = new PropostaSeguroParameter();

        ClienteParameter clienteParametro = new ClienteParameter();
        setarTelefone(atendimento, telefone, clienteParametro);
        clienteParametro.setCpf(atendimento.getCpf());

        Calendar calendar = new GregorianCalendar();

        if (atendimento.getDataNascimento() != null) {

            calendar.setTime(atendimento.getDataNascimento());

            clienteParametro.setDataNascimento(calendar);
        }

        if (email != null) {
            clienteParametro.setEmail(email.getEmail());
        }

        if (endereco != null) {

            EnderecoParameter enderecoParam = new EnderecoParameter();

            enderecoParam.setBairro(endereco.getBairro());
            enderecoParam.setLogradouro(endereco.getLogradouro());
            enderecoParam.setNumero(endereco.getNumero());
            enderecoParam.setComplemento(endereco.getComplemento());
            enderecoParam.setUf(endereco.getUf());
            enderecoParam.setCep(endereco.getCep());
            enderecoParam.setCidade(endereco.getCidade());
            clienteParametro.setEndereco(enderecoParam);

        }

        clienteParametro.setEstadoCivil(atendimento.getEstadoCivil() == null ? null : atendimento.getEstadoCivil().getSigla());

        clienteParametro.setGrauInstrucao("5");

        Calendar calendarDataEmissao = null;

        if (atendimento.getDataEmissaoDocumento() != null) {

            calendarDataEmissao = Calendar.getInstance();
            calendarDataEmissao.setTime(atendimento.getDataEmissaoDocumento());
            calendarDataEmissao.set(11, 6);

        }

        clienteParametro.setIdentidade(new IdentidadeParameter(calendarDataEmissao, atendimento.getOrgaoDocumento(), atendimento.getNumeroDocumento(), "Carteira de Identidade", atendimento.getUfDocumento()));
        clienteParametro.setNome(atendimento.getNome());
        clienteParametro.setNomeConjuge(atendimento.getNomeConjuge());
        clienteParametro.setNomeMae(atendimento.getNomeMae());
        clienteParametro.setNomePai(atendimento.getNomePai());

        clienteParametro.setSexo(atendimento.getSexo() == null ? null : atendimento.getSexo().name().substring(0, 1).toUpperCase());
        clienteParametro.setNacionalidade(atendimento.getNacionalidade());

        clienteParametro.setCidadeNascimento(atendimento.getCidadeNascimento());
        clienteParametro.setUfNascimento(atendimento.getUfNascimento());
        parametro.setCliente(clienteParametro);

        parametro.setCodLoja(((StringUtils.isBlank(loja) ? null : Integer.valueOf(Integer.parseInt(loja.trim())))).intValue());

        parametro.setCpfOperadorVendas(cpfAgent);

        parametro.setLoginConsig(loginConsig);
        parametro.setSenhaConsig(senhaConsig);

        parametro.setLogin(usrWsdl);
        parametro.setSenha(passWsdl);

        parametro.setMatricula(atendimento.getBeneficioPrincipal());

        parametro.setCodigoPlano(cartaoProdutoSeguroBmg.getPlano().getCodigoPlano().intValue());
        parametro.setCodigoSeguro(codSeguro.intValue());
        parametro.setNumeroInternoConta(
                cartaoProdutoSeguroBmg.getCartaoClienteAtivoVendaSeguro().getNumeroInternoConta());
        parametro.setCodigoTipoFormaEnvio(tipoEnvio.getCodigo().intValue());
        parametro.setCodigoformaPagamentoProdutoSeguro(formaPagamentoSeguro.getCodigo().intValue());
        parametro.setCodigoTipoPagamento(COD_TIPO_PAGAMENTO_PAPCARD);

        if (atendimento.getTipoBeneficio() != null)
            parametro.setCodigoTipoBeneficio(atendimento.getTipoBeneficio().intValue());

        return parametro;

    }

    /*
     * GERAR PROPOSTA WS SAQUE COMPLEMENTAR
     */
    public String gravarPropostaSaqueComplementar(String urlWsdl, String usrWsdl, String passWsdl, String loginConsig,
                                                  String senhaConsig, String loja, String token, GenericAtendimento atendimento, Long idCampanha,
                                                  Usuario usuario, GenericTelefone telefone, GenericDadosBancarios dadosBancarios, String cpfAgent,
                                                  TipoFormaEnvioEnum formaEnvio, CartaoSaqueComplementarBmg cartaoSaqueLimite)
            throws ProativaException, Exception {

        if (dadosBancarios == null)
            throw new ProativaException("Por favor, informe os dados bancários.");

        if (atendimento == null || atendimento.getCpf() == null || StringUtils.isBlank(atendimento.getCpf()))
            throw new ProativaException("Campo Cpf é obrigatório");

        if (cartaoSaqueLimite == null || cartaoSaqueLimite.getCartao() == null
                || cartaoSaqueLimite.getCartao().getNumeroContaInterna() == null)
            throw new ProativaException("Cartão BMG é obrigatório.");

        if (StringUtils.isBlank(loja))
            throw new ProativaException("Campo loja é Obrigatório");

        if (StringUtils.isBlank(atendimento.getBeneficioPrincipal()))
            throw new ProativaException("Campo Beneficio / Matricula é Obrigatório");

        VerificarLinkUtil.verificarLink(urlWsdl);

        SaqueComplementarWebService servico = null;

        try {

            SaqueComplementarParameter param = null;

            servico = (new SaqueComplementarWebServiceServiceLocator())
                    .getSaqueComplementar(new URL(urlWsdl + URL_SAQUE_COMPLEMENTAR));

            param = parametrizarSaqueComplementar(usrWsdl, passWsdl, loja, loginConsig, senhaConsig, cpfAgent, token, atendimento, dadosBancarios, telefone, formaEnvio, cartaoSaqueLimite);

            return servico.gravarPropostaSaqueComplementar(param);

        } catch (ServiceException e) {

            finalizarConsulta(usuario);

            throw new ProativaException("Erro WebServices Saque do consig : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            e.printStackTrace();

            if (e != null) {
                if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                    throw new ProativaException("Erro WebServices Saque do consig : " + tratarErro(e.getMessage()));

                if (e.getMessage().contains("com.bmg.econsig.consignacao.exception"))
                    throw new ProativaException("Erro WebServices Saque consig : " + tratarErro(e.getMessage()));

                if (e.getMessage().toUpperCase().contains("READ TIMED OUT"))
                    throw new ProativaException("Erro WebServices do consig : Consulta não realizada por timeout");

                if (e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                        || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT"))
                    throw new ProativaException("Webservice indisponivel : " + urlWsdl);
            }

            e.printStackTrace();

            throw new ProativaException("Erro WebServices Saque complementar consig : motivo desconhecido");

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE,
                    "gravarPropostaSaqueComplementar", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

        }

    }

    public String gerarScriptSaqueComplementar(String urlWsdl, String usrWsdl, String passWsdl, String loginConsig,
                                               String senhaConsig, String loja, String token, GenericAtendimento atendimento, Long idCampanha,
                                               Usuario usuario, GenericTelefone telefone, GenericDadosBancarios dadosBancarios, String cpfAgent,
                                               TipoFormaEnvioEnum formaEnvio, CartaoSaqueComplementarBmg cartaoSaqueLimite)
            throws ProativaException, Exception {

        if (atendimento == null)
            throw new ProativaException("Por favor, o atendimento é obrigatórios.");

        if (dadosBancarios == null)
            throw new ProativaException("Por favor, informe os dados bancários.");

        if (atendimento == null || StringUtils.isBlank(atendimento.getCpf()))
            throw new ProativaException("Campo Cpf é obrigatório");

        if (cartaoSaqueLimite == null || cartaoSaqueLimite.getCartao() == null
                || cartaoSaqueLimite.getCartao().getNumeroContaInterna() == null)
            throw new ProativaException("Cartão BMG é obrigatório.");

        if (cartaoSaqueLimite.getLimite() == null || cartaoSaqueLimite.getValorSaqueAverbar() == null
                || cartaoSaqueLimite.getValorSaqueAverbar() < cartaoSaqueLimite.getLimite().getValorSaqueMinimo()
                || cartaoSaqueLimite.getValorSaqueAverbar() > cartaoSaqueLimite.getLimite().getValorSaqueMaximo())
            throw new ProativaException("Valor de saque inválido. Deve está entre o valor de saque minimo e máximo do cartão.");

        if (StringUtils.isBlank(atendimento.getEntidadePrincipal()))
            throw new ProativaException("Campo entidade é Obrigatório");

        if (StringUtils.isBlank(atendimento.getBeneficioPrincipal()))
            throw new ProativaException("Campo Beneficio / Matricula é Obrigatório");

        VerificarLinkUtil.verificarLink(urlWsdl);

        SaqueComplementarWebService servico = null;

        try {

            SaqueComplementarParameter param = null;

            servico = (new SaqueComplementarWebServiceServiceLocator()).getSaqueComplementar(new URL(urlWsdl + URL_SAQUE_COMPLEMENTAR));

            param = parametrizarSaqueComplementar(usrWsdl, passWsdl, loja, loginConsig, senhaConsig.trim(), cpfAgent, token, atendimento, dadosBancarios, telefone, formaEnvio, cartaoSaqueLimite);

            ((Stub) servico).setTimeout(10000);

            String script = servico.geraScript(param);

            if (StringUtils.isNotBlank(script)) {

                return script.replaceAll("\\|{2}", "<br/>");
            }

            return script;

        } catch (ServiceException e) {

            finalizarConsulta(usuario);
            e.printStackTrace();

            throw new ProativaException("Erro WebServices Saque do consig : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            finalizarConsulta(usuario);

            e.printStackTrace();

            if (e != null) {

                if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                    throw new ProativaException("Erro WebServices Saque do consig : " + tratarErro(e.getMessage()));

                if (e.getMessage().contains("com.bmg.econsig.consignacao.exception"))
                    throw new ProativaException("Erro WebServices Saque consig : " + tratarErro(e.getMessage()));

                if (e.getMessage().toUpperCase().contains("READ TIMED OUT"))
                    throw new ProativaException("Erro WebServices do consig : Consulta não realizada por timeout");

                if (e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO") || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT"))
                    throw new ProativaException("Webservice indisponivel : " + urlWsdl);
            }


            throw new ProativaException("Erro WebServices Saque complementar consig : motivo desconhecido");

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "gerarScriptSaqueComplementar", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

        }

    }

    /*
     * GERAR SCRIPT IDENTIFICACAO WS SAQUE COMPLEMENTAR
     */
    public String gerarScriptIdentificacaoSaqueComplementar(String urlWsdl, String usrWsdl, String passWsdl, GenericAtendimento atendimento, Long idCampanha, Usuario usuario) throws ProativaException, Exception {

        if (atendimento == null || StringUtils.isBlank(atendimento.getCpf()))
            throw new ProativaException("Informe o cpf do cliente");

        if (atendimento == null || StringUtils.isBlank(atendimento.getNome()))
            throw new ProativaException("Informe o nome do cliente");

        VerificarLinkUtil.verificarLink(urlWsdl);

        SaqueComplementarWebService servico = null;

        try {

            servico = (new SaqueComplementarWebServiceServiceLocator()).getSaqueComplementar(new URL(urlWsdl + URL_SAQUE_COMPLEMENTAR));

            ScriptIdentificacaoParameter pa = new ScriptIdentificacaoParameter();
            pa.setCpf(atendimento.getCpf());
            pa.setCodigoEntidade(Integer.valueOf(atendimento.getEntidadePrincipal()));

            if (StringUtils.isNotBlank(atendimento.getOrgaoPrincipal()) && StringUtils.isNumeric(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim())) {

                pa.setSequencialOrgao(Integer.parseInt(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim()));

            } else {

                pa.setSequencialOrgao(null);
            }

            pa.setLogin(usrWsdl);
            pa.setSenha(passWsdl);
            pa.setNomeCliente(atendimento.getNome());

            ((Stub) servico).setTimeout(10000);

            String script = servico.geraScriptIdentificacao(pa);

            if (StringUtils.isNotBlank(script)) {
                return script.replaceAll("\\|{2}", "<br/>");
            }
            return script;

        } catch (ServiceException e) {

            e.printStackTrace();

            finalizarConsulta(usuario);

            throw new ProativaException("Erro WebServices Saque do consig : " + tratarErro(e.getFaultString()));

        } catch (Exception e) {

            // finalizarConsulta(usuario);

            e.printStackTrace();

            if (e != null) {
                if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                    throw new ProativaException("Erro WebServices Saque do consig : " + tratarErro(e.getMessage()));

                if (e.getMessage().contains("com.bmg.econsig.consignacao.exception"))
                    throw new ProativaException("Erro WebServices Saque consig : " + tratarErro(e.getMessage()));

                if (e.getMessage().toUpperCase().contains("READ TIMED OUT"))
                    throw new ProativaException("Erro WebServices do consig : Consulta não realizada por timeout");

                if (e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                        || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT"))
                    throw new ProativaException("Webservice indisponivel : " + urlWsdl);
            }

            e.printStackTrace();

            throw new ProativaException("Erro WebServices Saque complementar consig : motivo desconhecido");

        } finally {

            Long idAtendimento = (atendimento == null || atendimento.getId() == null) ? null : atendimento.getId();

            this.registro.registrarLog(idAtendimento, idCampanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "gerarScriptIdentificacaoSaqueComplementar", retornarXmlEnvio(servico), retornarXmlRetorno(servico));

        }

    }

    private SaqueComplementarParameter parametrizarSaqueComplementar(String usrWsdl, String passWsdl, String loja,
                                                                     String loginConsig, String senhaConsig, String cpfAgent, String token, GenericAtendimento atendimento,
                                                                     GenericDadosBancarios dadosBancarios, GenericTelefone telefone, TipoFormaEnvioEnum tipoEnvio,
                                                                     CartaoSaqueComplementarBmg cartaoSaqueLimite) throws ProativaException {

        SaqueComplementarParameter param = null;

        param = new SaqueComplementarParameter();

        param.setCodigoEntidade(
                Integer.valueOf(Integer.parseInt(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim())));

        if (StringUtils.isNotBlank(atendimento.getOrgaoPrincipal())
                && StringUtils.isNumeric(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim())) {

            param.setSequencialOrgao(Integer.parseInt(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim()));

        } else {

            param.setSequencialOrgao(null);
        }

        parametrizarTelefone(param, atendimento, telefone);

        if (atendimento.getSituacaoServidor() != null && atendimento.getSituacaoServidor() > 0)
            param.setCodigoSituacaoServidor(atendimento.getSituacaoServidor());

        atendimento.setBeneficioPrincipal(cartaoSaqueLimite.getCartao().getMatricula());

        param.setMatricula(atendimento.getBeneficioPrincipal());

        param.setCodigoLoja((loja == null) ? null : Integer.valueOf(loja.trim()));
        param.setLogin(usrWsdl);
        param.setSenha(passWsdl);
        param.setCpf(atendimento.getCpf());
        param.setLoginConsig(loginConsig);
        param.setSenhaConsig(senhaConsig);
        param.setCpfAgente(cpfAgent);

        param.setNumeroContaInterna(cartaoSaqueLimite.getCartao().getNumeroContaInterna());
        param.setAberturaContaPagamento(Integer.valueOf(atendimento.isAbrirContaPagamento() ? 1 : 0));
        param.setTipoSaque(1);

        // Codigo do banco para OP. Informar ‘0’ (zero) caso não seja OP.
        param.setBancoOrdemPagamento(0);

        param.setCodigoFormaEnvioTermo(tipoEnvio.getCodigo().toString());

        // Opcional(7)
        if (atendimento.getFormaPagamento().getParametro() != null)
            param.setFormaCredito(atendimento.getFormaPagamento().getParametro().getCodigo());
        else
            param.setFormaCredito(1);

        param.setAgregacaoDeMargemParaSaqueComplementar(false);

        param.setSeguros(null);
        atendimento.setSeguro(null);
        parametrizarDadosBancarios(param, atendimento, dadosBancarios);

        if (telefone != null)
            param.setTelefoneFixoOuCelular(new com.proativaservicos.service.asynchronous.bmg.saquecomplementar.TelefoneParameter(telefone.getDdd().toString(), telefone.getNumero(), null));

        param.setValorSaque(cartaoSaqueLimite.getValorSaqueAverbar().doubleValue());

        param.setCodigoSituacaoServidor(atendimento.getSituacaoServidor());

        if (StringUtils.isNotBlank(token))
            param.setToken(token);

        return param;

    }

    public void parametrizarTelefone(SaqueComplementarParameter param, GenericAtendimento atendimento, GenericTelefone telefone) {

        if (telefone != null && telefone.getNumero().startsWith("9"))
            param.setCelular1(new com.proativaservicos.service.asynchronous.bmg.saquecomplementar.TelefoneParameter(telefone.getDdd().toString(), telefone.getNumero(), null));

        else if (atendimento.isAbrirContaPagamento() && CollectionUtils.isNotEmpty(atendimento.getListaTelefones())) {

            for (GenericTelefone tel : atendimento.getListaTelefones()) {

                if (tel.getStatusTelefone() != null && AcaoStatusTelefoneEnum.CONTATO_CLIENTE.equals(tel.getStatusTelefone().getParametro()) && tel.getNumero().startsWith("9")) {

                    param.setCelular1(new com.proativaservicos.service.asynchronous.bmg.saquecomplementar.TelefoneParameter(tel.getDdd().toString(), tel.getNumero(), null));
                    break;

                }

            }

        }
    }

    public void parametrizarDadosBancarios(SaqueComplementarParameter param, GenericAtendimento atendimento, GenericDadosBancarios dadosBancarios) throws ProativaException {

        if (atendimento.getFormaPagamento() == null || atendimento.getFormaPagamento().getParametro() == null)
            throw new ProativaException("Forma de pagamento é obrigatório");

        param.setBanco(new BancoParameter());
        param.setConta(new ContaParameter());
        param.setAgencia(new AgenciaParameter());

        if (Arrays.<ParamentroFormaPagamentoEnum>asList(new ParamentroFormaPagamentoEnum[]{ParamentroFormaPagamentoEnum.TED, ParamentroFormaPagamentoEnum.CHEQUE, ParamentroFormaPagamentoEnum.TED_CONTA_SALARIO}).contains(atendimento.getFormaPagamento().getParametro())) {

            if (dadosBancarios == null || dadosBancarios.getBanco() == null || dadosBancarios.getTipoConta() == null
                    || StringUtils.isBlank(dadosBancarios.getAgencia())
                    || StringUtils.isBlank(dadosBancarios.getConta())
                    || (dadosBancarios.getTipoConta().getCodigo().intValue() < 0
                    && dadosBancarios.getTipoConta().getCodigo().intValue() > 2))
                throw new ProativaException("Dados bancários é obrigatório");

            param.getBanco().setNumero(Integer.parseInt(dadosBancarios.getBanco().getNumeroBanco()));
            param.getAgencia().setNumero(dadosBancarios.getAgencia());
            param.getConta().setNumero(dadosBancarios.getConta());

            if (dadosBancarios.getTipoConta() == null) {

                param.setFinalidadeCredito(1);

            } else {
                param.setFinalidadeCredito(dadosBancarios.getTipoConta().getCodigo().intValue());
            }

            if (StringUtils.isNotBlank(dadosBancarios.getDigitoAgencia()))
                param.getAgencia().setDigitoVerificador(dadosBancarios.getDigitoAgencia());

            if (StringUtils.isNotBlank(dadosBancarios.getDigitoConta()))
                param.getConta().setDigitoVerificador(dadosBancarios.getDigitoConta());

        } else if (ParamentroFormaPagamentoEnum.ORDEM_PAGAMENTO.equals(atendimento.getFormaPagamento().getParametro())) {

            if (dadosBancarios == null || dadosBancarios.getBanco() == null || StringUtils.isEmpty(dadosBancarios.getAgencia()))
                throw new ProativaException("Para ordem de pagamento, Banco e Agência são obrigatórios");

            param.getBanco().setNumero(Integer.parseInt(dadosBancarios.getBanco().getNumeroBanco()));
            param.getAgencia().setNumero(dadosBancarios.getAgencia());
            param.setConta(null);
            param.setBancoOrdemPagamento(0);
            if (StringUtils.isNotBlank(dadosBancarios.getDigitoAgencia()))
                param.getAgencia().setDigitoVerificador(dadosBancarios.getDigitoAgencia());

        } else if (dadosBancarios != null && dadosBancarios.getBanco() != null && StringUtils.isNotBlank(dadosBancarios.getAgencia()) && StringUtils.isNotBlank(dadosBancarios.getConta()) && dadosBancarios.getTipoConta() != null) {

            param.getBanco().setNumero(Integer.parseInt(dadosBancarios.getBanco().getNumeroBanco()));
            param.getAgencia().setNumero(dadosBancarios.getAgencia());
            param.getConta().setNumero(dadosBancarios.getConta());

            param.setFinalidadeCredito(dadosBancarios.getTipoConta().getCodigo().intValue());

            if (StringUtils.isNotBlank(dadosBancarios.getDigitoAgencia()))
                param.getAgencia().setDigitoVerificador(dadosBancarios.getDigitoAgencia());

            if (StringUtils.isNotBlank(dadosBancarios.getDigitoConta()))
                param.getConta().setDigitoVerificador(dadosBancarios.getDigitoConta());

        }

    }

    public SituacaoFuncionalReturn retornarSituacaoServidor(String urlWsdl, String usrWsdl, String passWsdl,
                                                            String entidade, String orgao) {

        if (StringUtils.isBlank(entidade))
            return null;

        SaqueComplementarWebService servico = null;

        SituacaoFuncionalParameter parametros = new SituacaoFuncionalParameter();

        try {

            if (StringUtils.isNotBlank(entidade) && StringUtils.isNumeric(entidade.replaceAll("\\D+", "").trim())) {

                parametros.setCodigoEntidade(Integer.valueOf(Integer.parseInt(entidade.replaceAll("\\D+", "").trim())));

            }

            if (StringUtils.isNotBlank(orgao) && StringUtils.isNumeric(orgao.replaceAll("\\D+", "").trim())) {

                parametros.setSequencialOrgao(Integer.parseInt(orgao.replaceAll("\\D+", "").trim()));

            } else {

                parametros.setSequencialOrgao(null);
            }

            servico = (new SaqueComplementarWebServiceServiceLocator()).getSaqueComplementar(new URL(urlWsdl + URL_SAQUE_COMPLEMENTAR));
            SituacaoFuncionalReturn retorno = servico.obtemSituacoesFuncionaisParaOOrgao(parametros);

            if (retorno != null && retorno.getSituacoesServidor() != null && retorno.getSituacoesServidor().length > 0)
                return retorno;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;

    }

    private void setarTelefone(GenericAtendimento atendimento, GenericTelefone telefone,
                               ClienteParameter clienteParametro) {

        if (telefone != null) {

            clienteParametro.setTelefone(new com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter(telefone.getDdd().toString(), telefone.getNumero(), null));

        }

    }

    private String retornarXmlEnvio(Object servico) throws AxisFault {
        try {

            if (servico != null)
                return U.formatXml(((Stub) servico)._getCall().getMessageContext().getRequestMessage().getSOAPPartAsString());

        } catch (NullPointerException e) {
            return "";
        }
        return "";
    }

    private String retornarXmlRetorno(Object servico) throws AxisFault {

        try {


            if (servico != null)
                return U.formatXml(((Stub) servico)._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString());

        } catch (NullPointerException e) {
            return "";
        }
        return "";
    }


    public static void main(String[] args) {


        WsdlBmgUtil util = new WsdlBmgUtil();

        Atendimento ate = new Atendimento();
        ate.setCpf("16018007622");
        ate.setEntidadePrincipal("1581");
        //ate.setOrgaoPrincipal("0");

        try {

            ClienteProdutoSeguroBMG list = util.retornarCartoesDisponiveisSeguroBmg("https://ws1.bmgconsig.com.br/webservices", "robo.52586", "Vendas2025@", ate, null, null, 20,false);

            System.out.println("mensaem: "+ list.getMensagemDeErro());

            for (CartaoProdutoSeguroBMG cartaoProdutoSeguro : list.getListaCartaoPlanos()) {

                System.out.print("BUSCANDO:: "+cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getNumeroCartao()+" | ");
                System.out.print(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getEhElegivel()+" | ");
                System.out.print(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getNumeroInternoConta()+" | ");
                System.out.print(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getCodigoEntidade()+" | ");
                System.out.print(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getSequencialOrgao()+" | ");
                System.out.print(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getLimiteCartao()+" | ");

                System.out.println(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getOrgaoFormatado());

                System.out.println(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getMotivoElegibilidade());
                System.out.println(cartaoProdutoSeguro.getListaPlanosSeguroReturn().getMensagemDeErro()==null?"":cartaoProdutoSeguro.getListaPlanosSeguroReturn().getMensagemDeErro());

                if (cartaoProdutoSeguro != null && cartaoProdutoSeguro.getListaPlanosSeguroReturn() != null && cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos() != null && StringUtils.isBlank(cartaoProdutoSeguro.getListaPlanosSeguroReturn().getMensagemDeErro()) && StringUtils.isBlank(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getMotivoElegibilidade())) {

                    System.out.println(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getNumeroInternoConta());
                    System.out.println(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getCodigoEntidade());
                    System.out.println(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getSequencialOrgao());
                    System.out.println(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getLimiteCartao());
                    System.out.println(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getLimiteCartao());


                    Double seguro = cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()[cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos().length - 1].getValorPremio().doubleValue();
                    System.out.println(seguro);

                    Arrays.stream(cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()).forEach(planosContratacaoSeguroStandAlone -> {
                        System.out.println(planosContratacaoSeguroStandAlone.getRating()+" - PREMIO: "+planosContratacaoSeguroStandAlone.getValorPremio()+" - "+planosContratacaoSeguroStandAlone.getValorCapitalSegurado());

                    });
                }
                System.out.println(" -------------------------------------------");
            }

        } catch (ProativaException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
