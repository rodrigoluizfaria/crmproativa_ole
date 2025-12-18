/**
 * AgregacaoMargemParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class AgregacaoMargemParameter  extends AbstractWebServicesParameter  implements java.io.Serializable {
    private String informacaoAdicional;

    private Double margemSugerida;

    private String senhaToken;

    public AgregacaoMargemParameter() {
    }

    public AgregacaoMargemParameter(
           String informacaoAdicional,
           Double margemSugerida,
           String senhaToken) {
        this.informacaoAdicional = informacaoAdicional;
        this.margemSugerida = margemSugerida;
        this.senhaToken = senhaToken;
    }


    /**
     * Gets the informacaoAdicional value for this AgregacaoMargemParameter.
     * 
     * @return informacaoAdicional
     */
    public String getInformacaoAdicional() {
        return informacaoAdicional;
    }


    /**
     * Sets the informacaoAdicional value for this AgregacaoMargemParameter.
     * 
     * @param informacaoAdicional
     */
    public void setInformacaoAdicional(String informacaoAdicional) {
        this.informacaoAdicional = informacaoAdicional;
    }


    /**
     * Gets the margemSugerida value for this AgregacaoMargemParameter.
     * 
     * @return margemSugerida
     */
    public Double getMargemSugerida() {
        return margemSugerida;
    }


    /**
     * Sets the margemSugerida value for this AgregacaoMargemParameter.
     * 
     * @param margemSugerida
     */
    public void setMargemSugerida(Double margemSugerida) {
        this.margemSugerida = margemSugerida;
    }


    /**
     * Gets the senhaToken value for this AgregacaoMargemParameter.
     * 
     * @return senhaToken
     */
    public String getSenhaToken() {
        return senhaToken;
    }


    /**
     * Sets the senhaToken value for this AgregacaoMargemParameter.
     * 
     * @param senhaToken
     */
    public void setSenhaToken(String senhaToken) {
        this.senhaToken = senhaToken;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
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
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgregacaoMargemParameter"));
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
