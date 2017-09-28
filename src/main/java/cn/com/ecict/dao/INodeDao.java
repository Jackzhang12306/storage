package cn.com.ecict.dao;

import cn.com.ecict.bean.NodeBean;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 17-9-27.
 */
@Repository
public interface INodeDao extends IBaseDao<NodeBean>{
    /**
     * 判定数据库中是否存在该IP的节点
     * @return
     */
    boolean checkNodeIP(String ip);
}
