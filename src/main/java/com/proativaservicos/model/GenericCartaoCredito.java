package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@MappedSuperclass
public abstract class GenericCartaoCredito extends Generic {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "numero_cartao")
    private String numeroCartao;

     @Column(name = "codigo_seguranca")
    private Integer codigoSeguranca;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "validade")
    private String validade;

    @Column(name = "status")
    private String status;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "bandeira")
    private String bandeira;

    @Column(name = "obervacao")
    private String observacao;

    @Column(name = "cartao_adicional")
    private Boolean cartaoAdicional;

    @Column(name = "limite_total")
    private BigDecimal limiteTotal;

    @Column(name = "limite_disponivel")
    private BigDecimal limiteDisponivel;

    @Column(name = "limite_emergencial")
    private BigDecimal limiteEmergencial;


    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Integer getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(Integer codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumeroMascarado() {

        if (numeroCartao == null || numeroCartao.length() < 4) {
            return "****";
        }

        String ultimos4 = numeroCartao.substring(numeroCartao.length() - 4);
        return "**** **** **** " + ultimos4;
    }

    public Boolean getCartaoAdicional() {
        return cartaoAdicional;
    }

    public void setCartaoAdicional(Boolean cartaoAdicional) {
        this.cartaoAdicional = cartaoAdicional;
    }

    public abstract GenericAtendimento getAtendimento();

    public abstract void setAtendimento(GenericAtendimento paramGenericAtendimento);

    public BigDecimal getLimiteTotal() {
        return limiteTotal;
    }

    public void setLimiteTotal(BigDecimal limiteTotal) {
        this.limiteTotal = limiteTotal;
    }

    public BigDecimal getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(BigDecimal limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }

    public BigDecimal getLimiteEmergencial() {
        return limiteEmergencial;
    }

    public void setLimiteEmergencial(BigDecimal limiteEmergencial) {
        this.limiteEmergencial = limiteEmergencial;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
