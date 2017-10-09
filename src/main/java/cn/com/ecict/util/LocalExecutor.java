package cn.com.ecict.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 执行本地命令工具类LocalExecutor
 *
 * @author cyq
 *
 */
public class LocalExecutor {

    /**
     * 获取本机IP地址
     * @return
     */
    public static final String getLocalIP() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            return ia.getHostAddress();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    /**
     * 判定IP是否可达
     * @param ip
     * @return
     */
    public static final boolean isIpReachable(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (address.isReachable(3000)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(ip+"网络不可达！");
            e.printStackTrace();
            return false;
        }
    }

    public static final Result exec(String cmd) {
        Result result;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(cmd);
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            StringBuffer output = new StringBuffer();
            while ((line = stdoutReader.readLine()) != null) {
                output.append(line).append("\n");
            }

            StringBuffer error= new StringBuffer();
            while ((line = stderrReader.readLine()) != null) {
                error.append(line).append("\n");
            }
            int exitVal = process.waitFor();
            if(exitVal==0){
                result=new Result(exitVal,output.toString());
            }else{
                result=new Result(exitVal,error.toString());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){

        String ip=LocalExecutor.getLocalIP();
        System.out.println(ip);
        boolean flag=LocalExecutor.isIpReachable("192.168.2.81");
        System.out.println(flag);
        Result result=LocalExecutor.exec("java");
        System.out.println("exitVal:\n"+result.getExitCode());
        System.out.println("OUTPUT:\n"+result.getOutput());

        /**
         * 如果你想进行输入输出重定向，pipeline等操作，则必须通过程序来实现。不能直接在command参数中做。
         * 例如，下面的例子
         * Process process = runtime.exec("java -version > a.txt");
         */
        result=LocalExecutor.exec("sh /root/data/genData.sh > /root/data/genData.txt");
        System.out.println("exitVal:\n"+result.getExitCode());
        System.out.println("OUTPUT:\n"+result.getOutput());

    }
}
class Result {
    private int exitCode;
    private String output;


    public Result(int exitCode, String output) {
        this.exitCode = exitCode;
        this.output = output;

    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}

