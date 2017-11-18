package com.cs.qsx.service;

import com.cs.qsx.domain.Msg;

import java.util.List;

/**
 * 包名 com.cs.qsx.service
 * 类名 MsgService
 * 类描述 留言处理业务层接口
 *
 * @author Lucifer
 * @version 1.0
 * 创建日期 2017/11/16
 * 时间 15:45
 */
public interface MsgService {

    /**
     * 查询所有留言
     *
     * @return 留言集合
     */
    List<Msg> findAll();

    /**
     * 新增一条留言
     *
     * @param msg 新增的留言对象
     */
    void addMsg(Msg msg);
}
