/**
 * ConsigBusinessException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public abstract class ConsigBusinessException  implements java.io.Serializable {
    private boolean bypassable;

    private String key;

    private Object[] values;

    public ConsigBusinessException() {
    }

    public ConsigBusinessException(
           boolean bypassable,
           String key,
           Object[] values) {
           this.bypassable = bypassable;
           this.key = key;
           this.values = values;
    }


    /**
     * Gets the bypassable value for this ConsigBusinessException.
     * 
     * @return bypassable
     */
    public boolean isBypassable() {
        return bypassable;
    }


    /**
     * Sets the bypassable value for this ConsigBusinessException.
     * 
     * @param bypassable
     */
    public void setBypassable(boolean bypassable) {
        this.bypassable = bypassable;
    }


    /**
     * Gets the key value for this ConsigBusinessException.
     * 
     * @return key
     */
    public String getKey() {
        return key;
    }


    /**
     * Sets the key value for this ConsigBusinessException.
     * 
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * Gets the values value for this ConsigBusinessException.
     * 
     * @return values
     */
    public Object[] getValues() {
        return values;
    }


    /**
     * Sets the values value for this ConsigBusinessException.
     * 
     * @param values
     */
    public void setValues(Object[] values) {
        this.values = values;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ConsigBusinessException)) return false;
        ConsigBusinessException other = (ConsigBusinessException) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.bypassable == other.isBypassable() &&
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.values==null && other.getValues()==null) || 
             (this.values!=null &&
              java.util.Arrays.equals(this.values, other.getValues())));
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
        _hashCode += (isBypassable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValues());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getValues(), i);
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
        new org.apache.axis.description.TypeDesc(ConsigBusinessException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exception.common.consig.bmg.com", "ConsigBusinessException"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bypassable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bypassable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("", "key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("values");
        elemField.setXmlName(new javax.xml.namespace.QName("", "values"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
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
