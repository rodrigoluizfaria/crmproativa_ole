package com.proativaservicos.model.argus;

public class GenericResponse {

    private String descStatus;
    private int codStatus;
    private boolean transferirOuvidoria;
    private String cpf;
    private String nome;
    private boolean temAtendimento;


    public GenericResponse(){

    }

    public GenericResponse(String descStatus, int codStatus) {
        this.descStatus = descStatus;
        this.codStatus = codStatus;
    }

    public GenericResponse(String descStatus, boolean transferirOuvidoria) {
        this.descStatus = descStatus;
        this.transferirOuvidoria = transferirOuvidoria;
    }

    public GenericResponse(String descStatus, boolean transferirOuvidoria,String cpf,String nome,boolean temAtendimento) {
        this.descStatus = descStatus;
        this.transferirOuvidoria = transferirOuvidoria;
        this.cpf = cpf;
        this.nome = nome;
        this.temAtendimento = temAtendimento;
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

    public boolean isTransferirOuvidoria() {
        return transferirOuvidoria;
    }

    public void setTransferirOuvidoria(boolean transferirOuvidoria) {
        this.transferirOuvidoria = transferirOuvidoria;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isTemAtendimento() {
        return temAtendimento;
    }

    public void setTemAtendimento(boolean temAtendimento) {
        this.temAtendimento = temAtendimento;
    }
}
