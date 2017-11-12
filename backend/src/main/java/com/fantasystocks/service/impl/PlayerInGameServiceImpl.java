package com.fantasystocks.service.impl;

import com.fantasystocks.dao.model.PlayerInGameDao;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.service.model.PlayerInGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PlayerInGameServiceImpl implements PlayerInGameService {
    @Autowired
    private PlayerInGameDao playerInGameDao;

    @Transactional
    @Override
    public void add(PlayerInGame Pis) {
        playerInGameDao.add(Pis);
    }

    @Transactional(readOnly = true )
    @Override
    public List<Long> getAll(String playerName) {
        return playerInGameDao.getSessionsForPlayer(playerName);
    }
}
