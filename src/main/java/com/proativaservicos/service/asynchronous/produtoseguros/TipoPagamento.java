/**
 * TipoPagamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class TipoPagamento  implements java.io.Serializable {
    private String aliasDescricao;

    private Integer codigo;

    private String descricao;

    public TipoPagamento() {
    }

    public TipoPagamento(
           String aliasDescricao,
           Integer codigo,
           String descricao) {
           this.aliasDescricao = aliasDescricao;
           this.codigo = codigo;
           this.descricao = descricao;
    }


    /**
     * Gets the aliasDescricao value for this TipoPagamento.
     * 
     * @return aliasDescricao
     */
    public String getAliasDescricao() {
        return aliasDescricao;
    }


    /**
     * Sets the aliasDescricao value for this TipoPagamento.
     * 
     * @param aliasDescricao
     */
    public void setAliasDescricao(String aliasDescricao) {
        this.aliasDescricao = aliasDescricao;
    }


    /**
     * Gets the codigo value for this TipoPagamento.
     * 
     * @return codigo
     */
    public Integer getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this TipoPagamento.
     * 
     * @param codigo
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the descricao value for this TipoPagamento.
     * 
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this TipoPagamento.
     * 
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TipoPagamento)) return false;
        TipoPagamento other = (TipoPagamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aliasDescricao==null && other.getAliasDescricao()==null) || 
             (this.aliasDescricao!=null &&
              this.aliasDescricao.equals(other.getAliasDescricao()))) &&
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao())));
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
        if (getAliasDescricao() != null) {
            _hashCode += getAliasDescricao().hashCode();
        }
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoPagamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoPagamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aliasDescricao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aliasDescricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
