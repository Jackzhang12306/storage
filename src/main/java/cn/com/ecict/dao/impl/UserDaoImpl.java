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
    public UserBean get(String username,String password) {
        return this.get("from UserBean where username=? and password=?",username, password);
    }
    @Override
    public boolean add(UserBean u){
        if(this.count("select count(1) from UserBean where username=?",u.getUsername())==0){
            this.save(u);
            return true;
        }
        else return false;
    }
    @Override
    public List<UserBean> find(int page, int rows){
        return this.find("form User", page, rows);
    }

    @Override
    public Integer getUserStatus(int userId) {
        return (Integer) this.getField("select status from UserBean where uid=?",userId);
    }

    @Override
    public Integer getUserId(String username) {
        return (Integer) this.getField("select uid from UserBean where username=?",username);
    }

    @Override
    public boolean updateStatus(Integer userId, Integer status) {
        return this.updateField("update UserBean u set u.status=? where u.uid=?",status,userId);
    }
}
