package com.fantasystocks.modules;

import com.fantasystocks.entity.Matchup;
import java.util.*;

public class RobinRound {

    public static void main(String[] args) {

        String[] players = {"Shiv","AK","Archan","Sarah","Angelo","Scott","Passumarthi","Krishnan","Adam","P"};

        String[][][] matchups = RoundRobin(players);
        Matchup[] tournaments = createMatchupIDs(matchups);



    }
    public static String[][][] RoundRobin(String[] players) {
        List<String> p = Arrays.asList(players);
        //Randomize given array
        Collections.shuffle(p);
        players = p.toArray(players);
        int users = players.length;
        if (users%2 != 0) {
            System.out.println("Not Valid");
        }

        int no_of_weeks = players.length - 1;
        //3d Array to store matchups.
        String[][][] matchup = new String[no_of_weeks][players.length/2][2];
        /*
        function creates a deterministic schedule based on array index but randomized based on players since the player
        array is randomized
        */
        for (int i = 0; i < no_of_weeks; i++) {
            for (int j = 0; j < players.length/2; j++) {
                int p1 = (i + j) % (players.length - 1);
                int p2 = (players.length - 1 - j + i) % (players.length - 1);


                if (j == 0) {
                    p2 = players.length - 1;
                }

                matchup[i][j][0] = players[p1];
                matchup[i][j][1] = players[p2];

            }
        }
        return matchup;
    }
    public static Matchup[] createMatchupIDs (String[][][] players){
        Matchup[] tournament = new Matchup[45];
        int k = 0;
        for (int i = 0; i < players.length; i ++) {
            for (int j = 0; j < players[i].length; j++) {
                Matchup m = Matchup.builder()
                        .player1Name(players[i][j][0])
                        .player2Name(players[i][j][1])
                        .activeWeek(i+1)
                        .build();
                tournament[k] = m;
                k++;
            }

        }
        return tournament;
    }
}
