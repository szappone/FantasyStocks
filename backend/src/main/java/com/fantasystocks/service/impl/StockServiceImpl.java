package com.fantasystocks.service.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.dao.model.StockDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Stock;
import com.fantasystocks.service.model.PlayerService;
import com.fantasystocks.service.model.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDao stockDao;

    @Transactional
    @Override
    public void add(Stock stock) {
        stockDao.add(stock);
    }


    @Transactional
    @Override
    public void update(Stock stock) {
        stockDao.update(stock);
    }

    @Transactional
    @Override
    public Stock get(String ticker) {
        return stockDao.get(ticker);
    }

    @Transactional
    @Override
    public List<String> listStockIDs() {
        return stockDao.listStockIDs();
    }

}
