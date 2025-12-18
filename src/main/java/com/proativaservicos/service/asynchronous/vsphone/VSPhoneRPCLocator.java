/**
 * VSPhoneRPCLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.vsphone;

public class VSPhoneRPCLocator extends org.apache.axis.client.Service implements com.proativaservicos.service.asynchronous.vsphone.VSPhoneRPC {

    public VSPhoneRPCLocator() {
    }


    public VSPhoneRPCLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VSPhoneRPCLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VSPhoneRPCPort
    private java.lang.String VSPhoneRPCPort_address = "http://10.8.1.254:8880/VSPhone4J/VSPhoneRPC";

    public java.lang.String getVSPhoneRPCPortAddress() {
        return VSPhoneRPCPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VSPhoneRPCPortWSDDServiceName = "VSPhoneRPCPort";

    public java.lang.String getVSPhoneRPCPortWSDDServiceName() {
        return VSPhoneRPCPortWSDDServiceName;
    }

    public void setVSPhoneRPCPortWSDDServiceName(java.lang.String name) {
        VSPhoneRPCPortWSDDServiceName = name;
    }

    public com.proativaservicos.service.asynchronous.vsphone.VSPhone getVSPhoneRPCPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VSPhoneRPCPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVSPhoneRPCPort(endpoint);
    }

    public com.proativaservicos.service.asynchronous.vsphone.VSPhone getVSPhoneRPCPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
           com.proativaservicos.service.asynchronous.vsphone.VSPhoneRPCSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.vsphone.VSPhoneRPCSoapBindingStub(portAddress, this);
            _stub.setPortName(getVSPhoneRPCPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVSPhoneRPCPortEndpointAddress(java.lang.String address) {
        VSPhoneRPCPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.proativaservicos.service.asynchronous.vsphone.VSPhone.class.isAssignableFrom(serviceEndpointInterface)) {
               com.proativaservicos.service.asynchronous.vsphone.VSPhoneRPCSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.vsphone.VSPhoneRPCSoapBindingStub(new java.net.URL(VSPhoneRPCPort_address), this);
                _stub.setPortName(getVSPhoneRPCPortWSDDServiceName());
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
        if ("VSPhoneRPCPort".equals(inputPortName)) {
            return getVSPhoneRPCPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.webservice.vsphone4j.virtualsistemas.com.br/", "VSPhoneRPC");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.webservice.vsphone4j.virtualsistemas.com.br/", "VSPhoneRPCPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VSPhoneRPCPort".equals(portName)) {
            setVSPhoneRPCPortEndpointAddress(address);
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
