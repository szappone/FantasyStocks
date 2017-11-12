package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.dao.model.PortfolioDao;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PortfolioDaoImpl implements PortfolioDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Portfolio portfolio) {
        sessionFactory.getCurrentSession().save(portfolio);
    }

    @Override
    public Portfolio get(long portfolioID) {
        return (sessionFactory.getCurrentSession().get(Portfolio.class, portfolioID));
    }

}
