package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetPortfolioResponse {
    private long portfolioID;
    private List<String> longs;
    private List<String> shorts;
    private List<String> bench;
}
