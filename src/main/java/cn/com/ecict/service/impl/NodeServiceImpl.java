package cn.com.ecict.service.impl;

import cn.com.ecict.bean.NodeBean;
import cn.com.ecict.dao.INodeDao;
import cn.com.ecict.service.INodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by root on 17-9-27.
 */
@Service("nodeService")
@Transactional(rollbackFor = Exception.class)
public class NodeServiceImpl extends BaseServiceImpl<NodeBean> implements INodeService {
    @Resource(name="nodeDao")
    private INodeDao nodeDao;

    @Override
    public INodeDao getDao() {
        return nodeDao;
    }

    @Override
    public NodeBean get(Integer id){
        return getDao().get(NodeBean.class, id);
    }
}
