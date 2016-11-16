/**
 * CameraStructBase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class CameraStructBase  implements java.io.Serializable {
    private int cameraId;

    private java.lang.String cameraName;

    private java.lang.String cameraIPAddress;

    private java.lang.String cameraPlace;

    private java.lang.String cameraUser;

    private java.lang.String cameraPassword;

    private java.lang.String captureService;

    public CameraStructBase() {
    }

    public CameraStructBase(
           int cameraId,
           java.lang.String cameraName,
           java.lang.String cameraIPAddress,
           java.lang.String cameraPlace,
           java.lang.String cameraUser,
           java.lang.String cameraPassword,
           java.lang.String captureService) {
           this.cameraId = cameraId;
           this.cameraName = cameraName;
           this.cameraIPAddress = cameraIPAddress;
           this.cameraPlace = cameraPlace;
           this.cameraUser = cameraUser;
           this.cameraPassword = cameraPassword;
           this.captureService = captureService;
    }


    /**
     * Gets the cameraId value for this CameraStructBase.
     * 
     * @return cameraId
     */
    public int getCameraId() {
        return cameraId;
    }


    /**
     * Sets the cameraId value for this CameraStructBase.
     * 
     * @param cameraId
     */
    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }


    /**
     * Gets the cameraName value for this CameraStructBase.
     * 
     * @return cameraName
     */
    public java.lang.String getCameraName() {
        return cameraName;
    }


    /**
     * Sets the cameraName value for this CameraStructBase.
     * 
     * @param cameraName
     */
    public void setCameraName(java.lang.String cameraName) {
        this.cameraName = cameraName;
    }


    /**
     * Gets the cameraIPAddress value for this CameraStructBase.
     * 
     * @return cameraIPAddress
     */
    public java.lang.String getCameraIPAddress() {
        return cameraIPAddress;
    }


    /**
     * Sets the cameraIPAddress value for this CameraStructBase.
     * 
     * @param cameraIPAddress
     */
    public void setCameraIPAddress(java.lang.String cameraIPAddress) {
        this.cameraIPAddress = cameraIPAddress;
    }


    /**
     * Gets the cameraPlace value for this CameraStructBase.
     * 
     * @return cameraPlace
     */
    public java.lang.String getCameraPlace() {
        return cameraPlace;
    }


    /**
     * Sets the cameraPlace value for this CameraStructBase.
     * 
     * @param cameraPlace
     */
    public void setCameraPlace(java.lang.String cameraPlace) {
        this.cameraPlace = cameraPlace;
    }


    /**
     * Gets the cameraUser value for this CameraStructBase.
     * 
     * @return cameraUser
     */
    public java.lang.String getCameraUser() {
        return cameraUser;
    }


    /**
     * Sets the cameraUser value for this CameraStructBase.
     * 
     * @param cameraUser
     */
    public void setCameraUser(java.lang.String cameraUser) {
        this.cameraUser = cameraUser;
    }


    /**
     * Gets the cameraPassword value for this CameraStructBase.
     * 
     * @return cameraPassword
     */
    public java.lang.String getCameraPassword() {
        return cameraPassword;
    }


    /**
     * Sets the cameraPassword value for this CameraStructBase.
     * 
     * @param cameraPassword
     */
    public void setCameraPassword(java.lang.String cameraPassword) {
        this.cameraPassword = cameraPassword;
    }


    /**
     * Gets the captureService value for this CameraStructBase.
     * 
     * @return captureService
     */
    public java.lang.String getCaptureService() {
        return captureService;
    }


    /**
     * Sets the captureService value for this CameraStructBase.
     * 
     * @param captureService
     */
    public void setCaptureService(java.lang.String captureService) {
        this.captureService = captureService;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CameraStructBase)) return false;
        CameraStructBase other = (CameraStructBase) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.cameraId == other.getCameraId() &&
            ((this.cameraName==null && other.getCameraName()==null) || 
             (this.cameraName!=null &&
              this.cameraName.equals(other.getCameraName()))) &&
            ((this.cameraIPAddress==null && other.getCameraIPAddress()==null) || 
             (this.cameraIPAddress!=null &&
              this.cameraIPAddress.equals(other.getCameraIPAddress()))) &&
            ((this.cameraPlace==null && other.getCameraPlace()==null) || 
             (this.cameraPlace!=null &&
              this.cameraPlace.equals(other.getCameraPlace()))) &&
            ((this.cameraUser==null && other.getCameraUser()==null) || 
             (this.cameraUser!=null &&
              this.cameraUser.equals(other.getCameraUser()))) &&
            ((this.cameraPassword==null && other.getCameraPassword()==null) || 
             (this.cameraPassword!=null &&
              this.cameraPassword.equals(other.getCameraPassword()))) &&
            ((this.captureService==null && other.getCaptureService()==null) || 
             (this.captureService!=null &&
              this.captureService.equals(other.getCaptureService())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getCameraId();
        if (getCameraName() != null) {
            _hashCode += getCameraName().hashCode();
        }
        if (getCameraIPAddress() != null) {
            _hashCode += getCameraIPAddress().hashCode();
        }
        if (getCameraPlace() != null) {
            _hashCode += getCameraPlace().hashCode();
        }
        if (getCameraUser() != null) {
            _hashCode += getCameraUser().hashCode();
        }
        if (getCameraPassword() != null) {
            _hashCode += getCameraPassword().hashCode();
        }
        if (getCaptureService() != null) {
            _hashCode += getCaptureService().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CameraStructBase.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "CameraStructBase"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cameraId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CameraId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cameraName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CameraName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cameraIPAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CameraIPAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cameraPlace");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CameraPlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cameraUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CameraUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cameraPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CameraPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("captureService");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CaptureService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
