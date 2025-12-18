/**
 * TestaTelefoneResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class TestaTelefoneResponse  extends com.proativaservicos.service.asynchronous.vsphone.CustomResponse  implements java.io.Serializable {
    private boolean atendeu;

    private java.lang.String atendido;

    private long duracao;

    public TestaTelefoneResponse() {
    }

    public TestaTelefoneResponse(
           com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes,
           boolean atendeu,
           java.lang.String atendido,
           long duracao) {
        super(
            excecoes);
        this.atendeu = atendeu;
        this.atendido = atendido;
        this.duracao = duracao;
    }


    /**
     * Gets the atendeu value for this TestaTelefoneResponse.
     * 
     * @return atendeu
     */
    public boolean isAtendeu() {
        return atendeu;
    }


    /**
     * Sets the atendeu value for this TestaTelefoneResponse.
     * 
     * @param atendeu
     */
    public void setAtendeu(boolean atendeu) {
        this.atendeu = atendeu;
    }


    /**
     * Gets the atendido value for this TestaTelefoneResponse.
     * 
     * @return atendido
     */
    public java.lang.String getAtendido() {
        return atendido;
    }


    /**
     * Sets the atendido value for this TestaTelefoneResponse.
     * 
     * @param atendido
     */
    public void setAtendido(java.lang.String atendido) {
        this.atendido = atendido;
    }


    /**
     * Gets the duracao value for this TestaTelefoneResponse.
     * 
     * @return duracao
     */
    public long getDuracao() {
        return duracao;
    }


    /**
     * Sets the duracao value for this TestaTelefoneResponse.
     * 
     * @param duracao
     */
    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestaTelefoneResponse)) return false;
        TestaTelefoneResponse other = (TestaTelefoneResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.atendeu == other.isAtendeu() &&
            ((this.atendido==null && other.getAtendido()==null) || 
             (this.atendido!=null &&
              this.atendido.equals(other.getAtendido()))) &&
            this.duracao == other.getDuracao();
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
        _hashCode += (isAtendeu() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAtendido() != null) {
            _hashCode += getAtendido().hashCode();
        }
        _hashCode += new Long(getDuracao()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestaTelefoneResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "TestaTelefoneResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("atendeu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "atendeu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("atendido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "atendido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "duracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
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
