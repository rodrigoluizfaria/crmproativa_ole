package com.proativaservicos.model.bancoMaster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class CartaoResponse {


    private String codigo;
    private String descricao;
    private String message;

    private List<ConsultaLimiteCartaoResponse> listaConsultaLimite;

    public CartaoResponse(String codigo, String descricao, String message, List<ConsultaLimiteCartaoResponse> listaConsultaLimite) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.message = message;
        this.listaConsultaLimite = listaConsultaLimite;
    }

    public CartaoResponse(String message, String codigo) {
        this.message = message;
        this.codigo = codigo;
    }

    public CartaoResponse() {
        this.listaConsultaLimite = new ArrayList<ConsultaLimiteCartaoResponse>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ConsultaLimiteCartaoResponse> getListaConsultaLimite() {
        return listaConsultaLimite;
    }

    public void setListaConsultaLimite(List<ConsultaLimiteCartaoResponse> listaConsultaLimite) {
        this.listaConsultaLimite = listaConsultaLimite;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigo", codigo)
                .append("descricao", descricao)
                .append("message", message)
                .append("listaConsultaLimite", listaConsultaLimite)
                .toString();
    }

    public String toJason() {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }

    }



}
