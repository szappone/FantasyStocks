package com.fantasystocks.modules;


import com.fantasystocks.entity.Portfolio;

import com.fantasystocks.service.model.StockService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class priceCalculatorTest {
    @Autowired
    private StockService stockService;

    @Test

    public void test_PortfolioScores(){
        List<String> l = Lists.newArrayList("AAPL","AMZN","GM");
        List<String> s = Lists.newArrayList("TRIP","AMT","LUK");
        List<String> b = Lists.newArrayList("IBM","WDC","C");
        Portfolio portfolios = Portfolio.builder()
                .bench(b)
                .longs(l)
                .shorts(s)
                .build();
        HashMap<String,Double> h = priceCalculator.PortfolioScores(portfolios, stockService);
        assertNotNull(h);

    }

}
