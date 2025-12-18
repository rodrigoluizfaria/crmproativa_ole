/**
 * InclusaoTelefonesComplementaresParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class InclusaoTelefonesComplementaresParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private Integer codigoApolice;

    private String loginConsig;

    private String senhaConsig;

    private com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone1;

    private com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone2;

    private com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone3;

    private com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone4;

    public InclusaoTelefonesComplementaresParameter() {
    }

    public InclusaoTelefonesComplementaresParameter(
           String login,
           String senha,
           Integer codigoApolice,
           String loginConsig,
           String senhaConsig,
           com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone1,
           com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone2,
           com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone3,
           com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone4) {
        super(
            login,
            senha);
        this.codigoApolice = codigoApolice;
        this.loginConsig = loginConsig;
        this.senhaConsig = senhaConsig;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.telefone3 = telefone3;
        this.telefone4 = telefone4;
    }


    /**
     * Gets the codigoApolice value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @return codigoApolice
     */
    public Integer getCodigoApolice() {
        return codigoApolice;
    }


    /**
     * Sets the codigoApolice value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @param codigoApolice
     */
    public void setCodigoApolice(Integer codigoApolice) {
        this.codigoApolice = codigoApolice;
    }


    /**
     * Gets the loginConsig value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @return loginConsig
     */
    public String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the senhaConsig value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @return senhaConsig
     */
    public String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the telefone1 value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @return telefone1
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter getTelefone1() {
        return telefone1;
    }


    /**
     * Sets the telefone1 value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @param telefone1
     */
    public void setTelefone1(com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone1) {
        this.telefone1 = telefone1;
    }


    /**
     * Gets the telefone2 value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @return telefone2
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter getTelefone2() {
        return telefone2;
    }


    /**
     * Sets the telefone2 value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @param telefone2
     */
    public void setTelefone2(com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone2) {
        this.telefone2 = telefone2;
    }


    /**
     * Gets the telefone3 value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @return telefone3
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter getTelefone3() {
        return telefone3;
    }


    /**
     * Sets the telefone3 value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @param telefone3
     */
    public void setTelefone3(com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone3) {
        this.telefone3 = telefone3;
    }


    /**
     * Gets the telefone4 value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @return telefone4
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter getTelefone4() {
        return telefone4;
    }


    /**
     * Sets the telefone4 value for this InclusaoTelefonesComplementaresParameter.
     * 
     * @param telefone4
     */
    public void setTelefone4(com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter telefone4) {
        this.telefone4 = telefone4;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof InclusaoTelefonesComplementaresParameter)) return false;
        InclusaoTelefonesComplementaresParameter other = (InclusaoTelefonesComplementaresParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoApolice==null && other.getCodigoApolice()==null) || 
             (this.codigoApolice!=null &&
              this.codigoApolice.equals(other.getCodigoApolice()))) &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig()))) &&
            ((this.telefone1==null && other.getTelefone1()==null) || 
             (this.telefone1!=null &&
              this.telefone1.equals(other.getTelefone1()))) &&
            ((this.telefone2==null && other.getTelefone2()==null) || 
             (this.telefone2!=null &&
              this.telefone2.equals(other.getTelefone2()))) &&
            ((this.telefone3==null && other.getTelefone3()==null) || 
             (this.telefone3!=null &&
              this.telefone3.equals(other.getTelefone3()))) &&
            ((this.telefone4==null && other.getTelefone4()==null) || 
             (this.telefone4!=null &&
              this.telefone4.equals(other.getTelefone4())));
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
        if (getCodigoApolice() != null) {
            _hashCode += getCodigoApolice().hashCode();
        }
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        if (getTelefone1() != null) {
            _hashCode += getTelefone1().hashCode();
        }
        if (getTelefone2() != null) {
            _hashCode += getTelefone2().hashCode();
        }
        if (getTelefone3() != null) {
            _hashCode += getTelefone3().hashCode();
        }
        if (getTelefone4() != null) {
            _hashCode += getTelefone4().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InclusaoTelefonesComplementaresParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "InclusaoTelefonesComplementaresParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoApolice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoApolice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senhaConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senhaConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefone1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefone1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefone2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefone2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefone3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefone3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefone4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefone4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
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
