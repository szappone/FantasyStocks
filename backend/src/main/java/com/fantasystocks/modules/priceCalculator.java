package com.fantasystocks.modules;

import com.jimmoores.quandl.*;
import com.jimmoores.quandl.classic.ClassicQuandlSession;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.Date;
import java.util.HashMap;


public class priceCalculator {
    public static void main(String[] args) {

        HashMap<String, String> x = new HashMap<String, String>();
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
        }

    }

    public static double getCurrentDay(String ticker) {
        ClassicQuandlSession session = ClassicQuandlSession.create();
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
        ClassicQuandlSession session = ClassicQuandlSession.create();
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

    public static HashMap<String, Double> score (HashMap<String, String> input) {
        HashMap<String, Double> scorer = new HashMap<String, Double>();
        ClassicQuandlSession session = ClassicQuandlSession.create();
        double sum = 0;
        for (String key : input.keySet()){
            Double value = getCurrentDay(key);
            Double value2 = getMonday(key);
            if (input.get(key).equalsIgnoreCase("Long")) {
                sum = sum + (value/value2 - 1)*100;
                scorer.put(key,(value/value2 - 1)*100);
            }
            else {
                sum = sum + (value2/value - 1)*100;
                scorer.put(key,(value2/value - 1)*100);
            }
            scorer.put("Total", sum/6);

        }
        return scorer;
    }
}
