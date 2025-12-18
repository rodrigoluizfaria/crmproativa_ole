/**
 * ContratoRefin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.consultaContrato;

public class ContratoRefin  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.bmg.consultaContrato.Contrato contrato;

    private com.proativaservicos.service.asynchronous.bmg.consultaContrato.SimulacaoRetorno simulacao;

    public ContratoRefin() {
    }

    public ContratoRefin(
           com.proativaservicos.service.asynchronous.bmg.consultaContrato.Contrato contrato,
           com.proativaservicos.service.asynchronous.bmg.consultaContrato.SimulacaoRetorno simulacao) {
           this.contrato = contrato;
           this.simulacao = simulacao;
    }


    /**
     * Gets the contrato value for this ContratoRefin.
     * 
     * @return contrato
     */
    public com.proativaservicos.service.asynchronous.bmg.consultaContrato.Contrato getContrato() {
        return contrato;
    }


    /**
     * Sets the contrato value for this ContratoRefin.
     * 
     * @param contrato
     */
    public void setContrato(com.proativaservicos.service.asynchronous.bmg.consultaContrato.Contrato contrato) {
        this.contrato = contrato;
    }


    /**
     * Gets the simulacao value for this ContratoRefin.
     * 
     * @return simulacao
     */
    public com.proativaservicos.service.asynchronous.bmg.consultaContrato.SimulacaoRetorno getSimulacao() {
        return simulacao;
    }


    /**
     * Sets the simulacao value for this ContratoRefin.
     * 
     * @param simulacao
     */
    public void setSimulacao(com.proativaservicos.service.asynchronous.bmg.consultaContrato.SimulacaoRetorno simulacao) {
        this.simulacao = simulacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContratoRefin)) return false;
        ContratoRefin other = (ContratoRefin) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contrato==null && other.getContrato()==null) || 
             (this.contrato!=null &&
              this.contrato.equals(other.getContrato()))) &&
            ((this.simulacao==null && other.getSimulacao()==null) || 
             (this.simulacao!=null &&
              this.simulacao.equals(other.getSimulacao())));
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
        if (getContrato() != null) {
            _hashCode += getContrato().hashCode();
        }
        if (getSimulacao() != null) {
            _hashCode += getSimulacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContratoRefin.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoRefin"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("simulacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "simulacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoRetorno"));
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
