package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerInGameDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.PlayerInGame;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class PlayerInGameDaoImpl implements PlayerInGameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(PlayerInGame Pis) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(Pis);

        tx.commit();
        session.close();
    }

    @Override
    public void remove(PlayerInGame pis) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.delete(pis);

        tx.commit();
        session.close();
    }

    @Override
    public List<PlayerInGame> getGamesForPlayer(String playerName) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        @SuppressWarnings("unchecked")
        Query query = session.createQuery("from PlayerInGame p where p.player.playerName = :pn");
        query.setParameter("pn", playerName);
        @SuppressWarnings("unchecked")
        List<PlayerInGame> playerInGames = Collections.checkedList(query.getResultList(), PlayerInGame.class);

        playerInGames.stream().forEach(playerInGame -> {
            playerInGame.getGame();
            playerInGame.getPlayer();
        });
        tx.commit();
        session.close();
        return playerInGames;
    }
}
