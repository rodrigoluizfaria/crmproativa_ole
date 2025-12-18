/**
 * VSPhoneRPCSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class VSPhoneRPCSoapBindingStub extends org.apache.axis.client.Stub implements com.proativaservicos.service.asynchronous.vsphone.VSPhone {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[20];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "day"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"), byte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "month"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"), byte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "year"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        oper.setReturnClass(java.util.Calendar.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "createDateReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("criaComandoDesligar");
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ComandoDesligar"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.ComandoDesligar.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "criaComandoDesligarReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executaComando");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ComandoRequest"), com.proativaservicos.service.asynchronous.vsphone.ComandoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ComandoResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.ComandoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "executaComandoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("statusGrupo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusGrupoRequest"), com.proativaservicos.service.asynchronous.vsphone.StatusGrupoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusGrupoResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.StatusGrupoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "statusGrupoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaGravaoByteArray");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BuscaGravacaoRequest"), com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BuscaGravacaoByteArray"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoByteArray.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "buscaGravaoByteArrayReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("statusRamal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusRamalRequest"), com.proativaservicos.service.asynchronous.vsphone.StatusRamalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusRamalResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.StatusRamalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "statusRamalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gravarChamada");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ramal"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "telefone"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("testaTelefone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "TestaTelefoneRequest"), com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "TestaTelefoneResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "testaTelefoneReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("logout");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("originaChamada");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "OriginaChamadaRequest"), com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "OriginaChamadaResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "originaChamadaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("login");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "senha"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "loginReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaGravacaoBase64");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BuscaGravacaoRequest"), com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BuscaGravacaoBase64"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoBase64.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "buscaGravacaoBase64Return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createDateTime");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "day"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"), byte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "month"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"), byte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "year"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "hour"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"), byte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "minute"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"), byte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "second"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"), byte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        oper.setReturnClass(java.util.Calendar.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "createDateTimeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("solicitaBina");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BinaRequest"), com.proativaservicos.service.asynchronous.vsphone.BinaRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BinaResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.BinaResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "solicitaBinaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("statusFila");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusFilaRequest"), com.proativaservicos.service.asynchronous.vsphone.StatusFilaRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "statusFilaResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.StatusFilaResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "statusFilaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listaEventos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "EventosRequest"), com.proativaservicos.service.asynchronous.vsphone.EventosRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "EventosResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.EventosResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "listaEventosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("logon");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "logonReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("procuraChamada");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadaRequest"), com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadaResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "procuraChamadaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("testaTelefoneDisponivel");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "testaTelefoneDisponivelReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("procuraChamadas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadasRequest"), com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadasRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadaResponse"));
        oper.setReturnClass(com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    public VSPhoneRPCSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public VSPhoneRPCSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public VSPhoneRPCSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://comum.webservice.vsphone4j.virtualsistemas", "CustomRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.CustomRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://comum.webservice.vsphone4j.virtualsistemas", "CustomResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.CustomResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://comum.webservice.vsphone4j.virtualsistemas", "CustomVSPhoneClass");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.CustomVSPhoneClass.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BinaRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.BinaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BinaResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.BinaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BuscaGravacaoBase64");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoBase64.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BuscaGravacaoByteArray");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoByteArray.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "BuscaGravacaoRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ComandoDesligar");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.ComandoDesligar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ComandoRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.ComandoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ComandoResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.ComandoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "DetalhesChamada");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.DetalhesChamada.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Evento");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Evento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "EventosRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.EventosRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "EventosResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.EventosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Excecao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Excecao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Host");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Host.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "OriginaChamadaRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "OriginaChamadaResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadaRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadaResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadasRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadasRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Ramal");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Ramal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusFilaRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.StatusFilaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "statusFilaResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.StatusFilaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusGrupoRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.StatusGrupoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusGrupoResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.StatusGrupoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusRamalRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.StatusRamalRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "StatusRamalResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.StatusRamalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "TestaTelefoneRequest");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "TestaTelefoneResponse");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Variavel");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Variavel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "ArrayOf_tns1_DetalhesChamada");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.DetalhesChamada[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "DetalhesChamada");
            qName2 = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "ArrayOf_tns1_Evento");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Evento[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Evento");
            qName2 = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "ArrayOf_tns1_Excecao");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Excecao[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Excecao");
            qName2 = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "ArrayOf_tns1_ProcuraChamada");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "ProcuraChamadaRequest");
            qName2 = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "ArrayOf_tns1_Ramal");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Ramal[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Ramal");
            qName2 = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "ArrayOf_tns1_Variavel");
            cachedSerQNames.add(qName);
            cls = com.proativaservicos.service.asynchronous.vsphone.Variavel[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://modelo.webservice.vsphone4j.virtualsistemas", "Variavel");
            qName2 = new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "item");
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
                    _call.setEncodingStyle(null);
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

    public java.util.Calendar createDate(byte day, byte month, short year) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "createDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Byte(day), new java.lang.Byte(month), new java.lang.Short(year)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.util.Calendar) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.util.Calendar) org.apache.axis.utils.JavaUtils.convert(_resp, java.util.Calendar.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.ComandoDesligar criaComandoDesligar() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "criaComandoDesligar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.ComandoDesligar) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.ComandoDesligar) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.ComandoDesligar.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.ComandoResponse executaComando(com.proativaservicos.service.asynchronous.vsphone.ComandoRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "executaComando"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.ComandoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.ComandoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.ComandoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.StatusGrupoResponse statusGrupo(com.proativaservicos.service.asynchronous.vsphone.StatusGrupoRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "statusGrupo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.StatusGrupoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.StatusGrupoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.StatusGrupoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoByteArray buscaGravaoByteArray(com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "buscaGravaoByteArray"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoByteArray) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoByteArray) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoByteArray.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.StatusRamalResponse statusRamal(com.proativaservicos.service.asynchronous.vsphone.StatusRamalRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "statusRamal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.StatusRamalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.StatusRamalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.StatusRamalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public boolean gravarChamada(java.lang.String ramal, java.lang.String telefone, java.lang.String identificador) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "gravarChamada"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ramal, telefone, identificador});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneResponse testaTelefone(com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "testaTelefone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.TestaTelefoneResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void logout() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "logout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse originaChamada(com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "originaChamada"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public boolean login(java.lang.String usuario, java.lang.String senha) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "login"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {usuario, senha});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoBase64 buscaGravacaoBase64(com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "buscaGravacaoBase64"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoBase64) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoBase64) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.BuscaGravacaoBase64.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.util.Calendar createDateTime(byte day, byte month, short year, byte hour, byte minute, byte second) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "createDateTime"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Byte(day), new java.lang.Byte(month), new java.lang.Short(year), new java.lang.Byte(hour), new java.lang.Byte(minute), new java.lang.Byte(second)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.util.Calendar) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.util.Calendar) org.apache.axis.utils.JavaUtils.convert(_resp, java.util.Calendar.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.BinaResponse solicitaBina(com.proativaservicos.service.asynchronous.vsphone.BinaRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "solicitaBina"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.BinaResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.BinaResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.BinaResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.StatusFilaResponse statusFila(com.proativaservicos.service.asynchronous.vsphone.StatusFilaRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "statusFila"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.StatusFilaResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.StatusFilaResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.StatusFilaResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.EventosResponse listaEventos(com.proativaservicos.service.asynchronous.vsphone.EventosRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "listaEventos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.EventosResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.EventosResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.EventosResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public boolean logon() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "logon"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse procuraChamada(com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "procuraChamada"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public boolean testaTelefoneDisponivel() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "testaTelefoneDisponivel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse procuraChamadas(com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadasRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.vsphone4j.virtualsistemas", "procuraChamadas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.proativaservicos.service.asynchronous.vsphone.ProcuraChamadaResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
