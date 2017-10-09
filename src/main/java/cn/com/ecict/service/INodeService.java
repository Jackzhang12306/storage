package cn.com.ecict.service;

import cn.com.ecict.bean.NodeBean;

import java.util.List;

/**
 * Created by root on 17-9-27.
 */
public interface INodeService extends IBaseService<NodeBean> {
    /**
     * 判定数据库中是否存在该IP的节点
     * @param ip
     * @return
     */
    boolean checkNodeIP(String ip);

    /**
     * 获取节点列表
     * @return
     */
    List<NodeBean> getNodeList();

    /**
     * 通过NodeId获取nodeIp
     * @param nodeId
     * @return
     */
    String getNodeIp(String nodeId);
}
