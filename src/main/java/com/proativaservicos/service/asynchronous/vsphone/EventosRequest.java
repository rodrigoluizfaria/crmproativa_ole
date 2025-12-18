/**
 * EventosRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class EventosRequest  implements java.io.Serializable {
    private java.lang.String identificacaoChamada;

    public EventosRequest() {
    }

    public EventosRequest(
           java.lang.String identificacaoChamada) {
           this.identificacaoChamada = identificacaoChamada;
    }


    /**
     * Gets the identificacaoChamada value for this EventosRequest.
     * 
     * @return identificacaoChamada
     */
    public java.lang.String getIdentificacaoChamada() {
        return identificacaoChamada;
    }


    /**
     * Sets the identificacaoChamada value for this EventosRequest.
     * 
     * @param identificacaoChamada
     */
    public void setIdentificacaoChamada(java.lang.String identificacaoChamada) {
        this.identificacaoChamada = identificacaoChamada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EventosRequest)) return false;
        EventosRequest other = (EventosRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identificacaoChamada==null && other.getIdentificacaoChamada()==null) || 
             (this.identificacaoChamada!=null &&
              this.identificacaoChamada.equals(other.getIdentificacaoChamada())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIdentificacaoChamada() != null) {
            _hashCode += getIdentificacaoChamada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EventosRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "EventosRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificacaoChamada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "identificacaoChamada"));
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
