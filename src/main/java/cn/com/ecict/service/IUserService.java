package cn.com.ecict.service;

import cn.com.ecict.bean.UserBean;


public interface IUserService extends IBaseService<UserBean>{

    /**
     * 用户名判定
     */
    boolean checkname(String username);
    /**
     * 用户登录
     */
    UserBean login(String username,String password);
    /**
     *用户注册
     */
    boolean regist(UserBean u);
    /**
     *用户退出
     */
    boolean exit();
}
