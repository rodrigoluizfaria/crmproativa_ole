/**
 * PlanosContratacaoSeguroLimiteSaque.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PlanosContratacaoSeguroLimiteSaque  extends com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroStandAlone  implements java.io.Serializable {
    private double valorSaqueMaximo;

    private double valorSaqueMinimo;

    public PlanosContratacaoSeguroLimiteSaque() {
    }

    public PlanosContratacaoSeguroLimiteSaque(
           com.proativaservicos.service.asynchronous.produtoseguros.PlanoCoberturaSeguroStadAlone[] coberturas,
           Integer codigoPlano,
           Integer codigoSeguro,
           String nomePlano,
           Integer quantidadeMesVigente,
           String tipoPagamento,
           Double valorCapitalSegurado,
           Double valorLiberadoComSeguroFgts,
           Double valorPremio,
           Integer rating,
           double valorSaqueMaximo,
           double valorSaqueMinimo) {
        super(
            coberturas,
            codigoPlano,
            codigoSeguro,
            nomePlano,
            quantidadeMesVigente,
            tipoPagamento,
            valorCapitalSegurado,
            valorLiberadoComSeguroFgts,
            valorPremio,rating);
        this.valorSaqueMaximo = valorSaqueMaximo;
        this.valorSaqueMinimo = valorSaqueMinimo;
    }


    /**
     * Gets the valorSaqueMaximo value for this PlanosContratacaoSeguroLimiteSaque.
     * 
     * @return valorSaqueMaximo
     */
    public double getValorSaqueMaximo() {
        return valorSaqueMaximo;
    }


    /**
     * Sets the valorSaqueMaximo value for this PlanosContratacaoSeguroLimiteSaque.
     * 
     * @param valorSaqueMaximo
     */
    public void setValorSaqueMaximo(double valorSaqueMaximo) {
        this.valorSaqueMaximo = valorSaqueMaximo;
    }


    /**
     * Gets the valorSaqueMinimo value for this PlanosContratacaoSeguroLimiteSaque.
     * 
     * @return valorSaqueMinimo
     */
    public double getValorSaqueMinimo() {
        return valorSaqueMinimo;
    }


    /**
     * Sets the valorSaqueMinimo value for this PlanosContratacaoSeguroLimiteSaque.
     * 
     * @param valorSaqueMinimo
     */
    public void setValorSaqueMinimo(double valorSaqueMinimo) {
        this.valorSaqueMinimo = valorSaqueMinimo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlanosContratacaoSeguroLimiteSaque)) return false;
        PlanosContratacaoSeguroLimiteSaque other = (PlanosContratacaoSeguroLimiteSaque) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.valorSaqueMaximo == other.getValorSaqueMaximo() &&
            this.valorSaqueMinimo == other.getValorSaqueMinimo();
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
        _hashCode += new Double(getValorSaqueMaximo()).hashCode();
        _hashCode += new Double(getValorSaqueMinimo()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlanosContratacaoSeguroLimiteSaque.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosContratacaoSeguroLimiteSaque"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaqueMaximo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaqueMaximo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaqueMinimo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaqueMinimo"));
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
