package com.proativaservicos.model.argus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TabulacoeItem {


    private String codIntegTabulacao;
    private int idTabulacao;
    private String tabulacaoDesc;
    private String categoriaDesc;
    private int idTipoCategoria;
    private String tipoCategoriaDesc;
    private boolean finalizador;
    private boolean agendamento;
    private String idTipoAgenda;
    private String idTipoAgendaFixo;
    private String idTipoAgendaFixoDesc;
    private String agendamentoFixo;

    public String getCodIntegTabulacao() {
        return codIntegTabulacao;
    }

    public void setCodIntegTabulacao(String codIntegTabulacao) {
        this.codIntegTabulacao = codIntegTabulacao;
    }

    public int getIdTabulacao() {
        return idTabulacao;
    }

    public void setIdTabulacao(int idTabulacao) {
        this.idTabulacao = idTabulacao;
    }

    public String getTabulacaoDesc() {
        return tabulacaoDesc;
    }

    public void setTabulacaoDesc(String tabulacaoDesc) {
        this.tabulacaoDesc = tabulacaoDesc;
    }

    public String getCategoriaDesc() {
        return categoriaDesc;
    }

    public void setCategoriaDesc(String categoriaDesc) {
        this.categoriaDesc = categoriaDesc;
    }

    public int getIdTipoCategoria() {
        return idTipoCategoria;
    }

    public void setIdTipoCategoria(int idTipoCategoria) {
        this.idTipoCategoria = idTipoCategoria;
    }

    public String getTipoCategoriaDesc() {
        return tipoCategoriaDesc;
    }

    public void setTipoCategoriaDesc(String tipoCategoriaDesc) {
        this.tipoCategoriaDesc = tipoCategoriaDesc;
    }

    public boolean isFinalizador() {
        return finalizador;
    }

    public void setFinalizador(boolean finalizador) {
        this.finalizador = finalizador;
    }

    public boolean isAgendamento() {
        return agendamento;
    }

    public void setAgendamento(boolean agendamento) {
        this.agendamento = agendamento;
    }

    public String getIdTipoAgenda() {
        return idTipoAgenda;
    }

    public void setIdTipoAgenda(String idTipoAgenda) {
        this.idTipoAgenda = idTipoAgenda;
    }

    public String getIdTipoAgendaFixo() {
        return idTipoAgendaFixo;
    }

    public void setIdTipoAgendaFixo(String idTipoAgendaFixo) {
        this.idTipoAgendaFixo = idTipoAgendaFixo;
    }

    public String getIdTipoAgendaFixoDesc() {
        return idTipoAgendaFixoDesc;
    }

    public void setIdTipoAgendaFixoDesc(String idTipoAgendaFixoDesc) {
        this.idTipoAgendaFixoDesc = idTipoAgendaFixoDesc;
    }

    public String getAgendamentoFixo() {
        return agendamentoFixo;
    }

    public void setAgendamentoFixo(String agendamentoFixo) {
        this.agendamentoFixo = agendamentoFixo;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("codIntegTabulacao", codIntegTabulacao)
                .add("idTabulacao", idTabulacao)
                .add("tabulacaoDesc", tabulacaoDesc)
                .add("categoriaDesc", categoriaDesc)
                .add("idTipoCategoria", idTipoCategoria)
                .add("tipoCategoriaDesc", tipoCategoriaDesc)
                .add("finalizador", finalizador)
                .add("agendamento", agendamento)
                .add("idTipoAgenda", idTipoAgenda)
                .add("idTipoAgendaFixo", idTipoAgendaFixo)
                .add("idTipoAgendaFixoDesc", idTipoAgendaFixoDesc)
                .add("agendamentoFixo", agendamentoFixo)
                .toString();
    }
}
