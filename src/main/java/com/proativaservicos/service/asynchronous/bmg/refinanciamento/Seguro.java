/**
 * Seguro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class Seguro  implements java.io.Serializable {
    private double capitalSegurado;

    private java.lang.Integer codigoPlano;

    private java.lang.String descricaoSeguro;

    private int tipoSeguro;

    private double valorDoSeguro;

    public Seguro() {
    }

    public Seguro(
           double capitalSegurado,
           java.lang.Integer codigoPlano,
           java.lang.String descricaoSeguro,
           int tipoSeguro,
           double valorDoSeguro) {
           this.capitalSegurado = capitalSegurado;
           this.codigoPlano = codigoPlano;
           this.descricaoSeguro = descricaoSeguro;
           this.tipoSeguro = tipoSeguro;
           this.valorDoSeguro = valorDoSeguro;
    }


    /**
     * Gets the capitalSegurado value for this Seguro.
     * 
     * @return capitalSegurado
     */
    public double getCapitalSegurado() {
        return capitalSegurado;
    }


    /**
     * Sets the capitalSegurado value for this Seguro.
     * 
     * @param capitalSegurado
     */
    public void setCapitalSegurado(double capitalSegurado) {
        this.capitalSegurado = capitalSegurado;
    }


    /**
     * Gets the codigoPlano value for this Seguro.
     * 
     * @return codigoPlano
     */
    public java.lang.Integer getCodigoPlano() {
        return codigoPlano;
    }


    /**
     * Sets the codigoPlano value for this Seguro.
     * 
     * @param codigoPlano
     */
    public void setCodigoPlano(java.lang.Integer codigoPlano) {
        this.codigoPlano = codigoPlano;
    }


    /**
     * Gets the descricaoSeguro value for this Seguro.
     * 
     * @return descricaoSeguro
     */
    public java.lang.String getDescricaoSeguro() {
        return descricaoSeguro;
    }


    /**
     * Sets the descricaoSeguro value for this Seguro.
     * 
     * @param descricaoSeguro
     */
    public void setDescricaoSeguro(java.lang.String descricaoSeguro) {
        this.descricaoSeguro = descricaoSeguro;
    }


    /**
     * Gets the tipoSeguro value for this Seguro.
     * 
     * @return tipoSeguro
     */
    public int getTipoSeguro() {
        return tipoSeguro;
    }


    /**
     * Sets the tipoSeguro value for this Seguro.
     * 
     * @param tipoSeguro
     */
    public void setTipoSeguro(int tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }


    /**
     * Gets the valorDoSeguro value for this Seguro.
     * 
     * @return valorDoSeguro
     */
    public double getValorDoSeguro() {
        return valorDoSeguro;
    }


    /**
     * Sets the valorDoSeguro value for this Seguro.
     * 
     * @param valorDoSeguro
     */
    public void setValorDoSeguro(double valorDoSeguro) {
        this.valorDoSeguro = valorDoSeguro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Seguro)) return false;
        Seguro other = (Seguro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.capitalSegurado == other.getCapitalSegurado() &&
            ((this.codigoPlano==null && other.getCodigoPlano()==null) || 
             (this.codigoPlano!=null &&
              this.codigoPlano.equals(other.getCodigoPlano()))) &&
            ((this.descricaoSeguro==null && other.getDescricaoSeguro()==null) || 
             (this.descricaoSeguro!=null &&
              this.descricaoSeguro.equals(other.getDescricaoSeguro()))) &&
            this.tipoSeguro == other.getTipoSeguro() &&
            this.valorDoSeguro == other.getValorDoSeguro();
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
        _hashCode += new Double(getCapitalSegurado()).hashCode();
        if (getCodigoPlano() != null) {
            _hashCode += getCodigoPlano().hashCode();
        }
        if (getDescricaoSeguro() != null) {
            _hashCode += getDescricaoSeguro().hashCode();
        }
        _hashCode += getTipoSeguro();
        _hashCode += new Double(getValorDoSeguro()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Seguro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capitalSegurado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capitalSegurado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricaoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorDoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorDoSeguro"));
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
