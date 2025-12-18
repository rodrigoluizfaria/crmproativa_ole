/**
 * ConsistenciasSegurosReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ConsistenciasSegurosReturn  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasProdutoSeguro[] consistenciasSeguros;

    private boolean excecao;

    private String mensagemRetorno;

    public ConsistenciasSegurosReturn() {
    }

    public ConsistenciasSegurosReturn(
           com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasProdutoSeguro[] consistenciasSeguros,
           boolean excecao,
           String mensagemRetorno) {
           this.consistenciasSeguros = consistenciasSeguros;
           this.excecao = excecao;
           this.mensagemRetorno = mensagemRetorno;
    }


    /**
     * Gets the consistenciasSeguros value for this ConsistenciasSegurosReturn.
     * 
     * @return consistenciasSeguros
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasProdutoSeguro[] getConsistenciasSeguros() {
        return consistenciasSeguros;
    }


    /**
     * Sets the consistenciasSeguros value for this ConsistenciasSegurosReturn.
     * 
     * @param consistenciasSeguros
     */
    public void setConsistenciasSeguros(com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasProdutoSeguro[] consistenciasSeguros) {
        this.consistenciasSeguros = consistenciasSeguros;
    }


    /**
     * Gets the excecao value for this ConsistenciasSegurosReturn.
     * 
     * @return excecao
     */
    public boolean isExcecao() {
        return excecao;
    }


    /**
     * Sets the excecao value for this ConsistenciasSegurosReturn.
     * 
     * @param excecao
     */
    public void setExcecao(boolean excecao) {
        this.excecao = excecao;
    }


    /**
     * Gets the mensagemRetorno value for this ConsistenciasSegurosReturn.
     * 
     * @return mensagemRetorno
     */
    public String getMensagemRetorno() {
        return mensagemRetorno;
    }


    /**
     * Sets the mensagemRetorno value for this ConsistenciasSegurosReturn.
     * 
     * @param mensagemRetorno
     */
    public void setMensagemRetorno(String mensagemRetorno) {
        this.mensagemRetorno = mensagemRetorno;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ConsistenciasSegurosReturn)) return false;
        ConsistenciasSegurosReturn other = (ConsistenciasSegurosReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consistenciasSeguros==null && other.getConsistenciasSeguros()==null) || 
             (this.consistenciasSeguros!=null &&
              java.util.Arrays.equals(this.consistenciasSeguros, other.getConsistenciasSeguros()))) &&
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
        if (getConsistenciasSeguros() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConsistenciasSeguros());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getConsistenciasSeguros(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isExcecao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagemRetorno() != null) {
            _hashCode += getMensagemRetorno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsistenciasSegurosReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciasSegurosReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consistenciasSeguros");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consistenciasSeguros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciasProdutoSeguro"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
