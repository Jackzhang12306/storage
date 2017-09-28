package cn.com.ecict.service.impl;

import cn.com.ecict.dao.IBaseDao;
import cn.com.ecict.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by root on 17-9-26.
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    public abstract IBaseDao<T> getDao();

    @Transactional//事务管理
    @Override
    public void save(T entity) {
        getDao().save(entity);
    }

    @Transactional
    @Override
    public void update(T entity) {
        getDao().update(entity);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        getDao().delete(entity);
    }

    @Override
    public List<T> getList(Class<T> c){
        String hql="from "+c.getSimpleName();
        System.out.println(hql);
        return getDao().find(hql);
    }

    public List<T> getByHQL(String hql, Object... params) {
        return getDao().find(hql, params);
    }
}
