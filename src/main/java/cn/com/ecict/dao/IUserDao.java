package cn.com.ecict.dao;


import cn.com.ecict.bean.UserBean;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IUserDao extends IBaseDao<UserBean>{

    //用户名和密码判定
    UserBean get(String username, String password);
    //添加新用户
    boolean add(UserBean u);
    //读取一页
    List<UserBean> find(int page, int rows);

    /**
     * 读取用户status字段值
     * @param userId
     * @return
     */
    Integer getUserStatus(int userId);

    /**
     * 读取指定用户的userId字段值
     * @param username
     * @return
     */
    Integer getUserId(String username);

    /**
     * 更新status字段
     */
    boolean updateStatus(Integer userId,Integer status);
}
