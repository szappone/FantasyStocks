package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.dao.model.PortfolioDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.entity.Portfolio;
import com.sun.xml.internal.ws.wsdl.writer.document.Port;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
    public Portfolio get(long portfolioID) {
        Session session = sessionFactory.openSession();
        Portfolio p = session.get(Portfolio.class, portfolioID);
        session.close();

        return p;
    }

    @Override
    public void addPortfolioToSession(long portfolioID, Player player, Game game) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from PlayerInGame " +
                "where player_id = :pid and game_id = :gid");
        query.setParameter("pid", player.getPlayerName());
        query.setParameter("gid", game.getGameId());
        PlayerInGame playerInGame = (PlayerInGame) query.getSingleResult();
        Portfolio portfolio = session.get(Portfolio.class, portfolioID);
        playerInGame.setPortfolio(portfolio);
        session.save(playerInGame);
        tx.commit();
        session.close();
    }
}
