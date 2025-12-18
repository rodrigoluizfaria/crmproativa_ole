/**
 * ProcuraChamadaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class ProcuraChamadaRequest  extends com.proativaservicos.service.asynchronous.vsphone.CustomRequest  implements java.io.Serializable {
    private java.util.Calendar fim;

    private java.util.Calendar inicio;

    private java.lang.String numeroDestino;

    private java.lang.String numeroOrigem;

    private boolean procuraParcial;

    public ProcuraChamadaRequest() {
    }

    public ProcuraChamadaRequest(
           java.util.Calendar fim,
           java.util.Calendar inicio,
           java.lang.String numeroDestino,
           java.lang.String numeroOrigem,
           boolean procuraParcial) {
        this.fim = fim;
        this.inicio = inicio;
        this.numeroDestino = numeroDestino;
        this.numeroOrigem = numeroOrigem;
        this.procuraParcial = procuraParcial;
    }


    /**
     * Gets the fim value for this ProcuraChamadaRequest.
     * 
     * @return fim
     */
    public java.util.Calendar getFim() {
        return fim;
    }


    /**
     * Sets the fim value for this ProcuraChamadaRequest.
     * 
     * @param fim
     */
    public void setFim(java.util.Calendar fim) {
        this.fim = fim;
    }


    /**
     * Gets the inicio value for this ProcuraChamadaRequest.
     * 
     * @return inicio
     */
    public java.util.Calendar getInicio() {
        return inicio;
    }


    /**
     * Sets the inicio value for this ProcuraChamadaRequest.
     * 
     * @param inicio
     */
    public void setInicio(java.util.Calendar inicio) {
        this.inicio = inicio;
    }


    /**
     * Gets the numeroDestino value for this ProcuraChamadaRequest.
     * 
     * @return numeroDestino
     */
    public java.lang.String getNumeroDestino() {
        return numeroDestino;
    }


    /**
     * Sets the numeroDestino value for this ProcuraChamadaRequest.
     * 
     * @param numeroDestino
     */
    public void setNumeroDestino(java.lang.String numeroDestino) {
        this.numeroDestino = numeroDestino;
    }


    /**
     * Gets the numeroOrigem value for this ProcuraChamadaRequest.
     * 
     * @return numeroOrigem
     */
    public java.lang.String getNumeroOrigem() {
        return numeroOrigem;
    }


    /**
     * Sets the numeroOrigem value for this ProcuraChamadaRequest.
     * 
     * @param numeroOrigem
     */
    public void setNumeroOrigem(java.lang.String numeroOrigem) {
        this.numeroOrigem = numeroOrigem;
    }


    /**
     * Gets the procuraParcial value for this ProcuraChamadaRequest.
     * 
     * @return procuraParcial
     */
    public boolean isProcuraParcial() {
        return procuraParcial;
    }


    /**
     * Sets the procuraParcial value for this ProcuraChamadaRequest.
     * 
     * @param procuraParcial
     */
    public void setProcuraParcial(boolean procuraParcial) {
        this.procuraParcial = procuraParcial;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcuraChamadaRequest)) return false;
        ProcuraChamadaRequest other = (ProcuraChamadaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.fim==null && other.getFim()==null) || 
             (this.fim!=null &&
              this.fim.equals(other.getFim()))) &&
            ((this.inicio==null && other.getInicio()==null) || 
             (this.inicio!=null &&
              this.inicio.equals(other.getInicio()))) &&
            ((this.numeroDestino==null && other.getNumeroDestino()==null) || 
             (this.numeroDestino!=null &&
              this.numeroDestino.equals(other.getNumeroDestino()))) &&
            ((this.numeroOrigem==null && other.getNumeroOrigem()==null) || 
             (this.numeroOrigem!=null &&
              this.numeroOrigem.equals(other.getNumeroOrigem()))) &&
            this.procuraParcial == other.isProcuraParcial();
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
        if (getFim() != null) {
            _hashCode += getFim().hashCode();
        }
        if (getInicio() != null) {
            _hashCode += getInicio().hashCode();
        }
        if (getNumeroDestino() != null) {
            _hashCode += getNumeroDestino().hashCode();
        }
        if (getNumeroOrigem() != null) {
            _hashCode += getNumeroOrigem().hashCode();
        }
        _hashCode += (isProcuraParcial() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcuraChamadaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fim");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "fim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "inicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "numeroDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOrigem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "numeroOrigem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procuraParcial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "procuraParcial"));
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
