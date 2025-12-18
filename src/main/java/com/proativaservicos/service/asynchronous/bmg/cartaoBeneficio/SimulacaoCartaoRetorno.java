/**
 * SimulacaoCartaoRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class SimulacaoCartaoRetorno  implements java.io.Serializable {
    private int numeroParcelas;

    private double valorParcela;

    public SimulacaoCartaoRetorno() {
    }

    public SimulacaoCartaoRetorno(
           int numeroParcelas,
           double valorParcela) {
           this.numeroParcelas = numeroParcelas;
           this.valorParcela = valorParcela;
    }


    /**
     * Gets the numeroParcelas value for this SimulacaoCartaoRetorno.
     * 
     * @return numeroParcelas
     */
    public int getNumeroParcelas() {
        return numeroParcelas;
    }


    /**
     * Sets the numeroParcelas value for this SimulacaoCartaoRetorno.
     * 
     * @param numeroParcelas
     */
    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }


    /**
     * Gets the valorParcela value for this SimulacaoCartaoRetorno.
     * 
     * @return valorParcela
     */
    public double getValorParcela() {
        return valorParcela;
    }


    /**
     * Sets the valorParcela value for this SimulacaoCartaoRetorno.
     * 
     * @param valorParcela
     */
    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimulacaoCartaoRetorno)) return false;
        SimulacaoCartaoRetorno other = (SimulacaoCartaoRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.numeroParcelas == other.getNumeroParcelas() &&
            this.valorParcela == other.getValorParcela();
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
        _hashCode += getNumeroParcelas();
        _hashCode += new Double(getValorParcela()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimulacaoCartaoRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoCartaoRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroParcelas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroParcelas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorParcela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorParcela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
