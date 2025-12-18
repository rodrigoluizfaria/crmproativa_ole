package com.proativaservicos.service.asynchronous.bmg.consultaContrato;

public class ConsultaContratoRefinanciamentoWebServiceProxy implements com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoWebService {
  private String _endpoint = null;
  private com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoWebService consultaContratoRefinanciamentoWebService = null;
  
  public ConsultaContratoRefinanciamentoWebServiceProxy() {
    _initConsultaContratoRefinanciamentoWebServiceProxy();
  }
  
  public ConsultaContratoRefinanciamentoWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaContratoRefinanciamentoWebServiceProxy();
  }
  
  private void _initConsultaContratoRefinanciamentoWebServiceProxy() {
    try {
      consultaContratoRefinanciamentoWebService = (new com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoWebServiceServiceLocator()).getConsultaContratoRefinanciamento();
      if (consultaContratoRefinanciamentoWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaContratoRefinanciamentoWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaContratoRefinanciamentoWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaContratoRefinanciamentoWebService != null)
      ((javax.xml.rpc.Stub)consultaContratoRefinanciamentoWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoWebService getConsultaContratoRefinanciamentoWebService() {
    if (consultaContratoRefinanciamentoWebService == null)
      _initConsultaContratoRefinanciamentoWebServiceProxy();
    return consultaContratoRefinanciamentoWebService;
  }
  
  public com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoRetorno consultaContratoRefinanciamento(com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.consultaContrato.ServiceException, com.proativaservicos.service.asynchronous.bmg.consultaContrato.MatriculaFormatacaoInvalidaException, com.proativaservicos.service.asynchronous.bmg.consultaContrato.NenhumRegistroEncontradoException{
    if (consultaContratoRefinanciamentoWebService == null)
      _initConsultaContratoRefinanciamentoWebServiceProxy();
    return consultaContratoRefinanciamentoWebService.consultaContratoRefinanciamento(param);
  }
  
  
}