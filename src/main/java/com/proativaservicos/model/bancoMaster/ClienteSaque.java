package com.proativaservicos.model.bancoMaster;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class ClienteSaque extends GenericResponse{


    private List<ConsultarLimiteSaqueResponse> consultarLimiteSaque;

    private CartaoResponse cartaoResponse;


    private Convenios convenios;

    public  List<ConsultarLimiteSaqueResponse> getConsultarLimiteSaque() {
        return consultarLimiteSaque;
    }

    public void setConsultarLimiteSaque( List<ConsultarLimiteSaqueResponse> consultarLimiteSaque) {
        this.consultarLimiteSaque = consultarLimiteSaque;
    }

    public Convenios getConvenios() {
        return convenios;
    }

    public void setConvenios(Convenios convenios) {
        this.convenios = convenios;
    }

    public CartaoResponse getCartaoResponse() {
        return cartaoResponse;
    }

    public void setCartaoResponse(CartaoResponse cartaoResponse) {
        this.cartaoResponse = cartaoResponse;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("consultarLimiteSaque", consultarLimiteSaque)
                .append("cartaoResponse", cartaoResponse)

                .append("convenios", convenios)
                .toString();
    }
}
