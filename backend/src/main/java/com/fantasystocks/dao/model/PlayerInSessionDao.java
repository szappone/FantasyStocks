package com.fantasystocks.dao.model;

import com.fantasystocks.entity.PlayerInSession;

import java.util.List;

public interface PlayerInSessionDao {
    public void add(PlayerInSession Pis);
    public List<Long> getSessionsForPlayer(String playerName);
}
