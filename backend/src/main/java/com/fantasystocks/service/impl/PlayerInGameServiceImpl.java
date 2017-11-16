package com.fantasystocks.service.impl;

import com.fantasystocks.controller.api.Session;
import com.fantasystocks.dao.model.GameDao;
import com.fantasystocks.dao.model.PlayerInGameDao;
import com.fantasystocks.dao.model.PortfolioDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.model.PlayerInGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    @Override
    public void remove(PlayerInGame Pis) {
        playerInGameDao.remove(Pis);
    }
}
