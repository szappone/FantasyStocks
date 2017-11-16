package com.fantasystocks.service.impl;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDao playerDao;

    @Transactional
    @Override
    public void add(Player player) {
        playerDao.add(player);
    }

    @Transactional
    @Override
    public void update(Player player) {
        playerDao.update(player);
    }

    @Transactional
    @Override
    public void addToSession(Player player, Game game) {
        playerDao.addToSession(player, game);
    }

    @Transactional
    @Override
    public Player get(String playerName) {
        return playerDao.get(playerName);
    }

    @Transactional(readOnly = true )
    @Override
    public List<Player> listPlayers() {
        return playerDao.listPlayers();
    }
}
