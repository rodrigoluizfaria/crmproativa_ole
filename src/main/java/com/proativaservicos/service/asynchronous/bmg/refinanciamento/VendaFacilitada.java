/**
 * VendaFacilitada.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class VendaFacilitada  implements java.io.Serializable {
    private int conta;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.Seguro[] seguros;

    private java.lang.String servico;

    private double valorMargem;

    private double valorSaque;

    public VendaFacilitada() {
    }

    public VendaFacilitada(
           int conta,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.Seguro[] seguros,
           java.lang.String servico,
           double valorMargem,
           double valorSaque) {
           this.conta = conta;
           this.seguros = seguros;
           this.servico = servico;
           this.valorMargem = valorMargem;
           this.valorSaque = valorSaque;
    }


    /**
     * Gets the conta value for this VendaFacilitada.
     * 
     * @return conta
     */
    public int getConta() {
        return conta;
    }


    /**
     * Sets the conta value for this VendaFacilitada.
     * 
     * @param conta
     */
    public void setConta(int conta) {
        this.conta = conta;
    }


    /**
     * Gets the seguros value for this VendaFacilitada.
     * 
     * @return seguros
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.Seguro[] getSeguros() {
        return seguros;
    }


    /**
     * Sets the seguros value for this VendaFacilitada.
     * 
     * @param seguros
     */
    public void setSeguros(com.proativaservicos.service.asynchronous.bmg.refinanciamento.Seguro[] seguros) {
        this.seguros = seguros;
    }


    /**
     * Gets the servico value for this VendaFacilitada.
     * 
     * @return servico
     */
    public java.lang.String getServico() {
        return servico;
    }


    /**
     * Sets the servico value for this VendaFacilitada.
     * 
     * @param servico
     */
    public void setServico(java.lang.String servico) {
        this.servico = servico;
    }


    /**
     * Gets the valorMargem value for this VendaFacilitada.
     * 
     * @return valorMargem
     */
    public double getValorMargem() {
        return valorMargem;
    }


    /**
     * Sets the valorMargem value for this VendaFacilitada.
     * 
     * @param valorMargem
     */
    public void setValorMargem(double valorMargem) {
        this.valorMargem = valorMargem;
    }


    /**
     * Gets the valorSaque value for this VendaFacilitada.
     * 
     * @return valorSaque
     */
    public double getValorSaque() {
        return valorSaque;
    }


    /**
     * Sets the valorSaque value for this VendaFacilitada.
     * 
     * @param valorSaque
     */
    public void setValorSaque(double valorSaque) {
        this.valorSaque = valorSaque;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VendaFacilitada)) return false;
        VendaFacilitada other = (VendaFacilitada) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.conta == other.getConta() &&
            ((this.seguros==null && other.getSeguros()==null) || 
             (this.seguros!=null &&
              java.util.Arrays.equals(this.seguros, other.getSeguros()))) &&
            ((this.servico==null && other.getServico()==null) || 
             (this.servico!=null &&
              this.servico.equals(other.getServico()))) &&
            this.valorMargem == other.getValorMargem() &&
            this.valorSaque == other.getValorSaque();
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
        _hashCode += getConta();
        if (getSeguros() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeguros());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSeguros(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getServico() != null) {
            _hashCode += getServico().hashCode();
        }
        _hashCode += new Double(getValorMargem()).hashCode();
        _hashCode += new Double(getValorSaque()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VendaFacilitada.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "VendaFacilitada"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seguros");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seguros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguro"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorMargem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorMargem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaque"));
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
