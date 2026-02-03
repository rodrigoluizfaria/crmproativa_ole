package com.proativaservicos.model.dto;

import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.model.Usuario;

import java.util.Date;
import java.util.List;

public class RelatorioFiltroDto {
    private static final long serialVersionUID = 1L;

    // Filtros de Data (Obrigatórios ou Padrão)
    private Date dataInicio;
    private Date dataFim;

    // Filtros de Seleção (Combos)
    private Usuario usuarioSelecionado;
    private List<StatusAtendimento> listaStatus; // Caso queira filtrar por múltiplos status

    // Filtros Booleanos (Checkboxes)
    private boolean apenasFcr;
    private boolean apenasReincidencia;
    private boolean apenasPrazoVencido;

    // Filtros de Texto (Opcionais)
    private String protocolo;


    public RelatorioFiltroDto() {

        this.apenasFcr = false;
        this.apenasReincidencia = false;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public List<StatusAtendimento> getListaStatus() {
        return listaStatus;
    }

    public void setListaStatus(List<StatusAtendimento> listaStatus) {
        this.listaStatus = listaStatus;
    }

    public boolean isApenasFcr() {
        return apenasFcr;
    }

    public void setApenasFcr(boolean apenasFcr) {
        this.apenasFcr = apenasFcr;
    }

    public boolean isApenasReincidencia() {
        return apenasReincidencia;
    }

    public void setApenasReincidencia(boolean apenasReincidencia) {
        this.apenasReincidencia = apenasReincidencia;
    }

    public boolean isApenasPrazoVencido() {
        return apenasPrazoVencido;
    }

    public void setApenasPrazoVencido(boolean apenasPrazoVencido) {
        this.apenasPrazoVencido = apenasPrazoVencido;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
