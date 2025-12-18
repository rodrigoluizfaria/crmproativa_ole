/**
 * SimulacaoPrestacaoSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

public class SimulacaoPrestacaoSoapBindingStub extends org.apache.axis.client.Stub implements com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("geraSimulacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametrosSimulacaoPrestacao"), com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosSimulacaoPrestacao.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfSimulacoes"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "geraSimulacaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacionalException"),
                      "com.bmg.econsig.webservice.LimiteOperacionalException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacionalException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "RiscoOperacionalException"),
                      "com.bmg.econsig.webservice.RiscoOperacionalException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "RiscoOperacionalException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterProduto");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametrosProduto"), com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosProduto.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfProduto"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterProdutoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsigBusinessException"),
                      "com.bmg.econsig.webservice.ConsigBusinessException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsigBusinessException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterContratoRetencao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CetConsultaRetencao"), com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.CetConsultaRetencao.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfContratoRetencao"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obterContratoRetencaoReturn"));
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
        oper.setName("retornarLimiteOperacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacaoParameter"), com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacaoRetorno"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retornarLimiteOperacaoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"),
                      "com.bmg.econsig.webservice.ServiceException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException"), 
                      true
                     ));
        _operations[3] = oper;

    }

    public SimulacaoPrestacaoSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SimulacaoPrestacaoSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SimulacaoPrestacaoSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/SimulacaoPrestacao", "ArrayOf_tns1_ResultadoConsistencia");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ResultadoConsistencia[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ResultadoConsistencia");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/SimulacaoPrestacao", "ArrayOf_xsd_anyType");
            cachedSerQNames.add(qName);
            cls = java.lang.Object[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.common.econsig.bmg.com", "WebServiceBean");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.WebServiceBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.cet.econsig.bmg.com", "CetParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.CetParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.common.econsig.bmg.com", "AbstractWebServicesParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.AbstractWebServicesParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfContratoRefinanciamento");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRefinanciamentoParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoRefinanciamentoParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfContratoRetencao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoRetencaoRetorno");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfFormaEnvioRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.FormaEnvioRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfProduto");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ProdutoReturn");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfRefinanciamento");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Contrato[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfSimulacoes");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoRetorno");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BancoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.BancoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "CetConsultaRetencao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.CetConsultaRetencao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsigBusinessException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ConsigBusinessException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Contrato.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoPortabilidade");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoPortabilidade.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoRefinanciamentoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRefinanciamentoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContratoRetencaoRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "FormaEnvioRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.FormaEnvioRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacaoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacaoRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "LimiteOperacionalException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacionalException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametrosProduto");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametrosSimulacaoPrestacao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosSimulacaoPrestacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ProdutoReturn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Protocolo");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.Protocolo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ResultadoConsistencia");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ResultadoConsistencia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "RiscoOperacionalException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.RiscoOperacionalException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SimulacaoRetorno");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "WebServiceParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.WebServiceParameter.class;
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

    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno[] geraSimulacao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosSimulacaoPrestacao parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacionalException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.RiscoOperacionalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "geraSimulacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacionalException) {
              throw (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacionalException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.RiscoOperacionalException) {
              throw (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.RiscoOperacionalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[] obterProduto(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ParametrosProduto parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ConsigBusinessException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterProduto"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ConsigBusinessException) {
              throw (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ConsigBusinessException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno[] obterContratoRetencao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.CetConsultaRetencao parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "obterContratoRetencao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ContratoRetencaoRetorno[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno retornarLimiteOperacao(com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoParameter parametros) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "retornarLimiteOperacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.LimiteOperacaoRetorno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
