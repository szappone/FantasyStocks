package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;

import java.util.List;

public interface GameDao {
    Long add(Game game);
    void update(Game game);
    Game get(long sessionID);
    List<PlayerInGame> getAllPlayerInGame(long sessionID);
}
