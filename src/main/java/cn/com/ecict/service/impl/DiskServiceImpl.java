package cn.com.ecict.service.impl;

import cn.com.ecict.bean.DiskBean;
import cn.com.ecict.dao.IDiskDao;
import cn.com.ecict.service.IDiskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by root on 17-9-27.
 */
@Service("diskService")
@Transactional(rollbackFor = Exception.class)
public class DiskServiceImpl extends BaseServiceImpl<DiskBean> implements IDiskService{
    @Resource(name="diskDao")
    private IDiskDao diskDao;

    @Override
    public IDiskDao getDao() {
        return diskDao;
    }

    @Override
    public DiskBean get(Integer id){
        return getDao().get(DiskBean.class, id);
    }
}
