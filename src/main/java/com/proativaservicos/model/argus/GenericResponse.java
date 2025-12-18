package com.proativaservicos.model.argus;

public class GenericResponse {

    private String descStatus;
    private int codStatus;


    public GenericResponse(){

    }

    public GenericResponse(String descStatus, int codStatus) {
        this.descStatus = descStatus;
        this.codStatus = codStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public int getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(int codStatus) {
        this.codStatus = codStatus;
    }
}
