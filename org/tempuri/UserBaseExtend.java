/**
 * UserBaseExtend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class UserBaseExtend  implements java.io.Serializable {
    private int id;

    private java.lang.String name;

    private java.lang.String passportNumber;

    private java.lang.String gender;

    private java.lang.String comment;

    private java.lang.String nation;

    private java.lang.String identityType;

    private java.lang.String bornedDate;

    private java.lang.String fullImagePath;

    private java.lang.String faceImage;

    public UserBaseExtend() {
    }

    public UserBaseExtend(
           int id,
           java.lang.String name,
           java.lang.String passportNumber,
           java.lang.String gender,
           java.lang.String comment,
           java.lang.String nation,
           java.lang.String identityType,
           java.lang.String bornedDate,
           java.lang.String fullImagePath,
           java.lang.String faceImage) {
           this.id = id;
           this.name = name;
           this.passportNumber = passportNumber;
           this.gender = gender;
           this.comment = comment;
           this.nation = nation;
           this.identityType = identityType;
           this.bornedDate = bornedDate;
           this.fullImagePath = fullImagePath;
           this.faceImage = faceImage;
    }


    /**
     * Gets the id value for this UserBaseExtend.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this UserBaseExtend.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the name value for this UserBaseExtend.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this UserBaseExtend.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the passportNumber value for this UserBaseExtend.
     * 
     * @return passportNumber
     */
    public java.lang.String getPassportNumber() {
        return passportNumber;
    }


    /**
     * Sets the passportNumber value for this UserBaseExtend.
     * 
     * @param passportNumber
     */
    public void setPassportNumber(java.lang.String passportNumber) {
        this.passportNumber = passportNumber;
    }


    /**
     * Gets the gender value for this UserBaseExtend.
     * 
     * @return gender
     */
    public java.lang.String getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this UserBaseExtend.
     * 
     * @param gender
     */
    public void setGender(java.lang.String gender) {
        this.gender = gender;
    }


    /**
     * Gets the comment value for this UserBaseExtend.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this UserBaseExtend.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the nation value for this UserBaseExtend.
     * 
     * @return nation
     */
    public java.lang.String getNation() {
        return nation;
    }


    /**
     * Sets the nation value for this UserBaseExtend.
     * 
     * @param nation
     */
    public void setNation(java.lang.String nation) {
        this.nation = nation;
    }


    /**
     * Gets the identityType value for this UserBaseExtend.
     * 
     * @return identityType
     */
    public java.lang.String getIdentityType() {
        return identityType;
    }


    /**
     * Sets the identityType value for this UserBaseExtend.
     * 
     * @param identityType
     */
    public void setIdentityType(java.lang.String identityType) {
        this.identityType = identityType;
    }


    /**
     * Gets the bornedDate value for this UserBaseExtend.
     * 
     * @return bornedDate
     */
    public java.lang.String getBornedDate() {
        return bornedDate;
    }


    /**
     * Sets the bornedDate value for this UserBaseExtend.
     * 
     * @param bornedDate
     */
    public void setBornedDate(java.lang.String bornedDate) {
        this.bornedDate = bornedDate;
    }


    /**
     * Gets the fullImagePath value for this UserBaseExtend.
     * 
     * @return fullImagePath
     */
    public java.lang.String getFullImagePath() {
        return fullImagePath;
    }


    /**
     * Sets the fullImagePath value for this UserBaseExtend.
     * 
     * @param fullImagePath
     */
    public void setFullImagePath(java.lang.String fullImagePath) {
        this.fullImagePath = fullImagePath;
    }


    /**
     * Gets the faceImage value for this UserBaseExtend.
     * 
     * @return faceImage
     */
    public java.lang.String getFaceImage() {
        return faceImage;
    }


    /**
     * Sets the faceImage value for this UserBaseExtend.
     * 
     * @param faceImage
     */
    public void setFaceImage(java.lang.String faceImage) {
        this.faceImage = faceImage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserBaseExtend)) return false;
        UserBaseExtend other = (UserBaseExtend) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.passportNumber==null && other.getPassportNumber()==null) || 
             (this.passportNumber!=null &&
              this.passportNumber.equals(other.getPassportNumber()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.nation==null && other.getNation()==null) || 
             (this.nation!=null &&
              this.nation.equals(other.getNation()))) &&
            ((this.identityType==null && other.getIdentityType()==null) || 
             (this.identityType!=null &&
              this.identityType.equals(other.getIdentityType()))) &&
            ((this.bornedDate==null && other.getBornedDate()==null) || 
             (this.bornedDate!=null &&
              this.bornedDate.equals(other.getBornedDate()))) &&
            ((this.fullImagePath==null && other.getFullImagePath()==null) || 
             (this.fullImagePath!=null &&
              this.fullImagePath.equals(other.getFullImagePath()))) &&
            ((this.faceImage==null && other.getFaceImage()==null) || 
             (this.faceImage!=null &&
              this.faceImage.equals(other.getFaceImage())));
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
        _hashCode += getId();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPassportNumber() != null) {
            _hashCode += getPassportNumber().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getNation() != null) {
            _hashCode += getNation().hashCode();
        }
        if (getIdentityType() != null) {
            _hashCode += getIdentityType().hashCode();
        }
        if (getBornedDate() != null) {
            _hashCode += getBornedDate().hashCode();
        }
        if (getFullImagePath() != null) {
            _hashCode += getFullImagePath().hashCode();
        }
        if (getFaceImage() != null) {
            _hashCode += getFaceImage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserBaseExtend.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "UserBaseExtend"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passportNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PassportNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Nation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identityType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdentityType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bornedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BornedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullImagePath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FullImagePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faceImage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FaceImage"));
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
