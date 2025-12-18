/**
 * ObterStatusSeguroReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ObterStatusSeguroReturn  implements java.io.Serializable {
    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private String mensagemDeErro;

    private String situacao;

    private String status;

    private Integer statusCodigo;

    private String statusDescricao;

    public ObterStatusSeguroReturn() {
    }

    public ObterStatusSeguroReturn(
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           String mensagemDeErro,
           String situacao,
           String status,
           Integer statusCodigo,
           String statusDescricao) {
           this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
           this.excecaoGenerica = excecaoGenerica;
           this.mensagemDeErro = mensagemDeErro;
           this.situacao = situacao;
           this.status = status;
           this.statusCodigo = statusCodigo;
           this.statusDescricao = statusDescricao;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this ObterStatusSeguroReturn.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this ObterStatusSeguroReturn.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this ObterStatusSeguroReturn.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this ObterStatusSeguroReturn.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the mensagemDeErro value for this ObterStatusSeguroReturn.
     * 
     * @return mensagemDeErro
     */
    public String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this ObterStatusSeguroReturn.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the situacao value for this ObterStatusSeguroReturn.
     * 
     * @return situacao
     */
    public String getSituacao() {
        return situacao;
    }


    /**
     * Sets the situacao value for this ObterStatusSeguroReturn.
     * 
     * @param situacao
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


    /**
     * Gets the status value for this ObterStatusSeguroReturn.
     * 
     * @return status
     */
    public String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ObterStatusSeguroReturn.
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Gets the statusCodigo value for this ObterStatusSeguroReturn.
     * 
     * @return statusCodigo
     */
    public Integer getStatusCodigo() {
        return statusCodigo;
    }


    /**
     * Sets the statusCodigo value for this ObterStatusSeguroReturn.
     * 
     * @param statusCodigo
     */
    public void setStatusCodigo(Integer statusCodigo) {
        this.statusCodigo = statusCodigo;
    }


    /**
     * Gets the statusDescricao value for this ObterStatusSeguroReturn.
     * 
     * @return statusDescricao
     */
    public String getStatusDescricao() {
        return statusDescricao;
    }


    /**
     * Sets the statusDescricao value for this ObterStatusSeguroReturn.
     * 
     * @param statusDescricao
     */
    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ObterStatusSeguroReturn)) return false;
        ObterStatusSeguroReturn other = (ObterStatusSeguroReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.excecaoDeRegraDeNegocio == other.isExcecaoDeRegraDeNegocio() &&
            this.excecaoGenerica == other.isExcecaoGenerica() &&
            ((this.mensagemDeErro==null && other.getMensagemDeErro()==null) || 
             (this.mensagemDeErro!=null &&
              this.mensagemDeErro.equals(other.getMensagemDeErro()))) &&
            ((this.situacao==null && other.getSituacao()==null) || 
             (this.situacao!=null &&
              this.situacao.equals(other.getSituacao()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusCodigo==null && other.getStatusCodigo()==null) || 
             (this.statusCodigo!=null &&
              this.statusCodigo.equals(other.getStatusCodigo()))) &&
            ((this.statusDescricao==null && other.getStatusDescricao()==null) || 
             (this.statusDescricao!=null &&
              this.statusDescricao.equals(other.getStatusDescricao())));
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
        _hashCode += (isExcecaoDeRegraDeNegocio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcecaoGenerica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagemDeErro() != null) {
            _hashCode += getMensagemDeErro().hashCode();
        }
        if (getSituacao() != null) {
            _hashCode += getSituacao().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusCodigo() != null) {
            _hashCode += getStatusCodigo().hashCode();
        }
        if (getStatusDescricao() != null) {
            _hashCode += getStatusDescricao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObterStatusSeguroReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusSeguroReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecaoDeRegraDeNegocio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecaoDeRegraDeNegocio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecaoGenerica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecaoGenerica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagemDeErro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemDeErro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("situacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "situacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDescricao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusDescricao"));
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
