package cn.com.ecict.service.impl;

import cn.com.ecict.bean.UserBean;

import cn.com.ecict.dao.IUserDao;
import cn.com.ecict.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 17-9-26.
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl  extends BaseServiceImpl<UserBean> implements IUserService {

    @Resource(name="userDao")
    private IUserDao userDao;

    @Override
    public IUserDao getDao() {
        return userDao;
    }
    @Override
    public UserBean get(Integer id){
        return getDao().get(UserBean.class, id);
    }

    @Override
    public boolean checkUsername(String username){
        String result=(String)getDao().getField("from UserBean where username=?",username);
        if(result!=null){
            return true;
        }else{
            return false;
        }
    }
    //登录
    @Override
    public UserBean login(String username,String password){
        System.out.println(username+","+password);
        return getDao().get(username, password);
    }
    //注册
    @Transactional
    @Override
    public boolean regist(UserBean u){
        return getDao().add(u);
    }

    @Override
    public boolean exit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserBean> getUserList() {
        return this.getList(UserBean.class);
    }

    @Override
    public Integer getUserStatus(Integer userId) {
        return getDao().getUserStatus(userId);
    }

    @Override
    public boolean updateUserStatus(Integer userId,Integer status) {
        return getDao().updateStatus(userId,status);
    }


}
