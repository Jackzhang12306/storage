package cn.com.ecict.controller;

import cn.com.ecict.bean.NodeBean;
import cn.com.ecict.service.INodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 17-9-27.
 */
@Controller
@RequestMapping(value = "/node")
public class NodeController {
    @Resource(name="nodeService")
    private INodeService nodeService;
    private static final Logger logger = Logger.getLogger(NodeController.class);

    /**
     * 获取集群节点列表
     * @return
     */
    @RequestMapping("/getNodeList.do")
    @ResponseBody
    public List<NodeBean> getNodeList(){

        //return nodeService.getNodeList();
        return null;
    }
}
