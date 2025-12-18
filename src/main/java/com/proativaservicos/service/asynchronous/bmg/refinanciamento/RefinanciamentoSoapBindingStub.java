/**
 * RefinanciamentoSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class RefinanciamentoSoapBindingStub extends org.apache.axis.client.Stub implements com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravaPropostaRefinanciamento");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "param"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaRefinanciamentoParameter"), com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaRefinanciamentoParameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "WSArrayOfPropostaGerada"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gravaPropostaRefinanciamentoReturn"));
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
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsigBusinessException"),
                      "com.bmg.econsig.webservice.ConsigBusinessException",
                      new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsigBusinessException"), 
                      true
                     ));
        _operations[0] = oper;

    }

    public RefinanciamentoSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public RefinanciamentoSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public RefinanciamentoSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/Refinanciamento", "ArrayOf_tns1_Seguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.Seguro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguro");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/Refinanciamento", "ArrayOf_tns2_BoletoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.BoletoParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "BoletoParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/Refinanciamento", "ArrayOf_tns2_EletroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.EletroParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "EletroParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://localhost:8080/webservices/Refinanciamento", "ArrayOf_xsd_anyType");
            cachedSerQNames.add(qName);
            cls = java.lang.Object[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.common.econsig.bmg.com", "WebServiceBean");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.WebServiceBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "BoletoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.BoletoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "EletroParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.EletroParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "IndicacaoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.IndicacaoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AbstractWebServicesParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.AbstractWebServicesParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgenciaParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.AgenciaParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfDadosScript");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.DadosScript[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosScript");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfRefinanciamento");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ArrayOfResultadoConsistencia");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.ResultadoConsistencia[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ResultadoConsistencia");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BancoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.BancoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ClienteParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.ClienteParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ConsigBusinessException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.ConsigBusinessException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContaParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.ContaParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Contrato");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.Contrato.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "DadosScript");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.DadosScript.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EnderecoParamter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.EnderecoParamter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "IdentidadeParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.IdentidadeParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ParametersPropostaConsignacao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.ParametersPropostaConsignacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Profissao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.Profissao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaConsignacaoListException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaConsignacaoListException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaGerada");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaRefinanciamentoParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaRefinanciamentoParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Protocolo");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.Protocolo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ResultadoConsistencia");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.ResultadoConsistencia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "RetornoCetPn");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.RetornoCetPn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguro");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.Seguro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ServiceException");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.ServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.TelefoneParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "VendaFacilitada");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.VendaFacilitada.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "WebServiceParameter");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.WebServiceParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "WSArrayOfPropostaGerada");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "PropostaGerada");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

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

    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada[] gravaPropostaRefinanciamento(com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaRefinanciamentoParameter param) throws java.rmi.RemoteException, com.proativaservicos.service.asynchronous.bmg.refinanciamento.ServiceException, com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaConsignacaoListException, com.proativaservicos.service.asynchronous.bmg.refinanciamento.ConsigBusinessException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "gravaPropostaRefinanciamento"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {param});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.refinanciamento.ServiceException) {
              throw (com.proativaservicos.service.asynchronous.bmg.refinanciamento.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaConsignacaoListException) {
              throw (com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaConsignacaoListException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.proativaservicos.service.asynchronous.bmg.refinanciamento.ConsigBusinessException) {
              throw (com.proativaservicos.service.asynchronous.bmg.refinanciamento.ConsigBusinessException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
