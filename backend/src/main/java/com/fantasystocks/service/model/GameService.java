package com.fantasystocks.service.model;

import com.fantasystocks.controller.api.Session;
import com.fantasystocks.entity.Game;

import java.util.List;

public interface GameService {
    Long add(Game game);
    void update(Game game);
    Game get(long sessionID);
    List<Session> getAllSessions(String playerName);
}
