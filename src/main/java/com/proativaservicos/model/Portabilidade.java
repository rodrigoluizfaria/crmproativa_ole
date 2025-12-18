package com.proativaservicos.model;


import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "portabilidade")
public class Portabilidade extends Generic {



    public Portabilidade() {}

    public Portabilidade(Long codigo,String beneficio,String especie) {

        setId(codigo);
        setBeneficio(beneficio);
        setEspecie(especie);

    }
    public Portabilidade(String beneficio, String especie) {
        setBeneficio(beneficio);
        setEspecie(especie);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento")
    private Atendimento atendimento;

    @Column(name = "banco_origem")
    @Enumerated(EnumType.STRING)
    private InstituicaoFinanceiraEnum bancoOrigem;

    @Column(name = "saldo_devedor")
    private BigDecimal saldoDevedor;

    @Column(name = "valor_parcela")
    private BigDecimal valorParcela;

    @Column(name = "prazo_total")
    private Integer prazoTotal;

    @Column(name = "prazo_restante")
    private Integer prazoRestante;

    @Column(name = "taxa_juros")
    private BigDecimal taxaJuros;

    @Column(name = "data_averbacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAverbacao;

    @Column(name = "beneficio")
    private String beneficio;

    @Column(name = "especie")
    private String especie;

    @Transient
    private boolean selecionado;





    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public InstituicaoFinanceiraEnum getBancoOrigem() {
        return bancoOrigem;
    }

    public void setBancoOrigem(InstituicaoFinanceiraEnum bancoOrigem) {
        this.bancoOrigem = bancoOrigem;
    }

    public BigDecimal getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(BigDecimal saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Integer getPrazoTotal() {
        return prazoTotal;
    }

    public void setPrazoTotal(Integer prazoTotal) {
        this.prazoTotal = prazoTotal;
    }

    public Integer getPrazoRestante() {
        return prazoRestante;
    }

    public void setPrazoRestante(Integer prazoRestante) {
        this.prazoRestante = prazoRestante;
    }

    public BigDecimal getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Date getDataAverbacao() {
        return dataAverbacao;
    }

    public void setDataAverbacao(Date dataAverbacao) {
        this.dataAverbacao = dataAverbacao;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return "Portabilidade{ ID: " +getId()+" "+
                ", bancoOrigem=" + bancoOrigem +
                ", saldoDevedor=" + saldoDevedor +
                ", valorParcela=" + valorParcela +
                ", prazoTotal=" + prazoTotal +
                ", prazoRestante=" + prazoRestante +
                ", taxaJuros=" + taxaJuros +
                ", dataAverbacao=" + dataAverbacao +
                ", beneficio='" + beneficio + '\'' +
                ", especie='" + especie + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bancoOrigem, saldoDevedor, valorParcela, prazoTotal, prazoRestante, taxaJuros, dataAverbacao, beneficio, especie);
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
