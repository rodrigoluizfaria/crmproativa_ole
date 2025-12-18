/**
 * Orgao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class Orgao  extends com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceBean  implements java.io.Serializable {
    private java.lang.String codigo;

    private int codigoEntidade;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Estabelecimento estabelecimento;

    private java.lang.String identificador;

    private java.lang.String nome;

    private java.lang.String nomeAbreviatura;

    private int sequencial;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[] unidadesPagadoras;

    public Orgao() {
    }

    public Orgao(
           java.lang.String codigo,
           int codigoEntidade,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Estabelecimento estabelecimento,
           java.lang.String identificador,
           java.lang.String nome,
           java.lang.String nomeAbreviatura,
           int sequencial,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[] unidadesPagadoras) {
        this.codigo = codigo;
        this.codigoEntidade = codigoEntidade;
        this.estabelecimento = estabelecimento;
        this.identificador = identificador;
        this.nome = nome;
        this.nomeAbreviatura = nomeAbreviatura;
        this.sequencial = sequencial;
        this.unidadesPagadoras = unidadesPagadoras;
    }


    /**
     * Gets the codigo value for this Orgao.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Orgao.
     * 
     * @param codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the codigoEntidade value for this Orgao.
     * 
     * @return codigoEntidade
     */
    public int getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this Orgao.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(int codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the estabelecimento value for this Orgao.
     * 
     * @return estabelecimento
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }


    /**
     * Sets the estabelecimento value for this Orgao.
     * 
     * @param estabelecimento
     */
    public void setEstabelecimento(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }


    /**
     * Gets the identificador value for this Orgao.
     * 
     * @return identificador
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this Orgao.
     * 
     * @param identificador
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the nome value for this Orgao.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Orgao.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the nomeAbreviatura value for this Orgao.
     * 
     * @return nomeAbreviatura
     */
    public java.lang.String getNomeAbreviatura() {
        return nomeAbreviatura;
    }


    /**
     * Sets the nomeAbreviatura value for this Orgao.
     * 
     * @param nomeAbreviatura
     */
    public void setNomeAbreviatura(java.lang.String nomeAbreviatura) {
        this.nomeAbreviatura = nomeAbreviatura;
    }


    /**
     * Gets the sequencial value for this Orgao.
     * 
     * @return sequencial
     */
    public int getSequencial() {
        return sequencial;
    }


    /**
     * Sets the sequencial value for this Orgao.
     * 
     * @param sequencial
     */
    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }


    /**
     * Gets the unidadesPagadoras value for this Orgao.
     * 
     * @return unidadesPagadoras
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[] getUnidadesPagadoras() {
        return unidadesPagadoras;
    }


    /**
     * Sets the unidadesPagadoras value for this Orgao.
     * 
     * @param unidadesPagadoras
     */
    public void setUnidadesPagadoras(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[] unidadesPagadoras) {
        this.unidadesPagadoras = unidadesPagadoras;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Orgao)) return false;
        Orgao other = (Orgao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            this.codigoEntidade == other.getCodigoEntidade() &&
            ((this.estabelecimento==null && other.getEstabelecimento()==null) || 
             (this.estabelecimento!=null &&
              this.estabelecimento.equals(other.getEstabelecimento()))) &&
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.nomeAbreviatura==null && other.getNomeAbreviatura()==null) || 
             (this.nomeAbreviatura!=null &&
              this.nomeAbreviatura.equals(other.getNomeAbreviatura()))) &&
            this.sequencial == other.getSequencial() &&
            ((this.unidadesPagadoras==null && other.getUnidadesPagadoras()==null) || 
             (this.unidadesPagadoras!=null &&
              java.util.Arrays.equals(this.unidadesPagadoras, other.getUnidadesPagadoras())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        _hashCode += getCodigoEntidade();
        if (getEstabelecimento() != null) {
            _hashCode += getEstabelecimento().hashCode();
        }
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getNomeAbreviatura() != null) {
            _hashCode += getNomeAbreviatura().hashCode();
        }
        _hashCode += getSequencial();
        if (getUnidadesPagadoras() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUnidadesPagadoras());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUnidadesPagadoras(), i);
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
        new org.apache.axis.description.TypeDesc(Orgao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://orgao.model.common.econsig.bmg.com", "Orgao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estabelecimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estabelecimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://estabelecimento.model.common.econsig.bmg.com", "Estabelecimento"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeAbreviatura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeAbreviatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadesPagadoras");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadesPagadoras"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "UnidadePagadora"));
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
