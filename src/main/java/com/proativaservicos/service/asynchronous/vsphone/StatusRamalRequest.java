/**
 * StatusRamalRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class StatusRamalRequest  extends com.proativaservicos.service.asynchronous.vsphone.CustomRequest  implements java.io.Serializable {
    private java.lang.String ramal;

    public StatusRamalRequest() {
    }

    public StatusRamalRequest(
           java.lang.String ramal) {
        this.ramal = ramal;
    }


    /**
     * Gets the ramal value for this StatusRamalRequest.
     * 
     * @return ramal
     */
    public java.lang.String getRamal() {
        return ramal;
    }


    /**
     * Sets the ramal value for this StatusRamalRequest.
     * 
     * @param ramal
     */
    public void setRamal(java.lang.String ramal) {
        this.ramal = ramal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusRamalRequest)) return false;
        StatusRamalRequest other = (StatusRamalRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
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
        if (getRamal() != null) {
            _hashCode += getRamal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusRamalRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusRamalRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ramal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ramal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
