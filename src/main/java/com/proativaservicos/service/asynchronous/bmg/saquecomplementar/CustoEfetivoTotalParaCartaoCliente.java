/**
 * CustoEfetivoTotalParaCartaoCliente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class CustoEfetivoTotalParaCartaoCliente  extends CustoEfetivoTotal  implements java.io.Serializable {
    private java.util.Calendar dataIof;

    private int diaDeVencimento;

    private int percentualDeLimiteDoSaque;

    private double taxaJurosAnual;

    private double taxaJurosAnualParaOServico;

    private double taxaJurosMensal;

    private double taxaJurosMensalParaOServico;

    private double valorCetAnual;

    private double valorCetAnualParaOServico;

    private double valorCetMensal;

    private double valorCetMensalParaOServico;

    private double valorIofAdicional;

    private double valorTarifa;

    private double valorTarifaDeEmissao;

    private double valorTarifaParaSaqueAutorizado;

    private double valorTarifaParaSaqueComplementar;

    public CustoEfetivoTotalParaCartaoCliente() {
    }

    public CustoEfetivoTotalParaCartaoCliente(
           java.util.Calendar dataPrimeiroVencimento,
           java.util.Calendar dataUltimoVencimento,
           Double percentualCETAnual,
           Double percentualCETMensal,
           Float spreadDaOperacao,
           Float spreadMinimo,
           Double taxaCalculada,
           Double taxaFormaCredito,
           Double valorCadastro,
           Double valorCalculadoIof,
           Double valorComissao,
           Double valorFinanciado,
           Double valorIof,
           Double valorOutros,
           java.util.Calendar dataIof,
           int diaDeVencimento,
           int percentualDeLimiteDoSaque,
           double taxaJurosAnual,
           double taxaJurosAnualParaOServico,
           double taxaJurosMensal,
           double taxaJurosMensalParaOServico,
           double valorCetAnual,
           double valorCetAnualParaOServico,
           double valorCetMensal,
           double valorCetMensalParaOServico,
           double valorIofAdicional,
           double valorTarifa,
           double valorTarifaDeEmissao,
           double valorTarifaParaSaqueAutorizado,
           double valorTarifaParaSaqueComplementar) {
        super(
            dataPrimeiroVencimento,
            dataUltimoVencimento,
            percentualCETAnual,
            percentualCETMensal,
            spreadDaOperacao,
            spreadMinimo,
            taxaCalculada,
            taxaFormaCredito,
            valorCadastro,
            valorCalculadoIof,
            valorComissao,
            valorFinanciado,
            valorIof,
            valorOutros);
        this.dataIof = dataIof;
        this.diaDeVencimento = diaDeVencimento;
        this.percentualDeLimiteDoSaque = percentualDeLimiteDoSaque;
        this.taxaJurosAnual = taxaJurosAnual;
        this.taxaJurosAnualParaOServico = taxaJurosAnualParaOServico;
        this.taxaJurosMensal = taxaJurosMensal;
        this.taxaJurosMensalParaOServico = taxaJurosMensalParaOServico;
        this.valorCetAnual = valorCetAnual;
        this.valorCetAnualParaOServico = valorCetAnualParaOServico;
        this.valorCetMensal = valorCetMensal;
        this.valorCetMensalParaOServico = valorCetMensalParaOServico;
        this.valorIofAdicional = valorIofAdicional;
        this.valorTarifa = valorTarifa;
        this.valorTarifaDeEmissao = valorTarifaDeEmissao;
        this.valorTarifaParaSaqueAutorizado = valorTarifaParaSaqueAutorizado;
        this.valorTarifaParaSaqueComplementar = valorTarifaParaSaqueComplementar;
    }


    /**
     * Gets the dataIof value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return dataIof
     */
    public java.util.Calendar getDataIof() {
        return dataIof;
    }


    /**
     * Sets the dataIof value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param dataIof
     */
    public void setDataIof(java.util.Calendar dataIof) {
        this.dataIof = dataIof;
    }


    /**
     * Gets the diaDeVencimento value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return diaDeVencimento
     */
    public int getDiaDeVencimento() {
        return diaDeVencimento;
    }


    /**
     * Sets the diaDeVencimento value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param diaDeVencimento
     */
    public void setDiaDeVencimento(int diaDeVencimento) {
        this.diaDeVencimento = diaDeVencimento;
    }


    /**
     * Gets the percentualDeLimiteDoSaque value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return percentualDeLimiteDoSaque
     */
    public int getPercentualDeLimiteDoSaque() {
        return percentualDeLimiteDoSaque;
    }


    /**
     * Sets the percentualDeLimiteDoSaque value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param percentualDeLimiteDoSaque
     */
    public void setPercentualDeLimiteDoSaque(int percentualDeLimiteDoSaque) {
        this.percentualDeLimiteDoSaque = percentualDeLimiteDoSaque;
    }


    /**
     * Gets the taxaJurosAnual value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return taxaJurosAnual
     */
    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }


    /**
     * Sets the taxaJurosAnual value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param taxaJurosAnual
     */
    public void setTaxaJurosAnual(double taxaJurosAnual) {
        this.taxaJurosAnual = taxaJurosAnual;
    }


    /**
     * Gets the taxaJurosAnualParaOServico value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return taxaJurosAnualParaOServico
     */
    public double getTaxaJurosAnualParaOServico() {
        return taxaJurosAnualParaOServico;
    }


    /**
     * Sets the taxaJurosAnualParaOServico value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param taxaJurosAnualParaOServico
     */
    public void setTaxaJurosAnualParaOServico(double taxaJurosAnualParaOServico) {
        this.taxaJurosAnualParaOServico = taxaJurosAnualParaOServico;
    }


    /**
     * Gets the taxaJurosMensal value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return taxaJurosMensal
     */
    public double getTaxaJurosMensal() {
        return taxaJurosMensal;
    }


    /**
     * Sets the taxaJurosMensal value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param taxaJurosMensal
     */
    public void setTaxaJurosMensal(double taxaJurosMensal) {
        this.taxaJurosMensal = taxaJurosMensal;
    }


    /**
     * Gets the taxaJurosMensalParaOServico value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return taxaJurosMensalParaOServico
     */
    public double getTaxaJurosMensalParaOServico() {
        return taxaJurosMensalParaOServico;
    }


    /**
     * Sets the taxaJurosMensalParaOServico value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param taxaJurosMensalParaOServico
     */
    public void setTaxaJurosMensalParaOServico(double taxaJurosMensalParaOServico) {
        this.taxaJurosMensalParaOServico = taxaJurosMensalParaOServico;
    }


    /**
     * Gets the valorCetAnual value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorCetAnual
     */
    public double getValorCetAnual() {
        return valorCetAnual;
    }


    /**
     * Sets the valorCetAnual value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorCetAnual
     */
    public void setValorCetAnual(double valorCetAnual) {
        this.valorCetAnual = valorCetAnual;
    }


    /**
     * Gets the valorCetAnualParaOServico value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorCetAnualParaOServico
     */
    public double getValorCetAnualParaOServico() {
        return valorCetAnualParaOServico;
    }


    /**
     * Sets the valorCetAnualParaOServico value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorCetAnualParaOServico
     */
    public void setValorCetAnualParaOServico(double valorCetAnualParaOServico) {
        this.valorCetAnualParaOServico = valorCetAnualParaOServico;
    }


    /**
     * Gets the valorCetMensal value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorCetMensal
     */
    public double getValorCetMensal() {
        return valorCetMensal;
    }


    /**
     * Sets the valorCetMensal value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorCetMensal
     */
    public void setValorCetMensal(double valorCetMensal) {
        this.valorCetMensal = valorCetMensal;
    }


    /**
     * Gets the valorCetMensalParaOServico value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorCetMensalParaOServico
     */
    public double getValorCetMensalParaOServico() {
        return valorCetMensalParaOServico;
    }


    /**
     * Sets the valorCetMensalParaOServico value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorCetMensalParaOServico
     */
    public void setValorCetMensalParaOServico(double valorCetMensalParaOServico) {
        this.valorCetMensalParaOServico = valorCetMensalParaOServico;
    }


    /**
     * Gets the valorIofAdicional value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorIofAdicional
     */
    public double getValorIofAdicional() {
        return valorIofAdicional;
    }


    /**
     * Sets the valorIofAdicional value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorIofAdicional
     */
    public void setValorIofAdicional(double valorIofAdicional) {
        this.valorIofAdicional = valorIofAdicional;
    }


    /**
     * Gets the valorTarifa value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorTarifa
     */
    public double getValorTarifa() {
        return valorTarifa;
    }


    /**
     * Sets the valorTarifa value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorTarifa
     */
    public void setValorTarifa(double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }


    /**
     * Gets the valorTarifaDeEmissao value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorTarifaDeEmissao
     */
    public double getValorTarifaDeEmissao() {
        return valorTarifaDeEmissao;
    }


    /**
     * Sets the valorTarifaDeEmissao value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorTarifaDeEmissao
     */
    public void setValorTarifaDeEmissao(double valorTarifaDeEmissao) {
        this.valorTarifaDeEmissao = valorTarifaDeEmissao;
    }


    /**
     * Gets the valorTarifaParaSaqueAutorizado value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorTarifaParaSaqueAutorizado
     */
    public double getValorTarifaParaSaqueAutorizado() {
        return valorTarifaParaSaqueAutorizado;
    }


    /**
     * Sets the valorTarifaParaSaqueAutorizado value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorTarifaParaSaqueAutorizado
     */
    public void setValorTarifaParaSaqueAutorizado(double valorTarifaParaSaqueAutorizado) {
        this.valorTarifaParaSaqueAutorizado = valorTarifaParaSaqueAutorizado;
    }


    /**
     * Gets the valorTarifaParaSaqueComplementar value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @return valorTarifaParaSaqueComplementar
     */
    public double getValorTarifaParaSaqueComplementar() {
        return valorTarifaParaSaqueComplementar;
    }


    /**
     * Sets the valorTarifaParaSaqueComplementar value for this CustoEfetivoTotalParaCartaoCliente.
     * 
     * @param valorTarifaParaSaqueComplementar
     */
    public void setValorTarifaParaSaqueComplementar(double valorTarifaParaSaqueComplementar) {
        this.valorTarifaParaSaqueComplementar = valorTarifaParaSaqueComplementar;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CustoEfetivoTotalParaCartaoCliente)) return false;
        CustoEfetivoTotalParaCartaoCliente other = (CustoEfetivoTotalParaCartaoCliente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.dataIof==null && other.getDataIof()==null) || 
             (this.dataIof!=null &&
              this.dataIof.equals(other.getDataIof()))) &&
            this.diaDeVencimento == other.getDiaDeVencimento() &&
            this.percentualDeLimiteDoSaque == other.getPercentualDeLimiteDoSaque() &&
            this.taxaJurosAnual == other.getTaxaJurosAnual() &&
            this.taxaJurosAnualParaOServico == other.getTaxaJurosAnualParaOServico() &&
            this.taxaJurosMensal == other.getTaxaJurosMensal() &&
            this.taxaJurosMensalParaOServico == other.getTaxaJurosMensalParaOServico() &&
            this.valorCetAnual == other.getValorCetAnual() &&
            this.valorCetAnualParaOServico == other.getValorCetAnualParaOServico() &&
            this.valorCetMensal == other.getValorCetMensal() &&
            this.valorCetMensalParaOServico == other.getValorCetMensalParaOServico() &&
            this.valorIofAdicional == other.getValorIofAdicional() &&
            this.valorTarifa == other.getValorTarifa() &&
            this.valorTarifaDeEmissao == other.getValorTarifaDeEmissao() &&
            this.valorTarifaParaSaqueAutorizado == other.getValorTarifaParaSaqueAutorizado() &&
            this.valorTarifaParaSaqueComplementar == other.getValorTarifaParaSaqueComplementar();
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
        if (getDataIof() != null) {
            _hashCode += getDataIof().hashCode();
        }
        _hashCode += getDiaDeVencimento();
        _hashCode += getPercentualDeLimiteDoSaque();
        _hashCode += new Double(getTaxaJurosAnual()).hashCode();
        _hashCode += new Double(getTaxaJurosAnualParaOServico()).hashCode();
        _hashCode += new Double(getTaxaJurosMensal()).hashCode();
        _hashCode += new Double(getTaxaJurosMensalParaOServico()).hashCode();
        _hashCode += new Double(getValorCetAnual()).hashCode();
        _hashCode += new Double(getValorCetAnualParaOServico()).hashCode();
        _hashCode += new Double(getValorCetMensal()).hashCode();
        _hashCode += new Double(getValorCetMensalParaOServico()).hashCode();
        _hashCode += new Double(getValorIofAdicional()).hashCode();
        _hashCode += new Double(getValorTarifa()).hashCode();
        _hashCode += new Double(getValorTarifaDeEmissao()).hashCode();
        _hashCode += new Double(getValorTarifaParaSaqueAutorizado()).hashCode();
        _hashCode += new Double(getValorTarifaParaSaqueComplementar()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustoEfetivoTotalParaCartaoCliente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CustoEfetivoTotalParaCartaoCliente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataIof");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataIof"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diaDeVencimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "diaDeVencimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualDeLimiteDoSaque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "percentualDeLimiteDoSaque"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxaJurosAnual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxaJurosAnual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxaJurosAnualParaOServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxaJurosAnualParaOServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxaJurosMensal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxaJurosMensal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxaJurosMensalParaOServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxaJurosMensalParaOServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCetAnual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCetAnual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCetAnualParaOServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCetAnualParaOServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCetMensal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCetMensal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCetMensalParaOServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCetMensalParaOServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorIofAdicional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorIofAdicional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTarifaDeEmissao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorTarifaDeEmissao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTarifaParaSaqueAutorizado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorTarifaParaSaqueAutorizado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTarifaParaSaqueComplementar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorTarifaParaSaqueComplementar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
