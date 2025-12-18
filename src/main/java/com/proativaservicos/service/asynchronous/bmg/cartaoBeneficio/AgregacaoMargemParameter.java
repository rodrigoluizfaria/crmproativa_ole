/**
 * AgregacaoMargemParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class AgregacaoMargemParameter  extends com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AbstractWebServicesParameter  implements java.io.Serializable {
    private java.lang.String informacaoAdicional;

    private java.lang.Double margemSugerida;

    private java.lang.String senhaToken;

    public AgregacaoMargemParameter() {
    }

    public AgregacaoMargemParameter(
           java.lang.String informacaoAdicional,
           java.lang.Double margemSugerida,
           java.lang.String senhaToken) {
        this.informacaoAdicional = informacaoAdicional;
        this.margemSugerida = margemSugerida;
        this.senhaToken = senhaToken;
    }


    /**
     * Gets the informacaoAdicional value for this AgregacaoMargemParameter.
     * 
     * @return informacaoAdicional
     */
    public java.lang.String getInformacaoAdicional() {
        return informacaoAdicional;
    }


    /**
     * Sets the informacaoAdicional value for this AgregacaoMargemParameter.
     * 
     * @param informacaoAdicional
     */
    public void setInformacaoAdicional(java.lang.String informacaoAdicional) {
        this.informacaoAdicional = informacaoAdicional;
    }


    /**
     * Gets the margemSugerida value for this AgregacaoMargemParameter.
     * 
     * @return margemSugerida
     */
    public java.lang.Double getMargemSugerida() {
        return margemSugerida;
    }


    /**
     * Sets the margemSugerida value for this AgregacaoMargemParameter.
     * 
     * @param margemSugerida
     */
    public void setMargemSugerida(java.lang.Double margemSugerida) {
        this.margemSugerida = margemSugerida;
    }


    /**
     * Gets the senhaToken value for this AgregacaoMargemParameter.
     * 
     * @return senhaToken
     */
    public java.lang.String getSenhaToken() {
        return senhaToken;
    }


    /**
     * Sets the senhaToken value for this AgregacaoMargemParameter.
     * 
     * @param senhaToken
     */
    public void setSenhaToken(java.lang.String senhaToken) {
        this.senhaToken = senhaToken;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AgregacaoMargemParameter)) return false;
        AgregacaoMargemParameter other = (AgregacaoMargemParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.informacaoAdicional==null && other.getInformacaoAdicional()==null) || 
             (this.informacaoAdicional!=null &&
              this.informacaoAdicional.equals(other.getInformacaoAdicional()))) &&
            ((this.margemSugerida==null && other.getMargemSugerida()==null) || 
             (this.margemSugerida!=null &&
              this.margemSugerida.equals(other.getMargemSugerida()))) &&
            ((this.senhaToken==null && other.getSenhaToken()==null) || 
             (this.senhaToken!=null &&
              this.senhaToken.equals(other.getSenhaToken())));
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
        if (getInformacaoAdicional() != null) {
            _hashCode += getInformacaoAdicional().hashCode();
        }
        if (getMargemSugerida() != null) {
            _hashCode += getMargemSugerida().hashCode();
        }
        if (getSenhaToken() != null) {
            _hashCode += getSenhaToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AgregacaoMargemParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://parameter.cartao.econsig.bmg.com", "AgregacaoMargemParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informacaoAdicional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "informacaoAdicional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("margemSugerida");
        elemField.setXmlName(new javax.xml.namespace.QName("", "margemSugerida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senhaToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senhaToken"));
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
