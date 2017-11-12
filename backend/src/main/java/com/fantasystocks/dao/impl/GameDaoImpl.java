package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.GameDao;
import com.fantasystocks.entity.Game;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Game game) { sessionFactory.getCurrentSession().save(game);
    }

    @Override
    public Game get(long sessionID) { return sessionFactory.getCurrentSession().get(Game.class, sessionID);
    }

}
