/**
 * DadosLimiteDeSaque.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class DadosLimiteDeSaque  implements java.io.Serializable {
    private double disponivelCompra;

    private double limiteCalculado;

    private double limiteCalculadoCompras;

    private double limiteDeCredito;

    public DadosLimiteDeSaque() {
    }

    public DadosLimiteDeSaque(
           double disponivelCompra,
           double limiteCalculado,
           double limiteCalculadoCompras,
           double limiteDeCredito) {
           this.disponivelCompra = disponivelCompra;
           this.limiteCalculado = limiteCalculado;
           this.limiteCalculadoCompras = limiteCalculadoCompras;
           this.limiteDeCredito = limiteDeCredito;
    }


    /**
     * Gets the disponivelCompra value for this DadosLimiteDeSaque.
     * 
     * @return disponivelCompra
     */
    public double getDisponivelCompra() {
        return disponivelCompra;
    }


    /**
     * Sets the disponivelCompra value for this DadosLimiteDeSaque.
     * 
     * @param disponivelCompra
     */
    public void setDisponivelCompra(double disponivelCompra) {
        this.disponivelCompra = disponivelCompra;
    }


    /**
     * Gets the limiteCalculado value for this DadosLimiteDeSaque.
     * 
     * @return limiteCalculado
     */
    public double getLimiteCalculado() {
        return limiteCalculado;
    }


    /**
     * Sets the limiteCalculado value for this DadosLimiteDeSaque.
     * 
     * @param limiteCalculado
     */
    public void setLimiteCalculado(double limiteCalculado) {
        this.limiteCalculado = limiteCalculado;
    }


    /**
     * Gets the limiteCalculadoCompras value for this DadosLimiteDeSaque.
     * 
     * @return limiteCalculadoCompras
     */
    public double getLimiteCalculadoCompras() {
        return limiteCalculadoCompras;
    }


    /**
     * Sets the limiteCalculadoCompras value for this DadosLimiteDeSaque.
     * 
     * @param limiteCalculadoCompras
     */
    public void setLimiteCalculadoCompras(double limiteCalculadoCompras) {
        this.limiteCalculadoCompras = limiteCalculadoCompras;
    }


    /**
     * Gets the limiteDeCredito value for this DadosLimiteDeSaque.
     * 
     * @return limiteDeCredito
     */
    public double getLimiteDeCredito() {
        return limiteDeCredito;
    }


    /**
     * Sets the limiteDeCredito value for this DadosLimiteDeSaque.
     * 
     * @param limiteDeCredito
     */
    public void setLimiteDeCredito(double limiteDeCredito) {
        this.limiteDeCredito = limiteDeCredito;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DadosLimiteDeSaque)) return false;
        DadosLimiteDeSaque other = (DadosLimiteDeSaque) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.disponivelCompra == other.getDisponivelCompra() &&
            this.limiteCalculado == other.getLimiteCalculado() &&
            this.limiteCalculadoCompras == other.getLimiteCalculadoCompras() &&
            this.limiteDeCredito == other.getLimiteDeCredito();
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
        _hashCode += new Double(getDisponivelCompra()).hashCode();
        _hashCode += new Double(getLimiteCalculado()).hashCode();
        _hashCode += new Double(getLimiteCalculadoCompras()).hashCode();
        _hashCode += new Double(getLimiteDeCredito()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DadosLimiteDeSaque.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosLimiteDeSaque"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disponivelCompra");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disponivelCompra"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limiteCalculado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteCalculado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limiteCalculadoCompras");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteCalculadoCompras"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limiteDeCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteDeCredito"));
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
