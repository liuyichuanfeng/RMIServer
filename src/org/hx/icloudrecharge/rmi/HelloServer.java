package org.hx.icloudrecharge.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class HelloServer {

    public static void main(String args[]) {
        LogConfig.config();
//        System.out.println("rmi://"+LogConfig.server+":"+LogConfig.port+"/"+LogConfig.api);
        try {
            //创建一个远程对象
            Hxlog hxlog = new HxlogImpl();
            //生成远程对象注册表Registry的实例，并指定端口为8888（默认端口是1099）
            LocateRegistry.createRegistry(Integer.parseInt(LogConfig.port));
            //把远程对象注册到RMI注册服务器上
            //绑定的URL标准格式为：rmi://host:port/name(协议名可以省略，下面两种写法都可以）
            Naming.bind("rmi://"+LogConfig.server+":"+LogConfig.port+"/"+LogConfig.api, hxlog);
//            System.out.println(">>INFO:远程对象绑定成功！");
        } catch (RemoteException e) {
            System.out.println("创建远程对象发生异常！");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.out.println("发生重复绑定对象异常！");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("发生URL畸形异常！");
            e.printStackTrace();
        }
    }
}  