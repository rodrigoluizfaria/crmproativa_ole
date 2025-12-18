/**
 * CartaoClienteAtivoVendaSeguro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class CartaoClienteAtivoVendaSeguro  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceBean  implements java.io.Serializable {
    private boolean cartaoSelecionado;

    private String cidade;

    private Integer codigoCliente;

    private String codigoEntidade;

    private java.math.BigDecimal cpf;

    private java.util.Calendar dataNascimento;

    private Boolean ehElegivel;

    private java.math.BigDecimal limiteCartao;

    private String motivoElegibilidade;

    private String nomeCliente;

    private String nomeEntidade;

    private String numeroCartao;

    private Integer numeroInternoConta;

    private String orgaoFormatado;

    private Short sequencialOrgao;

    public CartaoClienteAtivoVendaSeguro() {
    }

    public CartaoClienteAtivoVendaSeguro(
           boolean cartaoSelecionado,
           String cidade,
           Integer codigoCliente,
           String codigoEntidade,
           java.math.BigDecimal cpf,
           java.util.Calendar dataNascimento,
           Boolean ehElegivel,
           java.math.BigDecimal limiteCartao,
           String motivoElegibilidade,
           String nomeCliente,
           String nomeEntidade,
           String numeroCartao,
           Integer numeroInternoConta,
           String orgaoFormatado,
           Short sequencialOrgao) {
        this.cartaoSelecionado = cartaoSelecionado;
        this.cidade = cidade;
        this.codigoCliente = codigoCliente;
        this.codigoEntidade = codigoEntidade;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.ehElegivel = ehElegivel;
        this.limiteCartao = limiteCartao;
        this.motivoElegibilidade = motivoElegibilidade;
        this.nomeCliente = nomeCliente;
        this.nomeEntidade = nomeEntidade;
        this.numeroCartao = numeroCartao;
        this.numeroInternoConta = numeroInternoConta;
        this.orgaoFormatado = orgaoFormatado;
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the cartaoSelecionado value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return cartaoSelecionado
     */
    public boolean isCartaoSelecionado() {
        return cartaoSelecionado;
    }


    /**
     * Sets the cartaoSelecionado value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param cartaoSelecionado
     */
    public void setCartaoSelecionado(boolean cartaoSelecionado) {
        this.cartaoSelecionado = cartaoSelecionado;
    }


    /**
     * Gets the cidade value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return cidade
     */
    public String getCidade() {
        return cidade;
    }


    /**
     * Sets the cidade value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    /**
     * Gets the codigoCliente value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return codigoCliente
     */
    public Integer getCodigoCliente() {
        return codigoCliente;
    }


    /**
     * Sets the codigoCliente value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param codigoCliente
     */
    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }


    /**
     * Gets the codigoEntidade value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return codigoEntidade
     */
    public String getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(String codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the cpf value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return cpf
     */
    public java.math.BigDecimal getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param cpf
     */
    public void setCpf(java.math.BigDecimal cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the dataNascimento value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return dataNascimento
     */
    public java.util.Calendar getDataNascimento() {
        return dataNascimento;
    }


    /**
     * Sets the dataNascimento value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param dataNascimento
     */
    public void setDataNascimento(java.util.Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    /**
     * Gets the ehElegivel value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return ehElegivel
     */
    public Boolean getEhElegivel() {
        return ehElegivel;
    }


    /**
     * Sets the ehElegivel value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param ehElegivel
     */
    public void setEhElegivel(Boolean ehElegivel) {
        this.ehElegivel = ehElegivel;
    }


    /**
     * Gets the limiteCartao value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return limiteCartao
     */
    public java.math.BigDecimal getLimiteCartao() {
        return limiteCartao;
    }


    /**
     * Sets the limiteCartao value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param limiteCartao
     */
    public void setLimiteCartao(java.math.BigDecimal limiteCartao) {
        this.limiteCartao = limiteCartao;
    }


    /**
     * Gets the motivoElegibilidade value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return motivoElegibilidade
     */
    public String getMotivoElegibilidade() {
        return motivoElegibilidade;
    }


    /**
     * Sets the motivoElegibilidade value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param motivoElegibilidade
     */
    public void setMotivoElegibilidade(String motivoElegibilidade) {
        this.motivoElegibilidade = motivoElegibilidade;
    }


    /**
     * Gets the nomeCliente value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }


    /**
     * Sets the nomeCliente value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param nomeCliente
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }


    /**
     * Gets the nomeEntidade value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return nomeEntidade
     */
    public String getNomeEntidade() {
        return nomeEntidade;
    }


    /**
     * Sets the nomeEntidade value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param nomeEntidade
     */
    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }


    /**
     * Gets the numeroCartao value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return numeroCartao
     */
    public String getNumeroCartao() {
        return numeroCartao;
    }


    /**
     * Sets the numeroCartao value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param numeroCartao
     */
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }


    /**
     * Gets the numeroInternoConta value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return numeroInternoConta
     */
    public Integer getNumeroInternoConta() {
        return numeroInternoConta;
    }


    /**
     * Sets the numeroInternoConta value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param numeroInternoConta
     */
    public void setNumeroInternoConta(Integer numeroInternoConta) {
        this.numeroInternoConta = numeroInternoConta;
    }


    /**
     * Gets the orgaoFormatado value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return orgaoFormatado
     */
    public String getOrgaoFormatado() {
        return orgaoFormatado;
    }


    /**
     * Sets the orgaoFormatado value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param orgaoFormatado
     */
    public void setOrgaoFormatado(String orgaoFormatado) {
        this.orgaoFormatado = orgaoFormatado;
    }


    /**
     * Gets the sequencialOrgao value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @return sequencialOrgao
     */
    public Short getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this CartaoClienteAtivoVendaSeguro.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(Short sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CartaoClienteAtivoVendaSeguro)) return false;
        CartaoClienteAtivoVendaSeguro other = (CartaoClienteAtivoVendaSeguro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.cartaoSelecionado == other.isCartaoSelecionado() &&
            ((this.cidade==null && other.getCidade()==null) || 
             (this.cidade!=null &&
              this.cidade.equals(other.getCidade()))) &&
            ((this.codigoCliente==null && other.getCodigoCliente()==null) || 
             (this.codigoCliente!=null &&
              this.codigoCliente.equals(other.getCodigoCliente()))) &&
            ((this.codigoEntidade==null && other.getCodigoEntidade()==null) || 
             (this.codigoEntidade!=null &&
              this.codigoEntidade.equals(other.getCodigoEntidade()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.dataNascimento==null && other.getDataNascimento()==null) || 
             (this.dataNascimento!=null &&
              this.dataNascimento.equals(other.getDataNascimento()))) &&
            ((this.ehElegivel==null && other.getEhElegivel()==null) || 
             (this.ehElegivel!=null &&
              this.ehElegivel.equals(other.getEhElegivel()))) &&
            ((this.limiteCartao==null && other.getLimiteCartao()==null) || 
             (this.limiteCartao!=null &&
              this.limiteCartao.equals(other.getLimiteCartao()))) &&
            ((this.motivoElegibilidade==null && other.getMotivoElegibilidade()==null) || 
             (this.motivoElegibilidade!=null &&
              this.motivoElegibilidade.equals(other.getMotivoElegibilidade()))) &&
            ((this.nomeCliente==null && other.getNomeCliente()==null) || 
             (this.nomeCliente!=null &&
              this.nomeCliente.equals(other.getNomeCliente()))) &&
            ((this.nomeEntidade==null && other.getNomeEntidade()==null) || 
             (this.nomeEntidade!=null &&
              this.nomeEntidade.equals(other.getNomeEntidade()))) &&
            ((this.numeroCartao==null && other.getNumeroCartao()==null) || 
             (this.numeroCartao!=null &&
              this.numeroCartao.equals(other.getNumeroCartao()))) &&
            ((this.numeroInternoConta==null && other.getNumeroInternoConta()==null) || 
             (this.numeroInternoConta!=null &&
              this.numeroInternoConta.equals(other.getNumeroInternoConta()))) &&
            ((this.orgaoFormatado==null && other.getOrgaoFormatado()==null) || 
             (this.orgaoFormatado!=null &&
              this.orgaoFormatado.equals(other.getOrgaoFormatado()))) &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao())));
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
        _hashCode += (isCartaoSelecionado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCidade() != null) {
            _hashCode += getCidade().hashCode();
        }
        if (getCodigoCliente() != null) {
            _hashCode += getCodigoCliente().hashCode();
        }
        if (getCodigoEntidade() != null) {
            _hashCode += getCodigoEntidade().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getDataNascimento() != null) {
            _hashCode += getDataNascimento().hashCode();
        }
        if (getEhElegivel() != null) {
            _hashCode += getEhElegivel().hashCode();
        }
        if (getLimiteCartao() != null) {
            _hashCode += getLimiteCartao().hashCode();
        }
        if (getMotivoElegibilidade() != null) {
            _hashCode += getMotivoElegibilidade().hashCode();
        }
        if (getNomeCliente() != null) {
            _hashCode += getNomeCliente().hashCode();
        }
        if (getNomeEntidade() != null) {
            _hashCode += getNomeEntidade().hashCode();
        }
        if (getNumeroCartao() != null) {
            _hashCode += getNumeroCartao().hashCode();
        }
        if (getNumeroInternoConta() != null) {
            _hashCode += getNumeroInternoConta().hashCode();
        }
        if (getOrgaoFormatado() != null) {
            _hashCode += getOrgaoFormatado().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CartaoClienteAtivoVendaSeguro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoClienteAtivoVendaSeguro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartaoSelecionado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cartaoSelecionado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataNascimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ehElegivel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ehElegivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limiteCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motivoElegibilidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "motivoElegibilidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroInternoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroInternoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgaoFormatado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orgaoFormatado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialOrgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "short"));
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
