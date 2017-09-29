package cn.com.ecict.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.com.ecict.bean.NodeBean;

/**
 * 输出到终端工具类
 * @author cyq
 *
 */
public class OutUtil {
    /**
     * 输出节点列表
     * @param nodes
     */
    public static void print(List<NodeBean> nodes){
        for(NodeBean node:nodes)
            System.out.println(node.toString());
    }

    public static final void printMap(Map<String,String> map){
        for(String key:map.keySet())
            System.out.println(key+":"+map.get(key));
    }

    public static final void printMapObject(Map<String,Object> map){
        for(String key:map.keySet())
            System.out.println(key+":"+map.get(key));
    }

    public static final Map<String,String> toMap(Map<String,Object> data){
        Map<String,String> map=new HashMap<>();
        for(String key:data.keySet()){
            map.put(key, data.get(key).toString());
        }
        return map;
    }
}
