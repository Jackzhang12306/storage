package cn.com.ecict.dao;


import cn.com.ecict.bean.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IUserDao extends IBaseDao<User>{
    //用户名判定
    boolean getByName(String username);
    //用户名和密码判定
    User get(String username, String password);
    //添加新用户
    boolean add(User u);
    //读取一页
    List<User> find(int page, int rows);
}
