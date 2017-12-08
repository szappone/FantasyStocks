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
                .companyName("Amazon.com, Inc.")
                .ticker("AMZN")
                .build());
        stockService.add(Stock.builder()
                .companyName("Apple Inc.")
                .ticker("AAPL")
                .build());
        stockService.add(Stock.builder()
                .companyName("General Motors Company")
                .ticker("GM")
                .build());
        stockService.add(Stock.builder()
                .companyName("TripAdvisor, Inc.")
                .ticker("TRIP")
                .build());
        stockService.add(Stock.builder()
                .companyName("American Tower Corporation")
                .ticker("AMT")
                .build());
        stockService.add(Stock.builder()
                .companyName("Leucadia National Corporation")
                .ticker("LUK")
                .build());
        stockService.add(Stock.builder()
                .companyName("Alphabet Inc.")
                .ticker("GOOG")
                .build());
        stockService.add(Stock.builder()
                .companyName("International Business Machines Corporation")
                .ticker("IBM")
                .build());
        stockService.add(Stock.builder()
                .companyName("Western Digital Corporation")
                .ticker("WDC")
                .build());
        stockService.add(Stock.builder()
                .companyName("Citigroup Inc.")
                .ticker("C")
                .build());
    }

}
