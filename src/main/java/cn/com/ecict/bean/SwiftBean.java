package cn.com.ecict.bean;

import javax.persistence.*;

/**
 * create table swift(
 *  id int auto_increment primary key,
 *  nodeId int not null,
 *  nodeRole varchar(45),
 *  status int default 0,
 *  comment varchar(255)
 *  );
 * Created by root on 17-9-27.
 */
@Entity
@Table(name="swift")
public class SwiftBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;
    private Integer  nodeId;
    private String   nodeRole;
    private Integer  status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeRole() {
        return nodeRole;
    }

    public void setNodeRole(String nodeRole) {
        this.nodeRole = nodeRole;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String   comment;

}
