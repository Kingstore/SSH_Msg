package com.cs.qsx.service.impl;

import com.cs.qsx.dao.MsgDao;
import com.cs.qsx.domain.Msg;
import com.cs.qsx.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包名 com.cs.qsx.service.impl
 * 类名 MsgServiceImpl
 * 类描述 留言处理业务层接口实现类
 *
 * @author Lucifer
 * @version 1.0
 * 创建日期 2017/11/16
 * 时间 15:45
 */
@Service
public class MsgServiceImpl implements MsgService {

    private final MsgDao msgDao;

    @Autowired
    public MsgServiceImpl(MsgDao msgDao) {
        this.msgDao = msgDao;
    }

    @Override
    public List<Msg> findAll() {
        return msgDao.queryAll();
    }

    @Override
    public void addMsg(Msg msg) {
        msgDao.insertMsg(msg);
    }
}
