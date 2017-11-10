package com.fantasystocks.service.model;

import com.fantasystocks.entity.Player;

import java.util.List;

public interface PlayerService {
    void add (Player player);
    Player get(String playerName);
    List<Player> listPlayers();
}
