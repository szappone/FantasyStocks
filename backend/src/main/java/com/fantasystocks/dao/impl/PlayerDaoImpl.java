package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
public class PlayerDaoImpl implements PlayerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Player player) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(player);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Player player) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(player);
        tx.commit();
        session.close();
    }

    @Override
    public void addToSession(Player player, Game game) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Player p = session.get(Player.class, player.getPlayerName());
        Game g = session.get(Game.class, game.getGameId());
        p.addSession(g);
        session.save(p);
        tx.commit();
        session.close();
    }

    @Override
    public Player get(String playerName) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        log.debug("Fetching player with username: {}" + playerName);
        Player player = session.get(Player.class, playerName);
        tx.commit();
        session.close();

        return player;
    }

    @Override
    public List<Player> listPlayers() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        @SuppressWarnings("unchecked")
        TypedQuery<Player> query = session.createQuery("from Player");
        List<Player> players = query.getResultList();
        tx.commit();
        session.close();
        return players;
    }
}
