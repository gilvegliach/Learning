/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Volumes/Code/CortadoAndroid/workspaces/app/RemoteServiceTest/src/com/example/remoteservicetest/IRemoteService.aidl
 */
package com.example.remoteservicetest;
public interface IRemoteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.remoteservicetest.IRemoteService
{
private static final java.lang.String DESCRIPTOR = "com.example.remoteservicetest.IRemoteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.remoteservicetest.IRemoteService interface,
 * generating a proxy if needed.
 */
public static com.example.remoteservicetest.IRemoteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.remoteservicetest.IRemoteService))) {
return ((com.example.remoteservicetest.IRemoteService)iin);
}
return new com.example.remoteservicetest.IRemoteService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_process:
{
data.enforceInterface(DESCRIPTOR);
com.example.remoteservicetest.ParcelableData _arg0;
if ((0!=data.readInt())) {
_arg0 = com.example.remoteservicetest.ParcelableData.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
com.example.remoteservicetest.ParcelableData _result = this.process(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.remoteservicetest.IRemoteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public com.example.remoteservicetest.ParcelableData process(com.example.remoteservicetest.ParcelableData data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.example.remoteservicetest.ParcelableData _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((data!=null)) {
_data.writeInt(1);
data.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_process, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.example.remoteservicetest.ParcelableData.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_process = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public com.example.remoteservicetest.ParcelableData process(com.example.remoteservicetest.ParcelableData data) throws android.os.RemoteException;
}
