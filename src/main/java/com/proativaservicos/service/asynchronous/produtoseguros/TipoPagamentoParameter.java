/**
 * TipoPagamentoParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class TipoPagamentoParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private String codigoOrgao;

    private Integer codigoSeguro;

    private String entidade;

    public TipoPagamentoParameter() {
    }

    public TipoPagamentoParameter(
           String login,
           String senha,
           String codigoOrgao,
           Integer codigoSeguro,
           String entidade) {
        super(
            login,
            senha);
        this.codigoOrgao = codigoOrgao;
        this.codigoSeguro = codigoSeguro;
        this.entidade = entidade;
    }


    /**
     * Gets the codigoOrgao value for this TipoPagamentoParameter.
     * 
     * @return codigoOrgao
     */
    public String getCodigoOrgao() {
        return codigoOrgao;
    }


    /**
     * Sets the codigoOrgao value for this TipoPagamentoParameter.
     * 
     * @param codigoOrgao
     */
    public void setCodigoOrgao(String codigoOrgao) {
        this.codigoOrgao = codigoOrgao;
    }


    /**
     * Gets the codigoSeguro value for this TipoPagamentoParameter.
     * 
     * @return codigoSeguro
     */
    public Integer getCodigoSeguro() {
        return codigoSeguro;
    }


    /**
     * Sets the codigoSeguro value for this TipoPagamentoParameter.
     * 
     * @param codigoSeguro
     */
    public void setCodigoSeguro(Integer codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }


    /**
     * Gets the entidade value for this TipoPagamentoParameter.
     * 
     * @return entidade
     */
    public String getEntidade() {
        return entidade;
    }


    /**
     * Sets the entidade value for this TipoPagamentoParameter.
     * 
     * @param entidade
     */
    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TipoPagamentoParameter)) return false;
        TipoPagamentoParameter other = (TipoPagamentoParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoOrgao==null && other.getCodigoOrgao()==null) || 
             (this.codigoOrgao!=null &&
              this.codigoOrgao.equals(other.getCodigoOrgao()))) &&
            ((this.codigoSeguro==null && other.getCodigoSeguro()==null) || 
             (this.codigoSeguro!=null &&
              this.codigoSeguro.equals(other.getCodigoSeguro()))) &&
            ((this.entidade==null && other.getEntidade()==null) || 
             (this.entidade!=null &&
              this.entidade.equals(other.getEntidade())));
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
        if (getCodigoOrgao() != null) {
            _hashCode += getCodigoOrgao().hashCode();
        }
        if (getCodigoSeguro() != null) {
            _hashCode += getCodigoSeguro().hashCode();
        }
        if (getEntidade() != null) {
            _hashCode += getEntidade().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoPagamentoParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoPagamentoParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoOrgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
