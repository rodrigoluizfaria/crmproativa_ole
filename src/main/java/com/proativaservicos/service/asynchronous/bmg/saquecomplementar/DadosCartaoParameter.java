/**
 * DadosCartaoParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class DadosCartaoParameter  extends CartaoDisponivelParameter  implements java.io.Serializable {
    private Boolean agregacaoDeMargemParaSaqueComplementar;

    private boolean cpfImpedidoComissionar;

    private DadosLimiteDeSaque dadosLimiteDeSaqueComplementar;

    private String ipUsuario;

    private String matricula;

    private String matriculaInstituidor;

    private Long numeroContaInterna;

    private String script;

    private int tipoSaque;

    private Double valorAgregacaoDeMargemParaSaqueComplementar;

    private String vinculoMatricula;

    public DadosCartaoParameter() {
    }

    public DadosCartaoParameter(
           String login,
           String senha,
           String loginConsig,
           int codigoEntidade,
           String cpf,
           String senhaConsig,
           Integer sequencialOrgao,
           Boolean agregacaoDeMargemParaSaqueComplementar,
           boolean cpfImpedidoComissionar,
           DadosLimiteDeSaque dadosLimiteDeSaqueComplementar,
           String ipUsuario,
           String matricula,
           String matriculaInstituidor,
           Long numeroContaInterna,
           String script,
           int tipoSaque,
           Double valorAgregacaoDeMargemParaSaqueComplementar,
           String vinculoMatricula) {
        super(
            login,
            senha,
            loginConsig,
            codigoEntidade,
            cpf,
            senhaConsig,
            sequencialOrgao);
        this.agregacaoDeMargemParaSaqueComplementar = agregacaoDeMargemParaSaqueComplementar;
        this.cpfImpedidoComissionar = cpfImpedidoComissionar;
        this.dadosLimiteDeSaqueComplementar = dadosLimiteDeSaqueComplementar;
        this.ipUsuario = ipUsuario;
        this.matricula = matricula;
        this.matriculaInstituidor = matriculaInstituidor;
        this.numeroContaInterna = numeroContaInterna;
        this.script = script;
        this.tipoSaque = tipoSaque;
        this.valorAgregacaoDeMargemParaSaqueComplementar = valorAgregacaoDeMargemParaSaqueComplementar;
        this.vinculoMatricula = vinculoMatricula;
    }


    /**
     * Gets the agregacaoDeMargemParaSaqueComplementar value for this DadosCartaoParameter.
     * 
     * @return agregacaoDeMargemParaSaqueComplementar
     */
    public Boolean getAgregacaoDeMargemParaSaqueComplementar() {
        return agregacaoDeMargemParaSaqueComplementar;
    }


    /**
     * Sets the agregacaoDeMargemParaSaqueComplementar value for this DadosCartaoParameter.
     * 
     * @param agregacaoDeMargemParaSaqueComplementar
     */
    public void setAgregacaoDeMargemParaSaqueComplementar(Boolean agregacaoDeMargemParaSaqueComplementar) {
        this.agregacaoDeMargemParaSaqueComplementar = agregacaoDeMargemParaSaqueComplementar;
    }


    /**
     * Gets the cpfImpedidoComissionar value for this DadosCartaoParameter.
     * 
     * @return cpfImpedidoComissionar
     */
    public boolean isCpfImpedidoComissionar() {
        return cpfImpedidoComissionar;
    }


    /**
     * Sets the cpfImpedidoComissionar value for this DadosCartaoParameter.
     * 
     * @param cpfImpedidoComissionar
     */
    public void setCpfImpedidoComissionar(boolean cpfImpedidoComissionar) {
        this.cpfImpedidoComissionar = cpfImpedidoComissionar;
    }


    /**
     * Gets the dadosLimiteDeSaqueComplementar value for this DadosCartaoParameter.
     * 
     * @return dadosLimiteDeSaqueComplementar
     */
    public DadosLimiteDeSaque getDadosLimiteDeSaqueComplementar() {
        return dadosLimiteDeSaqueComplementar;
    }


    /**
     * Sets the dadosLimiteDeSaqueComplementar value for this DadosCartaoParameter.
     * 
     * @param dadosLimiteDeSaqueComplementar
     */
    public void setDadosLimiteDeSaqueComplementar(DadosLimiteDeSaque dadosLimiteDeSaqueComplementar) {
        this.dadosLimiteDeSaqueComplementar = dadosLimiteDeSaqueComplementar;
    }


    /**
     * Gets the ipUsuario value for this DadosCartaoParameter.
     * 
     * @return ipUsuario
     */
    public String getIpUsuario() {
        return ipUsuario;
    }


    /**
     * Sets the ipUsuario value for this DadosCartaoParameter.
     * 
     * @param ipUsuario
     */
    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }


    /**
     * Gets the matricula value for this DadosCartaoParameter.
     * 
     * @return matricula
     */
    public String getMatricula() {
        return matricula;
    }


    /**
     * Sets the matricula value for this DadosCartaoParameter.
     * 
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    /**
     * Gets the matriculaInstituidor value for this DadosCartaoParameter.
     * 
     * @return matriculaInstituidor
     */
    public String getMatriculaInstituidor() {
        return matriculaInstituidor;
    }


    /**
     * Sets the matriculaInstituidor value for this DadosCartaoParameter.
     * 
     * @param matriculaInstituidor
     */
    public void setMatriculaInstituidor(String matriculaInstituidor) {
        this.matriculaInstituidor = matriculaInstituidor;
    }


    /**
     * Gets the numeroContaInterna value for this DadosCartaoParameter.
     * 
     * @return numeroContaInterna
     */
    public Long getNumeroContaInterna() {
        return numeroContaInterna;
    }


    /**
     * Sets the numeroContaInterna value for this DadosCartaoParameter.
     * 
     * @param numeroContaInterna
     */
    public void setNumeroContaInterna(Long numeroContaInterna) {
        this.numeroContaInterna = numeroContaInterna;
    }


    /**
     * Gets the script value for this DadosCartaoParameter.
     * 
     * @return script
     */
    public String getScript() {
        return script;
    }


    /**
     * Sets the script value for this DadosCartaoParameter.
     * 
     * @param script
     */
    public void setScript(String script) {
        this.script = script;
    }


    /**
     * Gets the tipoSaque value for this DadosCartaoParameter.
     * 
     * @return tipoSaque
     */
    public int getTipoSaque() {
        return tipoSaque;
    }


    /**
     * Sets the tipoSaque value for this DadosCartaoParameter.
     * 
     * @param tipoSaque
     */
    public void setTipoSaque(int tipoSaque) {
        this.tipoSaque = tipoSaque;
    }


    /**
     * Gets the valorAgregacaoDeMargemParaSaqueComplementar value for this DadosCartaoParameter.
     * 
     * @return valorAgregacaoDeMargemParaSaqueComplementar
     */
    public Double getValorAgregacaoDeMargemParaSaqueComplementar() {
        return valorAgregacaoDeMargemParaSaqueComplementar;
    }


    /**
     * Sets the valorAgregacaoDeMargemParaSaqueComplementar value for this DadosCartaoParameter.
     * 
     * @param valorAgregacaoDeMargemParaSaqueComplementar
     */
    public void setValorAgregacaoDeMargemParaSaqueComplementar(Double valorAgregacaoDeMargemParaSaqueComplementar) {
        this.valorAgregacaoDeMargemParaSaqueComplementar = valorAgregacaoDeMargemParaSaqueComplementar;
    }


    /**
     * Gets the vinculoMatricula value for this DadosCartaoParameter.
     * 
     * @return vinculoMatricula
     */
    public String getVinculoMatricula() {
        return vinculoMatricula;
    }


    /**
     * Sets the vinculoMatricula value for this DadosCartaoParameter.
     * 
     * @param vinculoMatricula
     */
    public void setVinculoMatricula(String vinculoMatricula) {
        this.vinculoMatricula = vinculoMatricula;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DadosCartaoParameter)) return false;
        DadosCartaoParameter other = (DadosCartaoParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.agregacaoDeMargemParaSaqueComplementar==null && other.getAgregacaoDeMargemParaSaqueComplementar()==null) || 
             (this.agregacaoDeMargemParaSaqueComplementar!=null &&
              this.agregacaoDeMargemParaSaqueComplementar.equals(other.getAgregacaoDeMargemParaSaqueComplementar()))) &&
            this.cpfImpedidoComissionar == other.isCpfImpedidoComissionar() &&
            ((this.dadosLimiteDeSaqueComplementar==null && other.getDadosLimiteDeSaqueComplementar()==null) || 
             (this.dadosLimiteDeSaqueComplementar!=null &&
              this.dadosLimiteDeSaqueComplementar.equals(other.getDadosLimiteDeSaqueComplementar()))) &&
            ((this.ipUsuario==null && other.getIpUsuario()==null) || 
             (this.ipUsuario!=null &&
              this.ipUsuario.equals(other.getIpUsuario()))) &&
            ((this.matricula==null && other.getMatricula()==null) || 
             (this.matricula!=null &&
              this.matricula.equals(other.getMatricula()))) &&
            ((this.matriculaInstituidor==null && other.getMatriculaInstituidor()==null) || 
             (this.matriculaInstituidor!=null &&
              this.matriculaInstituidor.equals(other.getMatriculaInstituidor()))) &&
            ((this.numeroContaInterna==null && other.getNumeroContaInterna()==null) || 
             (this.numeroContaInterna!=null &&
              this.numeroContaInterna.equals(other.getNumeroContaInterna()))) &&
            ((this.script==null && other.getScript()==null) || 
             (this.script!=null &&
              this.script.equals(other.getScript()))) &&
            this.tipoSaque == other.getTipoSaque() &&
            ((this.valorAgregacaoDeMargemParaSaqueComplementar==null && other.getValorAgregacaoDeMargemParaSaqueComplementar()==null) || 
             (this.valorAgregacaoDeMargemParaSaqueComplementar!=null &&
              this.valorAgregacaoDeMargemParaSaqueComplementar.equals(other.getValorAgregacaoDeMargemParaSaqueComplementar()))) &&
            ((this.vinculoMatricula==null && other.getVinculoMatricula()==null) || 
             (this.vinculoMatricula!=null &&
              this.vinculoMatricula.equals(other.getVinculoMatricula())));
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
        if (getAgregacaoDeMargemParaSaqueComplementar() != null) {
            _hashCode += getAgregacaoDeMargemParaSaqueComplementar().hashCode();
        }
        _hashCode += (isCpfImpedidoComissionar() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDadosLimiteDeSaqueComplementar() != null) {
            _hashCode += getDadosLimiteDeSaqueComplementar().hashCode();
        }
        if (getIpUsuario() != null) {
            _hashCode += getIpUsuario().hashCode();
        }
        if (getMatricula() != null) {
            _hashCode += getMatricula().hashCode();
        }
        if (getMatriculaInstituidor() != null) {
            _hashCode += getMatriculaInstituidor().hashCode();
        }
        if (getNumeroContaInterna() != null) {
            _hashCode += getNumeroContaInterna().hashCode();
        }
        if (getScript() != null) {
            _hashCode += getScript().hashCode();
        }
        _hashCode += getTipoSaque();
        if (getValorAgregacaoDeMargemParaSaqueComplementar() != null) {
            _hashCode += getValorAgregacaoDeMargemParaSaqueComplementar().hashCode();
        }
        if (getVinculoMatricula() != null) {
            _hashCode += getVinculoMatricula().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DadosCartaoParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCartaoParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agregacaoDeMargemParaSaqueComplementar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agregacaoDeMargemParaSaqueComplementar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfImpedidoComissionar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfImpedidoComissionar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dadosLimiteDeSaqueComplementar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dadosLimiteDeSaqueComplementar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosLimiteDeSaque"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipUsuario"));
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
        elemField.setFieldName("matriculaInstituidor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "matriculaInstituidor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroContaInterna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroContaInterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("script");
        elemField.setXmlName(new javax.xml.namespace.QName("", "script"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSaque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoSaque"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAgregacaoDeMargemParaSaqueComplementar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorAgregacaoDeMargemParaSaqueComplementar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vinculoMatricula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vinculoMatricula"));
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
