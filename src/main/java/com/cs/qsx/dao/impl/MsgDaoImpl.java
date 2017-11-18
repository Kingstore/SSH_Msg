package com.cs.qsx.dao.impl;

import com.cs.qsx.dao.MsgDao;
import com.cs.qsx.domain.Msg;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 包名 com.cs.qsx.dao.impl
 * 类名 MsgDaoImpl
 * 类描述 留言数据持久层操作接口实现类
 *
 * @author Lucifer
 * @version 1.0
 * 创建日期 2017/11/16
 * 时间 15:42
 */
@Repository("msgDao")
public class MsgDaoImpl extends HibernateDaoSupport implements MsgDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public MsgDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    protected void injectSessionFactory() {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Msg> queryAll() {
        return (List<Msg>) getHibernateTemplate().find("from Msg");
    }

    @Override
    public void insertMsg(Msg msg) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        msg.setCreateDate(date);
        getHibernateTemplate().save(msg);
    }
}
