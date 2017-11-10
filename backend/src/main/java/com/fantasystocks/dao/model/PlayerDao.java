package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Player;

import java.util.List;

public interface PlayerDao {
    public void add(Player player);
    public Player get(String username);
    public List<Player> listPlayers();
}
