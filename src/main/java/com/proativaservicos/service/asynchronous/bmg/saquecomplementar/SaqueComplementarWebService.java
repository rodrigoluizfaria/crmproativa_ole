/**
 * SaqueComplementarWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public interface SaqueComplementarWebService extends java.rmi.Remote {
    public CartaoDisponivelRetorno buscarCartoesDisponiveis(CartaoDisponivelParameter param) throws java.rmi.RemoteException, ServiceException;
    public LimiteSaqueRetorno buscarLimiteSaque(DadosCartaoParameter param) throws java.rmi.RemoteException, ServiceException;
    public String gravarPropostaSaqueComplementar(SaqueComplementarParameter proposta) throws java.rmi.RemoteException, ServiceException, PropostaConsignacaoListException, MatriculaFormatacaoInvalidaException;
    public Object[] buscarSimulacao(SimulacaoCartaoParameter param) throws java.rmi.RemoteException, ServiceException;
    public String geraScript(SaqueComplementarParameter proposta) throws java.rmi.RemoteException, ServiceException, PropostaConsignacaoListException, MatriculaFormatacaoInvalidaException;
    public String geraScriptIdentificacao(ScriptIdentificacaoParameter param) throws java.rmi.RemoteException, ServiceException;
    public ValidaSeJaPossuiContaCartaoRetorno validaSeJaPossuiContaCartao(ValidaSeJaPossuiContaCartaoParameter param) throws java.rmi.RemoteException, ServiceException;
    public ObtemProdutosDeSeguroRetorno obtemProdutosDeSeguro(ObtemProdutosDeSeguroParameter param) throws java.rmi.RemoteException, ServiceException;
    public SituacaoFuncionalReturn obtemSituacoesFuncionaisParaOOrgao(SituacaoFuncionalParameter param) throws java.rmi.RemoteException, ServiceException;
    public GeracaoPropostaRetorno gravaPropostaSaqueComplementar(SaqueComplementarParameter param) throws java.rmi.RemoteException, ServiceException;
    public ValidacaoPropostaRetorno validaPropostaSaqueComplementar(SaqueComplementarParameter param) throws java.rmi.RemoteException, ServiceException;
    public DevolucaoPropostaLojistaParceiroParameter realizarDevolucaoPropostaLojistaParceiro(DevolucaoPropostaLojistaParceiroWebServiceParameter parameter) throws java.rmi.RemoteException;
}
