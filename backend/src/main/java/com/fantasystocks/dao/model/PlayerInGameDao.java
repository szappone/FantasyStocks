package com.fantasystocks.dao.model;

import com.fantasystocks.entity.PlayerInGame;

import java.util.List;

public interface PlayerInGameDao {
    public void add(PlayerInGame playerInGame);
    public List<Long> getSessionsForPlayer(String playerName);
}
