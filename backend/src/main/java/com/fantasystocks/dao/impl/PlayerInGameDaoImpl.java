package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerInGameDao;
import com.fantasystocks.entity.PlayerInGame;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PlayerInGameDaoImpl implements PlayerInGameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(PlayerInGame Pis) {
        sessionFactory.getCurrentSession().save(Pis);
    }

    @Override
    public List<Long> getSessionsForPlayer(String playerName) {
        @SuppressWarnings("unchecked")
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT P.sessionID FROM PlayerInGame P where P.playerName = :playerName");
        query.setParameter("playerName", playerName);
        return (List<Long>)query.getResultList();
    }

}
