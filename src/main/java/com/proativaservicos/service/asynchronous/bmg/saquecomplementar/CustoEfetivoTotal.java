/**
 * CustoEfetivoTotal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class CustoEfetivoTotal  implements java.io.Serializable {
    private java.util.Calendar dataPrimeiroVencimento;

    private java.util.Calendar dataUltimoVencimento;

    private Double percentualCETAnual;

    private Double percentualCETMensal;

    private Float spreadDaOperacao;

    private Float spreadMinimo;

    private Double taxaCalculada;

    private Double taxaFormaCredito;

    private Double valorCadastro;

    private Double valorCalculadoIof;

    private Double valorComissao;

    private Double valorFinanciado;

    private Double valorIof;

    private Double valorOutros;

    public CustoEfetivoTotal() {
    }

    public CustoEfetivoTotal(
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
           Double valorOutros) {
           this.dataPrimeiroVencimento = dataPrimeiroVencimento;
           this.dataUltimoVencimento = dataUltimoVencimento;
           this.percentualCETAnual = percentualCETAnual;
           this.percentualCETMensal = percentualCETMensal;
           this.spreadDaOperacao = spreadDaOperacao;
           this.spreadMinimo = spreadMinimo;
           this.taxaCalculada = taxaCalculada;
           this.taxaFormaCredito = taxaFormaCredito;
           this.valorCadastro = valorCadastro;
           this.valorCalculadoIof = valorCalculadoIof;
           this.valorComissao = valorComissao;
           this.valorFinanciado = valorFinanciado;
           this.valorIof = valorIof;
           this.valorOutros = valorOutros;
    }


    /**
     * Gets the dataPrimeiroVencimento value for this CustoEfetivoTotal.
     * 
     * @return dataPrimeiroVencimento
     */
    public java.util.Calendar getDataPrimeiroVencimento() {
        return dataPrimeiroVencimento;
    }


    /**
     * Sets the dataPrimeiroVencimento value for this CustoEfetivoTotal.
     * 
     * @param dataPrimeiroVencimento
     */
    public void setDataPrimeiroVencimento(java.util.Calendar dataPrimeiroVencimento) {
        this.dataPrimeiroVencimento = dataPrimeiroVencimento;
    }


    /**
     * Gets the dataUltimoVencimento value for this CustoEfetivoTotal.
     * 
     * @return dataUltimoVencimento
     */
    public java.util.Calendar getDataUltimoVencimento() {
        return dataUltimoVencimento;
    }


    /**
     * Sets the dataUltimoVencimento value for this CustoEfetivoTotal.
     * 
     * @param dataUltimoVencimento
     */
    public void setDataUltimoVencimento(java.util.Calendar dataUltimoVencimento) {
        this.dataUltimoVencimento = dataUltimoVencimento;
    }


    /**
     * Gets the percentualCETAnual value for this CustoEfetivoTotal.
     * 
     * @return percentualCETAnual
     */
    public Double getPercentualCETAnual() {
        return percentualCETAnual;
    }


    /**
     * Sets the percentualCETAnual value for this CustoEfetivoTotal.
     * 
     * @param percentualCETAnual
     */
    public void setPercentualCETAnual(Double percentualCETAnual) {
        this.percentualCETAnual = percentualCETAnual;
    }


    /**
     * Gets the percentualCETMensal value for this CustoEfetivoTotal.
     * 
     * @return percentualCETMensal
     */
    public Double getPercentualCETMensal() {
        return percentualCETMensal;
    }


    /**
     * Sets the percentualCETMensal value for this CustoEfetivoTotal.
     * 
     * @param percentualCETMensal
     */
    public void setPercentualCETMensal(Double percentualCETMensal) {
        this.percentualCETMensal = percentualCETMensal;
    }


    /**
     * Gets the spreadDaOperacao value for this CustoEfetivoTotal.
     * 
     * @return spreadDaOperacao
     */
    public Float getSpreadDaOperacao() {
        return spreadDaOperacao;
    }


    /**
     * Sets the spreadDaOperacao value for this CustoEfetivoTotal.
     * 
     * @param spreadDaOperacao
     */
    public void setSpreadDaOperacao(Float spreadDaOperacao) {
        this.spreadDaOperacao = spreadDaOperacao;
    }


    /**
     * Gets the spreadMinimo value for this CustoEfetivoTotal.
     * 
     * @return spreadMinimo
     */
    public Float getSpreadMinimo() {
        return spreadMinimo;
    }


    /**
     * Sets the spreadMinimo value for this CustoEfetivoTotal.
     * 
     * @param spreadMinimo
     */
    public void setSpreadMinimo(Float spreadMinimo) {
        this.spreadMinimo = spreadMinimo;
    }


    /**
     * Gets the taxaCalculada value for this CustoEfetivoTotal.
     * 
     * @return taxaCalculada
     */
    public Double getTaxaCalculada() {
        return taxaCalculada;
    }


    /**
     * Sets the taxaCalculada value for this CustoEfetivoTotal.
     * 
     * @param taxaCalculada
     */
    public void setTaxaCalculada(Double taxaCalculada) {
        this.taxaCalculada = taxaCalculada;
    }


    /**
     * Gets the taxaFormaCredito value for this CustoEfetivoTotal.
     * 
     * @return taxaFormaCredito
     */
    public Double getTaxaFormaCredito() {
        return taxaFormaCredito;
    }


    /**
     * Sets the taxaFormaCredito value for this CustoEfetivoTotal.
     * 
     * @param taxaFormaCredito
     */
    public void setTaxaFormaCredito(Double taxaFormaCredito) {
        this.taxaFormaCredito = taxaFormaCredito;
    }


    /**
     * Gets the valorCadastro value for this CustoEfetivoTotal.
     * 
     * @return valorCadastro
     */
    public Double getValorCadastro() {
        return valorCadastro;
    }


    /**
     * Sets the valorCadastro value for this CustoEfetivoTotal.
     * 
     * @param valorCadastro
     */
    public void setValorCadastro(Double valorCadastro) {
        this.valorCadastro = valorCadastro;
    }


    /**
     * Gets the valorCalculadoIof value for this CustoEfetivoTotal.
     * 
     * @return valorCalculadoIof
     */
    public Double getValorCalculadoIof() {
        return valorCalculadoIof;
    }


    /**
     * Sets the valorCalculadoIof value for this CustoEfetivoTotal.
     * 
     * @param valorCalculadoIof
     */
    public void setValorCalculadoIof(Double valorCalculadoIof) {
        this.valorCalculadoIof = valorCalculadoIof;
    }


    /**
     * Gets the valorComissao value for this CustoEfetivoTotal.
     * 
     * @return valorComissao
     */
    public Double getValorComissao() {
        return valorComissao;
    }


    /**
     * Sets the valorComissao value for this CustoEfetivoTotal.
     * 
     * @param valorComissao
     */
    public void setValorComissao(Double valorComissao) {
        this.valorComissao = valorComissao;
    }


    /**
     * Gets the valorFinanciado value for this CustoEfetivoTotal.
     * 
     * @return valorFinanciado
     */
    public Double getValorFinanciado() {
        return valorFinanciado;
    }


    /**
     * Sets the valorFinanciado value for this CustoEfetivoTotal.
     * 
     * @param valorFinanciado
     */
    public void setValorFinanciado(Double valorFinanciado) {
        this.valorFinanciado = valorFinanciado;
    }


    /**
     * Gets the valorIof value for this CustoEfetivoTotal.
     * 
     * @return valorIof
     */
    public Double getValorIof() {
        return valorIof;
    }


    /**
     * Sets the valorIof value for this CustoEfetivoTotal.
     * 
     * @param valorIof
     */
    public void setValorIof(Double valorIof) {
        this.valorIof = valorIof;
    }


    /**
     * Gets the valorOutros value for this CustoEfetivoTotal.
     * 
     * @return valorOutros
     */
    public Double getValorOutros() {
        return valorOutros;
    }


    /**
     * Sets the valorOutros value for this CustoEfetivoTotal.
     * 
     * @param valorOutros
     */
    public void setValorOutros(Double valorOutros) {
        this.valorOutros = valorOutros;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CustoEfetivoTotal)) return false;
        CustoEfetivoTotal other = (CustoEfetivoTotal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataPrimeiroVencimento==null && other.getDataPrimeiroVencimento()==null) || 
             (this.dataPrimeiroVencimento!=null &&
              this.dataPrimeiroVencimento.equals(other.getDataPrimeiroVencimento()))) &&
            ((this.dataUltimoVencimento==null && other.getDataUltimoVencimento()==null) || 
             (this.dataUltimoVencimento!=null &&
              this.dataUltimoVencimento.equals(other.getDataUltimoVencimento()))) &&
            ((this.percentualCETAnual==null && other.getPercentualCETAnual()==null) || 
             (this.percentualCETAnual!=null &&
              this.percentualCETAnual.equals(other.getPercentualCETAnual()))) &&
            ((this.percentualCETMensal==null && other.getPercentualCETMensal()==null) || 
             (this.percentualCETMensal!=null &&
              this.percentualCETMensal.equals(other.getPercentualCETMensal()))) &&
            ((this.spreadDaOperacao==null && other.getSpreadDaOperacao()==null) || 
             (this.spreadDaOperacao!=null &&
              this.spreadDaOperacao.equals(other.getSpreadDaOperacao()))) &&
            ((this.spreadMinimo==null && other.getSpreadMinimo()==null) || 
             (this.spreadMinimo!=null &&
              this.spreadMinimo.equals(other.getSpreadMinimo()))) &&
            ((this.taxaCalculada==null && other.getTaxaCalculada()==null) || 
             (this.taxaCalculada!=null &&
              this.taxaCalculada.equals(other.getTaxaCalculada()))) &&
            ((this.taxaFormaCredito==null && other.getTaxaFormaCredito()==null) || 
             (this.taxaFormaCredito!=null &&
              this.taxaFormaCredito.equals(other.getTaxaFormaCredito()))) &&
            ((this.valorCadastro==null && other.getValorCadastro()==null) || 
             (this.valorCadastro!=null &&
              this.valorCadastro.equals(other.getValorCadastro()))) &&
            ((this.valorCalculadoIof==null && other.getValorCalculadoIof()==null) || 
             (this.valorCalculadoIof!=null &&
              this.valorCalculadoIof.equals(other.getValorCalculadoIof()))) &&
            ((this.valorComissao==null && other.getValorComissao()==null) || 
             (this.valorComissao!=null &&
              this.valorComissao.equals(other.getValorComissao()))) &&
            ((this.valorFinanciado==null && other.getValorFinanciado()==null) || 
             (this.valorFinanciado!=null &&
              this.valorFinanciado.equals(other.getValorFinanciado()))) &&
            ((this.valorIof==null && other.getValorIof()==null) || 
             (this.valorIof!=null &&
              this.valorIof.equals(other.getValorIof()))) &&
            ((this.valorOutros==null && other.getValorOutros()==null) || 
             (this.valorOutros!=null &&
              this.valorOutros.equals(other.getValorOutros())));
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
        if (getDataPrimeiroVencimento() != null) {
            _hashCode += getDataPrimeiroVencimento().hashCode();
        }
        if (getDataUltimoVencimento() != null) {
            _hashCode += getDataUltimoVencimento().hashCode();
        }
        if (getPercentualCETAnual() != null) {
            _hashCode += getPercentualCETAnual().hashCode();
        }
        if (getPercentualCETMensal() != null) {
            _hashCode += getPercentualCETMensal().hashCode();
        }
        if (getSpreadDaOperacao() != null) {
            _hashCode += getSpreadDaOperacao().hashCode();
        }
        if (getSpreadMinimo() != null) {
            _hashCode += getSpreadMinimo().hashCode();
        }
        if (getTaxaCalculada() != null) {
            _hashCode += getTaxaCalculada().hashCode();
        }
        if (getTaxaFormaCredito() != null) {
            _hashCode += getTaxaFormaCredito().hashCode();
        }
        if (getValorCadastro() != null) {
            _hashCode += getValorCadastro().hashCode();
        }
        if (getValorCalculadoIof() != null) {
            _hashCode += getValorCalculadoIof().hashCode();
        }
        if (getValorComissao() != null) {
            _hashCode += getValorComissao().hashCode();
        }
        if (getValorFinanciado() != null) {
            _hashCode += getValorFinanciado().hashCode();
        }
        if (getValorIof() != null) {
            _hashCode += getValorIof().hashCode();
        }
        if (getValorOutros() != null) {
            _hashCode += getValorOutros().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustoEfetivoTotal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CustoEfetivoTotal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPrimeiroVencimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPrimeiroVencimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataUltimoVencimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataUltimoVencimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualCETAnual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "percentualCETAnual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualCETMensal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "percentualCETMensal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spreadDaOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "spreadDaOperacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spreadMinimo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "spreadMinimo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxaCalculada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxaCalculada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxaFormaCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxaFormaCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCadastro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCadastro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCalculadoIof");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCalculadoIof"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorComissao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorComissao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorFinanciado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorFinanciado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorIof");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorIof"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorOutros");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorOutros"));
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
