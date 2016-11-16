/**
 * FRSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class FRSServiceLocator extends org.apache.axis.client.Service implements org.tempuri.FRSService {

    public FRSServiceLocator() {
    }


    public FRSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FRSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FRSServiceSoap
    private java.lang.String FRSServiceSoap_address = "http://10.52.46.31:5658/FRSService.asmx";

    public java.lang.String getFRSServiceSoapAddress() {
        return FRSServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FRSServiceSoapWSDDServiceName = "FRSServiceSoap";

    public java.lang.String getFRSServiceSoapWSDDServiceName() {
        return FRSServiceSoapWSDDServiceName;
    }

    public void setFRSServiceSoapWSDDServiceName(java.lang.String name) {
        FRSServiceSoapWSDDServiceName = name;
    }

    public org.tempuri.FRSServiceSoap getFRSServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FRSServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFRSServiceSoap(endpoint);
    }

    public org.tempuri.FRSServiceSoap getFRSServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.FRSServiceSoapStub _stub = new org.tempuri.FRSServiceSoapStub(portAddress, this);
            _stub.setPortName(getFRSServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFRSServiceSoapEndpointAddress(java.lang.String address) {
        FRSServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.FRSServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.FRSServiceSoapStub _stub = new org.tempuri.FRSServiceSoapStub(new java.net.URL(FRSServiceSoap_address), this);
                _stub.setPortName(getFRSServiceSoapWSDDServiceName());
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
        if ("FRSServiceSoap".equals(inputPortName)) {
            return getFRSServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "FRSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "FRSServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FRSServiceSoap".equals(portName)) {
            setFRSServiceSoapEndpointAddress(address);
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
