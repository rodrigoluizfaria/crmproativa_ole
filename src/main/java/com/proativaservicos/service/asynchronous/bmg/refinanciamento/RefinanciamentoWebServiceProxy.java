package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class RefinanciamentoWebServiceProxy implements com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebService {
  private String _endpoint = null;
  private com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebService refinanciamentoWebService = null;
  
  public RefinanciamentoWebServiceProxy() {
    _initRefinanciamentoWebServiceProxy();
  }
  
  public RefinanciamentoWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initRefinanciamentoWebServiceProxy();
  }
  
  private void _initRefinanciamentoWebServiceProxy() {
    try {
      refinanciamentoWebService = (new com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebServiceServiceLocator()).getRefinanciamento();
      if (refinanciamentoWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)refinanciamentoWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)refinanciamentoWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (refinanciamentoWebService != null)
      ((javax.xml.rpc.Stub)refinanciamentoWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebService getRefinanciamentoWebService() {
    if (refinanciamentoWebService == null)
      _initRefinanciamentoWebServiceProxy();
    return refinanciamentoWebService;
  }
  
  public com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada[] gravaPropostaRefinanciamento(com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaRefinanciamentoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.refinanciamento.ServiceException, com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaConsignacaoListException, com.proativaservicos.service.asynchronous.bmg.refinanciamento.ConsigBusinessException{
    if (refinanciamentoWebService == null)
      _initRefinanciamentoWebServiceProxy();
    return refinanciamentoWebService.gravaPropostaRefinanciamento(param);
  }
  
  
}