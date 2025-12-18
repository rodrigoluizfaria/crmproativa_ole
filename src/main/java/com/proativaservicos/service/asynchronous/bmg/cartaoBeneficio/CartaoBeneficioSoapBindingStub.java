/**
 * CartaoBeneficioSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio;

public class CartaoBeneficioSoapBindingStub extends org.apache.axis.client.Stub implements com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoBeneficioWebService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[13];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravarPropostaCartao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "proposta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gravarPropostaCartaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"),
                      "com.bmg.econsig.webservice.PropostaConsignacaoListException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MatriculaFormatacaoInvalidaException"),
                      "com.bmg.econsig.webservice.MatriculaFormatacaoInvalidaException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MatriculaFormatacaoInvalidaException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarLimiteSaque");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteSaqueParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteSaqueRetorno"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "buscarLimiteSaqueReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarSimulacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoCartaoParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SimulacaoCartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://localhost:8080/webservices/CartaoBeneficio", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "buscarSimulacaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("geraScript");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "proposta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "geraScriptReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"),
                      "com.bmg.econsig.webservice.PropostaConsignacaoListException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MatriculaFormatacaoInvalidaException"),
                      "com.bmg.econsig.webservice.MatriculaFormatacaoInvalidaException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MatriculaFormatacaoInvalidaException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("geraScriptIdentificacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptIdentificacaoParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ScriptIdentificacaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "geraScriptIdentificacaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"),
                      "com.bmg.econsig.webservice.PropostaConsignacaoListException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MatriculaFormatacaoInvalidaException"),
                      "com.bmg.econsig.webservice.MatriculaFormatacaoInvalidaException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MatriculaFormatacaoInvalidaException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validaSeJaPossuiContaCartao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoRetorno"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "validaSeJaPossuiContaCartaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaUnidadePagadora");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parametro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsultaUnidadePagadoraParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsultaUnidadePagadoraRetorno"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "consultaUnidadePagadoraReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaProfissoes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parametro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "WebServiceParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsultaProfissaoRetorno"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaProfissaoRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "consultaProfissoesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtemProdutosDeSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroRetorno"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtemProdutosDeSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtemSituacoesFuncionaisParaOOrgao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoFuncionalParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoFuncionalReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtemSituacoesFuncionaisParaOOrgaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtemFormasDeEnvio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioReturn"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtemFormasDeEnvioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravaPropostaCartao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "proposta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GeracaoPropostaRetorno"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.GeracaoPropostaRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gravaPropostaCartaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validaPropostaCartao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "proposta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoParameter"), com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoPropostaRetorno"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidacaoPropostaRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "validaPropostaCartaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[12] = oper;

    }

    public CartaoBeneficioSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public CartaoBeneficioSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public CartaoBeneficioSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://cet.model.common.econsig.bmg.com", "RetornoCetPn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.RetornoCetPn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://estabelecimento.model.common.econsig.bmg.com", "Estabelecimento");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Estabelecimento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exception.common.consig.bmg.com", "ConsigBusinessException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsigBusinessException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/CartaoBeneficio", "ArrayOf_tns1_Contrato");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Contrato[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/CartaoBeneficio", "ArrayOf_tns1_EletroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.EletroParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EletroParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/CartaoBeneficio", "ArrayOf_tns1_Profissao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Profissao[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Profissao");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/CartaoBeneficio", "ArrayOf_tns1_ResultadoConsistencia");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ResultadoConsistencia[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ResultadoConsistencia");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/CartaoBeneficio", "ArrayOf_xsd_anyType");
            cachedSerQNames.add(qName);
            cls = java.lang.Object[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.common.econsig.bmg.com", "WebServiceBean");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://orgao.model.common.econsig.bmg.com", "Orgao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Orgao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.cartao.econsig.bmg.com", "AgregacaoMargemParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AgregacaoMargemParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.cartao.econsig.bmg.com", "DadosLimiteDeSaque");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosLimiteDeSaque.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AbstractWebServicesParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AbstractWebServicesParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgenciaParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.AgenciaParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfBeneficiariosSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BeneficiariosSeguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BeneficiariosSeguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfBoleto");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BoletoParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BoletoParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfDadosScript");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosScript[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosScript");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfFormaEnvioRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.FormaEnvioRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Seguros[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguros");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfSimulacaoCartaoRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SimulacaoCartaoRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoCartaoRetorno");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfSituacaoServidor");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoServidor[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoServidor");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfString");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfTipoProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.TipoProdutoSeguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoProdutoSeguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfUnidadePagadora");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "UnidadePagadora");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BancoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BancoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BeneficiariosSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BeneficiariosSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BoletoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.BoletoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ClienteParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ClienteParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsultaProfissaoRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaProfissaoRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsultaUnidadePagadoraParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsultaUnidadePagadoraRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContaParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ContaParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Contrato.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CustoEfetivoTotal");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CustoEfetivoTotalParaCartaoCliente");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CustoEfetivoTotalParaCartaoCliente.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosAssinaturaEletronica");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosAssinaturaEletronica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosScript");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.DadosScript.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EletroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.EletroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EnderecoParamter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.EnderecoParamter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.FormaEnvioRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GeracaoPropostaRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.GeracaoPropostaRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "IdentidadeParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.IdentidadeParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "IndicacaoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.IndicacaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteSaqueParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteSaqueRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MatriculaFormatacaoInvalidaException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemFormaEnvioReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametersPropostaConsignacao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ParametersPropostaConsignacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Profissao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Profissao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Protocolo");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Protocolo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ResultadoConsistencia");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ResultadoConsistencia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptIdentificacaoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ScriptIdentificacaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguros");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.Seguros.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoCartaoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SimulacaoCartaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoCartaoRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SimulacaoCartaoRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoFuncionalParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoFuncionalReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoServidor");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoServidor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.TelefoneParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.TipoProdutoSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "UnidadePagadora");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.UnidadePagadora.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoPropostaRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidacaoPropostaRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "WebServiceBaseParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceBaseParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "WebServiceParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceParameter.class;
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
                java.lang.String key = (java.lang.String) keys.nextElement();
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
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
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
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String gravarPropostaCartao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter proposta) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gravarPropostaCartao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {proposta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueRetorno buscarLimiteSaque(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "buscarLimiteSaque"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueRetorno) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.LimiteSaqueRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] buscarSimulacao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SimulacaoCartaoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "buscarSimulacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String geraScript(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter proposta) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "geraScript"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {proposta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String geraScriptIdentificacao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ScriptIdentificacaoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "geraScriptIdentificacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.PropostaConsignacaoListException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.MatriculaFormatacaoInvalidaException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoRetorno validaSeJaPossuiContaCartao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "validaSeJaPossuiContaCartao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoRetorno) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidaSeJaPossuiContaCartaoRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraRetorno consultaUnidadePagadora(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraParameter parametro) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "consultaUnidadePagadora"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraRetorno) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaUnidadePagadoraRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaProfissaoRetorno consultaProfissoes(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.WebServiceParameter parametro) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "consultaProfissoes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaProfissaoRetorno) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaProfissaoRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ConsultaProfissaoRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroRetorno obtemProdutosDeSeguro(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obtemProdutosDeSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroRetorno) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemProdutosDeSeguroRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalReturn obtemSituacoesFuncionaisParaOOrgao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obtemSituacoesFuncionaisParaOOrgao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.SituacaoFuncionalReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioReturn obtemFormasDeEnvio(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obtemFormasDeEnvio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ObtemFormaEnvioReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.GeracaoPropostaRetorno gravaPropostaCartao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter proposta) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gravaPropostaCartao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {proposta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.GeracaoPropostaRetorno) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.GeracaoPropostaRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.GeracaoPropostaRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidacaoPropostaRetorno validaPropostaCartao(com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.CartaoParameter proposta) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "validaPropostaCartao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {proposta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidacaoPropostaRetorno) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidacaoPropostaRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ValidacaoPropostaRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.cartaoBeneficio.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
