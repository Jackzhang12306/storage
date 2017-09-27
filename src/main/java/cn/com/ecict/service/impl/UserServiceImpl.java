package cn.com.ecict.service.impl;

import cn.com.ecict.bean.User;

import cn.com.ecict.dao.IUserDao;
import cn.com.ecict.service.UserService;
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
public class UserServiceImpl  extends BaseServiceImpl<User> implements UserService {

    @Resource(name="userDao")
    private IUserDao userDao;

    @Override
    public IUserDao getDao() {
        return userDao;
    }
    @Override
    public User get(Integer id){
        return getDao().get(User.class, id);
    }
    public boolean checkname(String username){
        return getDao().getByName(username);
    }
    //登录
    @Override
    public User login(String username,String password){
        System.out.println(username+","+password);
        return getDao().get(username, password);
    }
    //注册
    @Transactional
    @Override
    public boolean regist(User u){
        return getDao().add(u);
    }

    @Override
    public boolean exit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getPage(Integer page, Integer rows) {
        return getDao().find(page,rows);
    }

}
