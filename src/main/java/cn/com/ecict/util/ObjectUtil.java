package cn.com.ecict.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

/**
 * 	 对象工具类
 * @author chengyuqiang
 *
 */
public class ObjectUtil {
    public static boolean isEmpty(List<?> list){
        if(null==list){
            return true;
        }else if(list.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isEmpty(Map<?,?> map){
        if(null==map){
            return true;
        }else if(map.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        List<String> list=null;
        System.out.println(ObjectUtil.isEmpty(list));
        System.out.println(CollectionUtils.isEmpty(list));
        list=new ArrayList<>();
        System.out.println(ObjectUtil.isEmpty(list));
        System.out.println(CollectionUtils.isEmpty(list));
        list.add("aaa");
        System.out.println(ObjectUtil.isEmpty(list));
        System.out.println(CollectionUtils.isEmpty(list));
    }
}
