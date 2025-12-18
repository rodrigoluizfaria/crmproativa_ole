/**
 * ValidacaoElegibilidadeUpgradeReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ValidacaoElegibilidadeUpgradeReturn  implements java.io.Serializable {
    private boolean elegivel;

    private String mensagem;

    private String situacao;

    public ValidacaoElegibilidadeUpgradeReturn() {
    }

    public ValidacaoElegibilidadeUpgradeReturn(
           boolean elegivel,
           String mensagem,
           String situacao) {
           this.elegivel = elegivel;
           this.mensagem = mensagem;
           this.situacao = situacao;
    }


    /**
     * Gets the elegivel value for this ValidacaoElegibilidadeUpgradeReturn.
     * 
     * @return elegivel
     */
    public boolean isElegivel() {
        return elegivel;
    }


    /**
     * Sets the elegivel value for this ValidacaoElegibilidadeUpgradeReturn.
     * 
     * @param elegivel
     */
    public void setElegivel(boolean elegivel) {
        this.elegivel = elegivel;
    }


    /**
     * Gets the mensagem value for this ValidacaoElegibilidadeUpgradeReturn.
     * 
     * @return mensagem
     */
    public String getMensagem() {
        return mensagem;
    }


    /**
     * Sets the mensagem value for this ValidacaoElegibilidadeUpgradeReturn.
     * 
     * @param mensagem
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    /**
     * Gets the situacao value for this ValidacaoElegibilidadeUpgradeReturn.
     * 
     * @return situacao
     */
    public String getSituacao() {
        return situacao;
    }


    /**
     * Sets the situacao value for this ValidacaoElegibilidadeUpgradeReturn.
     * 
     * @param situacao
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ValidacaoElegibilidadeUpgradeReturn)) return false;
        ValidacaoElegibilidadeUpgradeReturn other = (ValidacaoElegibilidadeUpgradeReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.elegivel == other.isElegivel() &&
            ((this.mensagem==null && other.getMensagem()==null) || 
             (this.mensagem!=null &&
              this.mensagem.equals(other.getMensagem()))) &&
            ((this.situacao==null && other.getSituacao()==null) || 
             (this.situacao!=null &&
              this.situacao.equals(other.getSituacao())));
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
        _hashCode += (isElegivel() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagem() != null) {
            _hashCode += getMensagem().hashCode();
        }
        if (getSituacao() != null) {
            _hashCode += getSituacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidacaoElegibilidadeUpgradeReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeUpgradeReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elegivel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "elegivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("situacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "situacao"));
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
