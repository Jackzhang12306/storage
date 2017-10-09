package cn.com.ecict.controller;

import cn.com.ecict.bean.NodeBean;
import cn.com.ecict.service.INodeService;
import cn.com.ecict.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-9-27.
 */
@Controller
@RequestMapping(value = "/node")
public class NodeController extends BaseController{
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
        return nodeService.getNodeList();
    }

    @RequestMapping("/addNode.do")
    @ResponseBody
    public Map<String, String> addNode(String ip, String username,String password){
        if(StringUtil.isEmpty(ip)||StringUtil.isEmpty(username)||StringUtil.isEmpty(password)){
            System.out.println("节点关键信息缺失！");
            return null;
        }
        if(nodeService.checkNodeIP(ip)){
            System.out.println("IP地址已经存在，不能重复添加！");
            return null;
        }

        return null;
    }

}
