/**
 * SaqueComplementarSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class SaqueComplementarSoapBindingStub extends org.apache.axis.client.Stub implements SaqueComplementarWebService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[12];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarCartoesDisponiveis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoDisponivelParameter"), CartaoDisponivelParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoDisponivelRetorno"));
        oper.setReturnClass(CartaoDisponivelRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "buscarCartoesDisponiveisReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarLimiteSaque");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCartaoParameter"), DadosCartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteSaqueRetorno"));
        oper.setReturnClass(LimiteSaqueRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "buscarLimiteSaqueReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravarPropostaSaqueComplementar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "proposta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SaqueComplementarParameter"), SaqueComplementarParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gravarPropostaSaqueComplementarReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.PropostaConsignacaoListException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarSimulacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoCartaoParameter"), SimulacaoCartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://localhost:8080/webservices/SaqueComplementar", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "buscarSimulacaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("geraScript");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "proposta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SaqueComplementarParameter"), SaqueComplementarParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "geraScriptReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.PropostaConsignacaoListException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("geraScriptIdentificacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptIdentificacaoParameter"), ScriptIdentificacaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "geraScriptIdentificacaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validaSeJaPossuiContaCartao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoParameter"), ValidaSeJaPossuiContaCartaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoRetorno"));
        oper.setReturnClass(ValidaSeJaPossuiContaCartaoRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "validaSeJaPossuiContaCartaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtemProdutosDeSeguro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroParameter"), ObtemProdutosDeSeguroParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroRetorno"));
        oper.setReturnClass(ObtemProdutosDeSeguroRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtemProdutosDeSeguroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtemSituacoesFuncionaisParaOOrgao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoFuncionalParameter"), SituacaoFuncionalParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoFuncionalReturn"));
        oper.setReturnClass(SituacaoFuncionalReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtemSituacoesFuncionaisParaOOrgaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravaPropostaSaqueComplementar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SaqueComplementarParameter"), SaqueComplementarParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GeracaoPropostaRetorno"));
        oper.setReturnClass(GeracaoPropostaRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gravaPropostaSaqueComplementarReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validaPropostaSaqueComplementar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SaqueComplementarParameter"), SaqueComplementarParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoPropostaRetorno"));
        oper.setReturnClass(ValidacaoPropostaRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "validaPropostaSaqueComplementarReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.proativaservicos.service.asynchronous.bmg.saquecomplementar.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("realizarDevolucaoPropostaLojistaParceiro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parameter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DevolucaoPropostaLojistaParceiroWebServiceParameter"), DevolucaoPropostaLojistaParceiroWebServiceParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DevolucaoPropostaLojistaParceiroParameter"));
        oper.setReturnClass(DevolucaoPropostaLojistaParceiroParameter.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "realizarDevolucaoPropostaLojistaParceiroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[11] = oper;

    }

    public SaqueComplementarSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SaqueComplementarSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SaqueComplementarSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            cls = ConsigBusinessException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/SaqueComplementar", "ArrayOf_tns1_EletroParameter");
            cachedSerQNames.add(qName);
            cls = EletroParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EletroParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/SaqueComplementar", "ArrayOf_xsd_anyType");
            cachedSerQNames.add(qName);
            cls = Object[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.common.econsig.bmg.com", "WebServiceBean");
            cachedSerQNames.add(qName);
            cls = WebServiceBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.common.econsig.bmg.com", "WebServiceBaseParameter");
            cachedSerQNames.add(qName);
            cls = WebServiceBaseParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "BoletoParameter");
            cachedSerQNames.add(qName);
            cls = BoletoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AbstractWebServicesParameter");
            cachedSerQNames.add(qName);
            cls = AbstractWebServicesParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgenciaParameter");
            cachedSerQNames.add(qName);
            cls = AgenciaParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgregacaoMargemParameter");
            cachedSerQNames.add(qName);
            cls = AgregacaoMargemParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfBoleto");
            cachedSerQNames.add(qName);
            cls = BoletoParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "BoletoParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfCartaoRetorno");
            cachedSerQNames.add(qName);
            cls = CartaoRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoRetorno");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfDadosScript");
            cachedSerQNames.add(qName);
            cls = DadosScript[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosScript");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfFormaEnvioRetorno");
            cachedSerQNames.add(qName);
            cls = FormaEnvioRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfSeguro");
            cachedSerQNames.add(qName);
            cls = Seguros[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguros");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfSituacaoServidor");
            cachedSerQNames.add(qName);
            cls = SituacaoServidor[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoServidor");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfString");
            cachedSerQNames.add(qName);
            cls = String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfTipoProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = TipoProdutoSeguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoProdutoSeguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BancoParameter");
            cachedSerQNames.add(qName);
            cls = BancoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoDisponivelParameter");
            cachedSerQNames.add(qName);
            cls = CartaoDisponivelParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoDisponivelRetorno");
            cachedSerQNames.add(qName);
            cls = CartaoDisponivelRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CartaoRetorno");
            cachedSerQNames.add(qName);
            cls = CartaoRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContaParameter");
            cachedSerQNames.add(qName);
            cls = ContaParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CustoEfetivoTotal");
            cachedSerQNames.add(qName);
            cls = CustoEfetivoTotal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CustoEfetivoTotalParaCartaoCliente");
            cachedSerQNames.add(qName);
            cls = CustoEfetivoTotalParaCartaoCliente.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosAssinaturaEletronica");
            cachedSerQNames.add(qName);
            cls = DadosAssinaturaEletronica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosCartaoParameter");
            cachedSerQNames.add(qName);
            cls = DadosCartaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosLimiteDeSaque");
            cachedSerQNames.add(qName);
            cls = DadosLimiteDeSaque.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosScript");
            cachedSerQNames.add(qName);
            cls = DadosScript.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DevolucaoPropostaLojistaParceiroParameter");
            cachedSerQNames.add(qName);
            cls = DevolucaoPropostaLojistaParceiroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DevolucaoPropostaLojistaParceiroWebServiceParameter");
            cachedSerQNames.add(qName);
            cls = DevolucaoPropostaLojistaParceiroWebServiceParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EletroParameter");
            cachedSerQNames.add(qName);
            cls = EletroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno");
            cachedSerQNames.add(qName);
            cls = FormaEnvioRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "GeracaoPropostaRetorno");
            cachedSerQNames.add(qName);
            cls = GeracaoPropostaRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteSaqueRetorno");
            cachedSerQNames.add(qName);
            cls = LimiteSaqueRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "MatriculaFormatacaoInvalidaException");
            cachedSerQNames.add(qName);
            cls = MatriculaFormatacaoInvalidaException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroParameter");
            cachedSerQNames.add(qName);
            cls = ObtemProdutosDeSeguroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ObtemProdutosDeSeguroRetorno");
            cachedSerQNames.add(qName);
            cls = ObtemProdutosDeSeguroRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException");
            cachedSerQNames.add(qName);
            cls = PropostaConsignacaoListException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SaqueComplementarLojistaParceiroParameter");
            cachedSerQNames.add(qName);
            cls = SaqueComplementarLojistaParceiroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SaqueComplementarParameter");
            cachedSerQNames.add(qName);
            cls = SaqueComplementarParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ScriptIdentificacaoParameter");
            cachedSerQNames.add(qName);
            cls = ScriptIdentificacaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguros");
            cachedSerQNames.add(qName);
            cls = Seguros.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException");
            cachedSerQNames.add(qName);
            cls = ServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoCartaoParameter");
            cachedSerQNames.add(qName);
            cls = SimulacaoCartaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoFuncionalParameter");
            cachedSerQNames.add(qName);
            cls = SituacaoFuncionalParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoFuncionalReturn");
            cachedSerQNames.add(qName);
            cls = SituacaoFuncionalReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SituacaoServidor");
            cachedSerQNames.add(qName);
            cls = SituacaoServidor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter");
            cachedSerQNames.add(qName);
            cls = TelefoneParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TipoProdutoSeguro");
            cachedSerQNames.add(qName);
            cls = TipoProdutoSeguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidacaoPropostaRetorno");
            cachedSerQNames.add(qName);
            cls = ValidacaoPropostaRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoParameter");
            cachedSerQNames.add(qName);
            cls = ValidaSeJaPossuiContaCartaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ValidaSeJaPossuiContaCartaoRetorno");
            cachedSerQNames.add(qName);
            cls = ValidaSeJaPossuiContaCartaoRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "WebServiceParameter");
            cachedSerQNames.add(qName);
            cls = WebServiceParameter.class;
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

    public CartaoDisponivelRetorno buscarCartoesDisponiveis(CartaoDisponivelParameter param) throws java.rmi.RemoteException, ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "buscarCartoesDisponiveis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (CartaoDisponivelRetorno) _resp;
            } catch (Exception _exception) {
                return (CartaoDisponivelRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, CartaoDisponivelRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public LimiteSaqueRetorno buscarLimiteSaque(DadosCartaoParameter param) throws java.rmi.RemoteException, ServiceException {
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
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (LimiteSaqueRetorno) _resp;
            } catch (Exception _exception) {
                return (LimiteSaqueRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, LimiteSaqueRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public String gravarPropostaSaqueComplementar(SaqueComplementarParameter proposta) throws java.rmi.RemoteException, ServiceException, PropostaConsignacaoListException, MatriculaFormatacaoInvalidaException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gravarPropostaSaqueComplementar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {proposta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (String) _resp;
            } catch (Exception _exception) {
                return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof PropostaConsignacaoListException) {
              throw (PropostaConsignacaoListException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof MatriculaFormatacaoInvalidaException) {
              throw (MatriculaFormatacaoInvalidaException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public Object[] buscarSimulacao(SimulacaoCartaoParameter param) throws java.rmi.RemoteException, ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "buscarSimulacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (Object[]) _resp;
            } catch (Exception _exception) {
                return (Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public String geraScript(SaqueComplementarParameter proposta) throws java.rmi.RemoteException, ServiceException, PropostaConsignacaoListException, MatriculaFormatacaoInvalidaException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "geraScript"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {proposta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (String) _resp;
            } catch (Exception _exception) {
                return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof PropostaConsignacaoListException) {
              throw (PropostaConsignacaoListException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof MatriculaFormatacaoInvalidaException) {
              throw (MatriculaFormatacaoInvalidaException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public String geraScriptIdentificacao(ScriptIdentificacaoParameter param) throws java.rmi.RemoteException, ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "geraScriptIdentificacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (String) _resp;
            } catch (Exception _exception) {
                return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ValidaSeJaPossuiContaCartaoRetorno validaSeJaPossuiContaCartao(ValidaSeJaPossuiContaCartaoParameter param) throws java.rmi.RemoteException, ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "validaSeJaPossuiContaCartao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ValidaSeJaPossuiContaCartaoRetorno) _resp;
            } catch (Exception _exception) {
                return (ValidaSeJaPossuiContaCartaoRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, ValidaSeJaPossuiContaCartaoRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ObtemProdutosDeSeguroRetorno obtemProdutosDeSeguro(ObtemProdutosDeSeguroParameter param) throws java.rmi.RemoteException, ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obtemProdutosDeSeguro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ObtemProdutosDeSeguroRetorno) _resp;
            } catch (Exception _exception) {
                return (ObtemProdutosDeSeguroRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, ObtemProdutosDeSeguroRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public SituacaoFuncionalReturn obtemSituacoesFuncionaisParaOOrgao(SituacaoFuncionalParameter param) throws java.rmi.RemoteException, ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obtemSituacoesFuncionaisParaOOrgao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (SituacaoFuncionalReturn) _resp;
            } catch (Exception _exception) {
                return (SituacaoFuncionalReturn) org.apache.axis.utils.JavaUtils.convert(_resp, SituacaoFuncionalReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public GeracaoPropostaRetorno gravaPropostaSaqueComplementar(SaqueComplementarParameter param) throws java.rmi.RemoteException, ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gravaPropostaSaqueComplementar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (GeracaoPropostaRetorno) _resp;
            } catch (Exception _exception) {
                return (GeracaoPropostaRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, GeracaoPropostaRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ValidacaoPropostaRetorno validaPropostaSaqueComplementar(SaqueComplementarParameter param) throws java.rmi.RemoteException, ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "validaPropostaSaqueComplementar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ValidacaoPropostaRetorno) _resp;
            } catch (Exception _exception) {
                return (ValidacaoPropostaRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, ValidacaoPropostaRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ServiceException) {
              throw (ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public DevolucaoPropostaLojistaParceiroParameter realizarDevolucaoPropostaLojistaParceiro(DevolucaoPropostaLojistaParceiroWebServiceParameter parameter) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "realizarDevolucaoPropostaLojistaParceiro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (DevolucaoPropostaLojistaParceiroParameter) _resp;
            } catch (Exception _exception) {
                return (DevolucaoPropostaLojistaParceiroParameter) org.apache.axis.utils.JavaUtils.convert(_resp, DevolucaoPropostaLojistaParceiroParameter.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
