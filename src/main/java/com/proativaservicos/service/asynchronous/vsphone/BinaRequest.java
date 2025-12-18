/**
 * BinaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class BinaRequest  extends com.proativaservicos.service.asynchronous.vsphone.CustomRequest  implements java.io.Serializable {
	
    private com.proativaservicos.service.asynchronous.vsphone.Host host;

    private com.proativaservicos.service.asynchronous.vsphone.Ramal[] ramais;

    public BinaRequest() {
    }

    public BinaRequest(
           com.proativaservicos.service.asynchronous.vsphone.Host host,
           com.proativaservicos.service.asynchronous.vsphone.Ramal[] ramais) {
        this.host = host;
        this.ramais = ramais;
    }


    /**
     * Gets the host value for this BinaRequest.
     * 
     * @return host
     */
    public com.proativaservicos.service.asynchronous.vsphone.Host getHost() {
        return host;
    }


    /**
     * Sets the host value for this BinaRequest.
     * 
     * @param host
     */
    public void setHost(com.proativaservicos.service.asynchronous.vsphone.Host host) {
        this.host = host;
    }


    /**
     * Gets the ramais value for this BinaRequest.
     * 
     * @return ramais
     */
    public com.proativaservicos.service.asynchronous.vsphone.Ramal[] getRamais() {
        return ramais;
    }


    /**
     * Sets the ramais value for this BinaRequest.
     * 
     * @param ramais
     */
    public void setRamais(com.proativaservicos.service.asynchronous.vsphone.Ramal[] ramais) {
        this.ramais = ramais;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BinaRequest)) return false;
        BinaRequest other = (BinaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.host==null && other.getHost()==null) || 
             (this.host!=null &&
              this.host.equals(other.getHost()))) &&
            ((this.ramais==null && other.getRamais()==null) || 
             (this.ramais!=null &&
              java.util.Arrays.equals(this.ramais, other.getRamais())));
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
        if (getHost() != null) {
            _hashCode += getHost().hashCode();
        }
        if (getRamais() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRamais());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRamais(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BinaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BinaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("host");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "host"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Host"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ramais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ramais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Ramal"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "item"));
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
