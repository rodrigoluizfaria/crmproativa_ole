/**
 * ProdutoSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ProdutoSeguroParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.produtoseguros.MapProdutoSeguro[] map;

    public ProdutoSeguroParameter() {
    }

    public ProdutoSeguroParameter(
           String login,
           String senha,
           com.proativaservicos.service.asynchronous.produtoseguros.MapProdutoSeguro[] map) {
        super(
            login,
            senha);
        this.map = map;
    }


    /**
     * Gets the map value for this ProdutoSeguroParameter.
     * 
     * @return map
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.MapProdutoSeguro[] getMap() {
        return map;
    }


    /**
     * Sets the map value for this ProdutoSeguroParameter.
     * 
     * @param map
     */
    public void setMap(com.proativaservicos.service.asynchronous.produtoseguros.MapProdutoSeguro[] map) {
        this.map = map;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ProdutoSeguroParameter)) return false;
        ProdutoSeguroParameter other = (ProdutoSeguroParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.map==null && other.getMap()==null) || 
             (this.map!=null &&
              java.util.Arrays.equals(this.map, other.getMap())));
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
        if (getMap() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMap());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getMap(), i);
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
        new org.apache.axis.description.TypeDesc(ProdutoSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ProdutoSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("map");
        elemField.setXmlName(new javax.xml.namespace.QName("", "map"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MapProdutoSeguro"));
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
