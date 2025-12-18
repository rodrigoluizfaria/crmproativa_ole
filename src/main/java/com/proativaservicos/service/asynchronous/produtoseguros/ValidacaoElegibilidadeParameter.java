/**
 * ValidacaoElegibilidadeParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ValidacaoElegibilidadeParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private Integer codigoEntidade;

    private Short codigoSeguro;

    private String cpfCliente;

    private java.util.Calendar dataNascimento;

    private Boolean indicativoCarencia;

    private Long numeroContaInterna;

    private Integer prazoEmprestimo;

    private Integer tipoPagamentoSeguro;

    private String tipoVinculo;

    public ValidacaoElegibilidadeParameter() {
    }

    public ValidacaoElegibilidadeParameter(
           String login,
           String senha,
           Integer codigoEntidade,
           Short codigoSeguro,
           String cpfCliente,
           java.util.Calendar dataNascimento,
           Boolean indicativoCarencia,
           Long numeroContaInterna,
           Integer prazoEmprestimo,
           Integer tipoPagamentoSeguro,
           String tipoVinculo) {
        super(
            login,
            senha);
        this.codigoEntidade = codigoEntidade;
        this.codigoSeguro = codigoSeguro;
        this.cpfCliente = cpfCliente;
        this.dataNascimento = dataNascimento;
        this.indicativoCarencia = indicativoCarencia;
        this.numeroContaInterna = numeroContaInterna;
        this.prazoEmprestimo = prazoEmprestimo;
        this.tipoPagamentoSeguro = tipoPagamentoSeguro;
        this.tipoVinculo = tipoVinculo;
    }


    /**
     * Gets the codigoEntidade value for this ValidacaoElegibilidadeParameter.
     * 
     * @return codigoEntidade
     */
    public Integer getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this ValidacaoElegibilidadeParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(Integer codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoSeguro value for this ValidacaoElegibilidadeParameter.
     * 
     * @return codigoSeguro
     */
    public Short getCodigoSeguro() {
        return codigoSeguro;
    }


    /**
     * Sets the codigoSeguro value for this ValidacaoElegibilidadeParameter.
     * 
     * @param codigoSeguro
     */
    public void setCodigoSeguro(Short codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }


    /**
     * Gets the cpfCliente value for this ValidacaoElegibilidadeParameter.
     * 
     * @return cpfCliente
     */
    public String getCpfCliente() {
        return cpfCliente;
    }


    /**
     * Sets the cpfCliente value for this ValidacaoElegibilidadeParameter.
     * 
     * @param cpfCliente
     */
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }


    /**
     * Gets the dataNascimento value for this ValidacaoElegibilidadeParameter.
     * 
     * @return dataNascimento
     */
    public java.util.Calendar getDataNascimento() {
        return dataNascimento;
    }


    /**
     * Sets the dataNascimento value for this ValidacaoElegibilidadeParameter.
     * 
     * @param dataNascimento
     */
    public void setDataNascimento(java.util.Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    /**
     * Gets the indicativoCarencia value for this ValidacaoElegibilidadeParameter.
     * 
     * @return indicativoCarencia
     */
    public Boolean getIndicativoCarencia() {
        return indicativoCarencia;
    }


    /**
     * Sets the indicativoCarencia value for this ValidacaoElegibilidadeParameter.
     * 
     * @param indicativoCarencia
     */
    public void setIndicativoCarencia(Boolean indicativoCarencia) {
        this.indicativoCarencia = indicativoCarencia;
    }


    /**
     * Gets the numeroContaInterna value for this ValidacaoElegibilidadeParameter.
     * 
     * @return numeroContaInterna
     */
    public Long getNumeroContaInterna() {
        return numeroContaInterna;
    }


    /**
     * Sets the numeroContaInterna value for this ValidacaoElegibilidadeParameter.
     * 
     * @param numeroContaInterna
     */
    public void setNumeroContaInterna(Long numeroContaInterna) {
        this.numeroContaInterna = numeroContaInterna;
    }


    /**
     * Gets the prazoEmprestimo value for this ValidacaoElegibilidadeParameter.
     * 
     * @return prazoEmprestimo
     */
    public Integer getPrazoEmprestimo() {
        return prazoEmprestimo;
    }


    /**
     * Sets the prazoEmprestimo value for this ValidacaoElegibilidadeParameter.
     * 
     * @param prazoEmprestimo
     */
    public void setPrazoEmprestimo(Integer prazoEmprestimo) {
        this.prazoEmprestimo = prazoEmprestimo;
    }


    /**
     * Gets the tipoPagamentoSeguro value for this ValidacaoElegibilidadeParameter.
     * 
     * @return tipoPagamentoSeguro
     */
    public Integer getTipoPagamentoSeguro() {
        return tipoPagamentoSeguro;
    }


    /**
     * Sets the tipoPagamentoSeguro value for this ValidacaoElegibilidadeParameter.
     * 
     * @param tipoPagamentoSeguro
     */
    public void setTipoPagamentoSeguro(Integer tipoPagamentoSeguro) {
        this.tipoPagamentoSeguro = tipoPagamentoSeguro;
    }


    /**
     * Gets the tipoVinculo value for this ValidacaoElegibilidadeParameter.
     * 
     * @return tipoVinculo
     */
    public String getTipoVinculo() {
        return tipoVinculo;
    }


    /**
     * Sets the tipoVinculo value for this ValidacaoElegibilidadeParameter.
     * 
     * @param tipoVinculo
     */
    public void setTipoVinculo(String tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ValidacaoElegibilidadeParameter)) return false;
        ValidacaoElegibilidadeParameter other = (ValidacaoElegibilidadeParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoEntidade==null && other.getCodigoEntidade()==null) || 
             (this.codigoEntidade!=null &&
              this.codigoEntidade.equals(other.getCodigoEntidade()))) &&
            ((this.codigoSeguro==null && other.getCodigoSeguro()==null) || 
             (this.codigoSeguro!=null &&
              this.codigoSeguro.equals(other.getCodigoSeguro()))) &&
            ((this.cpfCliente==null && other.getCpfCliente()==null) || 
             (this.cpfCliente!=null &&
              this.cpfCliente.equals(other.getCpfCliente()))) &&
            ((this.dataNascimento==null && other.getDataNascimento()==null) || 
             (this.dataNascimento!=null &&
              this.dataNascimento.equals(other.getDataNascimento()))) &&
            ((this.indicativoCarencia==null && other.getIndicativoCarencia()==null) || 
             (this.indicativoCarencia!=null &&
              this.indicativoCarencia.equals(other.getIndicativoCarencia()))) &&
            ((this.numeroContaInterna==null && other.getNumeroContaInterna()==null) || 
             (this.numeroContaInterna!=null &&
              this.numeroContaInterna.equals(other.getNumeroContaInterna()))) &&
            ((this.prazoEmprestimo==null && other.getPrazoEmprestimo()==null) || 
             (this.prazoEmprestimo!=null &&
              this.prazoEmprestimo.equals(other.getPrazoEmprestimo()))) &&
            ((this.tipoPagamentoSeguro==null && other.getTipoPagamentoSeguro()==null) || 
             (this.tipoPagamentoSeguro!=null &&
              this.tipoPagamentoSeguro.equals(other.getTipoPagamentoSeguro()))) &&
            ((this.tipoVinculo==null && other.getTipoVinculo()==null) || 
             (this.tipoVinculo!=null &&
              this.tipoVinculo.equals(other.getTipoVinculo())));
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
        if (getCodigoEntidade() != null) {
            _hashCode += getCodigoEntidade().hashCode();
        }
        if (getCodigoSeguro() != null) {
            _hashCode += getCodigoSeguro().hashCode();
        }
        if (getCpfCliente() != null) {
            _hashCode += getCpfCliente().hashCode();
        }
        if (getDataNascimento() != null) {
            _hashCode += getDataNascimento().hashCode();
        }
        if (getIndicativoCarencia() != null) {
            _hashCode += getIndicativoCarencia().hashCode();
        }
        if (getNumeroContaInterna() != null) {
            _hashCode += getNumeroContaInterna().hashCode();
        }
        if (getPrazoEmprestimo() != null) {
            _hashCode += getPrazoEmprestimo().hashCode();
        }
        if (getTipoPagamentoSeguro() != null) {
            _hashCode += getTipoPagamentoSeguro().hashCode();
        }
        if (getTipoVinculo() != null) {
            _hashCode += getTipoVinculo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidacaoElegibilidadeParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "short"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataNascimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicativoCarencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicativoCarencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroContaInterna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroContaInterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prazoEmprestimo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prazoEmprestimo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPagamentoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPagamentoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoVinculo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoVinculo"));
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
