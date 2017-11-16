package com.fantasystocks.controller.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class Session {
    private Long sessionId;
    private String sessionName;
    private List<String> players;
    private List<Long> matchupIds;
    private Map<String, Long> portfolios;
    private Map<String, Double> playerScores;
    //private List<List<Week>> scheduleTable;
}
