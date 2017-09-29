package cn.com.ecict.service;

import cn.com.ecict.bean.UserBean;

import java.util.List;


public interface IUserService extends IBaseService<UserBean>{

    /**
     * 用户名判定
     */
    boolean checkUsername(String username);
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

    /**
     * 获取节点列表
     * @return
     */
    List<UserBean> getUserList();

    /**
     * 获取用户status值
     */
    Integer getUserStatus(Integer userId);

    /**
     * 更新admin用户status值
     * @return
     */
    boolean updateUserStatus(Integer userId,Integer status);


}
