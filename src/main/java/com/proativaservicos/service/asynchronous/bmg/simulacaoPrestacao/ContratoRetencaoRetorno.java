/**
 * ContratoRetencaoRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno;

public class ContratoRetencaoRetorno  implements java.io.Serializable {
    private java.lang.Double descontoConcedido;

    private java.lang.String descricaoServico;

    private java.lang.String numeroContratoInterno;

    private java.lang.Integer quantidadeParcelaAberto;

    private java.lang.Double valorDividaReal;

    private java.lang.Double valorPagar;

    public ContratoRetencaoRetorno() {
    }

    public ContratoRetencaoRetorno(
           java.lang.Double descontoConcedido,
           java.lang.String descricaoServico,
           java.lang.String numeroContratoInterno,
           java.lang.Integer quantidadeParcelaAberto,
           java.lang.Double valorDividaReal,
           java.lang.Double valorPagar) {
           this.descontoConcedido = descontoConcedido;
           this.descricaoServico = descricaoServico;
           this.numeroContratoInterno = numeroContratoInterno;
           this.quantidadeParcelaAberto = quantidadeParcelaAberto;
           this.valorDividaReal = valorDividaReal;
           this.valorPagar = valorPagar;
    }


    /**
     * Gets the descontoConcedido value for this ContratoRetencaoRetorno.
     * 
     * @return descontoConcedido
     */
    public java.lang.Double getDescontoConcedido() {
        return descontoConcedido;
    }


    /**
     * Sets the descontoConcedido value for this ContratoRetencaoRetorno.
     * 
     * @param descontoConcedido
     */
    public void setDescontoConcedido(java.lang.Double descontoConcedido) {
        this.descontoConcedido = descontoConcedido;
    }


    /**
     * Gets the descricaoServico value for this ContratoRetencaoRetorno.
     * 
     * @return descricaoServico
     */
    public java.lang.String getDescricaoServico() {
        return descricaoServico;
    }


    /**
     * Sets the descricaoServico value for this ContratoRetencaoRetorno.
     * 
     * @param descricaoServico
     */
    public void setDescricaoServico(java.lang.String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }


    /**
     * Gets the numeroContratoInterno value for this ContratoRetencaoRetorno.
     * 
     * @return numeroContratoInterno
     */
    public java.lang.String getNumeroContratoInterno() {
        return numeroContratoInterno;
    }


    /**
     * Sets the numeroContratoInterno value for this ContratoRetencaoRetorno.
     * 
     * @param numeroContratoInterno
     */
    public void setNumeroContratoInterno(java.lang.String numeroContratoInterno) {
        this.numeroContratoInterno = numeroContratoInterno;
    }


    /**
     * Gets the quantidadeParcelaAberto value for this ContratoRetencaoRetorno.
     * 
     * @return quantidadeParcelaAberto
     */
    public java.lang.Integer getQuantidadeParcelaAberto() {
        return quantidadeParcelaAberto;
    }


    /**
     * Sets the quantidadeParcelaAberto value for this ContratoRetencaoRetorno.
     * 
     * @param quantidadeParcelaAberto
     */
    public void setQuantidadeParcelaAberto(java.lang.Integer quantidadeParcelaAberto) {
        this.quantidadeParcelaAberto = quantidadeParcelaAberto;
    }


    /**
     * Gets the valorDividaReal value for this ContratoRetencaoRetorno.
     * 
     * @return valorDividaReal
     */
    public java.lang.Double getValorDividaReal() {
        return valorDividaReal;
    }


    /**
     * Sets the valorDividaReal value for this ContratoRetencaoRetorno.
     * 
     * @param valorDividaReal
     */
    public void setValorDividaReal(java.lang.Double valorDividaReal) {
        this.valorDividaReal = valorDividaReal;
    }


    /**
     * Gets the valorPagar value for this ContratoRetencaoRetorno.
     * 
     * @return valorPagar
     */
    public java.lang.Double getValorPagar() {
        return valorPagar;
    }


    /**
     * Sets the valorPagar value for this ContratoRetencaoRetorno.
     * 
     * @param valorPagar
     */
    public void setValorPagar(java.lang.Double valorPagar) {
        this.valorPagar = valorPagar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContratoRetencaoRetorno)) return false;
        ContratoRetencaoRetorno other = (ContratoRetencaoRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descontoConcedido==null && other.getDescontoConcedido()==null) || 
             (this.descontoConcedido!=null &&
              this.descontoConcedido.equals(other.getDescontoConcedido()))) &&
            ((this.descricaoServico==null && other.getDescricaoServico()==null) || 
             (this.descricaoServico!=null &&
              this.descricaoServico.equals(other.getDescricaoServico()))) &&
            ((this.numeroContratoInterno==null && other.getNumeroContratoInterno()==null) || 
             (this.numeroContratoInterno!=null &&
              this.numeroContratoInterno.equals(other.getNumeroContratoInterno()))) &&
            ((this.quantidadeParcelaAberto==null && other.getQuantidadeParcelaAberto()==null) || 
             (this.quantidadeParcelaAberto!=null &&
              this.quantidadeParcelaAberto.equals(other.getQuantidadeParcelaAberto()))) &&
            ((this.valorDividaReal==null && other.getValorDividaReal()==null) || 
             (this.valorDividaReal!=null &&
              this.valorDividaReal.equals(other.getValorDividaReal()))) &&
            ((this.valorPagar==null && other.getValorPagar()==null) || 
             (this.valorPagar!=null &&
              this.valorPagar.equals(other.getValorPagar())));
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
        if (getDescontoConcedido() != null) {
            _hashCode += getDescontoConcedido().hashCode();
        }
        if (getDescricaoServico() != null) {
            _hashCode += getDescricaoServico().hashCode();
        }
        if (getNumeroContratoInterno() != null) {
            _hashCode += getNumeroContratoInterno().hashCode();
        }
        if (getQuantidadeParcelaAberto() != null) {
            _hashCode += getQuantidadeParcelaAberto().hashCode();
        }
        if (getValorDividaReal() != null) {
            _hashCode += getValorDividaReal().hashCode();
        }
        if (getValorPagar() != null) {
            _hashCode += getValorPagar().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContratoRetencaoRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoRetencaoRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descontoConcedido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descontoConcedido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricaoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroContratoInterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroContratoInterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeParcelaAberto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeParcelaAberto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorDividaReal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorDividaReal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPagar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPagar"));
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
