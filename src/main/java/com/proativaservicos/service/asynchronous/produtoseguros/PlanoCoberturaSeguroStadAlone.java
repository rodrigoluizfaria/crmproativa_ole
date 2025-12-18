/**
 * PlanoCoberturaSeguroStadAlone.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PlanoCoberturaSeguroStadAlone  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceBean  implements java.io.Serializable {
    private Integer codigoCobertura;

    private Integer codigoProdutoSeguro;

    private String nomeCobertura;

    private Double valorBeneficio;

    public PlanoCoberturaSeguroStadAlone() {
    }

    public PlanoCoberturaSeguroStadAlone(
           Integer codigoCobertura,
           Integer codigoProdutoSeguro,
           String nomeCobertura,
           Double valorBeneficio) {
        this.codigoCobertura = codigoCobertura;
        this.codigoProdutoSeguro = codigoProdutoSeguro;
        this.nomeCobertura = nomeCobertura;
        this.valorBeneficio = valorBeneficio;
    }


    /**
     * Gets the codigoCobertura value for this PlanoCoberturaSeguroStadAlone.
     * 
     * @return codigoCobertura
     */
    public Integer getCodigoCobertura() {
        return codigoCobertura;
    }


    /**
     * Sets the codigoCobertura value for this PlanoCoberturaSeguroStadAlone.
     * 
     * @param codigoCobertura
     */
    public void setCodigoCobertura(Integer codigoCobertura) {
        this.codigoCobertura = codigoCobertura;
    }


    /**
     * Gets the codigoProdutoSeguro value for this PlanoCoberturaSeguroStadAlone.
     * 
     * @return codigoProdutoSeguro
     */
    public Integer getCodigoProdutoSeguro() {
        return codigoProdutoSeguro;
    }


    /**
     * Sets the codigoProdutoSeguro value for this PlanoCoberturaSeguroStadAlone.
     * 
     * @param codigoProdutoSeguro
     */
    public void setCodigoProdutoSeguro(Integer codigoProdutoSeguro) {
        this.codigoProdutoSeguro = codigoProdutoSeguro;
    }


    /**
     * Gets the nomeCobertura value for this PlanoCoberturaSeguroStadAlone.
     * 
     * @return nomeCobertura
     */
    public String getNomeCobertura() {
        return nomeCobertura;
    }


    /**
     * Sets the nomeCobertura value for this PlanoCoberturaSeguroStadAlone.
     * 
     * @param nomeCobertura
     */
    public void setNomeCobertura(String nomeCobertura) {
        this.nomeCobertura = nomeCobertura;
    }


    /**
     * Gets the valorBeneficio value for this PlanoCoberturaSeguroStadAlone.
     * 
     * @return valorBeneficio
     */
    public Double getValorBeneficio() {
        return valorBeneficio;
    }


    /**
     * Sets the valorBeneficio value for this PlanoCoberturaSeguroStadAlone.
     * 
     * @param valorBeneficio
     */
    public void setValorBeneficio(Double valorBeneficio) {
        this.valorBeneficio = valorBeneficio;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlanoCoberturaSeguroStadAlone)) return false;
        PlanoCoberturaSeguroStadAlone other = (PlanoCoberturaSeguroStadAlone) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoCobertura==null && other.getCodigoCobertura()==null) || 
             (this.codigoCobertura!=null &&
              this.codigoCobertura.equals(other.getCodigoCobertura()))) &&
            ((this.codigoProdutoSeguro==null && other.getCodigoProdutoSeguro()==null) || 
             (this.codigoProdutoSeguro!=null &&
              this.codigoProdutoSeguro.equals(other.getCodigoProdutoSeguro()))) &&
            ((this.nomeCobertura==null && other.getNomeCobertura()==null) || 
             (this.nomeCobertura!=null &&
              this.nomeCobertura.equals(other.getNomeCobertura()))) &&
            ((this.valorBeneficio==null && other.getValorBeneficio()==null) || 
             (this.valorBeneficio!=null &&
              this.valorBeneficio.equals(other.getValorBeneficio())));
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
        if (getCodigoCobertura() != null) {
            _hashCode += getCodigoCobertura().hashCode();
        }
        if (getCodigoProdutoSeguro() != null) {
            _hashCode += getCodigoProdutoSeguro().hashCode();
        }
        if (getNomeCobertura() != null) {
            _hashCode += getNomeCobertura().hashCode();
        }
        if (getValorBeneficio() != null) {
            _hashCode += getValorBeneficio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlanoCoberturaSeguroStadAlone.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanoCoberturaSeguroStadAlone"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCobertura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoCobertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProdutoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoProdutoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeCobertura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeCobertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorBeneficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorBeneficio"));
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
