package com.proativaservicos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartao_credito")
public class CartaoCredito extends GenericCartaoCredito {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento")
    private Atendimento atendimento;


    @Override
    public GenericAtendimento getAtendimento() {
        // TODO Auto-generated method stub
        return this.atendimento;
    }

    @Override
    public void setAtendimento(GenericAtendimento atn) {
        this.atendimento = (Atendimento) atn;

    }


}
