import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.rpc.ServiceException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proativaservicos.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.proativaservicos.model.CartaoSaqueComplementarBmg;
import com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoBeneficioWebService;
import com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoBeneficioWebServiceServiceLocator;
import com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoParameter;
import com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoRetorno;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.CartaoDisponivelParameter;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.CartaoDisponivelRetorno;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.CartaoRetorno;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.DadosCartaoParameter;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.LimiteSaqueRetorno;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.SaqueComplementarWebService;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.SaqueComplementarWebServiceServiceLocator;

public class Teste {

	public static String URL_SAQUE_COMPLEMENTAR = "/SaqueComplementar?wsdl";

	public static void main(String[] args) {
		/*System.out.println(Teste.deAccent("maçaão"));

		System.out.println(DateUtil.builder(new Date()).formatarDataParaString("yyyyMMddHHmmss").getDataTexto());
		try {
			consultarCartaoBeneficio();
		} catch (MalformedURLException | RemoteException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String Urlsdl = "https://ws1.bmgconsig.com.br/webservices";
		String usrWsdl = "pamelagra";
		String senhaWsdl = "416a253@";
		String cpf = "18141820982";
		String orgao = "";
		String entidade = "1581";

		CartaoDisponivelParameter cartaoParamiter = null;
		CartaoDisponivelRetorno cartaoRetorno = null;
		SaqueComplementarWebService servico = null;

		String logErro = "";
		String logErroCartao = "";
		System.out.println("Verificando link: " + Urlsdl);

		try {

			VerificarLinkUtil.verificarLink(Urlsdl);

			cartaoParamiter = new CartaoDisponivelParameter();
			cartaoParamiter.setLogin(usrWsdl);
			cartaoParamiter.setSenha(senhaWsdl);

			cartaoParamiter.setCpf(cpf);

			cartaoParamiter.setCodigoEntidade(Integer.parseInt(entidade));

			cartaoParamiter.setSequencialOrgao((StringUtils.isNoneBlank(orgao) ? Integer.valueOf(orgao) : null));

			servico = (new SaqueComplementarWebServiceServiceLocator()).getSaqueComplementar(
					new URL("https://ws1.bmgconsig.com.br/webservices/" + URL_SAQUE_COMPLEMENTAR));

			List<CartaoSaqueComplementarBmg> listCartoesBmg = new ArrayList<CartaoSaqueComplementarBmg>();

			CartaoDisponivelRetorno retorno = servico.buscarCartoesDisponiveis(cartaoParamiter);

			CartaoRetorno cartao[] = retorno.getCartoesRetorno();

			for (CartaoRetorno cartaoRetorno2 : cartao) {

				System.out.println("Cartão: " + cartaoRetorno2.getNumeroCartao());
				DadosCartaoParameter parametro = new DadosCartaoParameter();
				parametro.setCpf(cpf);
				parametro.setCodigoEntidade(Integer.valueOf(entidade));
				parametro.setSequencialOrgao(null);
				parametro.setLogin(usrWsdl);
				parametro.setSenha(senhaWsdl);
				parametro.setMatricula(cartaoRetorno2.getMatricula());
				parametro.setNumeroContaInterna(cartaoRetorno2.getNumeroContaInterna());
				parametro.setTipoSaque(1);

				servico = (new SaqueComplementarWebServiceServiceLocator())
						.getSaqueComplementar(new URL(Urlsdl + URL_SAQUE_COMPLEMENTAR));

				LimiteSaqueRetorno limiteSaqueRetorno = servico.buscarLimiteSaque(parametro);

				System.out.println("Limite Cartão: " + limiteSaqueRetorno.getLimiteCartao() + "\nValor saque minimo: "
						+ limiteSaqueRetorno.getValorSaqueMinimo() + " \nValor saque maximo: "
						+ limiteSaqueRetorno.getValorSaqueMaximo() + " \nMsg "
						+ limiteSaqueRetorno.getMensagemDeErro());
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
*/

		SecureRandom random = new SecureRandom();

// Gera número entre 0 e 99
		int numero = random.nextInt(100);

// Formata com dois dígitos (zero à esquerda se necessário)
		String numeroFormatado = String.format("%02d", numero);

		System.out.println("Número gerado: " + Utils.getNumeroRandomico());

		// Data com milissegundos para maior precisão
		String formatoData = DateUtil.builder(new Date())
				.formatarDataParaString("yyyyMMddHHmmss")
				.getDataTexto();

		// Gera um UUID e pega apenas os 8 primeiros caracteres
		String uuidCurto = UUID.randomUUID().toString().substring(0, 2).toUpperCase();

		// Dois dígitos aleatórios para reforçar
		String numeroRandomico = String.format("%02d", new SecureRandom().nextInt(100));

		System.out.println(formatoData + "-" + uuidCurto + "-" + numeroRandomico + "-");


		try {

			Map<String, Object> dados = new HashMap<>();
			dados.put("ramal", "9020");
			dados.put("destino", "31999631311");

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(dados);

			System.out.println(json);
			HttpPostUtil.enviarPostUrl("http://localhost:8180/profone/agent/logar", null, json, false);

		}catch (Exception e){

		}

	}

	private static void consultarCartaoBeneficio() throws MalformedURLException, ServiceException,
			com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException, RemoteException {

		System.out.println("Iniciando consulta....");
		ValidaSeJaPossuiContaCartaoParameter param = new ValidaSeJaPossuiContaCartaoParameter();
		param.setLogin("claudinei.50178S");
		param.setSenha("31eadc7$");
		param.setCodigoEntidade(4277);
		param.setSequencialOrgao(null);
		param.setMatricula("1116506944");

		param.setCpf("1513605");
		CartaoBeneficioWebService service = (new CartaoBeneficioWebServiceServiceLocator())
				.getCartaoBeneficio(new URL("https://ws1.bmgconsig.com.br/webservices/CartaoBeneficio?wsdl"));
		System.out.println("CONSULTANDO>>>");
		ValidaSeJaPossuiContaCartaoRetorno retorno = service.validaSeJaPossuiContaCartao(param);

		System.out.println("NUMERO CARTAO: " + retorno.getNumeroCartao());
		System.out.println("MSG ERRO: " + retorno.getMensagemDeErro());
		System.out.println("NUMERO INTERNO CONTA: " + retorno.getNumeroInternoConta());
		System.out.println("PERMITE ASSOCIAR CONTA: " + retorno.isPermiteAssociarContaPagamentoCartao());
		System.out.println("EXCECAO GENERICA: " + retorno.isExcecaoGenerica());
		System.out.println("EXCECAO REGRA NEGOCIO: " + retorno.isExcecaoDeRegraDeNegocio());

	}

	public static String deAccent(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
}
