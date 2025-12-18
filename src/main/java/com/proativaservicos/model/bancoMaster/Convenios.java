package com.proativaservicos.model.bancoMaster;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Convenios extends GenericResponse {

    private List<ConvenioResponse> listaConveniosResponses;


    public Convenios() {

    }

    public Convenios(Integer codigoErro, String mensagem) {
        setCodigoErro(codigoErro);
        setMessage(mensagem);

    }


    public Convenios(List<ConvenioResponse> listaConveniosResponses) {
        this.listaConveniosResponses = listaConveniosResponses;
    }

    public List<ConvenioResponse> getListaConveniosResponses() {
        return listaConveniosResponses;
    }

    public void setListaConveniosResponses(List<ConvenioResponse> listaConveniosResponses) {
        this.listaConveniosResponses = listaConveniosResponses;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoErro", getCodigoErro())
                .append("mensagem", getMessage())
                .append("listaConveniosResponses", listaConveniosResponses)
                .toString();
    }
}
