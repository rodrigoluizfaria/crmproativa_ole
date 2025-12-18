/**
 * DadosConsultaIN100.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.in100;

public class DadosConsultaIN100  extends com.proativaservicos.service.asynchronous.bmg.in100.WebServiceParameter  implements java.io.Serializable {
    private java.lang.Long numeroSolicitacao;

    private java.lang.String token;

    public DadosConsultaIN100() {
    }

    public DadosConsultaIN100(
           java.lang.String login,
           java.lang.String senha,
           java.lang.Long numeroSolicitacao,
           java.lang.String token) {
        super(
            login,
            senha);
        this.numeroSolicitacao = numeroSolicitacao;
        this.token = token;
    }


    /**
     * Gets the numeroSolicitacao value for this DadosConsultaIN100.
     * 
     * @return numeroSolicitacao
     */
    public java.lang.Long getNumeroSolicitacao() {
        return numeroSolicitacao;
    }


    /**
     * Sets the numeroSolicitacao value for this DadosConsultaIN100.
     * 
     * @param numeroSolicitacao
     */
    public void setNumeroSolicitacao(java.lang.Long numeroSolicitacao) {
        this.numeroSolicitacao = numeroSolicitacao;
    }


    /**
     * Gets the token value for this DadosConsultaIN100.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this DadosConsultaIN100.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DadosConsultaIN100)) return false;
        DadosConsultaIN100 other = (DadosConsultaIN100) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.numeroSolicitacao==null && other.getNumeroSolicitacao()==null) || 
             (this.numeroSolicitacao!=null &&
              this.numeroSolicitacao.equals(other.getNumeroSolicitacao()))) &&
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken())));
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
        if (getNumeroSolicitacao() != null) {
            _hashCode += getNumeroSolicitacao().hashCode();
        }
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DadosConsultaIN100.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosConsultaIN100"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSolicitacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroSolicitacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
