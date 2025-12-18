/**
 * CustomResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public abstract class CustomResponse  extends com.proativaservicos.service.asynchronous.vsphone.CustomVSPhoneClass  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes;

    public CustomResponse() {
    }

    public CustomResponse(
           com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes) {
        this.excecoes = excecoes;
    }


    /**
     * Gets the excecoes value for this CustomResponse.
     * 
     * @return excecoes
     */
    public com.proativaservicos.service.asynchronous.vsphone.Excecao[] getExcecoes() {
        return excecoes;
    }


    /**
     * Sets the excecoes value for this CustomResponse.
     * 
     * @param excecoes
     */
    public void setExcecoes(com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes) {
        this.excecoes = excecoes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomResponse)) return false;
        CustomResponse other = (CustomResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.excecoes==null && other.getExcecoes()==null) || 
             (this.excecoes!=null &&
              java.util.Arrays.equals(this.excecoes, other.getExcecoes())));
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
        if (getExcecoes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExcecoes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExcecoes(), i);
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
        new org.apache.axis.description.TypeDesc(CustomResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://comum.webservice.vsphone4j.virtualsistemas", "CustomResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecoes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://comum.webservice.vsphone4j.virtualsistemas", "excecoes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Excecao"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "item"));
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
