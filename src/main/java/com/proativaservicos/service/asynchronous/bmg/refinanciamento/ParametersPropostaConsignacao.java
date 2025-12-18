/**
 * ParametersPropostaConsignacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class ParametersPropostaConsignacao  extends com.proativaservicos.service.asynchronous.bmg.refinanciamento.WebServiceParameter  implements java.io.Serializable {
    private int aberturaContaPagamento;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.AgenciaParameter agencia;

    private java.lang.Boolean agregacaoDeMargemParaSaqueComplementar;

    private boolean associado;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.BancoParameter banco;

    private int bancoOrdemPagamento;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.BoletoParameter[] boletoPagamento;

    private java.lang.String cargo;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.ClienteParameter cliente;

    private boolean clientePreCadastrado;

    private java.lang.String cnpjEmpregador;

    private java.lang.Integer codEnt;

    private java.lang.String codigoAverbacao;

    private java.lang.String codigoEntidade;

    private java.lang.String codigoFormaEnvioTermo;

    private java.lang.Integer codigoLoja;

    private java.lang.String codigoServico;

    private java.lang.Integer codigoSituacaoServidor;

    private java.lang.Integer codigoTabela;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.ContaParameter conta;

    private java.lang.String cpf;

    private java.lang.String cpfAgente;

    private java.lang.String criterioIof;

    private java.lang.String criterioTac;

    private java.lang.String criterioTlf;

    private java.util.Calendar dataAdmissao;

    private java.util.Calendar dataFator;

    private java.util.Calendar dataRenda;

    private double descontoAdicional;

    private double descontoCompulsorio;

    private double descontoOutro;

    private boolean descontoPossuiCartao;

    private double descontoVoluntario;

    private int finalidadeCredito;

    private int formaCredito;

    private java.lang.String identificadorMargem;

    private boolean ignorarInconsistenciasPN;

    private java.lang.Boolean incluiSeguroVidaFederal;

    private java.lang.Integer indSeguroAderente;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.IndicacaoParameter indicacao;

    private java.lang.Boolean indicativoIN100;

    private java.lang.String informacoesAdicionais;

    private boolean inserirAtendimentoPlusoft;

    private java.lang.String ipUsuario;

    private java.lang.Double latitude;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato[] listaContrato;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.EletroParameter[] listaEletro;

    private java.lang.String loginConsig;

    private java.lang.Double longitude;

    private double margem;

    private java.lang.String matricula;

    private java.lang.String matriculaInstituidor;

    private java.lang.Long numeroApolice;

    private java.lang.String numeroCartao;

    private java.lang.String numeroPeculio;

    private short numeroPrestacoes;

    private java.lang.String numeroPropostaExterna;

    private java.lang.Long numeroSorteio;

    private boolean possuiCartao;

    private java.lang.Integer produto;

    private java.lang.Long protocoloMultiProdutos;

    private com.proativaservicos.service.asynchronous.bmg.refinanciamento.RetornoCetPn retornoCetPn;

    private java.lang.String senhaConsig;

    private java.lang.String senhaSME;

    private java.lang.Integer sequencialOrgao;

    private java.lang.Integer tipoBeneficio;

    private java.lang.Short tipoDomicilioBancario;

    private java.lang.String token;

    private java.lang.String ufContaBeneficio;

    private java.lang.String unidadePagadora;

    private java.lang.Boolean utilizaUserConsig;

    private java.lang.Boolean validouSenha;

    private java.lang.Double valorAgregacaoDeMargemParaSaqueComplementar;

    private java.lang.Double valorCapitalSegurado;

    private double valorIof;

    private double valorPrestacao;

    private double valorRenda;

    private java.lang.Double valorResidual;

    private java.lang.Double valorSeguroVidaFederal;

    private double valorSolicitado;

    private java.lang.String vinculoMatricula;

    public ParametersPropostaConsignacao() {
    }

    public ParametersPropostaConsignacao(
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
           java.lang.String vinculoMatricula) {
        super(
            login,
            senha);
        this.aberturaContaPagamento = aberturaContaPagamento;
        this.agencia = agencia;
        this.agregacaoDeMargemParaSaqueComplementar = agregacaoDeMargemParaSaqueComplementar;
        this.associado = associado;
        this.banco = banco;
        this.bancoOrdemPagamento = bancoOrdemPagamento;
        this.boletoPagamento = boletoPagamento;
        this.cargo = cargo;
        this.cliente = cliente;
        this.clientePreCadastrado = clientePreCadastrado;
        this.cnpjEmpregador = cnpjEmpregador;
        this.codEnt = codEnt;
        this.codigoAverbacao = codigoAverbacao;
        this.codigoEntidade = codigoEntidade;
        this.codigoFormaEnvioTermo = codigoFormaEnvioTermo;
        this.codigoLoja = codigoLoja;
        this.codigoServico = codigoServico;
        this.codigoSituacaoServidor = codigoSituacaoServidor;
        this.codigoTabela = codigoTabela;
        this.conta = conta;
        this.cpf = cpf;
        this.cpfAgente = cpfAgente;
        this.criterioIof = criterioIof;
        this.criterioTac = criterioTac;
        this.criterioTlf = criterioTlf;
        this.dataAdmissao = dataAdmissao;
        this.dataFator = dataFator;
        this.dataRenda = dataRenda;
        this.descontoAdicional = descontoAdicional;
        this.descontoCompulsorio = descontoCompulsorio;
        this.descontoOutro = descontoOutro;
        this.descontoPossuiCartao = descontoPossuiCartao;
        this.descontoVoluntario = descontoVoluntario;
        this.finalidadeCredito = finalidadeCredito;
        this.formaCredito = formaCredito;
        this.identificadorMargem = identificadorMargem;
        this.ignorarInconsistenciasPN = ignorarInconsistenciasPN;
        this.incluiSeguroVidaFederal = incluiSeguroVidaFederal;
        this.indSeguroAderente = indSeguroAderente;
        this.indicacao = indicacao;
        this.indicativoIN100 = indicativoIN100;
        this.informacoesAdicionais = informacoesAdicionais;
        this.inserirAtendimentoPlusoft = inserirAtendimentoPlusoft;
        this.ipUsuario = ipUsuario;
        this.latitude = latitude;
        this.listaContrato = listaContrato;
        this.listaEletro = listaEletro;
        this.loginConsig = loginConsig;
        this.longitude = longitude;
        this.margem = margem;
        this.matricula = matricula;
        this.matriculaInstituidor = matriculaInstituidor;
        this.numeroApolice = numeroApolice;
        this.numeroCartao = numeroCartao;
        this.numeroPeculio = numeroPeculio;
        this.numeroPrestacoes = numeroPrestacoes;
        this.numeroPropostaExterna = numeroPropostaExterna;
        this.numeroSorteio = numeroSorteio;
        this.possuiCartao = possuiCartao;
        this.produto = produto;
        this.protocoloMultiProdutos = protocoloMultiProdutos;
        this.retornoCetPn = retornoCetPn;
        this.senhaConsig = senhaConsig;
        this.senhaSME = senhaSME;
        this.sequencialOrgao = sequencialOrgao;
        this.tipoBeneficio = tipoBeneficio;
        this.tipoDomicilioBancario = tipoDomicilioBancario;
        this.token = token;
        this.ufContaBeneficio = ufContaBeneficio;
        this.unidadePagadora = unidadePagadora;
        this.utilizaUserConsig = utilizaUserConsig;
        this.validouSenha = validouSenha;
        this.valorAgregacaoDeMargemParaSaqueComplementar = valorAgregacaoDeMargemParaSaqueComplementar;
        this.valorCapitalSegurado = valorCapitalSegurado;
        this.valorIof = valorIof;
        this.valorPrestacao = valorPrestacao;
        this.valorRenda = valorRenda;
        this.valorResidual = valorResidual;
        this.valorSeguroVidaFederal = valorSeguroVidaFederal;
        this.valorSolicitado = valorSolicitado;
        this.vinculoMatricula = vinculoMatricula;
    }


    /**
     * Gets the aberturaContaPagamento value for this ParametersPropostaConsignacao.
     * 
     * @return aberturaContaPagamento
     */
    public int getAberturaContaPagamento() {
        return aberturaContaPagamento;
    }


    /**
     * Sets the aberturaContaPagamento value for this ParametersPropostaConsignacao.
     * 
     * @param aberturaContaPagamento
     */
    public void setAberturaContaPagamento(int aberturaContaPagamento) {
        this.aberturaContaPagamento = aberturaContaPagamento;
    }


    /**
     * Gets the agencia value for this ParametersPropostaConsignacao.
     * 
     * @return agencia
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.AgenciaParameter getAgencia() {
        return agencia;
    }


    /**
     * Sets the agencia value for this ParametersPropostaConsignacao.
     * 
     * @param agencia
     */
    public void setAgencia(com.proativaservicos.service.asynchronous.bmg.refinanciamento.AgenciaParameter agencia) {
        this.agencia = agencia;
    }


    /**
     * Gets the agregacaoDeMargemParaSaqueComplementar value for this ParametersPropostaConsignacao.
     * 
     * @return agregacaoDeMargemParaSaqueComplementar
     */
    public java.lang.Boolean getAgregacaoDeMargemParaSaqueComplementar() {
        return agregacaoDeMargemParaSaqueComplementar;
    }


    /**
     * Sets the agregacaoDeMargemParaSaqueComplementar value for this ParametersPropostaConsignacao.
     * 
     * @param agregacaoDeMargemParaSaqueComplementar
     */
    public void setAgregacaoDeMargemParaSaqueComplementar(java.lang.Boolean agregacaoDeMargemParaSaqueComplementar) {
        this.agregacaoDeMargemParaSaqueComplementar = agregacaoDeMargemParaSaqueComplementar;
    }


    /**
     * Gets the associado value for this ParametersPropostaConsignacao.
     * 
     * @return associado
     */
    public boolean isAssociado() {
        return associado;
    }


    /**
     * Sets the associado value for this ParametersPropostaConsignacao.
     * 
     * @param associado
     */
    public void setAssociado(boolean associado) {
        this.associado = associado;
    }


    /**
     * Gets the banco value for this ParametersPropostaConsignacao.
     * 
     * @return banco
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.BancoParameter getBanco() {
        return banco;
    }


    /**
     * Sets the banco value for this ParametersPropostaConsignacao.
     * 
     * @param banco
     */
    public void setBanco(com.proativaservicos.service.asynchronous.bmg.refinanciamento.BancoParameter banco) {
        this.banco = banco;
    }


    /**
     * Gets the bancoOrdemPagamento value for this ParametersPropostaConsignacao.
     * 
     * @return bancoOrdemPagamento
     */
    public int getBancoOrdemPagamento() {
        return bancoOrdemPagamento;
    }


    /**
     * Sets the bancoOrdemPagamento value for this ParametersPropostaConsignacao.
     * 
     * @param bancoOrdemPagamento
     */
    public void setBancoOrdemPagamento(int bancoOrdemPagamento) {
        this.bancoOrdemPagamento = bancoOrdemPagamento;
    }


    /**
     * Gets the boletoPagamento value for this ParametersPropostaConsignacao.
     * 
     * @return boletoPagamento
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.BoletoParameter[] getBoletoPagamento() {
        return boletoPagamento;
    }


    /**
     * Sets the boletoPagamento value for this ParametersPropostaConsignacao.
     * 
     * @param boletoPagamento
     */
    public void setBoletoPagamento(com.proativaservicos.service.asynchronous.bmg.refinanciamento.BoletoParameter[] boletoPagamento) {
        this.boletoPagamento = boletoPagamento;
    }


    /**
     * Gets the cargo value for this ParametersPropostaConsignacao.
     * 
     * @return cargo
     */
    public java.lang.String getCargo() {
        return cargo;
    }


    /**
     * Sets the cargo value for this ParametersPropostaConsignacao.
     * 
     * @param cargo
     */
    public void setCargo(java.lang.String cargo) {
        this.cargo = cargo;
    }


    /**
     * Gets the cliente value for this ParametersPropostaConsignacao.
     * 
     * @return cliente
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.ClienteParameter getCliente() {
        return cliente;
    }


    /**
     * Sets the cliente value for this ParametersPropostaConsignacao.
     * 
     * @param cliente
     */
    public void setCliente(com.proativaservicos.service.asynchronous.bmg.refinanciamento.ClienteParameter cliente) {
        this.cliente = cliente;
    }


    /**
     * Gets the clientePreCadastrado value for this ParametersPropostaConsignacao.
     * 
     * @return clientePreCadastrado
     */
    public boolean isClientePreCadastrado() {
        return clientePreCadastrado;
    }


    /**
     * Sets the clientePreCadastrado value for this ParametersPropostaConsignacao.
     * 
     * @param clientePreCadastrado
     */
    public void setClientePreCadastrado(boolean clientePreCadastrado) {
        this.clientePreCadastrado = clientePreCadastrado;
    }


    /**
     * Gets the cnpjEmpregador value for this ParametersPropostaConsignacao.
     * 
     * @return cnpjEmpregador
     */
    public java.lang.String getCnpjEmpregador() {
        return cnpjEmpregador;
    }


    /**
     * Sets the cnpjEmpregador value for this ParametersPropostaConsignacao.
     * 
     * @param cnpjEmpregador
     */
    public void setCnpjEmpregador(java.lang.String cnpjEmpregador) {
        this.cnpjEmpregador = cnpjEmpregador;
    }


    /**
     * Gets the codEnt value for this ParametersPropostaConsignacao.
     * 
     * @return codEnt
     */
    public java.lang.Integer getCodEnt() {
        return codEnt;
    }


    /**
     * Sets the codEnt value for this ParametersPropostaConsignacao.
     * 
     * @param codEnt
     */
    public void setCodEnt(java.lang.Integer codEnt) {
        this.codEnt = codEnt;
    }


    /**
     * Gets the codigoAverbacao value for this ParametersPropostaConsignacao.
     * 
     * @return codigoAverbacao
     */
    public java.lang.String getCodigoAverbacao() {
        return codigoAverbacao;
    }


    /**
     * Sets the codigoAverbacao value for this ParametersPropostaConsignacao.
     * 
     * @param codigoAverbacao
     */
    public void setCodigoAverbacao(java.lang.String codigoAverbacao) {
        this.codigoAverbacao = codigoAverbacao;
    }


    /**
     * Gets the codigoEntidade value for this ParametersPropostaConsignacao.
     * 
     * @return codigoEntidade
     */
    public java.lang.String getCodigoEntidade() {
        return codigoEntidade;
    }


    /**
     * Sets the codigoEntidade value for this ParametersPropostaConsignacao.
     * 
     * @param codigoEntidade
     */
    public void setCodigoEntidade(java.lang.String codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }


    /**
     * Gets the codigoFormaEnvioTermo value for this ParametersPropostaConsignacao.
     * 
     * @return codigoFormaEnvioTermo
     */
    public java.lang.String getCodigoFormaEnvioTermo() {
        return codigoFormaEnvioTermo;
    }


    /**
     * Sets the codigoFormaEnvioTermo value for this ParametersPropostaConsignacao.
     * 
     * @param codigoFormaEnvioTermo
     */
    public void setCodigoFormaEnvioTermo(java.lang.String codigoFormaEnvioTermo) {
        this.codigoFormaEnvioTermo = codigoFormaEnvioTermo;
    }


    /**
     * Gets the codigoLoja value for this ParametersPropostaConsignacao.
     * 
     * @return codigoLoja
     */
    public java.lang.Integer getCodigoLoja() {
        return codigoLoja;
    }


    /**
     * Sets the codigoLoja value for this ParametersPropostaConsignacao.
     * 
     * @param codigoLoja
     */
    public void setCodigoLoja(java.lang.Integer codigoLoja) {
        this.codigoLoja = codigoLoja;
    }


    /**
     * Gets the codigoServico value for this ParametersPropostaConsignacao.
     * 
     * @return codigoServico
     */
    public java.lang.String getCodigoServico() {
        return codigoServico;
    }


    /**
     * Sets the codigoServico value for this ParametersPropostaConsignacao.
     * 
     * @param codigoServico
     */
    public void setCodigoServico(java.lang.String codigoServico) {
        this.codigoServico = codigoServico;
    }


    /**
     * Gets the codigoSituacaoServidor value for this ParametersPropostaConsignacao.
     * 
     * @return codigoSituacaoServidor
     */
    public java.lang.Integer getCodigoSituacaoServidor() {
        return codigoSituacaoServidor;
    }


    /**
     * Sets the codigoSituacaoServidor value for this ParametersPropostaConsignacao.
     * 
     * @param codigoSituacaoServidor
     */
    public void setCodigoSituacaoServidor(java.lang.Integer codigoSituacaoServidor) {
        this.codigoSituacaoServidor = codigoSituacaoServidor;
    }


    /**
     * Gets the codigoTabela value for this ParametersPropostaConsignacao.
     * 
     * @return codigoTabela
     */
    public java.lang.Integer getCodigoTabela() {
        return codigoTabela;
    }


    /**
     * Sets the codigoTabela value for this ParametersPropostaConsignacao.
     * 
     * @param codigoTabela
     */
    public void setCodigoTabela(java.lang.Integer codigoTabela) {
        this.codigoTabela = codigoTabela;
    }


    /**
     * Gets the conta value for this ParametersPropostaConsignacao.
     * 
     * @return conta
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.ContaParameter getConta() {
        return conta;
    }


    /**
     * Sets the conta value for this ParametersPropostaConsignacao.
     * 
     * @param conta
     */
    public void setConta(com.proativaservicos.service.asynchronous.bmg.refinanciamento.ContaParameter conta) {
        this.conta = conta;
    }


    /**
     * Gets the cpf value for this ParametersPropostaConsignacao.
     * 
     * @return cpf
     */
    public java.lang.String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ParametersPropostaConsignacao.
     * 
     * @param cpf
     */
    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the cpfAgente value for this ParametersPropostaConsignacao.
     * 
     * @return cpfAgente
     */
    public java.lang.String getCpfAgente() {
        return cpfAgente;
    }


    /**
     * Sets the cpfAgente value for this ParametersPropostaConsignacao.
     * 
     * @param cpfAgente
     */
    public void setCpfAgente(java.lang.String cpfAgente) {
        this.cpfAgente = cpfAgente;
    }


    /**
     * Gets the criterioIof value for this ParametersPropostaConsignacao.
     * 
     * @return criterioIof
     */
    public java.lang.String getCriterioIof() {
        return criterioIof;
    }


    /**
     * Sets the criterioIof value for this ParametersPropostaConsignacao.
     * 
     * @param criterioIof
     */
    public void setCriterioIof(java.lang.String criterioIof) {
        this.criterioIof = criterioIof;
    }


    /**
     * Gets the criterioTac value for this ParametersPropostaConsignacao.
     * 
     * @return criterioTac
     */
    public java.lang.String getCriterioTac() {
        return criterioTac;
    }


    /**
     * Sets the criterioTac value for this ParametersPropostaConsignacao.
     * 
     * @param criterioTac
     */
    public void setCriterioTac(java.lang.String criterioTac) {
        this.criterioTac = criterioTac;
    }


    /**
     * Gets the criterioTlf value for this ParametersPropostaConsignacao.
     * 
     * @return criterioTlf
     */
    public java.lang.String getCriterioTlf() {
        return criterioTlf;
    }


    /**
     * Sets the criterioTlf value for this ParametersPropostaConsignacao.
     * 
     * @param criterioTlf
     */
    public void setCriterioTlf(java.lang.String criterioTlf) {
        this.criterioTlf = criterioTlf;
    }


    /**
     * Gets the dataAdmissao value for this ParametersPropostaConsignacao.
     * 
     * @return dataAdmissao
     */
    public java.util.Calendar getDataAdmissao() {
        return dataAdmissao;
    }


    /**
     * Sets the dataAdmissao value for this ParametersPropostaConsignacao.
     * 
     * @param dataAdmissao
     */
    public void setDataAdmissao(java.util.Calendar dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }


    /**
     * Gets the dataFator value for this ParametersPropostaConsignacao.
     * 
     * @return dataFator
     */
    public java.util.Calendar getDataFator() {
        return dataFator;
    }


    /**
     * Sets the dataFator value for this ParametersPropostaConsignacao.
     * 
     * @param dataFator
     */
    public void setDataFator(java.util.Calendar dataFator) {
        this.dataFator = dataFator;
    }


    /**
     * Gets the dataRenda value for this ParametersPropostaConsignacao.
     * 
     * @return dataRenda
     */
    public java.util.Calendar getDataRenda() {
        return dataRenda;
    }


    /**
     * Sets the dataRenda value for this ParametersPropostaConsignacao.
     * 
     * @param dataRenda
     */
    public void setDataRenda(java.util.Calendar dataRenda) {
        this.dataRenda = dataRenda;
    }


    /**
     * Gets the descontoAdicional value for this ParametersPropostaConsignacao.
     * 
     * @return descontoAdicional
     */
    public double getDescontoAdicional() {
        return descontoAdicional;
    }


    /**
     * Sets the descontoAdicional value for this ParametersPropostaConsignacao.
     * 
     * @param descontoAdicional
     */
    public void setDescontoAdicional(double descontoAdicional) {
        this.descontoAdicional = descontoAdicional;
    }


    /**
     * Gets the descontoCompulsorio value for this ParametersPropostaConsignacao.
     * 
     * @return descontoCompulsorio
     */
    public double getDescontoCompulsorio() {
        return descontoCompulsorio;
    }


    /**
     * Sets the descontoCompulsorio value for this ParametersPropostaConsignacao.
     * 
     * @param descontoCompulsorio
     */
    public void setDescontoCompulsorio(double descontoCompulsorio) {
        this.descontoCompulsorio = descontoCompulsorio;
    }


    /**
     * Gets the descontoOutro value for this ParametersPropostaConsignacao.
     * 
     * @return descontoOutro
     */
    public double getDescontoOutro() {
        return descontoOutro;
    }


    /**
     * Sets the descontoOutro value for this ParametersPropostaConsignacao.
     * 
     * @param descontoOutro
     */
    public void setDescontoOutro(double descontoOutro) {
        this.descontoOutro = descontoOutro;
    }


    /**
     * Gets the descontoPossuiCartao value for this ParametersPropostaConsignacao.
     * 
     * @return descontoPossuiCartao
     */
    public boolean isDescontoPossuiCartao() {
        return descontoPossuiCartao;
    }


    /**
     * Sets the descontoPossuiCartao value for this ParametersPropostaConsignacao.
     * 
     * @param descontoPossuiCartao
     */
    public void setDescontoPossuiCartao(boolean descontoPossuiCartao) {
        this.descontoPossuiCartao = descontoPossuiCartao;
    }


    /**
     * Gets the descontoVoluntario value for this ParametersPropostaConsignacao.
     * 
     * @return descontoVoluntario
     */
    public double getDescontoVoluntario() {
        return descontoVoluntario;
    }


    /**
     * Sets the descontoVoluntario value for this ParametersPropostaConsignacao.
     * 
     * @param descontoVoluntario
     */
    public void setDescontoVoluntario(double descontoVoluntario) {
        this.descontoVoluntario = descontoVoluntario;
    }


    /**
     * Gets the finalidadeCredito value for this ParametersPropostaConsignacao.
     * 
     * @return finalidadeCredito
     */
    public int getFinalidadeCredito() {
        return finalidadeCredito;
    }


    /**
     * Sets the finalidadeCredito value for this ParametersPropostaConsignacao.
     * 
     * @param finalidadeCredito
     */
    public void setFinalidadeCredito(int finalidadeCredito) {
        this.finalidadeCredito = finalidadeCredito;
    }


    /**
     * Gets the formaCredito value for this ParametersPropostaConsignacao.
     * 
     * @return formaCredito
     */
    public int getFormaCredito() {
        return formaCredito;
    }


    /**
     * Sets the formaCredito value for this ParametersPropostaConsignacao.
     * 
     * @param formaCredito
     */
    public void setFormaCredito(int formaCredito) {
        this.formaCredito = formaCredito;
    }


    /**
     * Gets the identificadorMargem value for this ParametersPropostaConsignacao.
     * 
     * @return identificadorMargem
     */
    public java.lang.String getIdentificadorMargem() {
        return identificadorMargem;
    }


    /**
     * Sets the identificadorMargem value for this ParametersPropostaConsignacao.
     * 
     * @param identificadorMargem
     */
    public void setIdentificadorMargem(java.lang.String identificadorMargem) {
        this.identificadorMargem = identificadorMargem;
    }


    /**
     * Gets the ignorarInconsistenciasPN value for this ParametersPropostaConsignacao.
     * 
     * @return ignorarInconsistenciasPN
     */
    public boolean isIgnorarInconsistenciasPN() {
        return ignorarInconsistenciasPN;
    }


    /**
     * Sets the ignorarInconsistenciasPN value for this ParametersPropostaConsignacao.
     * 
     * @param ignorarInconsistenciasPN
     */
    public void setIgnorarInconsistenciasPN(boolean ignorarInconsistenciasPN) {
        this.ignorarInconsistenciasPN = ignorarInconsistenciasPN;
    }


    /**
     * Gets the incluiSeguroVidaFederal value for this ParametersPropostaConsignacao.
     * 
     * @return incluiSeguroVidaFederal
     */
    public java.lang.Boolean getIncluiSeguroVidaFederal() {
        return incluiSeguroVidaFederal;
    }


    /**
     * Sets the incluiSeguroVidaFederal value for this ParametersPropostaConsignacao.
     * 
     * @param incluiSeguroVidaFederal
     */
    public void setIncluiSeguroVidaFederal(java.lang.Boolean incluiSeguroVidaFederal) {
        this.incluiSeguroVidaFederal = incluiSeguroVidaFederal;
    }


    /**
     * Gets the indSeguroAderente value for this ParametersPropostaConsignacao.
     * 
     * @return indSeguroAderente
     */
    public java.lang.Integer getIndSeguroAderente() {
        return indSeguroAderente;
    }


    /**
     * Sets the indSeguroAderente value for this ParametersPropostaConsignacao.
     * 
     * @param indSeguroAderente
     */
    public void setIndSeguroAderente(java.lang.Integer indSeguroAderente) {
        this.indSeguroAderente = indSeguroAderente;
    }


    /**
     * Gets the indicacao value for this ParametersPropostaConsignacao.
     * 
     * @return indicacao
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.IndicacaoParameter getIndicacao() {
        return indicacao;
    }


    /**
     * Sets the indicacao value for this ParametersPropostaConsignacao.
     * 
     * @param indicacao
     */
    public void setIndicacao(com.proativaservicos.service.asynchronous.bmg.refinanciamento.IndicacaoParameter indicacao) {
        this.indicacao = indicacao;
    }


    /**
     * Gets the indicativoIN100 value for this ParametersPropostaConsignacao.
     * 
     * @return indicativoIN100
     */
    public java.lang.Boolean getIndicativoIN100() {
        return indicativoIN100;
    }


    /**
     * Sets the indicativoIN100 value for this ParametersPropostaConsignacao.
     * 
     * @param indicativoIN100
     */
    public void setIndicativoIN100(java.lang.Boolean indicativoIN100) {
        this.indicativoIN100 = indicativoIN100;
    }


    /**
     * Gets the informacoesAdicionais value for this ParametersPropostaConsignacao.
     * 
     * @return informacoesAdicionais
     */
    public java.lang.String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }


    /**
     * Sets the informacoesAdicionais value for this ParametersPropostaConsignacao.
     * 
     * @param informacoesAdicionais
     */
    public void setInformacoesAdicionais(java.lang.String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }


    /**
     * Gets the inserirAtendimentoPlusoft value for this ParametersPropostaConsignacao.
     * 
     * @return inserirAtendimentoPlusoft
     */
    public boolean isInserirAtendimentoPlusoft() {
        return inserirAtendimentoPlusoft;
    }


    /**
     * Sets the inserirAtendimentoPlusoft value for this ParametersPropostaConsignacao.
     * 
     * @param inserirAtendimentoPlusoft
     */
    public void setInserirAtendimentoPlusoft(boolean inserirAtendimentoPlusoft) {
        this.inserirAtendimentoPlusoft = inserirAtendimentoPlusoft;
    }


    /**
     * Gets the ipUsuario value for this ParametersPropostaConsignacao.
     * 
     * @return ipUsuario
     */
    public java.lang.String getIpUsuario() {
        return ipUsuario;
    }


    /**
     * Sets the ipUsuario value for this ParametersPropostaConsignacao.
     * 
     * @param ipUsuario
     */
    public void setIpUsuario(java.lang.String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }


    /**
     * Gets the latitude value for this ParametersPropostaConsignacao.
     * 
     * @return latitude
     */
    public java.lang.Double getLatitude() {
        return latitude;
    }


    /**
     * Sets the latitude value for this ParametersPropostaConsignacao.
     * 
     * @param latitude
     */
    public void setLatitude(java.lang.Double latitude) {
        this.latitude = latitude;
    }


    /**
     * Gets the listaContrato value for this ParametersPropostaConsignacao.
     * 
     * @return listaContrato
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato[] getListaContrato() {
        return listaContrato;
    }


    /**
     * Sets the listaContrato value for this ParametersPropostaConsignacao.
     * 
     * @param listaContrato
     */
    public void setListaContrato(com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato[] listaContrato) {
        this.listaContrato = listaContrato;
    }


    /**
     * Gets the listaEletro value for this ParametersPropostaConsignacao.
     * 
     * @return listaEletro
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.EletroParameter[] getListaEletro() {
        return listaEletro;
    }


    /**
     * Sets the listaEletro value for this ParametersPropostaConsignacao.
     * 
     * @param listaEletro
     */
    public void setListaEletro(com.proativaservicos.service.asynchronous.bmg.refinanciamento.EletroParameter[] listaEletro) {
        this.listaEletro = listaEletro;
    }


    /**
     * Gets the loginConsig value for this ParametersPropostaConsignacao.
     * 
     * @return loginConsig
     */
    public java.lang.String getLoginConsig() {
        return loginConsig;
    }


    /**
     * Sets the loginConsig value for this ParametersPropostaConsignacao.
     * 
     * @param loginConsig
     */
    public void setLoginConsig(java.lang.String loginConsig) {
        this.loginConsig = loginConsig;
    }


    /**
     * Gets the longitude value for this ParametersPropostaConsignacao.
     * 
     * @return longitude
     */
    public java.lang.Double getLongitude() {
        return longitude;
    }


    /**
     * Sets the longitude value for this ParametersPropostaConsignacao.
     * 
     * @param longitude
     */
    public void setLongitude(java.lang.Double longitude) {
        this.longitude = longitude;
    }


    /**
     * Gets the margem value for this ParametersPropostaConsignacao.
     * 
     * @return margem
     */
    public double getMargem() {
        return margem;
    }


    /**
     * Sets the margem value for this ParametersPropostaConsignacao.
     * 
     * @param margem
     */
    public void setMargem(double margem) {
        this.margem = margem;
    }


    /**
     * Gets the matricula value for this ParametersPropostaConsignacao.
     * 
     * @return matricula
     */
    public java.lang.String getMatricula() {
        return matricula;
    }


    /**
     * Sets the matricula value for this ParametersPropostaConsignacao.
     * 
     * @param matricula
     */
    public void setMatricula(java.lang.String matricula) {
        this.matricula = matricula;
    }


    /**
     * Gets the matriculaInstituidor value for this ParametersPropostaConsignacao.
     * 
     * @return matriculaInstituidor
     */
    public java.lang.String getMatriculaInstituidor() {
        return matriculaInstituidor;
    }


    /**
     * Sets the matriculaInstituidor value for this ParametersPropostaConsignacao.
     * 
     * @param matriculaInstituidor
     */
    public void setMatriculaInstituidor(java.lang.String matriculaInstituidor) {
        this.matriculaInstituidor = matriculaInstituidor;
    }


    /**
     * Gets the numeroApolice value for this ParametersPropostaConsignacao.
     * 
     * @return numeroApolice
     */
    public java.lang.Long getNumeroApolice() {
        return numeroApolice;
    }


    /**
     * Sets the numeroApolice value for this ParametersPropostaConsignacao.
     * 
     * @param numeroApolice
     */
    public void setNumeroApolice(java.lang.Long numeroApolice) {
        this.numeroApolice = numeroApolice;
    }


    /**
     * Gets the numeroCartao value for this ParametersPropostaConsignacao.
     * 
     * @return numeroCartao
     */
    public java.lang.String getNumeroCartao() {
        return numeroCartao;
    }


    /**
     * Sets the numeroCartao value for this ParametersPropostaConsignacao.
     * 
     * @param numeroCartao
     */
    public void setNumeroCartao(java.lang.String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }


    /**
     * Gets the numeroPeculio value for this ParametersPropostaConsignacao.
     * 
     * @return numeroPeculio
     */
    public java.lang.String getNumeroPeculio() {
        return numeroPeculio;
    }


    /**
     * Sets the numeroPeculio value for this ParametersPropostaConsignacao.
     * 
     * @param numeroPeculio
     */
    public void setNumeroPeculio(java.lang.String numeroPeculio) {
        this.numeroPeculio = numeroPeculio;
    }


    /**
     * Gets the numeroPrestacoes value for this ParametersPropostaConsignacao.
     * 
     * @return numeroPrestacoes
     */
    public short getNumeroPrestacoes() {
        return numeroPrestacoes;
    }


    /**
     * Sets the numeroPrestacoes value for this ParametersPropostaConsignacao.
     * 
     * @param numeroPrestacoes
     */
    public void setNumeroPrestacoes(short numeroPrestacoes) {
        this.numeroPrestacoes = numeroPrestacoes;
    }


    /**
     * Gets the numeroPropostaExterna value for this ParametersPropostaConsignacao.
     * 
     * @return numeroPropostaExterna
     */
    public java.lang.String getNumeroPropostaExterna() {
        return numeroPropostaExterna;
    }


    /**
     * Sets the numeroPropostaExterna value for this ParametersPropostaConsignacao.
     * 
     * @param numeroPropostaExterna
     */
    public void setNumeroPropostaExterna(java.lang.String numeroPropostaExterna) {
        this.numeroPropostaExterna = numeroPropostaExterna;
    }


    /**
     * Gets the numeroSorteio value for this ParametersPropostaConsignacao.
     * 
     * @return numeroSorteio
     */
    public java.lang.Long getNumeroSorteio() {
        return numeroSorteio;
    }


    /**
     * Sets the numeroSorteio value for this ParametersPropostaConsignacao.
     * 
     * @param numeroSorteio
     */
    public void setNumeroSorteio(java.lang.Long numeroSorteio) {
        this.numeroSorteio = numeroSorteio;
    }


    /**
     * Gets the possuiCartao value for this ParametersPropostaConsignacao.
     * 
     * @return possuiCartao
     */
    public boolean isPossuiCartao() {
        return possuiCartao;
    }


    /**
     * Sets the possuiCartao value for this ParametersPropostaConsignacao.
     * 
     * @param possuiCartao
     */
    public void setPossuiCartao(boolean possuiCartao) {
        this.possuiCartao = possuiCartao;
    }


    /**
     * Gets the produto value for this ParametersPropostaConsignacao.
     * 
     * @return produto
     */
    public java.lang.Integer getProduto() {
        return produto;
    }


    /**
     * Sets the produto value for this ParametersPropostaConsignacao.
     * 
     * @param produto
     */
    public void setProduto(java.lang.Integer produto) {
        this.produto = produto;
    }


    /**
     * Gets the protocoloMultiProdutos value for this ParametersPropostaConsignacao.
     * 
     * @return protocoloMultiProdutos
     */
    public java.lang.Long getProtocoloMultiProdutos() {
        return protocoloMultiProdutos;
    }


    /**
     * Sets the protocoloMultiProdutos value for this ParametersPropostaConsignacao.
     * 
     * @param protocoloMultiProdutos
     */
    public void setProtocoloMultiProdutos(java.lang.Long protocoloMultiProdutos) {
        this.protocoloMultiProdutos = protocoloMultiProdutos;
    }


    /**
     * Gets the retornoCetPn value for this ParametersPropostaConsignacao.
     * 
     * @return retornoCetPn
     */
    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.RetornoCetPn getRetornoCetPn() {
        return retornoCetPn;
    }


    /**
     * Sets the retornoCetPn value for this ParametersPropostaConsignacao.
     * 
     * @param retornoCetPn
     */
    public void setRetornoCetPn(com.proativaservicos.service.asynchronous.bmg.refinanciamento.RetornoCetPn retornoCetPn) {
        this.retornoCetPn = retornoCetPn;
    }


    /**
     * Gets the senhaConsig value for this ParametersPropostaConsignacao.
     * 
     * @return senhaConsig
     */
    public java.lang.String getSenhaConsig() {
        return senhaConsig;
    }


    /**
     * Sets the senhaConsig value for this ParametersPropostaConsignacao.
     * 
     * @param senhaConsig
     */
    public void setSenhaConsig(java.lang.String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }


    /**
     * Gets the senhaSME value for this ParametersPropostaConsignacao.
     * 
     * @return senhaSME
     */
    public java.lang.String getSenhaSME() {
        return senhaSME;
    }


    /**
     * Sets the senhaSME value for this ParametersPropostaConsignacao.
     * 
     * @param senhaSME
     */
    public void setSenhaSME(java.lang.String senhaSME) {
        this.senhaSME = senhaSME;
    }


    /**
     * Gets the sequencialOrgao value for this ParametersPropostaConsignacao.
     * 
     * @return sequencialOrgao
     */
    public java.lang.Integer getSequencialOrgao() {
        return sequencialOrgao;
    }


    /**
     * Sets the sequencialOrgao value for this ParametersPropostaConsignacao.
     * 
     * @param sequencialOrgao
     */
    public void setSequencialOrgao(java.lang.Integer sequencialOrgao) {
        this.sequencialOrgao = sequencialOrgao;
    }


    /**
     * Gets the tipoBeneficio value for this ParametersPropostaConsignacao.
     * 
     * @return tipoBeneficio
     */
    public java.lang.Integer getTipoBeneficio() {
        return tipoBeneficio;
    }


    /**
     * Sets the tipoBeneficio value for this ParametersPropostaConsignacao.
     * 
     * @param tipoBeneficio
     */
    public void setTipoBeneficio(java.lang.Integer tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }


    /**
     * Gets the tipoDomicilioBancario value for this ParametersPropostaConsignacao.
     * 
     * @return tipoDomicilioBancario
     */
    public java.lang.Short getTipoDomicilioBancario() {
        return tipoDomicilioBancario;
    }


    /**
     * Sets the tipoDomicilioBancario value for this ParametersPropostaConsignacao.
     * 
     * @param tipoDomicilioBancario
     */
    public void setTipoDomicilioBancario(java.lang.Short tipoDomicilioBancario) {
        this.tipoDomicilioBancario = tipoDomicilioBancario;
    }


    /**
     * Gets the token value for this ParametersPropostaConsignacao.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this ParametersPropostaConsignacao.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the ufContaBeneficio value for this ParametersPropostaConsignacao.
     * 
     * @return ufContaBeneficio
     */
    public java.lang.String getUfContaBeneficio() {
        return ufContaBeneficio;
    }


    /**
     * Sets the ufContaBeneficio value for this ParametersPropostaConsignacao.
     * 
     * @param ufContaBeneficio
     */
    public void setUfContaBeneficio(java.lang.String ufContaBeneficio) {
        this.ufContaBeneficio = ufContaBeneficio;
    }


    /**
     * Gets the unidadePagadora value for this ParametersPropostaConsignacao.
     * 
     * @return unidadePagadora
     */
    public java.lang.String getUnidadePagadora() {
        return unidadePagadora;
    }


    /**
     * Sets the unidadePagadora value for this ParametersPropostaConsignacao.
     * 
     * @param unidadePagadora
     */
    public void setUnidadePagadora(java.lang.String unidadePagadora) {
        this.unidadePagadora = unidadePagadora;
    }


    /**
     * Gets the utilizaUserConsig value for this ParametersPropostaConsignacao.
     * 
     * @return utilizaUserConsig
     */
    public java.lang.Boolean getUtilizaUserConsig() {
        return utilizaUserConsig;
    }


    /**
     * Sets the utilizaUserConsig value for this ParametersPropostaConsignacao.
     * 
     * @param utilizaUserConsig
     */
    public void setUtilizaUserConsig(java.lang.Boolean utilizaUserConsig) {
        this.utilizaUserConsig = utilizaUserConsig;
    }


    /**
     * Gets the validouSenha value for this ParametersPropostaConsignacao.
     * 
     * @return validouSenha
     */
    public java.lang.Boolean getValidouSenha() {
        return validouSenha;
    }


    /**
     * Sets the validouSenha value for this ParametersPropostaConsignacao.
     * 
     * @param validouSenha
     */
    public void setValidouSenha(java.lang.Boolean validouSenha) {
        this.validouSenha = validouSenha;
    }


    /**
     * Gets the valorAgregacaoDeMargemParaSaqueComplementar value for this ParametersPropostaConsignacao.
     * 
     * @return valorAgregacaoDeMargemParaSaqueComplementar
     */
    public java.lang.Double getValorAgregacaoDeMargemParaSaqueComplementar() {
        return valorAgregacaoDeMargemParaSaqueComplementar;
    }


    /**
     * Sets the valorAgregacaoDeMargemParaSaqueComplementar value for this ParametersPropostaConsignacao.
     * 
     * @param valorAgregacaoDeMargemParaSaqueComplementar
     */
    public void setValorAgregacaoDeMargemParaSaqueComplementar(java.lang.Double valorAgregacaoDeMargemParaSaqueComplementar) {
        this.valorAgregacaoDeMargemParaSaqueComplementar = valorAgregacaoDeMargemParaSaqueComplementar;
    }


    /**
     * Gets the valorCapitalSegurado value for this ParametersPropostaConsignacao.
     * 
     * @return valorCapitalSegurado
     */
    public java.lang.Double getValorCapitalSegurado() {
        return valorCapitalSegurado;
    }


    /**
     * Sets the valorCapitalSegurado value for this ParametersPropostaConsignacao.
     * 
     * @param valorCapitalSegurado
     */
    public void setValorCapitalSegurado(java.lang.Double valorCapitalSegurado) {
        this.valorCapitalSegurado = valorCapitalSegurado;
    }


    /**
     * Gets the valorIof value for this ParametersPropostaConsignacao.
     * 
     * @return valorIof
     */
    public double getValorIof() {
        return valorIof;
    }


    /**
     * Sets the valorIof value for this ParametersPropostaConsignacao.
     * 
     * @param valorIof
     */
    public void setValorIof(double valorIof) {
        this.valorIof = valorIof;
    }


    /**
     * Gets the valorPrestacao value for this ParametersPropostaConsignacao.
     * 
     * @return valorPrestacao
     */
    public double getValorPrestacao() {
        return valorPrestacao;
    }


    /**
     * Sets the valorPrestacao value for this ParametersPropostaConsignacao.
     * 
     * @param valorPrestacao
     */
    public void setValorPrestacao(double valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }


    /**
     * Gets the valorRenda value for this ParametersPropostaConsignacao.
     * 
     * @return valorRenda
     */
    public double getValorRenda() {
        return valorRenda;
    }


    /**
     * Sets the valorRenda value for this ParametersPropostaConsignacao.
     * 
     * @param valorRenda
     */
    public void setValorRenda(double valorRenda) {
        this.valorRenda = valorRenda;
    }


    /**
     * Gets the valorResidual value for this ParametersPropostaConsignacao.
     * 
     * @return valorResidual
     */
    public java.lang.Double getValorResidual() {
        return valorResidual;
    }


    /**
     * Sets the valorResidual value for this ParametersPropostaConsignacao.
     * 
     * @param valorResidual
     */
    public void setValorResidual(java.lang.Double valorResidual) {
        this.valorResidual = valorResidual;
    }


    /**
     * Gets the valorSeguroVidaFederal value for this ParametersPropostaConsignacao.
     * 
     * @return valorSeguroVidaFederal
     */
    public java.lang.Double getValorSeguroVidaFederal() {
        return valorSeguroVidaFederal;
    }


    /**
     * Sets the valorSeguroVidaFederal value for this ParametersPropostaConsignacao.
     * 
     * @param valorSeguroVidaFederal
     */
    public void setValorSeguroVidaFederal(java.lang.Double valorSeguroVidaFederal) {
        this.valorSeguroVidaFederal = valorSeguroVidaFederal;
    }


    /**
     * Gets the valorSolicitado value for this ParametersPropostaConsignacao.
     * 
     * @return valorSolicitado
     */
    public double getValorSolicitado() {
        return valorSolicitado;
    }


    /**
     * Sets the valorSolicitado value for this ParametersPropostaConsignacao.
     * 
     * @param valorSolicitado
     */
    public void setValorSolicitado(double valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }


    /**
     * Gets the vinculoMatricula value for this ParametersPropostaConsignacao.
     * 
     * @return vinculoMatricula
     */
    public java.lang.String getVinculoMatricula() {
        return vinculoMatricula;
    }


    /**
     * Sets the vinculoMatricula value for this ParametersPropostaConsignacao.
     * 
     * @param vinculoMatricula
     */
    public void setVinculoMatricula(java.lang.String vinculoMatricula) {
        this.vinculoMatricula = vinculoMatricula;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametersPropostaConsignacao)) return false;
        ParametersPropostaConsignacao other = (ParametersPropostaConsignacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.aberturaContaPagamento == other.getAberturaContaPagamento() &&
            ((this.agencia==null && other.getAgencia()==null) || 
             (this.agencia!=null &&
              this.agencia.equals(other.getAgencia()))) &&
            ((this.agregacaoDeMargemParaSaqueComplementar==null && other.getAgregacaoDeMargemParaSaqueComplementar()==null) || 
             (this.agregacaoDeMargemParaSaqueComplementar!=null &&
              this.agregacaoDeMargemParaSaqueComplementar.equals(other.getAgregacaoDeMargemParaSaqueComplementar()))) &&
            this.associado == other.isAssociado() &&
            ((this.banco==null && other.getBanco()==null) || 
             (this.banco!=null &&
              this.banco.equals(other.getBanco()))) &&
            this.bancoOrdemPagamento == other.getBancoOrdemPagamento() &&
            ((this.boletoPagamento==null && other.getBoletoPagamento()==null) || 
             (this.boletoPagamento!=null &&
              java.util.Arrays.equals(this.boletoPagamento, other.getBoletoPagamento()))) &&
            ((this.cargo==null && other.getCargo()==null) || 
             (this.cargo!=null &&
              this.cargo.equals(other.getCargo()))) &&
            ((this.cliente==null && other.getCliente()==null) || 
             (this.cliente!=null &&
              this.cliente.equals(other.getCliente()))) &&
            this.clientePreCadastrado == other.isClientePreCadastrado() &&
            ((this.cnpjEmpregador==null && other.getCnpjEmpregador()==null) || 
             (this.cnpjEmpregador!=null &&
              this.cnpjEmpregador.equals(other.getCnpjEmpregador()))) &&
            ((this.codEnt==null && other.getCodEnt()==null) || 
             (this.codEnt!=null &&
              this.codEnt.equals(other.getCodEnt()))) &&
            ((this.codigoAverbacao==null && other.getCodigoAverbacao()==null) || 
             (this.codigoAverbacao!=null &&
              this.codigoAverbacao.equals(other.getCodigoAverbacao()))) &&
            ((this.codigoEntidade==null && other.getCodigoEntidade()==null) || 
             (this.codigoEntidade!=null &&
              this.codigoEntidade.equals(other.getCodigoEntidade()))) &&
            ((this.codigoFormaEnvioTermo==null && other.getCodigoFormaEnvioTermo()==null) || 
             (this.codigoFormaEnvioTermo!=null &&
              this.codigoFormaEnvioTermo.equals(other.getCodigoFormaEnvioTermo()))) &&
            ((this.codigoLoja==null && other.getCodigoLoja()==null) || 
             (this.codigoLoja!=null &&
              this.codigoLoja.equals(other.getCodigoLoja()))) &&
            ((this.codigoServico==null && other.getCodigoServico()==null) || 
             (this.codigoServico!=null &&
              this.codigoServico.equals(other.getCodigoServico()))) &&
            ((this.codigoSituacaoServidor==null && other.getCodigoSituacaoServidor()==null) || 
             (this.codigoSituacaoServidor!=null &&
              this.codigoSituacaoServidor.equals(other.getCodigoSituacaoServidor()))) &&
            ((this.codigoTabela==null && other.getCodigoTabela()==null) || 
             (this.codigoTabela!=null &&
              this.codigoTabela.equals(other.getCodigoTabela()))) &&
            ((this.conta==null && other.getConta()==null) || 
             (this.conta!=null &&
              this.conta.equals(other.getConta()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.cpfAgente==null && other.getCpfAgente()==null) || 
             (this.cpfAgente!=null &&
              this.cpfAgente.equals(other.getCpfAgente()))) &&
            ((this.criterioIof==null && other.getCriterioIof()==null) || 
             (this.criterioIof!=null &&
              this.criterioIof.equals(other.getCriterioIof()))) &&
            ((this.criterioTac==null && other.getCriterioTac()==null) || 
             (this.criterioTac!=null &&
              this.criterioTac.equals(other.getCriterioTac()))) &&
            ((this.criterioTlf==null && other.getCriterioTlf()==null) || 
             (this.criterioTlf!=null &&
              this.criterioTlf.equals(other.getCriterioTlf()))) &&
            ((this.dataAdmissao==null && other.getDataAdmissao()==null) || 
             (this.dataAdmissao!=null &&
              this.dataAdmissao.equals(other.getDataAdmissao()))) &&
            ((this.dataFator==null && other.getDataFator()==null) || 
             (this.dataFator!=null &&
              this.dataFator.equals(other.getDataFator()))) &&
            ((this.dataRenda==null && other.getDataRenda()==null) || 
             (this.dataRenda!=null &&
              this.dataRenda.equals(other.getDataRenda()))) &&
            this.descontoAdicional == other.getDescontoAdicional() &&
            this.descontoCompulsorio == other.getDescontoCompulsorio() &&
            this.descontoOutro == other.getDescontoOutro() &&
            this.descontoPossuiCartao == other.isDescontoPossuiCartao() &&
            this.descontoVoluntario == other.getDescontoVoluntario() &&
            this.finalidadeCredito == other.getFinalidadeCredito() &&
            this.formaCredito == other.getFormaCredito() &&
            ((this.identificadorMargem==null && other.getIdentificadorMargem()==null) || 
             (this.identificadorMargem!=null &&
              this.identificadorMargem.equals(other.getIdentificadorMargem()))) &&
            this.ignorarInconsistenciasPN == other.isIgnorarInconsistenciasPN() &&
            ((this.incluiSeguroVidaFederal==null && other.getIncluiSeguroVidaFederal()==null) || 
             (this.incluiSeguroVidaFederal!=null &&
              this.incluiSeguroVidaFederal.equals(other.getIncluiSeguroVidaFederal()))) &&
            ((this.indSeguroAderente==null && other.getIndSeguroAderente()==null) || 
             (this.indSeguroAderente!=null &&
              this.indSeguroAderente.equals(other.getIndSeguroAderente()))) &&
            ((this.indicacao==null && other.getIndicacao()==null) || 
             (this.indicacao!=null &&
              this.indicacao.equals(other.getIndicacao()))) &&
            ((this.indicativoIN100==null && other.getIndicativoIN100()==null) || 
             (this.indicativoIN100!=null &&
              this.indicativoIN100.equals(other.getIndicativoIN100()))) &&
            ((this.informacoesAdicionais==null && other.getInformacoesAdicionais()==null) || 
             (this.informacoesAdicionais!=null &&
              this.informacoesAdicionais.equals(other.getInformacoesAdicionais()))) &&
            this.inserirAtendimentoPlusoft == other.isInserirAtendimentoPlusoft() &&
            ((this.ipUsuario==null && other.getIpUsuario()==null) || 
             (this.ipUsuario!=null &&
              this.ipUsuario.equals(other.getIpUsuario()))) &&
            ((this.latitude==null && other.getLatitude()==null) || 
             (this.latitude!=null &&
              this.latitude.equals(other.getLatitude()))) &&
            ((this.listaContrato==null && other.getListaContrato()==null) || 
             (this.listaContrato!=null &&
              java.util.Arrays.equals(this.listaContrato, other.getListaContrato()))) &&
            ((this.listaEletro==null && other.getListaEletro()==null) || 
             (this.listaEletro!=null &&
              java.util.Arrays.equals(this.listaEletro, other.getListaEletro()))) &&
            ((this.loginConsig==null && other.getLoginConsig()==null) || 
             (this.loginConsig!=null &&
              this.loginConsig.equals(other.getLoginConsig()))) &&
            ((this.longitude==null && other.getLongitude()==null) || 
             (this.longitude!=null &&
              this.longitude.equals(other.getLongitude()))) &&
            this.margem == other.getMargem() &&
            ((this.matricula==null && other.getMatricula()==null) || 
             (this.matricula!=null &&
              this.matricula.equals(other.getMatricula()))) &&
            ((this.matriculaInstituidor==null && other.getMatriculaInstituidor()==null) || 
             (this.matriculaInstituidor!=null &&
              this.matriculaInstituidor.equals(other.getMatriculaInstituidor()))) &&
            ((this.numeroApolice==null && other.getNumeroApolice()==null) || 
             (this.numeroApolice!=null &&
              this.numeroApolice.equals(other.getNumeroApolice()))) &&
            ((this.numeroCartao==null && other.getNumeroCartao()==null) || 
             (this.numeroCartao!=null &&
              this.numeroCartao.equals(other.getNumeroCartao()))) &&
            ((this.numeroPeculio==null && other.getNumeroPeculio()==null) || 
             (this.numeroPeculio!=null &&
              this.numeroPeculio.equals(other.getNumeroPeculio()))) &&
            this.numeroPrestacoes == other.getNumeroPrestacoes() &&
            ((this.numeroPropostaExterna==null && other.getNumeroPropostaExterna()==null) || 
             (this.numeroPropostaExterna!=null &&
              this.numeroPropostaExterna.equals(other.getNumeroPropostaExterna()))) &&
            ((this.numeroSorteio==null && other.getNumeroSorteio()==null) || 
             (this.numeroSorteio!=null &&
              this.numeroSorteio.equals(other.getNumeroSorteio()))) &&
            this.possuiCartao == other.isPossuiCartao() &&
            ((this.produto==null && other.getProduto()==null) || 
             (this.produto!=null &&
              this.produto.equals(other.getProduto()))) &&
            ((this.protocoloMultiProdutos==null && other.getProtocoloMultiProdutos()==null) || 
             (this.protocoloMultiProdutos!=null &&
              this.protocoloMultiProdutos.equals(other.getProtocoloMultiProdutos()))) &&
            ((this.retornoCetPn==null && other.getRetornoCetPn()==null) || 
             (this.retornoCetPn!=null &&
              this.retornoCetPn.equals(other.getRetornoCetPn()))) &&
            ((this.senhaConsig==null && other.getSenhaConsig()==null) || 
             (this.senhaConsig!=null &&
              this.senhaConsig.equals(other.getSenhaConsig()))) &&
            ((this.senhaSME==null && other.getSenhaSME()==null) || 
             (this.senhaSME!=null &&
              this.senhaSME.equals(other.getSenhaSME()))) &&
            ((this.sequencialOrgao==null && other.getSequencialOrgao()==null) || 
             (this.sequencialOrgao!=null &&
              this.sequencialOrgao.equals(other.getSequencialOrgao()))) &&
            ((this.tipoBeneficio==null && other.getTipoBeneficio()==null) || 
             (this.tipoBeneficio!=null &&
              this.tipoBeneficio.equals(other.getTipoBeneficio()))) &&
            ((this.tipoDomicilioBancario==null && other.getTipoDomicilioBancario()==null) || 
             (this.tipoDomicilioBancario!=null &&
              this.tipoDomicilioBancario.equals(other.getTipoDomicilioBancario()))) &&
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            ((this.ufContaBeneficio==null && other.getUfContaBeneficio()==null) || 
             (this.ufContaBeneficio!=null &&
              this.ufContaBeneficio.equals(other.getUfContaBeneficio()))) &&
            ((this.unidadePagadora==null && other.getUnidadePagadora()==null) || 
             (this.unidadePagadora!=null &&
              this.unidadePagadora.equals(other.getUnidadePagadora()))) &&
            ((this.utilizaUserConsig==null && other.getUtilizaUserConsig()==null) || 
             (this.utilizaUserConsig!=null &&
              this.utilizaUserConsig.equals(other.getUtilizaUserConsig()))) &&
            ((this.validouSenha==null && other.getValidouSenha()==null) || 
             (this.validouSenha!=null &&
              this.validouSenha.equals(other.getValidouSenha()))) &&
            ((this.valorAgregacaoDeMargemParaSaqueComplementar==null && other.getValorAgregacaoDeMargemParaSaqueComplementar()==null) || 
             (this.valorAgregacaoDeMargemParaSaqueComplementar!=null &&
              this.valorAgregacaoDeMargemParaSaqueComplementar.equals(other.getValorAgregacaoDeMargemParaSaqueComplementar()))) &&
            ((this.valorCapitalSegurado==null && other.getValorCapitalSegurado()==null) || 
             (this.valorCapitalSegurado!=null &&
              this.valorCapitalSegurado.equals(other.getValorCapitalSegurado()))) &&
            this.valorIof == other.getValorIof() &&
            this.valorPrestacao == other.getValorPrestacao() &&
            this.valorRenda == other.getValorRenda() &&
            ((this.valorResidual==null && other.getValorResidual()==null) || 
             (this.valorResidual!=null &&
              this.valorResidual.equals(other.getValorResidual()))) &&
            ((this.valorSeguroVidaFederal==null && other.getValorSeguroVidaFederal()==null) || 
             (this.valorSeguroVidaFederal!=null &&
              this.valorSeguroVidaFederal.equals(other.getValorSeguroVidaFederal()))) &&
            this.valorSolicitado == other.getValorSolicitado() &&
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
        _hashCode += getAberturaContaPagamento();
        if (getAgencia() != null) {
            _hashCode += getAgencia().hashCode();
        }
        if (getAgregacaoDeMargemParaSaqueComplementar() != null) {
            _hashCode += getAgregacaoDeMargemParaSaqueComplementar().hashCode();
        }
        _hashCode += (isAssociado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getBanco() != null) {
            _hashCode += getBanco().hashCode();
        }
        _hashCode += getBancoOrdemPagamento();
        if (getBoletoPagamento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBoletoPagamento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBoletoPagamento(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCargo() != null) {
            _hashCode += getCargo().hashCode();
        }
        if (getCliente() != null) {
            _hashCode += getCliente().hashCode();
        }
        _hashCode += (isClientePreCadastrado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCnpjEmpregador() != null) {
            _hashCode += getCnpjEmpregador().hashCode();
        }
        if (getCodEnt() != null) {
            _hashCode += getCodEnt().hashCode();
        }
        if (getCodigoAverbacao() != null) {
            _hashCode += getCodigoAverbacao().hashCode();
        }
        if (getCodigoEntidade() != null) {
            _hashCode += getCodigoEntidade().hashCode();
        }
        if (getCodigoFormaEnvioTermo() != null) {
            _hashCode += getCodigoFormaEnvioTermo().hashCode();
        }
        if (getCodigoLoja() != null) {
            _hashCode += getCodigoLoja().hashCode();
        }
        if (getCodigoServico() != null) {
            _hashCode += getCodigoServico().hashCode();
        }
        if (getCodigoSituacaoServidor() != null) {
            _hashCode += getCodigoSituacaoServidor().hashCode();
        }
        if (getCodigoTabela() != null) {
            _hashCode += getCodigoTabela().hashCode();
        }
        if (getConta() != null) {
            _hashCode += getConta().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getCpfAgente() != null) {
            _hashCode += getCpfAgente().hashCode();
        }
        if (getCriterioIof() != null) {
            _hashCode += getCriterioIof().hashCode();
        }
        if (getCriterioTac() != null) {
            _hashCode += getCriterioTac().hashCode();
        }
        if (getCriterioTlf() != null) {
            _hashCode += getCriterioTlf().hashCode();
        }
        if (getDataAdmissao() != null) {
            _hashCode += getDataAdmissao().hashCode();
        }
        if (getDataFator() != null) {
            _hashCode += getDataFator().hashCode();
        }
        if (getDataRenda() != null) {
            _hashCode += getDataRenda().hashCode();
        }
        _hashCode += new Double(getDescontoAdicional()).hashCode();
        _hashCode += new Double(getDescontoCompulsorio()).hashCode();
        _hashCode += new Double(getDescontoOutro()).hashCode();
        _hashCode += (isDescontoPossuiCartao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Double(getDescontoVoluntario()).hashCode();
        _hashCode += getFinalidadeCredito();
        _hashCode += getFormaCredito();
        if (getIdentificadorMargem() != null) {
            _hashCode += getIdentificadorMargem().hashCode();
        }
        _hashCode += (isIgnorarInconsistenciasPN() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getIncluiSeguroVidaFederal() != null) {
            _hashCode += getIncluiSeguroVidaFederal().hashCode();
        }
        if (getIndSeguroAderente() != null) {
            _hashCode += getIndSeguroAderente().hashCode();
        }
        if (getIndicacao() != null) {
            _hashCode += getIndicacao().hashCode();
        }
        if (getIndicativoIN100() != null) {
            _hashCode += getIndicativoIN100().hashCode();
        }
        if (getInformacoesAdicionais() != null) {
            _hashCode += getInformacoesAdicionais().hashCode();
        }
        _hashCode += (isInserirAtendimentoPlusoft() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getIpUsuario() != null) {
            _hashCode += getIpUsuario().hashCode();
        }
        if (getLatitude() != null) {
            _hashCode += getLatitude().hashCode();
        }
        if (getListaContrato() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaContrato());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaContrato(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaEletro() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaEletro());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaEletro(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLoginConsig() != null) {
            _hashCode += getLoginConsig().hashCode();
        }
        if (getLongitude() != null) {
            _hashCode += getLongitude().hashCode();
        }
        _hashCode += new Double(getMargem()).hashCode();
        if (getMatricula() != null) {
            _hashCode += getMatricula().hashCode();
        }
        if (getMatriculaInstituidor() != null) {
            _hashCode += getMatriculaInstituidor().hashCode();
        }
        if (getNumeroApolice() != null) {
            _hashCode += getNumeroApolice().hashCode();
        }
        if (getNumeroCartao() != null) {
            _hashCode += getNumeroCartao().hashCode();
        }
        if (getNumeroPeculio() != null) {
            _hashCode += getNumeroPeculio().hashCode();
        }
        _hashCode += getNumeroPrestacoes();
        if (getNumeroPropostaExterna() != null) {
            _hashCode += getNumeroPropostaExterna().hashCode();
        }
        if (getNumeroSorteio() != null) {
            _hashCode += getNumeroSorteio().hashCode();
        }
        _hashCode += (isPossuiCartao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getProduto() != null) {
            _hashCode += getProduto().hashCode();
        }
        if (getProtocoloMultiProdutos() != null) {
            _hashCode += getProtocoloMultiProdutos().hashCode();
        }
        if (getRetornoCetPn() != null) {
            _hashCode += getRetornoCetPn().hashCode();
        }
        if (getSenhaConsig() != null) {
            _hashCode += getSenhaConsig().hashCode();
        }
        if (getSenhaSME() != null) {
            _hashCode += getSenhaSME().hashCode();
        }
        if (getSequencialOrgao() != null) {
            _hashCode += getSequencialOrgao().hashCode();
        }
        if (getTipoBeneficio() != null) {
            _hashCode += getTipoBeneficio().hashCode();
        }
        if (getTipoDomicilioBancario() != null) {
            _hashCode += getTipoDomicilioBancario().hashCode();
        }
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getUfContaBeneficio() != null) {
            _hashCode += getUfContaBeneficio().hashCode();
        }
        if (getUnidadePagadora() != null) {
            _hashCode += getUnidadePagadora().hashCode();
        }
        if (getUtilizaUserConsig() != null) {
            _hashCode += getUtilizaUserConsig().hashCode();
        }
        if (getValidouSenha() != null) {
            _hashCode += getValidouSenha().hashCode();
        }
        if (getValorAgregacaoDeMargemParaSaqueComplementar() != null) {
            _hashCode += getValorAgregacaoDeMargemParaSaqueComplementar().hashCode();
        }
        if (getValorCapitalSegurado() != null) {
            _hashCode += getValorCapitalSegurado().hashCode();
        }
        _hashCode += new Double(getValorIof()).hashCode();
        _hashCode += new Double(getValorPrestacao()).hashCode();
        _hashCode += new Double(getValorRenda()).hashCode();
        if (getValorResidual() != null) {
            _hashCode += getValorResidual().hashCode();
        }
        if (getValorSeguroVidaFederal() != null) {
            _hashCode += getValorSeguroVidaFederal().hashCode();
        }
        _hashCode += new Double(getValorSolicitado()).hashCode();
        if (getVinculoMatricula() != null) {
            _hashCode += getVinculoMatricula().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametersPropostaConsignacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametersPropostaConsignacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aberturaContaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aberturaContaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgenciaParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agregacaoDeMargemParaSaqueComplementar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agregacaoDeMargemParaSaqueComplementar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "associado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("banco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "banco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BancoParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bancoOrdemPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bancoOrdemPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("boletoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "boletoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "BoletoParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cargo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cargo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ClienteParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientePreCadastrado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientePreCadastrado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjEmpregador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjEmpregador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codEnt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codEnt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAverbacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAverbacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEntidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoFormaEnvioTermo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoFormaEnvioTermo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoLoja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoLoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSituacaoServidor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSituacaoServidor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTabela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTabela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContaParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfAgente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfAgente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("criterioIof");
        elemField.setXmlName(new javax.xml.namespace.QName("", "criterioIof"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("criterioTac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "criterioTac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("criterioTlf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "criterioTlf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAdmissao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataAdmissao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataFator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRenda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataRenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descontoAdicional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descontoAdicional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descontoCompulsorio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descontoCompulsorio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descontoOutro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descontoOutro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descontoPossuiCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descontoPossuiCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descontoVoluntario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descontoVoluntario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finalidadeCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "finalidadeCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formaCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formaCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorMargem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificadorMargem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ignorarInconsistenciasPN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ignorarInconsistenciasPN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incluiSeguroVidaFederal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "incluiSeguroVidaFederal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indSeguroAderente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indSeguroAderente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "IndicacaoParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicativoIN100");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicativoIN100"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informacoesAdicionais");
        elemField.setXmlName(new javax.xml.namespace.QName("", "informacoesAdicionais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inserirAtendimentoPlusoft");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inserirAtendimentoPlusoft"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("latitude");
        elemField.setXmlName(new javax.xml.namespace.QName("", "latitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaEletro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaEletro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "EletroParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("longitude");
        elemField.setXmlName(new javax.xml.namespace.QName("", "longitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("margem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "margem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
        elemField.setFieldName("numeroApolice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroApolice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroPeculio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroPeculio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroPrestacoes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroPrestacoes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroPropostaExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroPropostaExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSorteio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroSorteio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("possuiCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "possuiCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("produto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "produto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protocoloMultiProdutos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "protocoloMultiProdutos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retornoCetPn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "retornoCetPn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "RetornoCetPn"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senhaConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senhaConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senhaSME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senhaSME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequencialOrgao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequencialOrgao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoBeneficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoBeneficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDomicilioBancario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoDomicilioBancario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "short"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ufContaBeneficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ufContaBeneficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadePagadora");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadePagadora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("utilizaUserConsig");
        elemField.setXmlName(new javax.xml.namespace.QName("", "utilizaUserConsig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validouSenha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validouSenha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAgregacaoDeMargemParaSaqueComplementar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorAgregacaoDeMargemParaSaqueComplementar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCapitalSegurado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCapitalSegurado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorIof");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorIof"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrestacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrestacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorRenda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorRenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorResidual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorResidual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSeguroVidaFederal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSeguroVidaFederal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSolicitado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSolicitado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
