package com.fantasystocks.service.impl;

import com.fantasystocks.dao.model.MatchupDao;
import com.fantasystocks.dao.model.StockDao;
import com.fantasystocks.entity.Matchup;
import com.fantasystocks.entity.Stock;
import com.fantasystocks.service.model.MatchupService;
import com.fantasystocks.service.model.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class MatchupServiceImpl implements MatchupService {
    @Autowired
    private MatchupDao matchupDao;

    @Transactional
    @Override
    public void add(Matchup matchup){
        matchupDao.add(matchup);
    }

    @Transactional
    @Override
    public Matchup get(long matchupID){
        return matchupDao.get(matchupID);
    }

    @Transactional
    @Override
    public List<Long> listMatchupIDs(long gameID){
        return matchupDao.listMatchupIDs(gameID);
    }

}
