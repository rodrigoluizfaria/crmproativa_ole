/**
 * DadosCondicaoVendaSeguro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class DadosCondicaoVendaSeguro  implements java.io.Serializable {
    private java.math.BigDecimal capitalSeguradoMaximo;

    private String formaPagamento;

    private Integer idadeMaximaPermitida;

    private Integer idadeMinimaPermitida;

    private boolean indicaPagamentoParcelado;

    private boolean permiteMultiplasPropostasCliente;

    private Integer quantidadeMesesVigencia;

    private boolean renovacaoAutomatica;

    private Integer sequencialCondicaoVenda;

    private String tipoCalculoPremio;

    private java.math.BigDecimal valorCalculoPremio;

    private java.math.BigDecimal valorSorteio;

    public DadosCondicaoVendaSeguro() {
    }

    public DadosCondicaoVendaSeguro(
           java.math.BigDecimal capitalSeguradoMaximo,
           String formaPagamento,
           Integer idadeMaximaPermitida,
           Integer idadeMinimaPermitida,
           boolean indicaPagamentoParcelado,
           boolean permiteMultiplasPropostasCliente,
           Integer quantidadeMesesVigencia,
           boolean renovacaoAutomatica,
           Integer sequencialCondicaoVenda,
           String tipoCalculoPremio,
           java.math.BigDecimal valorCalculoPremio,
           java.math.BigDecimal valorSorteio) {
           this.capitalSeguradoMaximo = capitalSeguradoMaximo;
           this.formaPagamento = formaPagamento;
           this.idadeMaximaPermitida = idadeMaximaPermitida;
           this.idadeMinimaPermitida = idadeMinimaPermitida;
           this.indicaPagamentoParcelado = indicaPagamentoParcelado;
           this.permiteMultiplasPropostasCliente = permiteMultiplasPropostasCliente;
           this.quantidadeMesesVigencia = quantidadeMesesVigencia;
           this.renovacaoAutomatica = renovacaoAutomatica;
           this.sequencialCondicaoVenda = sequencialCondicaoVenda;
           this.tipoCalculoPremio = tipoCalculoPremio;
           this.valorCalculoPremio = valorCalculoPremio;
           this.valorSorteio = valorSorteio;
    }


    /**
     * Gets the capitalSeguradoMaximo value for this DadosCondicaoVendaSeguro.
     * 
     * @return capitalSeguradoMaximo
     */
    public java.math.BigDecimal getCapitalSeguradoMaximo() {
        return capitalSeguradoMaximo;
    }


    /**
     * Sets the capitalSeguradoMaximo value for this DadosCondicaoVendaSeguro.
     * 
     * @param capitalSeguradoMaximo
     */
    public void setCapitalSeguradoMaximo(java.math.BigDecimal capitalSeguradoMaximo) {
        this.capitalSeguradoMaximo = capitalSeguradoMaximo;
    }


    /**
     * Gets the formaPagamento value for this DadosCondicaoVendaSeguro.
     * 
     * @return formaPagamento
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }


    /**
     * Sets the formaPagamento value for this DadosCondicaoVendaSeguro.
     * 
     * @param formaPagamento
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }


    /**
     * Gets the idadeMaximaPermitida value for this DadosCondicaoVendaSeguro.
     * 
     * @return idadeMaximaPermitida
     */
    public Integer getIdadeMaximaPermitida() {
        return idadeMaximaPermitida;
    }


    /**
     * Sets the idadeMaximaPermitida value for this DadosCondicaoVendaSeguro.
     * 
     * @param idadeMaximaPermitida
     */
    public void setIdadeMaximaPermitida(Integer idadeMaximaPermitida) {
        this.idadeMaximaPermitida = idadeMaximaPermitida;
    }


    /**
     * Gets the idadeMinimaPermitida value for this DadosCondicaoVendaSeguro.
     * 
     * @return idadeMinimaPermitida
     */
    public Integer getIdadeMinimaPermitida() {
        return idadeMinimaPermitida;
    }


    /**
     * Sets the idadeMinimaPermitida value for this DadosCondicaoVendaSeguro.
     * 
     * @param idadeMinimaPermitida
     */
    public void setIdadeMinimaPermitida(Integer idadeMinimaPermitida) {
        this.idadeMinimaPermitida = idadeMinimaPermitida;
    }


    /**
     * Gets the indicaPagamentoParcelado value for this DadosCondicaoVendaSeguro.
     * 
     * @return indicaPagamentoParcelado
     */
    public boolean isIndicaPagamentoParcelado() {
        return indicaPagamentoParcelado;
    }


    /**
     * Sets the indicaPagamentoParcelado value for this DadosCondicaoVendaSeguro.
     * 
     * @param indicaPagamentoParcelado
     */
    public void setIndicaPagamentoParcelado(boolean indicaPagamentoParcelado) {
        this.indicaPagamentoParcelado = indicaPagamentoParcelado;
    }


    /**
     * Gets the permiteMultiplasPropostasCliente value for this DadosCondicaoVendaSeguro.
     * 
     * @return permiteMultiplasPropostasCliente
     */
    public boolean isPermiteMultiplasPropostasCliente() {
        return permiteMultiplasPropostasCliente;
    }


    /**
     * Sets the permiteMultiplasPropostasCliente value for this DadosCondicaoVendaSeguro.
     * 
     * @param permiteMultiplasPropostasCliente
     */
    public void setPermiteMultiplasPropostasCliente(boolean permiteMultiplasPropostasCliente) {
        this.permiteMultiplasPropostasCliente = permiteMultiplasPropostasCliente;
    }


    /**
     * Gets the quantidadeMesesVigencia value for this DadosCondicaoVendaSeguro.
     * 
     * @return quantidadeMesesVigencia
     */
    public Integer getQuantidadeMesesVigencia() {
        return quantidadeMesesVigencia;
    }


    /**
     * Sets the quantidadeMesesVigencia value for this DadosCondicaoVendaSeguro.
     * 
     * @param quantidadeMesesVigencia
     */
    public void setQuantidadeMesesVigencia(Integer quantidadeMesesVigencia) {
        this.quantidadeMesesVigencia = quantidadeMesesVigencia;
    }


    /**
     * Gets the renovacaoAutomatica value for this DadosCondicaoVendaSeguro.
     * 
     * @return renovacaoAutomatica
     */
    public boolean isRenovacaoAutomatica() {
        return renovacaoAutomatica;
    }


    /**
     * Sets the renovacaoAutomatica value for this DadosCondicaoVendaSeguro.
     * 
     * @param renovacaoAutomatica
     */
    public void setRenovacaoAutomatica(boolean renovacaoAutomatica) {
        this.renovacaoAutomatica = renovacaoAutomatica;
    }


    /**
     * Gets the sequencialCondicaoVenda value for this DadosCondicaoVendaSeguro.
     * 
     * @return sequencialCondicaoVenda
     */
    public Integer getSequencialCondicaoVenda() {
        return sequencialCondicaoVenda;
    }


    /**
     * Sets the sequencialCondicaoVenda value for this DadosCondicaoVendaSeguro.
     * 
     * @param sequencialCondicaoVenda
     */
    public void setSequencialCondicaoVenda(Integer sequencialCondicaoVenda) {
        this.sequencialCondicaoVenda = sequencialCondicaoVenda;
    }


    /**
     * Gets the tipoCalculoPremio value for this DadosCondicaoVendaSeguro.
     * 
     * @return tipoCalculoPremio
     */
    public String getTipoCalculoPremio() {
        return tipoCalculoPremio;
    }


    /**
     * Sets the tipoCalculoPremio value for this DadosCondicaoVendaSeguro.
     * 
     * @param tipoCalculoPremio
     */
    public void setTipoCalculoPremio(String tipoCalculoPremio) {
        this.tipoCalculoPremio = tipoCalculoPremio;
    }


    /**
     * Gets the valorCalculoPremio value for this DadosCondicaoVendaSeguro.
     * 
     * @return valorCalculoPremio
     */
    public java.math.BigDecimal getValorCalculoPremio() {
        return valorCalculoPremio;
    }


    /**
     * Sets the valorCalculoPremio value for this DadosCondicaoVendaSeguro.
     * 
     * @param valorCalculoPremio
     */
    public void setValorCalculoPremio(java.math.BigDecimal valorCalculoPremio) {
        this.valorCalculoPremio = valorCalculoPremio;
    }


    /**
     * Gets the valorSorteio value for this DadosCondicaoVendaSeguro.
     * 
     * @return valorSorteio
     */
    public java.math.BigDecimal getValorSorteio() {
        return valorSorteio;
    }


    /**
     * Sets the valorSorteio value for this DadosCondicaoVendaSeguro.
     * 
     * @param valorSorteio
     */
    public void setValorSorteio(java.math.BigDecimal valorSorteio) {
        this.valorSorteio = valorSorteio;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DadosCondicaoVendaSeguro)) return false;
        DadosCondicaoVendaSeguro other = (DadosCondicaoVendaSeguro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.capitalSeguradoMaximo==null && other.getCapitalSeguradoMaximo()==null) || 
             (this.capitalSeguradoMaximo!=null &&
              this.capitalSeguradoMaximo.equals(other.getCapitalSeguradoMaximo()))) &&
            ((this.formaPagamento==null && other.getFormaPagamento()==null) || 
             (this.formaPagamento!=null &&
              this.formaPagamento.equals(other.getFormaPagamento()))) &&
            ((this.idadeMaximaPermitida==null && other.getIdadeMaximaPermitida()==null) || 
             (this.idadeMaximaPermitida!=null &&
              this.idadeMaximaPermitida.equals(other.getIdadeMaximaPermitida()))) &&
            ((this.idadeMinimaPermitida==null && other.getIdadeMinimaPermitida()==null) || 
             (this.idadeMinimaPermitida!=null &&
              this.idadeMinimaPermitida.equals(other.getIdadeMinimaPermitida()))) &&
            this.indicaPagamentoParcelado == other.isIndicaPagamentoParcelado() &&
            this.permiteMultiplasPropostasCliente == other.isPermiteMultiplasPropostasCliente() &&
            ((this.quantidadeMesesVigencia==null && other.getQuantidadeMesesVigencia()==null) || 
             (this.quantidadeMesesVigencia!=null &&
              this.quantidadeMesesVigencia.equals(other.getQuantidadeMesesVigencia()))) &&
            this.renovacaoAutomatica == other.isRenovacaoAutomatica() &&
            ((this.sequencialCondicaoVenda==null && other.getSequencialCondicaoVenda()==null) || 
             (this.sequencialCondicaoVenda!=null &&
              this.sequencialCondicaoVenda.equals(other.getSequencialCondicaoVenda()))) &&
            ((this.tipoCalculoPremio==null && other.getTipoCalculoPremio()==null) || 
             (this.tipoCalculoPremio!=null &&
              this.tipoCalculoPremio.equals(other.getTipoCalculoPremio()))) &&
            ((this.valorCalculoPremio==null && other.getValorCalculoPremio()==null) || 
             (this.valorCalculoPremio!=null &&
              this.valorCalculoPremio.equals(other.getValorCalculoPremio()))) &&
            ((this.valorSorteio==null && other.getValorSorteio()==null) || 
             (this.valorSorteio!=null &&
              this.valorSorteio.equals(other.getValorSorteio())));
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
        if (getCapitalSeguradoMaximo() != null) {
            _hashCode += getCapitalSeguradoMaximo().hashCode();
        }
        if (getFormaPagamento() != null) {
            _hashCode += getFormaPagamento().hashCode();
        }
        if (getIdadeMaximaPermitida() != null) {
            _hashCode += getIdadeMaximaPermitida().hashCode();
        }
        if (getIdadeMinimaPermitida() != null) {
            _hashCode += getIdadeMinimaPermitida().hashCode();
        }
        _hashCode += (isIndicaPagamentoParcelado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isPermiteMultiplasPropostasCliente() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getQuantidadeMesesVigencia() != null) {
            _hashCode += getQuantidadeMesesVigencia().hashCode();
        }
        _hashCode += (isRenovacaoAutomatica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSequencialCondicaoVenda() != null) {
            _hashCode += getSequencialCondicaoVenda().hashCode();
        }
        if (getTipoCalculoPremio() != null) {
            _hashCode += getTipoCalculoPremio().hashCode();
        }
        if (getValorCalculoPremio() != null) {
            _hashCode += getValorCalculoPremio().hashCode();
        }
        if (getValorSorteio() != null) {
            _hashCode += getValorSorteio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DadosCondicaoVendaSeguro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCondicaoVendaSeguro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capitalSeguradoMaximo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capitalSeguradoMaximo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idadeMaximaPermitida");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idadeMaximaPermitida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idadeMinimaPermitida");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idadeMinimaPermitida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicaPagamentoParcelado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicaPagamentoParcelado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permiteMultiplasPropostasCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permiteMultiplasPropostasCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeMesesVigencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeMesesVigencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("renovacaoAutomatica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "renovacaoAutomatica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialCondicaoVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialCondicaoVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCalculoPremio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoCalculoPremio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCalculoPremio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCalculoPremio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSorteio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSorteio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "decimal"));
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
