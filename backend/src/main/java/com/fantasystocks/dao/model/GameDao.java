package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;

public interface GameDao {
    Long add(Game game);
    void update(Game game);
    Game get(long sessionID);
}
