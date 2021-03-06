package cn.com.ecict.bean;

import java.util.Date;
import javax.persistence.*;
/**
 * create table users(
 *  uid int auto_increment primary key,
 *  username varchar(45) not null,
 *  password varchar(100),
 *  email varchar(45),
 *  usertype int default 0,
 *  lastlog timestamp default CURRENT_TIMESTAMP
 * );
 * Created by root on 17-9-26.
 */
@Entity
@Table(name="users")
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  uid;
    //该字段不更新
    @Column(updatable=false)
    private String   username;
    private String   password;
    private String   email;
    private Integer  usertype;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastlog")
    private Date     lastlog;
    private Integer  status;

    public UserBean(){}

    public UserBean(Integer uid, String username, String password, String email, Integer usertype, Date lastlog) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.usertype = usertype;
        this.lastlog = lastlog;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", usertype=" + usertype +
                ", lastlog=" + lastlog +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Date getLastlog() {
        return lastlog;
    }

    public void setLastlog(Date lastlog) {
        this.lastlog = lastlog;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
