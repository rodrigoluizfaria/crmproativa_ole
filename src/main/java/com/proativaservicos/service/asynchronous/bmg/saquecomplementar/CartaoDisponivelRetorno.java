/**
 * CartaoDisponivelRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class CartaoDisponivelRetorno  implements java.io.Serializable {
    private CartaoRetorno[] cartoesRetorno;

    private FormaEnvioRetorno[] formasEnvio;

    public CartaoDisponivelRetorno() {
    }

    public CartaoDisponivelRetorno(
           CartaoRetorno[] cartoesRetorno,
           FormaEnvioRetorno[] formasEnvio) {
           this.cartoesRetorno = cartoesRetorno;
           this.formasEnvio = formasEnvio;
    }


    /**
     * Gets the cartoesRetorno value for this CartaoDisponivelRetorno.
     * 
     * @return cartoesRetorno
     */
    public CartaoRetorno[] getCartoesRetorno() {
        return cartoesRetorno;
    }


    /**
     * Sets the cartoesRetorno value for this CartaoDisponivelRetorno.
     * 
     * @param cartoesRetorno
     */
    public void setCartoesRetorno(CartaoRetorno[] cartoesRetorno) {
        this.cartoesRetorno = cartoesRetorno;
    }


    /**
     * Gets the formasEnvio value for this CartaoDisponivelRetorno.
     * 
     * @return formasEnvio
     */
    public FormaEnvioRetorno[] getFormasEnvio() {
        return formasEnvio;
    }


    /**
     * Sets the formasEnvio value for this CartaoDisponivelRetorno.
     * 
     * @param formasEnvio
     */
    public void setFormasEnvio(FormaEnvioRetorno[] formasEnvio) {
        this.formasEnvio = formasEnvio;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CartaoDisponivelRetorno)) return false;
        CartaoDisponivelRetorno other = (CartaoDisponivelRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cartoesRetorno==null && other.getCartoesRetorno()==null) || 
             (this.cartoesRetorno!=null &&
              java.util.Arrays.equals(this.cartoesRetorno, other.getCartoesRetorno()))) &&
            ((this.formasEnvio==null && other.getFormasEnvio()==null) || 
             (this.formasEnvio!=null &&
              java.util.Arrays.equals(this.formasEnvio, other.getFormasEnvio())));
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
        if (getCartoesRetorno() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCartoesRetorno());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCartoesRetorno(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFormasEnvio() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFormasEnvio());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFormasEnvio(), i);
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
        new org.apache.axis.description.TypeDesc(CartaoDisponivelRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoDisponivelRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartoesRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cartoesRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoRetorno"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formasEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formasEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno"));
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
