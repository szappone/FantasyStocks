package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Stock;

import java.util.List;

public interface StockDao {
    void add(Stock stock);
    void update(Stock stock);
    Stock get(String ticker);
    List<String> listStockIDs();
}
