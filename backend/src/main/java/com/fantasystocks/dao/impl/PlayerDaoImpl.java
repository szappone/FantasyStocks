package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.entity.Player;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Player player) {
        sessionFactory.getCurrentSession().save(player);
    }

    @Override
    public Player getPlayer(String username) {
        return null;
    }

    @Override
    public List<Player> listPlayers() {
        @SuppressWarnings("unchecked")
        TypedQuery<Player> query = sessionFactory.getCurrentSession().createQuery("from Player");
        return query.getResultList();
    }

}
