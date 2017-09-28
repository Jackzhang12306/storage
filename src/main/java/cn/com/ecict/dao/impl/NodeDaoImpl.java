package cn.com.ecict.dao.impl;

import cn.com.ecict.bean.NodeBean;
import cn.com.ecict.dao.INodeDao;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 17-9-27.
 */
@Repository("nodeDao")
public class NodeDaoImpl extends BaseDaoImpl<NodeBean> implements INodeDao {

    @Override
    public boolean checkNodeIP(String ip) {
        String hql="select count(1) from NodeBean where nodeIp=?";
        return this.count(hql,ip) == 0 ? false : true;
    }
}
