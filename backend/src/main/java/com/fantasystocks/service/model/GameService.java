package com.fantasystocks.service.model;

import com.fantasystocks.controller.api.Session;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;

import java.util.List;

public interface GameService {
    Long add(Game game);
    void update(Game game);
    Game get(long sessionID);
    List<Session> getAllSessions(String playerName);
    List<Player> getAllPlayers(long gameid);
}
