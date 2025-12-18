package com.proativaservicos.model.argus;

import com.proativaservicos.model.MailingDiscador;

import java.util.LinkedHashMap;
import java.util.List;

public class MailingArgus extends MailingDiscador {


    public MailingArgus(String[] arrayCabecalho, List<String[]> listLinhas, Integer quantidadeTelefones, LinkedHashMap<String, String> mapParamCabecalho, byte[] ioMailing) {
        super(arrayCabecalho, listLinhas, quantidadeTelefones, mapParamCabecalho, ioMailing);
    }
}
