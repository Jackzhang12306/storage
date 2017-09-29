package cn.com.ecict.dao.impl;
/**
 * Hibernate5 org.hibernate.Query过时
 */

import cn.com.ecict.dao.IBaseDao;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 17-9-26.
 */
@Repository("baseDao")
public class BaseDaoImpl <T> implements IBaseDao<T> {
    //Spring3.1以后，不再支持HibernateTemplate,只能使用原生的session接口来操作
    @Autowired
    private SessionFactory sessionFactory;

    //单例模式获取Session
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public Serializable save(T o) {
        return getSession().save(o);
    }
    @Override
    public void delete(T o) {
        getSession().delete(o);
    }
    @Override
    public void update(T o) {
        getSession().update(o);
    }
    @Override
    public void saveOrUpdate(T o) {
        getSession().saveOrUpdate(o);
    }
    @Override
    public List<T> find(String hql) {
        return getSession().createQuery(hql).list();
    }
    @Override
    public List<T> find(String hql, Object[] param) {
        Query q = getSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.list();
    }
    @Override
    public List<T> find(String hql, List<Object> param) {
        Query q = getSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.list();
    }
    @Override
    public List<T> find(String hql,int page,int rows) {
        Query q = getSession().createQuery(hql);
        return (List<T>)q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }
    @Override
    public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
        if (page == null || page < 1)  page = 1;
        if (rows == null || rows < 1)  rows = 10;
        Query q = getSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }
    @Override
    public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = getSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }
    @Override
    public T get(Class<T> c, long id) {
        return getSession().get(c, id);
    }

    @Override
    public T get(String hql, Object... param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Object getField(String hql, Object... param) {
        Query q = getSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return  q.uniqueResult();
    }

    @Override
    public boolean updateField(String hql, Object... param) {
        Query q = getSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.executeUpdate()==0;
    }

    @Override
    public T get(String hql, List<Object> param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }
    @Override
    public Long count(String hql) {
        return (Long) getSession().createQuery(hql).uniqueResult();
    }
    @Override
    public Long count(String hql, Object... param) {
        Query q = getSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return (Long) q.uniqueResult();
    }
    @Override
    public Long count(String hql, List<Object> param) {
        Query q = getSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return (Long) q.uniqueResult();
    }
    @Override
    public Integer executeHql(String hql) {
        return getSession().createQuery(hql).executeUpdate();
    }
    @Override
    public Integer executeHql(String hql, Object[] param) {
        Query q = getSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.executeUpdate();
    }
    @Override
    public Integer executeHql(String hql, List<Object> param) {
        Query q = getSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.executeUpdate();
    }
}
