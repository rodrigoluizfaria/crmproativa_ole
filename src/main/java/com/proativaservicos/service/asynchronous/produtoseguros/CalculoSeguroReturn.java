/**
 * CalculoSeguroReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class CalculoSeguroReturn  implements java.io.Serializable {
    private Double capitalSegurado;

    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private String mensagemDeErro;

    private Double valorDoSeguro;

    private Double valorLiberado;

    public CalculoSeguroReturn() {
    }

    public CalculoSeguroReturn(
           Double capitalSegurado,
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           String mensagemDeErro,
           Double valorDoSeguro,
           Double valorLiberado) {
           this.capitalSegurado = capitalSegurado;
           this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
           this.excecaoGenerica = excecaoGenerica;
           this.mensagemDeErro = mensagemDeErro;
           this.valorDoSeguro = valorDoSeguro;
           this.valorLiberado = valorLiberado;
    }


    /**
     * Gets the capitalSegurado value for this CalculoSeguroReturn.
     * 
     * @return capitalSegurado
     */
    public Double getCapitalSegurado() {
        return capitalSegurado;
    }


    /**
     * Sets the capitalSegurado value for this CalculoSeguroReturn.
     * 
     * @param capitalSegurado
     */
    public void setCapitalSegurado(Double capitalSegurado) {
        this.capitalSegurado = capitalSegurado;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this CalculoSeguroReturn.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this CalculoSeguroReturn.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this CalculoSeguroReturn.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this CalculoSeguroReturn.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the mensagemDeErro value for this CalculoSeguroReturn.
     * 
     * @return mensagemDeErro
     */
    public String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this CalculoSeguroReturn.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the valorDoSeguro value for this CalculoSeguroReturn.
     * 
     * @return valorDoSeguro
     */
    public Double getValorDoSeguro() {
        return valorDoSeguro;
    }


    /**
     * Sets the valorDoSeguro value for this CalculoSeguroReturn.
     * 
     * @param valorDoSeguro
     */
    public void setValorDoSeguro(Double valorDoSeguro) {
        this.valorDoSeguro = valorDoSeguro;
    }


    /**
     * Gets the valorLiberado value for this CalculoSeguroReturn.
     * 
     * @return valorLiberado
     */
    public Double getValorLiberado() {
        return valorLiberado;
    }


    /**
     * Sets the valorLiberado value for this CalculoSeguroReturn.
     * 
     * @param valorLiberado
     */
    public void setValorLiberado(Double valorLiberado) {
        this.valorLiberado = valorLiberado;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CalculoSeguroReturn)) return false;
        CalculoSeguroReturn other = (CalculoSeguroReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.capitalSegurado==null && other.getCapitalSegurado()==null) || 
             (this.capitalSegurado!=null &&
              this.capitalSegurado.equals(other.getCapitalSegurado()))) &&
            this.excecaoDeRegraDeNegocio == other.isExcecaoDeRegraDeNegocio() &&
            this.excecaoGenerica == other.isExcecaoGenerica() &&
            ((this.mensagemDeErro==null && other.getMensagemDeErro()==null) || 
             (this.mensagemDeErro!=null &&
              this.mensagemDeErro.equals(other.getMensagemDeErro()))) &&
            ((this.valorDoSeguro==null && other.getValorDoSeguro()==null) || 
             (this.valorDoSeguro!=null &&
              this.valorDoSeguro.equals(other.getValorDoSeguro()))) &&
            ((this.valorLiberado==null && other.getValorLiberado()==null) || 
             (this.valorLiberado!=null &&
              this.valorLiberado.equals(other.getValorLiberado())));
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
        if (getCapitalSegurado() != null) {
            _hashCode += getCapitalSegurado().hashCode();
        }
        _hashCode += (isExcecaoDeRegraDeNegocio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcecaoGenerica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagemDeErro() != null) {
            _hashCode += getMensagemDeErro().hashCode();
        }
        if (getValorDoSeguro() != null) {
            _hashCode += getValorDoSeguro().hashCode();
        }
        if (getValorLiberado() != null) {
            _hashCode += getValorLiberado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CalculoSeguroReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CalculoSeguroReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capitalSegurado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capitalSegurado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecaoDeRegraDeNegocio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecaoDeRegraDeNegocio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecaoGenerica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecaoGenerica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagemDeErro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemDeErro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorDoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorDoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorLiberado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorLiberado"));
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
