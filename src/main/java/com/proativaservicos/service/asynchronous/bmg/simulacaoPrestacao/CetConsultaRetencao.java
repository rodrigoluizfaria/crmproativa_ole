/**
 * CetConsultaRetencao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

public class CetConsultaRetencao  extends com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.CetParameter  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CetConsultaRetencao() {
    }

    public CetConsultaRetencao(
           java.lang.String login,
           java.lang.String senha,
           int codigoEntidade,
           int codigoFormaCredito,
           java.lang.Integer codigoSaldoRetido,
           com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Contrato contrato,
           long cpfAsNumber,
           java.lang.String criterioFormaPagamento,
           java.lang.String criterioIof,
           java.lang.String criterioTac,
           java.util.Calendar dataAdmissao,
           java.util.Calendar dataNascimento,
           java.util.Calendar dataProposta,
           java.lang.String loginConsig,
           int loja,
           java.lang.String orgao,
           int quantidadePrestacoes,
           java.lang.Double saldoDevedor,
           java.lang.String senhaConsig,
           int sequencialOrgao,
           java.lang.String servico,
           boolean sindicalizado,
           int tabelaFator,
           double taxaFormaCredito,
           int tipoFormaEnvio,
           long unidadePagadora,
           double valorLiberado,
           double valorPrestacao,
           double valorRenda) {
        super(
            login,
            senha,
            codigoEntidade,
            codigoFormaCredito,
            codigoSaldoRetido,
            contrato,
            cpfAsNumber,
            criterioFormaPagamento,
            criterioIof,
            criterioTac,
            dataAdmissao,
            dataNascimento,
            dataProposta,
            loginConsig,
            loja,
            orgao,
            quantidadePrestacoes,
            saldoDevedor,
            senhaConsig,
            sequencialOrgao,
            servico,
            sindicalizado,
            tabelaFator,
            taxaFormaCredito,
            tipoFormaEnvio,
            unidadePagadora,
            valorLiberado,
            valorPrestacao,
            valorRenda);
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CetConsultaRetencao)) return false;
        CetConsultaRetencao other = (CetConsultaRetencao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CetConsultaRetencao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CetConsultaRetencao"));
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
