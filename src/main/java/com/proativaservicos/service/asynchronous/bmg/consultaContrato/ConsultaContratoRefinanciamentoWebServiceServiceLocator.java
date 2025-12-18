/**
 * ConsultaContratoRefinanciamentoWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.consultaContrato;

public class ConsultaContratoRefinanciamentoWebServiceServiceLocator extends org.apache.axis.client.Service implements com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoWebServiceService {

    public ConsultaContratoRefinanciamentoWebServiceServiceLocator() {
    }


    public ConsultaContratoRefinanciamentoWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaContratoRefinanciamentoWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConsultaContratoRefinanciamento
    private java.lang.String ConsultaContratoRefinanciamento_address = "http://ws1.bmgconsig.com.br/webservices/ConsultaContratoRefinanciamento";

    public java.lang.String getConsultaContratoRefinanciamentoAddress() {
        return ConsultaContratoRefinanciamento_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConsultaContratoRefinanciamentoWSDDServiceName = "ConsultaContratoRefinanciamento";

    public java.lang.String getConsultaContratoRefinanciamentoWSDDServiceName() {
        return ConsultaContratoRefinanciamentoWSDDServiceName;
    }

    public void setConsultaContratoRefinanciamentoWSDDServiceName(java.lang.String name) {
        ConsultaContratoRefinanciamentoWSDDServiceName = name;
    }

    public com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoWebService getConsultaContratoRefinanciamento() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConsultaContratoRefinanciamento_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConsultaContratoRefinanciamento(endpoint);
    }

    public com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoWebService getConsultaContratoRefinanciamento(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoSoapBindingStub(portAddress, this);
            _stub.setPortName(getConsultaContratoRefinanciamentoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConsultaContratoRefinanciamentoEndpointAddress(java.lang.String address) {
        ConsultaContratoRefinanciamento_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoSoapBindingStub _stub = new com.proativaservicos.service.asynchronous.bmg.consultaContrato.ConsultaContratoRefinanciamentoSoapBindingStub(new java.net.URL(ConsultaContratoRefinanciamento_address), this);
                _stub.setPortName(getConsultaContratoRefinanciamentoWSDDServiceName());
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
        if ("ConsultaContratoRefinanciamento".equals(inputPortName)) {
            return getConsultaContratoRefinanciamento();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/webservices/ConsultaContratoRefinanciamento", "ConsultaContratoRefinanciamentoWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/webservices/ConsultaContratoRefinanciamento", "ConsultaContratoRefinanciamento"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConsultaContratoRefinanciamento".equals(portName)) {
            setConsultaContratoRefinanciamentoEndpointAddress(address);
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
