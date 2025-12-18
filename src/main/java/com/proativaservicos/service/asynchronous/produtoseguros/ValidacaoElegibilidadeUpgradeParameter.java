/**
 * ValidacaoElegibilidadeUpgradeParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ValidacaoElegibilidadeUpgradeParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private Integer codigoLoja;

    private Integer codigoSeguro;

    private String cpf;

    private String numeroInternoConta;

    public ValidacaoElegibilidadeUpgradeParameter() {
    }

    public ValidacaoElegibilidadeUpgradeParameter(
           String login,
           String senha,
           Integer codigoLoja,
           Integer codigoSeguro,
           String cpf,
           String numeroInternoConta) {
        super(
            login,
            senha);
        this.codigoLoja = codigoLoja;
        this.codigoSeguro = codigoSeguro;
        this.cpf = cpf;
        this.numeroInternoConta = numeroInternoConta;
    }


    /**
     * Gets the codigoLoja value for this ValidacaoElegibilidadeUpgradeParameter.
     * 
     * @return codigoLoja
     */
    public Integer getCodigoLoja() {
        return codigoLoja;
    }


    /**
     * Sets the codigoLoja value for this ValidacaoElegibilidadeUpgradeParameter.
     * 
     * @param codigoLoja
     */
    public void setCodigoLoja(Integer codigoLoja) {
        this.codigoLoja = codigoLoja;
    }


    /**
     * Gets the codigoSeguro value for this ValidacaoElegibilidadeUpgradeParameter.
     * 
     * @return codigoSeguro
     */
    public Integer getCodigoSeguro() {
        return codigoSeguro;
    }


    /**
     * Sets the codigoSeguro value for this ValidacaoElegibilidadeUpgradeParameter.
     * 
     * @param codigoSeguro
     */
    public void setCodigoSeguro(Integer codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }


    /**
     * Gets the cpf value for this ValidacaoElegibilidadeUpgradeParameter.
     * 
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ValidacaoElegibilidadeUpgradeParameter.
     * 
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the numeroInternoConta value for this ValidacaoElegibilidadeUpgradeParameter.
     * 
     * @return numeroInternoConta
     */
    public String getNumeroInternoConta() {
        return numeroInternoConta;
    }


    /**
     * Sets the numeroInternoConta value for this ValidacaoElegibilidadeUpgradeParameter.
     * 
     * @param numeroInternoConta
     */
    public void setNumeroInternoConta(String numeroInternoConta) {
        this.numeroInternoConta = numeroInternoConta;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ValidacaoElegibilidadeUpgradeParameter)) return false;
        ValidacaoElegibilidadeUpgradeParameter other = (ValidacaoElegibilidadeUpgradeParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoLoja==null && other.getCodigoLoja()==null) || 
             (this.codigoLoja!=null &&
              this.codigoLoja.equals(other.getCodigoLoja()))) &&
            ((this.codigoSeguro==null && other.getCodigoSeguro()==null) || 
             (this.codigoSeguro!=null &&
              this.codigoSeguro.equals(other.getCodigoSeguro()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.numeroInternoConta==null && other.getNumeroInternoConta()==null) || 
             (this.numeroInternoConta!=null &&
              this.numeroInternoConta.equals(other.getNumeroInternoConta())));
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
        if (getCodigoLoja() != null) {
            _hashCode += getCodigoLoja().hashCode();
        }
        if (getCodigoSeguro() != null) {
            _hashCode += getCodigoSeguro().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getNumeroInternoConta() != null) {
            _hashCode += getNumeroInternoConta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidacaoElegibilidadeUpgradeParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeUpgradeParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoLoja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoLoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroInternoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroInternoConta"));
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
