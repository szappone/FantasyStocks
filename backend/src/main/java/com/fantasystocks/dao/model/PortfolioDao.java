package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;

public interface PortfolioDao {
    public void add(Portfolio portfolio);
    public Portfolio get(long portfolioID);
    public void addPortfolioToSession(long portfolioID, Player player, Game game);
}
