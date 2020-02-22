package ssm.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.blog.dao.SysLogDao;
import ssm.blog.entity.SysLog;
import ssm.blog.service.SysLogService;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/4 17:50
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogDao sysLogDao;

    @Override
    public void insertSysLog(SysLog sysLog) {
        sysLogDao.insertSysLog(sysLog);
    }
}
