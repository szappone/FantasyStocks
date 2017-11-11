package com.fantasystocks.service.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.dao.model.PlayerInSessionDao;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInSession;
import com.fantasystocks.service.model.PlayerInSessionService;
import com.fantasystocks.service.model.PlayerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class PlayerInSessionServiceImpl implements PlayerInSessionService {
    @Autowired
    private PlayerInSessionDao playerInSessionDao;

    @Transactional
    @Override
    public void add(PlayerInSession Pis) {
        playerInSessionDao.add(Pis);
    }

    @Transactional(readOnly = true )
    @Override
    public List<Long> getAll(String playerName) {
        return playerInSessionDao.getAll(playerName);
    }
}
