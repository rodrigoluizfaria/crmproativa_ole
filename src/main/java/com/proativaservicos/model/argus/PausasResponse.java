package com.proativaservicos.model.argus;

import java.util.List;

public class PausasResponse extends GenericResponse {

private List<PausaItem> retornoPausasItens;

    public List<PausaItem> getRetornoPausasItens() {
        return retornoPausasItens;
    }

    public void setRetornoPausasItens(List<PausaItem> retornoPausasItens) {
        this.retornoPausasItens = retornoPausasItens;
    }
}
