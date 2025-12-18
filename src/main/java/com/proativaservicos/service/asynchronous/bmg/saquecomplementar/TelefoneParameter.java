/**
 * TelefoneParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class TelefoneParameter  extends AbstractWebServicesParameter  implements java.io.Serializable {
    private String ddd;

    private String numero;

    private String ramal;

    public TelefoneParameter() {
    }

    public TelefoneParameter(
           String ddd,
           String numero,
           String ramal) {
        this.ddd = ddd;
        this.numero = numero;
        this.ramal = ramal;
    }


    /**
     * Gets the ddd value for this TelefoneParameter.
     * 
     * @return ddd
     */
    public String getDdd() {
        return ddd;
    }


    /**
     * Sets the ddd value for this TelefoneParameter.
     * 
     * @param ddd
     */
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }


    /**
     * Gets the numero value for this TelefoneParameter.
     * 
     * @return numero
     */
    public String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this TelefoneParameter.
     * 
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }


    /**
     * Gets the ramal value for this TelefoneParameter.
     * 
     * @return ramal
     */
    public String getRamal() {
        return ramal;
    }


    /**
     * Sets the ramal value for this TelefoneParameter.
     * 
     * @param ramal
     */
    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TelefoneParameter)) return false;
        TelefoneParameter other = (TelefoneParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ddd==null && other.getDdd()==null) || 
             (this.ddd!=null &&
              this.ddd.equals(other.getDdd()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.ramal==null && other.getRamal()==null) || 
             (this.ramal!=null &&
              this.ramal.equals(other.getRamal())));
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
        if (getDdd() != null) {
            _hashCode += getDdd().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getRamal() != null) {
            _hashCode += getRamal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TelefoneParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ddd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ddd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ramal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ramal"));
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
