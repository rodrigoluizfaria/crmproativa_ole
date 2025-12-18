/**
 * ObtemFormaEnvioParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class ObtemFormaEnvioParameter  extends com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceBaseParameter  implements java.io.Serializable {
    private int codigoEntidade;

    private java.lang.String codigoFormaDeCredito;

    private java.lang.String cpf;

    private java.lang.Integer sequencialOrgao;

    public ObtemFormaEnvioParameter() {
    }

    public ObtemFormaEnvioParameter(
           java.lang.String login,
           java.lang.String senha,
           java.lang.String loginConsig,
           int codigoEntidade,
           java.lang.String codigoFormaDeCredito,
           java.lang.String cpf,
           java.lang.Integer sequencialOrgao) {
        super(
            login,
            senha,
            loginConsig);
        this.codigoEntidade = codigoEntidade;
        this.codigoFormaDeCredito = codigoFormaDeCredito;
        this.cpf = cpf;
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the codigoEntidade value for this ObtemFormaEnvioParameter.
     * 
     * @return codigoEntidade
     */
    public int getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this ObtemFormaEnvioParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(int codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoFormaDeCredito value for this ObtemFormaEnvioParameter.
     * 
     * @return codigoFormaDeCredito
     */
    public java.lang.String getCodigoFormaDeCredito() {
        return codigoFormaDeCredito;
    }


    /**
     * Sets the codigoFormaDeCredito value for this ObtemFormaEnvioParameter.
     * 
     * @param codigoFormaDeCredito
     */
    public void setCodigoFormaDeCredito(java.lang.String codigoFormaDeCredito) {
        this.codigoFormaDeCredito = codigoFormaDeCredito;
    }


    /**
     * Gets the cpf value for this ObtemFormaEnvioParameter.
     * 
     * @return cpf
     */
    public java.lang.String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ObtemFormaEnvioParameter.
     * 
     * @param cpf
     */
    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the sequencialOrgao value for this ObtemFormaEnvioParameter.
     * 
     * @return sequencialOrgao
     */
    public java.lang.Integer getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this ObtemFormaEnvioParameter.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(java.lang.Integer sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtemFormaEnvioParameter)) return false;
        ObtemFormaEnvioParameter other = (ObtemFormaEnvioParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.codigoEntidade == other.getCodigoEntidade() &&
            ((this.codigoFormaDeCredito==null && other.getCodigoFormaDeCredito()==null) || 
             (this.codigoFormaDeCredito!=null &&
              this.codigoFormaDeCredito.equals(other.getCodigoFormaDeCredito()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao())));
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
        if (getCodigoFormaDeCredito() != null) {
            _hashCode += getCodigoFormaDeCredito().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtemFormaEnvioParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoFormaDeCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoFormaDeCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialOrgao"));
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
