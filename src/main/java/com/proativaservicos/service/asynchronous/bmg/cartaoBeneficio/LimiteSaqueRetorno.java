/**
 * LimiteSaqueRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class LimiteSaqueRetorno  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotal cet;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotalParaCartaoCliente cetSaqueComplementar;

    private boolean elegivelComissao;

    private boolean elegivelSeguros;

    private boolean excecaoDeRegraDeNegocio;

    private boolean excecaoGenerica;

    private double limiteCartao;

    private double limiteDisponivel;

    private java.lang.String mensagemDeErro;

    private boolean permiteAberturaContaPagamento;

    private double valorMargem;

    private double valorSaqueMaximo;

    private double valorSaqueMinimo;

    private double valorSaqueParaMargemComplementarComAgregacaoDeMargem;

    public LimiteSaqueRetorno() {
    }

    public LimiteSaqueRetorno(
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotal cet,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotalParaCartaoCliente cetSaqueComplementar,
           boolean elegivelComissao,
           boolean elegivelSeguros,
           boolean excecaoDeRegraDeNegocio,
           boolean excecaoGenerica,
           double limiteCartao,
           double limiteDisponivel,
           java.lang.String mensagemDeErro,
           boolean permiteAberturaContaPagamento,
           double valorMargem,
           double valorSaqueMaximo,
           double valorSaqueMinimo,
           double valorSaqueParaMargemComplementarComAgregacaoDeMargem) {
           this.cet = cet;
           this.cetSaqueComplementar = cetSaqueComplementar;
           this.elegivelComissao = elegivelComissao;
           this.elegivelSeguros = elegivelSeguros;
           this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
           this.excecaoGenerica = excecaoGenerica;
           this.limiteCartao = limiteCartao;
           this.limiteDisponivel = limiteDisponivel;
           this.mensagemDeErro = mensagemDeErro;
           this.permiteAberturaContaPagamento = permiteAberturaContaPagamento;
           this.valorMargem = valorMargem;
           this.valorSaqueMaximo = valorSaqueMaximo;
           this.valorSaqueMinimo = valorSaqueMinimo;
           this.valorSaqueParaMargemComplementarComAgregacaoDeMargem = valorSaqueParaMargemComplementarComAgregacaoDeMargem;
    }


    /**
     * Gets the cet value for this LimiteSaqueRetorno.
     * 
     * @return cet
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotal getCet() {
        return cet;
    }


    /**
     * Sets the cet value for this LimiteSaqueRetorno.
     * 
     * @param cet
     */
    public void setCet(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotal cet) {
        this.cet = cet;
    }


    /**
     * Gets the cetSaqueComplementar value for this LimiteSaqueRetorno.
     * 
     * @return cetSaqueComplementar
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotalParaCartaoCliente getCetSaqueComplementar() {
        return cetSaqueComplementar;
    }


    /**
     * Sets the cetSaqueComplementar value for this LimiteSaqueRetorno.
     * 
     * @param cetSaqueComplementar
     */
    public void setCetSaqueComplementar(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotalParaCartaoCliente cetSaqueComplementar) {
        this.cetSaqueComplementar = cetSaqueComplementar;
    }


    /**
     * Gets the elegivelComissao value for this LimiteSaqueRetorno.
     * 
     * @return elegivelComissao
     */
    public boolean isElegivelComissao() {
        return elegivelComissao;
    }


    /**
     * Sets the elegivelComissao value for this LimiteSaqueRetorno.
     * 
     * @param elegivelComissao
     */
    public void setElegivelComissao(boolean elegivelComissao) {
        this.elegivelComissao = elegivelComissao;
    }


    /**
     * Gets the elegivelSeguros value for this LimiteSaqueRetorno.
     * 
     * @return elegivelSeguros
     */
    public boolean isElegivelSeguros() {
        return elegivelSeguros;
    }


    /**
     * Sets the elegivelSeguros value for this LimiteSaqueRetorno.
     * 
     * @param elegivelSeguros
     */
    public void setElegivelSeguros(boolean elegivelSeguros) {
        this.elegivelSeguros = elegivelSeguros;
    }


    /**
     * Gets the excecaoDeRegraDeNegocio value for this LimiteSaqueRetorno.
     * 
     * @return excecaoDeRegraDeNegocio
     */
    public boolean isExcecaoDeRegraDeNegocio() {
        return excecaoDeRegraDeNegocio;
    }


    /**
     * Sets the excecaoDeRegraDeNegocio value for this LimiteSaqueRetorno.
     * 
     * @param excecaoDeRegraDeNegocio
     */
    public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
        this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
    }


    /**
     * Gets the excecaoGenerica value for this LimiteSaqueRetorno.
     * 
     * @return excecaoGenerica
     */
    public boolean isExcecaoGenerica() {
        return excecaoGenerica;
    }


    /**
     * Sets the excecaoGenerica value for this LimiteSaqueRetorno.
     * 
     * @param excecaoGenerica
     */
    public void setExcecaoGenerica(boolean excecaoGenerica) {
        this.excecaoGenerica = excecaoGenerica;
    }


    /**
     * Gets the limiteCartao value for this LimiteSaqueRetorno.
     * 
     * @return limiteCartao
     */
    public double getLimiteCartao() {
        return limiteCartao;
    }


    /**
     * Sets the limiteCartao value for this LimiteSaqueRetorno.
     * 
     * @param limiteCartao
     */
    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }


    /**
     * Gets the limiteDisponivel value for this LimiteSaqueRetorno.
     * 
     * @return limiteDisponivel
     */
    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }


    /**
     * Sets the limiteDisponivel value for this LimiteSaqueRetorno.
     * 
     * @param limiteDisponivel
     */
    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }


    /**
     * Gets the mensagemDeErro value for this LimiteSaqueRetorno.
     * 
     * @return mensagemDeErro
     */
    public java.lang.String getMensagemDeErro() {
        return mensagemDeErro;
    }


    /**
     * Sets the mensagemDeErro value for this LimiteSaqueRetorno.
     * 
     * @param mensagemDeErro
     */
    public void setMensagemDeErro(java.lang.String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }


    /**
     * Gets the permiteAberturaContaPagamento value for this LimiteSaqueRetorno.
     * 
     * @return permiteAberturaContaPagamento
     */
    public boolean isPermiteAberturaContaPagamento() {
        return permiteAberturaContaPagamento;
    }


    /**
     * Sets the permiteAberturaContaPagamento value for this LimiteSaqueRetorno.
     * 
     * @param permiteAberturaContaPagamento
     */
    public void setPermiteAberturaContaPagamento(boolean permiteAberturaContaPagamento) {
        this.permiteAberturaContaPagamento = permiteAberturaContaPagamento;
    }


    /**
     * Gets the valorMargem value for this LimiteSaqueRetorno.
     * 
     * @return valorMargem
     */
    public double getValorMargem() {
        return valorMargem;
    }


    /**
     * Sets the valorMargem value for this LimiteSaqueRetorno.
     * 
     * @param valorMargem
     */
    public void setValorMargem(double valorMargem) {
        this.valorMargem = valorMargem;
    }


    /**
     * Gets the valorSaqueMaximo value for this LimiteSaqueRetorno.
     * 
     * @return valorSaqueMaximo
     */
    public double getValorSaqueMaximo() {
        return valorSaqueMaximo;
    }


    /**
     * Sets the valorSaqueMaximo value for this LimiteSaqueRetorno.
     * 
     * @param valorSaqueMaximo
     */
    public void setValorSaqueMaximo(double valorSaqueMaximo) {
        this.valorSaqueMaximo = valorSaqueMaximo;
    }


    /**
     * Gets the valorSaqueMinimo value for this LimiteSaqueRetorno.
     * 
     * @return valorSaqueMinimo
     */
    public double getValorSaqueMinimo() {
        return valorSaqueMinimo;
    }


    /**
     * Sets the valorSaqueMinimo value for this LimiteSaqueRetorno.
     * 
     * @param valorSaqueMinimo
     */
    public void setValorSaqueMinimo(double valorSaqueMinimo) {
        this.valorSaqueMinimo = valorSaqueMinimo;
    }


    /**
     * Gets the valorSaqueParaMargemComplementarComAgregacaoDeMargem value for this LimiteSaqueRetorno.
     * 
     * @return valorSaqueParaMargemComplementarComAgregacaoDeMargem
     */
    public double getValorSaqueParaMargemComplementarComAgregacaoDeMargem() {
        return valorSaqueParaMargemComplementarComAgregacaoDeMargem;
    }


    /**
     * Sets the valorSaqueParaMargemComplementarComAgregacaoDeMargem value for this LimiteSaqueRetorno.
     * 
     * @param valorSaqueParaMargemComplementarComAgregacaoDeMargem
     */
    public void setValorSaqueParaMargemComplementarComAgregacaoDeMargem(double valorSaqueParaMargemComplementarComAgregacaoDeMargem) {
        this.valorSaqueParaMargemComplementarComAgregacaoDeMargem = valorSaqueParaMargemComplementarComAgregacaoDeMargem;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LimiteSaqueRetorno)) return false;
        LimiteSaqueRetorno other = (LimiteSaqueRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cet==null && other.getCet()==null) || 
             (this.cet!=null &&
              this.cet.equals(other.getCet()))) &&
            ((this.cetSaqueComplementar==null && other.getCetSaqueComplementar()==null) || 
             (this.cetSaqueComplementar!=null &&
              this.cetSaqueComplementar.equals(other.getCetSaqueComplementar()))) &&
            this.elegivelComissao == other.isElegivelComissao() &&
            this.elegivelSeguros == other.isElegivelSeguros() &&
            this.excecaoDeRegraDeNegocio == other.isExcecaoDeRegraDeNegocio() &&
            this.excecaoGenerica == other.isExcecaoGenerica() &&
            this.limiteCartao == other.getLimiteCartao() &&
            this.limiteDisponivel == other.getLimiteDisponivel() &&
            ((this.mensagemDeErro==null && other.getMensagemDeErro()==null) || 
             (this.mensagemDeErro!=null &&
              this.mensagemDeErro.equals(other.getMensagemDeErro()))) &&
            this.permiteAberturaContaPagamento == other.isPermiteAberturaContaPagamento() &&
            this.valorMargem == other.getValorMargem() &&
            this.valorSaqueMaximo == other.getValorSaqueMaximo() &&
            this.valorSaqueMinimo == other.getValorSaqueMinimo() &&
            this.valorSaqueParaMargemComplementarComAgregacaoDeMargem == other.getValorSaqueParaMargemComplementarComAgregacaoDeMargem();
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
        if (getCet() != null) {
            _hashCode += getCet().hashCode();
        }
        if (getCetSaqueComplementar() != null) {
            _hashCode += getCetSaqueComplementar().hashCode();
        }
        _hashCode += (isElegivelComissao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isElegivelSeguros() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcecaoDeRegraDeNegocio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcecaoGenerica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Double(getLimiteCartao()).hashCode();
        _hashCode += new Double(getLimiteDisponivel()).hashCode();
        if (getMensagemDeErro() != null) {
            _hashCode += getMensagemDeErro().hashCode();
        }
        _hashCode += (isPermiteAberturaContaPagamento() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Double(getValorMargem()).hashCode();
        _hashCode += new Double(getValorSaqueMaximo()).hashCode();
        _hashCode += new Double(getValorSaqueMinimo()).hashCode();
        _hashCode += new Double(getValorSaqueParaMargemComplementarComAgregacaoDeMargem()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LimiteSaqueRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteSaqueRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CustoEfetivoTotal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cetSaqueComplementar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cetSaqueComplementar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CustoEfetivoTotalParaCartaoCliente"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elegivelComissao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "elegivelComissao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elegivelSeguros");
        elemField.setXmlName(new javax.xml.namespace.QName("", "elegivelSeguros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
        elemField.setFieldName("limiteCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limiteDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagemDeErro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemDeErro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permiteAberturaContaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permiteAberturaContaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorMargem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorMargem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaqueMaximo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaqueMaximo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaqueMinimo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaqueMinimo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaqueParaMargemComplementarComAgregacaoDeMargem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaqueParaMargemComplementarComAgregacaoDeMargem"));
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
