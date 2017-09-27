package cn.com.ecict.service;

import java.util.List;

/**
 * Created by root on 17-9-26.
 */
public interface BaseService <T>{
    //获取对象
    T get(Integer id);
    //保存对象
    void save(T t);
    //删除对象
    void delete(T t);
    //更新对象
    void update(T t);
    //获取一页
    List<T> getPage(Integer page,Integer rows);
}
