/**
 * DadosCancelamentoSegurosParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class DadosCancelamentoSegurosParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroParameter  implements java.io.Serializable {
    private Integer codigoBanco;

    private String codigoVerificador;

    private Integer numeroAgencia;

    private Long numeroConta;

    public DadosCancelamentoSegurosParameter() {
    }

    public DadosCancelamentoSegurosParameter(
           String login,
           String senha,
           java.util.Calendar data,
           String justificativa,
           String numeroAde,
           Integer tipoProdutoSeguro,
           Integer codigoBanco,
           String codigoVerificador,
           Integer numeroAgencia,
           Long numeroConta) {
        super(
            login,
            senha,
            data,
            justificativa,
            numeroAde,
            tipoProdutoSeguro);
        this.codigoBanco = codigoBanco;
        this.codigoVerificador = codigoVerificador;
        this.numeroAgencia = numeroAgencia;
        this.numeroConta = numeroConta;
    }


    /**
     * Gets the codigoBanco value for this DadosCancelamentoSegurosParameter.
     * 
     * @return codigoBanco
     */
    public Integer getCodigoBanco() {
        return codigoBanco;
    }


    /**
     * Sets the codigoBanco value for this DadosCancelamentoSegurosParameter.
     * 
     * @param codigoBanco
     */
    public void setCodigoBanco(Integer codigoBanco) {
        this.codigoBanco = codigoBanco;
    }


    /**
     * Gets the codigoVerificador value for this DadosCancelamentoSegurosParameter.
     * 
     * @return codigoVerificador
     */
    public String getCodigoVerificador() {
        return codigoVerificador;
    }


    /**
     * Sets the codigoVerificador value for this DadosCancelamentoSegurosParameter.
     * 
     * @param codigoVerificador
     */
    public void setCodigoVerificador(String codigoVerificador) {
        this.codigoVerificador = codigoVerificador;
    }


    /**
     * Gets the numeroAgencia value for this DadosCancelamentoSegurosParameter.
     * 
     * @return numeroAgencia
     */
    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }


    /**
     * Sets the numeroAgencia value for this DadosCancelamentoSegurosParameter.
     * 
     * @param numeroAgencia
     */
    public void setNumeroAgencia(Integer numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }


    /**
     * Gets the numeroConta value for this DadosCancelamentoSegurosParameter.
     * 
     * @return numeroConta
     */
    public Long getNumeroConta() {
        return numeroConta;
    }


    /**
     * Sets the numeroConta value for this DadosCancelamentoSegurosParameter.
     * 
     * @param numeroConta
     */
    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DadosCancelamentoSegurosParameter)) return false;
        DadosCancelamentoSegurosParameter other = (DadosCancelamentoSegurosParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoBanco==null && other.getCodigoBanco()==null) || 
             (this.codigoBanco!=null &&
              this.codigoBanco.equals(other.getCodigoBanco()))) &&
            ((this.codigoVerificador==null && other.getCodigoVerificador()==null) || 
             (this.codigoVerificador!=null &&
              this.codigoVerificador.equals(other.getCodigoVerificador()))) &&
            ((this.numeroAgencia==null && other.getNumeroAgencia()==null) || 
             (this.numeroAgencia!=null &&
              this.numeroAgencia.equals(other.getNumeroAgencia()))) &&
            ((this.numeroConta==null && other.getNumeroConta()==null) || 
             (this.numeroConta!=null &&
              this.numeroConta.equals(other.getNumeroConta())));
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
        if (getCodigoBanco() != null) {
            _hashCode += getCodigoBanco().hashCode();
        }
        if (getCodigoVerificador() != null) {
            _hashCode += getCodigoVerificador().hashCode();
        }
        if (getNumeroAgencia() != null) {
            _hashCode += getNumeroAgencia().hashCode();
        }
        if (getNumeroConta() != null) {
            _hashCode += getNumeroConta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DadosCancelamentoSegurosParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCancelamentoSegurosParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoBanco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoBanco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoVerificador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoVerificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAgencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroAgencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
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
