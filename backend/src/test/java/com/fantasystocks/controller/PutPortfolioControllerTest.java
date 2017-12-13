package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.controller.api.GetPortfolioResponse;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.model.PortfolioService;
import com.fantasystocks.service.model.StockService;
import com.google.common.collect.Lists;
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

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class PutPortfolioControllerTest extends EasyMockSupport {
    @TestSubject
    private PutPortfolioController putPortfolioController = new PutPortfolioController();
    @Mock
    private PortfolioService portfolioService;
    @Mock
    private StockService stockService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private static final long portfolioIdTest = 1234L;

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(putPortfolioController, "portfolioService", portfolioService);
        ReflectionTestUtils.setField(putPortfolioController, "stockService", stockService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void postPortfolioTest() {
        GetPortfolioResponse input = GetPortfolioResponse.builder()
                .bench(new ArrayList<>())
                .longs(new ArrayList<>())
                .shorts(new ArrayList<>())
                .portfolioID(portfolioIdTest)
                .build();
        Portfolio portfolio = Portfolio.builder()
                .bench(Lists.newArrayList("AAPL"))
                .longs(Lists.newArrayList("AAPL"))
                .shorts(Lists.newArrayList("AAPL"))
                .build();
        expect(portfolioService.get(portfolioIdTest)).andReturn(portfolio);
        portfolioService.update(portfolio);
        expectLastCall().once();
        replayAll();

        Object out = putPortfolioController.putPortfolio(input, httpServletRequest, httpServletResponse, portfolioIdTest);
        assertNotNull(out);
    }

    @Test
    public void postPortfolioTest_InUse() {
        GetPortfolioResponse input = GetPortfolioResponse.builder()
                .bench(Lists.newArrayList("AAPL"))
                .longs(Lists.newArrayList("AAPL"))
                .shorts(Lists.newArrayList("AAPL"))
                .portfolioID(portfolioIdTest)
                .build();
        Portfolio portfolio = Portfolio.builder()
                .bench(new ArrayList<>())
                .longs(new ArrayList<>())
                .shorts(new ArrayList<>())
                .build();
        expect(portfolioService.get(portfolioIdTest)).andReturn(portfolio);
        httpServletResponse.setStatus(400);
        expectLastCall().once();
        replayAll();

        Object out = putPortfolioController.putPortfolio(input, httpServletRequest, httpServletResponse, portfolioIdTest);
        assertNotNull(out);
    }

    @Test
    public void postPortfolioTest_Null() {
        GetPortfolioResponse input = GetPortfolioResponse.builder()
                .bench(Lists.newArrayList("AAPL"))
                .longs(Lists.newArrayList("AAPL"))
                .shorts(Lists.newArrayList("AAPL"))
                .portfolioID(portfolioIdTest)
                .build();
        expect(portfolioService.get(portfolioIdTest)).andReturn(null);
        httpServletResponse.setStatus(400);
        expectLastCall().once();
        replayAll();

        Object out = putPortfolioController.putPortfolio(input, httpServletRequest, httpServletResponse, portfolioIdTest);
        assertNotNull(out);
    }

}
