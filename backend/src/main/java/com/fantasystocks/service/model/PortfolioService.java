package com.fantasystocks.service.model;

import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;

import java.util.List;

public interface PortfolioService {
    void add(Portfolio portfolio);
    Portfolio get(long portfolioID);
}
