package com.fantasystocks.modules;

import org.junit.Test;

import java.util.Map;

import static com.fantasystocks.modules.Search.make_stock_data_base;
import static com.fantasystocks.modules.Search.stock_data;
import static org.junit.Assert.assertNotNull;

public class searchTest {
    @Test
    public void SearchTester() throws Exception{
        Map<String, String> data = make_stock_data_base();
        assertNotNull(data);
        String s = "AAPL";
        String[] stock = stock_data(data, s);
        assertNotNull(stock);

    }
}
