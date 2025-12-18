/**
 * ValidacaoElegibilidadeReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ValidacaoElegibilidadeReturn  implements java.io.Serializable {
    private Integer codigoProdutoPN;

    private Integer codigoRetorno;

    private String codigoSeguroEntidadeReguladora;

    private com.proativaservicos.service.asynchronous.produtoseguros.DadosCondicaoVendaSeguro condicaoVendaSeguro;

    private String mensagem;

    private String nomeCorretora;

    private String numeroApolice;

    private String situacao;

    private String stackTrace;

    public ValidacaoElegibilidadeReturn() {
    }

    public ValidacaoElegibilidadeReturn(
           Integer codigoProdutoPN,
           Integer codigoRetorno,
           String codigoSeguroEntidadeReguladora,
           com.proativaservicos.service.asynchronous.produtoseguros.DadosCondicaoVendaSeguro condicaoVendaSeguro,
           String mensagem,
           String nomeCorretora,
           String numeroApolice,
           String situacao,
           String stackTrace) {
           this.codigoProdutoPN = codigoProdutoPN;
           this.codigoRetorno = codigoRetorno;
           this.codigoSeguroEntidadeReguladora = codigoSeguroEntidadeReguladora;
           this.condicaoVendaSeguro = condicaoVendaSeguro;
           this.mensagem = mensagem;
           this.nomeCorretora = nomeCorretora;
           this.numeroApolice = numeroApolice;
           this.situacao = situacao;
           this.stackTrace = stackTrace;
    }


    /**
     * Gets the codigoProdutoPN value for this ValidacaoElegibilidadeReturn.
     * 
     * @return codigoProdutoPN
     */
    public Integer getCodigoProdutoPN() {
        return codigoProdutoPN;
    }


    /**
     * Sets the codigoProdutoPN value for this ValidacaoElegibilidadeReturn.
     * 
     * @param codigoProdutoPN
     */
    public void setCodigoProdutoPN(Integer codigoProdutoPN) {
        this.codigoProdutoPN = codigoProdutoPN;
    }


    /**
     * Gets the codigoRetorno value for this ValidacaoElegibilidadeReturn.
     * 
     * @return codigoRetorno
     */
    public Integer getCodigoRetorno() {
        return codigoRetorno;
    }


    /**
     * Sets the codigoRetorno value for this ValidacaoElegibilidadeReturn.
     * 
     * @param codigoRetorno
     */
    public void setCodigoRetorno(Integer codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }


    /**
     * Gets the codigoSeguroEntidadeReguladora value for this ValidacaoElegibilidadeReturn.
     * 
     * @return codigoSeguroEntidadeReguladora
     */
    public String getCodigoSeguroEntidadeReguladora() {
        return codigoSeguroEntidadeReguladora;
    }


    /**
     * Sets the codigoSeguroEntidadeReguladora value for this ValidacaoElegibilidadeReturn.
     * 
     * @param codigoSeguroEntidadeReguladora
     */
    public void setCodigoSeguroEntidadeReguladora(String codigoSeguroEntidadeReguladora) {
        this.codigoSeguroEntidadeReguladora = codigoSeguroEntidadeReguladora;
    }


    /**
     * Gets the condicaoVendaSeguro value for this ValidacaoElegibilidadeReturn.
     * 
     * @return condicaoVendaSeguro
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.DadosCondicaoVendaSeguro getCondicaoVendaSeguro() {
        return condicaoVendaSeguro;
    }


    /**
     * Sets the condicaoVendaSeguro value for this ValidacaoElegibilidadeReturn.
     * 
     * @param condicaoVendaSeguro
     */
    public void setCondicaoVendaSeguro(com.proativaservicos.service.asynchronous.produtoseguros.DadosCondicaoVendaSeguro condicaoVendaSeguro) {
        this.condicaoVendaSeguro = condicaoVendaSeguro;
    }


    /**
     * Gets the mensagem value for this ValidacaoElegibilidadeReturn.
     * 
     * @return mensagem
     */
    public String getMensagem() {
        return mensagem;
    }


    /**
     * Sets the mensagem value for this ValidacaoElegibilidadeReturn.
     * 
     * @param mensagem
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    /**
     * Gets the nomeCorretora value for this ValidacaoElegibilidadeReturn.
     * 
     * @return nomeCorretora
     */
    public String getNomeCorretora() {
        return nomeCorretora;
    }


    /**
     * Sets the nomeCorretora value for this ValidacaoElegibilidadeReturn.
     * 
     * @param nomeCorretora
     */
    public void setNomeCorretora(String nomeCorretora) {
        this.nomeCorretora = nomeCorretora;
    }


    /**
     * Gets the numeroApolice value for this ValidacaoElegibilidadeReturn.
     * 
     * @return numeroApolice
     */
    public String getNumeroApolice() {
        return numeroApolice;
    }


    /**
     * Sets the numeroApolice value for this ValidacaoElegibilidadeReturn.
     * 
     * @param numeroApolice
     */
    public void setNumeroApolice(String numeroApolice) {
        this.numeroApolice = numeroApolice;
    }


    /**
     * Gets the situacao value for this ValidacaoElegibilidadeReturn.
     * 
     * @return situacao
     */
    public String getSituacao() {
        return situacao;
    }


    /**
     * Sets the situacao value for this ValidacaoElegibilidadeReturn.
     * 
     * @param situacao
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


    /**
     * Gets the stackTrace value for this ValidacaoElegibilidadeReturn.
     * 
     * @return stackTrace
     */
    public String getStackTrace() {
        return stackTrace;
    }


    /**
     * Sets the stackTrace value for this ValidacaoElegibilidadeReturn.
     * 
     * @param stackTrace
     */
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ValidacaoElegibilidadeReturn)) return false;
        ValidacaoElegibilidadeReturn other = (ValidacaoElegibilidadeReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoProdutoPN==null && other.getCodigoProdutoPN()==null) || 
             (this.codigoProdutoPN!=null &&
              this.codigoProdutoPN.equals(other.getCodigoProdutoPN()))) &&
            ((this.codigoRetorno==null && other.getCodigoRetorno()==null) || 
             (this.codigoRetorno!=null &&
              this.codigoRetorno.equals(other.getCodigoRetorno()))) &&
            ((this.codigoSeguroEntidadeReguladora==null && other.getCodigoSeguroEntidadeReguladora()==null) || 
             (this.codigoSeguroEntidadeReguladora!=null &&
              this.codigoSeguroEntidadeReguladora.equals(other.getCodigoSeguroEntidadeReguladora()))) &&
            ((this.condicaoVendaSeguro==null && other.getCondicaoVendaSeguro()==null) || 
             (this.condicaoVendaSeguro!=null &&
              this.condicaoVendaSeguro.equals(other.getCondicaoVendaSeguro()))) &&
            ((this.mensagem==null && other.getMensagem()==null) || 
             (this.mensagem!=null &&
              this.mensagem.equals(other.getMensagem()))) &&
            ((this.nomeCorretora==null && other.getNomeCorretora()==null) || 
             (this.nomeCorretora!=null &&
              this.nomeCorretora.equals(other.getNomeCorretora()))) &&
            ((this.numeroApolice==null && other.getNumeroApolice()==null) || 
             (this.numeroApolice!=null &&
              this.numeroApolice.equals(other.getNumeroApolice()))) &&
            ((this.situacao==null && other.getSituacao()==null) || 
             (this.situacao!=null &&
              this.situacao.equals(other.getSituacao()))) &&
            ((this.stackTrace==null && other.getStackTrace()==null) || 
             (this.stackTrace!=null &&
              this.stackTrace.equals(other.getStackTrace())));
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
        if (getCodigoProdutoPN() != null) {
            _hashCode += getCodigoProdutoPN().hashCode();
        }
        if (getCodigoRetorno() != null) {
            _hashCode += getCodigoRetorno().hashCode();
        }
        if (getCodigoSeguroEntidadeReguladora() != null) {
            _hashCode += getCodigoSeguroEntidadeReguladora().hashCode();
        }
        if (getCondicaoVendaSeguro() != null) {
            _hashCode += getCondicaoVendaSeguro().hashCode();
        }
        if (getMensagem() != null) {
            _hashCode += getMensagem().hashCode();
        }
        if (getNomeCorretora() != null) {
            _hashCode += getNomeCorretora().hashCode();
        }
        if (getNumeroApolice() != null) {
            _hashCode += getNumeroApolice().hashCode();
        }
        if (getSituacao() != null) {
            _hashCode += getSituacao().hashCode();
        }
        if (getStackTrace() != null) {
            _hashCode += getStackTrace().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidacaoElegibilidadeReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProdutoPN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoProdutoPN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSeguroEntidadeReguladora");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSeguroEntidadeReguladora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("condicaoVendaSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "condicaoVendaSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCondicaoVendaSeguro"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeCorretora");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeCorretora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroApolice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroApolice"));
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
        elemField.setFieldName("stackTrace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stackTrace"));
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
