package com.fantasystocks.modules;

import com.fantasystocks.entity.Portfolio;
import static com.fantasystocks.modules.DuringGamePlayRecommender.sendRecs;
import com.google.common.collect.Lists;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import java.util.List;

public class DuringPlayRecommenderTest {

    @Test
    public void TestSendRecs() {
        List<String> l = Lists.newArrayList("IBM","WDC","C");
        List<String> s = Lists.newArrayList("TRIP","AMT","LUK");
        List<String> b = Lists.newArrayList("AAPL","AMZN","GM");
        Portfolio portfolios = Portfolio.builder()
                .bench(b)
                .longs(l)
                .shorts(s)
                .build();
        Portfolio p = sendRecs(portfolios);
        assertNotNull(p);
    }
}
