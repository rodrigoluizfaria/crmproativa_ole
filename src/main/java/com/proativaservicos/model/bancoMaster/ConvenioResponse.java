package com.proativaservicos.model.bancoMaster;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ConvenioResponse extends GenericResponse {

    private String cdCpf;

    private String idConvenio;

    public String getCdCpf() {
        return cdCpf;
    }

    public void setCdCpf(String cdCpf) {
        this.cdCpf = cdCpf;
    }

    public String getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(String idConvenio) {
        this.idConvenio = idConvenio;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cdCpf", cdCpf)
                .append("idConvenio", idConvenio).append("codigoErro",getCodigoErro()).append("mensagem", getMessage())
                .toString();
    }
}
