/**
 * PlanosSeguroLimiteSaqueDigTokenParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PlanosSeguroLimiteSaqueDigTokenParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueParameter  implements java.io.Serializable {
    private String token;

    public PlanosSeguroLimiteSaqueDigTokenParameter() {
    }

    public PlanosSeguroLimiteSaqueDigTokenParameter(
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
           Double valorMargem,
           String token) {
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
            tipoPagamentoSeguro,
            codigoServico,
            cpf,
            cpfImpedidoComissionar,
            valorMargem);
        this.token = token;
    }


    /**
     * Gets the token value for this PlanosSeguroLimiteSaqueDigTokenParameter.
     * 
     * @return token
     */
    public String getToken() {
        return token;
    }


    /**
     * Sets the token value for this PlanosSeguroLimiteSaqueDigTokenParameter.
     * 
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlanosSeguroLimiteSaqueDigTokenParameter)) return false;
        PlanosSeguroLimiteSaqueDigTokenParameter other = (PlanosSeguroLimiteSaqueDigTokenParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken())));
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
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlanosSeguroLimiteSaqueDigTokenParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroLimiteSaqueDigTokenParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
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
