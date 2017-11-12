package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
public class PlayerDaoImpl implements PlayerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Player player) {
        Session session = sessionFactory.getCurrentSession();
        session.save(player);
    }

    @Override
    public Player get(String playerName) {
        Session session = sessionFactory.getCurrentSession();
        log.debug("Fetching player with username: {}" + playerName);
        Player player = session.get(Player.class, playerName);
        return player;
    }

    @Override
    public List<Player> listPlayers() {
        @SuppressWarnings("unchecked")
        TypedQuery<Player> query = sessionFactory.getCurrentSession().createQuery("from Player");
        return query.getResultList();
    }
}
