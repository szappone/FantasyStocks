package com.fantasystocks.dao;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.dao.impl.PlayerInGameDaoImpl;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.entity.PlayerInGameId;
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

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertNotNull;


@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class PlayerInGameDaoImplTest extends EasyMockSupport {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private Query query;
    @TestSubject
    private PlayerInGameDaoImpl playerInGameDaoImpl = new PlayerInGameDaoImpl();

    private static final String gameNameTest = "test_gameName";
    private static final String playerNameTest = "test_playerName";
    private static final Long gameIdTest = 1234L;

    @After
    public void teardown() {
        verifyAll();
    }

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(playerInGameDaoImpl, "sessionFactory", sessionFactory);
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
        PlayerInGame game = buildPlayerInGame(gameNameTest, playerNameTest);
        PlayerInGameId playerInGameId = PlayerInGameId.builder()
                .game(gameIdTest)
                .player(playerNameTest)
                .build();
        expect(session.save(game)).andReturn(playerInGameId);
        replayAll();

        playerInGameDaoImpl.add(buildPlayerInGame(gameNameTest, playerNameTest));
    }

    @Test
    public void test_remove() {
        setup_open_close();
        PlayerInGame playerInGame = buildPlayerInGame(gameNameTest, playerNameTest);
        session.delete(playerInGame);
        expectLastCall().andVoid();
        replayAll();

        playerInGameDaoImpl.remove(playerInGame);
    }

    @Test
    public void test_listPlayers() {
        setup_open_close();
        expect(session.createQuery(anyString())).andReturn(query).once();
        expect(query.setParameter("pn", playerNameTest)).andReturn(null);
        expect(query.getResultList()).andReturn(new ArrayList());
        replayAll();

        List<PlayerInGame> playerInGames = playerInGameDaoImpl.getGamesForPlayer(playerNameTest);
        assertNotNull(playerInGames);
    }

    private PlayerInGame buildPlayerInGame(String gameName, String playerName) {
        Game game = Game.builder()
                .gameName(gameName)
                .build();
        Player player = Player.builder()
                .playerName(playerName)
                .build();
        return PlayerInGame.builder()
                .game(game)
                .player(player)
                .build();
    }
}
