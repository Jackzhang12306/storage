package cn.com.ecict.bean;

import java.util.List;

/**
 * Created by root on 17-9-28.
 */
public class ResultBean {
    private int progress;
    private List<String> nodeId;
    private List<String> nodeName;
    private List<String> pushStatus;
    private List<String> startStatus;
    private List<String> startTime;
    private List<String> endTime;
    private List<String> info;
    private String tip;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public List<String> getNodeId() {
        return nodeId;
    }

    public void setNodeId(List<String> nodeId) {
        this.nodeId = nodeId;
    }

    public List<String> getNodeName() {
        return nodeName;
    }

    public void setNodeName(List<String> nodeName) {
        this.nodeName = nodeName;
    }

    public List<String> getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(List<String> pushStatus) {
        this.pushStatus = pushStatus;
    }

    public List<String> getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(List<String> startStatus) {
        this.startStatus = startStatus;
    }

    public List<String> getStartTime() {
        return startTime;
    }

    public void setStartTime(List<String> startTime) {
        this.startTime = startTime;
    }

    public List<String> getEndTime() {
        return endTime;
    }

    public void setEndTime(List<String> endTime) {
        this.endTime = endTime;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "progress=" + progress +
                ", nodeId=" + nodeId +
                ", nodeName=" + nodeName +
                ", pushStatus=" + pushStatus +
                ", startStatus=" + startStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", info=" + info +
                ", tip='" + tip + '\'' +
                '}';
    }
}
