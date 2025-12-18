/**
 * IdentidadeParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class IdentidadeParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.AbstractWebServicesParameter  implements java.io.Serializable {
    private java.util.Calendar dataEmissao;

    private String emissor;

    private String numero;

    private String tipo;

    private String uf;

    public IdentidadeParameter() {
    }

    public IdentidadeParameter(
           java.util.Calendar dataEmissao,
           String emissor,
           String numero,
           String tipo,
           String uf) {
        this.dataEmissao = dataEmissao;
        this.emissor = emissor;
        this.numero = numero;
        this.tipo = tipo;
        this.uf = uf;
    }


    /**
     * Gets the dataEmissao value for this IdentidadeParameter.
     * 
     * @return dataEmissao
     */
    public java.util.Calendar getDataEmissao() {
        return dataEmissao;
    }


    /**
     * Sets the dataEmissao value for this IdentidadeParameter.
     * 
     * @param dataEmissao
     */
    public void setDataEmissao(java.util.Calendar dataEmissao) {
        this.dataEmissao = dataEmissao;
    }


    /**
     * Gets the emissor value for this IdentidadeParameter.
     * 
     * @return emissor
     */
    public String getEmissor() {
        return emissor;
    }


    /**
     * Sets the emissor value for this IdentidadeParameter.
     * 
     * @param emissor
     */
    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }


    /**
     * Gets the numero value for this IdentidadeParameter.
     * 
     * @return numero
     */
    public String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this IdentidadeParameter.
     * 
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }


    /**
     * Gets the tipo value for this IdentidadeParameter.
     * 
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this IdentidadeParameter.
     * 
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the uf value for this IdentidadeParameter.
     * 
     * @return uf
     */
    public String getUf() {
        return uf;
    }


    /**
     * Sets the uf value for this IdentidadeParameter.
     * 
     * @param uf
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof IdentidadeParameter)) return false;
        IdentidadeParameter other = (IdentidadeParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.dataEmissao==null && other.getDataEmissao()==null) || 
             (this.dataEmissao!=null &&
              this.dataEmissao.equals(other.getDataEmissao()))) &&
            ((this.emissor==null && other.getEmissor()==null) || 
             (this.emissor!=null &&
              this.emissor.equals(other.getEmissor()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.uf==null && other.getUf()==null) || 
             (this.uf!=null &&
              this.uf.equals(other.getUf())));
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
        if (getDataEmissao() != null) {
            _hashCode += getDataEmissao().hashCode();
        }
        if (getEmissor() != null) {
            _hashCode += getEmissor().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getUf() != null) {
            _hashCode += getUf().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IdentidadeParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "IdentidadeParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataEmissao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataEmissao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emissor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emissor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uf"));
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
