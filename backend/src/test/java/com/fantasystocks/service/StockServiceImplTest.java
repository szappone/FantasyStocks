package com.fantasystocks.service;

import com.fantasystocks.dao.model.StockDao;
import com.fantasystocks.entity.Stock;
import com.fantasystocks.service.impl.StockServiceImpl;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(EasyMockRunner.class)
public class StockServiceImplTest extends EasyMockSupport {
    @TestSubject
    private StockServiceImpl stockService = new StockServiceImpl();
    @Mock
    private StockDao stockDao;

    private static final String stockIdTest = "TEST";
    
    @Before
    public void autowireMocks() {
        ReflectionTestUtils.setField(stockService, "stockDao", stockDao);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void test_add() {
        Stock expected = buildStock(stockIdTest);
        stockDao.add(expected);
        expectLastCall().once();
        replayAll();

        stockService.add(expected);
    }

    @Test
    public void test_update() {
        Stock expected = buildStock(stockIdTest);
        stockDao.update(expected);
        expectLastCall().once();
        replayAll();

        stockService.update(expected);
    }

    @Test
    public void test_get() {
        Stock expected = buildStock(stockIdTest);
        expect(stockService.get(stockIdTest)).andReturn(expected);
        replayAll();

        Stock actual = stockService.get(stockIdTest);
        assertEquals(expected, actual);
    }

    @Test
    public void test_listStockIDs() {
        expect(stockDao.listStockIDs()).andReturn(new ArrayList<>()).once();
        replayAll();

        List<String> stocks = stockService.listStockIDs();
        assertNotNull(stocks);
    }


    private Stock buildStock(String id) {
        return Stock.builder()
                .ticker(id)
                .build();
    }
}
