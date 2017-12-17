package com.fantasystocks.config;

import com.fantasystocks.entity.Stock;
import com.fantasystocks.service.model.StockService;
import com.fantasystocks.modules.priceCalculator;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.POST;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @PostConstruct
    public void initPrices() {
        List<String> tickers = stockService.listStockIDs();


        for (int i = 0; i < tickers.size(); i++){
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e){
            }
            Stock stock = stockService.get(tickers.get(i));
            stock.setLastMondayPrice(priceCalculator.getMonday(tickers.get(i)));
            stock.setTodayPrice(priceCalculator.getCurrentDay(tickers.get(i)));
            stockService.update(stock);
        }
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void updatePrices(){
        LocalDate date = LocalDate.now();
        DayOfWeek day = date.getDayOfWeek();
        String s = day.toString();


        List<String> tickers = stockService.listStockIDs();

        for (int i = 0; i < tickers.size(); i++){
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e){
            }
            Stock stock = stockService.get(tickers.get(i));
            if (s.equals("MONDAY")) {
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e){
                }
                stock.setLastMondayPrice(priceCalculator.getCurrentDay(tickers.get(i)));
            }
            stock.setTodayPrice(priceCalculator.getCurrentDay(tickers.get(i)));
            stockService.update(stock);
        }

    }

}
