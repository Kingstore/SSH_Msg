package com.cs.qsx.dao;

import com.cs.qsx.domain.Msg;

import java.util.List;

/**
 * 包名 com.cs.qsx.dao
 * 类名 MsgDao
 * 类描述 留言持久层操作接口
 *
 * @author Lucifer
 * @version 1.0
 * 创建日期 2017/11/16
 * 时间 15:43
 */
public interface MsgDao {

    /**
     * 查询所有留言
     *
     * @return 留言集合
     */
    List<Msg> queryAll();


    /**
     * 新增一条留言
     *
     * @param msg 要插入的留言对象
     */
    void insertMsg(Msg msg);
}
