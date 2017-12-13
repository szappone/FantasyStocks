package com.fantasystocks.dao;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.dao.impl.PlayerDaoImpl;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
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

import org.hibernate.query.Query;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class PlayerDaoImplTest extends EasyMockSupport {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private Query query;
    @TestSubject
    private PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();

    private static final String playerNameTest = "test_playerName";
    private static final String gameNameTest = "test_gameName";
    private static final Long gameIdTest = 1234L;

    @After
    public void teardown() {
        verifyAll();
    }

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(playerDaoImpl, "sessionFactory", sessionFactory);
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
        Player player = buildPlayer(playerNameTest);
        expect(session.save(player)).andReturn(playerNameTest);
        replayAll();

        playerDaoImpl.add(player);
    }

    @Test
    public void test_update() {
        setup_open_close();
        Player player = buildPlayer(playerNameTest);
        session.saveOrUpdate(player);
        expectLastCall().andVoid().once();
        replayAll();

        playerDaoImpl.update(player);
    }

    @Test
    public void test_get() {
        setup_open_close();
        Player player = buildPlayer(playerNameTest);
        expect(session.get(Player.class, playerNameTest)).andReturn(player).once();
        replayAll();

        Player actual = playerDaoImpl.get(playerNameTest);
        assertEquals(player, actual);
    }

    @Test
    public void test_addToSession() {
        setup_open_close();
        Game game = buildGame(gameIdTest, gameNameTest);
        Player p = buildPlayer(playerNameTest);
        expect(session.get(Player.class, playerNameTest)).andReturn(p);
        expect(session.get(Game.class, gameIdTest)).andReturn(game);
        expect(session.save(p)).andReturn(playerNameTest).once();
        replayAll();

        playerDaoImpl.addToSession(p, game);
        assertEquals("Player names are not the same.", playerNameTest, p.getPlayerName());
        assertEquals("Game is not in player.", 1, p.getSessions().size());
    }

    @Test
    public void test_listPlayers() {
        setup_open_close();
        expect(session.createQuery(anyString())).andReturn(query).once();
        expect(query.getResultList()).andReturn(new ArrayList());
        replayAll();

        List<Player> playerList = playerDaoImpl.listPlayers();
        assertNotNull(playerList);
    }

    private Player buildPlayer(String playerName) {
        return Player.builder()
                .playerName(playerName)
                .build();
    }

    private Game buildGame(Long id, String gameName) {
        return Game.builder()
                .gameId(id)
                .gameName(gameName)
                .build();
    }
}
