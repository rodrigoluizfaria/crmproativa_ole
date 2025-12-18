/**
 * ObterStatusCompletoPropostaSeguroReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ObterStatusCompletoPropostaSeguroReturn  implements java.io.Serializable {
    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private String mensagemDeErro;

    private String situacao;

    private Integer statusPropostaCodigo;

    private String statusPropostaDescricao;

    private Integer statusSeguroCodigo;

    private String statusSeguroDescricao;

    public ObterStatusCompletoPropostaSeguroReturn() {
    }

    public ObterStatusCompletoPropostaSeguroReturn(
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           String mensagemDeErro,
           String situacao,
           Integer statusPropostaCodigo,
           String statusPropostaDescricao,
           Integer statusSeguroCodigo,
           String statusSeguroDescricao) {
           this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
           this.excecaoGenerica = excecaoGenerica;
           this.mensagemDeErro = mensagemDeErro;
           this.situacao = situacao;
           this.statusPropostaCodigo = statusPropostaCodigo;
           this.statusPropostaDescricao = statusPropostaDescricao;
           this.statusSeguroCodigo = statusSeguroCodigo;
           this.statusSeguroDescricao = statusSeguroDescricao;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the mensagemDeErro value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @return mensagemDeErro
     */
    public String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the situacao value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @return situacao
     */
    public String getSituacao() {
        return situacao;
    }


    /**
     * Sets the situacao value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @param situacao
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


    /**
     * Gets the statusPropostaCodigo value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @return statusPropostaCodigo
     */
    public Integer getStatusPropostaCodigo() {
        return statusPropostaCodigo;
    }


    /**
     * Sets the statusPropostaCodigo value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @param statusPropostaCodigo
     */
    public void setStatusPropostaCodigo(Integer statusPropostaCodigo) {
        this.statusPropostaCodigo = statusPropostaCodigo;
    }


    /**
     * Gets the statusPropostaDescricao value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @return statusPropostaDescricao
     */
    public String getStatusPropostaDescricao() {
        return statusPropostaDescricao;
    }


    /**
     * Sets the statusPropostaDescricao value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @param statusPropostaDescricao
     */
    public void setStatusPropostaDescricao(String statusPropostaDescricao) {
        this.statusPropostaDescricao = statusPropostaDescricao;
    }


    /**
     * Gets the statusSeguroCodigo value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @return statusSeguroCodigo
     */
    public Integer getStatusSeguroCodigo() {
        return statusSeguroCodigo;
    }


    /**
     * Sets the statusSeguroCodigo value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @param statusSeguroCodigo
     */
    public void setStatusSeguroCodigo(Integer statusSeguroCodigo) {
        this.statusSeguroCodigo = statusSeguroCodigo;
    }


    /**
     * Gets the statusSeguroDescricao value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @return statusSeguroDescricao
     */
    public String getStatusSeguroDescricao() {
        return statusSeguroDescricao;
    }


    /**
     * Sets the statusSeguroDescricao value for this ObterStatusCompletoPropostaSeguroReturn.
     * 
     * @param statusSeguroDescricao
     */
    public void setStatusSeguroDescricao(String statusSeguroDescricao) {
        this.statusSeguroDescricao = statusSeguroDescricao;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ObterStatusCompletoPropostaSeguroReturn)) return false;
        ObterStatusCompletoPropostaSeguroReturn other = (ObterStatusCompletoPropostaSeguroReturn) obj;
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
            ((this.statusPropostaCodigo==null && other.getStatusPropostaCodigo()==null) || 
             (this.statusPropostaCodigo!=null &&
              this.statusPropostaCodigo.equals(other.getStatusPropostaCodigo()))) &&
            ((this.statusPropostaDescricao==null && other.getStatusPropostaDescricao()==null) || 
             (this.statusPropostaDescricao!=null &&
              this.statusPropostaDescricao.equals(other.getStatusPropostaDescricao()))) &&
            ((this.statusSeguroCodigo==null && other.getStatusSeguroCodigo()==null) || 
             (this.statusSeguroCodigo!=null &&
              this.statusSeguroCodigo.equals(other.getStatusSeguroCodigo()))) &&
            ((this.statusSeguroDescricao==null && other.getStatusSeguroDescricao()==null) || 
             (this.statusSeguroDescricao!=null &&
              this.statusSeguroDescricao.equals(other.getStatusSeguroDescricao())));
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
        if (getStatusPropostaCodigo() != null) {
            _hashCode += getStatusPropostaCodigo().hashCode();
        }
        if (getStatusPropostaDescricao() != null) {
            _hashCode += getStatusPropostaDescricao().hashCode();
        }
        if (getStatusSeguroCodigo() != null) {
            _hashCode += getStatusSeguroCodigo().hashCode();
        }
        if (getStatusSeguroDescricao() != null) {
            _hashCode += getStatusSeguroDescricao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObterStatusCompletoPropostaSeguroReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusCompletoPropostaSeguroReturn"));
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
        elemField.setFieldName("statusPropostaCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusPropostaCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusPropostaDescricao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusPropostaDescricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusSeguroCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusSeguroCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusSeguroDescricao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusSeguroDescricao"));
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
