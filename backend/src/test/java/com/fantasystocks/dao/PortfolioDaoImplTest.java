package com.fantasystocks.dao;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.dao.impl.PortfolioDaoImpl;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.entity.Portfolio;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class PortfolioDaoImplTest extends EasyMockSupport {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private Query query;
    @TestSubject
    private PortfolioDaoImpl portfolioDaoImpl = new PortfolioDaoImpl();

    private static final Long portfolioIdTest = 1234L;
    private static final Long gameIdTest = 1234L;
    private static final String playerNameTest = "test_playerName";

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

    @Test
    public void test_get() {
        setup_open_close();
        Portfolio portfolio = buildPortfolio(portfolioIdTest);
        expect(session.get(Portfolio.class, portfolioIdTest)).andReturn(portfolio).once();
        replayAll();

        Portfolio actual = portfolioDaoImpl.get(portfolioIdTest);
        assertEquals(portfolio, actual);
    }

    @Test
    public void test_addPortfolioToSession() {
        setup_open_close();
        expect(session.createQuery(anyString())).andReturn(query).once();
        expect(query.setParameter("gid", gameIdTest)).andReturn(null);
        expect(query.setParameter("pid", playerNameTest)).andReturn(null);
        PlayerInGame playerInGame = new PlayerInGame();
        expect(query.getSingleResult()).andReturn(playerInGame);
        Portfolio portfolio = new Portfolio();
        expect(session.get(Portfolio.class, portfolioIdTest)).andReturn(portfolio).once();
        expect(session.save(playerInGame)).andReturn(null).once();
        replayAll();

        portfolioDaoImpl.addPortfolioToSession(portfolioIdTest, playerNameTest, gameIdTest);
    }

    private Portfolio buildPortfolio(Long portfolioId) {
        return Portfolio.builder()
                .portfolioId(portfolioId)
                .build();
    }
}
