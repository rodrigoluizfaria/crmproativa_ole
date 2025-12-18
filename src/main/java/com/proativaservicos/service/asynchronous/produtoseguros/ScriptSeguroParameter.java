/**
 * ScriptSeguroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ScriptSeguroParameter  extends com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter  implements java.io.Serializable {
    private int codLoja;

    private int codigoPlano;

    private int codigoSeguro;

    private int codigoTipoFormaEnvio;

    private Integer codigoTipoPagamento;

    private String cpf;

    private int formaPagamentoProdutoSeguro;

    private String loginConsig;

    private String matricula;

    private int numeroInternoConta;

    private double renda;

    private String senhaConsig;

    private boolean upgrade;

    public ScriptSeguroParameter() {
    }

    public ScriptSeguroParameter(
           String login,
           String senha,
           int codLoja,
           int codigoPlano,
           int codigoSeguro,
           int codigoTipoFormaEnvio,
           Integer codigoTipoPagamento,
           String cpf,
           int formaPagamentoProdutoSeguro,
           String loginConsig,
           String matricula,
           int numeroInternoConta,
           double renda,
           String senhaConsig,
           boolean upgrade) {
        super(
            login,
            senha);
        this.codLoja = codLoja;
        this.codigoPlano = codigoPlano;
        this.codigoSeguro = codigoSeguro;
        this.codigoTipoFormaEnvio = codigoTipoFormaEnvio;
        this.codigoTipoPagamento = codigoTipoPagamento;
        this.cpf = cpf;
        this.formaPagamentoProdutoSeguro = formaPagamentoProdutoSeguro;
        this.loginConsig = loginConsig;
        this.matricula = matricula;
        this.numeroInternoConta = numeroInternoConta;
        this.renda = renda;
        this.senhaConsig = senhaConsig;
        this.upgrade = upgrade;
    }


    /**
     * Gets the codLoja value for this ScriptSeguroParameter.
     * 
     * @return codLoja
     */
    public int getCodLoja() {
        return codLoja;
    }


    /**
     * Sets the codLoja value for this ScriptSeguroParameter.
     * 
     * @param codLoja
     */
    public void setCodLoja(int codLoja) {
        this.codLoja = codLoja;
    }


    /**
     * Gets the codigoPlano value for this ScriptSeguroParameter.
     * 
     * @return codigoPlano
     */
    public int getCodigoPlano() {
        return codigoPlano;
    }


    /**
     * Sets the codigoPlano value for this ScriptSeguroParameter.
     * 
     * @param codigoPlano
     */
    public void setCodigoPlano(int codigoPlano) {
        this.codigoPlano = codigoPlano;
    }


    /**
     * Gets the codigoSeguro value for this ScriptSeguroParameter.
     * 
     * @return codigoSeguro
     */
    public int getCodigoSeguro() {
        return codigoSeguro;
    }


    /**
     * Sets the codigoSeguro value for this ScriptSeguroParameter.
     * 
     * @param codigoSeguro
     */
    public void setCodigoSeguro(int codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }


    /**
     * Gets the codigoTipoFormaEnvio value for this ScriptSeguroParameter.
     * 
     * @return codigoTipoFormaEnvio
     */
    public int getCodigoTipoFormaEnvio() {
        return codigoTipoFormaEnvio;
    }


    /**
     * Sets the codigoTipoFormaEnvio value for this ScriptSeguroParameter.
     * 
     * @param codigoTipoFormaEnvio
     */
    public void setCodigoTipoFormaEnvio(int codigoTipoFormaEnvio) {
        this.codigoTipoFormaEnvio = codigoTipoFormaEnvio;
    }


    /**
     * Gets the codigoTipoPagamento value for this ScriptSeguroParameter.
     * 
     * @return codigoTipoPagamento
     */
    public Integer getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }


    /**
     * Sets the codigoTipoPagamento value for this ScriptSeguroParameter.
     * 
     * @param codigoTipoPagamento
     */
    public void setCodigoTipoPagamento(Integer codigoTipoPagamento) {
        this.codigoTipoPagamento = codigoTipoPagamento;
    }


    /**
     * Gets the cpf value for this ScriptSeguroParameter.
     * 
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ScriptSeguroParameter.
     * 
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the formaPagamentoProdutoSeguro value for this ScriptSeguroParameter.
     * 
     * @return formaPagamentoProdutoSeguro
     */
    public int getFormaPagamentoProdutoSeguro() {
        return formaPagamentoProdutoSeguro;
    }


    /**
     * Sets the formaPagamentoProdutoSeguro value for this ScriptSeguroParameter.
     * 
     * @param formaPagamentoProdutoSeguro
     */
    public void setFormaPagamentoProdutoSeguro(int formaPagamentoProdutoSeguro) {
        this.formaPagamentoProdutoSeguro = formaPagamentoProdutoSeguro;
    }


    /**
     * Gets the loginConsig value for this ScriptSeguroParameter.
     * 
     * @return loginConsig
     */
    public String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this ScriptSeguroParameter.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the matricula value for this ScriptSeguroParameter.
     * 
     * @return matricula
     */
    public String getMatricula() {
        return matricula;
    }


    /**
     * Sets the matricula value for this ScriptSeguroParameter.
     * 
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    /**
     * Gets the numeroInternoConta value for this ScriptSeguroParameter.
     * 
     * @return numeroInternoConta
     */
    public int getNumeroInternoConta() {
        return numeroInternoConta;
    }


    /**
     * Sets the numeroInternoConta value for this ScriptSeguroParameter.
     * 
     * @param numeroInternoConta
     */
    public void setNumeroInternoConta(int numeroInternoConta) {
        this.numeroInternoConta = numeroInternoConta;
    }


    /**
     * Gets the renda value for this ScriptSeguroParameter.
     * 
     * @return renda
     */
    public double getRenda() {
        return renda;
    }


    /**
     * Sets the renda value for this ScriptSeguroParameter.
     * 
     * @param renda
     */
    public void setRenda(double renda) {
        this.renda = renda;
    }


    /**
     * Gets the senhaConsig value for this ScriptSeguroParameter.
     * 
     * @return senhaConsig
     */
    public String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this ScriptSeguroParameter.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the upgrade value for this ScriptSeguroParameter.
     * 
     * @return upgrade
     */
    public boolean isUpgrade() {
        return upgrade;
    }


    /**
     * Sets the upgrade value for this ScriptSeguroParameter.
     * 
     * @param upgrade
     */
    public void setUpgrade(boolean upgrade) {
        this.upgrade = upgrade;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ScriptSeguroParameter)) return false;
        ScriptSeguroParameter other = (ScriptSeguroParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.codLoja == other.getCodLoja() &&
            this.codigoPlano == other.getCodigoPlano() &&
            this.codigoSeguro == other.getCodigoSeguro() &&
            this.codigoTipoFormaEnvio == other.getCodigoTipoFormaEnvio() &&
            ((this.codigoTipoPagamento==null && other.getCodigoTipoPagamento()==null) || 
             (this.codigoTipoPagamento!=null &&
              this.codigoTipoPagamento.equals(other.getCodigoTipoPagamento()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            this.formaPagamentoProdutoSeguro == other.getFormaPagamentoProdutoSeguro() &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.matricula==null && other.getMatricula()==null) || 
             (this.matricula!=null &&
              this.matricula.equals(other.getMatricula()))) &&
            this.numeroInternoConta == other.getNumeroInternoConta() &&
            this.renda == other.getRenda() &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig()))) &&
            this.upgrade == other.isUpgrade();
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
        _hashCode += getCodLoja();
        _hashCode += getCodigoPlano();
        _hashCode += getCodigoSeguro();
        _hashCode += getCodigoTipoFormaEnvio();
        if (getCodigoTipoPagamento() != null) {
            _hashCode += getCodigoTipoPagamento().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        _hashCode += getFormaPagamentoProdutoSeguro();
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getMatricula() != null) {
            _hashCode += getMatricula().hashCode();
        }
        _hashCode += getNumeroInternoConta();
        _hashCode += new Double(getRenda()).hashCode();
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        _hashCode += (isUpgrade() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ScriptSeguroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptSeguroParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codLoja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codLoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
        elemField.setFieldName("codigoTipoFormaEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoFormaEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formaPagamentoProdutoSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formaPagamentoProdutoSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
        elemField.setFieldName("numeroInternoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroInternoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upgrade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "upgrade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
