package cn.com.ecict.bean;

import javax.persistence.*;

/**
 * MariaDB [storage]> desc disks;
 *+------------+---------------+------+-----+---------+----------------+
 *| Field      | Type          | Null | Key | Default | Extra          |
 *+------------+---------------+------+-----+---------+----------------+
 *| diskId     | int(11)       | NO   | PRI | NULL    | auto_increment |
 *| discName   | varchar(100)  | NO   |     | NULL    |                |
 *| capacityGB | decimal(10,0) | YES  |     | 0       |                |
 *| status     | int(11)       | YES  |     | 0       |                |
 *| nodeId     | int(11)       | YES  |     | NULL    |                |
 *+------------+---------------+------+-----+---------+----------------+
 *
 * Created by root on 17-9-27.
 */
@Entity
@Table(name="disks")
public class DiskBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  diskId;
    private String   diskName;
    private Double   capacityGB;
    private Integer  status;
    private Integer  nodeId;

    public Integer getDiskId() {
        return diskId;
    }

    public void setDiskId(Integer diskId) {
        this.diskId = diskId;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public Double getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(Double capacityGB) {
        this.capacityGB = capacityGB;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

}
