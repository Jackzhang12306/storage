package cn.com.ecict.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * Created by root on 17-9-29.
 */
public class JsonUtil {
    // 定义jackson对象
    private static final ObjectMapper objMapper = new ObjectMapper();
    public static Gson gson = new Gson();

    /**
     * 对象转换成字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return objMapper.writeValueAsString(obj);
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * JSON字符串转换成对象
     *
     * @param jsonStr
     * @return
     */
    public static <T> T toObject(String jsonStr, Class<T> cls) {
        try {
            return objMapper.readValue(jsonStr, cls);
        } catch (Exception e) {

        }
        return null;
    }


    /**
     * 将JSON字符串转换为对象
     *
     * @param json     JSON字符串
     * @param javaType 对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, JavaType javaType) {
        try {
            return objMapper.readValue(json, javaType);
        } catch (Exception e) {

        }
        return null;
    }

    public static String toGson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 将对象转换成json字符串
     *
     * @param data
     * @return
     */
    public static String toJSONString(Object data) {
        try {
            String string = objMapper.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> T parseObject(String jsonData, Class<T> beanType) {
        try {
            T t = objMapper.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成list
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> parseArray(String jsonData, Class<T> beanType) {
        JavaType javaType = objMapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = objMapper.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        String param="{\"datanode.port\":\"50010\",\"namenode.rpc-port\":\"8020\",\"dfs.replication\":\"4\",\"namenode.http-port\":\"50070\",\"datanode.http-port\":\"50075\",\"journalnode.http-port\":\"8480\",\"dfs.namenode.checkpoint.dir\":\"/hadoop/namesecondary\",\"dataDirs\":[{\"nodeId\":\"b7159b1b-42c8-4c90-91f1-183d145d8745\",\"value\":\"/hadoop/data\"},{\"nodeId\":\"a9b2650c-a8e6-4c02-b5bf-7fbfa5f92b70\",\"value\":\"/hadoop/data\"},{\"nodeId\":\"8f944a4d-df65-46d4-94a9-3bd2f9887138\",\"value\":\"/hadoop/data\"}]}";
        Map<String,Object> map=JsonUtil.toObject(param, Map.class);
        for(String key:map.keySet()){
            System.out.println(key+"==>"+map.get(key));
        }
        /**
             datanode.port==>50010
             namenode.rpc-port==>8020
             dfs.replication==>4
             namenode.http-port==>50070
             datanode.http-port==>50075
             journalnode.http-port==>8480
             dfs.namenode.checkpoint.dir==>/hadoop/namesecondary
             dataDirs==>[{nodeId=b7159b1b-42c8-4c90-91f1-183d145d8745, value=/hadoop/data}, {nodeId=a9b2650c-a8e6-4c02-b5bf-7fbfa5f92b70, value=/hadoop/data}, {nodeId=8f944a4d-df65-46d4-94a9-3bd2f9887138, value=/hadoop/data}]
         *
         */
        System.out.println("#############################");
        List<Map<String,String>> dataDirs=(List<Map<String,String>>)map.get("dataDirs");
        for(Map<String,String> m:dataDirs){
            String nodeid=m.get("nodeId");
            String dataDir=m.get("value");
            System.out.println(nodeid+":"+dataDir);
        }
        /**
         b7159b1b-42c8-4c90-91f1-183d145d8745:/hadoop/data
         a9b2650c-a8e6-4c02-b5bf-7fbfa5f92b70:/hadoop/data
         8f944a4d-df65-46d4-94a9-3bd2f9887138:/hadoop/data
         *
         */

    }
}
