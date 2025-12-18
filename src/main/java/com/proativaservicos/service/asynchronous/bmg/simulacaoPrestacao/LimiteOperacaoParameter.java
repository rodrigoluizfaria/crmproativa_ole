/**
 * LimiteOperacaoParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoParameter;

public class LimiteOperacaoParameter  extends com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.WebServiceParameter  implements java.io.Serializable {
    private java.lang.Integer codigoEntidade;

    private java.lang.Integer codigoProduto;

    private long cpfAsNumber;

    private java.lang.String loginConsig;

    private java.lang.Integer loja;

    private double margem;

    private java.lang.Integer sequencialOrgao;

    private java.lang.String servico;

    private double valorRenda;

    public LimiteOperacaoParameter() {
    }

    public LimiteOperacaoParameter(
           java.lang.String login,
           java.lang.String senha,
           java.lang.Integer codigoEntidade,
           java.lang.Integer codigoProduto,
           long cpfAsNumber,
           java.lang.String loginConsig,
           java.lang.Integer loja,
           double margem,
           java.lang.Integer sequencialOrgao,
           java.lang.String servico,
           double valorRenda) {
        super(
            login,
            senha);
        this.codigoEntidade = codigoEntidade;
        this.codigoProduto = codigoProduto;
        this.cpfAsNumber = cpfAsNumber;
        this.loginConsig = loginConsig;
        this.loja = loja;
        this.margem = margem;
        this.sequencialOrgao = sequencialOrgao;
        this.servico = servico;
        this.valorRenda = valorRenda;
    }


    /**
     * Gets the codigoEntidade value for this LimiteOperacaoParameter.
     * 
     * @return codigoEntidade
     */
    public java.lang.Integer getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this LimiteOperacaoParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(java.lang.Integer codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoProduto value for this LimiteOperacaoParameter.
     * 
     * @return codigoProduto
     */
    public java.lang.Integer getCodigoProduto() {
        return codigoProduto;
    }


    /**
     * Sets the codigoProduto value for this LimiteOperacaoParameter.
     * 
     * @param codigoProduto
     */
    public void setCodigoProduto(java.lang.Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }


    /**
     * Gets the cpfAsNumber value for this LimiteOperacaoParameter.
     * 
     * @return cpfAsNumber
     */
    public long getCpfAsNumber() {
        return cpfAsNumber;
    }


    /**
     * Sets the cpfAsNumber value for this LimiteOperacaoParameter.
     * 
     * @param cpfAsNumber
     */
    public void setCpfAsNumber(long cpfAsNumber) {
        this.cpfAsNumber = cpfAsNumber;
    }


    /**
     * Gets the loginConsig value for this LimiteOperacaoParameter.
     * 
     * @return loginConsig
     */
    public java.lang.String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this LimiteOperacaoParameter.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(java.lang.String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the loja value for this LimiteOperacaoParameter.
     * 
     * @return loja
     */
    public java.lang.Integer getLoja() {
        return loja;
    }


    /**
     * Sets the loja value for this LimiteOperacaoParameter.
     * 
     * @param loja
     */
    public void setLoja(java.lang.Integer loja) {
        this.loja = loja;
    }


    /**
     * Gets the margem value for this LimiteOperacaoParameter.
     * 
     * @return margem
     */
    public double getMargem() {
        return margem;
    }


    /**
     * Sets the margem value for this LimiteOperacaoParameter.
     * 
     * @param margem
     */
    public void setMargem(double margem) {
        this.margem = margem;
    }


    /**
     * Gets the sequencialOrgao value for this LimiteOperacaoParameter.
     * 
     * @return sequencialOrgao
     */
    public java.lang.Integer getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this LimiteOperacaoParameter.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(java.lang.Integer sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the servico value for this LimiteOperacaoParameter.
     * 
     * @return servico
     */
    public java.lang.String getServico() {
        return servico;
    }


    /**
     * Sets the servico value for this LimiteOperacaoParameter.
     * 
     * @param servico
     */
    public void setServico(java.lang.String servico) {
        this.servico = servico;
    }


    /**
     * Gets the valorRenda value for this LimiteOperacaoParameter.
     * 
     * @return valorRenda
     */
    public double getValorRenda() {
        return valorRenda;
    }


    /**
     * Sets the valorRenda value for this LimiteOperacaoParameter.
     * 
     * @param valorRenda
     */
    public void setValorRenda(double valorRenda) {
        this.valorRenda = valorRenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LimiteOperacaoParameter)) return false;
        LimiteOperacaoParameter other = (LimiteOperacaoParameter) obj;
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
            ((this.codigoProduto==null && other.getCodigoProduto()==null) || 
             (this.codigoProduto!=null &&
              this.codigoProduto.equals(other.getCodigoProduto()))) &&
            this.cpfAsNumber == other.getCpfAsNumber() &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.loja==null && other.getLoja()==null) || 
             (this.loja!=null &&
              this.loja.equals(other.getLoja()))) &&
            this.margem == other.getMargem() &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao()))) &&
            ((this.servico==null && other.getServico()==null) || 
             (this.servico!=null &&
              this.servico.equals(other.getServico()))) &&
            this.valorRenda == other.getValorRenda();
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
        if (getCodigoProduto() != null) {
            _hashCode += getCodigoProduto().hashCode();
        }
        _hashCode += new Long(getCpfAsNumber()).hashCode();
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getLoja() != null) {
            _hashCode += getLoja().hashCode();
        }
        _hashCode += new Double(getMargem()).hashCode();
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        if (getServico() != null) {
            _hashCode += getServico().hashCode();
        }
        _hashCode += new Double(getValorRenda()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LimiteOperacaoParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacaoParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfAsNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfAsNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("margem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "margem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialOrgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorRenda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorRenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
