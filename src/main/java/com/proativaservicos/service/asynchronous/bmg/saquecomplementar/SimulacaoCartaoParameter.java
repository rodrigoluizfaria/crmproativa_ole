/**
 * SimulacaoCartaoParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class SimulacaoCartaoParameter  extends WebServiceParameter  implements java.io.Serializable {
    private int codigoEntidade;

    private double valorSaque;

    public SimulacaoCartaoParameter() {
    }

    public SimulacaoCartaoParameter(
           String login,
           String senha,
           int codigoEntidade,
           double valorSaque) {
        super(
            login,
            senha);
        this.codigoEntidade = codigoEntidade;
        this.valorSaque = valorSaque;
    }


    /**
     * Gets the codigoEntidade value for this SimulacaoCartaoParameter.
     * 
     * @return codigoEntidade
     */
    public int getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this SimulacaoCartaoParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(int codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the valorSaque value for this SimulacaoCartaoParameter.
     * 
     * @return valorSaque
     */
    public double getValorSaque() {
        return valorSaque;
    }


    /**
     * Sets the valorSaque value for this SimulacaoCartaoParameter.
     * 
     * @param valorSaque
     */
    public void setValorSaque(double valorSaque) {
        this.valorSaque = valorSaque;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SimulacaoCartaoParameter)) return false;
        SimulacaoCartaoParameter other = (SimulacaoCartaoParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.codigoEntidade == other.getCodigoEntidade() &&
            this.valorSaque == other.getValorSaque();
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
        _hashCode += getCodigoEntidade();
        _hashCode += new Double(getValorSaque()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimulacaoCartaoParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoCartaoParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaque"));
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
