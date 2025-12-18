package com.proativaservicos.service;


import com.proativaservicos.model.ConciliarAudioAnexo;
import com.proativaservicos.util.TranscreverPython;
import com.proativaservicos.util.Utils;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.File;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.BEAN)
public class TranscreverService {


    @Inject
    private ConcilicarAudioAnexoService concilicarAudioAnexoService;

    @Asynchronous
    public void transcreverConciliarPyton(ConciliarAudioAnexo conciliarAudioAnexo) {

        System.out.println("TranscreverConciliarPyton");

        if (validarConciliar(conciliarAudioAnexo)) {
            System.out.println("TranscreverConciliarPyton executando " + conciliarAudioAnexo.getNomeArquivo());
            String texto = TranscreverPython.transcrever(conciliarAudioAnexo.getArquivoCompleto() + File.separator + conciliarAudioAnexo.getNomeArquivo());
            texto = StringUtils.isNotBlank(texto) ? texto : "não foi possivel transcrever.";
            this.concilicarAudioAnexoService.salvarTranscricao(conciliarAudioAnexo.getId(), texto);

        }

    }


    @Asynchronous
    public void transcreverConciliarApiPyton(ConciliarAudioAnexo conciliarAudioAnexo) {

        System.out.println("TranscreverConciliarApiPyton");

        if (validarConciliar(conciliarAudioAnexo)) {

            String texto = TranscreverPython.transcreverAudioAnexoApi(conciliarAudioAnexo.getArquivoCompleto() + File.separator + conciliarAudioAnexo.getNomeArquivo());

            Integer codAudio = retornarAudioId(texto);

            if (codAudio != null) {

                this.concilicarAudioAnexoService.salvarTranscricao(conciliarAudioAnexo.getId(), "Processando transcrição, em instantes o resultado estará disponível.", codAudio);

            } else {

                this.concilicarAudioAnexoService.salvarTranscricao(conciliarAudioAnexo.getId(), "não foi possivel transcrever.");

            }

        }

    }

    private Integer retornarAudioId(String texto) {

        try {

            if (StringUtils.isNotBlank(texto) && Utils.isJSON(texto)) {

                JSONObject jsonObject = new JSONObject(texto);

                if (jsonObject.has("audio_id")) {
                    return jsonObject.getInt("audio_id");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean validarConciliar(ConciliarAudioAnexo conciliarAudioAnexo) {

        return conciliarAudioAnexo != null && conciliarAudioAnexo.getId() != null && StringUtils.isNotBlank(conciliarAudioAnexo.getNomeArquivo());
    }


    public static void main(String[] args) {

        String api = "{\"task_id\":\"fce5ca98-f102-4666-950a-f0726d15ebaf\",\"audio_id\":42}";

        System.out.println(Utils.isJSON(api));
        JSONObject jsonObject = new JSONObject(api);

        if (jsonObject.has("task_id")) {
            System.out.println(jsonObject.getString("task_id"));
        }

        if (jsonObject.has("audio_id")) {
            System.out.println(jsonObject.getInt("audio_id"));
        }
    }

}
