/**
 * TipoProdutoSeguro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class TipoProdutoSeguro  implements java.io.Serializable {
    private int codigoTipoSeguro;

    private String descricaoProdutoSeguro;

    public TipoProdutoSeguro() {
    }

    public TipoProdutoSeguro(
           int codigoTipoSeguro,
           String descricaoProdutoSeguro) {
           this.codigoTipoSeguro = codigoTipoSeguro;
           this.descricaoProdutoSeguro = descricaoProdutoSeguro;
    }


    /**
     * Gets the codigoTipoSeguro value for this TipoProdutoSeguro.
     * 
     * @return codigoTipoSeguro
     */
    public int getCodigoTipoSeguro() {
        return codigoTipoSeguro;
    }


    /**
     * Sets the codigoTipoSeguro value for this TipoProdutoSeguro.
     * 
     * @param codigoTipoSeguro
     */
    public void setCodigoTipoSeguro(int codigoTipoSeguro) {
        this.codigoTipoSeguro = codigoTipoSeguro;
    }


    /**
     * Gets the descricaoProdutoSeguro value for this TipoProdutoSeguro.
     * 
     * @return descricaoProdutoSeguro
     */
    public String getDescricaoProdutoSeguro() {
        return descricaoProdutoSeguro;
    }


    /**
     * Sets the descricaoProdutoSeguro value for this TipoProdutoSeguro.
     * 
     * @param descricaoProdutoSeguro
     */
    public void setDescricaoProdutoSeguro(String descricaoProdutoSeguro) {
        this.descricaoProdutoSeguro = descricaoProdutoSeguro;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TipoProdutoSeguro)) return false;
        TipoProdutoSeguro other = (TipoProdutoSeguro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codigoTipoSeguro == other.getCodigoTipoSeguro() &&
            ((this.descricaoProdutoSeguro==null && other.getDescricaoProdutoSeguro()==null) || 
             (this.descricaoProdutoSeguro!=null &&
              this.descricaoProdutoSeguro.equals(other.getDescricaoProdutoSeguro())));
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
        _hashCode += getCodigoTipoSeguro();
        if (getDescricaoProdutoSeguro() != null) {
            _hashCode += getDescricaoProdutoSeguro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoProdutoSeguro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoProdutoSeguro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoProdutoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricaoProdutoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
