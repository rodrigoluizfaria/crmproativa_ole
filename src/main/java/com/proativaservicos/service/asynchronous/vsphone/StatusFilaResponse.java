/**
 * StatusFilaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class StatusFilaResponse  extends com.proativaservicos.service.asynchronous.vsphone.CustomResponse  implements java.io.Serializable {
    private java.lang.String free;

    private java.lang.String logged;

    private java.lang.String ready;

    private java.lang.String callsWaiting;

    public StatusFilaResponse() {
    }

    public StatusFilaResponse(
           com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes,
           java.lang.String free,
           java.lang.String logged,
           java.lang.String ready,
           java.lang.String callsWaiting) {
        super(
            excecoes);
        this.free = free;
        this.logged = logged;
        this.ready = ready;
        this.callsWaiting = callsWaiting;
    }


    /**
     * Gets the free value for this StatusFilaResponse.
     * 
     * @return free
     */
    public java.lang.String getFree() {
        return free;
    }


    /**
     * Sets the free value for this StatusFilaResponse.
     * 
     * @param free
     */
    public void setFree(java.lang.String free) {
        this.free = free;
    }


    /**
     * Gets the logged value for this StatusFilaResponse.
     * 
     * @return logged
     */
    public java.lang.String getLogged() {
        return logged;
    }


    /**
     * Sets the logged value for this StatusFilaResponse.
     * 
     * @param logged
     */
    public void setLogged(java.lang.String logged) {
        this.logged = logged;
    }


    /**
     * Gets the ready value for this StatusFilaResponse.
     * 
     * @return ready
     */
    public java.lang.String getReady() {
        return ready;
    }


    /**
     * Sets the ready value for this StatusFilaResponse.
     * 
     * @param ready
     */
    public void setReady(java.lang.String ready) {
        this.ready = ready;
    }


    /**
     * Gets the callsWaiting value for this StatusFilaResponse.
     * 
     * @return callsWaiting
     */
    public java.lang.String getCallsWaiting() {
        return callsWaiting;
    }


    /**
     * Sets the callsWaiting value for this StatusFilaResponse.
     * 
     * @param callsWaiting
     */
    public void setCallsWaiting(java.lang.String callsWaiting) {
        this.callsWaiting = callsWaiting;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusFilaResponse)) return false;
        StatusFilaResponse other = (StatusFilaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.free==null && other.getFree()==null) || 
             (this.free!=null &&
              this.free.equals(other.getFree()))) &&
            ((this.logged==null && other.getLogged()==null) || 
             (this.logged!=null &&
              this.logged.equals(other.getLogged()))) &&
            ((this.ready==null && other.getReady()==null) || 
             (this.ready!=null &&
              this.ready.equals(other.getReady()))) &&
            ((this.callsWaiting==null && other.getCallsWaiting()==null) || 
             (this.callsWaiting!=null &&
              this.callsWaiting.equals(other.getCallsWaiting())));
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
        if (getFree() != null) {
            _hashCode += getFree().hashCode();
        }
        if (getLogged() != null) {
            _hashCode += getLogged().hashCode();
        }
        if (getReady() != null) {
            _hashCode += getReady().hashCode();
        }
        if (getCallsWaiting() != null) {
            _hashCode += getCallsWaiting().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusFilaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "statusFilaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("free");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "free"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logged");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "logged"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ready");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ready"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callsWaiting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "callsWaiting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
