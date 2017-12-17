package com.fantasystocks.controller.api;

import com.fantasystocks.entity.Game;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Map;

@Data
@Builder
public class GetMatchupResponse {
    private long matchupId;
    private long gameID;
    private String player1Name;
    private String player2Name;
    private Map<String, Double> p1Score;
    private Map<String, Double> p2Score;
    private long activeWeek;
}
