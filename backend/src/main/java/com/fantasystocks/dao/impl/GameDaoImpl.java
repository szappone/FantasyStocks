package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.GameDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PlayerInGame> getAllPlayerInGame(long sessionID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        @SuppressWarnings("unchecked")
        javax.persistence.Query query = session.createQuery(
                "from PlayerInGame p " +
                        "join fetch p.player " +
                        "join fetch p.game " +
                        "join fetch p.portfolio " +
                        "where p.game.gameId = :gid");
        query.setParameter("gid", sessionID);

        @SuppressWarnings("unchecked")
        List<PlayerInGame> playerInGames = Collections.checkedList(query.getResultList(), PlayerInGame.class);
        tx.commit();
        session.close();
        return playerInGames;
    }
}
