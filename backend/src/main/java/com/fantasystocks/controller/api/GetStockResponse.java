package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GetStockResponse {
    private String stockID;
    private String company_name;
    private double lastMondayPrice;
    private double todayPrice;
}
