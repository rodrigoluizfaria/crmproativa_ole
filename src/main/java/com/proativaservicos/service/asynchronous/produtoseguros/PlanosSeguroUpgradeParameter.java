/**
 * PlanosSeguroUpgradeParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PlanosSeguroUpgradeParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter  implements java.io.Serializable {
    private String cpf;

    public PlanosSeguroUpgradeParameter() {
    }

    public PlanosSeguroUpgradeParameter(
           String login,
           String senha,
           Integer codigoProdutoSeguro,
           java.util.Calendar dataNascimento,
           String entidade,
           String loginConsig,
           String matricula,
           Integer numeroInternoConta,
           Double renda,
           String senhaConsig,
           String sequencialOrgao,
           Integer tipoPagamentoSeguro,
           String cpf) {
        super(
            login,
            senha,
            codigoProdutoSeguro,
            dataNascimento,
            entidade,
            loginConsig,
            matricula,
            numeroInternoConta,
            renda,
            senhaConsig,
            sequencialOrgao,
            tipoPagamentoSeguro);
        this.cpf = cpf;
    }


    /**
     * Gets the cpf value for this PlanosSeguroUpgradeParameter.
     * 
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this PlanosSeguroUpgradeParameter.
     * 
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlanosSeguroUpgradeParameter)) return false;
        PlanosSeguroUpgradeParameter other = (PlanosSeguroUpgradeParameter) obj;
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
              this.cpf.equals(other.getCpf())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlanosSeguroUpgradeParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroUpgradeParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
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
