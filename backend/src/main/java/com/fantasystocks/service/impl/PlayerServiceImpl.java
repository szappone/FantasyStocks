package com.fantasystocks.service.impl;

import com.fantasystocks.dao.impl.PlayerDaoImpl;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDaoImpl playerDaoImpl;

    @Transactional
    @Override
    public void add(Player player) {
        playerDaoImpl.add(player);
    }

    @Transactional(readOnly = true )
    @Override
    public List<Player> listPlayers() {
        return playerDaoImpl.listPlayers();
    }
}
