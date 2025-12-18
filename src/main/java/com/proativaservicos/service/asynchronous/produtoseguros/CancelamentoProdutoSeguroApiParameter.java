/**
 * CancelamentoProdutoSeguroApiParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class CancelamentoProdutoSeguroApiParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroParameter  implements java.io.Serializable {
    private Integer codigoMotivoCancelamento;

    private Integer codigoSubMotivoCancelamento;

    public CancelamentoProdutoSeguroApiParameter() {
    }

    public CancelamentoProdutoSeguroApiParameter(
           String login,
           String senha,
           java.util.Calendar data,
           String justificativa,
           String numeroAde,
           Integer tipoProdutoSeguro,
           Integer codigoMotivoCancelamento,
           Integer codigoSubMotivoCancelamento) {
        super(
            login,
            senha,
            data,
            justificativa,
            numeroAde,
            tipoProdutoSeguro);
        this.codigoMotivoCancelamento = codigoMotivoCancelamento;
        this.codigoSubMotivoCancelamento = codigoSubMotivoCancelamento;
    }


    /**
     * Gets the codigoMotivoCancelamento value for this CancelamentoProdutoSeguroApiParameter.
     * 
     * @return codigoMotivoCancelamento
     */
    public Integer getCodigoMotivoCancelamento() {
        return codigoMotivoCancelamento;
    }


    /**
     * Sets the codigoMotivoCancelamento value for this CancelamentoProdutoSeguroApiParameter.
     * 
     * @param codigoMotivoCancelamento
     */
    public void setCodigoMotivoCancelamento(Integer codigoMotivoCancelamento) {
        this.codigoMotivoCancelamento = codigoMotivoCancelamento;
    }


    /**
     * Gets the codigoSubMotivoCancelamento value for this CancelamentoProdutoSeguroApiParameter.
     * 
     * @return codigoSubMotivoCancelamento
     */
    public Integer getCodigoSubMotivoCancelamento() {
        return codigoSubMotivoCancelamento;
    }


    /**
     * Sets the codigoSubMotivoCancelamento value for this CancelamentoProdutoSeguroApiParameter.
     * 
     * @param codigoSubMotivoCancelamento
     */
    public void setCodigoSubMotivoCancelamento(Integer codigoSubMotivoCancelamento) {
        this.codigoSubMotivoCancelamento = codigoSubMotivoCancelamento;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CancelamentoProdutoSeguroApiParameter)) return false;
        CancelamentoProdutoSeguroApiParameter other = (CancelamentoProdutoSeguroApiParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoMotivoCancelamento==null && other.getCodigoMotivoCancelamento()==null) || 
             (this.codigoMotivoCancelamento!=null &&
              this.codigoMotivoCancelamento.equals(other.getCodigoMotivoCancelamento()))) &&
            ((this.codigoSubMotivoCancelamento==null && other.getCodigoSubMotivoCancelamento()==null) || 
             (this.codigoSubMotivoCancelamento!=null &&
              this.codigoSubMotivoCancelamento.equals(other.getCodigoSubMotivoCancelamento())));
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
        if (getCodigoMotivoCancelamento() != null) {
            _hashCode += getCodigoMotivoCancelamento().hashCode();
        }
        if (getCodigoSubMotivoCancelamento() != null) {
            _hashCode += getCodigoSubMotivoCancelamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelamentoProdutoSeguroApiParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoProdutoSeguroApiParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMotivoCancelamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoMotivoCancelamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSubMotivoCancelamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSubMotivoCancelamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
