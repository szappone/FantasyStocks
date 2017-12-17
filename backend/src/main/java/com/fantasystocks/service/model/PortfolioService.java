package com.fantasystocks.service.model;

import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;

import javax.sound.sampled.Port;
import java.util.List;

public interface PortfolioService {
    void add(Portfolio portfolio);
    void update(Portfolio portfolio);
    Portfolio get(long portfolioID);
    Portfolio get(String playerName, long gameID);
}
