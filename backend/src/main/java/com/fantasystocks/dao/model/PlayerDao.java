package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;

import java.util.List;

public interface PlayerDao {
    void add(Player player);
    void update(Player player);
    void addToSession(Player player, Game game);
    Player get(String playerName);
    List<Player> listPlayers();
}
