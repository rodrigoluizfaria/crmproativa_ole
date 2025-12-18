package com.proativaservicos.model.trescplus;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListCampanha extends ResponseGeneric {


    @SerializedName("data")
    private List<Campanha> data;


    public List<Campanha> getData() {
        return data;
    }

    public void setData(List<Campanha> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "ListCampanhaResponse{" +
                "data=" + data +
                '}';
    }
}
