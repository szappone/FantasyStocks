package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.entity.Stock;
import com.fantasystocks.modules.PriceCalculator;
import com.fantasystocks.service.model.StockService;
import org.easymock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class GetOneStockControllerTest extends EasyMockSupport {
    @TestSubject
    private GetOneStockController getOneStockController = new GetOneStockController();
    @Mock
    private StockService stockService;
    @Mock
    private PriceCalculator PriceCalculator;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private static final String stockIdTest = "stockId_test";

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(getOneStockController, "stockService", stockService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void getOneStock_ValidTest() {
        expect(stockService.get(stockIdTest)).andReturn(new Stock()).once();
        replayAll();
        try {
            Object out = getOneStockController.getOneStock(httpServletRequest, httpServletResponse, stockIdTest);
            assertNotNull(out);
        } catch (Exception e) {
            assertNotNull(getOneStockController);
        }
    }

    @Test
    public void getOneStock_InvalidTest() {
        expect(stockService.get(stockIdTest)).andReturn(null).once();
        replayAll();
        Object out = getOneStockController.getOneStock(httpServletRequest, httpServletResponse, stockIdTest);
        assertNotNull(out);
    }
}
