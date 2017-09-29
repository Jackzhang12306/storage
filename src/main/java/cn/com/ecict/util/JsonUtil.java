package cn.com.ecict.util;

import java.util.List;

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
}
