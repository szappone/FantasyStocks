package com.fantasystocks.dao.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.dao.model.StockDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class StockDaoImpl implements StockDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Stock stock) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(stock);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Stock stock) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(stock);
        tx.commit();
        session.close();
    }

    @Override
    public Stock get(String ticker) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        log.debug("Fetching stock with ticker: {}" + ticker);
        Stock stock = session.get(Stock.class, ticker);
        tx.commit();
        session.close();
        return stock;
    }

    @Override
    public List<String> listStockIDs() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        @SuppressWarnings("unchecked")
        TypedQuery<Stock> query = session.createQuery("from Stock");
        List<Stock> stocks = query.getResultList();
        List<String> stockIDs = new ArrayList<>();
        for (int i = 0; i < stocks.size(); i++){
            stockIDs.add(stocks.get(i).getTicker());
        }
        tx.commit();
        session.close();
        return stockIDs;
    }
}
