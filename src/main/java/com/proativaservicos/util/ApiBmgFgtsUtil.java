package com.proativaservicos.util;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


@Named
public class ApiBmgFgtsUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private RegistroSistemaUtil registro;

    public String retornarSimulacaoFgts(String urlApi, String loginConsig, String senhaConsig, String clientId,
                                        String clientSecret, String appToken, GenericAtendimento atendimento, Long idCampanha, Usuario usuario,
                                        boolean gravarLog, boolean verificarLink) throws ProativaException {

//		VerificarLinkUtil.verificarLink(urlApi);

        Map<String, String> parametros = new HashMap<>();
        String logErro = null;

        parametros.put("client_id", clientId);
        parametros.put("client_secret", clientSecret);
        parametros.put("app-token", appToken);

        JSONObject json = new JSONObject();

        json.put("login", loginConsig);
        json.put("senha", senhaConsig);
        json.put("cpfCliente", atendimento.getCpf());

        json.put("dataNascimento", "1973-07-18T06:00:00");

        if (atendimento.getDataNascimento() != null) {
            String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(atendimento.getDataNascimento()) + "T06:00:00";
            json.put("dataNascimento", dataFormatada);

        }

        json.put("entidade", "4262");
        json.put("loginConsig", "");
        json.put("loja", "39540");
        json.put("produto", "9665");
        json.put("qtdParcelas", "");
        json.put("senhaConsig", "");
        json.put("sequencialOrgao", "");
        json.put("servico", "135");
        json.put("valorSolicitado", "");

        try {

            Map<String, String> retornoMap = HttpPostUtil.enviarPostOkttp(urlApi, parametros, json.toString(), true);

            if (retornoMap == null || retornoMap.isEmpty())
                throw new ProativaException("Nehum retorno.");

            String retornoJson = retornarJsonFgts(retornoMap.get("boby"));

            return retornoJson;

        } catch (ProativaException e) {

            if (gravarLog) {
                finalizarConsulta(usuario);

                logErro = e.getMessage();

                throw e;

            } else {
                //return  tratarErro(e.getMessage());
            }
            throw e;

        } catch (Exception e) {

            e.printStackTrace();

            e.printStackTrace();
            logErro = (e.getMessage() == null) ? "Erro..." : e.getMessage();

            if (gravarLog)
                finalizarConsulta(usuario);

            throw new ProativaException("Houve erro na API BMG: " + logErro);
        }


    }


    public String retornarJsonFgts(String json) throws ProativaException {

        try {

            if (Utils.isJSON(json)) {

                JSONObject jsonOb = new JSONObject(json);

                if (jsonOb.has("simularSaqueAniversarioFgtsResponse"))
                    jsonOb = new JSONObject(jsonOb.get("simularSaqueAniversarioFgtsResponse").toString());

                if (jsonOb.has("error")) {

                    jsonOb = new JSONObject(jsonOb.get("error").toString());

                    throw new ProativaException(jsonOb.has("message") ? jsonOb.getString("message").toString()
                            : "Erro ao realizar consulta");

                } else {

                    if (Utils.isJSON(jsonOb.toString()) && jsonOb.has("simularSaqueAniversarioFgtsReturn")) {

                        jsonOb = new JSONObject(jsonOb.get("simularSaqueAniversarioFgtsReturn").toString());
                        return jsonOb.toString();

                    }

                }

            }

        } catch (ProativaException e) {

            throw e;

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("erro desconhecido.");
        }

        return null;
    }


    public void finalizarConsulta(Usuario usuario) {

        this.registro.registrarLog(usuario.getId(), TipoEventoEnum.CONSULTA_CARTAO_BENEFICIO,
                "finalizou consulta cartão beneficio", usuario.getIp());

    }

    private String tratarErro(String erro) {

        return (erro == null) ? null
                : erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "")
                .replaceAll("com.bmg.consig.common.exception.ImpedidoOperarException:", "")
                .replaceAll("java.sql.SQLException:", "").replaceAll("jakarta.resource.ResourceException:", "")
                .replaceAll("com.bmg.econsig.consignacao.exception.PropostaConsignacaoListException:", "")
                .trim();
    }

    public static void main(String[] args) throws IOException {

        // Não foi possível realizar a consulta do saldo do FGTS. Tente novamente.

        String arquivoCSV = "C:\\Users\\rodrigo\\Documents\\BMG_FGTS.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        String cpf = "";


        //br = new BufferedReader(new FileReader(arquivoCSV));


            try {

                cpf = "";
                //String[] col = linha.split(csvDivisor);

                System.err.println(linha);

                ApiBmgFgtsUtil ws = new ApiBmgFgtsUtil();

                Atendimento atendimento = new Atendimento();

                atendimento.setCpf(StringUtils.leftPad("18170196", 11, "0"));

                atendimento.setDataNascimento(DateUtil.builder("31/10/1983").formatarStringParaData("dd/MM/yyyyy").getData());

                System.out.println(atendimento.getDataNascimento());

                System.out.println("Cliente [CPF= " + atendimento.getCpf() + " , Data nascimento=" + atendimento.getDataNascimento() + "]");
                cpf = atendimento.getCpf();
                String retorno = ws.retornarSimulacaoFgts("https://api-bmg.bancobmg.com.br/consig/v1/simularsaqueaniversariofgts", "pamelagra",
                        "416a253@", "afcfa5f2-a0bb-43f0-a149-56854e581dcd", "77b398e0-f8f7-4ad8-a537-85ade8b6fcd6",
                        "afcfa5f2-a0bb-43f0-a149-56854e581dcd", atendimento, 1L, new Usuario(12L, ""), false, false);

                 System.out.println("RETORNO METDO: "+retorno);





                if (Utils.isJSON(retorno)) {

                    JSONObject jsonObject = new JSONObject(retorno);
                    System.out.println("valorOriginal: " + jsonObject.get("valorOriginal").toString());
                    System.out.println("valorLiberado: " + jsonObject.get("valorLiberado").toString());
                    System.out.println("valorProporcional: " + jsonObject.get("valorProporcional").toString());

                    System.out.println("\nParcelas: " + jsonObject.get("parcelas").toString());
                    System.out.println("\nseguro: " + jsonObject.get("seguro").toString());
                    String msg = atendimento.getCpf() + ";" + jsonObject.get("mensagem").toString() + ";" + jsonObject.get("valorOriginal").toString() + ";" + jsonObject.get("valorLiberado").toString() + ";" + jsonObject.get("valorProporcional").toString() + ";" + jsonObject.get("parcelas").toString();
                    ArquivoUtil.geraLogCsv(new File("C:\\Users\\rodrigo\\Documents\\retorno_fgts_2.csv"), msg);

                } else {

                 //   ArquivoUtil.geraLogCsv(new File("C:\\Users\\rodrigo\\Documents\\retorno_fgts_bmg_2.csv"), atendimento.getCpf() + ";" + (StringUtils.isBlank(retorno) ? "Nenhum retorno" : retorno));
                }

            } catch (Exception e) {

               // ArquivoUtil.geraLogCsv(new File("C:\\Users\\rodrigo\\Documents\\retorno_fgts_bmg_2.csv"), cpf + ";" + e.getMessage());
                e.printStackTrace();
            }


    }

}
