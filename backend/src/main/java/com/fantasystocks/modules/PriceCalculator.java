package com.fantasystocks.modules;

import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.entity.Stock;
import com.fantasystocks.service.model.StockService;
import com.jimmoores.quandl.*;
import com.jimmoores.quandl.classic.ClassicQuandlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.HashMap;
import java.util.Map;

@Component
public class PriceCalculator {
    @Autowired
    private StockService stockService;


    public static void main(String[] args) {

       /* HashMap<String, String> x = new HashMap<String, String>();
        x.put("AAPL","Long");
        x.put("AMZN","Long");
        x.put("GM","Long");
        x.put("TRIP","Short");
        x.put("AMT","Short");
        x.put("LUK","Short");
        //System.out.println(getMonday("AAPL"));
        HashMap<String, Double> scorer = score(x);
        for (String key : scorer.keySet()){
            System.out.println("For stock " + key + ", value is " + scorer.get(key) + "%");
        }*/


    }

    public static double getCurrentDay(String ticker) {
        ClassicQuandlSession session = ClassicQuandlSession.create(SessionOptions.Builder.withAuthToken("MscxWmUUSFZ-D_xjYiuP").build());;
        TabularResult tabularResult = session.getDataSet(
                DataSetRequest.Builder
                        .of("WIKI/"+ticker)
                        .withFrequency(Frequency.DAILY)
                        .withMaxRows(5)
                        .withColumn(4)
                        .build());
        Row row = tabularResult.get(0);
        Double value = row.getDouble(1);
        return value;
    }
    public static double getMonday(String ticker) {
        ClassicQuandlSession session = ClassicQuandlSession.create(SessionOptions.Builder.withAuthToken("MscxWmUUSFZ-D_xjYiuP").build());;
        TabularResult tabularResult = session.getDataSet(
                DataSetRequest.Builder
                        .of("WIKI/"+ticker)
                        .withFrequency(Frequency.DAILY)
                        .withMaxRows(5)
                        .withColumn(1)
                        .build());
        int find_monday;
        for(find_monday = 0; find_monday < 5; find_monday++) {
            Row row = tabularResult.get(find_monday);
            LocalDate date = row.getLocalDate("Date");
            DayOfWeek dow = date.getDayOfWeek();
            if (dow.toString().equalsIgnoreCase("Monday")) {
                break;
            }
        }
        Row row2 = tabularResult.get(find_monday);
        Double value = row2.getDouble(1);
        //System.out.println(tabularResult.toPrettyPrintedString());
        return value;
    }

    public Map<String, Map<String, Double>> score (Map<String, Map<String, Double>> input) {
        double sum = 0;
        for (String stockType : input.keySet()) {
            Map<String, Double> type = input.get(stockType);
            for (String key : type.keySet()) {
                Stock stock = stockService.get(key);
                Double value = stock.getTodayPrice();
                Double value2 = stock.getLastMondayPrice();
                if (stockType.equalsIgnoreCase("longs")) {
                    sum = sum + (value / value2 - 1) * 100;
                    type.put(key, (value / value2 - 1) * 100);
                } else {
                    sum = sum + (value2 / value - 1) * 100;
                    type.put(key, (value2 / value - 1) * 100);
                }
            }
            input.put(stockType, type);
        }
        Map<String, Double> total = input.get("total");
        total.put("total", sum/6);
        input.put("total", total);
        return input;
    }
    public Map<String, Map<String, Double>> PortfolioScores(Portfolio p) {
        Map<String, Map<String, Double>> x = new HashMap<String, Map<String, Double>>();
        Map<String, Double> longs = new HashMap<>();
        Map<String, Double> shorts = new HashMap<>();
        for (String ss : p.getShorts()) {
            shorts.put(ss, 0.0);
        }
        for (String ss : p.getLongs()) {
            longs.put(ss, 0.0);
        }
        x.put("shorts", shorts);
        x.put("longs", longs);
        x.put("total", new HashMap<>());
        Map<String, Map<String, Double>> y = score(x);
        return y;

    }
}
