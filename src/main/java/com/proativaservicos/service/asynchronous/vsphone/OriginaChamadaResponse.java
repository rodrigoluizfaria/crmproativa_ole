/**
 * OriginaChamadaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class OriginaChamadaResponse  extends com.proativaservicos.service.asynchronous.vsphone.CustomResponse  implements java.io.Serializable {
    private java.lang.String identificacaoChamada;

    private java.lang.String mensagem;

    private java.lang.String resposta;

    private boolean sucesso;

    public OriginaChamadaResponse() {
    }

    public OriginaChamadaResponse(
           com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes,
           java.lang.String identificacaoChamada,
           java.lang.String mensagem,
           java.lang.String resposta,
           boolean sucesso) {
        super(
            excecoes);
        this.identificacaoChamada = identificacaoChamada;
        this.mensagem = mensagem;
        this.resposta = resposta;
        this.sucesso = sucesso;
    }


    /**
     * Gets the identificacaoChamada value for this OriginaChamadaResponse.
     * 
     * @return identificacaoChamada
     */
    public java.lang.String getIdentificacaoChamada() {
        return identificacaoChamada;
    }


    /**
     * Sets the identificacaoChamada value for this OriginaChamadaResponse.
     * 
     * @param identificacaoChamada
     */
    public void setIdentificacaoChamada(java.lang.String identificacaoChamada) {
        this.identificacaoChamada = identificacaoChamada;
    }


    /**
     * Gets the mensagem value for this OriginaChamadaResponse.
     * 
     * @return mensagem
     */
    public java.lang.String getMensagem() {
        return mensagem;
    }


    /**
     * Sets the mensagem value for this OriginaChamadaResponse.
     * 
     * @param mensagem
     */
    public void setMensagem(java.lang.String mensagem) {
        this.mensagem = mensagem;
    }


    /**
     * Gets the resposta value for this OriginaChamadaResponse.
     * 
     * @return resposta
     */
    public java.lang.String getResposta() {
        return resposta;
    }


    /**
     * Sets the resposta value for this OriginaChamadaResponse.
     * 
     * @param resposta
     */
    public void setResposta(java.lang.String resposta) {
        this.resposta = resposta;
    }


    /**
     * Gets the sucesso value for this OriginaChamadaResponse.
     * 
     * @return sucesso
     */
    public boolean isSucesso() {
        return sucesso;
    }


    /**
     * Sets the sucesso value for this OriginaChamadaResponse.
     * 
     * @param sucesso
     */
    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OriginaChamadaResponse)) return false;
        OriginaChamadaResponse other = (OriginaChamadaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.identificacaoChamada==null && other.getIdentificacaoChamada()==null) || 
             (this.identificacaoChamada!=null &&
              this.identificacaoChamada.equals(other.getIdentificacaoChamada()))) &&
            ((this.mensagem==null && other.getMensagem()==null) || 
             (this.mensagem!=null &&
              this.mensagem.equals(other.getMensagem()))) &&
            ((this.resposta==null && other.getResposta()==null) || 
             (this.resposta!=null &&
              this.resposta.equals(other.getResposta()))) &&
            this.sucesso == other.isSucesso();
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
        if (getIdentificacaoChamada() != null) {
            _hashCode += getIdentificacaoChamada().hashCode();
        }
        if (getMensagem() != null) {
            _hashCode += getMensagem().hashCode();
        }
        if (getResposta() != null) {
            _hashCode += getResposta().hashCode();
        }
        _hashCode += (isSucesso() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OriginaChamadaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "OriginaChamadaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificacaoChamada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "identificacaoChamada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "mensagem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resposta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "resposta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sucesso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "sucesso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
