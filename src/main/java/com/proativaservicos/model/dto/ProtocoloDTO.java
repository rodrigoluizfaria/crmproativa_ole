package com.proativaservicos.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProtocoloDTO {

    private String numeroProtocolo;
    private String status;
    private String tipo;
    private String descricao;
    private Date dataAbertura;
    private Date dataPrazo;

    public ProtocoloDTO(String numero, String status, String tipo, String desc, String dataAb, String dataPz) {
        this.numeroProtocolo = numero;
        this.status = status;
        this.tipo = tipo;
        this.descricao = desc;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.dataAbertura = sdf.parse(dataAb);
            this.dataPrazo = sdf.parse(dataPz);
        } catch (Exception e) { e.printStackTrace(); }
    }


    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(Date dataPrazo) {
        this.dataPrazo = dataPrazo;
    }
}
