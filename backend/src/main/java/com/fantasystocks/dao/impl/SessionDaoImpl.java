package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.SessionDao;
import com.fantasystocks.entity.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SessionDaoImpl implements SessionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Session session) { sessionFactory.getCurrentSession().save(session);
    }

    @Override
    public Session get(long sessionID) { return sessionFactory.getCurrentSession().get(Session.class, sessionID);
    }

}
