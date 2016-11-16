/**
 * FRSServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface FRSServiceSoap extends java.rmi.Remote {
    public int faceVerify(java.lang.String imgID, java.lang.String img1Str) throws java.rmi.RemoteException;
    public java.lang.String getPlaces(java.lang.String type) throws java.rmi.RemoteException;
    public org.tempuri.CameraStructBase[] getCameraList(java.lang.String type) throws java.rmi.RemoteException;
    public java.lang.String[] getServiceStatus() throws java.rmi.RemoteException;
    public int enrollUser(java.lang.String name, java.lang.String passport, java.lang.String gender, java.lang.String nation, java.lang.String type, java.lang.String comment, java.lang.String borned, java.lang.String image) throws java.rmi.RemoteException;
    public org.tempuri.UserBaseExtend getUser(java.lang.String name, java.lang.String passportNumber) throws java.rmi.RemoteException;
    public int getHitRecordPages(java.lang.String dateFrm, java.lang.String dateTo, java.lang.String sourceId, java.lang.String userName, int pSize) throws java.rmi.RemoteException;
    public org.tempuri.HitRecordExtend[] getHitRecord(int nPageSize, int nPage, java.lang.String dtFrom, java.lang.String dtTo, java.lang.String strSourceId, java.lang.String username) throws java.rmi.RemoteException;
    public java.lang.String getUserFullImage(int userID) throws java.rmi.RemoteException;
    public org.tempuri.UserBaseExtend getUserBase(int userID) throws java.rmi.RemoteException;
    public org.tempuri.SourceFaceInfoExtend getSourceFace_Thumbnail(int userID) throws java.rmi.RemoteException;
    public org.tempuri.SimilarFace[] searchFaceImage(java.lang.String imgStr) throws java.rmi.RemoteException;
    public org.tempuri.HitRecordExtend[] getLatestHitRecord(java.lang.String userType) throws java.rmi.RemoteException;
    public org.tempuri.CapturedFace[] getLatestCapturedFaces(java.lang.String userType) throws java.rmi.RemoteException;
    public int deEnroll(java.lang.String userID) throws java.rmi.RemoteException;
    public int deEnrollEx(java.lang.String name, java.lang.String passport) throws java.rmi.RemoteException;
    public java.lang.String getFullImage(java.lang.String imgID) throws java.rmi.RemoteException;
}
