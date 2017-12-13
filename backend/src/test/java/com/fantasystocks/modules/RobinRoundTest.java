package com.fantasystocks.modules;

import com.fantasystocks.entity.Matchup;
import org.junit.Test;

import static com.fantasystocks.modules.RobinRound.RoundRobin;
import static com.fantasystocks.modules.RobinRound.createMatchupIDs;
import static org.junit.Assert.assertNotNull;

public class RobinRoundTest {
    @Test
    public void RobinRoundTester() {
        String[] players = {"Shiv","AK","Archan","Sarah","Angelo","Scott","Passumarthi","Krishnan","Adam","P"};
        String[][][] matchups = RoundRobin(players);
        assertNotNull(matchups);
        Matchup[] tournaments = createMatchupIDs(matchups);
        assertNotNull(matchups);
    }
}
