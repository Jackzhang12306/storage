package cn.com.ecict.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取页面数据
 * @author cyq
 */
public class WebUtil {
    private URL url;
    private HttpURLConnection httpConn;
    private InputStreamReader input;
    private BufferedReader bufReader;
    private StringBuilder contentBuf;
    private boolean isopen=false;
    public WebUtil(){}

    public WebUtil(String url){
        try{
            this.url = new URL(url);
            this.httpConn = (HttpURLConnection)this.url.openConnection();
            isopen=true;
            input = new InputStreamReader(httpConn.getInputStream(), "utf-8");
            bufReader = new BufferedReader(input);
            String line = "";
            contentBuf = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                contentBuf.append(line);
            }
        }catch(Exception e){
            isopen=false;
            //System.out.println("WebpageCapture：异常，无法打开网页"+url);
            //e.printStackTrace();
        }
    }
    public boolean isOpen(){
        return isopen;
    }
    public void close(){
        try{
            bufReader.close();
            input.close();
            this.httpConn.connect();
        }catch(Exception e){
            System.out.println("WebpageCapture：关闭链接失败！");
            e.printStackTrace();
        }

    }
    /**
     * 在页面内容中查询key，以及key后到value
     * @param key
     * @param length，value的估计长度
     * @return
     */
    public String getString(String key){
        String buf = contentBuf.toString();
        int beginIx = buf.indexOf(key)+key.length()+4;
        String result = buf.substring(beginIx, beginIx+20);
        return result.split(",")[0];
    }
    public int getInt(String key){
        String buf = contentBuf.toString();
        int beginIx = buf.indexOf(key)+key.length()+4;
        String result = buf.substring(beginIx, beginIx+20);
        return this.parseInt(result);
    }
    public long getLong(String key){
        String buf = contentBuf.toString();
        int beginIx = buf.indexOf(key)+key.length()+4;
        String result = buf.substring(beginIx, beginIx+20);
        return this.parseLong(result);
    }
    private Integer parseInt(String strInt){
        return Integer.parseInt(strInt.split("\\D+")[0]);
    }
    private Long parseLong(String strInt){
        return Long.parseLong(strInt.split("\\D+")[0]);
    }
    public Double getFloat(String key){
        String buf = contentBuf.toString();
        int beginIx = buf.indexOf(key)+key.length()+4;
        String result = buf.substring(beginIx, beginIx+20);
        return this.parseFloat(result);
    }
    private Double parseFloat(String strInt){
        return Double.parseDouble(strInt.split("\\D+")[0]);
    }

    private Double drawDouble(String msg){
        char[] b = msg.toCharArray();
        String result = "";
        for(int i = 0; i < b.length; i++){
            if (("0123456789.").indexOf(b[i] + "") != -1){
                result += b[i];
            }
        }
        return Double.parseDouble(result);
    }

    private Integer drawInteger(String msg){
        char[] b = msg.toCharArray();
        String result = "";
        for(int i = 0; i < b.length; i++){
            if (("0123456789").indexOf(b[i] + "") != -1){
                result += b[i];
            }
        }
        return Integer.parseInt(result);
    }

    public String captureHTML(String begin,String end){
        String buf = contentBuf.toString();
        int beginIx = buf.indexOf(begin)+begin.length();
        int endIx = buf.indexOf(end);
        String result = buf.substring(beginIx, endIx);
        return result;
    }
    public String captureHTML(String begin,int end){
        String buf = contentBuf.toString();
        int beginIx = buf.indexOf(begin)+begin.length();
        String result = buf.substring(beginIx,beginIx+end);
        return result;
    }

    public Double getFloat(String begin,String end){
        String result=this.captureHTML(begin, end);
        return this.drawDouble(result);
    }

    public Integer getInt(String begin,String end){
        String result=this.captureHTML(begin, end);
        return this.drawInteger(result);
    }

    public static void main(String[] args) {
        String value;
        List<String> list=new ArrayList<String>();
        //WebpageCapture demo = new WebpageCapture("http://32.1.32.138:8088/jmx");
        //WebpageCapture web = new WebpageCapture("http://32.1.32.136:50070/jmx");
        //http://32.1.32.136:60010/jmx
        //http://32.1.32.136:60010/master-status
        //averageLoad
        //http://32.1.32.136:60010/master-status
        WebUtil web = new WebUtil("http://192.168.1.157:8099/");
        String result=web.captureHTML("Alive Workers:",50);

        System.out.println(web.getInt("<strong>Applications:</strong>","<a href=\"#running-app\">Running</a>"));
        System.out.println(web.getInt("<a href=\"#running-app\">Running</a>","<a href=\"#completed-app\">Completed</a>"));
    }
}
