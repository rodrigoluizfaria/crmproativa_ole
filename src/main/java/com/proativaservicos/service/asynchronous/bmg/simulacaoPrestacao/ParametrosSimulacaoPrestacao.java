/**
 * ParametrosSimulacaoPrestacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosSimulacaoPrestacao;

public class ParametrosSimulacaoPrestacao  extends com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.WebServiceParameter  implements java.io.Serializable {
    private boolean associado;

    private java.lang.Integer codigoEntidade;

    private java.lang.Integer codigoProduto;

    private com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoPortabilidade contrato;

    private long cpfAsNumber;

    private java.util.Calendar dataAdmissao;

    private java.util.Calendar dataFator;

    private java.util.Calendar dataNascimento;

    private java.lang.Integer formaCredito;

    private boolean indicadorApenasParcelamentoIdealMaximo;

    private boolean indicadorPesquisaCet;

    private java.lang.String loginConsig;

    private java.lang.Integer loja;

    private double margem;

    private java.lang.String modoPagamentoIof;

    private java.lang.String modoPagamentoTac;

    private java.lang.String modoPagamentoTlf;

    private java.lang.String numeroAdesao;

    private java.lang.Integer numeroContrato;

    private int quantidadePrestacoes;

    private java.lang.String senhaConsig;

    private java.lang.Integer sequencialOrgao;

    private java.lang.String servico;

    private double taxaFormaCredito;

    private java.lang.Integer tipoFormaEnvio;

    private long unidadePagadora;

    private double valorLiberado;

    private double valorPrestacao;

    private double valorRenda;

    private java.lang.Double valorSaldoDevedor;

    public ParametrosSimulacaoPrestacao() {
    }

    public ParametrosSimulacaoPrestacao(
           java.lang.String login,
           java.lang.String senha,
           boolean associado,
           java.lang.Integer codigoEntidade,
           java.lang.Integer codigoProduto,
           com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoPortabilidade contrato,
           long cpfAsNumber,
           java.util.Calendar dataAdmissao,
           java.util.Calendar dataFator,
           java.util.Calendar dataNascimento,
           java.lang.Integer formaCredito,
           boolean indicadorApenasParcelamentoIdealMaximo,
           boolean indicadorPesquisaCet,
           java.lang.String loginConsig,
           java.lang.Integer loja,
           double margem,
           java.lang.String modoPagamentoIof,
           java.lang.String modoPagamentoTac,
           java.lang.String modoPagamentoTlf,
           java.lang.String numeroAdesao,
           java.lang.Integer numeroContrato,
           int quantidadePrestacoes,
           java.lang.String senhaConsig,
           java.lang.Integer sequencialOrgao,
           java.lang.String servico,
           double taxaFormaCredito,
           java.lang.Integer tipoFormaEnvio,
           long unidadePagadora,
           double valorLiberado,
           double valorPrestacao,
           double valorRenda,
           java.lang.Double valorSaldoDevedor) {
        super(
            login,
            senha);
        this.associado = associado;
        this.codigoEntidade = codigoEntidade;
        this.codigoProduto = codigoProduto;
        this.contrato = contrato;
        this.cpfAsNumber = cpfAsNumber;
        this.dataAdmissao = dataAdmissao;
        this.dataFator = dataFator;
        this.dataNascimento = dataNascimento;
        this.formaCredito = formaCredito;
        this.indicadorApenasParcelamentoIdealMaximo = indicadorApenasParcelamentoIdealMaximo;
        this.indicadorPesquisaCet = indicadorPesquisaCet;
        this.loginConsig = loginConsig;
        this.loja = loja;
        this.margem = margem;
        this.modoPagamentoIof = modoPagamentoIof;
        this.modoPagamentoTac = modoPagamentoTac;
        this.modoPagamentoTlf = modoPagamentoTlf;
        this.numeroAdesao = numeroAdesao;
        this.numeroContrato = numeroContrato;
        this.quantidadePrestacoes = quantidadePrestacoes;
        this.senhaConsig = senhaConsig;
        this.sequencialOrgao = sequencialOrgao;
        this.servico = servico;
        this.taxaFormaCredito = taxaFormaCredito;
        this.tipoFormaEnvio = tipoFormaEnvio;
        this.unidadePagadora = unidadePagadora;
        this.valorLiberado = valorLiberado;
        this.valorPrestacao = valorPrestacao;
        this.valorRenda = valorRenda;
        this.valorSaldoDevedor = valorSaldoDevedor;
    }


    /**
     * Gets the associado value for this ParametrosSimulacaoPrestacao.
     * 
     * @return associado
     */
    public boolean isAssociado() {
        return associado;
    }


    /**
     * Sets the associado value for this ParametrosSimulacaoPrestacao.
     * 
     * @param associado
     */
    public void setAssociado(boolean associado) {
        this.associado = associado;
    }


    /**
     * Gets the codigoEntidade value for this ParametrosSimulacaoPrestacao.
     * 
     * @return codigoEntidade
     */
    public java.lang.Integer getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this ParametrosSimulacaoPrestacao.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(java.lang.Integer codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoProduto value for this ParametrosSimulacaoPrestacao.
     * 
     * @return codigoProduto
     */
    public java.lang.Integer getCodigoProduto() {
        return codigoProduto;
    }


    /**
     * Sets the codigoProduto value for this ParametrosSimulacaoPrestacao.
     * 
     * @param codigoProduto
     */
    public void setCodigoProduto(java.lang.Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }


    /**
     * Gets the contrato value for this ParametrosSimulacaoPrestacao.
     * 
     * @return contrato
     */
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoPortabilidade getContrato() {
        return contrato;
    }


    /**
     * Sets the contrato value for this ParametrosSimulacaoPrestacao.
     * 
     * @param contrato
     */
    public void setContrato(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoPortabilidade contrato) {
        this.contrato = contrato;
    }


    /**
     * Gets the cpfAsNumber value for this ParametrosSimulacaoPrestacao.
     * 
     * @return cpfAsNumber
     */
    public long getCpfAsNumber() {
        return cpfAsNumber;
    }


    /**
     * Sets the cpfAsNumber value for this ParametrosSimulacaoPrestacao.
     * 
     * @param cpfAsNumber
     */
    public void setCpfAsNumber(long cpfAsNumber) {
        this.cpfAsNumber = cpfAsNumber;
    }


    /**
     * Gets the dataAdmissao value for this ParametrosSimulacaoPrestacao.
     * 
     * @return dataAdmissao
     */
    public java.util.Calendar getDataAdmissao() {
        return dataAdmissao;
    }


    /**
     * Sets the dataAdmissao value for this ParametrosSimulacaoPrestacao.
     * 
     * @param dataAdmissao
     */
    public void setDataAdmissao(java.util.Calendar dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }


    /**
     * Gets the dataFator value for this ParametrosSimulacaoPrestacao.
     * 
     * @return dataFator
     */
    public java.util.Calendar getDataFator() {
        return dataFator;
    }


    /**
     * Sets the dataFator value for this ParametrosSimulacaoPrestacao.
     * 
     * @param dataFator
     */
    public void setDataFator(java.util.Calendar dataFator) {
        this.dataFator = dataFator;
    }


    /**
     * Gets the dataNascimento value for this ParametrosSimulacaoPrestacao.
     * 
     * @return dataNascimento
     */
    public java.util.Calendar getDataNascimento() {
        return dataNascimento;
    }


    /**
     * Sets the dataNascimento value for this ParametrosSimulacaoPrestacao.
     * 
     * @param dataNascimento
     */
    public void setDataNascimento(java.util.Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    /**
     * Gets the formaCredito value for this ParametrosSimulacaoPrestacao.
     * 
     * @return formaCredito
     */
    public java.lang.Integer getFormaCredito() {
        return formaCredito;
    }


    /**
     * Sets the formaCredito value for this ParametrosSimulacaoPrestacao.
     * 
     * @param formaCredito
     */
    public void setFormaCredito(java.lang.Integer formaCredito) {
        this.formaCredito = formaCredito;
    }


    /**
     * Gets the indicadorApenasParcelamentoIdealMaximo value for this ParametrosSimulacaoPrestacao.
     * 
     * @return indicadorApenasParcelamentoIdealMaximo
     */
    public boolean isIndicadorApenasParcelamentoIdealMaximo() {
        return indicadorApenasParcelamentoIdealMaximo;
    }


    /**
     * Sets the indicadorApenasParcelamentoIdealMaximo value for this ParametrosSimulacaoPrestacao.
     * 
     * @param indicadorApenasParcelamentoIdealMaximo
     */
    public void setIndicadorApenasParcelamentoIdealMaximo(boolean indicadorApenasParcelamentoIdealMaximo) {
        this.indicadorApenasParcelamentoIdealMaximo = indicadorApenasParcelamentoIdealMaximo;
    }


    /**
     * Gets the indicadorPesquisaCet value for this ParametrosSimulacaoPrestacao.
     * 
     * @return indicadorPesquisaCet
     */
    public boolean isIndicadorPesquisaCet() {
        return indicadorPesquisaCet;
    }


    /**
     * Sets the indicadorPesquisaCet value for this ParametrosSimulacaoPrestacao.
     * 
     * @param indicadorPesquisaCet
     */
    public void setIndicadorPesquisaCet(boolean indicadorPesquisaCet) {
        this.indicadorPesquisaCet = indicadorPesquisaCet;
    }


    /**
     * Gets the loginConsig value for this ParametrosSimulacaoPrestacao.
     * 
     * @return loginConsig
     */
    public java.lang.String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this ParametrosSimulacaoPrestacao.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(java.lang.String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the loja value for this ParametrosSimulacaoPrestacao.
     * 
     * @return loja
     */
    public java.lang.Integer getLoja() {
        return loja;
    }


    /**
     * Sets the loja value for this ParametrosSimulacaoPrestacao.
     * 
     * @param loja
     */
    public void setLoja(java.lang.Integer loja) {
        this.loja = loja;
    }


    /**
     * Gets the margem value for this ParametrosSimulacaoPrestacao.
     * 
     * @return margem
     */
    public double getMargem() {
        return margem;
    }


    /**
     * Sets the margem value for this ParametrosSimulacaoPrestacao.
     * 
     * @param margem
     */
    public void setMargem(double margem) {
        this.margem = margem;
    }


    /**
     * Gets the modoPagamentoIof value for this ParametrosSimulacaoPrestacao.
     * 
     * @return modoPagamentoIof
     */
    public java.lang.String getModoPagamentoIof() {
        return modoPagamentoIof;
    }


    /**
     * Sets the modoPagamentoIof value for this ParametrosSimulacaoPrestacao.
     * 
     * @param modoPagamentoIof
     */
    public void setModoPagamentoIof(java.lang.String modoPagamentoIof) {
        this.modoPagamentoIof = modoPagamentoIof;
    }


    /**
     * Gets the modoPagamentoTac value for this ParametrosSimulacaoPrestacao.
     * 
     * @return modoPagamentoTac
     */
    public java.lang.String getModoPagamentoTac() {
        return modoPagamentoTac;
    }


    /**
     * Sets the modoPagamentoTac value for this ParametrosSimulacaoPrestacao.
     * 
     * @param modoPagamentoTac
     */
    public void setModoPagamentoTac(java.lang.String modoPagamentoTac) {
        this.modoPagamentoTac = modoPagamentoTac;
    }


    /**
     * Gets the modoPagamentoTlf value for this ParametrosSimulacaoPrestacao.
     * 
     * @return modoPagamentoTlf
     */
    public java.lang.String getModoPagamentoTlf() {
        return modoPagamentoTlf;
    }


    /**
     * Sets the modoPagamentoTlf value for this ParametrosSimulacaoPrestacao.
     * 
     * @param modoPagamentoTlf
     */
    public void setModoPagamentoTlf(java.lang.String modoPagamentoTlf) {
        this.modoPagamentoTlf = modoPagamentoTlf;
    }


    /**
     * Gets the numeroAdesao value for this ParametrosSimulacaoPrestacao.
     * 
     * @return numeroAdesao
     */
    public java.lang.String getNumeroAdesao() {
        return numeroAdesao;
    }


    /**
     * Sets the numeroAdesao value for this ParametrosSimulacaoPrestacao.
     * 
     * @param numeroAdesao
     */
    public void setNumeroAdesao(java.lang.String numeroAdesao) {
        this.numeroAdesao = numeroAdesao;
    }


    /**
     * Gets the numeroContrato value for this ParametrosSimulacaoPrestacao.
     * 
     * @return numeroContrato
     */
    public java.lang.Integer getNumeroContrato() {
        return numeroContrato;
    }


    /**
     * Sets the numeroContrato value for this ParametrosSimulacaoPrestacao.
     * 
     * @param numeroContrato
     */
    public void setNumeroContrato(java.lang.Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
    }


    /**
     * Gets the quantidadePrestacoes value for this ParametrosSimulacaoPrestacao.
     * 
     * @return quantidadePrestacoes
     */
    public int getQuantidadePrestacoes() {
        return quantidadePrestacoes;
    }


    /**
     * Sets the quantidadePrestacoes value for this ParametrosSimulacaoPrestacao.
     * 
     * @param quantidadePrestacoes
     */
    public void setQuantidadePrestacoes(int quantidadePrestacoes) {
        this.quantidadePrestacoes = quantidadePrestacoes;
    }


    /**
     * Gets the senhaConsig value for this ParametrosSimulacaoPrestacao.
     * 
     * @return senhaConsig
     */
    public java.lang.String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this ParametrosSimulacaoPrestacao.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(java.lang.String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the sequencialOrgao value for this ParametrosSimulacaoPrestacao.
     * 
     * @return sequencialOrgao
     */
    public java.lang.Integer getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this ParametrosSimulacaoPrestacao.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(java.lang.Integer sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the servico value for this ParametrosSimulacaoPrestacao.
     * 
     * @return servico
     */
    public java.lang.String getServico() {
        return servico;
    }


    /**
     * Sets the servico value for this ParametrosSimulacaoPrestacao.
     * 
     * @param servico
     */
    public void setServico(java.lang.String servico) {
        this.servico = servico;
    }


    /**
     * Gets the taxaFormaCredito value for this ParametrosSimulacaoPrestacao.
     * 
     * @return taxaFormaCredito
     */
    public double getTaxaFormaCredito() {
        return taxaFormaCredito;
    }


    /**
     * Sets the taxaFormaCredito value for this ParametrosSimulacaoPrestacao.
     * 
     * @param taxaFormaCredito
     */
    public void setTaxaFormaCredito(double taxaFormaCredito) {
        this.taxaFormaCredito = taxaFormaCredito;
    }


    /**
     * Gets the tipoFormaEnvio value for this ParametrosSimulacaoPrestacao.
     * 
     * @return tipoFormaEnvio
     */
    public java.lang.Integer getTipoFormaEnvio() {
        return tipoFormaEnvio;
    }


    /**
     * Sets the tipoFormaEnvio value for this ParametrosSimulacaoPrestacao.
     * 
     * @param tipoFormaEnvio
     */
    public void setTipoFormaEnvio(java.lang.Integer tipoFormaEnvio) {
        this.tipoFormaEnvio = tipoFormaEnvio;
    }


    /**
     * Gets the unidadePagadora value for this ParametrosSimulacaoPrestacao.
     * 
     * @return unidadePagadora
     */
    public long getUnidadePagadora() {
        return unidadePagadora;
    }


    /**
     * Sets the unidadePagadora value for this ParametrosSimulacaoPrestacao.
     * 
     * @param unidadePagadora
     */
    public void setUnidadePagadora(long unidadePagadora) {
        this.unidadePagadora = unidadePagadora;
    }


    /**
     * Gets the valorLiberado value for this ParametrosSimulacaoPrestacao.
     * 
     * @return valorLiberado
     */
    public double getValorLiberado() {
        return valorLiberado;
    }


    /**
     * Sets the valorLiberado value for this ParametrosSimulacaoPrestacao.
     * 
     * @param valorLiberado
     */
    public void setValorLiberado(double valorLiberado) {
        this.valorLiberado = valorLiberado;
    }


    /**
     * Gets the valorPrestacao value for this ParametrosSimulacaoPrestacao.
     * 
     * @return valorPrestacao
     */
    public double getValorPrestacao() {
        return valorPrestacao;
    }


    /**
     * Sets the valorPrestacao value for this ParametrosSimulacaoPrestacao.
     * 
     * @param valorPrestacao
     */
    public void setValorPrestacao(double valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }


    /**
     * Gets the valorRenda value for this ParametrosSimulacaoPrestacao.
     * 
     * @return valorRenda
     */
    public double getValorRenda() {
        return valorRenda;
    }


    /**
     * Sets the valorRenda value for this ParametrosSimulacaoPrestacao.
     * 
     * @param valorRenda
     */
    public void setValorRenda(double valorRenda) {
        this.valorRenda = valorRenda;
    }


    /**
     * Gets the valorSaldoDevedor value for this ParametrosSimulacaoPrestacao.
     * 
     * @return valorSaldoDevedor
     */
    public java.lang.Double getValorSaldoDevedor() {
        return valorSaldoDevedor;
    }


    /**
     * Sets the valorSaldoDevedor value for this ParametrosSimulacaoPrestacao.
     * 
     * @param valorSaldoDevedor
     */
    public void setValorSaldoDevedor(java.lang.Double valorSaldoDevedor) {
        this.valorSaldoDevedor = valorSaldoDevedor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosSimulacaoPrestacao)) return false;
        ParametrosSimulacaoPrestacao other = (ParametrosSimulacaoPrestacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.associado == other.isAssociado() &&
            ((this.codigoEntidade==null && other.getCodigoEntidade()==null) || 
             (this.codigoEntidade!=null &&
              this.codigoEntidade.equals(other.getCodigoEntidade()))) &&
            ((this.codigoProduto==null && other.getCodigoProduto()==null) || 
             (this.codigoProduto!=null &&
              this.codigoProduto.equals(other.getCodigoProduto()))) &&
            ((this.contrato==null && other.getContrato()==null) || 
             (this.contrato!=null &&
              this.contrato.equals(other.getContrato()))) &&
            this.cpfAsNumber == other.getCpfAsNumber() &&
            ((this.dataAdmissao==null && other.getDataAdmissao()==null) || 
             (this.dataAdmissao!=null &&
              this.dataAdmissao.equals(other.getDataAdmissao()))) &&
            ((this.dataFator==null && other.getDataFator()==null) || 
             (this.dataFator!=null &&
              this.dataFator.equals(other.getDataFator()))) &&
            ((this.dataNascimento==null && other.getDataNascimento()==null) || 
             (this.dataNascimento!=null &&
              this.dataNascimento.equals(other.getDataNascimento()))) &&
            ((this.formaCredito==null && other.getFormaCredito()==null) || 
             (this.formaCredito!=null &&
              this.formaCredito.equals(other.getFormaCredito()))) &&
            this.indicadorApenasParcelamentoIdealMaximo == other.isIndicadorApenasParcelamentoIdealMaximo() &&
            this.indicadorPesquisaCet == other.isIndicadorPesquisaCet() &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.loja==null && other.getLoja()==null) || 
             (this.loja!=null &&
              this.loja.equals(other.getLoja()))) &&
            this.margem == other.getMargem() &&
            ((this.modoPagamentoIof==null && other.getModoPagamentoIof()==null) || 
             (this.modoPagamentoIof!=null &&
              this.modoPagamentoIof.equals(other.getModoPagamentoIof()))) &&
            ((this.modoPagamentoTac==null && other.getModoPagamentoTac()==null) || 
             (this.modoPagamentoTac!=null &&
              this.modoPagamentoTac.equals(other.getModoPagamentoTac()))) &&
            ((this.modoPagamentoTlf==null && other.getModoPagamentoTlf()==null) || 
             (this.modoPagamentoTlf!=null &&
              this.modoPagamentoTlf.equals(other.getModoPagamentoTlf()))) &&
            ((this.numeroAdesao==null && other.getNumeroAdesao()==null) || 
             (this.numeroAdesao!=null &&
              this.numeroAdesao.equals(other.getNumeroAdesao()))) &&
            ((this.numeroContrato==null && other.getNumeroContrato()==null) || 
             (this.numeroContrato!=null &&
              this.numeroContrato.equals(other.getNumeroContrato()))) &&
            this.quantidadePrestacoes == other.getQuantidadePrestacoes() &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig()))) &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao()))) &&
            ((this.servico==null && other.getServico()==null) || 
             (this.servico!=null &&
              this.servico.equals(other.getServico()))) &&
            this.taxaFormaCredito == other.getTaxaFormaCredito() &&
            ((this.tipoFormaEnvio==null && other.getTipoFormaEnvio()==null) || 
             (this.tipoFormaEnvio!=null &&
              this.tipoFormaEnvio.equals(other.getTipoFormaEnvio()))) &&
            this.unidadePagadora == other.getUnidadePagadora() &&
            this.valorLiberado == other.getValorLiberado() &&
            this.valorPrestacao == other.getValorPrestacao() &&
            this.valorRenda == other.getValorRenda() &&
            ((this.valorSaldoDevedor==null && other.getValorSaldoDevedor()==null) || 
             (this.valorSaldoDevedor!=null &&
              this.valorSaldoDevedor.equals(other.getValorSaldoDevedor())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += (isAssociado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCodigoEntidade() != null) {
            _hashCode += getCodigoEntidade().hashCode();
        }
        if (getCodigoProduto() != null) {
            _hashCode += getCodigoProduto().hashCode();
        }
        if (getContrato() != null) {
            _hashCode += getContrato().hashCode();
        }
        _hashCode += new Long(getCpfAsNumber()).hashCode();
        if (getDataAdmissao() != null) {
            _hashCode += getDataAdmissao().hashCode();
        }
        if (getDataFator() != null) {
            _hashCode += getDataFator().hashCode();
        }
        if (getDataNascimento() != null) {
            _hashCode += getDataNascimento().hashCode();
        }
        if (getFormaCredito() != null) {
            _hashCode += getFormaCredito().hashCode();
        }
        _hashCode += (isIndicadorApenasParcelamentoIdealMaximo() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIndicadorPesquisaCet() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getLoja() != null) {
            _hashCode += getLoja().hashCode();
        }
        _hashCode += new Double(getMargem()).hashCode();
        if (getModoPagamentoIof() != null) {
            _hashCode += getModoPagamentoIof().hashCode();
        }
        if (getModoPagamentoTac() != null) {
            _hashCode += getModoPagamentoTac().hashCode();
        }
        if (getModoPagamentoTlf() != null) {
            _hashCode += getModoPagamentoTlf().hashCode();
        }
        if (getNumeroAdesao() != null) {
            _hashCode += getNumeroAdesao().hashCode();
        }
        if (getNumeroContrato() != null) {
            _hashCode += getNumeroContrato().hashCode();
        }
        _hashCode += getQuantidadePrestacoes();
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        if (getServico() != null) {
            _hashCode += getServico().hashCode();
        }
        _hashCode += new Double(getTaxaFormaCredito()).hashCode();
        if (getTipoFormaEnvio() != null) {
            _hashCode += getTipoFormaEnvio().hashCode();
        }
        _hashCode += new Long(getUnidadePagadora()).hashCode();
        _hashCode += new Double(getValorLiberado()).hashCode();
        _hashCode += new Double(getValorPrestacao()).hashCode();
        _hashCode += new Double(getValorRenda()).hashCode();
        if (getValorSaldoDevedor() != null) {
            _hashCode += getValorSaldoDevedor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosSimulacaoPrestacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametrosSimulacaoPrestacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "associado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoPortabilidade"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfAsNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfAsNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAdmissao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataAdmissao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataFator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataNascimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formaCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formaCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorApenasParcelamentoIdealMaximo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorApenasParcelamentoIdealMaximo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorPesquisaCet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorPesquisaCet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("margem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "margem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modoPagamentoIof");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modoPagamentoIof"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modoPagamentoTac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modoPagamentoTac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modoPagamentoTlf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modoPagamentoTlf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAdesao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroAdesao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadePrestacoes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadePrestacoes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senhaConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senhaConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialOrgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxaFormaCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxaFormaCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFormaEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoFormaEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadePagadora");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadePagadora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorLiberado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorLiberado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrestacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrestacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorRenda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorRenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaldoDevedor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaldoDevedor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
