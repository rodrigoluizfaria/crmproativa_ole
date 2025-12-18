package com.proativaservicos.model.argus;

import com.google.common.base.MoreObjects;

import java.util.List;

public class TabulacoesResponse extends GenericResponse {

    private List<TabulacoeItem> retornoTabulacoesItens;

    public List<TabulacoeItem> getRetornoTabulacoesItens() {
        return retornoTabulacoesItens;
    }

    public void setRetornoTabulacoesItens(List<TabulacoeItem> retornoTabulacoesItens) {
        this.retornoTabulacoesItens = retornoTabulacoesItens;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("codStatus",getCodStatus()).add("descStatus",getDescStatus())
                .add("retornoTabulacoesItens", retornoTabulacoesItens)
                .toString();
    }
}
