package com.fantasystocks.service.model;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Matchup;

import java.util.List;

public interface MatchupService {
    void add(Matchup matchup);
    Matchup get(long matchupID);
    List<Long> listMatchupIDs(long gameID, long currentWeek);
    void createForSession(Game game, List<String> playerNames);
}
