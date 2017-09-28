package cn.com.ecict.dao.impl;

import cn.com.ecict.bean.UserBean;
import cn.com.ecict.dao.IUserDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 17-9-26.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserBean> implements IUserDao {
    @Override
    public boolean getByName(String username) {
        UserBean u=this.get("from UserBean where username=?",new Object[]{username});
        return u != null;
    }
    @Override
    public UserBean get(String username,String password) {
        return this.get("from UserBean where username=? and password=?",new Object[]{username, password});
    }
    @Override
    public boolean add(UserBean u){
        if(!this.getByName(u.getUsername())){
            this.save(u);
            return true;
        }
        else return false;
    }
    @Override
    public List<UserBean> find(int page, int rows){
        return this.find("form User", page, rows);
    }
}
