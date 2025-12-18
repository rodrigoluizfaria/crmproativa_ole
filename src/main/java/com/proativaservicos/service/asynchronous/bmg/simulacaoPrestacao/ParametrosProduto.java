/**
 * ParametrosProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosProduto;

public class ParametrosProduto  extends com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.WebServiceParameter  implements java.io.Serializable {
    private java.lang.Integer codigoBeneficio;

    private java.lang.Integer codigoEntidade;

    private java.lang.String codigoServico;

    private java.lang.Boolean indicativoCarencia;

    private java.lang.String loginConsig;

    private java.lang.String senhaConsig;

    private java.lang.Integer sequencialOrgao;

    private java.lang.Integer tipoFormaEnvio;

    public ParametrosProduto() {
    }

    public ParametrosProduto(
           java.lang.String login,
           java.lang.String senha,
           java.lang.Integer codigoBeneficio,
           java.lang.Integer codigoEntidade,
           java.lang.String codigoServico,
           java.lang.Boolean indicativoCarencia,
           java.lang.String loginConsig,
           java.lang.String senhaConsig,
           java.lang.Integer sequencialOrgao,
           java.lang.Integer tipoFormaEnvio) {
        super(
            login,
            senha);
        this.codigoBeneficio = codigoBeneficio;
        this.codigoEntidade = codigoEntidade;
        this.codigoServico = codigoServico;
        this.indicativoCarencia = indicativoCarencia;
        this.loginConsig = loginConsig;
        this.senhaConsig = senhaConsig;
        this.sequencialOrgao = sequencialOrgao;
        this.tipoFormaEnvio = tipoFormaEnvio;
    }


    /**
     * Gets the codigoBeneficio value for this ParametrosProduto.
     * 
     * @return codigoBeneficio
     */
    public java.lang.Integer getCodigoBeneficio() {
        return codigoBeneficio;
    }


    /**
     * Sets the codigoBeneficio value for this ParametrosProduto.
     * 
     * @param codigoBeneficio
     */
    public void setCodigoBeneficio(java.lang.Integer codigoBeneficio) {
        this.codigoBeneficio = codigoBeneficio;
    }


    /**
     * Gets the codigoEntidade value for this ParametrosProduto.
     * 
     * @return codigoEntidade
     */
    public java.lang.Integer getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this ParametrosProduto.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(java.lang.Integer codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoServico value for this ParametrosProduto.
     * 
     * @return codigoServico
     */
    public java.lang.String getCodigoServico() {
        return codigoServico;
    }


    /**
     * Sets the codigoServico value for this ParametrosProduto.
     * 
     * @param codigoServico
     */
    public void setCodigoServico(java.lang.String codigoServico) {
        this.codigoServico = codigoServico;
    }


    /**
     * Gets the indicativoCarencia value for this ParametrosProduto.
     * 
     * @return indicativoCarencia
     */
    public java.lang.Boolean getIndicativoCarencia() {
        return indicativoCarencia;
    }


    /**
     * Sets the indicativoCarencia value for this ParametrosProduto.
     * 
     * @param indicativoCarencia
     */
    public void setIndicativoCarencia(java.lang.Boolean indicativoCarencia) {
        this.indicativoCarencia = indicativoCarencia;
    }


    /**
     * Gets the loginConsig value for this ParametrosProduto.
     * 
     * @return loginConsig
     */
    public java.lang.String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this ParametrosProduto.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(java.lang.String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the senhaConsig value for this ParametrosProduto.
     * 
     * @return senhaConsig
     */
    public java.lang.String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this ParametrosProduto.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(java.lang.String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the sequencialOrgao value for this ParametrosProduto.
     * 
     * @return sequencialOrgao
     */
    public java.lang.Integer getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this ParametrosProduto.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(java.lang.Integer sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the tipoFormaEnvio value for this ParametrosProduto.
     * 
     * @return tipoFormaEnvio
     */
    public java.lang.Integer getTipoFormaEnvio() {
        return tipoFormaEnvio;
    }


    /**
     * Sets the tipoFormaEnvio value for this ParametrosProduto.
     * 
     * @param tipoFormaEnvio
     */
    public void setTipoFormaEnvio(java.lang.Integer tipoFormaEnvio) {
        this.tipoFormaEnvio = tipoFormaEnvio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosProduto)) return false;
        ParametrosProduto other = (ParametrosProduto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoBeneficio==null && other.getCodigoBeneficio()==null) || 
             (this.codigoBeneficio!=null &&
              this.codigoBeneficio.equals(other.getCodigoBeneficio()))) &&
            ((this.codigoEntidade==null && other.getCodigoEntidade()==null) || 
             (this.codigoEntidade!=null &&
              this.codigoEntidade.equals(other.getCodigoEntidade()))) &&
            ((this.codigoServico==null && other.getCodigoServico()==null) || 
             (this.codigoServico!=null &&
              this.codigoServico.equals(other.getCodigoServico()))) &&
            ((this.indicativoCarencia==null && other.getIndicativoCarencia()==null) || 
             (this.indicativoCarencia!=null &&
              this.indicativoCarencia.equals(other.getIndicativoCarencia()))) &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig()))) &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao()))) &&
            ((this.tipoFormaEnvio==null && other.getTipoFormaEnvio()==null) || 
             (this.tipoFormaEnvio!=null &&
              this.tipoFormaEnvio.equals(other.getTipoFormaEnvio())));
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
        if (getCodigoBeneficio() != null) {
            _hashCode += getCodigoBeneficio().hashCode();
        }
        if (getCodigoEntidade() != null) {
            _hashCode += getCodigoEntidade().hashCode();
        }
        if (getCodigoServico() != null) {
            _hashCode += getCodigoServico().hashCode();
        }
        if (getIndicativoCarencia() != null) {
            _hashCode += getIndicativoCarencia().hashCode();
        }
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        if (getTipoFormaEnvio() != null) {
            _hashCode += getTipoFormaEnvio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametrosProduto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoBeneficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoBeneficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicativoCarencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicativoCarencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFormaEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoFormaEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
