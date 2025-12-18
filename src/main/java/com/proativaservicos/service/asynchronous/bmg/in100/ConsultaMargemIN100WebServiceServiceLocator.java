/**
 * ConsultaMargemIN100WebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.in100;

public class ConsultaMargemIN100WebServiceServiceLocator extends org.apache.axis.client.Service implements com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100WebServiceService {

    public ConsultaMargemIN100WebServiceServiceLocator() {
    }


    public ConsultaMargemIN100WebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaMargemIN100WebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConsultaMargemIN100
    private java.lang.String ConsultaMargemIN100_address = "http://ws1.bmgconsig.com.br/webservices/ConsultaMargemIN100";

    public java.lang.String getConsultaMargemIN100Address() {
        return ConsultaMargemIN100_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConsultaMargemIN100WSDDServiceName = "ConsultaMargemIN100";

    public java.lang.String getConsultaMargemIN100WSDDServiceName() {
        return ConsultaMargemIN100WSDDServiceName;
    }

    public void setConsultaMargemIN100WSDDServiceName(java.lang.String name) {
        ConsultaMargemIN100WSDDServiceName = name;
    }

    public com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100WebService getConsultaMargemIN100() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConsultaMargemIN100_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConsultaMargemIN100(endpoint);
    }

    public com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100WebService getConsultaMargemIN100(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100SoapBindingStub _stub = new com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100SoapBindingStub(portAddress, this);
            _stub.setPortName(getConsultaMargemIN100WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConsultaMargemIN100EndpointAddress(java.lang.String address) {
        ConsultaMargemIN100_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100WebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100SoapBindingStub _stub = new com.proativaservicos.service.asynchronous.bmg.in100.ConsultaMargemIN100SoapBindingStub(new java.net.URL(ConsultaMargemIN100_address), this);
                _stub.setPortName(getConsultaMargemIN100WSDDServiceName());
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
        if ("ConsultaMargemIN100".equals(inputPortName)) {
            return getConsultaMargemIN100();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/webservices/ConsultaMargemIN100", "ConsultaMargemIN100WebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/webservices/ConsultaMargemIN100", "ConsultaMargemIN100"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConsultaMargemIN100".equals(portName)) {
            setConsultaMargemIN100EndpointAddress(address);
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
