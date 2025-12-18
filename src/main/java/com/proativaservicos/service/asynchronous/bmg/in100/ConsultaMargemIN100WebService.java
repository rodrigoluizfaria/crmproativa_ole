/**
 * ConsultaMargemIN100WebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.in100;

public interface ConsultaMargemIN100WebService extends java.rmi.Remote {
    public java.lang.String inserirSolicitacao(com.proativaservicos.service.asynchronous.bmg.in100.SolicitacaoIN100Parameter solicitacaoIN100) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.in100.ServiceException, com.proativaservicos.service.asynchronous.bmg.in100.ConsultaIn100Exception;
    public com.proativaservicos.service.asynchronous.bmg.in100.DetalheConsultaIN100[] pesquisar(com.proativaservicos.service.asynchronous.bmg.in100.FiltroConsultaIN100 filtroConsultaIN100) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.in100.ConsultaIn100Exception, com.proativaservicos.service.asynchronous.bmg.in100.ServiceException, com.proativaservicos.service.asynchronous.bmg.in100.RegistroNaoEncontradoException;
    public java.lang.String validarToken(com.proativaservicos.service.asynchronous.bmg.in100.DadosConsultaIN100 dadosConsultaIN100) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.in100.ServiceException, com.proativaservicos.service.asynchronous.bmg.in100.ConsultaIn100Exception;
    public java.lang.String reenviarSms(com.proativaservicos.service.asynchronous.bmg.in100.DadosReenviarSmsIN100 dadosReenviarSmsIN100) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.in100.ServiceException, com.proativaservicos.service.asynchronous.bmg.in100.ConsultaIn100Exception;
}
