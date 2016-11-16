/**
 * SourceFaceInfoExtend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SourceFaceInfoExtend  implements java.io.Serializable {
    private java.lang.String PERSON_NAME;

    private java.lang.String FACE_IMAGE;

    private java.lang.String PERSON_GENDER;

    private java.lang.String PASSPORT_NUMBER;

    private int PERSON_ID;

    public SourceFaceInfoExtend() {
    }

    public SourceFaceInfoExtend(
           java.lang.String PERSON_NAME,
           java.lang.String FACE_IMAGE,
           java.lang.String PERSON_GENDER,
           java.lang.String PASSPORT_NUMBER,
           int PERSON_ID) {
           this.PERSON_NAME = PERSON_NAME;
           this.FACE_IMAGE = FACE_IMAGE;
           this.PERSON_GENDER = PERSON_GENDER;
           this.PASSPORT_NUMBER = PASSPORT_NUMBER;
           this.PERSON_ID = PERSON_ID;
    }


    /**
     * Gets the PERSON_NAME value for this SourceFaceInfoExtend.
     * 
     * @return PERSON_NAME
     */
    public java.lang.String getPERSON_NAME() {
        return PERSON_NAME;
    }


    /**
     * Sets the PERSON_NAME value for this SourceFaceInfoExtend.
     * 
     * @param PERSON_NAME
     */
    public void setPERSON_NAME(java.lang.String PERSON_NAME) {
        this.PERSON_NAME = PERSON_NAME;
    }


    /**
     * Gets the FACE_IMAGE value for this SourceFaceInfoExtend.
     * 
     * @return FACE_IMAGE
     */
    public java.lang.String getFACE_IMAGE() {
        return FACE_IMAGE;
    }


    /**
     * Sets the FACE_IMAGE value for this SourceFaceInfoExtend.
     * 
     * @param FACE_IMAGE
     */
    public void setFACE_IMAGE(java.lang.String FACE_IMAGE) {
        this.FACE_IMAGE = FACE_IMAGE;
    }


    /**
     * Gets the PERSON_GENDER value for this SourceFaceInfoExtend.
     * 
     * @return PERSON_GENDER
     */
    public java.lang.String getPERSON_GENDER() {
        return PERSON_GENDER;
    }


    /**
     * Sets the PERSON_GENDER value for this SourceFaceInfoExtend.
     * 
     * @param PERSON_GENDER
     */
    public void setPERSON_GENDER(java.lang.String PERSON_GENDER) {
        this.PERSON_GENDER = PERSON_GENDER;
    }


    /**
     * Gets the PASSPORT_NUMBER value for this SourceFaceInfoExtend.
     * 
     * @return PASSPORT_NUMBER
     */
    public java.lang.String getPASSPORT_NUMBER() {
        return PASSPORT_NUMBER;
    }


    /**
     * Sets the PASSPORT_NUMBER value for this SourceFaceInfoExtend.
     * 
     * @param PASSPORT_NUMBER
     */
    public void setPASSPORT_NUMBER(java.lang.String PASSPORT_NUMBER) {
        this.PASSPORT_NUMBER = PASSPORT_NUMBER;
    }


    /**
     * Gets the PERSON_ID value for this SourceFaceInfoExtend.
     * 
     * @return PERSON_ID
     */
    public int getPERSON_ID() {
        return PERSON_ID;
    }


    /**
     * Sets the PERSON_ID value for this SourceFaceInfoExtend.
     * 
     * @param PERSON_ID
     */
    public void setPERSON_ID(int PERSON_ID) {
        this.PERSON_ID = PERSON_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SourceFaceInfoExtend)) return false;
        SourceFaceInfoExtend other = (SourceFaceInfoExtend) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PERSON_NAME==null && other.getPERSON_NAME()==null) || 
             (this.PERSON_NAME!=null &&
              this.PERSON_NAME.equals(other.getPERSON_NAME()))) &&
            ((this.FACE_IMAGE==null && other.getFACE_IMAGE()==null) || 
             (this.FACE_IMAGE!=null &&
              this.FACE_IMAGE.equals(other.getFACE_IMAGE()))) &&
            ((this.PERSON_GENDER==null && other.getPERSON_GENDER()==null) || 
             (this.PERSON_GENDER!=null &&
              this.PERSON_GENDER.equals(other.getPERSON_GENDER()))) &&
            ((this.PASSPORT_NUMBER==null && other.getPASSPORT_NUMBER()==null) || 
             (this.PASSPORT_NUMBER!=null &&
              this.PASSPORT_NUMBER.equals(other.getPASSPORT_NUMBER()))) &&
            this.PERSON_ID == other.getPERSON_ID();
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
        if (getPERSON_NAME() != null) {
            _hashCode += getPERSON_NAME().hashCode();
        }
        if (getFACE_IMAGE() != null) {
            _hashCode += getFACE_IMAGE().hashCode();
        }
        if (getPERSON_GENDER() != null) {
            _hashCode += getPERSON_GENDER().hashCode();
        }
        if (getPASSPORT_NUMBER() != null) {
            _hashCode += getPASSPORT_NUMBER().hashCode();
        }
        _hashCode += getPERSON_ID();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SourceFaceInfoExtend.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "SourceFaceInfoExtend"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERSON_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PERSON_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FACE_IMAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FACE_IMAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERSON_GENDER");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PERSON_GENDER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PASSPORT_NUMBER");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PASSPORT_NUMBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERSON_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PERSON_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
