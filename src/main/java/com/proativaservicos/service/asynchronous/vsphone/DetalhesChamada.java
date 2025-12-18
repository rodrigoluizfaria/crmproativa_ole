/**
 * DetalhesChamada.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class DetalhesChamada  implements java.io.Serializable {
    private java.lang.Integer bilhetagem;

    private java.lang.String codigoConta;

    private java.util.Calendar datahoraChamada;

    private java.lang.Integer duracao;

    private java.lang.Integer id;

    private java.lang.String identificacaoOrigem;

    private java.lang.String nomeArquivo;

    private java.lang.String numeroDestino;

    private java.lang.String numeroOrigem;

    private java.lang.String status;

    public DetalhesChamada() {
    }

    public DetalhesChamada(
           java.lang.Integer bilhetagem,
           java.lang.String codigoConta,
           java.util.Calendar datahoraChamada,
           java.lang.Integer duracao,
           java.lang.Integer id,
           java.lang.String identificacaoOrigem,
           java.lang.String nomeArquivo,
           java.lang.String numeroDestino,
           java.lang.String numeroOrigem,
           java.lang.String status) {
           this.bilhetagem = bilhetagem;
           this.codigoConta = codigoConta;
           this.datahoraChamada = datahoraChamada;
           this.duracao = duracao;
           this.id = id;
           this.identificacaoOrigem = identificacaoOrigem;
           this.nomeArquivo = nomeArquivo;
           this.numeroDestino = numeroDestino;
           this.numeroOrigem = numeroOrigem;
           this.status = status;
    }


    /**
     * Gets the bilhetagem value for this DetalhesChamada.
     * 
     * @return bilhetagem
     */
    public java.lang.Integer getBilhetagem() {
        return bilhetagem;
    }


    /**
     * Sets the bilhetagem value for this DetalhesChamada.
     * 
     * @param bilhetagem
     */
    public void setBilhetagem(java.lang.Integer bilhetagem) {
        this.bilhetagem = bilhetagem;
    }


    /**
     * Gets the codigoConta value for this DetalhesChamada.
     * 
     * @return codigoConta
     */
    public java.lang.String getCodigoConta() {
        return codigoConta;
    }


    /**
     * Sets the codigoConta value for this DetalhesChamada.
     * 
     * @param codigoConta
     */
    public void setCodigoConta(java.lang.String codigoConta) {
        this.codigoConta = codigoConta;
    }


    /**
     * Gets the datahoraChamada value for this DetalhesChamada.
     * 
     * @return datahoraChamada
     */
    public java.util.Calendar getDatahoraChamada() {
        return datahoraChamada;
    }


    /**
     * Sets the datahoraChamada value for this DetalhesChamada.
     * 
     * @param datahoraChamada
     */
    public void setDatahoraChamada(java.util.Calendar datahoraChamada) {
        this.datahoraChamada = datahoraChamada;
    }


    /**
     * Gets the duracao value for this DetalhesChamada.
     * 
     * @return duracao
     */
    public java.lang.Integer getDuracao() {
        return duracao;
    }


    /**
     * Sets the duracao value for this DetalhesChamada.
     * 
     * @param duracao
     */
    public void setDuracao(java.lang.Integer duracao) {
        this.duracao = duracao;
    }


    /**
     * Gets the id value for this DetalhesChamada.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this DetalhesChamada.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the identificacaoOrigem value for this DetalhesChamada.
     * 
     * @return identificacaoOrigem
     */
    public java.lang.String getIdentificacaoOrigem() {
        return identificacaoOrigem;
    }


    /**
     * Sets the identificacaoOrigem value for this DetalhesChamada.
     * 
     * @param identificacaoOrigem
     */
    public void setIdentificacaoOrigem(java.lang.String identificacaoOrigem) {
        this.identificacaoOrigem = identificacaoOrigem;
    }


    /**
     * Gets the nomeArquivo value for this DetalhesChamada.
     * 
     * @return nomeArquivo
     */
    public java.lang.String getNomeArquivo() {
        return nomeArquivo;
    }


    /**
     * Sets the nomeArquivo value for this DetalhesChamada.
     * 
     * @param nomeArquivo
     */
    public void setNomeArquivo(java.lang.String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }


    /**
     * Gets the numeroDestino value for this DetalhesChamada.
     * 
     * @return numeroDestino
     */
    public java.lang.String getNumeroDestino() {
        return numeroDestino;
    }


    /**
     * Sets the numeroDestino value for this DetalhesChamada.
     * 
     * @param numeroDestino
     */
    public void setNumeroDestino(java.lang.String numeroDestino) {
        this.numeroDestino = numeroDestino;
    }


    /**
     * Gets the numeroOrigem value for this DetalhesChamada.
     * 
     * @return numeroOrigem
     */
    public java.lang.String getNumeroOrigem() {
        return numeroOrigem;
    }


    /**
     * Sets the numeroOrigem value for this DetalhesChamada.
     * 
     * @param numeroOrigem
     */
    public void setNumeroOrigem(java.lang.String numeroOrigem) {
        this.numeroOrigem = numeroOrigem;
    }


    /**
     * Gets the status value for this DetalhesChamada.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this DetalhesChamada.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DetalhesChamada)) return false;
        DetalhesChamada other = (DetalhesChamada) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bilhetagem==null && other.getBilhetagem()==null) || 
             (this.bilhetagem!=null &&
              this.bilhetagem.equals(other.getBilhetagem()))) &&
            ((this.codigoConta==null && other.getCodigoConta()==null) || 
             (this.codigoConta!=null &&
              this.codigoConta.equals(other.getCodigoConta()))) &&
            ((this.datahoraChamada==null && other.getDatahoraChamada()==null) || 
             (this.datahoraChamada!=null &&
              this.datahoraChamada.equals(other.getDatahoraChamada()))) &&
            ((this.duracao==null && other.getDuracao()==null) || 
             (this.duracao!=null &&
              this.duracao.equals(other.getDuracao()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.identificacaoOrigem==null && other.getIdentificacaoOrigem()==null) || 
             (this.identificacaoOrigem!=null &&
              this.identificacaoOrigem.equals(other.getIdentificacaoOrigem()))) &&
            ((this.nomeArquivo==null && other.getNomeArquivo()==null) || 
             (this.nomeArquivo!=null &&
              this.nomeArquivo.equals(other.getNomeArquivo()))) &&
            ((this.numeroDestino==null && other.getNumeroDestino()==null) || 
             (this.numeroDestino!=null &&
              this.numeroDestino.equals(other.getNumeroDestino()))) &&
            ((this.numeroOrigem==null && other.getNumeroOrigem()==null) || 
             (this.numeroOrigem!=null &&
              this.numeroOrigem.equals(other.getNumeroOrigem()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getBilhetagem() != null) {
            _hashCode += getBilhetagem().hashCode();
        }
        if (getCodigoConta() != null) {
            _hashCode += getCodigoConta().hashCode();
        }
        if (getDatahoraChamada() != null) {
            _hashCode += getDatahoraChamada().hashCode();
        }
        if (getDuracao() != null) {
            _hashCode += getDuracao().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdentificacaoOrigem() != null) {
            _hashCode += getIdentificacaoOrigem().hashCode();
        }
        if (getNomeArquivo() != null) {
            _hashCode += getNomeArquivo().hashCode();
        }
        if (getNumeroDestino() != null) {
            _hashCode += getNumeroDestino().hashCode();
        }
        if (getNumeroOrigem() != null) {
            _hashCode += getNumeroOrigem().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DetalhesChamada.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "DetalhesChamada"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bilhetagem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "bilhetagem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "codigoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datahoraChamada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "datahoraChamada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "duracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificacaoOrigem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "identificacaoOrigem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeArquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "nomeArquivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "numeroDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOrigem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "numeroOrigem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "status"));
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
