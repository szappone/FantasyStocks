package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.dao.model.PlayerInSessionDao;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInSession;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PlayerInSessionDaoImpl implements PlayerInSessionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(PlayerInSession Pis) {
        sessionFactory.getCurrentSession().save(Pis);
    }

    @Override
    public List<Long> getAll(String playerName) {
        @SuppressWarnings("unchecked")
        Query query = sessionFactory.getCurrentSession().createQuery("select sessionID from PlayerInSession where playerName = " + playerName);
        return (List<Long>)query.getResultList();
    }

}
