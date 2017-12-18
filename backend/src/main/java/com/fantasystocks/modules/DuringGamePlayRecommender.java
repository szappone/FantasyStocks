package com.fantasystocks.modules;

import com.fantasystocks.entity.Portfolio;
import com.google.common.collect.Lists;
import com.jimmoores.quandl.*;
import com.jimmoores.quandl.classic.ClassicQuandlSession;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuringGamePlayRecommender {

    //for testing during presentation if required
    /*public static void main(String[] args) {
        Double[] x = recommend("AAPL");
        List<String> l = Lists.newArrayList("IBM","WDC","C");
        List<String> s = Lists.newArrayList("TRIP","AMT","LUK");
        List<String> b = Lists.newArrayList("AAPL","AMZN","GM");
        Portfolio portfolios = Portfolio.builder()
                .bench(b)
                .longs(l)
                .shorts(s)
                .build();
        Portfolio p = sendRecs(portfolios);
        for (String ss : p.getShorts()) {
            System.out.println(ss);
        }
        for (String ss : p.getLongs()) {
            System.out.println(ss);
        }
        for (String ss : p.getBench()) {
            System.out.println(ss);
        }

    }*/

    public static Double simpleMovingAverage(TabularResult tabularResult, int endDay, int startDay) {
        Double sum = 0.0;
        for (int i = startDay; i < endDay; i++) {
            Row row = tabularResult.get(i);
            Double value = row.getDouble(1);
            sum += value;
        }
        return sum / endDay;
    }

    public static Double[] recommend(String ticker) {
        ClassicQuandlSession session = ClassicQuandlSession.create(SessionOptions.Builder.withAuthToken("MscxWmUUSFZ-D_xjYiuP").build());;
        TabularResult tabularResult = session.getDataSet(
                DataSetRequest.Builder
                        .of("WIKI/" + ticker)
                        .withFrequency(Frequency.DAILY)
                        .withMaxRows(102)
                        .withColumn(4)
                        .build());
        Double Day50 = simpleMovingAverage(tabularResult, 50, 0);
        Double Day15 = simpleMovingAverage(tabularResult,15, 0);
        Double postDG25 = simpleMovingAverage(tabularResult, 25, 0);
        Double postDG100 = simpleMovingAverage(tabularResult, 100, 0);
        Double preDG25 = simpleMovingAverage(tabularResult, 26, 1);
        Double preDG100 = simpleMovingAverage(tabularResult, 101, 1);
        Double[] x = new Double[2];

        //GoldenCross
        if (preDG25 < preDG100 && postDG25 > postDG100) {
            x[0] = 1.0;
        }
        //DeathCross
        else if (preDG25 > preDG100 && postDG25 < postDG100) {
            x[0] = -1.0;
        } else {
            x[0] = 0.0;
        }
        x[1] = (Day15 / Day50 - 1) * 100;
        //System.out.println(x[0]);
        return x;
    }

    public static Portfolio sendRecs(Portfolio p) {
        Double[][] calcs = new Double[p.getShorts().size() + p.getLongs().size() + p.getBench().size()][3];
        HashMap<Double,String> indexer = new HashMap<>();
        int ctr = 0;
        for (String ss : p.getShorts()) {
            Double[] d = recommend(ss);
            calcs[ctr][0] = d[0];
            calcs[ctr][1] = d[1];
            calcs[ctr][2] = (double)ctr;
            indexer.put((double)ctr,ss);
            ctr++;
        }
        for (String ss : p.getLongs()) {
            Double[] d = recommend(ss);
            calcs[ctr][0] = d[0];
            calcs[ctr][1] = d[1];
            calcs[ctr][2] = (double)ctr;
            indexer.put((double)ctr,ss);
            ctr++;
        }
        for (String ss : p.getBench()) {
            Double[] d = recommend(ss);
            calcs[ctr][0] = d[0];
            calcs[ctr][1] = d[1];
            calcs[ctr][2] = (double)ctr;
            indexer.put((double)ctr,ss);
            ctr++;
        }
        java.util.Arrays.sort(calcs, (a, b) -> {
            if (a[0] > b[0]) {
                return 1;
            }
            else if(a[0] < b[0]) {
                return -1;
            }
            else {
                if (a[1] >= b[1]) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        List<String> l = new ArrayList<>();
        List<String> s = new ArrayList<>();
        List<String> b = new ArrayList<>();
        for(int i = 0; i < calcs.length; i ++) {
            if (i < calcs.length/3) {
                s.add(indexer.get(calcs[i][2]));
            }
            else if (i < 2 * calcs.length/3) {
                b.add(indexer.get(calcs[i][2]));
            }
            else {
                l.add(indexer.get(calcs[i][2]));
            }
        }
        Portfolio recs = Portfolio.builder()
                .bench(b)
                .longs(l)
                .shorts(s)
                .build();
        return recs;


    }
}




