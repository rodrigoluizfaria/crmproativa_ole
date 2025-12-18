/**
 * CartaoParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class CartaoParameter  extends com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ParametersPropostaConsignacao  implements java.io.Serializable {
    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AgregacaoMargemParameter agregacaoMargem;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BeneficiariosSeguro[] beneficiariosSeguro;

    private java.lang.Integer codigoMotivoPagamentoOutraConta;

    private java.lang.Integer codigoRejeicaoContaSimples;

    private java.lang.Boolean cpfImpedidoComissionar;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosLimiteDeSaque dadosLimiteDeSaqueComplementar;

    private java.lang.Integer layoutCartao;

    private java.lang.Long numeroContaInterna;

    private java.lang.Integer numeroExterno;

    private java.lang.Integer numeroParcelas;

    private java.lang.Integer parceriaComercial;

    private java.lang.Boolean permiteSomenteFormaCreditoTED;

    private java.lang.String script;

    private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Seguros[] seguros;

    private java.lang.Boolean solicitaAgregacaoMargem;

    private java.lang.String tipoDocumentoIdentificacao;

    private java.lang.Integer tipoEmissaoCartao;

    private java.lang.String tipoFormaEnvioFatura;

    private java.lang.Integer tipoSaque;

    private java.lang.Double valorParcela;

    private java.lang.Double valorSaque;

    public CartaoParameter() {
    }

    public CartaoParameter(
           java.lang.String login,
           java.lang.String senha,
           int aberturaContaPagamento,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AgenciaParameter agencia,
           java.lang.Boolean agregacaoDeMargemParaSaqueComplementar,
           boolean associado,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BancoParameter banco,
           int bancoOrdemPagamento,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BoletoParameter[] boletoPagamento,
           java.lang.String cargo,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ClienteParameter cliente,
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
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ContaParameter conta,
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
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.IndicacaoParameter indicacao,
           java.lang.Boolean indicativoIN100,
           java.lang.String informacoesAdicionais,
           boolean inserirAtendimentoPlusoft,
           java.lang.String ipUsuario,
           java.lang.Double latitude,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Contrato[] listaContrato,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.EletroParameter[] listaEletro,
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
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.RetornoCetPn retornoCetPn,
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
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AgregacaoMargemParameter agregacaoMargem,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BeneficiariosSeguro[] beneficiariosSeguro,
           java.lang.Integer codigoMotivoPagamentoOutraConta,
           java.lang.Integer codigoRejeicaoContaSimples,
           java.lang.Boolean cpfImpedidoComissionar,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosLimiteDeSaque dadosLimiteDeSaqueComplementar,
           java.lang.Integer layoutCartao,
           java.lang.Long numeroContaInterna,
           java.lang.Integer numeroExterno,
           java.lang.Integer numeroParcelas,
           java.lang.Integer parceriaComercial,
           java.lang.Boolean permiteSomenteFormaCreditoTED,
           java.lang.String script,
           com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Seguros[] seguros,
           java.lang.Boolean solicitaAgregacaoMargem,
           java.lang.String tipoDocumentoIdentificacao,
           java.lang.Integer tipoEmissaoCartao,
           java.lang.String tipoFormaEnvioFatura,
           java.lang.Integer tipoSaque,
           java.lang.Double valorParcela,
           java.lang.Double valorSaque) {
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
        this.agregacaoMargem = agregacaoMargem;
        this.beneficiariosSeguro = beneficiariosSeguro;
        this.codigoMotivoPagamentoOutraConta = codigoMotivoPagamentoOutraConta;
        this.codigoRejeicaoContaSimples = codigoRejeicaoContaSimples;
        this.cpfImpedidoComissionar = cpfImpedidoComissionar;
        this.dadosLimiteDeSaqueComplementar = dadosLimiteDeSaqueComplementar;
        this.layoutCartao = layoutCartao;
        this.numeroContaInterna = numeroContaInterna;
        this.numeroExterno = numeroExterno;
        this.numeroParcelas = numeroParcelas;
        this.parceriaComercial = parceriaComercial;
        this.permiteSomenteFormaCreditoTED = permiteSomenteFormaCreditoTED;
        this.script = script;
        this.seguros = seguros;
        this.solicitaAgregacaoMargem = solicitaAgregacaoMargem;
        this.tipoDocumentoIdentificacao = tipoDocumentoIdentificacao;
        this.tipoEmissaoCartao = tipoEmissaoCartao;
        this.tipoFormaEnvioFatura = tipoFormaEnvioFatura;
        this.tipoSaque = tipoSaque;
        this.valorParcela = valorParcela;
        this.valorSaque = valorSaque;
    }


    /**
     * Gets the agregacaoMargem value for this CartaoParameter.
     * 
     * @return agregacaoMargem
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AgregacaoMargemParameter getAgregacaoMargem() {
        return agregacaoMargem;
    }


    /**
     * Sets the agregacaoMargem value for this CartaoParameter.
     * 
     * @param agregacaoMargem
     */
    public void setAgregacaoMargem(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AgregacaoMargemParameter agregacaoMargem) {
        this.agregacaoMargem = agregacaoMargem;
    }


    /**
     * Gets the beneficiariosSeguro value for this CartaoParameter.
     * 
     * @return beneficiariosSeguro
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BeneficiariosSeguro[] getBeneficiariosSeguro() {
        return beneficiariosSeguro;
    }


    /**
     * Sets the beneficiariosSeguro value for this CartaoParameter.
     * 
     * @param beneficiariosSeguro
     */
    public void setBeneficiariosSeguro(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BeneficiariosSeguro[] beneficiariosSeguro) {
        this.beneficiariosSeguro = beneficiariosSeguro;
    }


    /**
     * Gets the codigoMotivoPagamentoOutraConta value for this CartaoParameter.
     * 
     * @return codigoMotivoPagamentoOutraConta
     */
    public java.lang.Integer getCodigoMotivoPagamentoOutraConta() {
        return codigoMotivoPagamentoOutraConta;
    }


    /**
     * Sets the codigoMotivoPagamentoOutraConta value for this CartaoParameter.
     * 
     * @param codigoMotivoPagamentoOutraConta
     */
    public void setCodigoMotivoPagamentoOutraConta(java.lang.Integer codigoMotivoPagamentoOutraConta) {
        this.codigoMotivoPagamentoOutraConta = codigoMotivoPagamentoOutraConta;
    }


    /**
     * Gets the codigoRejeicaoContaSimples value for this CartaoParameter.
     * 
     * @return codigoRejeicaoContaSimples
     */
    public java.lang.Integer getCodigoRejeicaoContaSimples() {
        return codigoRejeicaoContaSimples;
    }


    /**
     * Sets the codigoRejeicaoContaSimples value for this CartaoParameter.
     * 
     * @param codigoRejeicaoContaSimples
     */
    public void setCodigoRejeicaoContaSimples(java.lang.Integer codigoRejeicaoContaSimples) {
        this.codigoRejeicaoContaSimples = codigoRejeicaoContaSimples;
    }


    /**
     * Gets the cpfImpedidoComissionar value for this CartaoParameter.
     * 
     * @return cpfImpedidoComissionar
     */
    public java.lang.Boolean getCpfImpedidoComissionar() {
        return cpfImpedidoComissionar;
    }


    /**
     * Sets the cpfImpedidoComissionar value for this CartaoParameter.
     * 
     * @param cpfImpedidoComissionar
     */
    public void setCpfImpedidoComissionar(java.lang.Boolean cpfImpedidoComissionar) {
        this.cpfImpedidoComissionar = cpfImpedidoComissionar;
    }


    /**
     * Gets the dadosLimiteDeSaqueComplementar value for this CartaoParameter.
     * 
     * @return dadosLimiteDeSaqueComplementar
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosLimiteDeSaque getDadosLimiteDeSaqueComplementar() {
        return dadosLimiteDeSaqueComplementar;
    }


    /**
     * Sets the dadosLimiteDeSaqueComplementar value for this CartaoParameter.
     * 
     * @param dadosLimiteDeSaqueComplementar
     */
    public void setDadosLimiteDeSaqueComplementar(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosLimiteDeSaque dadosLimiteDeSaqueComplementar) {
        this.dadosLimiteDeSaqueComplementar = dadosLimiteDeSaqueComplementar;
    }


    /**
     * Gets the layoutCartao value for this CartaoParameter.
     * 
     * @return layoutCartao
     */
    public java.lang.Integer getLayoutCartao() {
        return layoutCartao;
    }


    /**
     * Sets the layoutCartao value for this CartaoParameter.
     * 
     * @param layoutCartao
     */
    public void setLayoutCartao(java.lang.Integer layoutCartao) {
        this.layoutCartao = layoutCartao;
    }


    /**
     * Gets the numeroContaInterna value for this CartaoParameter.
     * 
     * @return numeroContaInterna
     */
    public java.lang.Long getNumeroContaInterna() {
        return numeroContaInterna;
    }


    /**
     * Sets the numeroContaInterna value for this CartaoParameter.
     * 
     * @param numeroContaInterna
     */
    public void setNumeroContaInterna(java.lang.Long numeroContaInterna) {
        this.numeroContaInterna = numeroContaInterna;
    }


    /**
     * Gets the numeroExterno value for this CartaoParameter.
     * 
     * @return numeroExterno
     */
    public java.lang.Integer getNumeroExterno() {
        return numeroExterno;
    }


    /**
     * Sets the numeroExterno value for this CartaoParameter.
     * 
     * @param numeroExterno
     */
    public void setNumeroExterno(java.lang.Integer numeroExterno) {
        this.numeroExterno = numeroExterno;
    }


    /**
     * Gets the numeroParcelas value for this CartaoParameter.
     * 
     * @return numeroParcelas
     */
    public java.lang.Integer getNumeroParcelas() {
        return numeroParcelas;
    }


    /**
     * Sets the numeroParcelas value for this CartaoParameter.
     * 
     * @param numeroParcelas
     */
    public void setNumeroParcelas(java.lang.Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }


    /**
     * Gets the parceriaComercial value for this CartaoParameter.
     * 
     * @return parceriaComercial
     */
    public java.lang.Integer getParceriaComercial() {
        return parceriaComercial;
    }


    /**
     * Sets the parceriaComercial value for this CartaoParameter.
     * 
     * @param parceriaComercial
     */
    public void setParceriaComercial(java.lang.Integer parceriaComercial) {
        this.parceriaComercial = parceriaComercial;
    }


    /**
     * Gets the permiteSomenteFormaCreditoTED value for this CartaoParameter.
     * 
     * @return permiteSomenteFormaCreditoTED
     */
    public java.lang.Boolean getPermiteSomenteFormaCreditoTED() {
        return permiteSomenteFormaCreditoTED;
    }


    /**
     * Sets the permiteSomenteFormaCreditoTED value for this CartaoParameter.
     * 
     * @param permiteSomenteFormaCreditoTED
     */
    public void setPermiteSomenteFormaCreditoTED(java.lang.Boolean permiteSomenteFormaCreditoTED) {
        this.permiteSomenteFormaCreditoTED = permiteSomenteFormaCreditoTED;
    }


    /**
     * Gets the script value for this CartaoParameter.
     * 
     * @return script
     */
    public java.lang.String getScript() {
        return script;
    }


    /**
     * Sets the script value for this CartaoParameter.
     * 
     * @param script
     */
    public void setScript(java.lang.String script) {
        this.script = script;
    }


    /**
     * Gets the seguros value for this CartaoParameter.
     * 
     * @return seguros
     */
    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Seguros[] getSeguros() {
        return seguros;
    }


    /**
     * Sets the seguros value for this CartaoParameter.
     * 
     * @param seguros
     */
    public void setSeguros(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Seguros[] seguros) {
        this.seguros = seguros;
    }


    /**
     * Gets the solicitaAgregacaoMargem value for this CartaoParameter.
     * 
     * @return solicitaAgregacaoMargem
     */
    public java.lang.Boolean getSolicitaAgregacaoMargem() {
        return solicitaAgregacaoMargem;
    }


    /**
     * Sets the solicitaAgregacaoMargem value for this CartaoParameter.
     * 
     * @param solicitaAgregacaoMargem
     */
    public void setSolicitaAgregacaoMargem(java.lang.Boolean solicitaAgregacaoMargem) {
        this.solicitaAgregacaoMargem = solicitaAgregacaoMargem;
    }


    /**
     * Gets the tipoDocumentoIdentificacao value for this CartaoParameter.
     * 
     * @return tipoDocumentoIdentificacao
     */
    public java.lang.String getTipoDocumentoIdentificacao() {
        return tipoDocumentoIdentificacao;
    }


    /**
     * Sets the tipoDocumentoIdentificacao value for this CartaoParameter.
     * 
     * @param tipoDocumentoIdentificacao
     */
    public void setTipoDocumentoIdentificacao(java.lang.String tipoDocumentoIdentificacao) {
        this.tipoDocumentoIdentificacao = tipoDocumentoIdentificacao;
    }


    /**
     * Gets the tipoEmissaoCartao value for this CartaoParameter.
     * 
     * @return tipoEmissaoCartao
     */
    public java.lang.Integer getTipoEmissaoCartao() {
        return tipoEmissaoCartao;
    }


    /**
     * Sets the tipoEmissaoCartao value for this CartaoParameter.
     * 
     * @param tipoEmissaoCartao
     */
    public void setTipoEmissaoCartao(java.lang.Integer tipoEmissaoCartao) {
        this.tipoEmissaoCartao = tipoEmissaoCartao;
    }


    /**
     * Gets the tipoFormaEnvioFatura value for this CartaoParameter.
     * 
     * @return tipoFormaEnvioFatura
     */
    public java.lang.String getTipoFormaEnvioFatura() {
        return tipoFormaEnvioFatura;
    }


    /**
     * Sets the tipoFormaEnvioFatura value for this CartaoParameter.
     * 
     * @param tipoFormaEnvioFatura
     */
    public void setTipoFormaEnvioFatura(java.lang.String tipoFormaEnvioFatura) {
        this.tipoFormaEnvioFatura = tipoFormaEnvioFatura;
    }


    /**
     * Gets the tipoSaque value for this CartaoParameter.
     * 
     * @return tipoSaque
     */
    public java.lang.Integer getTipoSaque() {
        return tipoSaque;
    }


    /**
     * Sets the tipoSaque value for this CartaoParameter.
     * 
     * @param tipoSaque
     */
    public void setTipoSaque(java.lang.Integer tipoSaque) {
        this.tipoSaque = tipoSaque;
    }


    /**
     * Gets the valorParcela value for this CartaoParameter.
     * 
     * @return valorParcela
     */
    public java.lang.Double getValorParcela() {
        return valorParcela;
    }


    /**
     * Sets the valorParcela value for this CartaoParameter.
     * 
     * @param valorParcela
     */
    public void setValorParcela(java.lang.Double valorParcela) {
        this.valorParcela = valorParcela;
    }


    /**
     * Gets the valorSaque value for this CartaoParameter.
     * 
     * @return valorSaque
     */
    public java.lang.Double getValorSaque() {
        return valorSaque;
    }


    /**
     * Sets the valorSaque value for this CartaoParameter.
     * 
     * @param valorSaque
     */
    public void setValorSaque(java.lang.Double valorSaque) {
        this.valorSaque = valorSaque;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CartaoParameter)) return false;
        CartaoParameter other = (CartaoParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.agregacaoMargem==null && other.getAgregacaoMargem()==null) || 
             (this.agregacaoMargem!=null &&
              this.agregacaoMargem.equals(other.getAgregacaoMargem()))) &&
            ((this.beneficiariosSeguro==null && other.getBeneficiariosSeguro()==null) || 
             (this.beneficiariosSeguro!=null &&
              java.util.Arrays.equals(this.beneficiariosSeguro, other.getBeneficiariosSeguro()))) &&
            ((this.codigoMotivoPagamentoOutraConta==null && other.getCodigoMotivoPagamentoOutraConta()==null) || 
             (this.codigoMotivoPagamentoOutraConta!=null &&
              this.codigoMotivoPagamentoOutraConta.equals(other.getCodigoMotivoPagamentoOutraConta()))) &&
            ((this.codigoRejeicaoContaSimples==null && other.getCodigoRejeicaoContaSimples()==null) || 
             (this.codigoRejeicaoContaSimples!=null &&
              this.codigoRejeicaoContaSimples.equals(other.getCodigoRejeicaoContaSimples()))) &&
            ((this.cpfImpedidoComissionar==null && other.getCpfImpedidoComissionar()==null) || 
             (this.cpfImpedidoComissionar!=null &&
              this.cpfImpedidoComissionar.equals(other.getCpfImpedidoComissionar()))) &&
            ((this.dadosLimiteDeSaqueComplementar==null && other.getDadosLimiteDeSaqueComplementar()==null) || 
             (this.dadosLimiteDeSaqueComplementar!=null &&
              this.dadosLimiteDeSaqueComplementar.equals(other.getDadosLimiteDeSaqueComplementar()))) &&
            ((this.layoutCartao==null && other.getLayoutCartao()==null) || 
             (this.layoutCartao!=null &&
              this.layoutCartao.equals(other.getLayoutCartao()))) &&
            ((this.numeroContaInterna==null && other.getNumeroContaInterna()==null) || 
             (this.numeroContaInterna!=null &&
              this.numeroContaInterna.equals(other.getNumeroContaInterna()))) &&
            ((this.numeroExterno==null && other.getNumeroExterno()==null) || 
             (this.numeroExterno!=null &&
              this.numeroExterno.equals(other.getNumeroExterno()))) &&
            ((this.numeroParcelas==null && other.getNumeroParcelas()==null) || 
             (this.numeroParcelas!=null &&
              this.numeroParcelas.equals(other.getNumeroParcelas()))) &&
            ((this.parceriaComercial==null && other.getParceriaComercial()==null) || 
             (this.parceriaComercial!=null &&
              this.parceriaComercial.equals(other.getParceriaComercial()))) &&
            ((this.permiteSomenteFormaCreditoTED==null && other.getPermiteSomenteFormaCreditoTED()==null) || 
             (this.permiteSomenteFormaCreditoTED!=null &&
              this.permiteSomenteFormaCreditoTED.equals(other.getPermiteSomenteFormaCreditoTED()))) &&
            ((this.script==null && other.getScript()==null) || 
             (this.script!=null &&
              this.script.equals(other.getScript()))) &&
            ((this.seguros==null && other.getSeguros()==null) || 
             (this.seguros!=null &&
              java.util.Arrays.equals(this.seguros, other.getSeguros()))) &&
            ((this.solicitaAgregacaoMargem==null && other.getSolicitaAgregacaoMargem()==null) || 
             (this.solicitaAgregacaoMargem!=null &&
              this.solicitaAgregacaoMargem.equals(other.getSolicitaAgregacaoMargem()))) &&
            ((this.tipoDocumentoIdentificacao==null && other.getTipoDocumentoIdentificacao()==null) || 
             (this.tipoDocumentoIdentificacao!=null &&
              this.tipoDocumentoIdentificacao.equals(other.getTipoDocumentoIdentificacao()))) &&
            ((this.tipoEmissaoCartao==null && other.getTipoEmissaoCartao()==null) || 
             (this.tipoEmissaoCartao!=null &&
              this.tipoEmissaoCartao.equals(other.getTipoEmissaoCartao()))) &&
            ((this.tipoFormaEnvioFatura==null && other.getTipoFormaEnvioFatura()==null) || 
             (this.tipoFormaEnvioFatura!=null &&
              this.tipoFormaEnvioFatura.equals(other.getTipoFormaEnvioFatura()))) &&
            ((this.tipoSaque==null && other.getTipoSaque()==null) || 
             (this.tipoSaque!=null &&
              this.tipoSaque.equals(other.getTipoSaque()))) &&
            ((this.valorParcela==null && other.getValorParcela()==null) || 
             (this.valorParcela!=null &&
              this.valorParcela.equals(other.getValorParcela()))) &&
            ((this.valorSaque==null && other.getValorSaque()==null) || 
             (this.valorSaque!=null &&
              this.valorSaque.equals(other.getValorSaque())));
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
        if (getAgregacaoMargem() != null) {
            _hashCode += getAgregacaoMargem().hashCode();
        }
        if (getBeneficiariosSeguro() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBeneficiariosSeguro());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBeneficiariosSeguro(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodigoMotivoPagamentoOutraConta() != null) {
            _hashCode += getCodigoMotivoPagamentoOutraConta().hashCode();
        }
        if (getCodigoRejeicaoContaSimples() != null) {
            _hashCode += getCodigoRejeicaoContaSimples().hashCode();
        }
        if (getCpfImpedidoComissionar() != null) {
            _hashCode += getCpfImpedidoComissionar().hashCode();
        }
        if (getDadosLimiteDeSaqueComplementar() != null) {
            _hashCode += getDadosLimiteDeSaqueComplementar().hashCode();
        }
        if (getLayoutCartao() != null) {
            _hashCode += getLayoutCartao().hashCode();
        }
        if (getNumeroContaInterna() != null) {
            _hashCode += getNumeroContaInterna().hashCode();
        }
        if (getNumeroExterno() != null) {
            _hashCode += getNumeroExterno().hashCode();
        }
        if (getNumeroParcelas() != null) {
            _hashCode += getNumeroParcelas().hashCode();
        }
        if (getParceriaComercial() != null) {
            _hashCode += getParceriaComercial().hashCode();
        }
        if (getPermiteSomenteFormaCreditoTED() != null) {
            _hashCode += getPermiteSomenteFormaCreditoTED().hashCode();
        }
        if (getScript() != null) {
            _hashCode += getScript().hashCode();
        }
        if (getSeguros() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeguros());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSeguros(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSolicitaAgregacaoMargem() != null) {
            _hashCode += getSolicitaAgregacaoMargem().hashCode();
        }
        if (getTipoDocumentoIdentificacao() != null) {
            _hashCode += getTipoDocumentoIdentificacao().hashCode();
        }
        if (getTipoEmissaoCartao() != null) {
            _hashCode += getTipoEmissaoCartao().hashCode();
        }
        if (getTipoFormaEnvioFatura() != null) {
            _hashCode += getTipoFormaEnvioFatura().hashCode();
        }
        if (getTipoSaque() != null) {
            _hashCode += getTipoSaque().hashCode();
        }
        if (getValorParcela() != null) {
            _hashCode += getValorParcela().hashCode();
        }
        if (getValorSaque() != null) {
            _hashCode += getValorSaque().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CartaoParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agregacaoMargem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agregacaoMargem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://parameter.cartao.econsig.bmg.com", "AgregacaoMargemParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiariosSeguro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beneficiariosSeguro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BeneficiariosSeguro"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMotivoPagamentoOutraConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoMotivoPagamentoOutraConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRejeicaoContaSimples");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRejeicaoContaSimples"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfImpedidoComissionar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfImpedidoComissionar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dadosLimiteDeSaqueComplementar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dadosLimiteDeSaqueComplementar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://parameter.cartao.econsig.bmg.com", "DadosLimiteDeSaque"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("layoutCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "layoutCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroContaInterna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroContaInterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroExterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroExterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroParcelas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroParcelas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parceriaComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parceriaComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permiteSomenteFormaCreditoTED");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permiteSomenteFormaCreditoTED"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("script");
        elemField.setXmlName(new javax.xml.namespace.QName("", "script"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seguros");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seguros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguros"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitaAgregacaoMargem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "solicitaAgregacaoMargem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumentoIdentificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoDocumentoIdentificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEmissaoCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoEmissaoCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFormaEnvioFatura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoFormaEnvioFatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSaque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoSaque"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorParcela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorParcela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaque"));
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
