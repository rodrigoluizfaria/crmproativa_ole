/**
 * OcorrenciaProdutoSeguroReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class OcorrenciaProdutoSeguroReturn  implements java.io.Serializable {
    private boolean executadoComSucessso;

    private String mensagemDeErro;

    public OcorrenciaProdutoSeguroReturn() {
    }

    public OcorrenciaProdutoSeguroReturn(
           boolean executadoComSucessso,
           String mensagemDeErro) {
           this.executadoComSucessso = executadoComSucessso;
           this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the executadoComSucessso value for this OcorrenciaProdutoSeguroReturn.
     * 
     * @return executadoComSucessso
     */
    public boolean isExecutadoComSucessso() {
        return executadoComSucessso;
    }


    /**
     * Sets the executadoComSucessso value for this OcorrenciaProdutoSeguroReturn.
     * 
     * @param executadoComSucessso
     */
    public void setExecutadoComSucessso(boolean executadoComSucessso) {
        this.executadoComSucessso = executadoComSucessso;
    }


    /**
     * Gets the mensagemDeErro value for this OcorrenciaProdutoSeguroReturn.
     * 
     * @return mensagemDeErro
     */
    public String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this OcorrenciaProdutoSeguroReturn.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof OcorrenciaProdutoSeguroReturn)) return false;
        OcorrenciaProdutoSeguroReturn other = (OcorrenciaProdutoSeguroReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.executadoComSucessso == other.isExecutadoComSucessso() &&
            ((this.mensagemDeErro==null && other.getMensagemDeErro()==null) || 
             (this.mensagemDeErro!=null &&
              this.mensagemDeErro.equals(other.getMensagemDeErro())));
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
        if (getMensagemDeErro() != null) {
            _hashCode += getMensagemDeErro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OcorrenciaProdutoSeguroReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "OcorrenciaProdutoSeguroReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executadoComSucessso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "executadoComSucessso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagemDeErro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemDeErro"));
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
