package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;

import java.util.List;

public interface GameDao {
    Long add(Game game);
    void update(Game game);
    Game get(long sessionID);
    public List<Player> getAllPlayers(long sessionID);
}
