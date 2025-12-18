/**
 * PropostaRefinanciamentoParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class PropostaRefinanciamentoParameter  extends com.proativaservicos.service.asynchronous.bmg.refinanciamento.ParametersPropostaConsignacao  implements java.io.Serializable {
    private java.lang.Boolean indicativoCarencia;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.VendaFacilitada vendaFacilitada;

    public PropostaRefinanciamentoParameter() {
    }

    public PropostaRefinanciamentoParameter(
           java.lang.String login,
           java.lang.String senha,
           int aberturaContaPagamento,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.AgenciaParameter agencia,
           java.lang.Boolean agregacaoDeMargemParaSaqueComplementar,
           boolean associado,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.BancoParameter banco,
           int bancoOrdemPagamento,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.BoletoParameter[] boletoPagamento,
           java.lang.String cargo,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.ClienteParameter cliente,
           boolean clientePreCadastrado,
           java.lang.String cnpjEmpregador,
           java.lang.Integer codEnt,
           java.lang.String codigoAverbacao,
           java.lang.String codigoEntidade,
           java.lang.String codigoFormaEnvioTermo,
           java.lang.Integer codigoLoja,
           java.lang.String codigoServico,
           java.lang.Integer codigoSituacaoServidor,
           java.lang.Integer codigoTabela,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.ContaParameter conta,
           java.lang.String cpf,
           java.lang.String cpfAgente,
           java.lang.String criterioIof,
           java.lang.String criterioTac,
           java.lang.String criterioTlf,
           java.util.Calendar dataAdmissao,
           java.util.Calendar dataFator,
           java.util.Calendar dataRenda,
           double descontoAdicional,
           double descontoCompulsorio,
           double descontoOutro,
           boolean descontoPossuiCartao,
           double descontoVoluntario,
           int finalidadeCredito,
           int formaCredito,
           java.lang.String identificadorMargem,
           boolean ignorarInconsistenciasPN,
           java.lang.Boolean incluiSeguroVidaFederal,
           java.lang.Integer indSeguroAderente,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.IndicacaoParameter indicacao,
           java.lang.Boolean indicativoIN100,
           java.lang.String informacoesAdicionais,
           boolean inserirAtendimentoPlusoft,
           java.lang.String ipUsuario,
           java.lang.Double latitude,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato[] listaContrato,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.EletroParameter[] listaEletro,
           java.lang.String loginConsig,
           java.lang.Double longitude,
           double margem,
           java.lang.String matricula,
           java.lang.String matriculaInstituidor,
           java.lang.Long numeroApolice,
           java.lang.String numeroCartao,
           java.lang.String numeroPeculio,
           short numeroPrestacoes,
           java.lang.String numeroPropostaExterna,
           java.lang.Long numeroSorteio,
           boolean possuiCartao,
           java.lang.Integer produto,
           java.lang.Long protocoloMultiProdutos,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.RetornoCetPn retornoCetPn,
           java.lang.String senhaConsig,
           java.lang.String senhaSME,
           java.lang.Integer sequencialOrgao,
           java.lang.Integer tipoBeneficio,
           java.lang.Short tipoDomicilioBancario,
           java.lang.String token,
           java.lang.String ufContaBeneficio,
           java.lang.String unidadePagadora,
           java.lang.Boolean utilizaUserConsig,
           java.lang.Boolean validouSenha,
           java.lang.Double valorAgregacaoDeMargemParaSaqueComplementar,
           java.lang.Double valorCapitalSegurado,
           double valorIof,
           double valorPrestacao,
           double valorRenda,
           java.lang.Double valorResidual,
           java.lang.Double valorSeguroVidaFederal,
           double valorSolicitado,
           java.lang.String vinculoMatricula,
           java.lang.Boolean indicativoCarencia,
           com.proativaservicos.service.asynchronous.bmg.refinanciamento.VendaFacilitada vendaFacilitada) {
        super(
            login,
            senha,
            aberturaContaPagamento,
            agencia,
            agregacaoDeMargemParaSaqueComplementar,
            associado,
            banco,
            bancoOrdemPagamento,
            boletoPagamento,
            cargo,
            cliente,
            clientePreCadastrado,
            cnpjEmpregador,
            codEnt,
            codigoAverbacao,
            codigoEntidade,
            codigoFormaEnvioTermo,
            codigoLoja,
            codigoServico,
            codigoSituacaoServidor,
            codigoTabela,
            conta,
            cpf,
            cpfAgente,
            criterioIof,
            criterioTac,
            criterioTlf,
            dataAdmissao,
            dataFator,
            dataRenda,
            descontoAdicional,
            descontoCompulsorio,
            descontoOutro,
            descontoPossuiCartao,
            descontoVoluntario,
            finalidadeCredito,
            formaCredito,
            identificadorMargem,
            ignorarInconsistenciasPN,
            incluiSeguroVidaFederal,
            indSeguroAderente,
            indicacao,
            indicativoIN100,
            informacoesAdicionais,
            inserirAtendimentoPlusoft,
            ipUsuario,
            latitude,
            listaContrato,
            listaEletro,
            loginConsig,
            longitude,
            margem,
            matricula,
            matriculaInstituidor,
            numeroApolice,
            numeroCartao,
            numeroPeculio,
            numeroPrestacoes,
            numeroPropostaExterna,
            numeroSorteio,
            possuiCartao,
            produto,
            protocoloMultiProdutos,
            retornoCetPn,
            senhaConsig,
            senhaSME,
            sequencialOrgao,
            tipoBeneficio,
            tipoDomicilioBancario,
            token,
            ufContaBeneficio,
            unidadePagadora,
            utilizaUserConsig,
            validouSenha,
            valorAgregacaoDeMargemParaSaqueComplementar,
            valorCapitalSegurado,
            valorIof,
            valorPrestacao,
            valorRenda,
            valorResidual,
            valorSeguroVidaFederal,
            valorSolicitado,
            vinculoMatricula);
        this.indicativoCarencia = indicativoCarencia;
        this.vendaFacilitada = vendaFacilitada;
    }


    /**
     * Gets the indicativoCarencia value for this PropostaRefinanciamentoParameter.
     * 
     * @return indicativoCarencia
     */
    public java.lang.Boolean getIndicativoCarencia() {
        return indicativoCarencia;
    }


    /**
     * Sets the indicativoCarencia value for this PropostaRefinanciamentoParameter.
     * 
     * @param indicativoCarencia
     */
    public void setIndicativoCarencia(java.lang.Boolean indicativoCarencia) {
        this.indicativoCarencia = indicativoCarencia;
    }


    /**
     * Gets the vendaFacilitada value for this PropostaRefinanciamentoParameter.
     * 
     * @return vendaFacilitada
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.VendaFacilitada getVendaFacilitada() {
        return vendaFacilitada;
    }


    /**
     * Sets the vendaFacilitada value for this PropostaRefinanciamentoParameter.
     * 
     * @param vendaFacilitada
     */
    public void setVendaFacilitada(com.proativaservicos.service.asynchronous.bmg.refinanciamento.VendaFacilitada vendaFacilitada) {
        this.vendaFacilitada = vendaFacilitada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PropostaRefinanciamentoParameter)) return false;
        PropostaRefinanciamentoParameter other = (PropostaRefinanciamentoParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.indicativoCarencia==null && other.getIndicativoCarencia()==null) || 
             (this.indicativoCarencia!=null &&
              this.indicativoCarencia.equals(other.getIndicativoCarencia()))) &&
            ((this.vendaFacilitada==null && other.getVendaFacilitada()==null) || 
             (this.vendaFacilitada!=null &&
              this.vendaFacilitada.equals(other.getVendaFacilitada())));
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
        if (getIndicativoCarencia() != null) {
            _hashCode += getIndicativoCarencia().hashCode();
        }
        if (getVendaFacilitada() != null) {
            _hashCode += getVendaFacilitada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PropostaRefinanciamentoParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaRefinanciamentoParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicativoCarencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicativoCarencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendaFacilitada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vendaFacilitada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "VendaFacilitada"));
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
