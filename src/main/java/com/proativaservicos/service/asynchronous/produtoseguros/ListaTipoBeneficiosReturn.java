/**
 * ListaTipoBeneficiosReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ListaTipoBeneficiosReturn  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceBean  implements java.io.Serializable {
    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private String mensagemDeErro;

    private com.proativaservicos.service.asynchronous.produtoseguros.DadosCadastroBasicoTipoBeneficio[] tipoBeneficios;

    public ListaTipoBeneficiosReturn() {
    }

    public ListaTipoBeneficiosReturn(
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           String mensagemDeErro,
           com.proativaservicos.service.asynchronous.produtoseguros.DadosCadastroBasicoTipoBeneficio[] tipoBeneficios) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
        this.excecaoGenerica = excecaoGenerica;
        this.mensagemDeErro = mensagemDeErro;
        this.tipoBeneficios = tipoBeneficios;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this ListaTipoBeneficiosReturn.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this ListaTipoBeneficiosReturn.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this ListaTipoBeneficiosReturn.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this ListaTipoBeneficiosReturn.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the mensagemDeErro value for this ListaTipoBeneficiosReturn.
     * 
     * @return mensagemDeErro
     */
    public String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this ListaTipoBeneficiosReturn.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the tipoBeneficios value for this ListaTipoBeneficiosReturn.
     * 
     * @return tipoBeneficios
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.DadosCadastroBasicoTipoBeneficio[] getTipoBeneficios() {
        return tipoBeneficios;
    }


    /**
     * Sets the tipoBeneficios value for this ListaTipoBeneficiosReturn.
     * 
     * @param tipoBeneficios
     */
    public void setTipoBeneficios(com.proativaservicos.service.asynchronous.produtoseguros.DadosCadastroBasicoTipoBeneficio[] tipoBeneficios) {
        this.tipoBeneficios = tipoBeneficios;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ListaTipoBeneficiosReturn)) return false;
        ListaTipoBeneficiosReturn other = (ListaTipoBeneficiosReturn) obj;
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
            ((this.mensagemDeErro==null && other.getMensagemDeErro()==null) || 
             (this.mensagemDeErro!=null &&
              this.mensagemDeErro.equals(other.getMensagemDeErro()))) &&
            ((this.tipoBeneficios==null && other.getTipoBeneficios()==null) || 
             (this.tipoBeneficios!=null &&
              java.util.Arrays.equals(this.tipoBeneficios, other.getTipoBeneficios())));
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
        if (getMensagemDeErro() != null) {
            _hashCode += getMensagemDeErro().hashCode();
        }
        if (getTipoBeneficios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipoBeneficios());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getTipoBeneficios(), i);
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
        new org.apache.axis.description.TypeDesc(ListaTipoBeneficiosReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaTipoBeneficiosReturn"));
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
        elemField.setFieldName("mensagemDeErro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemDeErro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoBeneficios");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoBeneficios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCadastroBasicoTipoBeneficio"));
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
