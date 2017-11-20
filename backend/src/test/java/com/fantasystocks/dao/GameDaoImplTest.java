package com.fantasystocks.dao;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.dao.impl.GameDaoImpl;
import com.fantasystocks.dao.model.GameDao;
import com.fantasystocks.entity.Game;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;


@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class GameDaoImplTest extends EasyMockSupport {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @TestSubject
    private GameDaoImpl gameDaoImpl = new GameDaoImpl();

    private static final String gameNameTest = "test_gameName";
    private static final Long gameIdTest = 1234L;

    @After
    public void teardown() {
        verifyAll();
    }

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(gameDaoImpl, "sessionFactory", sessionFactory);
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
        Game game = buildGame(gameNameTest);
        expect(session.save(game)).andReturn(gameIdTest);
        replayAll();

        Long actualId = gameDaoImpl.add(game);
        assertEquals("Ids are not the same.", gameIdTest, actualId);
    }

    @Test
    public void test_update() {
        setup_open_close();
        Game game = buildGame(gameNameTest);
        session.saveOrUpdate(game);
        expectLastCall().andVoid().once();
        replayAll();

        gameDaoImpl.update(game);
    }

    @Test
    public void test_get() {
        setup_open_close();
        Game game = buildGame(gameNameTest);
        expect(session.get(Game.class, gameIdTest)).andReturn(game).once();
        replayAll();

        Game actual = gameDaoImpl.get(gameIdTest);
        assertEquals(game, actual);
    }

    private Game buildGame(String gameName) {
        return Game.builder()
                .gameName(gameName)
                .build();
    }
}
