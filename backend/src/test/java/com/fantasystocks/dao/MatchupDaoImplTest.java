package com.fantasystocks.dao;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.dao.impl.MatchupDaoImpl;
import com.fantasystocks.entity.Matchup;
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
public class MatchupDaoImplTest extends EasyMockSupport {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private Query query;
    @TestSubject
    private MatchupDaoImpl matchupDaoImpl = new MatchupDaoImpl();

    private static final Long matchupIdTest = 1234L;

    @After
    public void teardown() {
        verifyAll();
    }

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(matchupDaoImpl, "sessionFactory", sessionFactory);
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
        Matchup matchup = buildMatchup(matchupIdTest);
        expect(session.save(matchup)).andReturn(matchupIdTest);
        replayAll();

        matchupDaoImpl.add(matchup);
    }

    @Test
    public void test_get() {
        setup_open_close();
        Matchup matchup = buildMatchup(matchupIdTest);
        expect(session.get(Matchup.class, matchupIdTest)).andReturn(matchup).once();
        replayAll();

        Matchup actual = matchupDaoImpl.get(matchupIdTest);
        assertEquals(matchup, actual);
    }

    @Test
    public void test_listMatchupIDs() {
        setup_open_close();
        expect(session.createQuery(anyString())).andReturn(query).once();
        expect(query.setParameter(anyString(), anyLong())).andReturn(query).anyTimes();
        expect(query.getResultList()).andReturn(new ArrayList()).once();
        replayAll();

        List out = matchupDaoImpl.listMatchupIDs(matchupIdTest, matchupIdTest);
        assertNotNull(out);
    }

    private Matchup buildMatchup(long matchupId) {
        return Matchup.builder()
                .matchupId(matchupId)
                .build();
    }
}
