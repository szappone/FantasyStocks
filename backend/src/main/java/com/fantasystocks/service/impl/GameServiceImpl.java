package com.fantasystocks.service.impl;

import com.fantasystocks.dao.model.GameDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
    @Autowired
    private GameDao sessionDao;

    @Transactional
    @Override
    public Long add(Game game) {
        return sessionDao.add(game);
    }

    @Transactional
    @Override
    public void update(Game game) {
        sessionDao.update(game);
    }

    @Transactional
    @Override
    public Game get(long sessionID) {
        return sessionDao.get(sessionID);
    }

}
