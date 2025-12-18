/**
 * BoletoParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class BoletoParameter  extends com.proativaservicos.service.asynchronous.bmg.refinanciamento.AbstractWebServicesParameter  implements java.io.Serializable {
    private java.lang.String cedente;

    private java.util.Calendar dataVencimento;

    private java.lang.String descricaoPagamento;

    private java.lang.String linhaDigitavel;

    private java.lang.String sacado;

    private java.lang.String tipoPagamento;

    private java.lang.Double valorPagamento;

    public BoletoParameter() {
    }

    public BoletoParameter(
           java.lang.String cedente,
           java.util.Calendar dataVencimento,
           java.lang.String descricaoPagamento,
           java.lang.String linhaDigitavel,
           java.lang.String sacado,
           java.lang.String tipoPagamento,
           java.lang.Double valorPagamento) {
        this.cedente = cedente;
        this.dataVencimento = dataVencimento;
        this.descricaoPagamento = descricaoPagamento;
        this.linhaDigitavel = linhaDigitavel;
        this.sacado = sacado;
        this.tipoPagamento = tipoPagamento;
        this.valorPagamento = valorPagamento;
    }


    /**
     * Gets the cedente value for this BoletoParameter.
     * 
     * @return cedente
     */
    public java.lang.String getCedente() {
        return cedente;
    }


    /**
     * Sets the cedente value for this BoletoParameter.
     * 
     * @param cedente
     */
    public void setCedente(java.lang.String cedente) {
        this.cedente = cedente;
    }


    /**
     * Gets the dataVencimento value for this BoletoParameter.
     * 
     * @return dataVencimento
     */
    public java.util.Calendar getDataVencimento() {
        return dataVencimento;
    }


    /**
     * Sets the dataVencimento value for this BoletoParameter.
     * 
     * @param dataVencimento
     */
    public void setDataVencimento(java.util.Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }


    /**
     * Gets the descricaoPagamento value for this BoletoParameter.
     * 
     * @return descricaoPagamento
     */
    public java.lang.String getDescricaoPagamento() {
        return descricaoPagamento;
    }


    /**
     * Sets the descricaoPagamento value for this BoletoParameter.
     * 
     * @param descricaoPagamento
     */
    public void setDescricaoPagamento(java.lang.String descricaoPagamento) {
        this.descricaoPagamento = descricaoPagamento;
    }


    /**
     * Gets the linhaDigitavel value for this BoletoParameter.
     * 
     * @return linhaDigitavel
     */
    public java.lang.String getLinhaDigitavel() {
        return linhaDigitavel;
    }


    /**
     * Sets the linhaDigitavel value for this BoletoParameter.
     * 
     * @param linhaDigitavel
     */
    public void setLinhaDigitavel(java.lang.String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }


    /**
     * Gets the sacado value for this BoletoParameter.
     * 
     * @return sacado
     */
    public java.lang.String getSacado() {
        return sacado;
    }


    /**
     * Sets the sacado value for this BoletoParameter.
     * 
     * @param sacado
     */
    public void setSacado(java.lang.String sacado) {
        this.sacado = sacado;
    }


    /**
     * Gets the tipoPagamento value for this BoletoParameter.
     * 
     * @return tipoPagamento
     */
    public java.lang.String getTipoPagamento() {
        return tipoPagamento;
    }


    /**
     * Sets the tipoPagamento value for this BoletoParameter.
     * 
     * @param tipoPagamento
     */
    public void setTipoPagamento(java.lang.String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }


    /**
     * Gets the valorPagamento value for this BoletoParameter.
     * 
     * @return valorPagamento
     */
    public java.lang.Double getValorPagamento() {
        return valorPagamento;
    }


    /**
     * Sets the valorPagamento value for this BoletoParameter.
     * 
     * @param valorPagamento
     */
    public void setValorPagamento(java.lang.Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BoletoParameter)) return false;
        BoletoParameter other = (BoletoParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.cedente==null && other.getCedente()==null) || 
             (this.cedente!=null &&
              this.cedente.equals(other.getCedente()))) &&
            ((this.dataVencimento==null && other.getDataVencimento()==null) || 
             (this.dataVencimento!=null &&
              this.dataVencimento.equals(other.getDataVencimento()))) &&
            ((this.descricaoPagamento==null && other.getDescricaoPagamento()==null) || 
             (this.descricaoPagamento!=null &&
              this.descricaoPagamento.equals(other.getDescricaoPagamento()))) &&
            ((this.linhaDigitavel==null && other.getLinhaDigitavel()==null) || 
             (this.linhaDigitavel!=null &&
              this.linhaDigitavel.equals(other.getLinhaDigitavel()))) &&
            ((this.sacado==null && other.getSacado()==null) || 
             (this.sacado!=null &&
              this.sacado.equals(other.getSacado()))) &&
            ((this.tipoPagamento==null && other.getTipoPagamento()==null) || 
             (this.tipoPagamento!=null &&
              this.tipoPagamento.equals(other.getTipoPagamento()))) &&
            ((this.valorPagamento==null && other.getValorPagamento()==null) || 
             (this.valorPagamento!=null &&
              this.valorPagamento.equals(other.getValorPagamento())));
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
        if (getCedente() != null) {
            _hashCode += getCedente().hashCode();
        }
        if (getDataVencimento() != null) {
            _hashCode += getDataVencimento().hashCode();
        }
        if (getDescricaoPagamento() != null) {
            _hashCode += getDescricaoPagamento().hashCode();
        }
        if (getLinhaDigitavel() != null) {
            _hashCode += getLinhaDigitavel().hashCode();
        }
        if (getSacado() != null) {
            _hashCode += getSacado().hashCode();
        }
        if (getTipoPagamento() != null) {
            _hashCode += getTipoPagamento().hashCode();
        }
        if (getValorPagamento() != null) {
            _hashCode += getValorPagamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BoletoParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "BoletoParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataVencimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataVencimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricaoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linhaDigitavel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "linhaDigitavel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sacado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sacado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
