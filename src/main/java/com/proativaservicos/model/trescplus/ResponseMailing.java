package com.proativaservicos.model.trescplus;

import java.util.List;

public class ResponseMailing extends ResponseGeneric {


    private List<Mailing> data;


    public List<Mailing> getData() {
        return data;
    }

    public void setData(List<Mailing> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MailingResponse{" +
                "data=" + data +
                '}';
    }
}
