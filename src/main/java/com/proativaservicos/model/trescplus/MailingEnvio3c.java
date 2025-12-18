package com.proativaservicos.model.trescplus;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

public class MailingEnvio3c {


    private byte[] ioMailing;

    private File mailing;

    private int quantidadetelefones;

    private String arrayCabecalho[];

    private List<String[]> listLinhas;

    private LinkedHashMap<String,String> mapParamCabecalho;


    public MailingEnvio3c(String[] arrayCabecalho, List<String[]> listLinhas, Integer quantidadeTelefones, LinkedHashMap<String,String> mapParamCabecalho, byte[] ioMailing) {

        this.arrayCabecalho = arrayCabecalho;
        this.listLinhas = listLinhas;
        this.quantidadetelefones = quantidadeTelefones;
        this.ioMailing = ioMailing;
        this.mapParamCabecalho = mapParamCabecalho;
    }

    public byte[] getIoMailing() {
        return ioMailing;
    }

    public void setIoMailing(byte[] ioMailing) {
        this.ioMailing = ioMailing;
    }

    public File getMailing() {
        return mailing;
    }

    public void setMailing(File mailing) {
        this.mailing = mailing;
    }



    public int getQuantidadetelefones() {
        return quantidadetelefones;
    }

    public void setQuantidadetelefones(int quantidadetelefones) {
        this.quantidadetelefones = quantidadetelefones;
    }

    public String[] getArrayCabecalho() {
        return arrayCabecalho;
    }

    public void setArrayCabecalho(String[] arrayCabecalho) {
        this.arrayCabecalho = arrayCabecalho;
    }

    public List<String[]> getListLinhas() {
        return listLinhas;
    }

    public void setListLinhas(List<String[]> listLinhas) {
        this.listLinhas = listLinhas;
    }

    public LinkedHashMap<String, String> getMapParamCabecalho() {
        return mapParamCabecalho;
    }

    public void setMapParamCabecalho(LinkedHashMap<String, String> mapParamCabecalho) {
        this.mapParamCabecalho = mapParamCabecalho;
    }
}
