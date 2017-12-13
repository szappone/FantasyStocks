package com.fantasystocks.dao;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.dao.impl.StockDaoImpl;
import com.fantasystocks.entity.Stock;
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

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class StockDaoImplTest extends EasyMockSupport {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private Query query;
    @TestSubject
    private StockDaoImpl stockDaoImpl = new StockDaoImpl();

    private static final String tickerTest = "AAPL";

    @After
    public void teardown() {
        verifyAll();
    }

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(stockDaoImpl, "sessionFactory", sessionFactory);
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
        Stock stock = buildStock(tickerTest);
        expect(session.save(stock)).andReturn(tickerTest);
        replayAll();

        stockDaoImpl.add(stock);
    }

    @Test
    public void test_update() {
        setup_open_close();
        Stock stock = buildStock(tickerTest);
        session.saveOrUpdate(stock);
        expectLastCall().once();
        replayAll();

        stockDaoImpl.update(stock);
    }

    @Test
    public void test_get() {
        setup_open_close();
        Stock stock = buildStock(tickerTest);
        expect(session.get(Stock.class, tickerTest)).andReturn(stock);
        replayAll();

        Stock actual = stockDaoImpl.get(tickerTest);
        assertEquals(stock, actual);
    }

    @Test
    public void test_listPlayers() {
        setup_open_close();
        expect(session.createQuery(anyString())).andReturn(query).once();
        expect(query.getResultList()).andReturn(new ArrayList());
        replayAll();

        List<String> stockIds = stockDaoImpl.listStockIDs();
        assertNotNull(stockIds);
    }

    private Stock buildStock(String ticker) {
        return Stock.builder()
                .ticker(ticker)
                .build();
    }
}
