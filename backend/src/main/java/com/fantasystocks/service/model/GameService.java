package com.fantasystocks.service.model;

import com.fantasystocks.entity.Game;

public interface GameService {
    Long add(Game game);
    void update(Game game);
    Game get(long sessionID);
}
