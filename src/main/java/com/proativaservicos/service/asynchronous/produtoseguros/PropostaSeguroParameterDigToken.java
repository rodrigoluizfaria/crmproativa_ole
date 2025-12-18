/**
 * PropostaSeguroParameterDigToken.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PropostaSeguroParameterDigToken  extends com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameter  implements java.io.Serializable {
    private String token;

    public PropostaSeguroParameterDigToken() {
    }

    public PropostaSeguroParameterDigToken(
           String login,
           String senha,
           com.proativaservicos.service.asynchronous.produtoseguros.ClienteParameter cliente,
           int codLoja,
           String codigoAssociado,
           int codigoPlano,
           int codigoSeguro,
           int codigoTipoBeneficio,
           Integer codigoTipoFormaEnvio,
           Integer codigoTipoPagamento,
           int codigoformaPagamentoProdutoSeguro,
           String cpfOperadorVendas,
           String dataHoraAdesao,
           String loginConsig,
           String matricula,
           String nomeSocial,
           int numeroInternoConta,
           Long protocoloMultiProdutos,
           double renda,
           String senhaConsig,
           String token) {
        super(
            login,
            senha,
            cliente,
            codLoja,
            codigoAssociado,
            codigoPlano,
            codigoSeguro,
            codigoTipoBeneficio,
            codigoTipoFormaEnvio,
            codigoTipoPagamento,
            codigoformaPagamentoProdutoSeguro,
            cpfOperadorVendas,
            dataHoraAdesao,
            loginConsig,
            matricula,
            nomeSocial,
            numeroInternoConta,
            protocoloMultiProdutos,
            renda,
            senhaConsig);
        this.token = token;
    }


    /**
     * Gets the token value for this PropostaSeguroParameterDigToken.
     * 
     * @return token
     */
    public String getToken() {
        return token;
    }


    /**
     * Sets the token value for this PropostaSeguroParameterDigToken.
     * 
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PropostaSeguroParameterDigToken)) return false;
        PropostaSeguroParameterDigToken other = (PropostaSeguroParameterDigToken) obj;
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
        new org.apache.axis.description.TypeDesc(PropostaSeguroParameterDigToken.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaSeguroParameterDigToken"));
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
