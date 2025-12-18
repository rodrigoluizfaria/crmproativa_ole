/**
 * ConsultaContratoRefinanciamentoRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.consultaContrato;

public class ConsultaContratoRefinanciamentoRetorno  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.bmg.consultaContrato.ContratoRefin[] contratos;

    public ConsultaContratoRefinanciamentoRetorno() {
    }

    public ConsultaContratoRefinanciamentoRetorno(
           com.proativaservicos.service.asynchronous.bmg.consultaContrato.ContratoRefin[] contratos) {
           this.contratos = contratos;
    }


    /**
     * Gets the contratos value for this ConsultaContratoRefinanciamentoRetorno.
     * 
     * @return contratos
     */
    public com.proativaservicos.service.asynchronous.bmg.consultaContrato.ContratoRefin[] getContratos() {
        return contratos;
    }


    /**
     * Sets the contratos value for this ConsultaContratoRefinanciamentoRetorno.
     * 
     * @param contratos
     */
    public void setContratos(com.proativaservicos.service.asynchronous.bmg.consultaContrato.ContratoRefin[] contratos) {
        this.contratos = contratos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaContratoRefinanciamentoRetorno)) return false;
        ConsultaContratoRefinanciamentoRetorno other = (ConsultaContratoRefinanciamentoRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contratos==null && other.getContratos()==null) || 
             (this.contratos!=null &&
              java.util.Arrays.equals(this.contratos, other.getContratos())));
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
        if (getContratos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContratos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContratos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaContratoRefinanciamentoRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsultaContratoRefinanciamentoRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contratos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contratos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoRefin"));
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
