/**
 * ProdutoReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn;

public class ProdutoReturn  implements java.io.Serializable {
    private java.lang.Integer codigo;

    private java.lang.String descricao;

    private com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.FormaEnvioRetorno[] formasEnvio;

    private com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[] produtosAssociados;

    public ProdutoReturn() {
    }

    public ProdutoReturn(
           java.lang.Integer codigo,
           java.lang.String descricao,
           com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.FormaEnvioRetorno[] formasEnvio,
           com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[] produtosAssociados) {
           this.codigo = codigo;
           this.descricao = descricao;
           this.formasEnvio = formasEnvio;
           this.produtosAssociados = produtosAssociados;
    }


    /**
     * Gets the codigo value for this ProdutoReturn.
     * 
     * @return codigo
     */
    public java.lang.Integer getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this ProdutoReturn.
     * 
     * @param codigo
     */
    public void setCodigo(java.lang.Integer codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the descricao value for this ProdutoReturn.
     * 
     * @return descricao
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this ProdutoReturn.
     * 
     * @param descricao
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }


    /**
     * Gets the formasEnvio value for this ProdutoReturn.
     * 
     * @return formasEnvio
     */
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.FormaEnvioRetorno[] getFormasEnvio() {
        return formasEnvio;
    }


    /**
     * Sets the formasEnvio value for this ProdutoReturn.
     * 
     * @param formasEnvio
     */
    public void setFormasEnvio(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.FormaEnvioRetorno[] formasEnvio) {
        this.formasEnvio = formasEnvio;
    }


    /**
     * Gets the produtosAssociados value for this ProdutoReturn.
     * 
     * @return produtosAssociados
     */
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[] getProdutosAssociados() {
        return produtosAssociados;
    }


    /**
     * Sets the produtosAssociados value for this ProdutoReturn.
     * 
     * @param produtosAssociados
     */
    public void setProdutosAssociados(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[] produtosAssociados) {
        this.produtosAssociados = produtosAssociados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProdutoReturn)) return false;
        ProdutoReturn other = (ProdutoReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao()))) &&
            ((this.formasEnvio==null && other.getFormasEnvio()==null) || 
             (this.formasEnvio!=null &&
              java.util.Arrays.equals(this.formasEnvio, other.getFormasEnvio()))) &&
            ((this.produtosAssociados==null && other.getProdutosAssociados()==null) || 
             (this.produtosAssociados!=null &&
              java.util.Arrays.equals(this.produtosAssociados, other.getProdutosAssociados())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        if (getFormasEnvio() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFormasEnvio());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFormasEnvio(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProdutosAssociados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProdutosAssociados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProdutosAssociados(), i);
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
        new org.apache.axis.description.TypeDesc(ProdutoReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ProdutoReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formasEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formasEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("produtosAssociados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "produtosAssociados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ProdutoReturn"));
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
