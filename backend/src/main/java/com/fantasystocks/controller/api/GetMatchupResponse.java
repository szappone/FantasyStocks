package com.fantasystocks.controller.api;

import com.fantasystocks.entity.Game;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@Builder
public class GetMatchupResponse {
    private long matchupId;
    private long gameID;
    private String player1Name;
    private String player2Name;
    private long p1Score;
    private long p2Score;
    private long activeWeek;
}
