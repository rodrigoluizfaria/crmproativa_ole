/**
 * ConsistenciaSegurosParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ConsistenciaSegurosParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private Integer codigoApolice;

    public ConsistenciaSegurosParameter() {
    }

    public ConsistenciaSegurosParameter(
           String login,
           String senha,
           Integer codigoApolice) {
        super(
            login,
            senha);
        this.codigoApolice = codigoApolice;
    }


    /**
     * Gets the codigoApolice value for this ConsistenciaSegurosParameter.
     * 
     * @return codigoApolice
     */
    public Integer getCodigoApolice() {
        return codigoApolice;
    }


    /**
     * Sets the codigoApolice value for this ConsistenciaSegurosParameter.
     * 
     * @param codigoApolice
     */
    public void setCodigoApolice(Integer codigoApolice) {
        this.codigoApolice = codigoApolice;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ConsistenciaSegurosParameter)) return false;
        ConsistenciaSegurosParameter other = (ConsistenciaSegurosParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoApolice==null && other.getCodigoApolice()==null) || 
             (this.codigoApolice!=null &&
              this.codigoApolice.equals(other.getCodigoApolice())));
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
        if (getCodigoApolice() != null) {
            _hashCode += getCodigoApolice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsistenciaSegurosParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciaSegurosParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoApolice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoApolice"));
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
