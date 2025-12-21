package com.proativaservicos.model.dto;

import com.proativaservicos.util.constantes.TipoStatusAtividadesEnum;

import java.util.Date;

public class HistoricoAtividadesDto {


    private TipoStatusAtividadesEnum tipoStatusAtividade;
    private String descricao;
    private String detalhes;
    private Date data;
    private String autor;
    private String tipoIcone;

    public HistoricoAtividadesDto(){}
    public HistoricoAtividadesDto(TipoStatusAtividadesEnum tipoStatusAtividade, String descricao, String detalhes, Date data, String autor, String tipoIcone) {
        this.tipoStatusAtividade = tipoStatusAtividade;
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.data = data;
        this.autor = autor;
        this.tipoIcone = tipoIcone;
    }

    public TipoStatusAtividadesEnum getTipoStatusAtividade() {
        return tipoStatusAtividade;
    }

    public void setTipoStatusAtividade(TipoStatusAtividadesEnum tipoStatusAtividade) {
        this.tipoStatusAtividade = tipoStatusAtividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipoIcone() {
        return tipoIcone;
    }

    public void setTipoIcone(String tipoIcone) {
        this.tipoIcone = tipoIcone;
    }
}
