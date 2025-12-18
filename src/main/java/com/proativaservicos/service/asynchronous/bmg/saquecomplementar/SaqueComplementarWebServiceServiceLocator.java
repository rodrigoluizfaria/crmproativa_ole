/**
 * SaqueComplementarWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class SaqueComplementarWebServiceServiceLocator extends org.apache.axis.client.Service implements SaqueComplementarWebServiceService {

    public SaqueComplementarWebServiceServiceLocator() {
    }


    public SaqueComplementarWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SaqueComplementarWebServiceServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SaqueComplementar
    private String SaqueComplementar_address = "https://ws1.bmgconsig.com.br/webservices/SaqueComplementar";

    public String getSaqueComplementarAddress() {
        return SaqueComplementar_address;
    }

    // The WSDD service name defaults to the port name.
    private String SaqueComplementarWSDDServiceName = "SaqueComplementar";

    public String getSaqueComplementarWSDDServiceName() {
        return SaqueComplementarWSDDServiceName;
    }

    public void setSaqueComplementarWSDDServiceName(String name) {
        SaqueComplementarWSDDServiceName = name;
    }

    public SaqueComplementarWebService getSaqueComplementar() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SaqueComplementar_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSaqueComplementar(endpoint);
    }

    public SaqueComplementarWebService getSaqueComplementar(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SaqueComplementarSoapBindingStub _stub = new SaqueComplementarSoapBindingStub(portAddress, this);
            _stub.setPortName(getSaqueComplementarWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSaqueComplementarEndpointAddress(String address) {
        SaqueComplementar_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SaqueComplementarWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                SaqueComplementarSoapBindingStub _stub = new SaqueComplementarSoapBindingStub(new java.net.URL(SaqueComplementar_address), this);
                _stub.setPortName(getSaqueComplementarWSDDServiceName());
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
        if ("SaqueComplementar".equals(inputPortName)) {
            return getSaqueComplementar();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/webservices/SaqueComplementar", "SaqueComplementarWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/webservices/SaqueComplementar", "SaqueComplementar"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("SaqueComplementar".equals(portName)) {
            setSaqueComplementarEndpointAddress(address);
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
