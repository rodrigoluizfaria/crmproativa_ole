package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class SaqueComplementarWebServiceProxy implements SaqueComplementarWebService {
  private String _endpoint = null;
  private SaqueComplementarWebService saqueComplementarWebService = null;
  
  public SaqueComplementarWebServiceProxy() {
    _initSaqueComplementarWebServiceProxy();
  }
  
  public SaqueComplementarWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSaqueComplementarWebServiceProxy();
  }
  
  private void _initSaqueComplementarWebServiceProxy() {
    try {
      saqueComplementarWebService = (new SaqueComplementarWebServiceServiceLocator()).getSaqueComplementar();
      if (saqueComplementarWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)saqueComplementarWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)saqueComplementarWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (saqueComplementarWebService != null)
      ((javax.xml.rpc.Stub)saqueComplementarWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SaqueComplementarWebService getSaqueComplementarWebService() {
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService;
  }
  
  public CartaoDisponivelRetorno buscarCartoesDisponiveis(CartaoDisponivelParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.buscarCartoesDisponiveis(param);
  }
  
  public LimiteSaqueRetorno buscarLimiteSaque(DadosCartaoParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.buscarLimiteSaque(param);
  }
  
  public String gravarPropostaSaqueComplementar(SaqueComplementarParameter proposta) throws java.rmi.RemoteException, ServiceException, PropostaConsignacaoListException, MatriculaFormatacaoInvalidaException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.gravarPropostaSaqueComplementar(proposta);
  }
  
  public Object[] buscarSimulacao(SimulacaoCartaoParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.buscarSimulacao(param);
  }
  
  public String geraScript(SaqueComplementarParameter proposta) throws java.rmi.RemoteException, ServiceException, PropostaConsignacaoListException, MatriculaFormatacaoInvalidaException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.geraScript(proposta);
  }
  
  public String geraScriptIdentificacao(ScriptIdentificacaoParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.geraScriptIdentificacao(param);
  }
  
  public ValidaSeJaPossuiContaCartaoRetorno validaSeJaPossuiContaCartao(ValidaSeJaPossuiContaCartaoParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.validaSeJaPossuiContaCartao(param);
  }
  
  public ObtemProdutosDeSeguroRetorno obtemProdutosDeSeguro(ObtemProdutosDeSeguroParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.obtemProdutosDeSeguro(param);
  }
  
  public SituacaoFuncionalReturn obtemSituacoesFuncionaisParaOOrgao(SituacaoFuncionalParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.obtemSituacoesFuncionaisParaOOrgao(param);
  }
  
  public GeracaoPropostaRetorno gravaPropostaSaqueComplementar(SaqueComplementarParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.gravaPropostaSaqueComplementar(param);
  }
  
  public ValidacaoPropostaRetorno validaPropostaSaqueComplementar(SaqueComplementarParameter param) throws java.rmi.RemoteException, ServiceException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.validaPropostaSaqueComplementar(param);
  }
  
  public DevolucaoPropostaLojistaParceiroParameter realizarDevolucaoPropostaLojistaParceiro(DevolucaoPropostaLojistaParceiroWebServiceParameter parameter) throws java.rmi.RemoteException{
    if (saqueComplementarWebService == null)
      _initSaqueComplementarWebServiceProxy();
    return saqueComplementarWebService.realizarDevolucaoPropostaLojistaParceiro(parameter);
  }
  
  
}