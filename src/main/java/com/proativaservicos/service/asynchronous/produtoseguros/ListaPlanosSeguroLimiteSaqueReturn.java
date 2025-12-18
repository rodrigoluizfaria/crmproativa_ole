/**
 * ListaPlanosSeguroLimiteSaqueReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ListaPlanosSeguroLimiteSaqueReturn  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceBean  implements java.io.Serializable {
    private boolean excecaoGenerica;

    private boolean excecaoRegraNegocio;

    private String mensagemErro;

    private com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroLimiteSaque[] planosComLimiteSaque;

    public ListaPlanosSeguroLimiteSaqueReturn() {
    }

    public ListaPlanosSeguroLimiteSaqueReturn(
           boolean excecaoGenerica,
           boolean excecaoRegraNegocio,
           String mensagemErro,
           com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroLimiteSaque[] planosComLimiteSaque) {
        this.excecaoGenerica = excecaoGenerica;
        this.excecaoRegraNegocio = excecaoRegraNegocio;
        this.mensagemErro = mensagemErro;
        this.planosComLimiteSaque = planosComLimiteSaque;
    }


    /**
     * Gets the excecaoGenerica value for this ListaPlanosSeguroLimiteSaqueReturn.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this ListaPlanosSeguroLimiteSaqueReturn.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the excecaoRegraNegocio value for this ListaPlanosSeguroLimiteSaqueReturn.
     * 
     * @return excecaoRegraNegocio
     */
    public boolean isExcecaoRegraNegocio() {
        return excecaoRegraNegocio;
    }


    /**
     * Sets the excecaoRegraNegocio value for this ListaPlanosSeguroLimiteSaqueReturn.
     * 
     * @param excecaoRegraNegocio
     */
    public void setExcecaoRegraNegocio(boolean excecaoRegraNegocio) {
        this.excecaoRegraNegocio = excecaoRegraNegocio;
    }


    /**
     * Gets the mensagemErro value for this ListaPlanosSeguroLimiteSaqueReturn.
     * 
     * @return mensagemErro
     */
    public String getMensagemErro() {
        return mensagemErro;
    }


    /**
     * Sets the mensagemErro value for this ListaPlanosSeguroLimiteSaqueReturn.
     * 
     * @param mensagemErro
     */
    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }


    /**
     * Gets the planosComLimiteSaque value for this ListaPlanosSeguroLimiteSaqueReturn.
     * 
     * @return planosComLimiteSaque
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroLimiteSaque[] getPlanosComLimiteSaque() {
        return planosComLimiteSaque;
    }


    /**
     * Sets the planosComLimiteSaque value for this ListaPlanosSeguroLimiteSaqueReturn.
     * 
     * @param planosComLimiteSaque
     */
    public void setPlanosComLimiteSaque(com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroLimiteSaque[] planosComLimiteSaque) {
        this.planosComLimiteSaque = planosComLimiteSaque;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ListaPlanosSeguroLimiteSaqueReturn)) return false;
        ListaPlanosSeguroLimiteSaqueReturn other = (ListaPlanosSeguroLimiteSaqueReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.excecaoGenerica == other.isExcecaoGenerica() &&
            this.excecaoRegraNegocio == other.isExcecaoRegraNegocio() &&
            ((this.mensagemErro==null && other.getMensagemErro()==null) || 
             (this.mensagemErro!=null &&
              this.mensagemErro.equals(other.getMensagemErro()))) &&
            ((this.planosComLimiteSaque==null && other.getPlanosComLimiteSaque()==null) || 
             (this.planosComLimiteSaque!=null &&
              java.util.Arrays.equals(this.planosComLimiteSaque, other.getPlanosComLimiteSaque())));
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
        _hashCode += (isExcecaoGenerica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcecaoRegraNegocio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagemErro() != null) {
            _hashCode += getMensagemErro().hashCode();
        }
        if (getPlanosComLimiteSaque() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPlanosComLimiteSaque());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getPlanosComLimiteSaque(), i);
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
        new org.apache.axis.description.TypeDesc(ListaPlanosSeguroLimiteSaqueReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaPlanosSeguroLimiteSaqueReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecaoGenerica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecaoGenerica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excecaoRegraNegocio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excecaoRegraNegocio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagemErro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemErro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("planosComLimiteSaque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "planosComLimiteSaque"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosContratacaoSeguroLimiteSaque"));
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
