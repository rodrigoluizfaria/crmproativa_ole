import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.CartaoSaqueComplementarBmg;
import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.*;
import com.proativaservicos.util.VerificarLinkUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TesteConsulta {

    public static String URL_SAQUE_COMPLEMENTAR = "/SaqueComplementar?wsdl";


    public List<CartaoSaqueComplementarBmg> retornarCartoesDisponiveisBmg(String Urlsdl, String usrWsdl, String senhaWsdl, GenericAtendimento atendimento, Long idCampanha, Usuario usuario, boolean validarPropostaGravada, boolean validarSeguro, boolean gravarLog, Integer indiceCartao) throws Exception {

        CartaoDisponivelParameter cartaoParamiter = null;
        CartaoDisponivelRetorno cartaoRetorno = null;
        SaqueComplementarWebService servico = null;

        String logErro = "";
        String logErroCartao = "";
        System.out.println("Verificando link: " + Urlsdl + URL_SAQUE_COMPLEMENTAR);
        //  VerificarLinkUtil.verificarLink(Urlsdl);

        try {

            cartaoParamiter = new CartaoDisponivelParameter();
            cartaoParamiter.setLogin(usrWsdl);
            cartaoParamiter.setSenha(senhaWsdl);
            cartaoParamiter.setCpf(atendimento.getCpf());
            cartaoParamiter.setCodigoEntidade(Integer.parseInt(atendimento.getEntidadePrincipal()));
            cartaoParamiter.setSequencialOrgao((StringUtils.isNoneBlank(atendimento.getOrgaoPrincipal()) ? Integer.valueOf(atendimento.getOrgaoPrincipal()): null));

            servico = (new SaqueComplementarWebServiceServiceLocator()).getSaqueComplementar(new URL(Urlsdl + URL_SAQUE_COMPLEMENTAR));

            cartaoRetorno = servico.buscarCartoesDisponiveis(cartaoParamiter);

            List<CartaoSaqueComplementarBmg> listCartoesBmg = new ArrayList<CartaoSaqueComplementarBmg>();

            if (cartaoRetorno != null && cartaoRetorno.getCartoesRetorno() != null
                    && (cartaoRetorno.getCartoesRetorno().length > 0)) {

                if (indiceCartao == null) {

                    indiceCartao = Integer.valueOf(0);
                }

                String erroCartaoLiberado = null;

                for (CartaoRetorno retorno : cartaoRetorno.getCartoesRetorno()) {

                    if (retorno != null && retorno.isLiberado()) {

                        LimiteSaqueRetorno limite = retornarLimiteSaqueRetorno(Urlsdl, usrWsdl, senhaWsdl, atendimento, idCampanha, usuario, retorno, true, false);

                        //	LimiteSaqueRetorno limite = null;
                        System.out.println(retorno.getNumeroCartao() + "  " + limite.getValorSaqueMaximo());

                        if (limite != null) {

                            Integer index1 = indiceCartao;

                            listCartoesBmg.add(new CartaoSaqueComplementarBmg(index1, retorno, limite));

                        } else if (retorno != null && !retorno.isLiberado()) {
                            // ERRO IMPEDIDO....
                            erroCartaoLiberado = "Ocorreu um erro na Webservice BMG: " + retorno.getMensagemImpedimento();
                        }

                    }

                }

                if (erroCartaoLiberado != null && (listCartoesBmg == null || listCartoesBmg.isEmpty())) {

                    throw new ProativaException(erroCartaoLiberado);
                }
            }

            return listCartoesBmg;

        } catch (ProativaException e) {


            logErro = e.getMessage() + " - " + logErroCartao;
            e.printStackTrace();

            throw e;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {
            // TODO Auto-generated catch block

            logErro = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();

            throw new ProativaException(
                    "Houve erro na Webservice BMG: " + e.getFaultString());

        } catch (Exception e) {

            e.printStackTrace();


            logErro = e.getMessage();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))
                throw new ProativaException(
                        "Erro WebServices SaqueComplementar do consig : " + e.getMessage());

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")
                    || e.getMessage().toUpperCase().contains("CONNECTION REFUSED")) {

                throw new ProativaException("Servidor inoperante : " + usrWsdl);
            }
            e.printStackTrace();

            throw new Exception("Ocorreu um erro inesperado no web services saque complementar do consig - "
                    + e.getMessage());

        } finally {

        }


    }

    private LimiteSaqueRetorno retornarLimiteSaqueRetorno(String Urlsdl, String usrWsdl, String senhaWsdl,
                                                          GenericAtendimento atendimento, Long idCampanha, Usuario usuario, CartaoRetorno cartao,
                                                          boolean consultarSeguro, boolean registrarLog) throws ProativaException, Exception {

        DadosCartaoParameter parametro = null;
        LimiteSaqueRetorno limiteSaqueRetorno = null;
        VerificarLinkUtil.verificarLink(Urlsdl);
        String errorMsg = null;

        try {

            parametro = new DadosCartaoParameter();
            parametro.setCpf(atendimento.getCpf());
            parametro.setLogin(usrWsdl);
            parametro.setSenha(senhaWsdl);
            parametro.setMatricula(cartao.getMatricula());
            parametro.setNumeroContaInterna(cartao.getNumeroContaInterna());
            parametro.setTipoSaque(1);

            parametro.setCodigoEntidade(Integer.valueOf(atendimento.getEntidadePrincipal()));
            parametro.setSequencialOrgao((StringUtils.isNoneBlank(atendimento.getOrgaoPrincipal())
                    ? Integer.valueOf(atendimento.getOrgaoPrincipal())
                    : null));

            SaqueComplementarWebService servico;

            servico = (new SaqueComplementarWebServiceServiceLocator())
                    .getSaqueComplementar(new URL(Urlsdl + URL_SAQUE_COMPLEMENTAR));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);

            if (consultarSeguro) {

                ObtemProdutosDeSeguroParameter paramSeguro = new ObtemProdutosDeSeguroParameter();

                paramSeguro.setLogin(usrWsdl);
                paramSeguro.setSenha(senhaWsdl);
                paramSeguro.setCpf(atendimento.getCpf());

                if (atendimento.getDataNascimento() != null) {

                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(atendimento.getDataNascimento());
                    calendar.set(Calendar.DECEMBER, 6);
                    paramSeguro.setDataDeNacimento(calendar);
                }

                paramSeguro.setCodigoEntidade(
                        Integer.parseInt(atendimento.getEntidadePrincipal().replaceAll("\\D+", "").trim()));

                parametro.setSequencialOrgao((StringUtils.isNoneBlank(atendimento.getOrgaoPrincipal())
                        ? Integer.valueOf(atendimento.getOrgaoPrincipal().replaceAll("\\D+", "").trim())
                        : null));

                ObtemProdutosDeSeguroRetorno retornoSeguro = servico.obtemProdutosDeSeguro(paramSeguro);

                if (retornoSeguro != null && retornoSeguro.getTiposDeSeguro() != null
                        && retornoSeguro.getTiposDeSeguro().length > 0) {

                    //parametro.setCodigoTipoSeguro(Integer.valueOf(1));

                }

            }

            limiteSaqueRetorno = servico.buscarLimiteSaque(parametro);

            return limiteSaqueRetorno;

        } catch (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException e) {
            // TODO Auto-generated catch block


            errorMsg = (e.getMessage() == null) ? e.getFaultString() : e.getMessage();
            throw new ProativaException("Erro webservice BMG : " + e.getFaultString());

        } catch (Exception e) {
            // TODO Auto-generated catch block

            errorMsg = e.getMessage();

            if (e.getMessage().contains("com.bmg.econsig.common.exception.ServiceException"))

                throw new ProativaException("Erro WebServices SaqueComplementar BMG : " + e.getMessage());

            if (e.getMessage().toUpperCase().contains("READ TIMED OUT")
                    || e.getMessage().toUpperCase().contains("TEMPO ESGOTADO PARA CONEXÃO")
                    || e.getMessage().toUpperCase().contains("CONNECTION TIMED OUT")) {

                throw new ProativaException("Webservice indisponivel - Link : " + Urlsdl);

            }

            e.printStackTrace();

            throw new Exception("Ocorreu um erro inesperado no web services saque complementar do consig - " + e.getMessage());

        } finally {


        }

    }


    public static void main(String[] args) {

        Atendimento atendimento = new Atendimento();
        atendimento.setCpf("56783078191");
       // atendimento.setEntidadePrincipal("3764");

        TesteConsulta teste = new TesteConsulta();

        try {

            List<CartaoSaqueComplementarBmg> list = teste.retornarCartoesDisponiveisBmg("https://ws1.bmgconsig.com.br/webservices", "pamelagra", "416a253@", atendimento, null, null, false, false, false, null);

            for (CartaoSaqueComplementarBmg cartaoSaqueComplementarBmg : list) {

                System.out.println(cartaoSaqueComplementarBmg.getLimite().isElegivelSeguros());
            }


        } catch (ProativaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
