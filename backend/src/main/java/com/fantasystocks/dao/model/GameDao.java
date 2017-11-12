package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;

public interface GameDao {
    public void add(Game game);
    public Game get(long sessionID);
}
