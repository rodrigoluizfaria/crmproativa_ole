/**
 * PropostaSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class PropostaSeguroParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.produtoseguros.ClienteParameter cliente;

    private int codLoja;

    private String codigoAssociado;

    private int codigoPlano;

    private int codigoSeguro;

    private int codigoTipoBeneficio;

    private Integer codigoTipoFormaEnvio;

    private Integer codigoTipoPagamento;

    private int codigoformaPagamentoProdutoSeguro;

    private String cpfOperadorVendas;

    private String dataHoraAdesao;

    private String loginConsig;

    private String matricula;

    private String nomeSocial;

    private int numeroInternoConta;

    private Long protocoloMultiProdutos;

    private double renda;

    private String senhaConsig;

    public PropostaSeguroParameter() {
    }

    public PropostaSeguroParameter(
           String login,
           String senha,
           com.proativaservicos.service.asynchronous.produtoseguros.ClienteParameter cliente,
           int codLoja,
           String codigoAssociado,
           int codigoPlano,
           int codigoSeguro,
           int codigoTipoBeneficio,
           Integer codigoTipoFormaEnvio,
           Integer codigoTipoPagamento,
           int codigoformaPagamentoProdutoSeguro,
           String cpfOperadorVendas,
           String dataHoraAdesao,
           String loginConsig,
           String matricula,
           String nomeSocial,
           int numeroInternoConta,
           Long protocoloMultiProdutos,
           double renda,
           String senhaConsig) {
        super(
            login,
            senha);
        this.cliente = cliente;
        this.codLoja = codLoja;
        this.codigoAssociado = codigoAssociado;
        this.codigoPlano = codigoPlano;
        this.codigoSeguro = codigoSeguro;
        this.codigoTipoBeneficio = codigoTipoBeneficio;
        this.codigoTipoFormaEnvio = codigoTipoFormaEnvio;
        this.codigoTipoPagamento = codigoTipoPagamento;
        this.codigoformaPagamentoProdutoSeguro = codigoformaPagamentoProdutoSeguro;
        this.cpfOperadorVendas = cpfOperadorVendas;
        this.dataHoraAdesao = dataHoraAdesao;
        this.loginConsig = loginConsig;
        this.matricula = matricula;
        this.nomeSocial = nomeSocial;
        this.numeroInternoConta = numeroInternoConta;
        this.protocoloMultiProdutos = protocoloMultiProdutos;
        this.renda = renda;
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the cliente value for this PropostaSeguroParameter.
     * 
     * @return cliente
     */
    public com.proativaservicos.service.asynchronous.produtoseguros.ClienteParameter getCliente() {
        return cliente;
    }


    /**
     * Sets the cliente value for this PropostaSeguroParameter.
     * 
     * @param cliente
     */
    public void setCliente(com.proativaservicos.service.asynchronous.produtoseguros.ClienteParameter cliente) {
        this.cliente = cliente;
    }


    /**
     * Gets the codLoja value for this PropostaSeguroParameter.
     * 
     * @return codLoja
     */
    public int getCodLoja() {
        return codLoja;
    }


    /**
     * Sets the codLoja value for this PropostaSeguroParameter.
     * 
     * @param codLoja
     */
    public void setCodLoja(int codLoja) {
        this.codLoja = codLoja;
    }


    /**
     * Gets the codigoAssociado value for this PropostaSeguroParameter.
     * 
     * @return codigoAssociado
     */
    public String getCodigoAssociado() {
        return codigoAssociado;
    }


    /**
     * Sets the codigoAssociado value for this PropostaSeguroParameter.
     * 
     * @param codigoAssociado
     */
    public void setCodigoAssociado(String codigoAssociado) {
        this.codigoAssociado = codigoAssociado;
    }


    /**
     * Gets the codigoPlano value for this PropostaSeguroParameter.
     * 
     * @return codigoPlano
     */
    public int getCodigoPlano() {
        return codigoPlano;
    }


    /**
     * Sets the codigoPlano value for this PropostaSeguroParameter.
     * 
     * @param codigoPlano
     */
    public void setCodigoPlano(int codigoPlano) {
        this.codigoPlano = codigoPlano;
    }


    /**
     * Gets the codigoSeguro value for this PropostaSeguroParameter.
     * 
     * @return codigoSeguro
     */
    public int getCodigoSeguro() {
        return codigoSeguro;
    }


    /**
     * Sets the codigoSeguro value for this PropostaSeguroParameter.
     * 
     * @param codigoSeguro
     */
    public void setCodigoSeguro(int codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }


    /**
     * Gets the codigoTipoBeneficio value for this PropostaSeguroParameter.
     * 
     * @return codigoTipoBeneficio
     */
    public int getCodigoTipoBeneficio() {
        return codigoTipoBeneficio;
    }


    /**
     * Sets the codigoTipoBeneficio value for this PropostaSeguroParameter.
     * 
     * @param codigoTipoBeneficio
     */
    public void setCodigoTipoBeneficio(int codigoTipoBeneficio) {
        this.codigoTipoBeneficio = codigoTipoBeneficio;
    }


    /**
     * Gets the codigoTipoFormaEnvio value for this PropostaSeguroParameter.
     * 
     * @return codigoTipoFormaEnvio
     */
    public Integer getCodigoTipoFormaEnvio() {
        return codigoTipoFormaEnvio;
    }


    /**
     * Sets the codigoTipoFormaEnvio value for this PropostaSeguroParameter.
     * 
     * @param codigoTipoFormaEnvio
     */
    public void setCodigoTipoFormaEnvio(Integer codigoTipoFormaEnvio) {
        this.codigoTipoFormaEnvio = codigoTipoFormaEnvio;
    }


    /**
     * Gets the codigoTipoPagamento value for this PropostaSeguroParameter.
     * 
     * @return codigoTipoPagamento
     */
    public Integer getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }


    /**
     * Sets the codigoTipoPagamento value for this PropostaSeguroParameter.
     * 
     * @param codigoTipoPagamento
     */
    public void setCodigoTipoPagamento(Integer codigoTipoPagamento) {
        this.codigoTipoPagamento = codigoTipoPagamento;
    }


    /**
     * Gets the codigoformaPagamentoProdutoSeguro value for this PropostaSeguroParameter.
     * 
     * @return codigoformaPagamentoProdutoSeguro
     */
    public int getCodigoformaPagamentoProdutoSeguro() {
        return codigoformaPagamentoProdutoSeguro;
    }


    /**
     * Sets the codigoformaPagamentoProdutoSeguro value for this PropostaSeguroParameter.
     * 
     * @param codigoformaPagamentoProdutoSeguro
     */
    public void setCodigoformaPagamentoProdutoSeguro(int codigoformaPagamentoProdutoSeguro) {
        this.codigoformaPagamentoProdutoSeguro = codigoformaPagamentoProdutoSeguro;
    }


    /**
     * Gets the cpfOperadorVendas value for this PropostaSeguroParameter.
     * 
     * @return cpfOperadorVendas
     */
    public String getCpfOperadorVendas() {
        return cpfOperadorVendas;
    }


    /**
     * Sets the cpfOperadorVendas value for this PropostaSeguroParameter.
     * 
     * @param cpfOperadorVendas
     */
    public void setCpfOperadorVendas(String cpfOperadorVendas) {
        this.cpfOperadorVendas = cpfOperadorVendas;
    }


    /**
     * Gets the dataHoraAdesao value for this PropostaSeguroParameter.
     * 
     * @return dataHoraAdesao
     */
    public String getDataHoraAdesao() {
        return dataHoraAdesao;
    }


    /**
     * Sets the dataHoraAdesao value for this PropostaSeguroParameter.
     * 
     * @param dataHoraAdesao
     */
    public void setDataHoraAdesao(String dataHoraAdesao) {
        this.dataHoraAdesao = dataHoraAdesao;
    }


    /**
     * Gets the loginConsig value for this PropostaSeguroParameter.
     * 
     * @return loginConsig
     */
    public String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this PropostaSeguroParameter.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the matricula value for this PropostaSeguroParameter.
     * 
     * @return matricula
     */
    public String getMatricula() {
        return matricula;
    }


    /**
     * Sets the matricula value for this PropostaSeguroParameter.
     * 
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    /**
     * Gets the nomeSocial value for this PropostaSeguroParameter.
     * 
     * @return nomeSocial
     */
    public String getNomeSocial() {
        return nomeSocial;
    }


    /**
     * Sets the nomeSocial value for this PropostaSeguroParameter.
     * 
     * @param nomeSocial
     */
    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }


    /**
     * Gets the numeroInternoConta value for this PropostaSeguroParameter.
     * 
     * @return numeroInternoConta
     */
    public int getNumeroInternoConta() {
        return numeroInternoConta;
    }


    /**
     * Sets the numeroInternoConta value for this PropostaSeguroParameter.
     * 
     * @param numeroInternoConta
     */
    public void setNumeroInternoConta(int numeroInternoConta) {
        this.numeroInternoConta = numeroInternoConta;
    }


    /**
     * Gets the protocoloMultiProdutos value for this PropostaSeguroParameter.
     * 
     * @return protocoloMultiProdutos
     */
    public Long getProtocoloMultiProdutos() {
        return protocoloMultiProdutos;
    }


    /**
     * Sets the protocoloMultiProdutos value for this PropostaSeguroParameter.
     * 
     * @param protocoloMultiProdutos
     */
    public void setProtocoloMultiProdutos(Long protocoloMultiProdutos) {
        this.protocoloMultiProdutos = protocoloMultiProdutos;
    }


    /**
     * Gets the renda value for this PropostaSeguroParameter.
     * 
     * @return renda
     */
    public double getRenda() {
        return renda;
    }


    /**
     * Sets the renda value for this PropostaSeguroParameter.
     * 
     * @param renda
     */
    public void setRenda(double renda) {
        this.renda = renda;
    }


    /**
     * Gets the senhaConsig value for this PropostaSeguroParameter.
     * 
     * @return senhaConsig
     */
    public String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this PropostaSeguroParameter.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PropostaSeguroParameter)) return false;
        PropostaSeguroParameter other = (PropostaSeguroParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.cliente==null && other.getCliente()==null) || 
             (this.cliente!=null &&
              this.cliente.equals(other.getCliente()))) &&
            this.codLoja == other.getCodLoja() &&
            ((this.codigoAssociado==null && other.getCodigoAssociado()==null) || 
             (this.codigoAssociado!=null &&
              this.codigoAssociado.equals(other.getCodigoAssociado()))) &&
            this.codigoPlano == other.getCodigoPlano() &&
            this.codigoSeguro == other.getCodigoSeguro() &&
            this.codigoTipoBeneficio == other.getCodigoTipoBeneficio() &&
            ((this.codigoTipoFormaEnvio==null && other.getCodigoTipoFormaEnvio()==null) || 
             (this.codigoTipoFormaEnvio!=null &&
              this.codigoTipoFormaEnvio.equals(other.getCodigoTipoFormaEnvio()))) &&
            ((this.codigoTipoPagamento==null && other.getCodigoTipoPagamento()==null) || 
             (this.codigoTipoPagamento!=null &&
              this.codigoTipoPagamento.equals(other.getCodigoTipoPagamento()))) &&
            this.codigoformaPagamentoProdutoSeguro == other.getCodigoformaPagamentoProdutoSeguro() &&
            ((this.cpfOperadorVendas==null && other.getCpfOperadorVendas()==null) || 
             (this.cpfOperadorVendas!=null &&
              this.cpfOperadorVendas.equals(other.getCpfOperadorVendas()))) &&
            ((this.dataHoraAdesao==null && other.getDataHoraAdesao()==null) || 
             (this.dataHoraAdesao!=null &&
              this.dataHoraAdesao.equals(other.getDataHoraAdesao()))) &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.matricula==null && other.getMatricula()==null) || 
             (this.matricula!=null &&
              this.matricula.equals(other.getMatricula()))) &&
            ((this.nomeSocial==null && other.getNomeSocial()==null) || 
             (this.nomeSocial!=null &&
              this.nomeSocial.equals(other.getNomeSocial()))) &&
            this.numeroInternoConta == other.getNumeroInternoConta() &&
            ((this.protocoloMultiProdutos==null && other.getProtocoloMultiProdutos()==null) || 
             (this.protocoloMultiProdutos!=null &&
              this.protocoloMultiProdutos.equals(other.getProtocoloMultiProdutos()))) &&
            this.renda == other.getRenda() &&
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
        if (getCliente() != null) {
            _hashCode += getCliente().hashCode();
        }
        _hashCode += getCodLoja();
        if (getCodigoAssociado() != null) {
            _hashCode += getCodigoAssociado().hashCode();
        }
        _hashCode += getCodigoPlano();
        _hashCode += getCodigoSeguro();
        _hashCode += getCodigoTipoBeneficio();
        if (getCodigoTipoFormaEnvio() != null) {
            _hashCode += getCodigoTipoFormaEnvio().hashCode();
        }
        if (getCodigoTipoPagamento() != null) {
            _hashCode += getCodigoTipoPagamento().hashCode();
        }
        _hashCode += getCodigoformaPagamentoProdutoSeguro();
        if (getCpfOperadorVendas() != null) {
            _hashCode += getCpfOperadorVendas().hashCode();
        }
        if (getDataHoraAdesao() != null) {
            _hashCode += getDataHoraAdesao().hashCode();
        }
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getMatricula() != null) {
            _hashCode += getMatricula().hashCode();
        }
        if (getNomeSocial() != null) {
            _hashCode += getNomeSocial().hashCode();
        }
        _hashCode += getNumeroInternoConta();
        if (getProtocoloMultiProdutos() != null) {
            _hashCode += getProtocoloMultiProdutos().hashCode();
        }
        _hashCode += new Double(getRenda()).hashCode();
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PropostaSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ClienteParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codLoja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codLoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAssociado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAssociado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoBeneficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoBeneficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoFormaEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoFormaEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoformaPagamentoProdutoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoformaPagamentoProdutoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfOperadorVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfOperadorVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataHoraAdesao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataHoraAdesao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matricula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "matricula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeSocial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeSocial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroInternoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroInternoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protocoloMultiProdutos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "protocoloMultiProdutos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("renda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "renda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
