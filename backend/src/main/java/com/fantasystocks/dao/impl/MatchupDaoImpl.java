package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.MatchupDao;
import com.fantasystocks.dao.model.PortfolioDao;
import com.fantasystocks.entity.Matchup;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.entity.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class MatchupDaoImpl implements MatchupDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Matchup matchup) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(matchup);
        tx.commit();
        session.close();
    }


    @Override
    public Matchup get(long matchupID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Matchup matchup = session.get(Matchup.class, matchupID);
        tx.commit();
        session.close();
        return matchup;
    }

    @Override
    public List<Long> listMatchupIDs(long gameID, long currentWeek) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        @SuppressWarnings("unchecked")
        javax.persistence.Query query = session.createQuery(
                "from Matchup m " +
                        "where m.game.gameId = :gid " +
                        "and activeWeek = :week");
        query.setParameter("gid", gameID);
        query.setParameter("week", currentWeek);

        @SuppressWarnings("unchecked")
        List<Matchup> matchups = Collections.checkedList(query.getResultList(), Matchup.class);

        List<Long> mids = new ArrayList<>();
        for (int i = 0; i < matchups.size(); i++){
            mids.add(matchups.get(i).getMatchupId());
        }
        tx.commit();
        session.close();
        return mids;
    }

}
