package com.fantasystocks.modules;


import com.fantasystocks.entity.Portfolio;

import com.fantasystocks.entity.Stock;
import com.fantasystocks.service.model.StockService;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.collections.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
@RunWith(EasyMockRunner.class)
public class priceCalculatorTest extends EasyMockSupport {
    @TestSubject
    private PriceCalculator calc = new PriceCalculator();
    @Mock
    public StockService stockService;

    @Before
    public void autowireMocks() {
        ReflectionTestUtils.setField(calc, "stockService", stockService);
    }

    @Before
    public void init() {
        stockService.add(Stock.builder()
                .companyName("Amazon.com, Inc.")
                .ticker("AMZN")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("Apple Inc.")
                .ticker("AAPL")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("General Motors Company")
                .ticker("GM")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("TripAdvisor, Inc.")
                .ticker("TRIP")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("American Tower Corporation")
                .ticker("AMT")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("Leucadia National Corporation")
                .ticker("LUK")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("Alphabet Inc.")
                .ticker("GOOG")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("International Business Machines Corporation")
                .ticker("IBM")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("Western Digital Corporation")
                .ticker("WDC")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
        stockService.add(Stock.builder()
                .companyName("Citigroup Inc.")
                .ticker("C")
                .lastMondayPrice(15.0)
                .todayPrice(30.0)
                .build());
    }


    @Test
    @Ignore
    public void test_PortfolioScores(){
        List<String> l = Lists.newArrayList("AAPL","AMZN","GM");
        List<String> s = Lists.newArrayList("TRIP","AMT","LUK");
        List<String> b = Lists.newArrayList("IBM","WDC","C");
        Portfolio portfolios = Portfolio.builder()
                .bench(b)
                .longs(l)
                .shorts(s)
                .build();
        Map<String, Map<String, Double>> h = calc.PortfolioScores(portfolios);
        assertNotNull(h);

    }

}
