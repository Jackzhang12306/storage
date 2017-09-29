package cn.com.ecict.controller;

import cn.com.ecict.bean.NodeBean;
import cn.com.ecict.bean.ResultBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 所有控制器的父类，实现了推送进度相关方法
 * @author chengyuqiang
 *
 */
public class BaseController {

    public int savecount =0;
    public int pushcount =0 ;//已完成的部署个数
    public ResultBean pushResult;
    public DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public List<String> errorList;

    /**
     * 初始化部署进度
     * @param nodes
     * @return
     */
    public ResultBean initResultBean(List<NodeBean> nodes){
        savecount =0;
        if(null==pushResult)pushResult =new ResultBean();
        List<String> status = new ArrayList<String>();
        List<String> nodeIdList = new ArrayList<String>();
        List<String> startTimeList = new ArrayList<String>();
        List<String> endTimeList = new ArrayList<String>();
        List<String> startStatusList = new ArrayList<String>();
        List<String> infoList = new ArrayList<String>();
        List<String> nodeNameList = new ArrayList<String>();
        //List：先add后set
        for(NodeBean node:nodes){
            status.add("等待部署...");
            startTimeList.add("尚未开始...");
            endTimeList.add("时间未知...");
            startStatusList.add("请稍等，还没有结束.....");
            nodeIdList.add(node.getNodeId().toString());
            nodeNameList.add(node.getNodeName()+":"+node.getNodeRole());
            infoList.add("等待部署...");
        }
        pushResult.setPushStatus(status);
        pushResult.setNodeId(nodeIdList);
        pushResult.setStartTime(startTimeList);
        pushResult.setEndTime(endTimeList);
        pushResult.setStartStatus(startStatusList);
        pushResult.setInfo(infoList);
        pushResult.setNodeName(nodeNameList);
        return pushResult;
    }

    /**
     * 开始检测节点
     * @author cyq
     * @param node
     */
    public void setCheckStart(NodeBean node){
        String startTime=format.format(new Date());
        pushResult.getStartTime().set(node.getIndex(),startTime);
        String info =pushResult.getInfo().get(node.getIndex());
        info+=";"+startTime+" 开始环境检测...";
        pushResult.getInfo().set(node.getIndex(), info);
        pushResult.getPushStatus().set(node.getIndex(), "开始环境检测...");
    }
    /**
     * 检测节点结束
     * @author cyq
     * @param node
     */
    public void setCheckEnd(NodeBean node){
        String time=format.format(new Date());
        String info =pushResult.getInfo().get(node.getIndex());
        info+=";"+time+" 环境检测结束...";
        pushResult.getInfo().set(node.getIndex(), info);
        pushResult.getPushStatus().set(node.getIndex(), "环境检测结束...");
    }


}
