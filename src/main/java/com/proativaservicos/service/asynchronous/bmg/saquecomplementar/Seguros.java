/**
 * Seguros.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class Seguros  implements java.io.Serializable {
    private double capitalSegurado;

    private Integer codigoPlano;

    private String descricaoSeguro;

    private int tipoSeguro;

    private double valorDoSeguro;

    public Seguros() {
    }

    public Seguros(
           double capitalSegurado,
           Integer codigoPlano,
           String descricaoSeguro,
           int tipoSeguro,
           double valorDoSeguro) {
           this.capitalSegurado = capitalSegurado;
           this.codigoPlano = codigoPlano;
           this.descricaoSeguro = descricaoSeguro;
           this.tipoSeguro = tipoSeguro;
           this.valorDoSeguro = valorDoSeguro;
    }


    /**
     * Gets the capitalSegurado value for this Seguros.
     * 
     * @return capitalSegurado
     */
    public double getCapitalSegurado() {
        return capitalSegurado;
    }


    /**
     * Sets the capitalSegurado value for this Seguros.
     * 
     * @param capitalSegurado
     */
    public void setCapitalSegurado(double capitalSegurado) {
        this.capitalSegurado = capitalSegurado;
    }


    /**
     * Gets the codigoPlano value for this Seguros.
     * 
     * @return codigoPlano
     */
    public Integer getCodigoPlano() {
        return codigoPlano;
    }


    /**
     * Sets the codigoPlano value for this Seguros.
     * 
     * @param codigoPlano
     */
    public void setCodigoPlano(Integer codigoPlano) {
        this.codigoPlano = codigoPlano;
    }


    /**
     * Gets the descricaoSeguro value for this Seguros.
     * 
     * @return descricaoSeguro
     */
    public String getDescricaoSeguro() {
        return descricaoSeguro;
    }


    /**
     * Sets the descricaoSeguro value for this Seguros.
     * 
     * @param descricaoSeguro
     */
    public void setDescricaoSeguro(String descricaoSeguro) {
        this.descricaoSeguro = descricaoSeguro;
    }


    /**
     * Gets the tipoSeguro value for this Seguros.
     * 
     * @return tipoSeguro
     */
    public int getTipoSeguro() {
        return tipoSeguro;
    }


    /**
     * Sets the tipoSeguro value for this Seguros.
     * 
     * @param tipoSeguro
     */
    public void setTipoSeguro(int tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }


    /**
     * Gets the valorDoSeguro value for this Seguros.
     * 
     * @return valorDoSeguro
     */
    public double getValorDoSeguro() {
        return valorDoSeguro;
    }


    /**
     * Sets the valorDoSeguro value for this Seguros.
     * 
     * @param valorDoSeguro
     */
    public void setValorDoSeguro(double valorDoSeguro) {
        this.valorDoSeguro = valorDoSeguro;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Seguros)) return false;
        Seguros other = (Seguros) obj;
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
        new org.apache.axis.description.TypeDesc(Seguros.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguros"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capitalSegurado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capitalSegurado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
