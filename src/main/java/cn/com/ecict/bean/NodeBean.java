package cn.com.ecict.bean;

import javax.persistence.*;

/**
 * create table nodes(
 *  nodeId int auto_increment primary key,
 *  nodeName varchar(45) not null,
 *  nodeIp varchar(45) not null,
 *  username varchar(45) not null,
 *  password varchar(100),
 *  nodeRole varchar(100),
 *  nodeStatus int default 0
 * );
 * Created by root on 17-9-27.
 */
@Entity
@Table(name="nodes")
public class NodeBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer     nodeId;
    private String      nodeName;
    private String      nodeIp;
    private String      username;
    private String      password;
    private String      nodeRole;
    private String      nodeStatus;
    private Integer     index;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNodeRole() {
        return nodeRole;
    }

    public void setNodeRole(String nodeRole) {
        this.nodeRole = nodeRole;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


}
