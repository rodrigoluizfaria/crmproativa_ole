/**
 * ClienteParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class ClienteParameter  extends com.proativaservicos.service.asynchronous.bmg.refinanciamento.AbstractWebServicesParameter  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter celular1;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter celular2;

    private java.lang.String cidadeNascimento;

    private java.lang.String cpf;

    private java.util.Calendar dataNascimento;

    private java.lang.String email;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.EnderecoParamter endereco;

    private java.lang.String estadoCivil;

    private java.lang.String grauInstrucao;

    private java.lang.Integer grauParentescoPEP;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.IdentidadeParameter identidade;

    private java.lang.String nacionalidade;

    private java.lang.String nome;

    private java.lang.String nomeConjuge;

    private java.lang.String nomeMae;

    private java.lang.String nomePai;

    private boolean pessoaPoliticamenteExposta;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.Profissao profissao;

    private java.lang.String sexo;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter telefone;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter telefoneFixoOuCelular;

    private java.lang.String ufNascimento;

    public ClienteParameter() {
    }

    public ClienteParameter(
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter celular1,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter celular2,
           java.lang.String cidadeNascimento,
           java.lang.String cpf,
           java.util.Calendar dataNascimento,
           java.lang.String email,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.EnderecoParamter endereco,
           java.lang.String estadoCivil,
           java.lang.String grauInstrucao,
           java.lang.Integer grauParentescoPEP,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.IdentidadeParameter identidade,
           java.lang.String nacionalidade,
           java.lang.String nome,
           java.lang.String nomeConjuge,
           java.lang.String nomeMae,
           java.lang.String nomePai,
           boolean pessoaPoliticamenteExposta,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.Profissao profissao,
           java.lang.String sexo,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter telefone,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter telefoneFixoOuCelular,
           java.lang.String ufNascimento) {
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.cidadeNascimento = cidadeNascimento;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.endereco = endereco;
        this.estadoCivil = estadoCivil;
        this.grauInstrucao = grauInstrucao;
        this.grauParentescoPEP = grauParentescoPEP;
        this.identidade = identidade;
        this.nacionalidade = nacionalidade;
        this.nome = nome;
        this.nomeConjuge = nomeConjuge;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.pessoaPoliticamenteExposta = pessoaPoliticamenteExposta;
        this.profissao = profissao;
        this.sexo = sexo;
        this.telefone = telefone;
        this.telefoneFixoOuCelular = telefoneFixoOuCelular;
        this.ufNascimento = ufNascimento;
    }


    /**
     * Gets the celular1 value for this ClienteParameter.
     * 
     * @return celular1
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter getCelular1() {
        return celular1;
    }


    /**
     * Sets the celular1 value for this ClienteParameter.
     * 
     * @param celular1
     */
    public void setCelular1(com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter celular1) {
        this.celular1 = celular1;
    }


    /**
     * Gets the celular2 value for this ClienteParameter.
     * 
     * @return celular2
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter getCelular2() {
        return celular2;
    }


    /**
     * Sets the celular2 value for this ClienteParameter.
     * 
     * @param celular2
     */
    public void setCelular2(com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter celular2) {
        this.celular2 = celular2;
    }


    /**
     * Gets the cidadeNascimento value for this ClienteParameter.
     * 
     * @return cidadeNascimento
     */
    public java.lang.String getCidadeNascimento() {
        return cidadeNascimento;
    }


    /**
     * Sets the cidadeNascimento value for this ClienteParameter.
     * 
     * @param cidadeNascimento
     */
    public void setCidadeNascimento(java.lang.String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }


    /**
     * Gets the cpf value for this ClienteParameter.
     * 
     * @return cpf
     */
    public java.lang.String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ClienteParameter.
     * 
     * @param cpf
     */
    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the dataNascimento value for this ClienteParameter.
     * 
     * @return dataNascimento
     */
    public java.util.Calendar getDataNascimento() {
        return dataNascimento;
    }


    /**
     * Sets the dataNascimento value for this ClienteParameter.
     * 
     * @param dataNascimento
     */
    public void setDataNascimento(java.util.Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    /**
     * Gets the email value for this ClienteParameter.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this ClienteParameter.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the endereco value for this ClienteParameter.
     * 
     * @return endereco
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.EnderecoParamter getEndereco() {
        return endereco;
    }


    /**
     * Sets the endereco value for this ClienteParameter.
     * 
     * @param endereco
     */
    public void setEndereco(com.proativaservicos.service.asynchronous.bmg.refinanciamento.EnderecoParamter endereco) {
        this.endereco = endereco;
    }


    /**
     * Gets the estadoCivil value for this ClienteParameter.
     * 
     * @return estadoCivil
     */
    public java.lang.String getEstadoCivil() {
        return estadoCivil;
    }


    /**
     * Sets the estadoCivil value for this ClienteParameter.
     * 
     * @param estadoCivil
     */
    public void setEstadoCivil(java.lang.String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }


    /**
     * Gets the grauInstrucao value for this ClienteParameter.
     * 
     * @return grauInstrucao
     */
    public java.lang.String getGrauInstrucao() {
        return grauInstrucao;
    }


    /**
     * Sets the grauInstrucao value for this ClienteParameter.
     * 
     * @param grauInstrucao
     */
    public void setGrauInstrucao(java.lang.String grauInstrucao) {
        this.grauInstrucao = grauInstrucao;
    }


    /**
     * Gets the grauParentescoPEP value for this ClienteParameter.
     * 
     * @return grauParentescoPEP
     */
    public java.lang.Integer getGrauParentescoPEP() {
        return grauParentescoPEP;
    }


    /**
     * Sets the grauParentescoPEP value for this ClienteParameter.
     * 
     * @param grauParentescoPEP
     */
    public void setGrauParentescoPEP(java.lang.Integer grauParentescoPEP) {
        this.grauParentescoPEP = grauParentescoPEP;
    }


    /**
     * Gets the identidade value for this ClienteParameter.
     * 
     * @return identidade
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.IdentidadeParameter getIdentidade() {
        return identidade;
    }


    /**
     * Sets the identidade value for this ClienteParameter.
     * 
     * @param identidade
     */
    public void setIdentidade(com.proativaservicos.service.asynchronous.bmg.refinanciamento.IdentidadeParameter identidade) {
        this.identidade = identidade;
    }


    /**
     * Gets the nacionalidade value for this ClienteParameter.
     * 
     * @return nacionalidade
     */
    public java.lang.String getNacionalidade() {
        return nacionalidade;
    }


    /**
     * Sets the nacionalidade value for this ClienteParameter.
     * 
     * @param nacionalidade
     */
    public void setNacionalidade(java.lang.String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }


    /**
     * Gets the nome value for this ClienteParameter.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this ClienteParameter.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the nomeConjuge value for this ClienteParameter.
     * 
     * @return nomeConjuge
     */
    public java.lang.String getNomeConjuge() {
        return nomeConjuge;
    }


    /**
     * Sets the nomeConjuge value for this ClienteParameter.
     * 
     * @param nomeConjuge
     */
    public void setNomeConjuge(java.lang.String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }


    /**
     * Gets the nomeMae value for this ClienteParameter.
     * 
     * @return nomeMae
     */
    public java.lang.String getNomeMae() {
        return nomeMae;
    }


    /**
     * Sets the nomeMae value for this ClienteParameter.
     * 
     * @param nomeMae
     */
    public void setNomeMae(java.lang.String nomeMae) {
        this.nomeMae = nomeMae;
    }


    /**
     * Gets the nomePai value for this ClienteParameter.
     * 
     * @return nomePai
     */
    public java.lang.String getNomePai() {
        return nomePai;
    }


    /**
     * Sets the nomePai value for this ClienteParameter.
     * 
     * @param nomePai
     */
    public void setNomePai(java.lang.String nomePai) {
        this.nomePai = nomePai;
    }


    /**
     * Gets the pessoaPoliticamenteExposta value for this ClienteParameter.
     * 
     * @return pessoaPoliticamenteExposta
     */
    public boolean isPessoaPoliticamenteExposta() {
        return pessoaPoliticamenteExposta;
    }


    /**
     * Sets the pessoaPoliticamenteExposta value for this ClienteParameter.
     * 
     * @param pessoaPoliticamenteExposta
     */
    public void setPessoaPoliticamenteExposta(boolean pessoaPoliticamenteExposta) {
        this.pessoaPoliticamenteExposta = pessoaPoliticamenteExposta;
    }


    /**
     * Gets the profissao value for this ClienteParameter.
     * 
     * @return profissao
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.Profissao getProfissao() {
        return profissao;
    }


    /**
     * Sets the profissao value for this ClienteParameter.
     * 
     * @param profissao
     */
    public void setProfissao(com.proativaservicos.service.asynchronous.bmg.refinanciamento.Profissao profissao) {
        this.profissao = profissao;
    }


    /**
     * Gets the sexo value for this ClienteParameter.
     * 
     * @return sexo
     */
    public java.lang.String getSexo() {
        return sexo;
    }


    /**
     * Sets the sexo value for this ClienteParameter.
     * 
     * @param sexo
     */
    public void setSexo(java.lang.String sexo) {
        this.sexo = sexo;
    }


    /**
     * Gets the telefone value for this ClienteParameter.
     * 
     * @return telefone
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter getTelefone() {
        return telefone;
    }


    /**
     * Sets the telefone value for this ClienteParameter.
     * 
     * @param telefone
     */
    public void setTelefone(com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter telefone) {
        this.telefone = telefone;
    }


    /**
     * Gets the telefoneFixoOuCelular value for this ClienteParameter.
     * 
     * @return telefoneFixoOuCelular
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter getTelefoneFixoOuCelular() {
        return telefoneFixoOuCelular;
    }


    /**
     * Sets the telefoneFixoOuCelular value for this ClienteParameter.
     * 
     * @param telefoneFixoOuCelular
     */
    public void setTelefoneFixoOuCelular(com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter telefoneFixoOuCelular) {
        this.telefoneFixoOuCelular = telefoneFixoOuCelular;
    }


    /**
     * Gets the ufNascimento value for this ClienteParameter.
     * 
     * @return ufNascimento
     */
    public java.lang.String getUfNascimento() {
        return ufNascimento;
    }


    /**
     * Sets the ufNascimento value for this ClienteParameter.
     * 
     * @param ufNascimento
     */
    public void setUfNascimento(java.lang.String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClienteParameter)) return false;
        ClienteParameter other = (ClienteParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.celular1==null && other.getCelular1()==null) || 
             (this.celular1!=null &&
              this.celular1.equals(other.getCelular1()))) &&
            ((this.celular2==null && other.getCelular2()==null) || 
             (this.celular2!=null &&
              this.celular2.equals(other.getCelular2()))) &&
            ((this.cidadeNascimento==null && other.getCidadeNascimento()==null) || 
             (this.cidadeNascimento!=null &&
              this.cidadeNascimento.equals(other.getCidadeNascimento()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.dataNascimento==null && other.getDataNascimento()==null) || 
             (this.dataNascimento!=null &&
              this.dataNascimento.equals(other.getDataNascimento()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.endereco==null && other.getEndereco()==null) || 
             (this.endereco!=null &&
              this.endereco.equals(other.getEndereco()))) &&
            ((this.estadoCivil==null && other.getEstadoCivil()==null) || 
             (this.estadoCivil!=null &&
              this.estadoCivil.equals(other.getEstadoCivil()))) &&
            ((this.grauInstrucao==null && other.getGrauInstrucao()==null) || 
             (this.grauInstrucao!=null &&
              this.grauInstrucao.equals(other.getGrauInstrucao()))) &&
            ((this.grauParentescoPEP==null && other.getGrauParentescoPEP()==null) || 
             (this.grauParentescoPEP!=null &&
              this.grauParentescoPEP.equals(other.getGrauParentescoPEP()))) &&
            ((this.identidade==null && other.getIdentidade()==null) || 
             (this.identidade!=null &&
              this.identidade.equals(other.getIdentidade()))) &&
            ((this.nacionalidade==null && other.getNacionalidade()==null) || 
             (this.nacionalidade!=null &&
              this.nacionalidade.equals(other.getNacionalidade()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.nomeConjuge==null && other.getNomeConjuge()==null) || 
             (this.nomeConjuge!=null &&
              this.nomeConjuge.equals(other.getNomeConjuge()))) &&
            ((this.nomeMae==null && other.getNomeMae()==null) || 
             (this.nomeMae!=null &&
              this.nomeMae.equals(other.getNomeMae()))) &&
            ((this.nomePai==null && other.getNomePai()==null) || 
             (this.nomePai!=null &&
              this.nomePai.equals(other.getNomePai()))) &&
            this.pessoaPoliticamenteExposta == other.isPessoaPoliticamenteExposta() &&
            ((this.profissao==null && other.getProfissao()==null) || 
             (this.profissao!=null &&
              this.profissao.equals(other.getProfissao()))) &&
            ((this.sexo==null && other.getSexo()==null) || 
             (this.sexo!=null &&
              this.sexo.equals(other.getSexo()))) &&
            ((this.telefone==null && other.getTelefone()==null) || 
             (this.telefone!=null &&
              this.telefone.equals(other.getTelefone()))) &&
            ((this.telefoneFixoOuCelular==null && other.getTelefoneFixoOuCelular()==null) || 
             (this.telefoneFixoOuCelular!=null &&
              this.telefoneFixoOuCelular.equals(other.getTelefoneFixoOuCelular()))) &&
            ((this.ufNascimento==null && other.getUfNascimento()==null) || 
             (this.ufNascimento!=null &&
              this.ufNascimento.equals(other.getUfNascimento())));
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
        if (getCelular1() != null) {
            _hashCode += getCelular1().hashCode();
        }
        if (getCelular2() != null) {
            _hashCode += getCelular2().hashCode();
        }
        if (getCidadeNascimento() != null) {
            _hashCode += getCidadeNascimento().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getDataNascimento() != null) {
            _hashCode += getDataNascimento().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getEndereco() != null) {
            _hashCode += getEndereco().hashCode();
        }
        if (getEstadoCivil() != null) {
            _hashCode += getEstadoCivil().hashCode();
        }
        if (getGrauInstrucao() != null) {
            _hashCode += getGrauInstrucao().hashCode();
        }
        if (getGrauParentescoPEP() != null) {
            _hashCode += getGrauParentescoPEP().hashCode();
        }
        if (getIdentidade() != null) {
            _hashCode += getIdentidade().hashCode();
        }
        if (getNacionalidade() != null) {
            _hashCode += getNacionalidade().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getNomeConjuge() != null) {
            _hashCode += getNomeConjuge().hashCode();
        }
        if (getNomeMae() != null) {
            _hashCode += getNomeMae().hashCode();
        }
        if (getNomePai() != null) {
            _hashCode += getNomePai().hashCode();
        }
        _hashCode += (isPessoaPoliticamenteExposta() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getProfissao() != null) {
            _hashCode += getProfissao().hashCode();
        }
        if (getSexo() != null) {
            _hashCode += getSexo().hashCode();
        }
        if (getTelefone() != null) {
            _hashCode += getTelefone().hashCode();
        }
        if (getTelefoneFixoOuCelular() != null) {
            _hashCode += getTelefoneFixoOuCelular().hashCode();
        }
        if (getUfNascimento() != null) {
            _hashCode += getUfNascimento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClienteParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ClienteParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("celular1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "celular1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("celular2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "celular2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cidadeNascimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cidadeNascimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataNascimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endereco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endereco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EnderecoParamter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoCivil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoCivil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grauInstrucao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grauInstrucao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grauParentescoPEP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grauParentescoPEP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "IdentidadeParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nacionalidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nacionalidade"));
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
        elemField.setFieldName("nomeConjuge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeConjuge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeMae");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeMae"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomePai");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomePai"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pessoaPoliticamenteExposta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pessoaPoliticamenteExposta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profissao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profissao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Profissao"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sexo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sexo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefoneFixoOuCelular");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefoneFixoOuCelular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ufNascimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ufNascimento"));
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
