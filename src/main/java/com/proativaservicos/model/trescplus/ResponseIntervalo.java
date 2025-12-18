package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseIntervalo extends  ResponseGeneric {


    @SerializedName("data")
    private List<Intervalo> data;

    public List<Intervalo> getData() {
        return data;
    }

    public void setData(List<Intervalo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseIntervalo{" +
                "data=" + data +
                '}';
    }
}
