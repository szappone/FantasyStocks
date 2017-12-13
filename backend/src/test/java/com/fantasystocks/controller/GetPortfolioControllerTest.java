package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.model.PortfolioService;
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

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class GetPortfolioControllerTest extends EasyMockSupport {
    @TestSubject
    private GetPortfolioController getPortfolioController = new GetPortfolioController();
    @Mock
    private PortfolioService portfolioService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private static final long portfolioIdTest = 1234L;

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(getPortfolioController, "portfolioService", portfolioService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void getPortfolio_ValidTest() {
        expect(portfolioService.get(portfolioIdTest)).andReturn(new Portfolio()).once();
        replayAll();

        Object out = getPortfolioController.getPortfolio(httpServletRequest, httpServletResponse, portfolioIdTest);
        assertNotNull(out);
    }

    @Test
    public void getPortfolio_InvalidTest() {
        expect(portfolioService.get(portfolioIdTest)).andReturn(null).once();
        httpServletResponse.setStatus(404);
        expectLastCall().once();
        replayAll();

        Object out = getPortfolioController.getPortfolio(httpServletRequest, httpServletResponse, portfolioIdTest);
        assertNotNull(out);
    }

}
