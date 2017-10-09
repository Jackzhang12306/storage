package cn.com.ecict.dao;

import cn.com.ecict.bean.DiskBean;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 17-9-27.
 */
@Repository
public interface IDiskDao extends IBaseDao<DiskBean>{
    /**
     * 添加磁盘
     * @param disk
     * @return
     */
    boolean add(DiskBean disk);
}
