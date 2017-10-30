package com.fantasystocks.service.impl;

import com.fantasystocks.dao.impl.PlayerDaoImpl;
import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDao playerDao;

    @Transactional
    @Override
    public void add(Player player) {
        playerDao.add(player);
    }

    @Transactional(readOnly = true )
    @Override
    public List<Player> listPlayers() {
        return playerDao.listPlayers();
    }
}
