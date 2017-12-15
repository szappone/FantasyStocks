package com.fantasystocks.modules;


import com.fantasystocks.entity.Portfolio;

import org.junit.Test;
import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class priceCalculatorTest {

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
        HashMap<String,Double> h = priceCalculator.PortfolioScores(portfolios);
        assertNotNull(h);

    }

}
