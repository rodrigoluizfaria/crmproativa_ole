package com.proativaservicos.model.trescplus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCall extends  ResponseGeneric{

    @JsonProperty("data")
    private Call data;

    public Call getData() {
        return data;
    }

    public void setData(Call data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseCalls{" +
                "data=" + data +
                "} " + super.toString();
    }
}
