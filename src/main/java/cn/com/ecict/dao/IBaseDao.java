package cn.com.ecict.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 17-9-26.
 */
public interface IBaseDao <T>{
    //保存对象
    Serializable save(T o);
    // 删除对象
    void delete(T o);
    //更新对象
    void update(T o);
    //保存或更新对象
    void saveOrUpdate(T o);
    // 获得一个对象
    T get(Class<T> c, long id);
    // 获得一个对象
    T get(String hql, Object[] param);
    //对象总数
    Long count(String hql);
    // 查询
    List<T> find(String hql);
    // 查询集合
    List<T> find(String hql, Object[] param);
    // 查询集合
    List<T> find(String hql, List<Object> param);
    //分页查询，第page页，共rows行
    List<T> find(String hql, int page, int rows);
    //分页查询,page 查询第几页,rows 每页显示几条记录
    List<T> find(String hql, Object[] param, Integer page, Integer rows);
    // 分页查询
    List<T> find(String hql, List<Object> param, Integer page, Integer rows);

    // 获得一个对象：select count(*) from 类
    T get(String hql, List<Object> param);

    // select count(*) from 类
    Long count(String hql, Object[] param);
    //select count(*) from 类
    Long count(String hql, List<Object> param);
    // 执行HQL语句
    Integer executeHql(String hql);
    //执行HQL语句
    Integer executeHql(String hql, Object[] param);
    //执行HQL语句
    Integer executeHql(String hql, List<Object> param);
}
