/**
 * ConsultaUnidadePagadoraRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class ConsultaUnidadePagadoraRetorno  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[] unidadesPagadoras;

    public ConsultaUnidadePagadoraRetorno() {
    }

    public ConsultaUnidadePagadoraRetorno(
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[] unidadesPagadoras) {
           this.unidadesPagadoras = unidadesPagadoras;
    }


    /**
     * Gets the unidadesPagadoras value for this ConsultaUnidadePagadoraRetorno.
     * 
     * @return unidadesPagadoras
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[] getUnidadesPagadoras() {
        return unidadesPagadoras;
    }


    /**
     * Sets the unidadesPagadoras value for this ConsultaUnidadePagadoraRetorno.
     * 
     * @param unidadesPagadoras
     */
    public void setUnidadesPagadoras(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[] unidadesPagadoras) {
        this.unidadesPagadoras = unidadesPagadoras;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaUnidadePagadoraRetorno)) return false;
        ConsultaUnidadePagadoraRetorno other = (ConsultaUnidadePagadoraRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.unidadesPagadoras==null && other.getUnidadesPagadoras()==null) || 
             (this.unidadesPagadoras!=null &&
              java.util.Arrays.equals(this.unidadesPagadoras, other.getUnidadesPagadoras())));
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
        if (getUnidadesPagadoras() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUnidadesPagadoras());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUnidadesPagadoras(), i);
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
        new org.apache.axis.description.TypeDesc(ConsultaUnidadePagadoraRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsultaUnidadePagadoraRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadesPagadoras");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadesPagadoras"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "UnidadePagadora"));
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
