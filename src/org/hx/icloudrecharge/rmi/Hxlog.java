package org.hx.icloudrecharge.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hxlog  extends Remote {

    public void LogInfo(String logMsg) throws RemoteException;

    public void ErrorLogInfo(String logMsg) throws RemoteException;

    public void ErrorLogInfo(String logMsg, Exception exception) throws RemoteException;
}

