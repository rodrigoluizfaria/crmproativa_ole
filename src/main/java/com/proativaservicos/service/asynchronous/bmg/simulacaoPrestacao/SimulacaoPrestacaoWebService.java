/**
 * SimulacaoPrestacaoWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

public interface SimulacaoPrestacaoWebService extends java.rmi.Remote {
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno[] geraSimulacao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosSimulacaoPrestacao parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacionalException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.RiscoOperacionalException;
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[] obterProduto(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosProduto parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ConsigBusinessException;
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno[] obterContratoRetencao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.CetConsultaRetencao parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException;
    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno retornarLimiteOperacao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoParameter parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException;
}
