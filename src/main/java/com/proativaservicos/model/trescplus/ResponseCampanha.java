package com.proativaservicos.model.trescplus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResponseCampanha extends ResponseGeneric {

    @SerializedName("data")
    private Campanha data;

    public Campanha getData() {
        return data;
    }
    public void setData(Campanha data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public class Errors {

        public String[] mailing;

        public String[] getMailing() {
            return mailing;
        }

        public void setMailing(String[] mailing) {
            this.mailing = mailing;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("data", data)
                .append("errors", errors)
                .toString();
    }

    public String toJson() throws JsonProcessingException {

        try {

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(this);

        } catch (Exception e) {
            e.printStackTrace();
            return getDetail();
        }
    }
}
