/**
 * StatusGrupoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class StatusGrupoResponse  extends com.proativaservicos.service.asynchronous.vsphone.CustomResponse  implements java.io.Serializable {
    private java.lang.String mensagem;

    private java.lang.String statusGrupo;

    private java.lang.Boolean sucesso;

    public StatusGrupoResponse() {
    }

    public StatusGrupoResponse(
           com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes,
           java.lang.String mensagem,
           java.lang.String statusGrupo,
           java.lang.Boolean sucesso) {
        super(
            excecoes);
        this.mensagem = mensagem;
        this.statusGrupo = statusGrupo;
        this.sucesso = sucesso;
    }


    /**
     * Gets the mensagem value for this StatusGrupoResponse.
     * 
     * @return mensagem
     */
    public java.lang.String getMensagem() {
        return mensagem;
    }


    /**
     * Sets the mensagem value for this StatusGrupoResponse.
     * 
     * @param mensagem
     */
    public void setMensagem(java.lang.String mensagem) {
        this.mensagem = mensagem;
    }


    /**
     * Gets the statusGrupo value for this StatusGrupoResponse.
     * 
     * @return statusGrupo
     */
    public java.lang.String getStatusGrupo() {
        return statusGrupo;
    }


    /**
     * Sets the statusGrupo value for this StatusGrupoResponse.
     * 
     * @param statusGrupo
     */
    public void setStatusGrupo(java.lang.String statusGrupo) {
        this.statusGrupo = statusGrupo;
    }


    /**
     * Gets the sucesso value for this StatusGrupoResponse.
     * 
     * @return sucesso
     */
    public java.lang.Boolean getSucesso() {
        return sucesso;
    }


    /**
     * Sets the sucesso value for this StatusGrupoResponse.
     * 
     * @param sucesso
     */
    public void setSucesso(java.lang.Boolean sucesso) {
        this.sucesso = sucesso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusGrupoResponse)) return false;
        StatusGrupoResponse other = (StatusGrupoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.mensagem==null && other.getMensagem()==null) || 
             (this.mensagem!=null &&
              this.mensagem.equals(other.getMensagem()))) &&
            ((this.statusGrupo==null && other.getStatusGrupo()==null) || 
             (this.statusGrupo!=null &&
              this.statusGrupo.equals(other.getStatusGrupo()))) &&
            ((this.sucesso==null && other.getSucesso()==null) || 
             (this.sucesso!=null &&
              this.sucesso.equals(other.getSucesso())));
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
        if (getMensagem() != null) {
            _hashCode += getMensagem().hashCode();
        }
        if (getStatusGrupo() != null) {
            _hashCode += getStatusGrupo().hashCode();
        }
        if (getSucesso() != null) {
            _hashCode += getSucesso().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusGrupoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusGrupoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "mensagem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusGrupo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "statusGrupo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sucesso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "sucesso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
