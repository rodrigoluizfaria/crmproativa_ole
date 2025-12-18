/**
 * CetParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

public class CetParameter  extends com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.WebServiceParameter  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int codigoEntidade;

    private int codigoFormaCredito;

    private java.lang.Integer codigoSaldoRetido;

    private com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Contrato contrato;

    private long cpfAsNumber;

    private java.lang.String criterioFormaPagamento;

    private java.lang.String criterioIof;

    private java.lang.String criterioTac;

    private java.util.Calendar dataAdmissao;

    private java.util.Calendar dataNascimento;

    private java.util.Calendar dataProposta;

    private java.lang.String loginConsig;

    private int loja;

    private java.lang.String orgao;

    private int quantidadePrestacoes;

    private java.lang.Double saldoDevedor;

    private java.lang.String senhaConsig;

    private int sequencialOrgao;

    private java.lang.String servico;

    private boolean sindicalizado;

    private int tabelaFator;

    private double taxaFormaCredito;

    private int tipoFormaEnvio;

    private long unidadePagadora;

    private double valorLiberado;

    private double valorPrestacao;

    private double valorRenda;

    public CetParameter() {
    }

    public CetParameter(
           java.lang.String login,
           java.lang.String senha,
           int codigoEntidade,
           int codigoFormaCredito,
           java.lang.Integer codigoSaldoRetido,
           com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Contrato contrato,
           long cpfAsNumber,
           java.lang.String criterioFormaPagamento,
           java.lang.String criterioIof,
           java.lang.String criterioTac,
           java.util.Calendar dataAdmissao,
           java.util.Calendar dataNascimento,
           java.util.Calendar dataProposta,
           java.lang.String loginConsig,
           int loja,
           java.lang.String orgao,
           int quantidadePrestacoes,
           java.lang.Double saldoDevedor,
           java.lang.String senhaConsig,
           int sequencialOrgao,
           java.lang.String servico,
           boolean sindicalizado,
           int tabelaFator,
           double taxaFormaCredito,
           int tipoFormaEnvio,
           long unidadePagadora,
           double valorLiberado,
           double valorPrestacao,
           double valorRenda) {
        super(
            login,
            senha);
        this.codigoEntidade = codigoEntidade;
        this.codigoFormaCredito = codigoFormaCredito;
        this.codigoSaldoRetido = codigoSaldoRetido;
        this.contrato = contrato;
        this.cpfAsNumber = cpfAsNumber;
        this.criterioFormaPagamento = criterioFormaPagamento;
        this.criterioIof = criterioIof;
        this.criterioTac = criterioTac;
        this.dataAdmissao = dataAdmissao;
        this.dataNascimento = dataNascimento;
        this.dataProposta = dataProposta;
        this.loginConsig = loginConsig;
        this.loja = loja;
        this.orgao = orgao;
        this.quantidadePrestacoes = quantidadePrestacoes;
        this.saldoDevedor = saldoDevedor;
        this.senhaConsig = senhaConsig;
        this.sequencialOrgao = sequencialOrgao;
        this.servico = servico;
        this.sindicalizado = sindicalizado;
        this.tabelaFator = tabelaFator;
        this.taxaFormaCredito = taxaFormaCredito;
        this.tipoFormaEnvio = tipoFormaEnvio;
        this.unidadePagadora = unidadePagadora;
        this.valorLiberado = valorLiberado;
        this.valorPrestacao = valorPrestacao;
        this.valorRenda = valorRenda;
    }


    /**
     * Gets the codigoEntidade value for this CetParameter.
     * 
     * @return codigoEntidade
     */
    public int getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this CetParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(int codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoFormaCredito value for this CetParameter.
     * 
     * @return codigoFormaCredito
     */
    public int getCodigoFormaCredito() {
        return codigoFormaCredito;
    }


    /**
     * Sets the codigoFormaCredito value for this CetParameter.
     * 
     * @param codigoFormaCredito
     */
    public void setCodigoFormaCredito(int codigoFormaCredito) {
        this.codigoFormaCredito = codigoFormaCredito;
    }


    /**
     * Gets the codigoSaldoRetido value for this CetParameter.
     * 
     * @return codigoSaldoRetido
     */
    public java.lang.Integer getCodigoSaldoRetido() {
        return codigoSaldoRetido;
    }


    /**
     * Sets the codigoSaldoRetido value for this CetParameter.
     * 
     * @param codigoSaldoRetido
     */
    public void setCodigoSaldoRetido(java.lang.Integer codigoSaldoRetido) {
        this.codigoSaldoRetido = codigoSaldoRetido;
    }


    /**
     * Gets the contrato value for this CetParameter.
     * 
     * @return contrato
     */
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Contrato getContrato() {
        return contrato;
    }


    /**
     * Sets the contrato value for this CetParameter.
     * 
     * @param contrato
     */
    public void setContrato(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Contrato contrato) {
        this.contrato = contrato;
    }


    /**
     * Gets the cpfAsNumber value for this CetParameter.
     * 
     * @return cpfAsNumber
     */
    public long getCpfAsNumber() {
        return cpfAsNumber;
    }


    /**
     * Sets the cpfAsNumber value for this CetParameter.
     * 
     * @param cpfAsNumber
     */
    public void setCpfAsNumber(long cpfAsNumber) {
        this.cpfAsNumber = cpfAsNumber;
    }


    /**
     * Gets the criterioFormaPagamento value for this CetParameter.
     * 
     * @return criterioFormaPagamento
     */
    public java.lang.String getCriterioFormaPagamento() {
        return criterioFormaPagamento;
    }


    /**
     * Sets the criterioFormaPagamento value for this CetParameter.
     * 
     * @param criterioFormaPagamento
     */
    public void setCriterioFormaPagamento(java.lang.String criterioFormaPagamento) {
        this.criterioFormaPagamento = criterioFormaPagamento;
    }


    /**
     * Gets the criterioIof value for this CetParameter.
     * 
     * @return criterioIof
     */
    public java.lang.String getCriterioIof() {
        return criterioIof;
    }


    /**
     * Sets the criterioIof value for this CetParameter.
     * 
     * @param criterioIof
     */
    public void setCriterioIof(java.lang.String criterioIof) {
        this.criterioIof = criterioIof;
    }


    /**
     * Gets the criterioTac value for this CetParameter.
     * 
     * @return criterioTac
     */
    public java.lang.String getCriterioTac() {
        return criterioTac;
    }


    /**
     * Sets the criterioTac value for this CetParameter.
     * 
     * @param criterioTac
     */
    public void setCriterioTac(java.lang.String criterioTac) {
        this.criterioTac = criterioTac;
    }


    /**
     * Gets the dataAdmissao value for this CetParameter.
     * 
     * @return dataAdmissao
     */
    public java.util.Calendar getDataAdmissao() {
        return dataAdmissao;
    }


    /**
     * Sets the dataAdmissao value for this CetParameter.
     * 
     * @param dataAdmissao
     */
    public void setDataAdmissao(java.util.Calendar dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }


    /**
     * Gets the dataNascimento value for this CetParameter.
     * 
     * @return dataNascimento
     */
    public java.util.Calendar getDataNascimento() {
        return dataNascimento;
    }


    /**
     * Sets the dataNascimento value for this CetParameter.
     * 
     * @param dataNascimento
     */
    public void setDataNascimento(java.util.Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    /**
     * Gets the dataProposta value for this CetParameter.
     * 
     * @return dataProposta
     */
    public java.util.Calendar getDataProposta() {
        return dataProposta;
    }


    /**
     * Sets the dataProposta value for this CetParameter.
     * 
     * @param dataProposta
     */
    public void setDataProposta(java.util.Calendar dataProposta) {
        this.dataProposta = dataProposta;
    }


    /**
     * Gets the loginConsig value for this CetParameter.
     * 
     * @return loginConsig
     */
    public java.lang.String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this CetParameter.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(java.lang.String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the loja value for this CetParameter.
     * 
     * @return loja
     */
    public int getLoja() {
        return loja;
    }


    /**
     * Sets the loja value for this CetParameter.
     * 
     * @param loja
     */
    public void setLoja(int loja) {
        this.loja = loja;
    }


    /**
     * Gets the orgao value for this CetParameter.
     * 
     * @return orgao
     */
    public java.lang.String getOrgao() {
        return orgao;
    }


    /**
     * Sets the orgao value for this CetParameter.
     * 
     * @param orgao
     */
    public void setOrgao(java.lang.String orgao) {
        this.orgao = orgao;
    }


    /**
     * Gets the quantidadePrestacoes value for this CetParameter.
     * 
     * @return quantidadePrestacoes
     */
    public int getQuantidadePrestacoes() {
        return quantidadePrestacoes;
    }


    /**
     * Sets the quantidadePrestacoes value for this CetParameter.
     * 
     * @param quantidadePrestacoes
     */
    public void setQuantidadePrestacoes(int quantidadePrestacoes) {
        this.quantidadePrestacoes = quantidadePrestacoes;
    }


    /**
     * Gets the saldoDevedor value for this CetParameter.
     * 
     * @return saldoDevedor
     */
    public java.lang.Double getSaldoDevedor() {
        return saldoDevedor;
    }


    /**
     * Sets the saldoDevedor value for this CetParameter.
     * 
     * @param saldoDevedor
     */
    public void setSaldoDevedor(java.lang.Double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }


    /**
     * Gets the senhaConsig value for this CetParameter.
     * 
     * @return senhaConsig
     */
    public java.lang.String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this CetParameter.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(java.lang.String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the sequencialOrgao value for this CetParameter.
     * 
     * @return sequencialOrgao
     */
    public int getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this CetParameter.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(int sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the servico value for this CetParameter.
     * 
     * @return servico
     */
    public java.lang.String getServico() {
        return servico;
    }


    /**
     * Sets the servico value for this CetParameter.
     * 
     * @param servico
     */
    public void setServico(java.lang.String servico) {
        this.servico = servico;
    }


    /**
     * Gets the sindicalizado value for this CetParameter.
     * 
     * @return sindicalizado
     */
    public boolean isSindicalizado() {
        return sindicalizado;
    }


    /**
     * Sets the sindicalizado value for this CetParameter.
     * 
     * @param sindicalizado
     */
    public void setSindicalizado(boolean sindicalizado) {
        this.sindicalizado = sindicalizado;
    }


    /**
     * Gets the tabelaFator value for this CetParameter.
     * 
     * @return tabelaFator
     */
    public int getTabelaFator() {
        return tabelaFator;
    }


    /**
     * Sets the tabelaFator value for this CetParameter.
     * 
     * @param tabelaFator
     */
    public void setTabelaFator(int tabelaFator) {
        this.tabelaFator = tabelaFator;
    }


    /**
     * Gets the taxaFormaCredito value for this CetParameter.
     * 
     * @return taxaFormaCredito
     */
    public double getTaxaFormaCredito() {
        return taxaFormaCredito;
    }


    /**
     * Sets the taxaFormaCredito value for this CetParameter.
     * 
     * @param taxaFormaCredito
     */
    public void setTaxaFormaCredito(double taxaFormaCredito) {
        this.taxaFormaCredito = taxaFormaCredito;
    }


    /**
     * Gets the tipoFormaEnvio value for this CetParameter.
     * 
     * @return tipoFormaEnvio
     */
    public int getTipoFormaEnvio() {
        return tipoFormaEnvio;
    }


    /**
     * Sets the tipoFormaEnvio value for this CetParameter.
     * 
     * @param tipoFormaEnvio
     */
    public void setTipoFormaEnvio(int tipoFormaEnvio) {
        this.tipoFormaEnvio = tipoFormaEnvio;
    }


    /**
     * Gets the unidadePagadora value for this CetParameter.
     * 
     * @return unidadePagadora
     */
    public long getUnidadePagadora() {
        return unidadePagadora;
    }


    /**
     * Sets the unidadePagadora value for this CetParameter.
     * 
     * @param unidadePagadora
     */
    public void setUnidadePagadora(long unidadePagadora) {
        this.unidadePagadora = unidadePagadora;
    }


    /**
     * Gets the valorLiberado value for this CetParameter.
     * 
     * @return valorLiberado
     */
    public double getValorLiberado() {
        return valorLiberado;
    }


    /**
     * Sets the valorLiberado value for this CetParameter.
     * 
     * @param valorLiberado
     */
    public void setValorLiberado(double valorLiberado) {
        this.valorLiberado = valorLiberado;
    }


    /**
     * Gets the valorPrestacao value for this CetParameter.
     * 
     * @return valorPrestacao
     */
    public double getValorPrestacao() {
        return valorPrestacao;
    }


    /**
     * Sets the valorPrestacao value for this CetParameter.
     * 
     * @param valorPrestacao
     */
    public void setValorPrestacao(double valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }


    /**
     * Gets the valorRenda value for this CetParameter.
     * 
     * @return valorRenda
     */
    public double getValorRenda() {
        return valorRenda;
    }


    /**
     * Sets the valorRenda value for this CetParameter.
     * 
     * @param valorRenda
     */
    public void setValorRenda(double valorRenda) {
        this.valorRenda = valorRenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CetParameter)) return false;
        CetParameter other = (CetParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.codigoEntidade == other.getCodigoEntidade() &&
            this.codigoFormaCredito == other.getCodigoFormaCredito() &&
            ((this.codigoSaldoRetido==null && other.getCodigoSaldoRetido()==null) || 
             (this.codigoSaldoRetido!=null &&
              this.codigoSaldoRetido.equals(other.getCodigoSaldoRetido()))) &&
            ((this.contrato==null && other.getContrato()==null) || 
             (this.contrato!=null &&
              this.contrato.equals(other.getContrato()))) &&
            this.cpfAsNumber == other.getCpfAsNumber() &&
            ((this.criterioFormaPagamento==null && other.getCriterioFormaPagamento()==null) || 
             (this.criterioFormaPagamento!=null &&
              this.criterioFormaPagamento.equals(other.getCriterioFormaPagamento()))) &&
            ((this.criterioIof==null && other.getCriterioIof()==null) || 
             (this.criterioIof!=null &&
              this.criterioIof.equals(other.getCriterioIof()))) &&
            ((this.criterioTac==null && other.getCriterioTac()==null) || 
             (this.criterioTac!=null &&
              this.criterioTac.equals(other.getCriterioTac()))) &&
            ((this.dataAdmissao==null && other.getDataAdmissao()==null) || 
             (this.dataAdmissao!=null &&
              this.dataAdmissao.equals(other.getDataAdmissao()))) &&
            ((this.dataNascimento==null && other.getDataNascimento()==null) || 
             (this.dataNascimento!=null &&
              this.dataNascimento.equals(other.getDataNascimento()))) &&
            ((this.dataProposta==null && other.getDataProposta()==null) || 
             (this.dataProposta!=null &&
              this.dataProposta.equals(other.getDataProposta()))) &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            this.loja == other.getLoja() &&
            ((this.orgao==null && other.getOrgao()==null) || 
             (this.orgao!=null &&
              this.orgao.equals(other.getOrgao()))) &&
            this.quantidadePrestacoes == other.getQuantidadePrestacoes() &&
            ((this.saldoDevedor==null && other.getSaldoDevedor()==null) || 
             (this.saldoDevedor!=null &&
              this.saldoDevedor.equals(other.getSaldoDevedor()))) &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig()))) &&
            this.sequencialOrgao == other.getSequencialOrgao() &&
            ((this.servico==null && other.getServico()==null) || 
             (this.servico!=null &&
              this.servico.equals(other.getServico()))) &&
            this.sindicalizado == other.isSindicalizado() &&
            this.tabelaFator == other.getTabelaFator() &&
            this.taxaFormaCredito == other.getTaxaFormaCredito() &&
            this.tipoFormaEnvio == other.getTipoFormaEnvio() &&
            this.unidadePagadora == other.getUnidadePagadora() &&
            this.valorLiberado == other.getValorLiberado() &&
            this.valorPrestacao == other.getValorPrestacao() &&
            this.valorRenda == other.getValorRenda();
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
        _hashCode += getCodigoEntidade();
        _hashCode += getCodigoFormaCredito();
        if (getCodigoSaldoRetido() != null) {
            _hashCode += getCodigoSaldoRetido().hashCode();
        }
        if (getContrato() != null) {
            _hashCode += getContrato().hashCode();
        }
        _hashCode += new Long(getCpfAsNumber()).hashCode();
        if (getCriterioFormaPagamento() != null) {
            _hashCode += getCriterioFormaPagamento().hashCode();
        }
        if (getCriterioIof() != null) {
            _hashCode += getCriterioIof().hashCode();
        }
        if (getCriterioTac() != null) {
            _hashCode += getCriterioTac().hashCode();
        }
        if (getDataAdmissao() != null) {
            _hashCode += getDataAdmissao().hashCode();
        }
        if (getDataNascimento() != null) {
            _hashCode += getDataNascimento().hashCode();
        }
        if (getDataProposta() != null) {
            _hashCode += getDataProposta().hashCode();
        }
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        _hashCode += getLoja();
        if (getOrgao() != null) {
            _hashCode += getOrgao().hashCode();
        }
        _hashCode += getQuantidadePrestacoes();
        if (getSaldoDevedor() != null) {
            _hashCode += getSaldoDevedor().hashCode();
        }
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        _hashCode += getSequencialOrgao();
        if (getServico() != null) {
            _hashCode += getServico().hashCode();
        }
        _hashCode += (isSindicalizado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getTabelaFator();
        _hashCode += new Double(getTaxaFormaCredito()).hashCode();
        _hashCode += getTipoFormaEnvio();
        _hashCode += new Long(getUnidadePagadora()).hashCode();
        _hashCode += new Double(getValorLiberado()).hashCode();
        _hashCode += new Double(getValorPrestacao()).hashCode();
        _hashCode += new Double(getValorRenda()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CetParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://parameter.cet.econsig.bmg.com", "CetParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoFormaCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoFormaCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSaldoRetido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSaldoRetido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfAsNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfAsNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("criterioFormaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "criterioFormaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("criterioIof");
        elemField.setXmlName(new javax.xml.namespace.QName("", "criterioIof"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("criterioTac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "criterioTac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAdmissao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataAdmissao"));
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
        elemField.setFieldName("dataProposta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataProposta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadePrestacoes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadePrestacoes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saldoDevedor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "saldoDevedor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sindicalizado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sindicalizado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tabelaFator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tabelaFator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
        elemField.setNillable(false);
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
