package cn.com.ecict.service;

import java.util.List;

/**
 * Created by root on 17-9-26.
 */
public interface IBaseService <T>{
    //获取对象
    T get(Integer id);
    //保存对象
    void save(T t);
    //删除对象
    void delete(T t);
    //更新对象
    void update(T t);

    //获取T列表
    List<T> getList(Class<T> c);
}
