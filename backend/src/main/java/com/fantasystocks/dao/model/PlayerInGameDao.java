package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.PlayerInGame;

import java.util.List;

public interface PlayerInGameDao {
    void add(PlayerInGame playerInGame);
    void remove(PlayerInGame playerInGame);
    List<PlayerInGame> getGamesForPlayer(String playerName);
}
