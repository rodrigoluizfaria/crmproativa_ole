/**
 * BeneficiariosSeguro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class BeneficiariosSeguro  extends com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AbstractWebServicesParameter  implements java.io.Serializable {
    private java.lang.String cpfBeneficiario;

    private java.util.Calendar dataNascimentoBeneficiario;

    private java.lang.Integer grauParentesco;

    private int idBeneficiario;

    private java.lang.String nomeBeneficiario;

    private java.lang.Double percentualParticipacao;

    public BeneficiariosSeguro() {
    }

    public BeneficiariosSeguro(
           java.lang.String cpfBeneficiario,
           java.util.Calendar dataNascimentoBeneficiario,
           java.lang.Integer grauParentesco,
           int idBeneficiario,
           java.lang.String nomeBeneficiario,
           java.lang.Double percentualParticipacao) {
        this.cpfBeneficiario = cpfBeneficiario;
        this.dataNascimentoBeneficiario = dataNascimentoBeneficiario;
        this.grauParentesco = grauParentesco;
        this.idBeneficiario = idBeneficiario;
        this.nomeBeneficiario = nomeBeneficiario;
        this.percentualParticipacao = percentualParticipacao;
    }


    /**
     * Gets the cpfBeneficiario value for this BeneficiariosSeguro.
     * 
     * @return cpfBeneficiario
     */
    public java.lang.String getCpfBeneficiario() {
        return cpfBeneficiario;
    }


    /**
     * Sets the cpfBeneficiario value for this BeneficiariosSeguro.
     * 
     * @param cpfBeneficiario
     */
    public void setCpfBeneficiario(java.lang.String cpfBeneficiario) {
        this.cpfBeneficiario = cpfBeneficiario;
    }


    /**
     * Gets the dataNascimentoBeneficiario value for this BeneficiariosSeguro.
     * 
     * @return dataNascimentoBeneficiario
     */
    public java.util.Calendar getDataNascimentoBeneficiario() {
        return dataNascimentoBeneficiario;
    }


    /**
     * Sets the dataNascimentoBeneficiario value for this BeneficiariosSeguro.
     * 
     * @param dataNascimentoBeneficiario
     */
    public void setDataNascimentoBeneficiario(java.util.Calendar dataNascimentoBeneficiario) {
        this.dataNascimentoBeneficiario = dataNascimentoBeneficiario;
    }


    /**
     * Gets the grauParentesco value for this BeneficiariosSeguro.
     * 
     * @return grauParentesco
     */
    public java.lang.Integer getGrauParentesco() {
        return grauParentesco;
    }


    /**
     * Sets the grauParentesco value for this BeneficiariosSeguro.
     * 
     * @param grauParentesco
     */
    public void setGrauParentesco(java.lang.Integer grauParentesco) {
        this.grauParentesco = grauParentesco;
    }


    /**
     * Gets the idBeneficiario value for this BeneficiariosSeguro.
     * 
     * @return idBeneficiario
     */
    public int getIdBeneficiario() {
        return idBeneficiario;
    }


    /**
     * Sets the idBeneficiario value for this BeneficiariosSeguro.
     * 
     * @param idBeneficiario
     */
    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }


    /**
     * Gets the nomeBeneficiario value for this BeneficiariosSeguro.
     * 
     * @return nomeBeneficiario
     */
    public java.lang.String getNomeBeneficiario() {
        return nomeBeneficiario;
    }


    /**
     * Sets the nomeBeneficiario value for this BeneficiariosSeguro.
     * 
     * @param nomeBeneficiario
     */
    public void setNomeBeneficiario(java.lang.String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }


    /**
     * Gets the percentualParticipacao value for this BeneficiariosSeguro.
     * 
     * @return percentualParticipacao
     */
    public java.lang.Double getPercentualParticipacao() {
        return percentualParticipacao;
    }


    /**
     * Sets the percentualParticipacao value for this BeneficiariosSeguro.
     * 
     * @param percentualParticipacao
     */
    public void setPercentualParticipacao(java.lang.Double percentualParticipacao) {
        this.percentualParticipacao = percentualParticipacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BeneficiariosSeguro)) return false;
        BeneficiariosSeguro other = (BeneficiariosSeguro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.cpfBeneficiario==null && other.getCpfBeneficiario()==null) || 
             (this.cpfBeneficiario!=null &&
              this.cpfBeneficiario.equals(other.getCpfBeneficiario()))) &&
            ((this.dataNascimentoBeneficiario==null && other.getDataNascimentoBeneficiario()==null) || 
             (this.dataNascimentoBeneficiario!=null &&
              this.dataNascimentoBeneficiario.equals(other.getDataNascimentoBeneficiario()))) &&
            ((this.grauParentesco==null && other.getGrauParentesco()==null) || 
             (this.grauParentesco!=null &&
              this.grauParentesco.equals(other.getGrauParentesco()))) &&
            this.idBeneficiario == other.getIdBeneficiario() &&
            ((this.nomeBeneficiario==null && other.getNomeBeneficiario()==null) || 
             (this.nomeBeneficiario!=null &&
              this.nomeBeneficiario.equals(other.getNomeBeneficiario()))) &&
            ((this.percentualParticipacao==null && other.getPercentualParticipacao()==null) || 
             (this.percentualParticipacao!=null &&
              this.percentualParticipacao.equals(other.getPercentualParticipacao())));
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
        if (getCpfBeneficiario() != null) {
            _hashCode += getCpfBeneficiario().hashCode();
        }
        if (getDataNascimentoBeneficiario() != null) {
            _hashCode += getDataNascimentoBeneficiario().hashCode();
        }
        if (getGrauParentesco() != null) {
            _hashCode += getGrauParentesco().hashCode();
        }
        _hashCode += getIdBeneficiario();
        if (getNomeBeneficiario() != null) {
            _hashCode += getNomeBeneficiario().hashCode();
        }
        if (getPercentualParticipacao() != null) {
            _hashCode += getPercentualParticipacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BeneficiariosSeguro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BeneficiariosSeguro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfBeneficiario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfBeneficiario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascimentoBeneficiario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataNascimentoBeneficiario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grauParentesco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grauParentesco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idBeneficiario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idBeneficiario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeBeneficiario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeBeneficiario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualParticipacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "percentualParticipacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
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
