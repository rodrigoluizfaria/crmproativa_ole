/**
 * PlanosContratacaoSeguroStandAlone.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PlanosContratacaoSeguroStandAlone  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceBean  implements java.io.Serializable {

    private com.proativaservicos.service.asynchronous.produtoseguros.PlanoCoberturaSeguroStadAlone[] coberturas;

    private Integer codigoPlano;

    private Integer codigoSeguro;

    private String nomePlano;

    private Integer quantidadeMesVigente;

    private String tipoPagamento;

    private Double valorCapitalSegurado;

    private Double valorLiberadoComSeguroFgts;

    private Double valorPremio;

    private Integer rating;

    public PlanosContratacaoSeguroStandAlone() {
    }

    public PlanosContratacaoSeguroStandAlone(
            com.proativaservicos.service.asynchronous.produtoseguros.PlanoCoberturaSeguroStadAlone[] coberturas,
            Integer codigoPlano,
            Integer codigoSeguro,
            String nomePlano,
            Integer quantidadeMesVigente,
            String tipoPagamento,
            Double valorCapitalSegurado,
            Double valorLiberadoComSeguroFgts,
            Double valorPremio,
            Integer rating) {

        this.coberturas = coberturas;
        this.codigoPlano = codigoPlano;
        this.codigoSeguro = codigoSeguro;
        this.nomePlano = nomePlano;
        this.quantidadeMesVigente = quantidadeMesVigente;
        this.tipoPagamento = tipoPagamento;
        this.valorCapitalSegurado = valorCapitalSegurado;
        this.valorLiberadoComSeguroFgts = valorLiberadoComSeguroFgts;
        this.valorPremio = valorPremio;
        this.rating = rating;
    }


    /**
     * Gets the coberturas value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return coberturas
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.PlanoCoberturaSeguroStadAlone[] getCoberturas() {
        return coberturas;
    }


    /**
     * Sets the coberturas value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param coberturas
     */
    public void setCoberturas(com.proativaservicos.service.asynchronous.produtoseguros.PlanoCoberturaSeguroStadAlone[] coberturas) {
        this.coberturas = coberturas;
    }


    /**
     * Gets the codigoPlano value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return codigoPlano
     */
    public Integer getCodigoPlano() {
        return codigoPlano;
    }


    /**
     * Sets the codigoPlano value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param codigoPlano
     */
    public void setCodigoPlano(Integer codigoPlano) {
        this.codigoPlano = codigoPlano;
    }


    /**
     * Gets the codigoSeguro value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return codigoSeguro
     */
    public Integer getCodigoSeguro() {
        return codigoSeguro;
    }


    /**
     * Sets the codigoSeguro value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param codigoSeguro
     */
    public void setCodigoSeguro(Integer codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }


    /**
     * Gets the nomePlano value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return nomePlano
     */
    public String getNomePlano() {
        return nomePlano;
    }


    /**
     * Sets the nomePlano value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param nomePlano
     */
    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }


    /**
     * Gets the quantidadeMesVigente value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return quantidadeMesVigente
     */
    public Integer getQuantidadeMesVigente() {
        return quantidadeMesVigente;
    }


    /**
     * Sets the quantidadeMesVigente value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param quantidadeMesVigente
     */
    public void setQuantidadeMesVigente(Integer quantidadeMesVigente) {
        this.quantidadeMesVigente = quantidadeMesVigente;
    }


    /**
     * Gets the tipoPagamento value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return tipoPagamento
     */
    public String getTipoPagamento() {
        return tipoPagamento;
    }


    /**
     * Sets the tipoPagamento value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param tipoPagamento
     */
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }


    /**
     * Gets the valorCapitalSegurado value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return valorCapitalSegurado
     */
    public Double getValorCapitalSegurado() {
        return valorCapitalSegurado;
    }


    /**
     * Sets the valorCapitalSegurado value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param valorCapitalSegurado
     */
    public void setValorCapitalSegurado(Double valorCapitalSegurado) {
        this.valorCapitalSegurado = valorCapitalSegurado;
    }


    /**
     * Gets the valorLiberadoComSeguroFgts value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return valorLiberadoComSeguroFgts
     */
    public Double getValorLiberadoComSeguroFgts() {
        return valorLiberadoComSeguroFgts;
    }


    /**
     * Sets the valorLiberadoComSeguroFgts value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param valorLiberadoComSeguroFgts
     */
    public void setValorLiberadoComSeguroFgts(Double valorLiberadoComSeguroFgts) {
        this.valorLiberadoComSeguroFgts = valorLiberadoComSeguroFgts;
    }


    /**
     * Gets the valorPremio value for this PlanosContratacaoSeguroStandAlone.
     *
     * @return valorPremio
     */
    public Double getValorPremio() {
        return valorPremio;
    }


    /**
     * Sets the valorPremio value for this PlanosContratacaoSeguroStandAlone.
     *
     * @param valorPremio
     */
    public void setValorPremio(Double valorPremio) {
        this.valorPremio = valorPremio;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlanosContratacaoSeguroStandAlone)) return false;
        PlanosContratacaoSeguroStandAlone other = (PlanosContratacaoSeguroStandAlone) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
                ((this.coberturas==null && other.getCoberturas()==null) ||
                        (this.coberturas!=null &&
                                java.util.Arrays.equals(this.coberturas, other.getCoberturas()))) &&
                ((this.codigoPlano==null && other.getCodigoPlano()==null) ||
                        (this.codigoPlano!=null &&
                                this.codigoPlano.equals(other.getCodigoPlano()))) &&
                ((this.codigoSeguro==null && other.getCodigoSeguro()==null) ||
                        (this.codigoSeguro!=null &&
                                this.codigoSeguro.equals(other.getCodigoSeguro()))) &&
                ((this.nomePlano==null && other.getNomePlano()==null) ||
                        (this.nomePlano!=null &&
                                this.nomePlano.equals(other.getNomePlano()))) &&
                ((this.quantidadeMesVigente==null && other.getQuantidadeMesVigente()==null) ||
                        (this.quantidadeMesVigente!=null &&
                                this.quantidadeMesVigente.equals(other.getQuantidadeMesVigente()))) &&
                ((this.tipoPagamento==null && other.getTipoPagamento()==null) ||
                        (this.tipoPagamento!=null &&
                                this.tipoPagamento.equals(other.getTipoPagamento()))) &&
                ((this.valorCapitalSegurado==null && other.getValorCapitalSegurado()==null) ||
                        (this.valorCapitalSegurado!=null &&
                                this.valorCapitalSegurado.equals(other.getValorCapitalSegurado()))) &&
                ((this.valorLiberadoComSeguroFgts==null && other.getValorLiberadoComSeguroFgts()==null) ||
                        (this.valorLiberadoComSeguroFgts!=null &&
                                this.valorLiberadoComSeguroFgts.equals(other.getValorLiberadoComSeguroFgts()))) &&

                ((this.valorPremio==null && other.getValorPremio()==null) ||
                        (this.valorPremio!=null &&
                                this.valorPremio.equals(other.getValorPremio()))) &&

                ((this.rating==null && other.getRating()==null) ||
                        (this.rating!=null &&
                                this.rating.equals(other.getRating())))

        ;

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
        if (getCoberturas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCoberturas());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCoberturas(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodigoPlano() != null) {
            _hashCode += getCodigoPlano().hashCode();
        }
        if (getCodigoSeguro() != null) {
            _hashCode += getCodigoSeguro().hashCode();
        }
        if (getNomePlano() != null) {
            _hashCode += getNomePlano().hashCode();
        }
        if (getQuantidadeMesVigente() != null) {
            _hashCode += getQuantidadeMesVigente().hashCode();
        }
        if (getTipoPagamento() != null) {
            _hashCode += getTipoPagamento().hashCode();
        }
        if (getValorCapitalSegurado() != null) {
            _hashCode += getValorCapitalSegurado().hashCode();
        }
        if (getValorLiberadoComSeguroFgts() != null) {
            _hashCode += getValorLiberadoComSeguroFgts().hashCode();
        }
        if (getValorPremio() != null) {
            _hashCode += getValorPremio().hashCode();

        }

        if (getRating() != null) {
            _hashCode += getRating().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(PlanosContratacaoSeguroStandAlone.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosContratacaoSeguroStandAlone"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coberturas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coberturas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanoCoberturaSeguroStadAlone"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomePlano");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomePlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeMesVigente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeMesVigente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);

        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCapitalSegurado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCapitalSegurado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);

        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorLiberadoComSeguroFgts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorLiberadoComSeguroFgts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);

        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPremio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPremio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);

        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rating");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
