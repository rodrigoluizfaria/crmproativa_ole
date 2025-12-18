/**
 * ScriptAdesaoReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ScriptAdesaoReturn  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private boolean executadoComSucessso;

    private String mensagemDeErro;

    private String script;

    public ScriptAdesaoReturn() {
    }

    public ScriptAdesaoReturn(
           String login,
           String senha,
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           boolean executadoComSucessso,
           String mensagemDeErro,
           String script) {
        super(
            login,
            senha);
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
        this.excecaoGenerica = excecaoGenerica;
        this.executadoComSucessso = executadoComSucessso;
        this.mensagemDeErro = mensagemDeErro;
        this.script = script;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this ScriptAdesaoReturn.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this ScriptAdesaoReturn.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this ScriptAdesaoReturn.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this ScriptAdesaoReturn.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the executadoComSucessso value for this ScriptAdesaoReturn.
     * 
     * @return executadoComSucessso
     */
    public boolean isExecutadoComSucessso() {
        return executadoComSucessso;
    }


    /**
     * Sets the executadoComSucessso value for this ScriptAdesaoReturn.
     * 
     * @param executadoComSucessso
     */
    public void setExecutadoComSucessso(boolean executadoComSucessso) {
        this.executadoComSucessso = executadoComSucessso;
    }


    /**
     * Gets the mensagemDeErro value for this ScriptAdesaoReturn.
     * 
     * @return mensagemDeErro
     */
    public String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this ScriptAdesaoReturn.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the script value for this ScriptAdesaoReturn.
     * 
     * @return script
     */
    public String getScript() {
        return script;
    }


    /**
     * Sets the script value for this ScriptAdesaoReturn.
     * 
     * @param script
     */
    public void setScript(String script) {
        this.script = script;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ScriptAdesaoReturn)) return false;
        ScriptAdesaoReturn other = (ScriptAdesaoReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.excecaoDeRegraDeNegocio == other.isExcecaoDeRegraDeNegocio() &&
            this.excecaoGenerica == other.isExcecaoGenerica() &&
            this.executadoComSucessso == other.isExecutadoComSucessso() &&
            ((this.mensagemDeErro==null && other.getMensagemDeErro()==null) || 
             (this.mensagemDeErro!=null &&
              this.mensagemDeErro.equals(other.getMensagemDeErro()))) &&
            ((this.script==null && other.getScript()==null) || 
             (this.script!=null &&
              this.script.equals(other.getScript())));
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
        _hashCode += (isExcecaoDeRegraDeNegocio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcecaoGenerica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExecutadoComSucessso() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagemDeErro() != null) {
            _hashCode += getMensagemDeErro().hashCode();
        }
        if (getScript() != null) {
            _hashCode += getScript().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ScriptAdesaoReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptAdesaoReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecaoDeRegraDeNegocio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecaoDeRegraDeNegocio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecaoGenerica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecaoGenerica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executadoComSucessso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "executadoComSucessso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagemDeErro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemDeErro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("script");
        elemField.setXmlName(new javax.xml.namespace.QName("", "script"));
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
