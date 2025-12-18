/**
 * RefinanciamentoWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.refinanciamento;

public class RefinanciamentoWebServiceServiceLocator extends org.apache.axis.client.Service implements com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebServiceService {

    public RefinanciamentoWebServiceServiceLocator() {
    }


    public RefinanciamentoWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RefinanciamentoWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Refinanciamento
    private java.lang.String Refinanciamento_address = "http://ws1.bmgconsig.com.br/webservices/Refinanciamento";

    public java.lang.String getRefinanciamentoAddress() {
        return Refinanciamento_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RefinanciamentoWSDDServiceName = "Refinanciamento";

    public java.lang.String getRefinanciamentoWSDDServiceName() {
        return RefinanciamentoWSDDServiceName;
    }

    public void setRefinanciamentoWSDDServiceName(java.lang.String name) {
        RefinanciamentoWSDDServiceName = name;
    }

    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebService getRefinanciamento() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Refinanciamento_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRefinanciamento(endpoint);
    }

    public com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebService getRefinanciamento(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoSoapBindingStub(portAddress, this);
            _stub.setPortName(getRefinanciamentoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRefinanciamentoEndpointAddress(java.lang.String address) {
        Refinanciamento_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.bmg.refinanciamento.RefinanciamentoSoapBindingStub(new java.net.URL(Refinanciamento_address), this);
                _stub.setPortName(getRefinanciamentoWSDDServiceName());
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
        if ("Refinanciamento".equals(inputPortName)) {
            return getRefinanciamento();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/webservices/Refinanciamento", "RefinanciamentoWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/webservices/Refinanciamento", "Refinanciamento"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Refinanciamento".equals(portName)) {
            setRefinanciamentoEndpointAddress(address);
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
