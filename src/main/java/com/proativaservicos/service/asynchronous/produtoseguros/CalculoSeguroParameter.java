/**
 * CalculoSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class CalculoSeguroParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private String codigoEntidade;

    private String codigoOrgao;

    private Integer codigoSeguro;

    private String cpf;

    private Double limiteRenda;

    private String loginConsig;

    private Integer prazoEmprestimo;

    private String senhaConsig;

    private String sequencialOrgao;

    public CalculoSeguroParameter() {
    }

    public CalculoSeguroParameter(
           String login,
           String senha,
           String codigoEntidade,
           String codigoOrgao,
           Integer codigoSeguro,
           String cpf,
           Double limiteRenda,
           String loginConsig,
           Integer prazoEmprestimo,
           String senhaConsig,
           String sequencialOrgao) {
        super(
            login,
            senha);
        this.codigoEntidade = codigoEntidade;
        this.codigoOrgao = codigoOrgao;
        this.codigoSeguro = codigoSeguro;
        this.cpf = cpf;
        this.limiteRenda = limiteRenda;
        this.loginConsig = loginConsig;
        this.prazoEmprestimo = prazoEmprestimo;
        this.senhaConsig = senhaConsig;
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the codigoEntidade value for this CalculoSeguroParameter.
     * 
     * @return codigoEntidade
     */
    public String getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this CalculoSeguroParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(String codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoOrgao value for this CalculoSeguroParameter.
     * 
     * @return codigoOrgao
     */
    public String getCodigoOrgao() {
        return codigoOrgao;
    }


    /**
     * Sets the codigoOrgao value for this CalculoSeguroParameter.
     * 
     * @param codigoOrgao
     */
    public void setCodigoOrgao(String codigoOrgao) {
        this.codigoOrgao = codigoOrgao;
    }


    /**
     * Gets the codigoSeguro value for this CalculoSeguroParameter.
     * 
     * @return codigoSeguro
     */
    public Integer getCodigoSeguro() {
        return codigoSeguro;
    }


    /**
     * Sets the codigoSeguro value for this CalculoSeguroParameter.
     * 
     * @param codigoSeguro
     */
    public void setCodigoSeguro(Integer codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }


    /**
     * Gets the cpf value for this CalculoSeguroParameter.
     * 
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this CalculoSeguroParameter.
     * 
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the limiteRenda value for this CalculoSeguroParameter.
     * 
     * @return limiteRenda
     */
    public Double getLimiteRenda() {
        return limiteRenda;
    }


    /**
     * Sets the limiteRenda value for this CalculoSeguroParameter.
     * 
     * @param limiteRenda
     */
    public void setLimiteRenda(Double limiteRenda) {
        this.limiteRenda = limiteRenda;
    }


    /**
     * Gets the loginConsig value for this CalculoSeguroParameter.
     * 
     * @return loginConsig
     */
    public String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this CalculoSeguroParameter.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the prazoEmprestimo value for this CalculoSeguroParameter.
     * 
     * @return prazoEmprestimo
     */
    public Integer getPrazoEmprestimo() {
        return prazoEmprestimo;
    }


    /**
     * Sets the prazoEmprestimo value for this CalculoSeguroParameter.
     * 
     * @param prazoEmprestimo
     */
    public void setPrazoEmprestimo(Integer prazoEmprestimo) {
        this.prazoEmprestimo = prazoEmprestimo;
    }


    /**
     * Gets the senhaConsig value for this CalculoSeguroParameter.
     * 
     * @return senhaConsig
     */
    public String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this CalculoSeguroParameter.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the sequencialOrgao value for this CalculoSeguroParameter.
     * 
     * @return sequencialOrgao
     */
    public String getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this CalculoSeguroParameter.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(String sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CalculoSeguroParameter)) return false;
        CalculoSeguroParameter other = (CalculoSeguroParameter) obj;
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
            ((this.codigoOrgao==null && other.getCodigoOrgao()==null) || 
             (this.codigoOrgao!=null &&
              this.codigoOrgao.equals(other.getCodigoOrgao()))) &&
            ((this.codigoSeguro==null && other.getCodigoSeguro()==null) || 
             (this.codigoSeguro!=null &&
              this.codigoSeguro.equals(other.getCodigoSeguro()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.limiteRenda==null && other.getLimiteRenda()==null) || 
             (this.limiteRenda!=null &&
              this.limiteRenda.equals(other.getLimiteRenda()))) &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.prazoEmprestimo==null && other.getPrazoEmprestimo()==null) || 
             (this.prazoEmprestimo!=null &&
              this.prazoEmprestimo.equals(other.getPrazoEmprestimo()))) &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig()))) &&
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
        if (getCodigoOrgao() != null) {
            _hashCode += getCodigoOrgao().hashCode();
        }
        if (getCodigoSeguro() != null) {
            _hashCode += getCodigoSeguro().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getLimiteRenda() != null) {
            _hashCode += getLimiteRenda().hashCode();
        }
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getPrazoEmprestimo() != null) {
            _hashCode += getPrazoEmprestimo().hashCode();
        }
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CalculoSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CalculoSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoOrgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limiteRenda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteRenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prazoEmprestimo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prazoEmprestimo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
