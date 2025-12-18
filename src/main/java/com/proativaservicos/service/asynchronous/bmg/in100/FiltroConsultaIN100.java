/**
 * FiltroConsultaIN100.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.in100;

public class FiltroConsultaIN100  extends com.proativaservicos.service.asynchronous.bmg.in100.WebServiceParameter  implements java.io.Serializable {
    private java.lang.String cpf;

    private java.lang.Long numeroSolicitacao;

    private java.util.Calendar periodoFinal;

    private java.util.Calendar periodoInicial;

    public FiltroConsultaIN100() {
    }

    public FiltroConsultaIN100(
           java.lang.String login,
           java.lang.String senha,
           java.lang.String cpf,
           java.lang.Long numeroSolicitacao,
           java.util.Calendar periodoFinal,
           java.util.Calendar periodoInicial) {
        super(
            login,
            senha);
        this.cpf = cpf;
        this.numeroSolicitacao = numeroSolicitacao;
        this.periodoFinal = periodoFinal;
        this.periodoInicial = periodoInicial;
    }


    /**
     * Gets the cpf value for this FiltroConsultaIN100.
     * 
     * @return cpf
     */
    public java.lang.String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this FiltroConsultaIN100.
     * 
     * @param cpf
     */
    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the numeroSolicitacao value for this FiltroConsultaIN100.
     * 
     * @return numeroSolicitacao
     */
    public java.lang.Long getNumeroSolicitacao() {
        return numeroSolicitacao;
    }


    /**
     * Sets the numeroSolicitacao value for this FiltroConsultaIN100.
     * 
     * @param numeroSolicitacao
     */
    public void setNumeroSolicitacao(java.lang.Long numeroSolicitacao) {
        this.numeroSolicitacao = numeroSolicitacao;
    }


    /**
     * Gets the periodoFinal value for this FiltroConsultaIN100.
     * 
     * @return periodoFinal
     */
    public java.util.Calendar getPeriodoFinal() {
        return periodoFinal;
    }


    /**
     * Sets the periodoFinal value for this FiltroConsultaIN100.
     * 
     * @param periodoFinal
     */
    public void setPeriodoFinal(java.util.Calendar periodoFinal) {
        this.periodoFinal = periodoFinal;
    }


    /**
     * Gets the periodoInicial value for this FiltroConsultaIN100.
     * 
     * @return periodoInicial
     */
    public java.util.Calendar getPeriodoInicial() {
        return periodoInicial;
    }


    /**
     * Sets the periodoInicial value for this FiltroConsultaIN100.
     * 
     * @param periodoInicial
     */
    public void setPeriodoInicial(java.util.Calendar periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FiltroConsultaIN100)) return false;
        FiltroConsultaIN100 other = (FiltroConsultaIN100) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.numeroSolicitacao==null && other.getNumeroSolicitacao()==null) || 
             (this.numeroSolicitacao!=null &&
              this.numeroSolicitacao.equals(other.getNumeroSolicitacao()))) &&
            ((this.periodoFinal==null && other.getPeriodoFinal()==null) || 
             (this.periodoFinal!=null &&
              this.periodoFinal.equals(other.getPeriodoFinal()))) &&
            ((this.periodoInicial==null && other.getPeriodoInicial()==null) || 
             (this.periodoInicial!=null &&
              this.periodoInicial.equals(other.getPeriodoInicial())));
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
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getNumeroSolicitacao() != null) {
            _hashCode += getNumeroSolicitacao().hashCode();
        }
        if (getPeriodoFinal() != null) {
            _hashCode += getPeriodoFinal().hashCode();
        }
        if (getPeriodoInicial() != null) {
            _hashCode += getPeriodoInicial().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FiltroConsultaIN100.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FiltroConsultaIN100"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSolicitacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroSolicitacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodoFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "periodoFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodoInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "periodoInicial"));
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
