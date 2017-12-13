package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class GetPortfolioScoreResponse {
    private double totalScore;
    private HashMap<String, Double> scoreStorer = new HashMap<String, Double>();
}
