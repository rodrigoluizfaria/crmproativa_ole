package com.proativaservicos.model.bancoMaster;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class ConsultarLimiteSaqueResponse extends GenericResponse {

    private List<ConsultaLimitesPorCpfResponse> limitesPorCpf;

    public ConsultarLimiteSaqueResponse() {

    }

    public ConsultarLimiteSaqueResponse(Integer codigoErro, String mensagem, List<ConsultaLimitesPorCpfResponse> limitesPorCpf) {
        this.limitesPorCpf = limitesPorCpf;

        setCodigoErro(codigoErro);
        setMessage(mensagem);
    }

    public ConsultarLimiteSaqueResponse(List<ConsultaLimitesPorCpfResponse> limitesPorCpf) {
        this.limitesPorCpf = limitesPorCpf;
    }



    public List<ConsultaLimitesPorCpfResponse> getLimitesPorCpf() {
        return limitesPorCpf;
    }

    public void setLimitesPorCpf(List<ConsultaLimitesPorCpfResponse> limitesPorCpf) {
        this.limitesPorCpf = limitesPorCpf;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Codigo Erro", getCodigoErro()).append("Mensagem", getMessage())
                .append("limitesPorCpf", limitesPorCpf)
                .toString();
    }
}
