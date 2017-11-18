package com.cs.qsx.filter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;

import javax.persistence.FlushModeType;
import javax.servlet.annotation.WebFilter;

/**
 * 包名 com.cs.qsx.filter
 * 类名 HibernateFlushFilter
 * 类描述 Servlet Filter implementation class HibernateFlushFilter 为了解决Spring二次修改Hibernate的FlushMode
 *
 * @author Lucifer
 * @version 1.0
 * 创建日期 2017/11/16
 * 时间 16:05
 */
@WebFilter("/*")
public class HibernateFlushFilter extends OpenSessionInViewFilter {
    @Override
    protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.setFlushMode(FlushModeType.AUTO);
        return session;
    }

}
