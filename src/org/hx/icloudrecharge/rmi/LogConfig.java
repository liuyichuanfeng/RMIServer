package org.hx.icloudrecharge.rmi;

import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.util.Properties;

public class LogConfig {
    public static String server="";
    public static String port="";
    public static String api="";
    public static void config() {
        String filePath = System.getProperty("user.dir")+File.separator+ "log4j.properties";

        try{
            PropertyConfigurator.configure(filePath);
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            Properties props = new Properties();
            props.load(br);
            LogConfig.server=props.get("logRMIServer").toString();
            LogConfig.port=props.get("logRMIPort").toString();
            LogConfig.api=props.get("logRMIApi").toString();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
