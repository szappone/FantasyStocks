package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;

public interface PortfolioDao {
    void add(Portfolio portfolio);
    Portfolio get(long portfolioID);
    Portfolio get(String playerName, long gameID);
    void update(Portfolio portfolio);
    void addPortfolioToSession(long portfolioID, String playerId, long gameId);
}
