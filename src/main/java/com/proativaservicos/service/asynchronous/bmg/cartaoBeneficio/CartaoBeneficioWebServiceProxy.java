package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class CartaoBeneficioWebServiceProxy implements com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoBeneficioWebService {
  private String _endpoint = null;
  private com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoBeneficioWebService cartaoBeneficioWebService = null;
  
  public CartaoBeneficioWebServiceProxy() {
    _initCartaoBeneficioWebServiceProxy();
  }
  
  public CartaoBeneficioWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initCartaoBeneficioWebServiceProxy();
  }
  
  private void _initCartaoBeneficioWebServiceProxy() {
    try {
      cartaoBeneficioWebService = (new com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoBeneficioWebServiceServiceLocator()).getCartaoBeneficio();
      if (cartaoBeneficioWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cartaoBeneficioWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cartaoBeneficioWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cartaoBeneficioWebService != null)
      ((javax.xml.rpc.Stub)cartaoBeneficioWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoBeneficioWebService getCartaoBeneficioWebService() {
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService;
  }
  
  public java.lang.String gravarPropostaCartao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter proposta) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.gravarPropostaCartao(proposta);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueRetorno buscarLimiteSaque(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.buscarLimiteSaque(param);
  }
  
  public java.lang.Object[] buscarSimulacao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SimulacaoCartaoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.buscarSimulacao(param);
  }
  
  public java.lang.String geraScript(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter proposta) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.geraScript(proposta);
  }
  
  public java.lang.String geraScriptIdentificacao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ScriptIdentificacaoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.geraScriptIdentificacao(param);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoRetorno validaSeJaPossuiContaCartao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.validaSeJaPossuiContaCartao(param);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraRetorno consultaUnidadePagadora(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraParameter parametro) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.consultaUnidadePagadora(parametro);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaProfissaoRetorno consultaProfissoes(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceParameter parametro) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.consultaProfissoes(parametro);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroRetorno obtemProdutosDeSeguro(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.obtemProdutosDeSeguro(param);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalReturn obtemSituacoesFuncionaisParaOOrgao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.obtemSituacoesFuncionaisParaOOrgao(param);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioReturn obtemFormasDeEnvio(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.obtemFormasDeEnvio(param);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.GeracaoPropostaRetorno gravaPropostaCartao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter proposta) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.gravaPropostaCartao(proposta);
  }
  
  public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidacaoPropostaRetorno validaPropostaCartao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter proposta) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException{
    if (cartaoBeneficioWebService == null)
      _initCartaoBeneficioWebServiceProxy();
    return cartaoBeneficioWebService.validaPropostaCartao(proposta);
  }
  
  
}