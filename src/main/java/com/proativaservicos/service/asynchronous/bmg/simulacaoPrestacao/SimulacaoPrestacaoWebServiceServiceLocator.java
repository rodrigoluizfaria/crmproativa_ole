/**
 * SimulacaoPrestacaoWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao;

public class SimulacaoPrestacaoWebServiceServiceLocator extends org.apache.axis.client.Service implements com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebServiceService {

    public SimulacaoPrestacaoWebServiceServiceLocator() {
    }


    public SimulacaoPrestacaoWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SimulacaoPrestacaoWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SimulacaoPrestacao
    private java.lang.String SimulacaoPrestacao_address = "http://ws1.bmgconsig.com.br/webservices/SimulacaoPrestacao";

    public java.lang.String getSimulacaoPrestacaoAddress() {
        return SimulacaoPrestacao_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SimulacaoPrestacaoWSDDServiceName = "SimulacaoPrestacao";

    public java.lang.String getSimulacaoPrestacaoWSDDServiceName() {
        return SimulacaoPrestacaoWSDDServiceName;
    }

    public void setSimulacaoPrestacaoWSDDServiceName(java.lang.String name) {
        SimulacaoPrestacaoWSDDServiceName = name;
    }

    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebService getSimulacaoPrestacao() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SimulacaoPrestacao_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSimulacaoPrestacao(endpoint);
    }

    public com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebService getSimulacaoPrestacao(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoSoapBindingStub(portAddress, this);
            _stub.setPortName(getSimulacaoPrestacaoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSimulacaoPrestacaoEndpointAddress(java.lang.String address) {
        SimulacaoPrestacao_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoPrestacaoSoapBindingStub(new java.net.URL(SimulacaoPrestacao_address), this);
                _stub.setPortName(getSimulacaoPrestacaoWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SimulacaoPrestacao".equals(inputPortName)) {
            return getSimulacaoPrestacao();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/webservices/SimulacaoPrestacao", "SimulacaoPrestacaoWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/webservices/SimulacaoPrestacao", "SimulacaoPrestacao"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SimulacaoPrestacao".equals(portName)) {
            setSimulacaoPrestacaoEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
