/**
 * ObtemFormaEnvioProdutoSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ObtemFormaEnvioProdutoSeguroParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceBaseParameter  implements java.io.Serializable {
    private Integer codigoEntidade;

    private String codigoFormaDeCredito;

    private String codigoServico;

    private String cpf;

    private Integer sequencialOrgao;

    public ObtemFormaEnvioProdutoSeguroParameter() {
    }

    public ObtemFormaEnvioProdutoSeguroParameter(
           String login,
           String senha,
           String loginConsig,
           Integer codigoEntidade,
           String codigoFormaDeCredito,
           String codigoServico,
           String cpf,
           Integer sequencialOrgao) {
        super(
            login,
            senha,
            loginConsig);
        this.codigoEntidade = codigoEntidade;
        this.codigoFormaDeCredito = codigoFormaDeCredito;
        this.codigoServico = codigoServico;
        this.cpf = cpf;
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the codigoEntidade value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @return codigoEntidade
     */
    public Integer getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(Integer codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoFormaDeCredito value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @return codigoFormaDeCredito
     */
    public String getCodigoFormaDeCredito() {
        return codigoFormaDeCredito;
    }


    /**
     * Sets the codigoFormaDeCredito value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @param codigoFormaDeCredito
     */
    public void setCodigoFormaDeCredito(String codigoFormaDeCredito) {
        this.codigoFormaDeCredito = codigoFormaDeCredito;
    }


    /**
     * Gets the codigoServico value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @return codigoServico
     */
    public String getCodigoServico() {
        return codigoServico;
    }


    /**
     * Sets the codigoServico value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @param codigoServico
     */
    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }


    /**
     * Gets the cpf value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the sequencialOrgao value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @return sequencialOrgao
     */
    public Integer getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this ObtemFormaEnvioProdutoSeguroParameter.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(Integer sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ObtemFormaEnvioProdutoSeguroParameter)) return false;
        ObtemFormaEnvioProdutoSeguroParameter other = (ObtemFormaEnvioProdutoSeguroParameter) obj;
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
            ((this.codigoFormaDeCredito==null && other.getCodigoFormaDeCredito()==null) || 
             (this.codigoFormaDeCredito!=null &&
              this.codigoFormaDeCredito.equals(other.getCodigoFormaDeCredito()))) &&
            ((this.codigoServico==null && other.getCodigoServico()==null) || 
             (this.codigoServico!=null &&
              this.codigoServico.equals(other.getCodigoServico()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao())));
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
        if (getCodigoFormaDeCredito() != null) {
            _hashCode += getCodigoFormaDeCredito().hashCode();
        }
        if (getCodigoServico() != null) {
            _hashCode += getCodigoServico().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtemFormaEnvioProdutoSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioProdutoSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoFormaDeCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoFormaDeCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialOrgao"));
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
