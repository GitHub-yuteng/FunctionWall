package com.functionwall.service.impl;

import com.functionwall.dao.LogMapper;
import com.functionwall.exception.FunctionWallErrorCode;
import com.functionwall.exception.FunctionWallRuntimeException;
import com.functionwall.pojo.model.LogDomain;
import com.functionwall.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请求日志
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logDao;

    @Override
    public void addLog(String action, String data, String ip, Integer authorId) {
        LogDomain logDomain = new LogDomain();
        logDomain.setAuthorId(authorId);
        logDomain.setIp(ip);
        logDomain.setData(data);
        logDomain.setAction(action);
        logDao.addLog(logDomain);
    }

    @Override
    public void deleteLogById(Integer id) {
        if (null == id) {
            throw new FunctionWallRuntimeException(FunctionWallErrorCode.LOG_ID_IS_NULL);
        }
        deleteLogById(id);
    }

    @Override
    public PageInfo<LogDomain> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LogDomain> logs = logDao.getLogs();
        PageInfo<LogDomain> pageInfo = new PageInfo<>(logs);
        return pageInfo;
    }
}
