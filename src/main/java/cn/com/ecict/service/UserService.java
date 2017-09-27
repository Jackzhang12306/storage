package cn.com.ecict.service;

import cn.com.ecict.bean.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserService extends BaseService<User>{

    /**
     * 用户名判定
     */
    boolean checkname(String username);
    /**
     * 用户登录
     */
    User login(String username,String password);
    /**
     *用户注册
     */
    boolean regist(User u);
    /**
     *用户退出
     */
    boolean exit();
}
