package org.hx.icloudrecharge.rmi;

import org.apache.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;

public class HxlogImpl extends UnicastRemoteObject implements Hxlog {
    private static final long serialVersionUID = 1961558474342609777L;
    public HxlogImpl()throws RemoteException {
        super();
    }

    private static Logger log4jErrLog = null;
    private static Logger log4jNormalLog = null;
    private static Logger log4jDeployLog = null;
    private static Logger log4jWSErrLog = null;

    public static void initializeLog4j() throws Exception {

        log4jErrLog = Logger.getLogger("errorLog");
        log4jNormalLog = Logger.getLogger("normalLog");
        log4jDeployLog = Logger.getLogger("deployLog");
        log4jWSErrLog = Logger.getLogger("errorLog");

    }

    public void LogInfo(String logMsg) {
        String msg=MsgHandle.deleteMsg(logMsg);
        msg=MsgHandle.hidemobile(msg);
        HxlogImpl.NormalLog(msg);
    }

    /**
     * 错误日志记录
     *
     * @param logMsg 错误信息
     */
    public void ErrorLogInfo(String logMsg) {
        String msg=MsgHandle.deleteMsg(logMsg);
        msg=MsgHandle.hidemobile(msg);
        HxlogImpl.ErrorLog(msg);
    }

    /**
     * 错误日志记录
     *
     * @param strExecute string参数
     * @param exception 异常信息
     */
    public void ErrorLogInfo(String strExecute, Exception exception) {
        String msg=MsgHandle.deleteMsg(strExecute);
        msg=MsgHandle.hidemobile(msg);
        StringBuffer sbLog = new StringBuffer();
        sbLog.append("strParam:").append(msg);
        if (exception != null) {
            StackTraceElement[] trace = exception.getStackTrace();
            for (StackTraceElement tempTrace : trace) {
                sbLog.append("\r\n").append(tempTrace);
            }
        }
        HxlogImpl.ErrorLog(sbLog.toString());
        sbLog.delete(0, sbLog.length());
        sbLog = null;
    }

    /**
     * websocket 错误日志
     *
     * @param logMsg
     */
    public static void wsErrorLogInfo(String logMsg) {
        HxlogImpl.WSErrorLog(logMsg);
    }

    /**
     * websocket 错误日志
     *
     * @param logMsg
     * @param exception
     */
    public static void wsErrorLogInfo(String logMsg, Exception exception) {
        StringBuffer sbLog = new StringBuffer();
        sbLog.append("errorMessage:").append(logMsg);
        if (exception != null) {
            StackTraceElement[] trace = exception.getStackTrace();
            for (StackTraceElement tempTrace : trace) {
                sbLog.append("\r\n").append(tempTrace);
            }
        }
        wsErrorLogInfo(sbLog.toString());
        sbLog.delete(0, sbLog.length());
        sbLog = null;
    }

    private static void SetUpLogInfo(String logMsg) {
        HxlogImpl.DeployLog(logMsg);
    }

    private static void WSErrorLog(String logMsg)  {
        if (null == log4jWSErrLog) {
            try {
                initializeLog4j();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(HxlogImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log4jWSErrLog.error(logMsg);
    }

    private static void ErrorLog(String logMsg)  {
        if (null == log4jErrLog) {
            try {
                initializeLog4j();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(HxlogImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log4jErrLog.error(logMsg);
    }

    private static void NormalLog(String logMsg) {
        if (null == log4jNormalLog) {
            try {
                initializeLog4j();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(HxlogImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log4jNormalLog.info(logMsg);
    }

    private static void DeployLog(String logMsg)  {
        if (null == log4jDeployLog) {
            try {
                initializeLog4j();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(HxlogImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log4jDeployLog.info(logMsg);
    }
}