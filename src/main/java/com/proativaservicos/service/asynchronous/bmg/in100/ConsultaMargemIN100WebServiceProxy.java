package com.proativaservicos.service.asynchronous.bmg.in100;

public class ConsultaMargemIN100WebServiceProxy implements com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100WebService {
  private String _endpoint = null;
  private com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100WebService consultaMargemIN100WebService = null;
  
  public ConsultaMargemIN100WebServiceProxy() {
    _initConsultaMargemIN100WebServiceProxy();
  }
  
  public ConsultaMargemIN100WebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaMargemIN100WebServiceProxy();
  }
  
  private void _initConsultaMargemIN100WebServiceProxy() {
    try {
      consultaMargemIN100WebService = (new com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100WebServiceServiceLocator()).getConsultaMargemIN100();
      if (consultaMargemIN100WebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaMargemIN100WebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaMargemIN100WebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaMargemIN100WebService != null)
      ((javax.xml.rpc.Stub)consultaMargemIN100WebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100WebService getConsultaMargemIN100WebService() {
    if (consultaMargemIN100WebService == null)
      _initConsultaMargemIN100WebServiceProxy();
    return consultaMargemIN100WebService;
  }
  
  public java.lang.String inserirSolicitacao(com.proativaservicos.service.asynchronous.bmg.in100.SolicitacaoIN100Parameter solicitacaoIN100) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.in100.ServiceException, com.proativaservicos.service.asynchronous.bmg.in100.ConsultaIn100Exception{
    if (consultaMargemIN100WebService == null)
      _initConsultaMargemIN100WebServiceProxy();
    return consultaMargemIN100WebService.inserirSolicitacao(solicitacaoIN100);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.in100.DetalheConsultaIN100[] pesquisar(com.proativaservicos.service.asynchronous.bmg.in100.FiltroConsultaIN100 filtroConsultaIN100) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.in100.ConsultaIn100Exception, com.proativaservicos.service.asynchronous.bmg.in100.ServiceException, com.proativaservicos.service.asynchronous.bmg.in100.RegistroNaoEncontradoException{
    if (consultaMargemIN100WebService == null)
      _initConsultaMargemIN100WebServiceProxy();
    return consultaMargemIN100WebService.pesquisar(filtroConsultaIN100);
  }
  
  public java.lang.String validarToken(com.proativaservicos.service.asynchronous.bmg.in100.DadosConsultaIN100 dadosConsultaIN100) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.in100.ServiceException, com.proativaservicos.service.asynchronous.bmg.in100.ConsultaIn100Exception{
    if (consultaMargemIN100WebService == null)
      _initConsultaMargemIN100WebServiceProxy();
    return consultaMargemIN100WebService.validarToken(dadosConsultaIN100);
  }
  
  public java.lang.String reenviarSms(com.proativaservicos.service.asynchronous.bmg.in100.DadosReenviarSmsIN100 dadosReenviarSmsIN100) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.in100.ServiceException, com.proativaservicos.service.asynchronous.bmg.in100.ConsultaIn100Exception{
    if (consultaMargemIN100WebService == null)
      _initConsultaMargemIN100WebServiceProxy();
    return consultaMargemIN100WebService.reenviarSms(dadosReenviarSmsIN100);
  }
  
  
}