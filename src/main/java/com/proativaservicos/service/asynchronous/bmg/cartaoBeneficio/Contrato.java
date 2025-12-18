/**
 * Contrato.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class Contrato  implements java.io.Serializable {
    private java.util.Calendar dataCancelamento;

    private java.util.Calendar dataDesagio;

    private java.util.Calendar dataFim;

    private java.util.Calendar dataInicio;

    private java.util.Calendar dataInicioContrato;

    private java.lang.Double margemMetabusca;

    private java.lang.Integer minimoParcelasRefinanciamento;

    private java.lang.Integer numeroCalculo;

    private java.lang.Integer numeroContrato;

    private java.lang.String numeroDaAdesao;

    private java.lang.String numeroExterno;

    private java.lang.Integer numeroInternoContrato;

    private java.lang.String numeroTEC;

    private java.lang.Double percentualParcelasPagas;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Protocolo protocolo;

    private java.lang.Integer quantidadeParcelaFinal;

    private java.lang.Integer quantidadeParcelaInicial;

    private java.lang.Integer quantidadeParcelasAberto;

    private java.lang.Integer quantidadeParcelasPagas;

    private java.lang.Integer quantidadeParcelasRefin;

    private java.lang.Integer quantidadeTotalParcela;

    private boolean retencao;

    private java.lang.Double saldoDevedor;

    private java.lang.String timestamp;

    private java.lang.Integer tipoRefin;

    private java.lang.Double valorParcela;

    public Contrato() {
    }

    public Contrato(
           java.util.Calendar dataCancelamento,
           java.util.Calendar dataDesagio,
           java.util.Calendar dataFim,
           java.util.Calendar dataInicio,
           java.util.Calendar dataInicioContrato,
           java.lang.Double margemMetabusca,
           java.lang.Integer minimoParcelasRefinanciamento,
           java.lang.Integer numeroCalculo,
           java.lang.Integer numeroContrato,
           java.lang.String numeroDaAdesao,
           java.lang.String numeroExterno,
           java.lang.Integer numeroInternoContrato,
           java.lang.String numeroTEC,
           java.lang.Double percentualParcelasPagas,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Protocolo protocolo,
           java.lang.Integer quantidadeParcelaFinal,
           java.lang.Integer quantidadeParcelaInicial,
           java.lang.Integer quantidadeParcelasAberto,
           java.lang.Integer quantidadeParcelasPagas,
           java.lang.Integer quantidadeParcelasRefin,
           java.lang.Integer quantidadeTotalParcela,
           boolean retencao,
           java.lang.Double saldoDevedor,
           java.lang.String timestamp,
           java.lang.Integer tipoRefin,
           java.lang.Double valorParcela) {
           this.dataCancelamento = dataCancelamento;
           this.dataDesagio = dataDesagio;
           this.dataFim = dataFim;
           this.dataInicio = dataInicio;
           this.dataInicioContrato = dataInicioContrato;
           this.margemMetabusca = margemMetabusca;
           this.minimoParcelasRefinanciamento = minimoParcelasRefinanciamento;
           this.numeroCalculo = numeroCalculo;
           this.numeroContrato = numeroContrato;
           this.numeroDaAdesao = numeroDaAdesao;
           this.numeroExterno = numeroExterno;
           this.numeroInternoContrato = numeroInternoContrato;
           this.numeroTEC = numeroTEC;
           this.percentualParcelasPagas = percentualParcelasPagas;
           this.protocolo = protocolo;
           this.quantidadeParcelaFinal = quantidadeParcelaFinal;
           this.quantidadeParcelaInicial = quantidadeParcelaInicial;
           this.quantidadeParcelasAberto = quantidadeParcelasAberto;
           this.quantidadeParcelasPagas = quantidadeParcelasPagas;
           this.quantidadeParcelasRefin = quantidadeParcelasRefin;
           this.quantidadeTotalParcela = quantidadeTotalParcela;
           this.retencao = retencao;
           this.saldoDevedor = saldoDevedor;
           this.timestamp = timestamp;
           this.tipoRefin = tipoRefin;
           this.valorParcela = valorParcela;
    }


    /**
     * Gets the dataCancelamento value for this Contrato.
     * 
     * @return dataCancelamento
     */
    public java.util.Calendar getDataCancelamento() {
        return dataCancelamento;
    }


    /**
     * Sets the dataCancelamento value for this Contrato.
     * 
     * @param dataCancelamento
     */
    public void setDataCancelamento(java.util.Calendar dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }


    /**
     * Gets the dataDesagio value for this Contrato.
     * 
     * @return dataDesagio
     */
    public java.util.Calendar getDataDesagio() {
        return dataDesagio;
    }


    /**
     * Sets the dataDesagio value for this Contrato.
     * 
     * @param dataDesagio
     */
    public void setDataDesagio(java.util.Calendar dataDesagio) {
        this.dataDesagio = dataDesagio;
    }


    /**
     * Gets the dataFim value for this Contrato.
     * 
     * @return dataFim
     */
    public java.util.Calendar getDataFim() {
        return dataFim;
    }


    /**
     * Sets the dataFim value for this Contrato.
     * 
     * @param dataFim
     */
    public void setDataFim(java.util.Calendar dataFim) {
        this.dataFim = dataFim;
    }


    /**
     * Gets the dataInicio value for this Contrato.
     * 
     * @return dataInicio
     */
    public java.util.Calendar getDataInicio() {
        return dataInicio;
    }


    /**
     * Sets the dataInicio value for this Contrato.
     * 
     * @param dataInicio
     */
    public void setDataInicio(java.util.Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }


    /**
     * Gets the dataInicioContrato value for this Contrato.
     * 
     * @return dataInicioContrato
     */
    public java.util.Calendar getDataInicioContrato() {
        return dataInicioContrato;
    }


    /**
     * Sets the dataInicioContrato value for this Contrato.
     * 
     * @param dataInicioContrato
     */
    public void setDataInicioContrato(java.util.Calendar dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }


    /**
     * Gets the margemMetabusca value for this Contrato.
     * 
     * @return margemMetabusca
     */
    public java.lang.Double getMargemMetabusca() {
        return margemMetabusca;
    }


    /**
     * Sets the margemMetabusca value for this Contrato.
     * 
     * @param margemMetabusca
     */
    public void setMargemMetabusca(java.lang.Double margemMetabusca) {
        this.margemMetabusca = margemMetabusca;
    }


    /**
     * Gets the minimoParcelasRefinanciamento value for this Contrato.
     * 
     * @return minimoParcelasRefinanciamento
     */
    public java.lang.Integer getMinimoParcelasRefinanciamento() {
        return minimoParcelasRefinanciamento;
    }


    /**
     * Sets the minimoParcelasRefinanciamento value for this Contrato.
     * 
     * @param minimoParcelasRefinanciamento
     */
    public void setMinimoParcelasRefinanciamento(java.lang.Integer minimoParcelasRefinanciamento) {
        this.minimoParcelasRefinanciamento = minimoParcelasRefinanciamento;
    }


    /**
     * Gets the numeroCalculo value for this Contrato.
     * 
     * @return numeroCalculo
     */
    public java.lang.Integer getNumeroCalculo() {
        return numeroCalculo;
    }


    /**
     * Sets the numeroCalculo value for this Contrato.
     * 
     * @param numeroCalculo
     */
    public void setNumeroCalculo(java.lang.Integer numeroCalculo) {
        this.numeroCalculo = numeroCalculo;
    }


    /**
     * Gets the numeroContrato value for this Contrato.
     * 
     * @return numeroContrato
     */
    public java.lang.Integer getNumeroContrato() {
        return numeroContrato;
    }


    /**
     * Sets the numeroContrato value for this Contrato.
     * 
     * @param numeroContrato
     */
    public void setNumeroContrato(java.lang.Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
    }


    /**
     * Gets the numeroDaAdesao value for this Contrato.
     * 
     * @return numeroDaAdesao
     */
    public java.lang.String getNumeroDaAdesao() {
        return numeroDaAdesao;
    }


    /**
     * Sets the numeroDaAdesao value for this Contrato.
     * 
     * @param numeroDaAdesao
     */
    public void setNumeroDaAdesao(java.lang.String numeroDaAdesao) {
        this.numeroDaAdesao = numeroDaAdesao;
    }


    /**
     * Gets the numeroExterno value for this Contrato.
     * 
     * @return numeroExterno
     */
    public java.lang.String getNumeroExterno() {
        return numeroExterno;
    }


    /**
     * Sets the numeroExterno value for this Contrato.
     * 
     * @param numeroExterno
     */
    public void setNumeroExterno(java.lang.String numeroExterno) {
        this.numeroExterno = numeroExterno;
    }


    /**
     * Gets the numeroInternoContrato value for this Contrato.
     * 
     * @return numeroInternoContrato
     */
    public java.lang.Integer getNumeroInternoContrato() {
        return numeroInternoContrato;
    }


    /**
     * Sets the numeroInternoContrato value for this Contrato.
     * 
     * @param numeroInternoContrato
     */
    public void setNumeroInternoContrato(java.lang.Integer numeroInternoContrato) {
        this.numeroInternoContrato = numeroInternoContrato;
    }


    /**
     * Gets the numeroTEC value for this Contrato.
     * 
     * @return numeroTEC
     */
    public java.lang.String getNumeroTEC() {
        return numeroTEC;
    }


    /**
     * Sets the numeroTEC value for this Contrato.
     * 
     * @param numeroTEC
     */
    public void setNumeroTEC(java.lang.String numeroTEC) {
        this.numeroTEC = numeroTEC;
    }


    /**
     * Gets the percentualParcelasPagas value for this Contrato.
     * 
     * @return percentualParcelasPagas
     */
    public java.lang.Double getPercentualParcelasPagas() {
        return percentualParcelasPagas;
    }


    /**
     * Sets the percentualParcelasPagas value for this Contrato.
     * 
     * @param percentualParcelasPagas
     */
    public void setPercentualParcelasPagas(java.lang.Double percentualParcelasPagas) {
        this.percentualParcelasPagas = percentualParcelasPagas;
    }


    /**
     * Gets the protocolo value for this Contrato.
     * 
     * @return protocolo
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Protocolo getProtocolo() {
        return protocolo;
    }


    /**
     * Sets the protocolo value for this Contrato.
     * 
     * @param protocolo
     */
    public void setProtocolo(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Protocolo protocolo) {
        this.protocolo = protocolo;
    }


    /**
     * Gets the quantidadeParcelaFinal value for this Contrato.
     * 
     * @return quantidadeParcelaFinal
     */
    public java.lang.Integer getQuantidadeParcelaFinal() {
        return quantidadeParcelaFinal;
    }


    /**
     * Sets the quantidadeParcelaFinal value for this Contrato.
     * 
     * @param quantidadeParcelaFinal
     */
    public void setQuantidadeParcelaFinal(java.lang.Integer quantidadeParcelaFinal) {
        this.quantidadeParcelaFinal = quantidadeParcelaFinal;
    }


    /**
     * Gets the quantidadeParcelaInicial value for this Contrato.
     * 
     * @return quantidadeParcelaInicial
     */
    public java.lang.Integer getQuantidadeParcelaInicial() {
        return quantidadeParcelaInicial;
    }


    /**
     * Sets the quantidadeParcelaInicial value for this Contrato.
     * 
     * @param quantidadeParcelaInicial
     */
    public void setQuantidadeParcelaInicial(java.lang.Integer quantidadeParcelaInicial) {
        this.quantidadeParcelaInicial = quantidadeParcelaInicial;
    }


    /**
     * Gets the quantidadeParcelasAberto value for this Contrato.
     * 
     * @return quantidadeParcelasAberto
     */
    public java.lang.Integer getQuantidadeParcelasAberto() {
        return quantidadeParcelasAberto;
    }


    /**
     * Sets the quantidadeParcelasAberto value for this Contrato.
     * 
     * @param quantidadeParcelasAberto
     */
    public void setQuantidadeParcelasAberto(java.lang.Integer quantidadeParcelasAberto) {
        this.quantidadeParcelasAberto = quantidadeParcelasAberto;
    }


    /**
     * Gets the quantidadeParcelasPagas value for this Contrato.
     * 
     * @return quantidadeParcelasPagas
     */
    public java.lang.Integer getQuantidadeParcelasPagas() {
        return quantidadeParcelasPagas;
    }


    /**
     * Sets the quantidadeParcelasPagas value for this Contrato.
     * 
     * @param quantidadeParcelasPagas
     */
    public void setQuantidadeParcelasPagas(java.lang.Integer quantidadeParcelasPagas) {
        this.quantidadeParcelasPagas = quantidadeParcelasPagas;
    }


    /**
     * Gets the quantidadeParcelasRefin value for this Contrato.
     * 
     * @return quantidadeParcelasRefin
     */
    public java.lang.Integer getQuantidadeParcelasRefin() {
        return quantidadeParcelasRefin;
    }


    /**
     * Sets the quantidadeParcelasRefin value for this Contrato.
     * 
     * @param quantidadeParcelasRefin
     */
    public void setQuantidadeParcelasRefin(java.lang.Integer quantidadeParcelasRefin) {
        this.quantidadeParcelasRefin = quantidadeParcelasRefin;
    }


    /**
     * Gets the quantidadeTotalParcela value for this Contrato.
     * 
     * @return quantidadeTotalParcela
     */
    public java.lang.Integer getQuantidadeTotalParcela() {
        return quantidadeTotalParcela;
    }


    /**
     * Sets the quantidadeTotalParcela value for this Contrato.
     * 
     * @param quantidadeTotalParcela
     */
    public void setQuantidadeTotalParcela(java.lang.Integer quantidadeTotalParcela) {
        this.quantidadeTotalParcela = quantidadeTotalParcela;
    }


    /**
     * Gets the retencao value for this Contrato.
     * 
     * @return retencao
     */
    public boolean isRetencao() {
        return retencao;
    }


    /**
     * Sets the retencao value for this Contrato.
     * 
     * @param retencao
     */
    public void setRetencao(boolean retencao) {
        this.retencao = retencao;
    }


    /**
     * Gets the saldoDevedor value for this Contrato.
     * 
     * @return saldoDevedor
     */
    public java.lang.Double getSaldoDevedor() {
        return saldoDevedor;
    }


    /**
     * Sets the saldoDevedor value for this Contrato.
     * 
     * @param saldoDevedor
     */
    public void setSaldoDevedor(java.lang.Double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }


    /**
     * Gets the timestamp value for this Contrato.
     * 
     * @return timestamp
     */
    public java.lang.String getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp value for this Contrato.
     * 
     * @param timestamp
     */
    public void setTimestamp(java.lang.String timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * Gets the tipoRefin value for this Contrato.
     * 
     * @return tipoRefin
     */
    public java.lang.Integer getTipoRefin() {
        return tipoRefin;
    }


    /**
     * Sets the tipoRefin value for this Contrato.
     * 
     * @param tipoRefin
     */
    public void setTipoRefin(java.lang.Integer tipoRefin) {
        this.tipoRefin = tipoRefin;
    }


    /**
     * Gets the valorParcela value for this Contrato.
     * 
     * @return valorParcela
     */
    public java.lang.Double getValorParcela() {
        return valorParcela;
    }


    /**
     * Sets the valorParcela value for this Contrato.
     * 
     * @param valorParcela
     */
    public void setValorParcela(java.lang.Double valorParcela) {
        this.valorParcela = valorParcela;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Contrato)) return false;
        Contrato other = (Contrato) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataCancelamento==null && other.getDataCancelamento()==null) || 
             (this.dataCancelamento!=null &&
              this.dataCancelamento.equals(other.getDataCancelamento()))) &&
            ((this.dataDesagio==null && other.getDataDesagio()==null) || 
             (this.dataDesagio!=null &&
              this.dataDesagio.equals(other.getDataDesagio()))) &&
            ((this.dataFim==null && other.getDataFim()==null) || 
             (this.dataFim!=null &&
              this.dataFim.equals(other.getDataFim()))) &&
            ((this.dataInicio==null && other.getDataInicio()==null) || 
             (this.dataInicio!=null &&
              this.dataInicio.equals(other.getDataInicio()))) &&
            ((this.dataInicioContrato==null && other.getDataInicioContrato()==null) || 
             (this.dataInicioContrato!=null &&
              this.dataInicioContrato.equals(other.getDataInicioContrato()))) &&
            ((this.margemMetabusca==null && other.getMargemMetabusca()==null) || 
             (this.margemMetabusca!=null &&
              this.margemMetabusca.equals(other.getMargemMetabusca()))) &&
            ((this.minimoParcelasRefinanciamento==null && other.getMinimoParcelasRefinanciamento()==null) || 
             (this.minimoParcelasRefinanciamento!=null &&
              this.minimoParcelasRefinanciamento.equals(other.getMinimoParcelasRefinanciamento()))) &&
            ((this.numeroCalculo==null && other.getNumeroCalculo()==null) || 
             (this.numeroCalculo!=null &&
              this.numeroCalculo.equals(other.getNumeroCalculo()))) &&
            ((this.numeroContrato==null && other.getNumeroContrato()==null) || 
             (this.numeroContrato!=null &&
              this.numeroContrato.equals(other.getNumeroContrato()))) &&
            ((this.numeroDaAdesao==null && other.getNumeroDaAdesao()==null) || 
             (this.numeroDaAdesao!=null &&
              this.numeroDaAdesao.equals(other.getNumeroDaAdesao()))) &&
            ((this.numeroExterno==null && other.getNumeroExterno()==null) || 
             (this.numeroExterno!=null &&
              this.numeroExterno.equals(other.getNumeroExterno()))) &&
            ((this.numeroInternoContrato==null && other.getNumeroInternoContrato()==null) || 
             (this.numeroInternoContrato!=null &&
              this.numeroInternoContrato.equals(other.getNumeroInternoContrato()))) &&
            ((this.numeroTEC==null && other.getNumeroTEC()==null) || 
             (this.numeroTEC!=null &&
              this.numeroTEC.equals(other.getNumeroTEC()))) &&
            ((this.percentualParcelasPagas==null && other.getPercentualParcelasPagas()==null) || 
             (this.percentualParcelasPagas!=null &&
              this.percentualParcelasPagas.equals(other.getPercentualParcelasPagas()))) &&
            ((this.protocolo==null && other.getProtocolo()==null) || 
             (this.protocolo!=null &&
              this.protocolo.equals(other.getProtocolo()))) &&
            ((this.quantidadeParcelaFinal==null && other.getQuantidadeParcelaFinal()==null) || 
             (this.quantidadeParcelaFinal!=null &&
              this.quantidadeParcelaFinal.equals(other.getQuantidadeParcelaFinal()))) &&
            ((this.quantidadeParcelaInicial==null && other.getQuantidadeParcelaInicial()==null) || 
             (this.quantidadeParcelaInicial!=null &&
              this.quantidadeParcelaInicial.equals(other.getQuantidadeParcelaInicial()))) &&
            ((this.quantidadeParcelasAberto==null && other.getQuantidadeParcelasAberto()==null) || 
             (this.quantidadeParcelasAberto!=null &&
              this.quantidadeParcelasAberto.equals(other.getQuantidadeParcelasAberto()))) &&
            ((this.quantidadeParcelasPagas==null && other.getQuantidadeParcelasPagas()==null) || 
             (this.quantidadeParcelasPagas!=null &&
              this.quantidadeParcelasPagas.equals(other.getQuantidadeParcelasPagas()))) &&
            ((this.quantidadeParcelasRefin==null && other.getQuantidadeParcelasRefin()==null) || 
             (this.quantidadeParcelasRefin!=null &&
              this.quantidadeParcelasRefin.equals(other.getQuantidadeParcelasRefin()))) &&
            ((this.quantidadeTotalParcela==null && other.getQuantidadeTotalParcela()==null) || 
             (this.quantidadeTotalParcela!=null &&
              this.quantidadeTotalParcela.equals(other.getQuantidadeTotalParcela()))) &&
            this.retencao == other.isRetencao() &&
            ((this.saldoDevedor==null && other.getSaldoDevedor()==null) || 
             (this.saldoDevedor!=null &&
              this.saldoDevedor.equals(other.getSaldoDevedor()))) &&
            ((this.timestamp==null && other.getTimestamp()==null) || 
             (this.timestamp!=null &&
              this.timestamp.equals(other.getTimestamp()))) &&
            ((this.tipoRefin==null && other.getTipoRefin()==null) || 
             (this.tipoRefin!=null &&
              this.tipoRefin.equals(other.getTipoRefin()))) &&
            ((this.valorParcela==null && other.getValorParcela()==null) || 
             (this.valorParcela!=null &&
              this.valorParcela.equals(other.getValorParcela())));
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
        if (getDataCancelamento() != null) {
            _hashCode += getDataCancelamento().hashCode();
        }
        if (getDataDesagio() != null) {
            _hashCode += getDataDesagio().hashCode();
        }
        if (getDataFim() != null) {
            _hashCode += getDataFim().hashCode();
        }
        if (getDataInicio() != null) {
            _hashCode += getDataInicio().hashCode();
        }
        if (getDataInicioContrato() != null) {
            _hashCode += getDataInicioContrato().hashCode();
        }
        if (getMargemMetabusca() != null) {
            _hashCode += getMargemMetabusca().hashCode();
        }
        if (getMinimoParcelasRefinanciamento() != null) {
            _hashCode += getMinimoParcelasRefinanciamento().hashCode();
        }
        if (getNumeroCalculo() != null) {
            _hashCode += getNumeroCalculo().hashCode();
        }
        if (getNumeroContrato() != null) {
            _hashCode += getNumeroContrato().hashCode();
        }
        if (getNumeroDaAdesao() != null) {
            _hashCode += getNumeroDaAdesao().hashCode();
        }
        if (getNumeroExterno() != null) {
            _hashCode += getNumeroExterno().hashCode();
        }
        if (getNumeroInternoContrato() != null) {
            _hashCode += getNumeroInternoContrato().hashCode();
        }
        if (getNumeroTEC() != null) {
            _hashCode += getNumeroTEC().hashCode();
        }
        if (getPercentualParcelasPagas() != null) {
            _hashCode += getPercentualParcelasPagas().hashCode();
        }
        if (getProtocolo() != null) {
            _hashCode += getProtocolo().hashCode();
        }
        if (getQuantidadeParcelaFinal() != null) {
            _hashCode += getQuantidadeParcelaFinal().hashCode();
        }
        if (getQuantidadeParcelaInicial() != null) {
            _hashCode += getQuantidadeParcelaInicial().hashCode();
        }
        if (getQuantidadeParcelasAberto() != null) {
            _hashCode += getQuantidadeParcelasAberto().hashCode();
        }
        if (getQuantidadeParcelasPagas() != null) {
            _hashCode += getQuantidadeParcelasPagas().hashCode();
        }
        if (getQuantidadeParcelasRefin() != null) {
            _hashCode += getQuantidadeParcelasRefin().hashCode();
        }
        if (getQuantidadeTotalParcela() != null) {
            _hashCode += getQuantidadeTotalParcela().hashCode();
        }
        _hashCode += (isRetencao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSaldoDevedor() != null) {
            _hashCode += getSaldoDevedor().hashCode();
        }
        if (getTimestamp() != null) {
            _hashCode += getTimestamp().hashCode();
        }
        if (getTipoRefin() != null) {
            _hashCode += getTipoRefin().hashCode();
        }
        if (getValorParcela() != null) {
            _hashCode += getValorParcela().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Contrato.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCancelamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataCancelamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDesagio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataDesagio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFim");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataFim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInicioContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataInicioContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("margemMetabusca");
        elemField.setXmlName(new javax.xml.namespace.QName("", "margemMetabusca"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minimoParcelasRefinanciamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "minimoParcelasRefinanciamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCalculo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCalculo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroDaAdesao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroDaAdesao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroExterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroExterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroInternoContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroInternoContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTEC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroTEC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualParcelasPagas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "percentualParcelasPagas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protocolo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "protocolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Protocolo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeParcelaFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeParcelaFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeParcelaInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeParcelaInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeParcelasAberto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeParcelasAberto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeParcelasPagas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeParcelasPagas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeParcelasRefin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeParcelasRefin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidadeTotalParcela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantidadeTotalParcela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retencao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "retencao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saldoDevedor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "saldoDevedor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRefin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoRefin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorParcela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorParcela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
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
