/**
 * InclusaoTelefonesComplementaresReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class InclusaoTelefonesComplementaresReturn  implements java.io.Serializable {
    private boolean excecao;

    private String mensagemRetorno;

    public InclusaoTelefonesComplementaresReturn() {
    }

    public InclusaoTelefonesComplementaresReturn(
           boolean excecao,
           String mensagemRetorno) {
           this.excecao = excecao;
           this.mensagemRetorno = mensagemRetorno;
    }


    /**
     * Gets the excecao value for this InclusaoTelefonesComplementaresReturn.
     * 
     * @return excecao
     */
    public boolean isExcecao() {
        return excecao;
    }


    /**
     * Sets the excecao value for this InclusaoTelefonesComplementaresReturn.
     * 
     * @param excecao
     */
    public void setExcecao(boolean excecao) {
        this.excecao = excecao;
    }


    /**
     * Gets the mensagemRetorno value for this InclusaoTelefonesComplementaresReturn.
     * 
     * @return mensagemRetorno
     */
    public String getMensagemRetorno() {
        return mensagemRetorno;
    }


    /**
     * Sets the mensagemRetorno value for this InclusaoTelefonesComplementaresReturn.
     * 
     * @param mensagemRetorno
     */
    public void setMensagemRetorno(String mensagemRetorno) {
        this.mensagemRetorno = mensagemRetorno;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof InclusaoTelefonesComplementaresReturn)) return false;
        InclusaoTelefonesComplementaresReturn other = (InclusaoTelefonesComplementaresReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.excecao == other.isExcecao() &&
            ((this.mensagemRetorno==null && other.getMensagemRetorno()==null) || 
             (this.mensagemRetorno!=null &&
              this.mensagemRetorno.equals(other.getMensagemRetorno())));
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
        _hashCode += (isExcecao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagemRetorno() != null) {
            _hashCode += getMensagemRetorno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InclusaoTelefonesComplementaresReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "InclusaoTelefonesComplementaresReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagemRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemRetorno"));
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
