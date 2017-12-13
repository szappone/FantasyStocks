package com.fantasystocks.service;

import com.fantasystocks.dao.model.PortfolioDao;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.impl.PortfolioServiceImpl;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

@RunWith(EasyMockRunner.class)
public class PortfolioServiceImplTest extends EasyMockSupport {
    @TestSubject
    private PortfolioServiceImpl portfolioService = new PortfolioServiceImpl();
    @Mock
    private PortfolioDao portfolioDao;

    private static final long portfolioIdTest = 1234L;
    @Before
    public void autowireMocks() {
        ReflectionTestUtils.setField(portfolioService, "portfolioDao", portfolioDao);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void test_add() {
        Portfolio expected = buildPortfolio(portfolioIdTest);
        portfolioDao.add(expected);
        expectLastCall().once();
        replayAll();

        portfolioService.add(expected);
    }

    @Test
    public void test_update() {
        Portfolio expected = buildPortfolio(portfolioIdTest);
        portfolioDao.update(expected);
        expectLastCall().once();
        replayAll();

        portfolioService.update(expected);
    }

    @Test
    public void test_get() {
        Portfolio expected = buildPortfolio(portfolioIdTest);
        expect(portfolioService.get(portfolioIdTest)).andReturn(expected);
        replayAll();

        Portfolio actual = portfolioService.get(portfolioIdTest);
        assertEquals(expected, actual);
    }


    private Portfolio buildPortfolio(Long id) {
        return Portfolio.builder()
                .portfolioId(id)
                .build();
    }
}
