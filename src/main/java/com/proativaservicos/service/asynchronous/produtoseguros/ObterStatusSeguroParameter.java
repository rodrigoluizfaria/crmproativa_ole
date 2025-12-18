/**
 * ObterStatusSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ObterStatusSeguroParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private String loginConsig;

    private String numeroAde;

    private String senhaConsig;

    public ObterStatusSeguroParameter() {
    }

    public ObterStatusSeguroParameter(
           String login,
           String senha,
           String loginConsig,
           String numeroAde,
           String senhaConsig) {
        super(
            login,
            senha);
        this.loginConsig = loginConsig;
        this.numeroAde = numeroAde;
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the loginConsig value for this ObterStatusSeguroParameter.
     * 
     * @return loginConsig
     */
    public String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this ObterStatusSeguroParameter.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the numeroAde value for this ObterStatusSeguroParameter.
     * 
     * @return numeroAde
     */
    public String getNumeroAde() {
        return numeroAde;
    }


    /**
     * Sets the numeroAde value for this ObterStatusSeguroParameter.
     * 
     * @param numeroAde
     */
    public void setNumeroAde(String numeroAde) {
        this.numeroAde = numeroAde;
    }


    /**
     * Gets the senhaConsig value for this ObterStatusSeguroParameter.
     * 
     * @return senhaConsig
     */
    public String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this ObterStatusSeguroParameter.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ObterStatusSeguroParameter)) return false;
        ObterStatusSeguroParameter other = (ObterStatusSeguroParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.numeroAde==null && other.getNumeroAde()==null) || 
             (this.numeroAde!=null &&
              this.numeroAde.equals(other.getNumeroAde()))) &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig())));
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
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getNumeroAde() != null) {
            _hashCode += getNumeroAde().hashCode();
        }
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObterStatusSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroAde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senhaConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senhaConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
