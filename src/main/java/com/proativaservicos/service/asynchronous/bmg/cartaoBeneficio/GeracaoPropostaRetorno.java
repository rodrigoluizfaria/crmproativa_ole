/**
 * GeracaoPropostaRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class GeracaoPropostaRetorno  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosAssinaturaEletronica dadosAssinaturaEletronica;

    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private java.lang.String mensagemDeErro;

    private java.lang.String numeroPropostaGerada;

    private java.lang.String[] numerosPropostasGeradasAutomaticamente;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosScript[] scripts;

    public GeracaoPropostaRetorno() {
    }

    public GeracaoPropostaRetorno(
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosAssinaturaEletronica dadosAssinaturaEletronica,
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           java.lang.String mensagemDeErro,
           java.lang.String numeroPropostaGerada,
           java.lang.String[] numerosPropostasGeradasAutomaticamente,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosScript[] scripts) {
           this.dadosAssinaturaEletronica = dadosAssinaturaEletronica;
           this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
           this.excecaoGenerica = excecaoGenerica;
           this.mensagemDeErro = mensagemDeErro;
           this.numeroPropostaGerada = numeroPropostaGerada;
           this.numerosPropostasGeradasAutomaticamente = numerosPropostasGeradasAutomaticamente;
           this.scripts = scripts;
    }


    /**
     * Gets the dadosAssinaturaEletronica value for this GeracaoPropostaRetorno.
     * 
     * @return dadosAssinaturaEletronica
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosAssinaturaEletronica getDadosAssinaturaEletronica() {
        return dadosAssinaturaEletronica;
    }


    /**
     * Sets the dadosAssinaturaEletronica value for this GeracaoPropostaRetorno.
     * 
     * @param dadosAssinaturaEletronica
     */
    public void setDadosAssinaturaEletronica(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosAssinaturaEletronica dadosAssinaturaEletronica) {
        this.dadosAssinaturaEletronica = dadosAssinaturaEletronica;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this GeracaoPropostaRetorno.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this GeracaoPropostaRetorno.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this GeracaoPropostaRetorno.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this GeracaoPropostaRetorno.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the mensagemDeErro value for this GeracaoPropostaRetorno.
     * 
     * @return mensagemDeErro
     */
    public java.lang.String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this GeracaoPropostaRetorno.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(java.lang.String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the numeroPropostaGerada value for this GeracaoPropostaRetorno.
     * 
     * @return numeroPropostaGerada
     */
    public java.lang.String getNumeroPropostaGerada() {
        return numeroPropostaGerada;
    }


    /**
     * Sets the numeroPropostaGerada value for this GeracaoPropostaRetorno.
     * 
     * @param numeroPropostaGerada
     */
    public void setNumeroPropostaGerada(java.lang.String numeroPropostaGerada) {
        this.numeroPropostaGerada = numeroPropostaGerada;
    }


    /**
     * Gets the numerosPropostasGeradasAutomaticamente value for this GeracaoPropostaRetorno.
     * 
     * @return numerosPropostasGeradasAutomaticamente
     */
    public java.lang.String[] getNumerosPropostasGeradasAutomaticamente() {
        return numerosPropostasGeradasAutomaticamente;
    }


    /**
     * Sets the numerosPropostasGeradasAutomaticamente value for this GeracaoPropostaRetorno.
     * 
     * @param numerosPropostasGeradasAutomaticamente
     */
    public void setNumerosPropostasGeradasAutomaticamente(java.lang.String[] numerosPropostasGeradasAutomaticamente) {
        this.numerosPropostasGeradasAutomaticamente = numerosPropostasGeradasAutomaticamente;
    }


    /**
     * Gets the scripts value for this GeracaoPropostaRetorno.
     * 
     * @return scripts
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosScript[] getScripts() {
        return scripts;
    }


    /**
     * Sets the scripts value for this GeracaoPropostaRetorno.
     * 
     * @param scripts
     */
    public void setScripts(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosScript[] scripts) {
        this.scripts = scripts;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GeracaoPropostaRetorno)) return false;
        GeracaoPropostaRetorno other = (GeracaoPropostaRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dadosAssinaturaEletronica==null && other.getDadosAssinaturaEletronica()==null) || 
             (this.dadosAssinaturaEletronica!=null &&
              this.dadosAssinaturaEletronica.equals(other.getDadosAssinaturaEletronica()))) &&
            this.excecaoDeRegraDeNegocio == other.isExcecaoDeRegraDeNegocio() &&
            this.excecaoGenerica == other.isExcecaoGenerica() &&
            ((this.mensagemDeErro==null && other.getMensagemDeErro()==null) || 
             (this.mensagemDeErro!=null &&
              this.mensagemDeErro.equals(other.getMensagemDeErro()))) &&
            ((this.numeroPropostaGerada==null && other.getNumeroPropostaGerada()==null) || 
             (this.numeroPropostaGerada!=null &&
              this.numeroPropostaGerada.equals(other.getNumeroPropostaGerada()))) &&
            ((this.numerosPropostasGeradasAutomaticamente==null && other.getNumerosPropostasGeradasAutomaticamente()==null) || 
             (this.numerosPropostasGeradasAutomaticamente!=null &&
              java.util.Arrays.equals(this.numerosPropostasGeradasAutomaticamente, other.getNumerosPropostasGeradasAutomaticamente()))) &&
            ((this.scripts==null && other.getScripts()==null) || 
             (this.scripts!=null &&
              java.util.Arrays.equals(this.scripts, other.getScripts())));
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
        if (getDadosAssinaturaEletronica() != null) {
            _hashCode += getDadosAssinaturaEletronica().hashCode();
        }
        _hashCode += (isExcecaoDeRegraDeNegocio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcecaoGenerica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMensagemDeErro() != null) {
            _hashCode += getMensagemDeErro().hashCode();
        }
        if (getNumeroPropostaGerada() != null) {
            _hashCode += getNumeroPropostaGerada().hashCode();
        }
        if (getNumerosPropostasGeradasAutomaticamente() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNumerosPropostasGeradasAutomaticamente());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNumerosPropostasGeradasAutomaticamente(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getScripts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getScripts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getScripts(), i);
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
        new org.apache.axis.description.TypeDesc(GeracaoPropostaRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GeracaoPropostaRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dadosAssinaturaEletronica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dadosAssinaturaEletronica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosAssinaturaEletronica"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("numeroPropostaGerada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroPropostaGerada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numerosPropostasGeradasAutomaticamente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numerosPropostasGeradasAutomaticamente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scripts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "scripts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosScript"));
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
