package com.proativaservicos.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lowagie.text.pdf.codec.Base64;
import com.proativaservicos.dao.integra.VonixClient;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.api.MongoDB;
import com.proativaservicos.util.HttpPostUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.VerificarLinkUtil;
import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.transaction.UserTransaction;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class VonixService {

    private static String URL_CONTACS = "/contacts/";

    @Inject
    private LogImportacaoDiscadorService serviceLogDiscador;


    private MongoDB mongo;

    private VonixClient vonixClient;


    @Resource
    private UserTransaction transaction;


    @Asynchronous
    public void alimentarDiscadorVonix(IntegracaoWs integracao, Campanha campanha, List<Atendimento> listAtendimentos) {

        List<LogImportacaoDiscador> listLogImportacao = null;
        System.out.println("iniciando importação por lote vonix [Asynchronous]");

        String objId = null;
        JsonArray listJSONError = null;
        String erro = "";

        try {

            verificarSeServicoEstaDisponivel(integracao);

            String url = integracao.getUrl() + URL_CONTACS + campanha.getFila().getNome();

            Map<String, String> mapParametros = new HashedMap<String, String>();

            String usuarioEsenha = integracao.getUsr() + ":" + integracao.getPsw();

            String encoding = Base64.encodeBytes(usuarioEsenha.getBytes());

            mapParametros.put("Authorization", "Basic " + encoding);

            List<Atendimento> listAtendimentoUnicos = (List<Atendimento>) listAtendimentos.stream().filter(Utils.distinctByKey(a -> a.getId())).collect(Collectors.toList());

            // FOR DE MIL EM MIL
            listLogImportacao = new ArrayList<LogImportacaoDiscador>();

            int totaldeLotes = listAtendimentoUnicos.size() / 1000 + ((listAtendimentoUnicos.size() % 1000 > 0) ? 1 : 0);

            objId = escrevendoLog(campanha.getId(), campanha.getDescricao(), null, totaldeLotes, 0, 0, listAtendimentoUnicos.size(), "IMPORTANDO");

            String observacao = "";

            Long quantidadeEnviado = 0L;
            Long quantidadeNaoEnviado = 0L;

            listJSONError = new JsonArray();

            for (int i = 0; i < listAtendimentoUnicos.size() / 1000 + ((listAtendimentoUnicos.size() % 1000 > 0) ? 1 : 0); i++) {

                JsonArray jsonArrayAtendimentos = new JsonArray();


                LogImportacaoDiscador logDiscador = new LogImportacaoDiscador();

                logDiscador.setTipoIntegracao(TipoIntegracaoEnum.VONIX);

                logDiscador.setRetrabalhar((campanha.getRetrabalharCampanha() != null) ? campanha.getRetrabalharCampanha() : Boolean.FALSE);

                atualizandoLog(objId, i + 1, ((i + 1) != totaldeLotes) ? 1000 : (listAtendimentoUnicos.size() % 1000), "IMPORTANDO", observacao, quantidadeEnviado, quantidadeNaoEnviado, listJSONError);

                // SUBLIST 1000 ATENDIMENTOS...
                for (Atendimento atendimento : listAtendimentoUnicos.subList(i * 1000, Math.min(listAtendimentoUnicos.size(), (i + 1) * 1000))) {

                    if (CollectionUtils.isNotEmpty(atendimento.getListaTelefones())) {

                        JsonObject jsonAtendimento = new JsonObject();

                        jsonAtendimento.addProperty("id", atendimento.getId());

                        jsonAtendimento.addProperty("contact_name", StringUtils.isNoneBlank(atendimento.getNome()) ? atendimento.getNome() : "Contato não Informado");

                        // VALIDAR POSSIBILIDADE DE PRIORIDADE
                        jsonAtendimento.addProperty("billing_group_id", campanha == null ? "1" : String.valueOf(campanha.getId()));

                        if (atendimento.getPrioridadeFila() != null)
                            jsonAtendimento.addProperty("priority", String.valueOf(atendimento.getPrioridadeFila()));

                        JsonArray jasonArrayTelefone = new JsonArray();

                        for (Telefone telefone : atendimento.getListTelefones()) {

                            JsonObject jsonTelefone = new JsonObject();
                            jsonTelefone.addProperty("id", telefone.getId());
                            jsonTelefone.addProperty("number", telefone.getDdd().toString() + telefone.getNumero());
                            jasonArrayTelefone.add((JsonElement) jsonTelefone);

                        }

                        jsonAtendimento.add("to", (JsonElement) jasonArrayTelefone);
                        jsonArrayAtendimentos.add((JsonElement) jsonAtendimento);

                    }


                }

                logDiscador.setDataEnvio(new Date(System.currentTimeMillis()));

                /// ENVIAR PARA VONIX
                //Map<String, String> retornoMap = HttpPostUtil.enviarPostUrl(url, mapParametros,	jsonArrayAtendimentos.toString(), url.startsWith("https"));
                Map<String, String> retornoMap = enviarLote(url, mapParametros, jsonArrayAtendimentos, objId, true);

                if (retornoMap != null && !retornoMap.isEmpty()) {

                    logDiscador.setHttpStatus(retornoMap.getOrDefault("status", ""));

                    if (retornoMap.containsKey("retorno")) {

                        try {

                            this.transaction.begin();

                            logDiscador.setDadoRetorno(retornoMap.getOrDefault("retorno", "nenhum dado retornado"));

                            JSONObject jsonVonix = null;

                            if (Utils.isJSON(retornoMap.get("retorno"))) {

                                jsonVonix = new JSONObject(retornoMap.get("retorno"));

                            } else {

                                listJSONError.addAll(jsonArrayAtendimentos);

                                observacao = retornoMap.get("retorno");

                                atualizandoLog(objId, i, (i != totaldeLotes) ? 1000 : (listAtendimentoUnicos.size() % 1000), "IMPORTANDO", observacao, quantidadeEnviado, quantidadeNaoEnviado, listJSONError);

                                System.out.println("ATENDIMENTOS NÃO ENVIADOS: " + jsonArrayAtendimentos.toString());
                            }

                            System.out.println("RETORNO VONIX: [ " + logDiscador.getDadoRetorno() + " ] | CAMPANHA: [ " + campanha.getDescricao() + " ]");

                            if (jsonVonix != null) {

                                logDiscador.setQuantidadeNaoEnviado(jsonVonix.isNull("not_imported_count") ? null : Long.valueOf(jsonVonix.getInt("not_imported_count")));

                                logDiscador.setQuantidadeEnviado(jsonVonix.isNull("imported_count") ? null : Long.valueOf(jsonVonix.getInt("imported_count")));

                                quantidadeEnviado += logDiscador.getQuantidadeEnviado() == null ? 0L : logDiscador.getQuantidadeEnviado();

                                quantidadeNaoEnviado += logDiscador.getQuantidadeNaoEnviado() == null ? 0L : logDiscador.getQuantidadeNaoEnviado();
                            }

                            logDiscador.setQuantidadeEnviado(Long.valueOf(jsonArrayAtendimentos.size()));

                            logDiscador.setImportacao(listAtendimentoUnicos.get(0).getImportacao());

                            this.serviceLogDiscador.inserir(logDiscador);

                            this.transaction.commit();

                            listLogImportacao.add(logDiscador);


                        } catch (Exception e) {

                            e.printStackTrace();


                        }
                    }

                } else {
                    System.out.println("SEM RETORNO DISCADOR....");
                }

            }

            System.out.println("Fim importação por lote vonix [Asynchronous] - CAMPANHA: " + campanha.getDescricao());
            atualizandoLog(objId, 0, totaldeLotes, "IMPORTADA", "", quantidadeEnviado, quantidadeNaoEnviado, listJSONError);


        } catch (ProativaException ex) {

            ex.printStackTrace();
            erro = ex.getMessage();

        } catch (Exception e) {
            erro = e.getMessage();
            e.printStackTrace();

        }

        if (StringUtils.isNotBlank(erro)) {

            atualizandoLog(objId, 0, null, "IMPORTADA", "", null, null, listJSONError);
        }

    }

    private Map<String, String> enviarLote(String url, Map<String, String> mapParametros, JsonArray jsonArrayAtendimentos, String objId, boolean reenviar) throws ProativaException, Exception {

        Map<String, String> retornoMap = null;
        try {

            retornoMap = HttpPostUtil.enviarPostUrl(url, mapParametros, jsonArrayAtendimentos.toString(), url.startsWith("https"));

            System.out.println("ENVIANDO LOTE | Qtdade:   " + jsonArrayAtendimentos.size());

            if (!reenviar)
                return retornoMap;

            if (retornoMap != null && !retornoMap.isEmpty()) {

                String statusHttp = retornoMap.containsKey("status") ? retornoMap.get("status") : "";

                if ((!retornoMap.containsKey("retorno") || !Utils.isJSON(retornoMap.get("retorno")) || statusHttp.equals("500")) && reenviar) {

                    System.out.println("RETORNO ERRO: " + (retornoMap.containsKey("retorno") ? retornoMap.get("retorno") : ""));
                    System.out.println("REENVIANDO LOTE ....");
                    atualizandoLogErro(objId, "REENVIANDO ATENDIMENTO", jsonArrayAtendimentos);
                    enviarLote(url, mapParametros, jsonArrayAtendimentos, objId, false);

                }

            }

        } catch (Exception e) {

            if (!(e instanceof ProativaException) && reenviar) {

                enviarLote(url, mapParametros, jsonArrayAtendimentos, objId, false);

            } else {

                throw e;
            }

        }
        return retornoMap;

    }

    private String escrevendoLog(Long idCampanha, String campanha, String arquivo, Integer totalLote, Integer loteAtual, Integer totalEnviarLote, Integer totalAtendimento, String statusImportecao) {

        Document doc = new Document();
        doc.append("campanha", campanha);
        doc.append("id_campanha", idCampanha);
        doc.append("arquivo", arquivo);
        doc.append("quantidade_total_lote", totalLote);
        doc.append("lote_atual", loteAtual);
        doc.append("total_atendimento_lote_atual", totalEnviarLote);
        doc.append("total_atendimento", totalAtendimento);
        doc.append("status_importacao", statusImportecao);
        doc.append("erro_envio_lote", null);
        doc.append("atendimentos_erro", null);
        doc.append("observacao", null);
        doc.append("enviado", null);
        doc.append("nao_enviado", null);
        doc.append("date", new Date());

        this.mongo = new MongoDB("importacao", "importacao_log");

        return this.mongo.inserirDoc(doc);


    }

    private void atualizandoLog(String objId, Integer loteAtual, Integer totalEnviarLote, String statusImportecao, String observacao, Long enviado, Long naoEnviado, JsonArray listError) {

        try {

            List<Document> list = new ArrayList<Document>(Arrays.asList(new Document("status_importacao", statusImportecao)));


            if (StringUtils.isNotBlank(observacao)) {
                list.add(new Document("observacao", observacao));

            }

            if (naoEnviado != null) {
                list.add(new Document("nao_enviado", naoEnviado.intValue()));

            }

            if (enviado != null) {
                list.add(new Document("enviado", enviado.intValue()));
            }


            if (totalEnviarLote != null) {
                list.add(new Document("total_atendimento_lote_atual", totalEnviarLote));

            }

            if (loteAtual != null) {
                list.add(new Document("lote_atual", loteAtual));

            }

            if (listError != null) {
                list.add(new Document("erro_envio_lote", listError.isJsonNull()));

            }

            if ((listError != null && !listError.isJsonNull())) {
                list.add(new Document("atendimentos_erro", listError.toString()));
            }

            this.mongo = new MongoDB("importacao", "importacao_log");
            this.mongo.alterarDoc(list, objId);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void atualizandoLogErro(String objId, String observacao, JsonArray listError) {

        List<Document> list = Arrays.asList(new Document("erro_envio_lote", listError.isJsonNull()),
                new Document("atendimentos_erro", (listError == null || listError.isJsonNull()) ? null : listError.toString()),
                new Document("observacao", observacao));

        this.mongo = new MongoDB("importacao", "importacao_log");
        this.mongo.alterarDoc(list, objId);

    }


    private boolean verificarSeServicoEstaDisponivel(IntegracaoWs integracao) throws ProativaException {

        boolean online = false;
        int tentativas = 0;

        do {

            try {

                VerificarLinkUtil.verificarLink(integracao.getUrl());
                online = true;

            } catch (ProativaException ce) {

                online = false;
                tentativas++;

                if (tentativas == 20) {

                    throw new ProativaException("Serviço Indisponivel: [ " + integracao.getUrl() + " ]");

                }

            }

        } while (!online);

        return online;
    }

    @Asynchronous
    public void alimentarDiscagemFimFila(IntegracaoWs integracao, GenericAtendimento atendimento) {

        try {

            initVonixClient(integracao);

            verificarSeServicoEstaDisponivel(integracao);

            List<AcaoStatusTelefoneEnum> paramentrosStatusTelefone = Arrays.asList(new AcaoStatusTelefoneEnum[]{AcaoStatusTelefoneEnum.CONTATO_DISPOSITIVO_ELETRONICO, AcaoStatusTelefoneEnum.CONTATO_CLIENTE});

            Set<String> listTelefones = (Set<String>) atendimento.getListaTelefones().stream().filter(t -> (t.getStatusTelefone() == null || paramentrosStatusTelefone.contains(t.getStatusTelefone().getParametro())))
                    .map(t -> t.getId().toString() + ";" + t.getDdd() + t.getNumero()).collect(Collectors.toSet());

            if (CollectionUtils.isNotEmpty(listTelefones)) {

                Fila fila = atendimento.getCampanha().getFila();

                if (fila != null)
                    this.vonixClient.enviarContatos(atendimento.getId(), atendimento.getNome(), fila.getNome(), listTelefones);

            }


        } catch (Exception e) {

            System.out.println("Erro ao enviar contatos Vonix" + (e instanceof ProativaException ? ": " + e.getMessage() : ""));
            alimentarDiscagemFimFila(integracao, atendimento);
        }

    }


    public String consultarContatosVonix(IntegracaoWs integra, String nomeFila) {


        initVonixClient(integra);

        try {

            return this.vonixClient.consultarContatosFila(nomeFila);


        } catch (Exception e) {

            e.printStackTrace();
            return "Erro ao consultar.";

        }

    }


    public void deletarContatosDiscadorVonix(IntegracaoWs integracao, List<Long> listAtendimentos) {


        try {

            initVonixClient(integracao);

            verificarSeServicoEstaDisponivel(integracao);

            System.out.println("Iniciando, deletar contatos. ");

            for (Long atendimento : listAtendimentos) {

                this.vonixClient.removerContato(atendimento);


            }

            System.out.println("Finalizando, deletar contatos. ");

        } catch (ProativaException e) {

            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void initVonixClient(IntegracaoWs integracao) {

        this.vonixClient = new VonixClient(integracao.getUrl(), integracao.getUsr(), integracao.getPsw());

    }

    public void entrarEmpausa(IntegracaoWs integracao, Usuario usuario, Pausa pausa) {


    }

    public String consultarQuantidadeContatos(IntegracaoWs integrarWs, String nomeFila) throws ProativaException {

        try {

            initVonixClient(integrarWs);
            return this.vonixClient.consultarContatosFila(nomeFila);

        } catch (IOException | SAXException | RuntimeException | ParserConfigurationException  e) {

            e.printStackTrace();
            throw new ProativaException("Erro ao consultar dados vonix.");

        }

    }

    public boolean limparContatosFila(IntegracaoWs integrarWs, String nomeFila) throws ProativaException {

        try {

            initVonixClient(integrarWs);
            return this.vonixClient.removerTodosContatos(nomeFila);

        } catch (IOException e) {

            e.printStackTrace();
            throw new ProativaException("Erro ao remover os contatos");

        }

    }

}
