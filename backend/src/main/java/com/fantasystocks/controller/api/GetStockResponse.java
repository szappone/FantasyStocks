package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GetStockResponse {
    private String stockID;
    private double lastMondayPrice;
    private double todayPrice;
}
