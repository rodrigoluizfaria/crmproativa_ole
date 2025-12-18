/**
 * Protocolo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Protocolo;

public class Protocolo  extends com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.WebServiceBean  implements java.io.Serializable {
    private int codigoProtocolo;

    private com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ResultadoConsistencia[] listaConsistencias;

    public Protocolo() {
    }

    public Protocolo(
           int codigoProtocolo,
           com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ResultadoConsistencia[] listaConsistencias) {
        this.codigoProtocolo = codigoProtocolo;
        this.listaConsistencias = listaConsistencias;
    }


    /**
     * Gets the codigoProtocolo value for this Protocolo.
     * 
     * @return codigoProtocolo
     */
    public int getCodigoProtocolo() {
        return codigoProtocolo;
    }


    /**
     * Sets the codigoProtocolo value for this Protocolo.
     * 
     * @param codigoProtocolo
     */
    public void setCodigoProtocolo(int codigoProtocolo) {
        this.codigoProtocolo = codigoProtocolo;
    }


    /**
     * Gets the listaConsistencias value for this Protocolo.
     * 
     * @return listaConsistencias
     */
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ResultadoConsistencia[] getListaConsistencias() {
        return listaConsistencias;
    }


    /**
     * Sets the listaConsistencias value for this Protocolo.
     * 
     * @param listaConsistencias
     */
    public void setListaConsistencias(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ResultadoConsistencia[] listaConsistencias) {
        this.listaConsistencias = listaConsistencias;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Protocolo)) return false;
        Protocolo other = (Protocolo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.codigoProtocolo == other.getCodigoProtocolo() &&
            ((this.listaConsistencias==null && other.getListaConsistencias()==null) || 
             (this.listaConsistencias!=null &&
              java.util.Arrays.equals(this.listaConsistencias, other.getListaConsistencias())));
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
        _hashCode += getCodigoProtocolo();
        if (getListaConsistencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaConsistencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaConsistencias(), i);
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
        new org.apache.axis.description.TypeDesc(Protocolo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Protocolo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProtocolo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoProtocolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaConsistencias");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaConsistencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ResultadoConsistencia"));
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
