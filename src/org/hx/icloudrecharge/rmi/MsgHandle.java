package org.hx.icloudrecharge.rmi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgHandle {
    public static String[] pushArray(String[] arr, String num) {
        String[] result = new String[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        result[result.length-1] = num;
        return result;
    }

    public static String deleteMsg(String str){
        String regPwd=",{0,1}\\s*\\\\{0,1}\\s*\"\\s*(Password|pwd|oldPwd|newPwd|passwd|DKey|OldPassword|NewPassword)\\s*\\\\{0,1}\\s*\"\\s*:\\s*\\S+\\s*\"";
        String[] arr = str.split(regPwd);
        String deleteMsg="";
        for(String s : arr)
        {
            deleteMsg+=s;
        }
        return deleteMsg;
    }

    public static  String hidemobile(String str){
        String regMobile="\"\\s*1\\d{10}\\s*\\\\{0,1}\\s*\"";
        String[] arr = str.split(regMobile);
        String[] arrReg=new String[]{};
        String hideMobile=arr[0];
        Pattern pattern = Pattern.compile(regMobile);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            arrReg=pushArray(arrReg,matcher.group());
        }
        if(arrReg!=null){

            for(int i=1;i<arr.length;i++){
                arrReg[i-1] = arrReg[i-1].substring(0, arrReg[i-1].indexOf('1')) +arrReg[i-1].substring(arrReg[i-1].indexOf('1'), arrReg[i-1].indexOf('1')+3) + "****" + arrReg[i-1].substring(arrReg[i-1].indexOf('1')+7, arrReg[i-1].length());
                //System.out.println(arrReg[i-1]);
                hideMobile=hideMobile+arrReg[i-1]+arr[i];
            }
        }
        return hideMobile;
    }
}
