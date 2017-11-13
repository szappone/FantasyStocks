package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.GameDao;
import com.fantasystocks.entity.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Game game) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Long gameId = (Long) session.save(game);
        tx.commit();
        session.close();
        return gameId;
    }

    @Override
    public void update(Game game) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(game);
        tx.commit();
        session.close();
    }

    @Override
    public Game get(long sessionID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Game game = session.get(Game.class, sessionID);
        tx.commit();
        session.close();
        return game;
    }

}
