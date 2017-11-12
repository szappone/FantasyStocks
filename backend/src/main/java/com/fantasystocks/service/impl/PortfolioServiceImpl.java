package com.fantasystocks.service.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.dao.model.PortfolioDao;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.model.PlayerService;
import com.fantasystocks.service.model.PortfolioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioDao portfolioDao;

    @Transactional
    @Override
    public void add(Portfolio portfolio) {
        portfolioDao.add(portfolio);
    }

    @Transactional
    @Override
    public Portfolio get(long portfolioID) {
        return portfolioDao.get(portfolioID);
    }

}
