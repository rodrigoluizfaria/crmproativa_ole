/**
 * ProdutoSeguroWebServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ProdutoSeguroWebServiceSoapBindingStub extends org.apache.axis.client.Stub implements com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[30];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("atualizarProdutoSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ProdutoSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "OcorrenciaProdutoSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "atualizarProdutoSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelarProdutoSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoProdutoSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "OcorrenciaProdutoSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "cancelarProdutoSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelarProdutoSeguroApi");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoProdutoSeguroApiParameter"), com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroApiParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "OcorrenciaProdutoSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "cancelarProdutoSeguroApiReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException",
                      new javax.xml.namespace.QName("http://exception.common.consig.bmg.com", "ConsigBusinessException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelarSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parameter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCancelamentoSegurosParameter"), com.proativaservicos.service.asynchronous.produtoseguros.DadosCancelamentoSegurosParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "cancelarSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravaPropostaSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GravaPropostaSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gravaPropostaSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravarPropostaDigToken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaSeguroParameterDigToken"), com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameterDigToken.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GravaPropostaSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gravarPropostaDigTokenReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravaPropostaSeguroUpgrade");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GravaPropostaSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gravaPropostaSeguroUpgradeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listaPlanos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaPlanosSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "listaPlanosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listaPlanosRating");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaPlanosSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "listaPlanosRatingReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listaPlanosUpgrade");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroUpgradeParameter"), com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroUpgradeParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaPlanosSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "listaPlanosUpgradeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gerarScriptUpgrade");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptAdesaoReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gerarScriptUpgradeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("geraScript");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptAdesaoReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "geraScriptReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gerarScriptVendaUpgrade");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptAdesaoReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gerarScriptVendaUpgradeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("geraScriptVenda");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptAdesaoReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "geraScriptVendaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listaFormaPagamentoProdutoSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaPagamentoSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.FormaPagamentoSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaFormaPagamentoSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaFormaPagamentoSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "listaFormaPagamentoProdutoSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listaTipoBeneficio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoBeneficioParameter"), com.proativaservicos.service.asynchronous.produtoseguros.TipoBeneficioParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaTipoBeneficiosReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaTipoBeneficiosReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "listaTipoBeneficioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtemFormasDeEnvio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioProdutoSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioProdutoSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtemFormasDeEnvioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("incluirTelefonesComplementares");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "InclusaoTelefonesComplementaresParameter"), com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "InclusaoTelefonesComplementaresReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "incluirTelefonesComplementaresReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterConsistenciasSeguros");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciaSegurosParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaSegurosParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciasSegurosReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasSegurosReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterConsistenciasSegurosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validarElegibilidadeSeguros");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "validarElegibilidadeSegurosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validarElegibilidadeSegurosUpgrade");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeUpgradeParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeUpgradeReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "validarElegibilidadeSegurosUpgradeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultarConsistenciaTelefonesComplementares");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciaTelefonesComplementaresParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciaTelefonesComplementaresReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "consultarConsistenciaTelefonesComplementaresReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterCartoesDisponiveis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaCartoesSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaCartoesSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterCartoesDisponiveisReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("calcularSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CalculoSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CalculoSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "calcularSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterStatusSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterStatusSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterTiposPagamentoSeguros");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "tipoPagamentoParameter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoPagamentoParameter"), com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoPagamentoSegurosReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoSegurosReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterTiposPagamentoSegurosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterPlanosLimiteSaque");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroLimiteSaqueParameter"), com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaPlanosSeguroLimiteSaqueReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterPlanosLimiteSaqueReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException",
                      new javax.xml.namespace.QName("http://exception.common.consig.bmg.com", "ConsigBusinessException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterPlanosLimiteSaqueDigToken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroLimiteSaqueDigTokenParameter"), com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueDigTokenParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaPlanosSeguroLimiteSaqueReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterPlanosLimiteSaqueDigTokenReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException",
                      new javax.xml.namespace.QName("http://exception.common.consig.bmg.com", "ConsigBusinessException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterCartoesUpgrade");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaCartoesSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaCartoesSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterCartoesUpgradeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "fault"),
                      "com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException",
                      new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException"), 
                      true
                     ));
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterStatusCompletoPropostaSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusSeguroParameter"), com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusCompletoPropostaSeguroReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusCompletoPropostaSeguroReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterStatusCompletoPropostaSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[29] = oper;

    }

    public ProdutoSeguroWebServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ProdutoSeguroWebServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ProdutoSeguroWebServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://exception.common.consig.bmg.com", "ConsigBusinessException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exception.produtoseguro.econsig.bmg.com", "ProdutoSeguroException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "ArrayOf_tns1_CartaoClienteAtivoVendaSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteAtivoVendaSeguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoClienteAtivoVendaSeguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "ArrayOf_tns1_FormaEnvioRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.FormaEnvioRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "ArrayOf_xsd_anyType");
            cachedSerQNames.add(qName);
            cls = Object[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.common.econsig.bmg.com", "WebServiceBean");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.WebServiceBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.common.econsig.bmg.com", "AbstractWebServicesParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.AbstractWebServicesParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.common.econsig.bmg.com", "WebServiceBaseParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.WebServiceBaseParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.common.econsig.bmg.com", "WebServiceParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.WebServiceParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfConsistenciasProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasProdutoSeguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciasProdutoSeguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfDadosCadastroBasicoTipoBeneficio");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.DadosCadastroBasicoTipoBeneficio[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCadastroBasicoTipoBeneficio");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfFormaPagamentoProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.FormaPagamentoProdutoSeguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaPagamentoProdutoSeguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfMapProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.MapProdutoSeguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MapProdutoSeguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfPlanoCoberturaSeguroStadAlone");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanoCoberturaSeguroStadAlone[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanoCoberturaSeguroStadAlone");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfPlanosContratacaoSeguroLimiteSaque");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroLimiteSaque[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosContratacaoSeguroLimiteSaque");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfPlanosContratacaoSeguroStandAlone");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroStandAlone[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosContratacaoSeguroStandAlone");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfPlanosContratacaoSeguroStandAloneRating");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroStandAlone[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosContratacaoSeguroStandAlone");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfRetornoConsistenciaSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.RetornoConsistenciaSeguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "RetornoConsistenciaSeguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfRetornoTipoPagamentoSegurosCartao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamento[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoPagamento");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CalculoSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CalculoSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoProdutoSeguroApiParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroApiParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoProdutoSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CancelamentoSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoClienteAtivoVendaSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteAtivoVendaSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ClienteParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ClienteParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciaSegurosParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaSegurosParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciasProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasProdutoSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciasSegurosReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasSegurosReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciaTelefonesComplementaresParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsistenciaTelefonesComplementaresReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCadastroBasicoTipoBeneficio");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.DadosCadastroBasicoTipoBeneficio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCancelamentoSegurosParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.DadosCancelamentoSegurosParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCondicaoVendaSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.DadosCondicaoVendaSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EnderecoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.EnderecoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.FormaEnvioRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaPagamentoProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.FormaPagamentoProdutoSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaPagamentoSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.FormaPagamentoSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GravaPropostaSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "IdentidadeParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.IdentidadeParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "InclusaoTelefonesComplementaresParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "InclusaoTelefonesComplementaresReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaCartoesSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaCartoesSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaFormaPagamentoSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ListaFormaPagamentoSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaPlanosSeguroLimiteSaqueReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaPlanosSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ListaTipoBeneficiosReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ListaTipoBeneficiosReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MapProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.MapProdutoSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioProdutoSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioProdutoSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusCompletoPropostaSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusCompletoPropostaSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObterStatusSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "OcorrenciaProdutoSeguroReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanoCoberturaSeguroStadAlone");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanoCoberturaSeguroStadAlone.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosContratacaoSeguroLimiteSaque");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroLimiteSaque.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosContratacaoSeguroStandAlone");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroStandAlone.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroLimiteSaqueDigTokenParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueDigTokenParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroLimiteSaqueParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PlanosSeguroUpgradeParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroUpgradeParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ProdutoSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Profissao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.Profissao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaSeguroParameterDigToken");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameterDigToken.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "RetornoConsistenciaSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.RetornoConsistenciaSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptAdesaoReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.TelefoneParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoBeneficioParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.TipoBeneficioParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoPagamento");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoPagamentoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoPagamentoSegurosReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoSegurosReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeUpgradeParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoElegibilidadeUpgradeReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        Class cls = (Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            Class sf = (Class)
                                 cachedSerFactories.get(i);
                            Class df = (Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn atualizarProdutoSeguro(com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "atualizarProdutoSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn cancelarProdutoSeguro(com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "cancelarProdutoSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn cancelarProdutoSeguroApi(com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoProdutoSeguroApiParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "cancelarProdutoSeguroApi"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.OcorrenciaProdutoSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoSeguroReturn cancelarSeguro(com.proativaservicos.service.asynchronous.produtoseguros.DadosCancelamentoSegurosParameter parameter) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "cancelarSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.CancelamentoSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn gravaPropostaSeguro(com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gravaPropostaSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn gravarPropostaDigToken(com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameterDigToken param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gravarPropostaDigToken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn gravaPropostaSeguroUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.PropostaSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gravaPropostaSeguroUpgrade"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.GravaPropostaSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn listaPlanos(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "listaPlanos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn listaPlanosRating(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "listaPlanosRating"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn listaPlanosUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroUpgradeParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "listaPlanosUpgrade"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn gerarScriptUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gerarScriptUpgrade"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn geraScript(com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "geraScript"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn gerarScriptVendaUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gerarScriptVendaUpgrade"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn geraScriptVenda(com.proativaservicos.service.asynchronous.produtoseguros.ScriptSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "geraScriptVenda"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ScriptAdesaoReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaFormaPagamentoSeguroReturn listaFormaPagamentoProdutoSeguro(com.proativaservicos.service.asynchronous.produtoseguros.FormaPagamentoSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "listaFormaPagamentoProdutoSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaFormaPagamentoSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaFormaPagamentoSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaFormaPagamentoSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaTipoBeneficiosReturn listaTipoBeneficio(com.proativaservicos.service.asynchronous.produtoseguros.TipoBeneficioParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "listaTipoBeneficio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaTipoBeneficiosReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaTipoBeneficiosReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaTipoBeneficiosReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioReturn obtemFormasDeEnvio(com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioProdutoSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obtemFormasDeEnvio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ObtemFormaEnvioReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresReturn incluirTelefonesComplementares(com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "incluirTelefonesComplementares"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.InclusaoTelefonesComplementaresReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasSegurosReturn obterConsistenciasSeguros(com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaSegurosParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterConsistenciasSeguros"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasSegurosReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasSegurosReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciasSegurosReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeReturn validarElegibilidadeSeguros(com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "validarElegibilidadeSeguros"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeReturn validarElegibilidadeSegurosUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "validarElegibilidadeSegurosUpgrade"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ValidacaoElegibilidadeUpgradeReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresReturn consultarConsistenciaTelefonesComplementares(com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresParameter param) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "consultarConsistenciaTelefonesComplementares"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ConsistenciaTelefonesComplementaresReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn obterCartoesDisponiveis(com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterCartoesDisponiveis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroReturn calcularSeguro(com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroParameter param) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "calcularSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.CalculoSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroReturn obterStatusSeguro(com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroParameter param) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterStatusSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoSegurosReturn obterTiposPagamentoSeguros(com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoParameter tipoPagamentoParameter) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterTiposPagamentoSeguros"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {tipoPagamentoParameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoSegurosReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoSegurosReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamentoSegurosReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn obterPlanosLimiteSaque(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterPlanosLimiteSaque"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn obterPlanosLimiteSaqueDigToken(com.proativaservicos.service.asynchronous.produtoseguros.PlanosSeguroLimiteSaqueDigTokenParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteException, com.proativaservicos.service.asynchronous.produtoseguros.ServiceException, com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterPlanosLimiteSaqueDigToken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroLimiteSaqueReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ConsigBusinessException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn obterCartoesUpgrade(com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterCartoesUpgrade"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ListaCartoesSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) {
              throw (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusCompletoPropostaSeguroReturn obterStatusCompletoPropostaSeguro(com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusSeguroParameter param) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterStatusCompletoPropostaSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusCompletoPropostaSeguroReturn) _resp;
            } catch (Exception _exception) {
                return (com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusCompletoPropostaSeguroReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.produtoseguros.ObterStatusCompletoPropostaSeguroReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
