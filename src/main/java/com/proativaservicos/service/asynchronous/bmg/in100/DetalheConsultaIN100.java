/**
 * DetalheConsultaIN100.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.in100;

public class DetalheConsultaIN100  extends com.proativaservicos.service.asynchronous.bmg.in100.WebServiceParameter  implements java.io.Serializable {
    private java.lang.String cidade;

    private com.proativaservicos.service.asynchronous.bmg.in100.DetalheDadosConsultaIN100 consulta;

    private java.lang.String cpf;

    private java.util.Calendar dataCadastroConsulta;

    private java.util.Calendar dataNascimento;

    private java.lang.String detalhe;

    private java.lang.String matricula;

    private java.lang.String nome;

    private java.lang.Long numeroSolicitacao;

    private java.lang.String sms;

    private java.lang.String telefone;

    private java.lang.String token;

    public DetalheConsultaIN100() {
    }

    public DetalheConsultaIN100(
           java.lang.String login,
           java.lang.String senha,
           java.lang.String cidade,
           com.proativaservicos.service.asynchronous.bmg.in100.DetalheDadosConsultaIN100 consulta,
           java.lang.String cpf,
           java.util.Calendar dataCadastroConsulta,
           java.util.Calendar dataNascimento,
           java.lang.String detalhe,
           java.lang.String matricula,
           java.lang.String nome,
           java.lang.Long numeroSolicitacao,
           java.lang.String sms,
           java.lang.String telefone,
           java.lang.String token) {
        super(
            login,
            senha);
        this.cidade = cidade;
        this.consulta = consulta;
        this.cpf = cpf;
        this.dataCadastroConsulta = dataCadastroConsulta;
        this.dataNascimento = dataNascimento;
        this.detalhe = detalhe;
        this.matricula = matricula;
        this.nome = nome;
        this.numeroSolicitacao = numeroSolicitacao;
        this.sms = sms;
        this.telefone = telefone;
        this.token = token;
    }


    /**
     * Gets the cidade value for this DetalheConsultaIN100.
     * 
     * @return cidade
     */
    public java.lang.String getCidade() {
        return cidade;
    }


    /**
     * Sets the cidade value for this DetalheConsultaIN100.
     * 
     * @param cidade
     */
    public void setCidade(java.lang.String cidade) {
        this.cidade = cidade;
    }


    /**
     * Gets the consulta value for this DetalheConsultaIN100.
     * 
     * @return consulta
     */
    public com.proativaservicos.service.asynchronous.bmg.in100.DetalheDadosConsultaIN100 getConsulta() {
        return consulta;
    }


    /**
     * Sets the consulta value for this DetalheConsultaIN100.
     * 
     * @param consulta
     */
    public void setConsulta(com.proativaservicos.service.asynchronous.bmg.in100.DetalheDadosConsultaIN100 consulta) {
        this.consulta = consulta;
    }


    /**
     * Gets the cpf value for this DetalheConsultaIN100.
     * 
     * @return cpf
     */
    public java.lang.String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this DetalheConsultaIN100.
     * 
     * @param cpf
     */
    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the dataCadastroConsulta value for this DetalheConsultaIN100.
     * 
     * @return dataCadastroConsulta
     */
    public java.util.Calendar getDataCadastroConsulta() {
        return dataCadastroConsulta;
    }


    /**
     * Sets the dataCadastroConsulta value for this DetalheConsultaIN100.
     * 
     * @param dataCadastroConsulta
     */
    public void setDataCadastroConsulta(java.util.Calendar dataCadastroConsulta) {
        this.dataCadastroConsulta = dataCadastroConsulta;
    }


    /**
     * Gets the dataNascimento value for this DetalheConsultaIN100.
     * 
     * @return dataNascimento
     */
    public java.util.Calendar getDataNascimento() {
        return dataNascimento;
    }


    /**
     * Sets the dataNascimento value for this DetalheConsultaIN100.
     * 
     * @param dataNascimento
     */
    public void setDataNascimento(java.util.Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    /**
     * Gets the detalhe value for this DetalheConsultaIN100.
     * 
     * @return detalhe
     */
    public java.lang.String getDetalhe() {
        return detalhe;
    }


    /**
     * Sets the detalhe value for this DetalheConsultaIN100.
     * 
     * @param detalhe
     */
    public void setDetalhe(java.lang.String detalhe) {
        this.detalhe = detalhe;
    }


    /**
     * Gets the matricula value for this DetalheConsultaIN100.
     * 
     * @return matricula
     */
    public java.lang.String getMatricula() {
        return matricula;
    }


    /**
     * Sets the matricula value for this DetalheConsultaIN100.
     * 
     * @param matricula
     */
    public void setMatricula(java.lang.String matricula) {
        this.matricula = matricula;
    }


    /**
     * Gets the nome value for this DetalheConsultaIN100.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this DetalheConsultaIN100.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the numeroSolicitacao value for this DetalheConsultaIN100.
     * 
     * @return numeroSolicitacao
     */
    public java.lang.Long getNumeroSolicitacao() {
        return numeroSolicitacao;
    }


    /**
     * Sets the numeroSolicitacao value for this DetalheConsultaIN100.
     * 
     * @param numeroSolicitacao
     */
    public void setNumeroSolicitacao(java.lang.Long numeroSolicitacao) {
        this.numeroSolicitacao = numeroSolicitacao;
    }


    /**
     * Gets the sms value for this DetalheConsultaIN100.
     * 
     * @return sms
     */
    public java.lang.String getSms() {
        return sms;
    }


    /**
     * Sets the sms value for this DetalheConsultaIN100.
     * 
     * @param sms
     */
    public void setSms(java.lang.String sms) {
        this.sms = sms;
    }


    /**
     * Gets the telefone value for this DetalheConsultaIN100.
     * 
     * @return telefone
     */
    public java.lang.String getTelefone() {
        return telefone;
    }


    /**
     * Sets the telefone value for this DetalheConsultaIN100.
     * 
     * @param telefone
     */
    public void setTelefone(java.lang.String telefone) {
        this.telefone = telefone;
    }


    /**
     * Gets the token value for this DetalheConsultaIN100.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this DetalheConsultaIN100.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DetalheConsultaIN100)) return false;
        DetalheConsultaIN100 other = (DetalheConsultaIN100) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.cidade==null && other.getCidade()==null) || 
             (this.cidade!=null &&
              this.cidade.equals(other.getCidade()))) &&
            ((this.consulta==null && other.getConsulta()==null) || 
             (this.consulta!=null &&
              this.consulta.equals(other.getConsulta()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.dataCadastroConsulta==null && other.getDataCadastroConsulta()==null) || 
             (this.dataCadastroConsulta!=null &&
              this.dataCadastroConsulta.equals(other.getDataCadastroConsulta()))) &&
            ((this.dataNascimento==null && other.getDataNascimento()==null) || 
             (this.dataNascimento!=null &&
              this.dataNascimento.equals(other.getDataNascimento()))) &&
            ((this.detalhe==null && other.getDetalhe()==null) || 
             (this.detalhe!=null &&
              this.detalhe.equals(other.getDetalhe()))) &&
            ((this.matricula==null && other.getMatricula()==null) || 
             (this.matricula!=null &&
              this.matricula.equals(other.getMatricula()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.numeroSolicitacao==null && other.getNumeroSolicitacao()==null) || 
             (this.numeroSolicitacao!=null &&
              this.numeroSolicitacao.equals(other.getNumeroSolicitacao()))) &&
            ((this.sms==null && other.getSms()==null) || 
             (this.sms!=null &&
              this.sms.equals(other.getSms()))) &&
            ((this.telefone==null && other.getTelefone()==null) || 
             (this.telefone!=null &&
              this.telefone.equals(other.getTelefone()))) &&
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken())));
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
        if (getCidade() != null) {
            _hashCode += getCidade().hashCode();
        }
        if (getConsulta() != null) {
            _hashCode += getConsulta().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getDataCadastroConsulta() != null) {
            _hashCode += getDataCadastroConsulta().hashCode();
        }
        if (getDataNascimento() != null) {
            _hashCode += getDataNascimento().hashCode();
        }
        if (getDetalhe() != null) {
            _hashCode += getDetalhe().hashCode();
        }
        if (getMatricula() != null) {
            _hashCode += getMatricula().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getNumeroSolicitacao() != null) {
            _hashCode += getNumeroSolicitacao().hashCode();
        }
        if (getSms() != null) {
            _hashCode += getSms().hashCode();
        }
        if (getTelefone() != null) {
            _hashCode += getTelefone().hashCode();
        }
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DetalheConsultaIN100.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DetalheConsultaIN100"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consulta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DetalheDadosConsultaIN100"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCadastroConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataCadastroConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataNascimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalhe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "detalhe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matricula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "matricula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSolicitacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroSolicitacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sms");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sms"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
