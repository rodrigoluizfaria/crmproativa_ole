/**
 * OriginaChamadaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class OriginaChamadaRequest  extends com.proativaservicos.service.asynchronous.vsphone.CustomRequest  implements java.io.Serializable {
    private java.lang.String aplicacao;

    private java.lang.String aplicacaoDados;

    private java.lang.String contexto;

    private java.lang.String destino;

    private java.lang.String identificacao;

    private boolean monitorar;

    private java.lang.String origem;

    private long tempoLimite;

    private com.proativaservicos.service.asynchronous.vsphone.Variavel[] variaveis;

    public OriginaChamadaRequest() {
    }

    public OriginaChamadaRequest(
           java.lang.String aplicacao,
           java.lang.String aplicacaoDados,
           java.lang.String contexto,
           java.lang.String destino,
           java.lang.String identificacao,
           boolean monitorar,
           java.lang.String origem,
           long tempoLimite,
           com.proativaservicos.service.asynchronous.vsphone.Variavel[] variaveis) {
        this.aplicacao = aplicacao;
        this.aplicacaoDados = aplicacaoDados;
        this.contexto = contexto;
        this.destino = destino;
        this.identificacao = identificacao;
        this.monitorar = monitorar;
        this.origem = origem;
        this.tempoLimite = tempoLimite;
        this.variaveis = variaveis;
    }


    /**
     * Gets the aplicacao value for this OriginaChamadaRequest.
     * 
     * @return aplicacao
     */
    public java.lang.String getAplicacao() {
        return aplicacao;
    }


    /**
     * Sets the aplicacao value for this OriginaChamadaRequest.
     * 
     * @param aplicacao
     */
    public void setAplicacao(java.lang.String aplicacao) {
        this.aplicacao = aplicacao;
    }


    /**
     * Gets the aplicacaoDados value for this OriginaChamadaRequest.
     * 
     * @return aplicacaoDados
     */
    public java.lang.String getAplicacaoDados() {
        return aplicacaoDados;
    }


    /**
     * Sets the aplicacaoDados value for this OriginaChamadaRequest.
     * 
     * @param aplicacaoDados
     */
    public void setAplicacaoDados(java.lang.String aplicacaoDados) {
        this.aplicacaoDados = aplicacaoDados;
    }


    /**
     * Gets the contexto value for this OriginaChamadaRequest.
     * 
     * @return contexto
     */
    public java.lang.String getContexto() {
        return contexto;
    }


    /**
     * Sets the contexto value for this OriginaChamadaRequest.
     * 
     * @param contexto
     */
    public void setContexto(java.lang.String contexto) {
        this.contexto = contexto;
    }


    /**
     * Gets the destino value for this OriginaChamadaRequest.
     * 
     * @return destino
     */
    public java.lang.String getDestino() {
        return destino;
    }


    /**
     * Sets the destino value for this OriginaChamadaRequest.
     * 
     * @param destino
     */
    public void setDestino(java.lang.String destino) {
        this.destino = destino;
    }


    /**
     * Gets the identificacao value for this OriginaChamadaRequest.
     * 
     * @return identificacao
     */
    public java.lang.String getIdentificacao() {
        return identificacao;
    }


    /**
     * Sets the identificacao value for this OriginaChamadaRequest.
     * 
     * @param identificacao
     */
    public void setIdentificacao(java.lang.String identificacao) {
        this.identificacao = identificacao;
    }


    /**
     * Gets the monitorar value for this OriginaChamadaRequest.
     * 
     * @return monitorar
     */
    public boolean isMonitorar() {
        return monitorar;
    }


    /**
     * Sets the monitorar value for this OriginaChamadaRequest.
     * 
     * @param monitorar
     */
    public void setMonitorar(boolean monitorar) {
        this.monitorar = monitorar;
    }


    /**
     * Gets the origem value for this OriginaChamadaRequest.
     * 
     * @return origem
     */
    public java.lang.String getOrigem() {
        return origem;
    }


    /**
     * Sets the origem value for this OriginaChamadaRequest.
     * 
     * @param origem
     */
    public void setOrigem(java.lang.String origem) {
        this.origem = origem;
    }


    /**
     * Gets the tempoLimite value for this OriginaChamadaRequest.
     * 
     * @return tempoLimite
     */
    public long getTempoLimite() {
        return tempoLimite;
    }


    /**
     * Sets the tempoLimite value for this OriginaChamadaRequest.
     * 
     * @param tempoLimite
     */
    public void setTempoLimite(long tempoLimite) {
        this.tempoLimite = tempoLimite;
    }


    /**
     * Gets the variaveis value for this OriginaChamadaRequest.
     * 
     * @return variaveis
     */
    public com.proativaservicos.service.asynchronous.vsphone.Variavel[] getVariaveis() {
        return variaveis;
    }


    /**
     * Sets the variaveis value for this OriginaChamadaRequest.
     * 
     * @param variaveis
     */
    public void setVariaveis(com.proativaservicos.service.asynchronous.vsphone.Variavel[] variaveis) {
        this.variaveis = variaveis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OriginaChamadaRequest)) return false;
        OriginaChamadaRequest other = (OriginaChamadaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.aplicacao==null && other.getAplicacao()==null) || 
             (this.aplicacao!=null &&
              this.aplicacao.equals(other.getAplicacao()))) &&
            ((this.aplicacaoDados==null && other.getAplicacaoDados()==null) || 
             (this.aplicacaoDados!=null &&
              this.aplicacaoDados.equals(other.getAplicacaoDados()))) &&
            ((this.contexto==null && other.getContexto()==null) || 
             (this.contexto!=null &&
              this.contexto.equals(other.getContexto()))) &&
            ((this.destino==null && other.getDestino()==null) || 
             (this.destino!=null &&
              this.destino.equals(other.getDestino()))) &&
            ((this.identificacao==null && other.getIdentificacao()==null) || 
             (this.identificacao!=null &&
              this.identificacao.equals(other.getIdentificacao()))) &&
            this.monitorar == other.isMonitorar() &&
            ((this.origem==null && other.getOrigem()==null) || 
             (this.origem!=null &&
              this.origem.equals(other.getOrigem()))) &&
            this.tempoLimite == other.getTempoLimite() &&
            ((this.variaveis==null && other.getVariaveis()==null) || 
             (this.variaveis!=null &&
              java.util.Arrays.equals(this.variaveis, other.getVariaveis())));
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
        if (getAplicacao() != null) {
            _hashCode += getAplicacao().hashCode();
        }
        if (getAplicacaoDados() != null) {
            _hashCode += getAplicacaoDados().hashCode();
        }
        if (getContexto() != null) {
            _hashCode += getContexto().hashCode();
        }
        if (getDestino() != null) {
            _hashCode += getDestino().hashCode();
        }
        if (getIdentificacao() != null) {
            _hashCode += getIdentificacao().hashCode();
        }
        _hashCode += (isMonitorar() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOrigem() != null) {
            _hashCode += getOrigem().hashCode();
        }
        _hashCode += new Long(getTempoLimite()).hashCode();
        if (getVariaveis() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVariaveis());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVariaveis(), i);
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
        new org.apache.axis.description.TypeDesc(OriginaChamadaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "OriginaChamadaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "aplicacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicacaoDados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "aplicacaoDados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "contexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "destino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "identificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monitorar");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "monitorar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "origem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tempoLimite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "tempoLimite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("variaveis");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "variaveis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Variavel"));
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
