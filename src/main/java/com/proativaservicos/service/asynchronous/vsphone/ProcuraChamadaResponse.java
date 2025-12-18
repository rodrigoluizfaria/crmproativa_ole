/**
 * ProcuraChamadaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class ProcuraChamadaResponse  extends com.proativaservicos.service.asynchronous.vsphone.CustomResponse  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.vsphone.DetalhesChamada[] detalhesChamadas;

    public ProcuraChamadaResponse() {
    }

    public ProcuraChamadaResponse(
           com.proativaservicos.service.asynchronous.vsphone.Excecao[] excecoes,
           com.proativaservicos.service.asynchronous.vsphone.DetalhesChamada[] detalhesChamadas) {
        super(
            excecoes);
        this.detalhesChamadas = detalhesChamadas;
    }


    /**
     * Gets the detalhesChamadas value for this ProcuraChamadaResponse.
     * 
     * @return detalhesChamadas
     */
    public com.proativaservicos.service.asynchronous.vsphone.DetalhesChamada[] getDetalhesChamadas() {
        return detalhesChamadas;
    }


    /**
     * Sets the detalhesChamadas value for this ProcuraChamadaResponse.
     * 
     * @param detalhesChamadas
     */
    public void setDetalhesChamadas(com.proativaservicos.service.asynchronous.vsphone.DetalhesChamada[] detalhesChamadas) {
        this.detalhesChamadas = detalhesChamadas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcuraChamadaResponse)) return false;
        ProcuraChamadaResponse other = (ProcuraChamadaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.detalhesChamadas==null && other.getDetalhesChamadas()==null) || 
             (this.detalhesChamadas!=null &&
              java.util.Arrays.equals(this.detalhesChamadas, other.getDetalhesChamadas())));
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
        if (getDetalhesChamadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalhesChamadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalhesChamadas(), i);
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
        new org.apache.axis.description.TypeDesc(ProcuraChamadaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalhesChamadas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "detalhesChamadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "DetalhesChamada"));
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
