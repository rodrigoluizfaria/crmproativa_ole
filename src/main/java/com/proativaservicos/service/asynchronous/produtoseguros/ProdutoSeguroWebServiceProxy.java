package com.proativaservicos.service.asynchronous.produtoseguros;

public class ProdutoSeguroWebServiceProxy implements com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebService {
  private String _endpoint = null;
  private com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebService produtoSeguroWebService = null;
  
  public ProdutoSeguroWebServiceProxy() {
    _initProdutoSeguroWebServiceProxy();
  }
  
  public ProdutoSeguroWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initProdutoSeguroWebServiceProxy();
  }
  
  private void _initProdutoSeguroWebServiceProxy() {
    try {
      produtoSeguroWebService = (new com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebServiceServiceLocator()).getProdutoSeguroWebService();
      if (produtoSeguroWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)produtoSeguroWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)produtoSeguroWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (produtoSeguroWebService != null)
      ((javax.xml.rpc.Stub)produtoSeguroWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebService getProdutoSeguroWebService() {
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService;
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn atualizarProdutoSeguro(com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.atualizarProdutoSeguro(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn cancelarProdutoSeguro(com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.cancelarProdutoSeguro(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn cancelarProdutoSeguroApi(com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroApiParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.cancelarProdutoSeguroApi(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoSeguroReturn cancelarSeguro(com.proativaservicos.service.asynchronous.produtoseguros.DadosCancelamentoSegurosParameter parameter) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.cancelarSeguro(parameter);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn gravaPropostaSeguro(com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.gravaPropostaSeguro(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn gravarPropostaDigToken(com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameterDigToken param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.gravarPropostaDigToken(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn gravaPropostaSeguroUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.gravaPropostaSeguroUpgrade(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn listaPlanos(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.listaPlanos(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn listaPlanosRating(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.listaPlanosRating(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn listaPlanosUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroUpgradeParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.listaPlanosUpgrade(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn gerarScriptUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.gerarScriptUpgrade(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn geraScript(com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.geraScript(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn gerarScriptVendaUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.gerarScriptVendaUpgrade(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn geraScriptVenda(com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.geraScriptVenda(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaFormaPagamentoSeguroReturn listaFormaPagamentoProdutoSeguro(com.proativaservicos.service.asynchronous.produtoseguros.FormaPagamentoSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.listaFormaPagamentoProdutoSeguro(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaTipoBeneficiosReturn listaTipoBeneficio(com.proativaservicos.service.asynchronous.produtoseguros.TipoBeneficioParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.listaTipoBeneficio(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioReturn obtemFormasDeEnvio(com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioProdutoSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obtemFormasDeEnvio(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresReturn incluirTelefonesComplementares(com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.incluirTelefonesComplementares(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasSegurosReturn obterConsistenciasSeguros(com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaSegurosParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obterConsistenciasSeguros(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeReturn validarElegibilidadeSeguros(com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.validarElegibilidadeSeguros(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeReturn validarElegibilidadeSegurosUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.validarElegibilidadeSegurosUpgrade(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresReturn consultarConsistenciaTelefonesComplementares(com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresParameter param) throws java.rmi.RemoteException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.consultarConsistenciaTelefonesComplementares(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn obterCartoesDisponiveis(com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obterCartoesDisponiveis(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroReturn calcularSeguro(com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroParameter param) throws java.rmi.RemoteException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.calcularSeguro(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroReturn obterStatusSeguro(com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroParameter param) throws java.rmi.RemoteException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obterStatusSeguro(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoSegurosReturn obterTiposPagamentoSeguros(com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoParameter tipoPagamentoParameter) throws java.rmi.RemoteException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obterTiposPagamentoSeguros(tipoPagamentoParameter);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn obterPlanosLimiteSaque(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obterPlanosLimiteSaque(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn obterPlanosLimiteSaqueDigToken(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueDigTokenParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obterPlanosLimiteSaqueDigToken(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn obterCartoesUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obterCartoesUpgrade(param);
  }
  
  public com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusCompletoPropostaSeguroReturn obterStatusCompletoPropostaSeguro(com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroParameter param) throws java.rmi.RemoteException{
    if (produtoSeguroWebService == null)
      _initProdutoSeguroWebServiceProxy();
    return produtoSeguroWebService.obterStatusCompletoPropostaSeguro(param);
  }
  
  
}