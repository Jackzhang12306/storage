package cn.com.ecict.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.PropertyResourceBundle;

public class PropertiesUtil {

    protected static Properties prop = new Properties();

    private final static PropertyResourceBundle config = (PropertyResourceBundle) PropertyResourceBundle
            .getBundle("config/suConfig");

    public static void load(String fileName) {
        InputStream stream = null;
        try {
            stream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            if (stream != null)
                prop.load(stream);
        } catch (IOException e) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public final static String get(String property) {
        try {
            return config.getString(property);
        } catch (Exception e) {
            System.out.println("Load System Property error := " + e.getMessage());
            return null;
        }
    }

    public static void setProProperty(String key, String value){
        prop.setProperty(key, value);
    }

    public static void savePropertieFile(String filePath){
        try {
            OutputStream fos = new FileOutputStream(filePath);
            prop.store(fos, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
/*//		String aa = PropertiesUtil.get("jdbc.url");
//		System.out.println(aa);

		PropertiesUtil.load("config/service.properties");
		String initLimit = PropertiesUtil.getProperty("initLimit");
		String syncLimit = PropertiesUtil.getProperty("syncLimit");
		String tickTime = PropertiesUtil.getProperty("tickTime");
		String zookeeperClientPort = PropertiesUtil.getProperty("clientPort");
		String zookeeperLeaderElectPort = PropertiesUtil.getProperty("leaderElectPort");
		String zookeeperPeerCommunicatePort = PropertiesUtil.getProperty("peerCommunicatePort");

///		System.out.println(url);

		PropertiesUtil.load("11.properties");
//		PropertiesUtil.setProProperty("name", "45");
		PropertiesUtil.setProProperty("yyy", "er");
		PropertiesUtil.savePropertieFile(PropertiesUtil.class.getResource("/").getFile().toString() + "22.properties");*/

        String webServerIP="";
        try {
            webServerIP = InetAddress.getLocalHost().getHostAddress()+":8649";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        PropertiesUtil.load("config/hadoop-metrics2.properties");
        PropertiesUtil.setProProperty("*.sink.file.class","org.apache.hadoop.metrics2.sink.FileSink");
        PropertiesUtil.setProProperty("*.sink.ganglia.class","org.apache.hadoop.metrics2.sink.ganglia.GangliaSink31");
        PropertiesUtil.setProProperty("*.sink.ganglia.period","60");
        PropertiesUtil.setProProperty("*.sink.ganglia.supportsparse","false");
        PropertiesUtil.setProProperty("*.sink.ganglia.dmax","jvm.metrics.threadsBlocked=70,jvm.metrics.memHeapUsedM=40");
        PropertiesUtil.setProProperty("namenode.sink.ganglia.servers",webServerIP);
        PropertiesUtil.setProProperty("datanode.sink.ganglia.servers",webServerIP);
        PropertiesUtil.setProProperty("resourcemanager.sink.ganglia.servers",webServerIP);
        PropertiesUtil.setProProperty("nodemanager.sink.ganglia.servers",webServerIP);
        PropertiesUtil.setProProperty("maptask.sink.ganglia.servers",webServerIP);
        PropertiesUtil.setProProperty("reducetask.sink.ganglia.servers",webServerIP);
        PropertiesUtil.setProProperty("dfs.class","org.apache.hadoop.metrics.ganglia.GangliaContext31");
        PropertiesUtil.setProProperty("dfs.period","60");
        PropertiesUtil.setProProperty("dfs.servers",webServerIP);
        PropertiesUtil.setProProperty("mapred.class","org.apache.hadoop.metrics.ganglia.GangliaContext31");
        PropertiesUtil.setProProperty("mapred.period","60");
        PropertiesUtil.setProProperty("mapred.servers",webServerIP);
        String savePath = "/tmp/hadoop-metrics2.properties";
        PropertiesUtil.savePropertieFile(savePath);
    }
}
