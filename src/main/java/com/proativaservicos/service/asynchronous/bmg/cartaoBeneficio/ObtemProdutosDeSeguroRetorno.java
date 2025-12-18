/**
 * ObtemProdutosDeSeguroRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class ObtemProdutosDeSeguroRetorno  implements java.io.Serializable {
    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private java.lang.String mensagemDeErro;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.TipoProdutoSeguro[] tiposDeSeguro;

    public ObtemProdutosDeSeguroRetorno() {
    }

    public ObtemProdutosDeSeguroRetorno(
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           java.lang.String mensagemDeErro,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.TipoProdutoSeguro[] tiposDeSeguro) {
           this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
           this.excecaoGenerica = excecaoGenerica;
           this.mensagemDeErro = mensagemDeErro;
           this.tiposDeSeguro = tiposDeSeguro;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this ObtemProdutosDeSeguroRetorno.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this ObtemProdutosDeSeguroRetorno.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this ObtemProdutosDeSeguroRetorno.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this ObtemProdutosDeSeguroRetorno.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the mensagemDeErro value for this ObtemProdutosDeSeguroRetorno.
     * 
     * @return mensagemDeErro
     */
    public java.lang.String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this ObtemProdutosDeSeguroRetorno.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(java.lang.String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the tiposDeSeguro value for this ObtemProdutosDeSeguroRetorno.
     * 
     * @return tiposDeSeguro
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.TipoProdutoSeguro[] getTiposDeSeguro() {
        return tiposDeSeguro;
    }


    /**
     * Sets the tiposDeSeguro value for this ObtemProdutosDeSeguroRetorno.
     * 
     * @param tiposDeSeguro
     */
    public void setTiposDeSeguro(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.TipoProdutoSeguro[] tiposDeSeguro) {
        this.tiposDeSeguro = tiposDeSeguro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtemProdutosDeSeguroRetorno)) return false;
        ObtemProdutosDeSeguroRetorno other = (ObtemProdutosDeSeguroRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.excecaoDeRegraDeNegocio == other.isExcecaoDeRegraDeNegocio() &&
            this.excecaoGenerica == other.isExcecaoGenerica() &&
            ((this.mensagemDeErro==null && other.getMensagemDeErro()==null) || 
             (this.mensagemDeErro!=null &&
              this.mensagemDeErro.equals(other.getMensagemDeErro()))) &&
            ((this.tiposDeSeguro==null && other.getTiposDeSeguro()==null) || 
             (this.tiposDeSeguro!=null &&
              java.util.Arrays.equals(this.tiposDeSeguro, other.getTiposDeSeguro())));
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
        _hashCode += (isExcecaoDeRegraDeNegocio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcecaoGenerica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagemDeErro() != null) {
            _hashCode += getMensagemDeErro().hashCode();
        }
        if (getTiposDeSeguro() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTiposDeSeguro());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTiposDeSeguro(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtemProdutosDeSeguroRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("tiposDeSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tiposDeSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoProdutoSeguro"));
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
