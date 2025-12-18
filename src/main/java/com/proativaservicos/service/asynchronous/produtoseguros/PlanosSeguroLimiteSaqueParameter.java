/**
 * PlanosSeguroLimiteSaqueParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PlanosSeguroLimiteSaqueParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter  implements java.io.Serializable {
    private String codigoServico;

    private String cpf;

    private Boolean cpfImpedidoComissionar;

    private Double valorMargem;

    public PlanosSeguroLimiteSaqueParameter() {
    }

    public PlanosSeguroLimiteSaqueParameter(
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
           String codigoServico,
           String cpf,
           Boolean cpfImpedidoComissionar,
           Double valorMargem) {
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
        this.codigoServico = codigoServico;
        this.cpf = cpf;
        this.cpfImpedidoComissionar = cpfImpedidoComissionar;
        this.valorMargem = valorMargem;
    }


    /**
     * Gets the codigoServico value for this PlanosSeguroLimiteSaqueParameter.
     * 
     * @return codigoServico
     */
    public String getCodigoServico() {
        return codigoServico;
    }


    /**
     * Sets the codigoServico value for this PlanosSeguroLimiteSaqueParameter.
     * 
     * @param codigoServico
     */
    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }


    /**
     * Gets the cpf value for this PlanosSeguroLimiteSaqueParameter.
     * 
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this PlanosSeguroLimiteSaqueParameter.
     * 
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the cpfImpedidoComissionar value for this PlanosSeguroLimiteSaqueParameter.
     * 
     * @return cpfImpedidoComissionar
     */
    public Boolean getCpfImpedidoComissionar() {
        return cpfImpedidoComissionar;
    }


    /**
     * Sets the cpfImpedidoComissionar value for this PlanosSeguroLimiteSaqueParameter.
     * 
     * @param cpfImpedidoComissionar
     */
    public void setCpfImpedidoComissionar(Boolean cpfImpedidoComissionar) {
        this.cpfImpedidoComissionar = cpfImpedidoComissionar;
    }


    /**
     * Gets the valorMargem value for this PlanosSeguroLimiteSaqueParameter.
     * 
     * @return valorMargem
     */
    public Double getValorMargem() {
        return valorMargem;
    }


    /**
     * Sets the valorMargem value for this PlanosSeguroLimiteSaqueParameter.
     * 
     * @param valorMargem
     */
    public void setValorMargem(Double valorMargem) {
        this.valorMargem = valorMargem;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlanosSeguroLimiteSaqueParameter)) return false;
        PlanosSeguroLimiteSaqueParameter other = (PlanosSeguroLimiteSaqueParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoServico==null && other.getCodigoServico()==null) || 
             (this.codigoServico!=null &&
              this.codigoServico.equals(other.getCodigoServico()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.cpfImpedidoComissionar==null && other.getCpfImpedidoComissionar()==null) || 
             (this.cpfImpedidoComissionar!=null &&
              this.cpfImpedidoComissionar.equals(other.getCpfImpedidoComissionar()))) &&
            ((this.valorMargem==null && other.getValorMargem()==null) || 
             (this.valorMargem!=null &&
              this.valorMargem.equals(other.getValorMargem())));
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
        if (getCodigoServico() != null) {
            _hashCode += getCodigoServico().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getCpfImpedidoComissionar() != null) {
            _hashCode += getCpfImpedidoComissionar().hashCode();
        }
        if (getValorMargem() != null) {
            _hashCode += getValorMargem().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlanosSeguroLimiteSaqueParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroLimiteSaqueParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfImpedidoComissionar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfImpedidoComissionar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorMargem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorMargem"));
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
