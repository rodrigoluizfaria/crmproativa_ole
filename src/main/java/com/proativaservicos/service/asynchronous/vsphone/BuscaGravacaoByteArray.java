/**
 * BuscaGravacaoByteArray.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class BuscaGravacaoByteArray  extends com.proativaservicos.service.asynchronous.vsphone.CustomResponse  implements java.io.Serializable {
    private byte[] arquivo;

    public BuscaGravacaoByteArray() {
    }

    public BuscaGravacaoByteArray(
    		com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes,
           byte[] arquivo) {
        super(
            excecoes);
        this.arquivo = arquivo;
    }


    /**
     * Gets the arquivo value for this BuscaGravacaoByteArray.
     * 
     * @return arquivo
     */
    public byte[] getArquivo() {
        return arquivo;
    }


    /**
     * Sets the arquivo value for this BuscaGravacaoByteArray.
     * 
     * @param arquivo
     */
    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscaGravacaoByteArray)) return false;
        BuscaGravacaoByteArray other = (BuscaGravacaoByteArray) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arquivo==null && other.getArquivo()==null) || 
             (this.arquivo!=null &&
              java.util.Arrays.equals(this.arquivo, other.getArquivo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getArquivo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArquivo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArquivo(), i);
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
        new org.apache.axis.description.TypeDesc(BuscaGravacaoByteArray.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BuscaGravacaoByteArray"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "arquivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
