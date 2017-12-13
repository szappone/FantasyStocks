package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.service.model.StockService;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class GetAllStocksControllerTest extends EasyMockSupport {
    @TestSubject
    private GetAllStocksController getAllStocksController = new GetAllStocksController();
    @Mock
    private StockService stockService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(getAllStocksController, "stockService", stockService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void getAllStocks_test() {
        expect(stockService.listStockIDs()).andReturn(new ArrayList<>());
        replayAll();
        List<String> list = (List<String>) getAllStocksController.getAllStocks(httpServletRequest, httpServletResponse);
        assertNotNull(list);
    }
}
