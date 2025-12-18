package com.proativaservicos.model.trescplus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public  abstract  class ResponseGeneric implements Serializable {

    @SerializedName("status")
    private int status;

    @SerializedName("title")
    private String title;

    @SerializedName("detail")
    private String detail;

    @SerializedName("transaction_id")
    @JsonProperty("transaction_id")
    private String transactionId;


    @SerializedName("meta")
    private Meta meta;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }



    @Override
    public String toString() {
        return "ResponseGeneric{" +
                "status=" + status +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", meta=" + meta +
                '}';
    }
}
