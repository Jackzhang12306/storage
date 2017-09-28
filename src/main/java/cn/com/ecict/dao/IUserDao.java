package cn.com.ecict.dao;


import cn.com.ecict.bean.UserBean;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IUserDao extends IBaseDao<UserBean>{
    //用户名判定
    boolean getByName(String username);
    //用户名和密码判定
    UserBean get(String username, String password);
    //添加新用户
    boolean add(UserBean u);
    //读取一页
    List<UserBean> find(int page, int rows);
}
