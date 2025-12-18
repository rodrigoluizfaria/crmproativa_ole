/**
 * CancelamentoProdutoSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class CancelamentoProdutoSeguroParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private java.util.Calendar data;

    private String justificativa;

    private String numeroAde;

    private Integer tipoProdutoSeguro;

    public CancelamentoProdutoSeguroParameter() {
    }

    public CancelamentoProdutoSeguroParameter(
           String login,
           String senha,
           java.util.Calendar data,
           String justificativa,
           String numeroAde,
           Integer tipoProdutoSeguro) {
        super(
            login,
            senha);
        this.data = data;
        this.justificativa = justificativa;
        this.numeroAde = numeroAde;
        this.tipoProdutoSeguro = tipoProdutoSeguro;
    }


    /**
     * Gets the data value for this CancelamentoProdutoSeguroParameter.
     * 
     * @return data
     */
    public java.util.Calendar getData() {
        return data;
    }


    /**
     * Sets the data value for this CancelamentoProdutoSeguroParameter.
     * 
     * @param data
     */
    public void setData(java.util.Calendar data) {
        this.data = data;
    }


    /**
     * Gets the justificativa value for this CancelamentoProdutoSeguroParameter.
     * 
     * @return justificativa
     */
    public String getJustificativa() {
        return justificativa;
    }


    /**
     * Sets the justificativa value for this CancelamentoProdutoSeguroParameter.
     * 
     * @param justificativa
     */
    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }


    /**
     * Gets the numeroAde value for this CancelamentoProdutoSeguroParameter.
     * 
     * @return numeroAde
     */
    public String getNumeroAde() {
        return numeroAde;
    }


    /**
     * Sets the numeroAde value for this CancelamentoProdutoSeguroParameter.
     * 
     * @param numeroAde
     */
    public void setNumeroAde(String numeroAde) {
        this.numeroAde = numeroAde;
    }


    /**
     * Gets the tipoProdutoSeguro value for this CancelamentoProdutoSeguroParameter.
     * 
     * @return tipoProdutoSeguro
     */
    public Integer getTipoProdutoSeguro() {
        return tipoProdutoSeguro;
    }


    /**
     * Sets the tipoProdutoSeguro value for this CancelamentoProdutoSeguroParameter.
     * 
     * @param tipoProdutoSeguro
     */
    public void setTipoProdutoSeguro(Integer tipoProdutoSeguro) {
        this.tipoProdutoSeguro = tipoProdutoSeguro;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CancelamentoProdutoSeguroParameter)) return false;
        CancelamentoProdutoSeguroParameter other = (CancelamentoProdutoSeguroParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              this.data.equals(other.getData()))) &&
            ((this.justificativa==null && other.getJustificativa()==null) || 
             (this.justificativa!=null &&
              this.justificativa.equals(other.getJustificativa()))) &&
            ((this.numeroAde==null && other.getNumeroAde()==null) || 
             (this.numeroAde!=null &&
              this.numeroAde.equals(other.getNumeroAde()))) &&
            ((this.tipoProdutoSeguro==null && other.getTipoProdutoSeguro()==null) || 
             (this.tipoProdutoSeguro!=null &&
              this.tipoProdutoSeguro.equals(other.getTipoProdutoSeguro())));
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
        if (getData() != null) {
            _hashCode += getData().hashCode();
        }
        if (getJustificativa() != null) {
            _hashCode += getJustificativa().hashCode();
        }
        if (getNumeroAde() != null) {
            _hashCode += getNumeroAde().hashCode();
        }
        if (getTipoProdutoSeguro() != null) {
            _hashCode += getTipoProdutoSeguro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelamentoProdutoSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoProdutoSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("justificativa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "justificativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroAde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoProdutoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoProdutoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
