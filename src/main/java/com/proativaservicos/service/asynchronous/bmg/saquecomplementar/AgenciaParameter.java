/**
 * AgenciaParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class AgenciaParameter  extends AbstractWebServicesParameter  implements java.io.Serializable {
    private String digitoVerificador;

    private String numero;

    public AgenciaParameter() {
    }

    public AgenciaParameter(
           String digitoVerificador,
           String numero) {
        this.digitoVerificador = digitoVerificador;
        this.numero = numero;
    }


    /**
     * Gets the digitoVerificador value for this AgenciaParameter.
     * 
     * @return digitoVerificador
     */
    public String getDigitoVerificador() {
        return digitoVerificador;
    }


    /**
     * Sets the digitoVerificador value for this AgenciaParameter.
     * 
     * @param digitoVerificador
     */
    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }


    /**
     * Gets the numero value for this AgenciaParameter.
     * 
     * @return numero
     */
    public String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this AgenciaParameter.
     * 
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof AgenciaParameter)) return false;
        AgenciaParameter other = (AgenciaParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.digitoVerificador==null && other.getDigitoVerificador()==null) || 
             (this.digitoVerificador!=null &&
              this.digitoVerificador.equals(other.getDigitoVerificador()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero())));
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
        if (getDigitoVerificador() != null) {
            _hashCode += getDigitoVerificador().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AgenciaParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgenciaParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digitoVerificador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "digitoVerificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
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
