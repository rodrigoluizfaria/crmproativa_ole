/**
 * ContaParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class ContaParameter  extends com.proativaservicos.service.asynchronous.bmg.refinanciamento.AbstractWebServicesParameter  implements java.io.Serializable {
    private java.lang.String digitoVerificador;

    private java.lang.String numero;

    private java.lang.Integer tipoConta;

    public ContaParameter() {
    }

    public ContaParameter(
           java.lang.String digitoVerificador,
           java.lang.String numero,
           java.lang.Integer tipoConta) {
        this.digitoVerificador = digitoVerificador;
        this.numero = numero;
        this.tipoConta = tipoConta;
    }


    /**
     * Gets the digitoVerificador value for this ContaParameter.
     * 
     * @return digitoVerificador
     */
    public java.lang.String getDigitoVerificador() {
        return digitoVerificador;
    }


    /**
     * Sets the digitoVerificador value for this ContaParameter.
     * 
     * @param digitoVerificador
     */
    public void setDigitoVerificador(java.lang.String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }


    /**
     * Gets the numero value for this ContaParameter.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this ContaParameter.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the tipoConta value for this ContaParameter.
     * 
     * @return tipoConta
     */
    public java.lang.Integer getTipoConta() {
        return tipoConta;
    }


    /**
     * Sets the tipoConta value for this ContaParameter.
     * 
     * @param tipoConta
     */
    public void setTipoConta(java.lang.Integer tipoConta) {
        this.tipoConta = tipoConta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContaParameter)) return false;
        ContaParameter other = (ContaParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.digitoVerificador==null && other.getDigitoVerificador()==null) || 
             (this.digitoVerificador!=null &&
              this.digitoVerificador.equals(other.getDigitoVerificador()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.tipoConta==null && other.getTipoConta()==null) || 
             (this.tipoConta!=null &&
              this.tipoConta.equals(other.getTipoConta())));
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
        if (getDigitoVerificador() != null) {
            _hashCode += getDigitoVerificador().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getTipoConta() != null) {
            _hashCode += getTipoConta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContaParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContaParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digitoVerificador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "digitoVerificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
