/**
 * VSPhone.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public interface VSPhone extends java.rmi.Remote {
    public java.util.Calendar createDate(byte day, byte month, short year) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.ComandoDesligar criaComandoDesligar() throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.ComandoResponse executaComando(com.proativaservicos.service.asynchronous.vsphone.ComandoRequest request) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.StatusGrupoResponse statusGrupo(com.proativaservicos.service.asynchronous.vsphone.StatusGrupoRequest request) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoByteArray buscaGravaoByteArray(com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest request) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.StatusRamalResponse statusRamal(com.proativaservicos.service.asynchronous.vsphone.StatusRamalRequest request) throws java.rmi.RemoteException;
    public boolean gravarChamada(java.lang.String ramal, java.lang.String telefone, java.lang.String identificador) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneResponse testaTelefone(com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneRequest request) throws java.rmi.RemoteException;
    public void logout() throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse originaChamada(com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaRequest request) throws java.rmi.RemoteException;
    public boolean login(java.lang.String usuario, java.lang.String senha) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoBase64 buscaGravacaoBase64(com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest request) throws java.rmi.RemoteException;
    public java.util.Calendar createDateTime(byte day, byte month, short year, byte hour, byte minute, byte second) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.BinaResponse solicitaBina(com.proativaservicos.service.asynchronous.vsphone.BinaRequest request) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.StatusFilaResponse statusFila(com.proativaservicos.service.asynchronous.vsphone.StatusFilaRequest request) throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.EventosResponse listaEventos(com.proativaservicos.service.asynchronous.vsphone.EventosRequest request) throws java.rmi.RemoteException;
    public boolean logon() throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse procuraChamada(com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaRequest request) throws java.rmi.RemoteException;
    public boolean testaTelefoneDisponivel() throws java.rmi.RemoteException;
    public com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse procuraChamadas(com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadasRequest request) throws java.rmi.RemoteException;
}
