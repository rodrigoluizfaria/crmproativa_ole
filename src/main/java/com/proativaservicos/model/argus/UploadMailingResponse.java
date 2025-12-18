package com.proativaservicos.model.argus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class UploadMailingResponse implements Response {

    private int idArquivo;

    private int codStatus;

    private String descStatus;

    public int getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(int idArquivo) {
        this.idArquivo = idArquivo;
    }

    public int getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(int codStatus) {
        this.codStatus = codStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("idArquivo", idArquivo)
                .append("codStatus", codStatus)
                .append("descStatus", descStatus)
                .toString();
    }

    public String toJson()  {

        try {

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(this);

        } catch (Exception e) {
            e.printStackTrace();
            return getDescStatus();
        }
    }
}
