package cn.com.ecict.dao.impl;

import cn.com.ecict.bean.User;
import cn.com.ecict.dao.IUserDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 17-9-26.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
    @Override
    public boolean getByName(String username) {
        User u=this.get("from User where username=?",new Object[]{username});
        return u != null;
    }
    @Override
    public User get(String username,String password) {
        return this.get("from User where username=? and password=?",new Object[]{username, password});
    }
    @Override
    public boolean add(User u){
        if(!this.getByName(u.getUsername())){
            this.save(u);
            return true;
        }
        else return false;
    }
    @Override
    public List<User> find(int page, int rows){
        return this.find("form User", page, rows);
    }
}
