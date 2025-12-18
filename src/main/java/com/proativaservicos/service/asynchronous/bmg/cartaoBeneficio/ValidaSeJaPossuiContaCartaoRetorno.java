/**
 * ValidaSeJaPossuiContaCartaoRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class ValidaSeJaPossuiContaCartaoRetorno  implements java.io.Serializable {
    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private java.lang.String mensagemDeErro;

    private java.lang.String numeroCartao;

    private java.lang.Long numeroInternoConta;

    private boolean permiteAssociarContaPagamentoCartao;

    public ValidaSeJaPossuiContaCartaoRetorno() {
    }

    public ValidaSeJaPossuiContaCartaoRetorno(
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           java.lang.String mensagemDeErro,
           java.lang.String numeroCartao,
           java.lang.Long numeroInternoConta,
           boolean permiteAssociarContaPagamentoCartao) {
           this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
           this.excecaoGenerica = excecaoGenerica;
           this.mensagemDeErro = mensagemDeErro;
           this.numeroCartao = numeroCartao;
           this.numeroInternoConta = numeroInternoConta;
           this.permiteAssociarContaPagamentoCartao = permiteAssociarContaPagamentoCartao;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the mensagemDeErro value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @return mensagemDeErro
     */
    public java.lang.String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(java.lang.String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the numeroCartao value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @return numeroCartao
     */
    public java.lang.String getNumeroCartao() {
        return numeroCartao;
    }


    /**
     * Sets the numeroCartao value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @param numeroCartao
     */
    public void setNumeroCartao(java.lang.String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }


    /**
     * Gets the numeroInternoConta value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @return numeroInternoConta
     */
    public java.lang.Long getNumeroInternoConta() {
        return numeroInternoConta;
    }


    /**
     * Sets the numeroInternoConta value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @param numeroInternoConta
     */
    public void setNumeroInternoConta(java.lang.Long numeroInternoConta) {
        this.numeroInternoConta = numeroInternoConta;
    }


    /**
     * Gets the permiteAssociarContaPagamentoCartao value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @return permiteAssociarContaPagamentoCartao
     */
    public boolean isPermiteAssociarContaPagamentoCartao() {
        return permiteAssociarContaPagamentoCartao;
    }


    /**
     * Sets the permiteAssociarContaPagamentoCartao value for this ValidaSeJaPossuiContaCartaoRetorno.
     * 
     * @param permiteAssociarContaPagamentoCartao
     */
    public void setPermiteAssociarContaPagamentoCartao(boolean permiteAssociarContaPagamentoCartao) {
        this.permiteAssociarContaPagamentoCartao = permiteAssociarContaPagamentoCartao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaSeJaPossuiContaCartaoRetorno)) return false;
        ValidaSeJaPossuiContaCartaoRetorno other = (ValidaSeJaPossuiContaCartaoRetorno) obj;
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
            ((this.numeroCartao==null && other.getNumeroCartao()==null) || 
             (this.numeroCartao!=null &&
              this.numeroCartao.equals(other.getNumeroCartao()))) &&
            ((this.numeroInternoConta==null && other.getNumeroInternoConta()==null) || 
             (this.numeroInternoConta!=null &&
              this.numeroInternoConta.equals(other.getNumeroInternoConta()))) &&
            this.permiteAssociarContaPagamentoCartao == other.isPermiteAssociarContaPagamentoCartao();
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
        if (getNumeroCartao() != null) {
            _hashCode += getNumeroCartao().hashCode();
        }
        if (getNumeroInternoConta() != null) {
            _hashCode += getNumeroInternoConta().hashCode();
        }
        _hashCode += (isPermiteAssociarContaPagamentoCartao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidaSeJaPossuiContaCartaoRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoRetorno"));
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
        elemField.setFieldName("numeroCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroInternoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroInternoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permiteAssociarContaPagamentoCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permiteAssociarContaPagamentoCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
