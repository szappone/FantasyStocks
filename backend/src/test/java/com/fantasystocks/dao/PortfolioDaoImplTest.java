package com.fantasystocks.dao;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.dao.impl.PortfolioDaoImpl;
import com.fantasystocks.entity.Portfolio;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class PortfolioDaoImplTest extends EasyMockSupport {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @TestSubject
    private PortfolioDaoImpl portfolioDaoImpl = new PortfolioDaoImpl();

    private static final Long portfolioIdTest = 1234L;

    @After
    public void teardown() {
        verifyAll();
    }

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(portfolioDaoImpl, "sessionFactory", sessionFactory);
    }

    private void setup_open_close() {
        expect(sessionFactory.openSession()).andReturn(session).anyTimes();
        expect(session.beginTransaction()).andReturn(transaction).anyTimes();
        transaction.commit();
        expectLastCall().anyTimes();
        session.close();
        expectLastCall().anyTimes();
    }

    @Test
    public void test_add() {
        setup_open_close();
        Portfolio portfolio = buildPortfolio(portfolioIdTest);
        expect(session.save(portfolio)).andReturn(portfolioIdTest);
        replayAll();

        portfolioDaoImpl.add(portfolio);
    }

    @Test
    public void test_update() {
        setup_open_close();
        Portfolio portfolio = buildPortfolio(portfolioIdTest);
        session.saveOrUpdate(portfolio);
        expectLastCall().andVoid().once();
        replayAll();

        portfolioDaoImpl.update(portfolio);
    }

    private Portfolio buildPortfolio(Long portfolioId) {
        return Portfolio.builder()
                .portfolioId(portfolioId)
                .build();
    }
}
