/**
 * ContratoPortabilidade.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoPortabilidade;

public class ContratoPortabilidade  extends com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.AbstractWebServicesParameter  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.BancoParameter banco;

    private java.lang.String numeroContrato;

    private java.lang.Integer produtoPortabilidade;

    private int quantidadeParcelasAberto;

    private int quantidadeParcelasOriginal;

    private double saldoDevedor;

    private double valorParcela;

    public ContratoPortabilidade() {
    }

    public ContratoPortabilidade(
           com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.BancoParameter banco,
           java.lang.String numeroContrato,
           java.lang.Integer produtoPortabilidade,
           int quantidadeParcelasAberto,
           int quantidadeParcelasOriginal,
           double saldoDevedor,
           double valorParcela) {
        this.banco = banco;
        this.numeroContrato = numeroContrato;
        this.produtoPortabilidade = produtoPortabilidade;
        this.quantidadeParcelasAberto = quantidadeParcelasAberto;
        this.quantidadeParcelasOriginal = quantidadeParcelasOriginal;
        this.saldoDevedor = saldoDevedor;
        this.valorParcela = valorParcela;
    }


    /**
     * Gets the banco value for this ContratoPortabilidade.
     * 
     * @return banco
     */
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.BancoParameter getBanco() {
        return banco;
    }


    /**
     * Sets the banco value for this ContratoPortabilidade.
     * 
     * @param banco
     */
    public void setBanco(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.BancoParameter banco) {
        this.banco = banco;
    }


    /**
     * Gets the numeroContrato value for this ContratoPortabilidade.
     * 
     * @return numeroContrato
     */
    public java.lang.String getNumeroContrato() {
        return numeroContrato;
    }


    /**
     * Sets the numeroContrato value for this ContratoPortabilidade.
     * 
     * @param numeroContrato
     */
    public void setNumeroContrato(java.lang.String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }


    /**
     * Gets the produtoPortabilidade value for this ContratoPortabilidade.
     * 
     * @return produtoPortabilidade
     */
    public java.lang.Integer getProdutoPortabilidade() {
        return produtoPortabilidade;
    }


    /**
     * Sets the produtoPortabilidade value for this ContratoPortabilidade.
     * 
     * @param produtoPortabilidade
     */
    public void setProdutoPortabilidade(java.lang.Integer produtoPortabilidade) {
        this.produtoPortabilidade = produtoPortabilidade;
    }


    /**
     * Gets the quantidadeParcelasAberto value for this ContratoPortabilidade.
     * 
     * @return quantidadeParcelasAberto
     */
    public int getQuantidadeParcelasAberto() {
        return quantidadeParcelasAberto;
    }


    /**
     * Sets the quantidadeParcelasAberto value for this ContratoPortabilidade.
     * 
     * @param quantidadeParcelasAberto
     */
    public void setQuantidadeParcelasAberto(int quantidadeParcelasAberto) {
        this.quantidadeParcelasAberto = quantidadeParcelasAberto;
    }


    /**
     * Gets the quantidadeParcelasOriginal value for this ContratoPortabilidade.
     * 
     * @return quantidadeParcelasOriginal
     */
    public int getQuantidadeParcelasOriginal() {
        return quantidadeParcelasOriginal;
    }


    /**
     * Sets the quantidadeParcelasOriginal value for this ContratoPortabilidade.
     * 
     * @param quantidadeParcelasOriginal
     */
    public void setQuantidadeParcelasOriginal(int quantidadeParcelasOriginal) {
        this.quantidadeParcelasOriginal = quantidadeParcelasOriginal;
    }


    /**
     * Gets the saldoDevedor value for this ContratoPortabilidade.
     * 
     * @return saldoDevedor
     */
    public double getSaldoDevedor() {
        return saldoDevedor;
    }


    /**
     * Sets the saldoDevedor value for this ContratoPortabilidade.
     * 
     * @param saldoDevedor
     */
    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }


    /**
     * Gets the valorParcela value for this ContratoPortabilidade.
     * 
     * @return valorParcela
     */
    public double getValorParcela() {
        return valorParcela;
    }


    /**
     * Sets the valorParcela value for this ContratoPortabilidade.
     * 
     * @param valorParcela
     */
    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContratoPortabilidade)) return false;
        ContratoPortabilidade other = (ContratoPortabilidade) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.banco==null && other.getBanco()==null) || 
             (this.banco!=null &&
              this.banco.equals(other.getBanco()))) &&
            ((this.numeroContrato==null && other.getNumeroContrato()==null) || 
             (this.numeroContrato!=null &&
              this.numeroContrato.equals(other.getNumeroContrato()))) &&
            ((this.produtoPortabilidade==null && other.getProdutoPortabilidade()==null) || 
             (this.produtoPortabilidade!=null &&
              this.produtoPortabilidade.equals(other.getProdutoPortabilidade()))) &&
            this.quantidadeParcelasAberto == other.getQuantidadeParcelasAberto() &&
            this.quantidadeParcelasOriginal == other.getQuantidadeParcelasOriginal() &&
            this.saldoDevedor == other.getSaldoDevedor() &&
            this.valorParcela == other.getValorParcela();
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
        if (getBanco() != null) {
            _hashCode += getBanco().hashCode();
        }
        if (getNumeroContrato() != null) {
            _hashCode += getNumeroContrato().hashCode();
        }
        if (getProdutoPortabilidade() != null) {
            _hashCode += getProdutoPortabilidade().hashCode();
        }
        _hashCode += getQuantidadeParcelasAberto();
        _hashCode += getQuantidadeParcelasOriginal();
        _hashCode += new Double(getSaldoDevedor()).hashCode();
        _hashCode += new Double(getValorParcela()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContratoPortabilidade.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoPortabilidade"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("banco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "banco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BancoParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("produtoPortabilidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "produtoPortabilidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeParcelasAberto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeParcelasAberto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeParcelasOriginal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeParcelasOriginal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saldoDevedor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "saldoDevedor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorParcela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorParcela"));
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
