/**
 * LimiteOperacaoRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno;

public class LimiteOperacaoRetorno  implements java.io.Serializable {
    private java.lang.Double valorMaximo;

    private java.lang.Double valorMinimo;

    public LimiteOperacaoRetorno() {
    }

    public LimiteOperacaoRetorno(
           java.lang.Double valorMaximo,
           java.lang.Double valorMinimo) {
           this.valorMaximo = valorMaximo;
           this.valorMinimo = valorMinimo;
    }


    /**
     * Gets the valorMaximo value for this LimiteOperacaoRetorno.
     * 
     * @return valorMaximo
     */
    public java.lang.Double getValorMaximo() {
        return valorMaximo;
    }


    /**
     * Sets the valorMaximo value for this LimiteOperacaoRetorno.
     * 
     * @param valorMaximo
     */
    public void setValorMaximo(java.lang.Double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }


    /**
     * Gets the valorMinimo value for this LimiteOperacaoRetorno.
     * 
     * @return valorMinimo
     */
    public java.lang.Double getValorMinimo() {
        return valorMinimo;
    }


    /**
     * Sets the valorMinimo value for this LimiteOperacaoRetorno.
     * 
     * @param valorMinimo
     */
    public void setValorMinimo(java.lang.Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LimiteOperacaoRetorno)) return false;
        LimiteOperacaoRetorno other = (LimiteOperacaoRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valorMaximo==null && other.getValorMaximo()==null) || 
             (this.valorMaximo!=null &&
              this.valorMaximo.equals(other.getValorMaximo()))) &&
            ((this.valorMinimo==null && other.getValorMinimo()==null) || 
             (this.valorMinimo!=null &&
              this.valorMinimo.equals(other.getValorMinimo())));
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
        if (getValorMaximo() != null) {
            _hashCode += getValorMaximo().hashCode();
        }
        if (getValorMinimo() != null) {
            _hashCode += getValorMinimo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LimiteOperacaoRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacaoRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorMaximo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorMaximo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorMinimo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorMinimo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
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
