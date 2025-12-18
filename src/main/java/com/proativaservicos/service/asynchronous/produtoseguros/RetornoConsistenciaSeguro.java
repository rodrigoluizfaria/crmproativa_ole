/**
 * RetornoConsistenciaSeguro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class RetornoConsistenciaSeguro  implements java.io.Serializable {
    private Integer codigoApolice;

    private Integer codigoConsistencia;

    private Integer codigoSeguro;

    private java.math.BigDecimal cpf;

    private java.util.Calendar dataNegacao;

    private String descricaoConsistencia;

    private String nomeCliente;

    private String numeroAdesao;

    private String ocorrencia;

    public RetornoConsistenciaSeguro() {
    }

    public RetornoConsistenciaSeguro(
           Integer codigoApolice,
           Integer codigoConsistencia,
           Integer codigoSeguro,
           java.math.BigDecimal cpf,
           java.util.Calendar dataNegacao,
           String descricaoConsistencia,
           String nomeCliente,
           String numeroAdesao,
           String ocorrencia) {
           this.codigoApolice = codigoApolice;
           this.codigoConsistencia = codigoConsistencia;
           this.codigoSeguro = codigoSeguro;
           this.cpf = cpf;
           this.dataNegacao = dataNegacao;
           this.descricaoConsistencia = descricaoConsistencia;
           this.nomeCliente = nomeCliente;
           this.numeroAdesao = numeroAdesao;
           this.ocorrencia = ocorrencia;
    }


    /**
     * Gets the codigoApolice value for this RetornoConsistenciaSeguro.
     * 
     * @return codigoApolice
     */
    public Integer getCodigoApolice() {
        return codigoApolice;
    }


    /**
     * Sets the codigoApolice value for this RetornoConsistenciaSeguro.
     * 
     * @param codigoApolice
     */
    public void setCodigoApolice(Integer codigoApolice) {
        this.codigoApolice = codigoApolice;
    }


    /**
     * Gets the codigoConsistencia value for this RetornoConsistenciaSeguro.
     * 
     * @return codigoConsistencia
     */
    public Integer getCodigoConsistencia() {
        return codigoConsistencia;
    }


    /**
     * Sets the codigoConsistencia value for this RetornoConsistenciaSeguro.
     * 
     * @param codigoConsistencia
     */
    public void setCodigoConsistencia(Integer codigoConsistencia) {
        this.codigoConsistencia = codigoConsistencia;
    }


    /**
     * Gets the codigoSeguro value for this RetornoConsistenciaSeguro.
     * 
     * @return codigoSeguro
     */
    public Integer getCodigoSeguro() {
        return codigoSeguro;
    }


    /**
     * Sets the codigoSeguro value for this RetornoConsistenciaSeguro.
     * 
     * @param codigoSeguro
     */
    public void setCodigoSeguro(Integer codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }


    /**
     * Gets the cpf value for this RetornoConsistenciaSeguro.
     * 
     * @return cpf
     */
    public java.math.BigDecimal getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this RetornoConsistenciaSeguro.
     * 
     * @param cpf
     */
    public void setCpf(java.math.BigDecimal cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the dataNegacao value for this RetornoConsistenciaSeguro.
     * 
     * @return dataNegacao
     */
    public java.util.Calendar getDataNegacao() {
        return dataNegacao;
    }


    /**
     * Sets the dataNegacao value for this RetornoConsistenciaSeguro.
     * 
     * @param dataNegacao
     */
    public void setDataNegacao(java.util.Calendar dataNegacao) {
        this.dataNegacao = dataNegacao;
    }


    /**
     * Gets the descricaoConsistencia value for this RetornoConsistenciaSeguro.
     * 
     * @return descricaoConsistencia
     */
    public String getDescricaoConsistencia() {
        return descricaoConsistencia;
    }


    /**
     * Sets the descricaoConsistencia value for this RetornoConsistenciaSeguro.
     * 
     * @param descricaoConsistencia
     */
    public void setDescricaoConsistencia(String descricaoConsistencia) {
        this.descricaoConsistencia = descricaoConsistencia;
    }


    /**
     * Gets the nomeCliente value for this RetornoConsistenciaSeguro.
     * 
     * @return nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }


    /**
     * Sets the nomeCliente value for this RetornoConsistenciaSeguro.
     * 
     * @param nomeCliente
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }


    /**
     * Gets the numeroAdesao value for this RetornoConsistenciaSeguro.
     * 
     * @return numeroAdesao
     */
    public String getNumeroAdesao() {
        return numeroAdesao;
    }


    /**
     * Sets the numeroAdesao value for this RetornoConsistenciaSeguro.
     * 
     * @param numeroAdesao
     */
    public void setNumeroAdesao(String numeroAdesao) {
        this.numeroAdesao = numeroAdesao;
    }


    /**
     * Gets the ocorrencia value for this RetornoConsistenciaSeguro.
     * 
     * @return ocorrencia
     */
    public String getOcorrencia() {
        return ocorrencia;
    }


    /**
     * Sets the ocorrencia value for this RetornoConsistenciaSeguro.
     * 
     * @param ocorrencia
     */
    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RetornoConsistenciaSeguro)) return false;
        RetornoConsistenciaSeguro other = (RetornoConsistenciaSeguro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoApolice==null && other.getCodigoApolice()==null) || 
             (this.codigoApolice!=null &&
              this.codigoApolice.equals(other.getCodigoApolice()))) &&
            ((this.codigoConsistencia==null && other.getCodigoConsistencia()==null) || 
             (this.codigoConsistencia!=null &&
              this.codigoConsistencia.equals(other.getCodigoConsistencia()))) &&
            ((this.codigoSeguro==null && other.getCodigoSeguro()==null) || 
             (this.codigoSeguro!=null &&
              this.codigoSeguro.equals(other.getCodigoSeguro()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.dataNegacao==null && other.getDataNegacao()==null) || 
             (this.dataNegacao!=null &&
              this.dataNegacao.equals(other.getDataNegacao()))) &&
            ((this.descricaoConsistencia==null && other.getDescricaoConsistencia()==null) || 
             (this.descricaoConsistencia!=null &&
              this.descricaoConsistencia.equals(other.getDescricaoConsistencia()))) &&
            ((this.nomeCliente==null && other.getNomeCliente()==null) || 
             (this.nomeCliente!=null &&
              this.nomeCliente.equals(other.getNomeCliente()))) &&
            ((this.numeroAdesao==null && other.getNumeroAdesao()==null) || 
             (this.numeroAdesao!=null &&
              this.numeroAdesao.equals(other.getNumeroAdesao()))) &&
            ((this.ocorrencia==null && other.getOcorrencia()==null) || 
             (this.ocorrencia!=null &&
              this.ocorrencia.equals(other.getOcorrencia())));
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
        if (getCodigoApolice() != null) {
            _hashCode += getCodigoApolice().hashCode();
        }
        if (getCodigoConsistencia() != null) {
            _hashCode += getCodigoConsistencia().hashCode();
        }
        if (getCodigoSeguro() != null) {
            _hashCode += getCodigoSeguro().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getDataNegacao() != null) {
            _hashCode += getDataNegacao().hashCode();
        }
        if (getDescricaoConsistencia() != null) {
            _hashCode += getDescricaoConsistencia().hashCode();
        }
        if (getNomeCliente() != null) {
            _hashCode += getNomeCliente().hashCode();
        }
        if (getNumeroAdesao() != null) {
            _hashCode += getNumeroAdesao().hashCode();
        }
        if (getOcorrencia() != null) {
            _hashCode += getOcorrencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetornoConsistenciaSeguro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "RetornoConsistenciaSeguro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoApolice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoApolice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoConsistencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoConsistencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNegacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataNegacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoConsistencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricaoConsistencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAdesao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroAdesao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ocorrencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ocorrencia"));
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
