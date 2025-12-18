package com.proativaservicos.service.asynchronous.vsphone;

public class VSPhoneProxy implements com.proativaservicos.service.asynchronous.vsphone.VSPhone {
  private String _endpoint = null;
  private com.proativaservicos.service.asynchronous.vsphone.VSPhone vSPhone = null;
  
  public VSPhoneProxy() {
    _initVSPhoneProxy();
  }
  
  public VSPhoneProxy(String endpoint) {
    _endpoint = endpoint;
    _initVSPhoneProxy();
  }
  
  private void _initVSPhoneProxy() {
    try {
      vSPhone = (new com.proativaservicos.service.asynchronous.vsphone.VSPhoneRPCLocator()).getVSPhoneRPCPort();
      if (vSPhone != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)vSPhone)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)vSPhone)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (vSPhone != null)
      ((javax.xml.rpc.Stub)vSPhone)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.VSPhone getVSPhone() {
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone;
  }
  
  public java.util.Calendar createDate(byte day, byte month, short year) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.createDate(day, month, year);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.ComandoDesligar criaComandoDesligar() throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.criaComandoDesligar();
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.ComandoResponse executaComando(com.proativaservicos.service.asynchronous.vsphone.ComandoRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.executaComando(request);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.StatusGrupoResponse statusGrupo(com.proativaservicos.service.asynchronous.vsphone.StatusGrupoRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.statusGrupo(request);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoByteArray buscaGravaoByteArray(com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.buscaGravaoByteArray(request);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.StatusRamalResponse statusRamal(com.proativaservicos.service.asynchronous.vsphone.StatusRamalRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.statusRamal(request);
  }
  
  public boolean gravarChamada(java.lang.String ramal, java.lang.String telefone, java.lang.String identificador) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.gravarChamada(ramal, telefone, identificador);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneResponse testaTelefone(com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.testaTelefone(request);
  }
  
  public void logout() throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    vSPhone.logout();
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse originaChamada(com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.originaChamada(request);
  }
  
  public boolean login(java.lang.String usuario, java.lang.String senha) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.login(usuario, senha);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoBase64 buscaGravacaoBase64(com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.buscaGravacaoBase64(request);
  }
  
  public java.util.Calendar createDateTime(byte day, byte month, short year, byte hour, byte minute, byte second) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.createDateTime(day, month, year, hour, minute, second);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.BinaResponse solicitaBina(com.proativaservicos.service.asynchronous.vsphone.BinaRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.solicitaBina(request);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.StatusFilaResponse statusFila(com.proativaservicos.service.asynchronous.vsphone.StatusFilaRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.statusFila(request);
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.EventosResponse listaEventos(com.proativaservicos.service.asynchronous.vsphone.EventosRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.listaEventos(request);
  }
  
  public boolean logon() throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.logon();
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse procuraChamada(com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.procuraChamada(request);
  }
  
  public boolean testaTelefoneDisponivel() throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.testaTelefoneDisponivel();
  }
  
  public com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse procuraChamadas(com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadasRequest request) throws java.rmi.RemoteException{
    if (vSPhone == null)
      _initVSPhoneProxy();
    return vSPhone.procuraChamadas(request);
  }
  
  
}