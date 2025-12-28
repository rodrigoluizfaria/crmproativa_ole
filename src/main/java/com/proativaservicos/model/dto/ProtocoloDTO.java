package com.proativaservicos.model.dto;

import com.proativaservicos.util.constantes.StatusProtocoloEnum;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProtocoloDTO {

    private Long idAtendimento;
    private String numeroProtocolo;
    private StatusProtocoloEnum status;
    private String tipo;
    private String descricao;
    private Date dataAbertura;
    private Date dataPrazo;

    public ProtocoloDTO() {
    }

    public ProtocoloDTO(String numero, StatusProtocoloEnum status, String tipo, String desc, String dataAb, String dataPz,Long idAtendimento) {
        this.numeroProtocolo = numero;
        this.status = status;
        this.tipo = tipo;
        this.idAtendimento = idAtendimento;
        this.descricao = desc;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            if (StringUtils.isNotBlank(dataAb))
                this.dataAbertura = sdf.parse(dataAb);
            if (StringUtils.isNotBlank(dataPz))
                this.dataPrazo = sdf.parse(dataPz);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public StatusProtocoloEnum getStatus() {
        return status;
    }

    public void setStatus(StatusProtocoloEnum status) {
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

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }
}
