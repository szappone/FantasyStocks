package com.fantasystocks.service.model;

import com.fantasystocks.entity.Game;

public interface GameService {
    void add(Game game);
    Game get(long sessionID);
}
