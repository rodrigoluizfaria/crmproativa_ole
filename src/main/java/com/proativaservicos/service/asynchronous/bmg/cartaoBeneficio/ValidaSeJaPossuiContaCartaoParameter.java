/**
 * ValidaSeJaPossuiContaCartaoParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class ValidaSeJaPossuiContaCartaoParameter  extends com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceParameter  implements java.io.Serializable {
    private int codigoEntidade;

    private java.lang.String cpf;

    private java.lang.String matricula;

    private java.lang.String matriculaInstituidor;

    private java.lang.Integer sequencialOrgao;

    private java.lang.String vinculoMatricula;

    public ValidaSeJaPossuiContaCartaoParameter() {
    }

    public ValidaSeJaPossuiContaCartaoParameter(
           java.lang.String login,
           java.lang.String senha,
           int codigoEntidade,
           java.lang.String cpf,
           java.lang.String matricula,
           java.lang.String matriculaInstituidor,
           java.lang.Integer sequencialOrgao,
           java.lang.String vinculoMatricula) {
        super(
            login,
            senha);
        this.codigoEntidade = codigoEntidade;
        this.cpf = cpf;
        this.matricula = matricula;
        this.matriculaInstituidor = matriculaInstituidor;
        this.sequencialOrgao = sequencialOrgao;
        this.vinculoMatricula = vinculoMatricula;
    }


    /**
     * Gets the codigoEntidade value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @return codigoEntidade
     */
    public int getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(int codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the cpf value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @return cpf
     */
    public java.lang.String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @param cpf
     */
    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the matricula value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @return matricula
     */
    public java.lang.String getMatricula() {
        return matricula;
    }


    /**
     * Sets the matricula value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @param matricula
     */
    public void setMatricula(java.lang.String matricula) {
        this.matricula = matricula;
    }


    /**
     * Gets the matriculaInstituidor value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @return matriculaInstituidor
     */
    public java.lang.String getMatriculaInstituidor() {
        return matriculaInstituidor;
    }


    /**
     * Sets the matriculaInstituidor value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @param matriculaInstituidor
     */
    public void setMatriculaInstituidor(java.lang.String matriculaInstituidor) {
        this.matriculaInstituidor = matriculaInstituidor;
    }


    /**
     * Gets the sequencialOrgao value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @return sequencialOrgao
     */
    public java.lang.Integer getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(java.lang.Integer sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the vinculoMatricula value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @return vinculoMatricula
     */
    public java.lang.String getVinculoMatricula() {
        return vinculoMatricula;
    }


    /**
     * Sets the vinculoMatricula value for this ValidaSeJaPossuiContaCartaoParameter.
     * 
     * @param vinculoMatricula
     */
    public void setVinculoMatricula(java.lang.String vinculoMatricula) {
        this.vinculoMatricula = vinculoMatricula;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaSeJaPossuiContaCartaoParameter)) return false;
        ValidaSeJaPossuiContaCartaoParameter other = (ValidaSeJaPossuiContaCartaoParameter) obj;
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
            ((this.matricula==null && other.getMatricula()==null) || 
             (this.matricula!=null &&
              this.matricula.equals(other.getMatricula()))) &&
            ((this.matriculaInstituidor==null && other.getMatriculaInstituidor()==null) || 
             (this.matriculaInstituidor!=null &&
              this.matriculaInstituidor.equals(other.getMatriculaInstituidor()))) &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao()))) &&
            ((this.vinculoMatricula==null && other.getVinculoMatricula()==null) || 
             (this.vinculoMatricula!=null &&
              this.vinculoMatricula.equals(other.getVinculoMatricula())));
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
        if (getMatricula() != null) {
            _hashCode += getMatricula().hashCode();
        }
        if (getMatriculaInstituidor() != null) {
            _hashCode += getMatriculaInstituidor().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        if (getVinculoMatricula() != null) {
            _hashCode += getVinculoMatricula().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidaSeJaPossuiContaCartaoParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoParameter"));
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
        elemField.setFieldName("matricula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "matricula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matriculaInstituidor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "matriculaInstituidor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialOrgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vinculoMatricula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vinculoMatricula"));
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
