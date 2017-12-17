package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.dao.model.PortfolioDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.entity.Portfolio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

@Repository
public class PortfolioDaoImpl implements PortfolioDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Portfolio portfolio) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(portfolio);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Portfolio portfolio) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(portfolio);
        tx.commit();
        session.close();
    }

    @Override
    public Portfolio get(long portfolioID) {
        Session session = sessionFactory.openSession();
        Portfolio p = session.get(Portfolio.class, portfolioID);
        session.close();
        return p;
    }

    @Override
    public Portfolio get(String playerName, long gameID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        @SuppressWarnings("unchecked")
        javax.persistence.Query query = session.createQuery(
                "from PlayerInGame p " +
                        "join fetch p.portfolio " +
                        "where p.player.playerName = :pn " +
                        "and p.game.gameId = :gid");
        query.setParameter("pn", playerName);
        query.setParameter("gid", gameID);

        @SuppressWarnings("unchecked")
        PlayerInGame pig = (PlayerInGame)query.getSingleResult();
        Portfolio p = pig.getPortfolio();
        tx.commit();
        session.close();
        return p;
    }

    @Override
    public void addPortfolioToSession(long portfolioID, String playerId, long gameId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from PlayerInGame " +
                "where player_id = :pid and game_id = :gid");
        query.setParameter("pid", playerId);
        query.setParameter("gid", gameId);
        PlayerInGame playerInGame = (PlayerInGame) query.getSingleResult();
        Portfolio portfolio = session.get(Portfolio.class, portfolioID);
        playerInGame.setPortfolio(portfolio);
        session.save(playerInGame);
        tx.commit();
        session.close();
    }
}
