/**
 * ObtemProdutosDeSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class ObtemProdutosDeSeguroParameter  extends com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceBaseParameter  implements java.io.Serializable {
    private int codigoEntidade;

    private java.lang.String cpf;

    private java.util.Calendar dataDeNacimento;

    private java.lang.Integer sequencialOrgao;

    public ObtemProdutosDeSeguroParameter() {
    }

    public ObtemProdutosDeSeguroParameter(
           java.lang.String login,
           java.lang.String senha,
           java.lang.String loginConsig,
           int codigoEntidade,
           java.lang.String cpf,
           java.util.Calendar dataDeNacimento,
           java.lang.Integer sequencialOrgao) {
        super(
            login,
            senha,
            loginConsig);
        this.codigoEntidade = codigoEntidade;
        this.cpf = cpf;
        this.dataDeNacimento = dataDeNacimento;
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the codigoEntidade value for this ObtemProdutosDeSeguroParameter.
     * 
     * @return codigoEntidade
     */
    public int getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this ObtemProdutosDeSeguroParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(int codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the cpf value for this ObtemProdutosDeSeguroParameter.
     * 
     * @return cpf
     */
    public java.lang.String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ObtemProdutosDeSeguroParameter.
     * 
     * @param cpf
     */
    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the dataDeNacimento value for this ObtemProdutosDeSeguroParameter.
     * 
     * @return dataDeNacimento
     */
    public java.util.Calendar getDataDeNacimento() {
        return dataDeNacimento;
    }


    /**
     * Sets the dataDeNacimento value for this ObtemProdutosDeSeguroParameter.
     * 
     * @param dataDeNacimento
     */
    public void setDataDeNacimento(java.util.Calendar dataDeNacimento) {
        this.dataDeNacimento = dataDeNacimento;
    }


    /**
     * Gets the sequencialOrgao value for this ObtemProdutosDeSeguroParameter.
     * 
     * @return sequencialOrgao
     */
    public java.lang.Integer getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this ObtemProdutosDeSeguroParameter.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(java.lang.Integer sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtemProdutosDeSeguroParameter)) return false;
        ObtemProdutosDeSeguroParameter other = (ObtemProdutosDeSeguroParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.codigoEntidade == other.getCodigoEntidade() &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.dataDeNacimento==null && other.getDataDeNacimento()==null) || 
             (this.dataDeNacimento!=null &&
              this.dataDeNacimento.equals(other.getDataDeNacimento()))) &&
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
        _hashCode += getCodigoEntidade();
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getDataDeNacimento() != null) {
            _hashCode += getDataDeNacimento().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtemProdutosDeSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDeNacimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataDeNacimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
