package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

public class SimulacaoPrestacaoWebServiceProxy implements com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebService {
  private String _endpoint = null;
  private com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebService simulacaoPrestacaoWebService = null;
  
  public SimulacaoPrestacaoWebServiceProxy() {
    _initSimulacaoPrestacaoWebServiceProxy();
  }
  
  public SimulacaoPrestacaoWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSimulacaoPrestacaoWebServiceProxy();
  }
  
  private void _initSimulacaoPrestacaoWebServiceProxy() {
    try {
      simulacaoPrestacaoWebService = (new com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebServiceServiceLocator()).getSimulacaoPrestacao();
      if (simulacaoPrestacaoWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)simulacaoPrestacaoWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)simulacaoPrestacaoWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (simulacaoPrestacaoWebService != null)
      ((javax.xml.rpc.Stub)simulacaoPrestacaoWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebService getSimulacaoPrestacaoWebService() {
    if (simulacaoPrestacaoWebService == null)
      _initSimulacaoPrestacaoWebServiceProxy();
    return simulacaoPrestacaoWebService;
  }
  
  public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno[] geraSimulacao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosSimulacaoPrestacao parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacionalException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.RiscoOperacionalException{
    if (simulacaoPrestacaoWebService == null)
      _initSimulacaoPrestacaoWebServiceProxy();
    return simulacaoPrestacaoWebService.geraSimulacao(parametros);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[] obterProduto(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosProduto parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ConsigBusinessException{
    if (simulacaoPrestacaoWebService == null)
      _initSimulacaoPrestacaoWebServiceProxy();
    return simulacaoPrestacaoWebService.obterProduto(parametros);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno[] obterContratoRetencao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.CetConsultaRetencao parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException{
    if (simulacaoPrestacaoWebService == null)
      _initSimulacaoPrestacaoWebServiceProxy();
    return simulacaoPrestacaoWebService.obterContratoRetencao(parametros);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno retornarLimiteOperacao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoParameter parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException{
    if (simulacaoPrestacaoWebService == null)
      _initSimulacaoPrestacaoWebServiceProxy();
    return simulacaoPrestacaoWebService.retornarLimiteOperacao(parametros);
  }
  
  
}