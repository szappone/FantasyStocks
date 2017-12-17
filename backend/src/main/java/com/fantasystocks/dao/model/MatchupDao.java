package com.fantasystocks.dao.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Matchup;
import com.fantasystocks.entity.Stock;

import java.util.List;

public interface MatchupDao {
    void add(Matchup matchup);
    Matchup get(long matchupID);
    List<Long> listMatchupIDs(long gameID, long currentWeek);
    void createForSession(Game game, List<String> playerNames);
}
