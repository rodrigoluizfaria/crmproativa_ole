package com.proativaservicos.util;

import com.google.gson.GsonBuilder;
import com.proativaservicos.model.Importacao;
import com.proativaservicos.util.constantes.DadosBaseImportacaoEnum;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.*;

import java.io.Serializable;
import java.util.Map;

@Stateless
public class ImportacaoArquivoUtil implements Serializable {
    private static final long serialVersionUID = 1L;


    @Resource(mappedName = "java:/queue/ImportacaoMsgAsynchronous")
    private Queue queue;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;


    public void sendImportacao(Long idCampanha, Importacao importacao, Map<Integer, DadosBaseImportacaoEnum> mapCabecalho, byte[] arquivo) {

      try {

            System.out.println("ENVIANDO IMPORTACAO");
            MapMessage mapMessage = this.context.createMapMessage();
            mapMessage.setLong("idCampanha", idCampanha);
            mapMessage.setString("header", (new GsonBuilder()).serializeNulls().create().toJson(mapCabecalho));
            mapMessage.setBytes("arquivo", arquivo);
            mapMessage.setLong("idImportacao", importacao.getId());
            mapMessage.setLong("idUsuario", importacao.getUsuario().getId());
            mapMessage.setLong("idEmpresa", importacao.getEmpresa().getId());
            mapMessage.setString("idCodRefin", importacao.getCodTabelaRefin() != null ? String.valueOf(importacao.getCodTabelaRefin()) : null);

            this.context.createProducer().send(queue, mapMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}
