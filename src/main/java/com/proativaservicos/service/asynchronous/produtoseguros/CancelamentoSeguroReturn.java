/**
 * CancelamentoSeguroReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class CancelamentoSeguroReturn  implements java.io.Serializable {
    private boolean executadoComSucessso;

    private String mensagem;

    public CancelamentoSeguroReturn() {
    }

    public CancelamentoSeguroReturn(
           boolean executadoComSucessso,
           String mensagem) {
           this.executadoComSucessso = executadoComSucessso;
           this.mensagem = mensagem;
    }


    /**
     * Gets the executadoComSucessso value for this CancelamentoSeguroReturn.
     * 
     * @return executadoComSucessso
     */
    public boolean isExecutadoComSucessso() {
        return executadoComSucessso;
    }


    /**
     * Sets the executadoComSucessso value for this CancelamentoSeguroReturn.
     * 
     * @param executadoComSucessso
     */
    public void setExecutadoComSucessso(boolean executadoComSucessso) {
        this.executadoComSucessso = executadoComSucessso;
    }


    /**
     * Gets the mensagem value for this CancelamentoSeguroReturn.
     * 
     * @return mensagem
     */
    public String getMensagem() {
        return mensagem;
    }


    /**
     * Sets the mensagem value for this CancelamentoSeguroReturn.
     * 
     * @param mensagem
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CancelamentoSeguroReturn)) return false;
        CancelamentoSeguroReturn other = (CancelamentoSeguroReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.executadoComSucessso == other.isExecutadoComSucessso() &&
            ((this.mensagem==null && other.getMensagem()==null) || 
             (this.mensagem!=null &&
              this.mensagem.equals(other.getMensagem())));
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
        _hashCode += (isExecutadoComSucessso() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagem() != null) {
            _hashCode += getMensagem().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelamentoSeguroReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoSeguroReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executadoComSucessso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "executadoComSucessso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagem"));
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
