package com.fantasystocks.config;

import com.fantasystocks.entity.Stock;
import com.fantasystocks.service.model.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StockInitializer {
    @Autowired
    private StockService stockService;

    @PostConstruct
    public void init() {
        stockService.add(Stock.builder()
                .companyName("Amazon")
                .ticker("AMZN")
                .build());
    }

}
