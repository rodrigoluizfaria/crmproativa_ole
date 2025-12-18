package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCalls  extends  ResponseGeneric{

    @SerializedName("data")
    private List<Call> data;

    public List<Call> getData() {
        return data;
    }

    public void setData(List<Call> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseCalls{" +
                "data=" + data +
                "} " + super.toString();
    }
}
