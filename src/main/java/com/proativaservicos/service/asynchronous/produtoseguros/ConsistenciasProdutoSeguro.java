/**
 * ConsistenciasProdutoSeguro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ConsistenciasProdutoSeguro  implements java.io.Serializable {
    private Integer codigoApolice;

    private Integer codigoConsistencia;

    private java.util.Calendar dataAlteracaoStatus;

    private String descricaoConsistencia;

    private boolean liberada;

    public ConsistenciasProdutoSeguro() {
    }

    public ConsistenciasProdutoSeguro(
           Integer codigoApolice,
           Integer codigoConsistencia,
           java.util.Calendar dataAlteracaoStatus,
           String descricaoConsistencia,
           boolean liberada) {
           this.codigoApolice = codigoApolice;
           this.codigoConsistencia = codigoConsistencia;
           this.dataAlteracaoStatus = dataAlteracaoStatus;
           this.descricaoConsistencia = descricaoConsistencia;
           this.liberada = liberada;
    }


    /**
     * Gets the codigoApolice value for this ConsistenciasProdutoSeguro.
     * 
     * @return codigoApolice
     */
    public Integer getCodigoApolice() {
        return codigoApolice;
    }


    /**
     * Sets the codigoApolice value for this ConsistenciasProdutoSeguro.
     * 
     * @param codigoApolice
     */
    public void setCodigoApolice(Integer codigoApolice) {
        this.codigoApolice = codigoApolice;
    }


    /**
     * Gets the codigoConsistencia value for this ConsistenciasProdutoSeguro.
     * 
     * @return codigoConsistencia
     */
    public Integer getCodigoConsistencia() {
        return codigoConsistencia;
    }


    /**
     * Sets the codigoConsistencia value for this ConsistenciasProdutoSeguro.
     * 
     * @param codigoConsistencia
     */
    public void setCodigoConsistencia(Integer codigoConsistencia) {
        this.codigoConsistencia = codigoConsistencia;
    }


    /**
     * Gets the dataAlteracaoStatus value for this ConsistenciasProdutoSeguro.
     * 
     * @return dataAlteracaoStatus
     */
    public java.util.Calendar getDataAlteracaoStatus() {
        return dataAlteracaoStatus;
    }


    /**
     * Sets the dataAlteracaoStatus value for this ConsistenciasProdutoSeguro.
     * 
     * @param dataAlteracaoStatus
     */
    public void setDataAlteracaoStatus(java.util.Calendar dataAlteracaoStatus) {
        this.dataAlteracaoStatus = dataAlteracaoStatus;
    }


    /**
     * Gets the descricaoConsistencia value for this ConsistenciasProdutoSeguro.
     * 
     * @return descricaoConsistencia
     */
    public String getDescricaoConsistencia() {
        return descricaoConsistencia;
    }


    /**
     * Sets the descricaoConsistencia value for this ConsistenciasProdutoSeguro.
     * 
     * @param descricaoConsistencia
     */
    public void setDescricaoConsistencia(String descricaoConsistencia) {
        this.descricaoConsistencia = descricaoConsistencia;
    }


    /**
     * Gets the liberada value for this ConsistenciasProdutoSeguro.
     * 
     * @return liberada
     */
    public boolean isLiberada() {
        return liberada;
    }


    /**
     * Sets the liberada value for this ConsistenciasProdutoSeguro.
     * 
     * @param liberada
     */
    public void setLiberada(boolean liberada) {
        this.liberada = liberada;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ConsistenciasProdutoSeguro)) return false;
        ConsistenciasProdutoSeguro other = (ConsistenciasProdutoSeguro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoApolice==null && other.getCodigoApolice()==null) || 
             (this.codigoApolice!=null &&
              this.codigoApolice.equals(other.getCodigoApolice()))) &&
            ((this.codigoConsistencia==null && other.getCodigoConsistencia()==null) || 
             (this.codigoConsistencia!=null &&
              this.codigoConsistencia.equals(other.getCodigoConsistencia()))) &&
            ((this.dataAlteracaoStatus==null && other.getDataAlteracaoStatus()==null) || 
             (this.dataAlteracaoStatus!=null &&
              this.dataAlteracaoStatus.equals(other.getDataAlteracaoStatus()))) &&
            ((this.descricaoConsistencia==null && other.getDescricaoConsistencia()==null) || 
             (this.descricaoConsistencia!=null &&
              this.descricaoConsistencia.equals(other.getDescricaoConsistencia()))) &&
            this.liberada == other.isLiberada();
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
        if (getCodigoApolice() != null) {
            _hashCode += getCodigoApolice().hashCode();
        }
        if (getCodigoConsistencia() != null) {
            _hashCode += getCodigoConsistencia().hashCode();
        }
        if (getDataAlteracaoStatus() != null) {
            _hashCode += getDataAlteracaoStatus().hashCode();
        }
        if (getDescricaoConsistencia() != null) {
            _hashCode += getDescricaoConsistencia().hashCode();
        }
        _hashCode += (isLiberada() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsistenciasProdutoSeguro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciasProdutoSeguro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoApolice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoApolice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoConsistencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoConsistencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAlteracaoStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataAlteracaoStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoConsistencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricaoConsistencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("liberada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "liberada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
