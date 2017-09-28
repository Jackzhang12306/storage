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
    public boolean checkUsername(String username) {
        long count=this.count("select count(1) from UserBean where username=?",username);
        return count==1;
    }
    @Override
    public UserBean get(String username,String password) {
        return this.get("from UserBean where username=? and password=?",new Object[]{username, password});
    }
    @Override
    public boolean add(UserBean u){
        if(!this.checkUsername(u.getUsername())){
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
