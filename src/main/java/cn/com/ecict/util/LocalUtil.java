package cn.com.ecict.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 执行本地命令工具类
 *
 * @author cyq
 *
 */
public class LocalUtil {
    /**
     * 获取默认格式的当前日期时间
     *
     * @return
     */
    public static String getCurrentDatetime() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

    public static final String getOS(){
        return null;
    }

    public static final String getIP() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            return ia.getHostAddress();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public static final String exe(String cmd) {
        StringBuffer sb = null;
        Process process = null;
        LineNumberReader reader = null;
        try {
            sb = new StringBuffer();
            process = Runtime.getRuntime().exec(cmd);
            reader = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            process.destroy();
        }
    }
    public static void main(String[] args){
        String r=LocalUtil.exe("nmap -sP 192.168.1.0/24");
        System.out.println(r);
        LocalUtil.exe("/usr/bin/echo \"\" > /root/test.txt");
        String version="";
        //LocalUtil.exe("echo "+version+" > /root/test.txt");
    }
}
