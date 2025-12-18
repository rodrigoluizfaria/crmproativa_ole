/**
 * ConsistenciaTelefonesComplementaresParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ConsistenciaTelefonesComplementaresParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private String codigoAdesao;

    private int codigoLoja;

    private int codigoProdutoSeguro;

    private String cpf;

    private java.util.Calendar dataAdesaoFim;

    private java.util.Calendar dataAdesaoInicio;

    public ConsistenciaTelefonesComplementaresParameter() {
    }

    public ConsistenciaTelefonesComplementaresParameter(
           String login,
           String senha,
           String codigoAdesao,
           int codigoLoja,
           int codigoProdutoSeguro,
           String cpf,
           java.util.Calendar dataAdesaoFim,
           java.util.Calendar dataAdesaoInicio) {
        super(
            login,
            senha);
        this.codigoAdesao = codigoAdesao;
        this.codigoLoja = codigoLoja;
        this.codigoProdutoSeguro = codigoProdutoSeguro;
        this.cpf = cpf;
        this.dataAdesaoFim = dataAdesaoFim;
        this.dataAdesaoInicio = dataAdesaoInicio;
    }


    /**
     * Gets the codigoAdesao value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @return codigoAdesao
     */
    public String getCodigoAdesao() {
        return codigoAdesao;
    }


    /**
     * Sets the codigoAdesao value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @param codigoAdesao
     */
    public void setCodigoAdesao(String codigoAdesao) {
        this.codigoAdesao = codigoAdesao;
    }


    /**
     * Gets the codigoLoja value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @return codigoLoja
     */
    public int getCodigoLoja() {
        return codigoLoja;
    }


    /**
     * Sets the codigoLoja value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @param codigoLoja
     */
    public void setCodigoLoja(int codigoLoja) {
        this.codigoLoja = codigoLoja;
    }


    /**
     * Gets the codigoProdutoSeguro value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @return codigoProdutoSeguro
     */
    public int getCodigoProdutoSeguro() {
        return codigoProdutoSeguro;
    }


    /**
     * Sets the codigoProdutoSeguro value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @param codigoProdutoSeguro
     */
    public void setCodigoProdutoSeguro(int codigoProdutoSeguro) {
        this.codigoProdutoSeguro = codigoProdutoSeguro;
    }


    /**
     * Gets the cpf value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the dataAdesaoFim value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @return dataAdesaoFim
     */
    public java.util.Calendar getDataAdesaoFim() {
        return dataAdesaoFim;
    }


    /**
     * Sets the dataAdesaoFim value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @param dataAdesaoFim
     */
    public void setDataAdesaoFim(java.util.Calendar dataAdesaoFim) {
        this.dataAdesaoFim = dataAdesaoFim;
    }


    /**
     * Gets the dataAdesaoInicio value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @return dataAdesaoInicio
     */
    public java.util.Calendar getDataAdesaoInicio() {
        return dataAdesaoInicio;
    }


    /**
     * Sets the dataAdesaoInicio value for this ConsistenciaTelefonesComplementaresParameter.
     * 
     * @param dataAdesaoInicio
     */
    public void setDataAdesaoInicio(java.util.Calendar dataAdesaoInicio) {
        this.dataAdesaoInicio = dataAdesaoInicio;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ConsistenciaTelefonesComplementaresParameter)) return false;
        ConsistenciaTelefonesComplementaresParameter other = (ConsistenciaTelefonesComplementaresParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoAdesao==null && other.getCodigoAdesao()==null) || 
             (this.codigoAdesao!=null &&
              this.codigoAdesao.equals(other.getCodigoAdesao()))) &&
            this.codigoLoja == other.getCodigoLoja() &&
            this.codigoProdutoSeguro == other.getCodigoProdutoSeguro() &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.dataAdesaoFim==null && other.getDataAdesaoFim()==null) || 
             (this.dataAdesaoFim!=null &&
              this.dataAdesaoFim.equals(other.getDataAdesaoFim()))) &&
            ((this.dataAdesaoInicio==null && other.getDataAdesaoInicio()==null) || 
             (this.dataAdesaoInicio!=null &&
              this.dataAdesaoInicio.equals(other.getDataAdesaoInicio())));
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
        if (getCodigoAdesao() != null) {
            _hashCode += getCodigoAdesao().hashCode();
        }
        _hashCode += getCodigoLoja();
        _hashCode += getCodigoProdutoSeguro();
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getDataAdesaoFim() != null) {
            _hashCode += getDataAdesaoFim().hashCode();
        }
        if (getDataAdesaoInicio() != null) {
            _hashCode += getDataAdesaoInicio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsistenciaTelefonesComplementaresParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciaTelefonesComplementaresParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAdesao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAdesao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoLoja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoLoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProdutoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoProdutoSeguro"));
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
        elemField.setFieldName("dataAdesaoFim");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataAdesaoFim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAdesaoInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataAdesaoInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
