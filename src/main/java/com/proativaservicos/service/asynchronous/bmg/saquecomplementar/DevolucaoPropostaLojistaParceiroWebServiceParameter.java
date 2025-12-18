/**
 * DevolucaoPropostaLojistaParceiroWebServiceParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class DevolucaoPropostaLojistaParceiroWebServiceParameter  extends WebServiceBaseParameter  implements java.io.Serializable {
    private Boolean indicaDevolucaoParcial;

    private Integer numeroExterno;

    private Double valorDevolucao;

    public DevolucaoPropostaLojistaParceiroWebServiceParameter() {
    }

    public DevolucaoPropostaLojistaParceiroWebServiceParameter(
           String login,
           String senha,
           String loginConsig,
           Boolean indicaDevolucaoParcial,
           Integer numeroExterno,
           Double valorDevolucao) {
        super(
            login,
            senha,
            loginConsig);
        this.indicaDevolucaoParcial = indicaDevolucaoParcial;
        this.numeroExterno = numeroExterno;
        this.valorDevolucao = valorDevolucao;
    }


    /**
     * Gets the indicaDevolucaoParcial value for this DevolucaoPropostaLojistaParceiroWebServiceParameter.
     * 
     * @return indicaDevolucaoParcial
     */
    public Boolean getIndicaDevolucaoParcial() {
        return indicaDevolucaoParcial;
    }


    /**
     * Sets the indicaDevolucaoParcial value for this DevolucaoPropostaLojistaParceiroWebServiceParameter.
     * 
     * @param indicaDevolucaoParcial
     */
    public void setIndicaDevolucaoParcial(Boolean indicaDevolucaoParcial) {
        this.indicaDevolucaoParcial = indicaDevolucaoParcial;
    }


    /**
     * Gets the numeroExterno value for this DevolucaoPropostaLojistaParceiroWebServiceParameter.
     * 
     * @return numeroExterno
     */
    public Integer getNumeroExterno() {
        return numeroExterno;
    }


    /**
     * Sets the numeroExterno value for this DevolucaoPropostaLojistaParceiroWebServiceParameter.
     * 
     * @param numeroExterno
     */
    public void setNumeroExterno(Integer numeroExterno) {
        this.numeroExterno = numeroExterno;
    }


    /**
     * Gets the valorDevolucao value for this DevolucaoPropostaLojistaParceiroWebServiceParameter.
     * 
     * @return valorDevolucao
     */
    public Double getValorDevolucao() {
        return valorDevolucao;
    }


    /**
     * Sets the valorDevolucao value for this DevolucaoPropostaLojistaParceiroWebServiceParameter.
     * 
     * @param valorDevolucao
     */
    public void setValorDevolucao(Double valorDevolucao) {
        this.valorDevolucao = valorDevolucao;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DevolucaoPropostaLojistaParceiroWebServiceParameter)) return false;
        DevolucaoPropostaLojistaParceiroWebServiceParameter other = (DevolucaoPropostaLojistaParceiroWebServiceParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.indicaDevolucaoParcial==null && other.getIndicaDevolucaoParcial()==null) || 
             (this.indicaDevolucaoParcial!=null &&
              this.indicaDevolucaoParcial.equals(other.getIndicaDevolucaoParcial()))) &&
            ((this.numeroExterno==null && other.getNumeroExterno()==null) || 
             (this.numeroExterno!=null &&
              this.numeroExterno.equals(other.getNumeroExterno()))) &&
            ((this.valorDevolucao==null && other.getValorDevolucao()==null) || 
             (this.valorDevolucao!=null &&
              this.valorDevolucao.equals(other.getValorDevolucao())));
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
        if (getIndicaDevolucaoParcial() != null) {
            _hashCode += getIndicaDevolucaoParcial().hashCode();
        }
        if (getNumeroExterno() != null) {
            _hashCode += getNumeroExterno().hashCode();
        }
        if (getValorDevolucao() != null) {
            _hashCode += getValorDevolucao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DevolucaoPropostaLojistaParceiroWebServiceParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DevolucaoPropostaLojistaParceiroWebServiceParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicaDevolucaoParcial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicaDevolucaoParcial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroExterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroExterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorDevolucao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorDevolucao"));
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
