/**
 * ProdutoSeguroWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.produtoseguros;

public class ProdutoSeguroWebServiceServiceLocator extends org.apache.axis.client.Service implements com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebServiceService {

    public ProdutoSeguroWebServiceServiceLocator() {
    }


    public ProdutoSeguroWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProdutoSeguroWebServiceServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProdutoSeguroWebService
    private String ProdutoSeguroWebService_address = "http://ws1.bmgconsig.com.br/webservices/ProdutoSeguroWebService";

    public String getProdutoSeguroWebServiceAddress() {
        return ProdutoSeguroWebService_address;
    }

    // The WSDD service name defaults to the port name.
    private String ProdutoSeguroWebServiceWSDDServiceName = "ProdutoSeguroWebService";

    public String getProdutoSeguroWebServiceWSDDServiceName() {
        return ProdutoSeguroWebServiceWSDDServiceName;
    }

    public void setProdutoSeguroWebServiceWSDDServiceName(String name) {
        ProdutoSeguroWebServiceWSDDServiceName = name;
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebService getProdutoSeguroWebService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProdutoSeguroWebService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProdutoSeguroWebService(endpoint);
    }

    public com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebService getProdutoSeguroWebService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebServiceSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getProdutoSeguroWebServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProdutoSeguroWebServiceEndpointAddress(String address) {
        ProdutoSeguroWebService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebServiceSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.produtoseguros.ProdutoSeguroWebServiceSoapBindingStub(new java.net.URL(ProdutoSeguroWebService_address), this);
                _stub.setPortName(getProdutoSeguroWebServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
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
        String inputPortName = portName.getLocalPart();
        if ("ProdutoSeguroWebService".equals(inputPortName)) {
            return getProdutoSeguroWebService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "ProdutoSeguroWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/webservices/ProdutoSeguroWebService", "ProdutoSeguroWebService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("ProdutoSeguroWebService".equals(portName)) {
            setProdutoSeguroWebServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
