/**
 * PlanosSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PlanosSeguroParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private Integer codigoProdutoSeguro;

    private java.util.Calendar dataNascimento;

    private String entidade;

    private String loginConsig;

    private String matricula;

    private Integer numeroInternoConta;

    private Double renda;

    private String senhaConsig;

    private String sequencialOrgao;

    private Integer tipoPagamentoSeguro;

    public PlanosSeguroParameter() {
    }

    public PlanosSeguroParameter(
           String login,
           String senha,
           Integer codigoProdutoSeguro,
           java.util.Calendar dataNascimento,
           String entidade,
           String loginConsig,
           String matricula,
           Integer numeroInternoConta,
           Double renda,
           String senhaConsig,
           String sequencialOrgao,
           Integer tipoPagamentoSeguro) {
        super(
            login,
            senha);
        this.codigoProdutoSeguro = codigoProdutoSeguro;
        this.dataNascimento = dataNascimento;
        this.entidade = entidade;
        this.loginConsig = loginConsig;
        this.matricula = matricula;
        this.numeroInternoConta = numeroInternoConta;
        this.renda = renda;
        this.senhaConsig = senhaConsig;
        this.sequencialOrgao = sequencialOrgao;
        this.tipoPagamentoSeguro = tipoPagamentoSeguro;
    }


    /**
     * Gets the codigoProdutoSeguro value for this PlanosSeguroParameter.
     * 
     * @return codigoProdutoSeguro
     */
    public Integer getCodigoProdutoSeguro() {
        return codigoProdutoSeguro;
    }


    /**
     * Sets the codigoProdutoSeguro value for this PlanosSeguroParameter.
     * 
     * @param codigoProdutoSeguro
     */
    public void setCodigoProdutoSeguro(Integer codigoProdutoSeguro) {
        this.codigoProdutoSeguro = codigoProdutoSeguro;
    }


    /**
     * Gets the dataNascimento value for this PlanosSeguroParameter.
     * 
     * @return dataNascimento
     */
    public java.util.Calendar getDataNascimento() {
        return dataNascimento;
    }


    /**
     * Sets the dataNascimento value for this PlanosSeguroParameter.
     * 
     * @param dataNascimento
     */
    public void setDataNascimento(java.util.Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    /**
     * Gets the entidade value for this PlanosSeguroParameter.
     * 
     * @return entidade
     */
    public String getEntidade() {
        return entidade;
    }


    /**
     * Sets the entidade value for this PlanosSeguroParameter.
     * 
     * @param entidade
     */
    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }


    /**
     * Gets the loginConsig value for this PlanosSeguroParameter.
     * 
     * @return loginConsig
     */
    public String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this PlanosSeguroParameter.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the matricula value for this PlanosSeguroParameter.
     * 
     * @return matricula
     */
    public String getMatricula() {
        return matricula;
    }


    /**
     * Sets the matricula value for this PlanosSeguroParameter.
     * 
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    /**
     * Gets the numeroInternoConta value for this PlanosSeguroParameter.
     * 
     * @return numeroInternoConta
     */
    public Integer getNumeroInternoConta() {
        return numeroInternoConta;
    }


    /**
     * Sets the numeroInternoConta value for this PlanosSeguroParameter.
     * 
     * @param numeroInternoConta
     */
    public void setNumeroInternoConta(Integer numeroInternoConta) {
        this.numeroInternoConta = numeroInternoConta;
    }


    /**
     * Gets the renda value for this PlanosSeguroParameter.
     * 
     * @return renda
     */
    public Double getRenda() {
        return renda;
    }


    /**
     * Sets the renda value for this PlanosSeguroParameter.
     * 
     * @param renda
     */
    public void setRenda(Double renda) {
        this.renda = renda;
    }


    /**
     * Gets the senhaConsig value for this PlanosSeguroParameter.
     * 
     * @return senhaConsig
     */
    public String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this PlanosSeguroParameter.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the sequencialOrgao value for this PlanosSeguroParameter.
     * 
     * @return sequencialOrgao
     */
    public String getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this PlanosSeguroParameter.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(String sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the tipoPagamentoSeguro value for this PlanosSeguroParameter.
     * 
     * @return tipoPagamentoSeguro
     */
    public Integer getTipoPagamentoSeguro() {
        return tipoPagamentoSeguro;
    }


    /**
     * Sets the tipoPagamentoSeguro value for this PlanosSeguroParameter.
     * 
     * @param tipoPagamentoSeguro
     */
    public void setTipoPagamentoSeguro(Integer tipoPagamentoSeguro) {
        this.tipoPagamentoSeguro = tipoPagamentoSeguro;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlanosSeguroParameter)) return false;
        PlanosSeguroParameter other = (PlanosSeguroParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoProdutoSeguro==null && other.getCodigoProdutoSeguro()==null) || 
             (this.codigoProdutoSeguro!=null &&
              this.codigoProdutoSeguro.equals(other.getCodigoProdutoSeguro()))) &&
            ((this.dataNascimento==null && other.getDataNascimento()==null) || 
             (this.dataNascimento!=null &&
              this.dataNascimento.equals(other.getDataNascimento()))) &&
            ((this.entidade==null && other.getEntidade()==null) || 
             (this.entidade!=null &&
              this.entidade.equals(other.getEntidade()))) &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.matricula==null && other.getMatricula()==null) || 
             (this.matricula!=null &&
              this.matricula.equals(other.getMatricula()))) &&
            ((this.numeroInternoConta==null && other.getNumeroInternoConta()==null) || 
             (this.numeroInternoConta!=null &&
              this.numeroInternoConta.equals(other.getNumeroInternoConta()))) &&
            ((this.renda==null && other.getRenda()==null) || 
             (this.renda!=null &&
              this.renda.equals(other.getRenda()))) &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig()))) &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao()))) &&
            ((this.tipoPagamentoSeguro==null && other.getTipoPagamentoSeguro()==null) || 
             (this.tipoPagamentoSeguro!=null &&
              this.tipoPagamentoSeguro.equals(other.getTipoPagamentoSeguro())));
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
        if (getCodigoProdutoSeguro() != null) {
            _hashCode += getCodigoProdutoSeguro().hashCode();
        }
        if (getDataNascimento() != null) {
            _hashCode += getDataNascimento().hashCode();
        }
        if (getEntidade() != null) {
            _hashCode += getEntidade().hashCode();
        }
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getMatricula() != null) {
            _hashCode += getMatricula().hashCode();
        }
        if (getNumeroInternoConta() != null) {
            _hashCode += getNumeroInternoConta().hashCode();
        }
        if (getRenda() != null) {
            _hashCode += getRenda().hashCode();
        }
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        if (getTipoPagamentoSeguro() != null) {
            _hashCode += getTipoPagamentoSeguro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlanosSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProdutoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoProdutoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataNascimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matricula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "matricula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroInternoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroInternoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("renda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "renda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senhaConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senhaConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialOrgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPagamentoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPagamentoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
