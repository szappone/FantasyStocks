package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;

import java.util.List;

public interface PortfolioDao {
    public void add(Portfolio portfolio);
    public Portfolio get(long portfolioID);
}
