package com.fantasystocks.service;

import com.fantasystocks.controller.api.Session;
import com.fantasystocks.dao.model.GameDao;
import com.fantasystocks.dao.model.PlayerInGameDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.service.impl.GameServiceImpl;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

@RunWith(EasyMockRunner.class)
public class GameServiceImplTest extends EasyMockSupport {
    @TestSubject
    private GameServiceImpl gameService = new GameServiceImpl();
    @Mock
    private GameDao sessionDao;
    @Mock
    private PlayerInGameDao playerInGameDao;

    private static final String playerNameTest = "test_playerName";
    private static final String gameNameTest = "test_gameName";
    private static final Long gameIdTest = 1234L;
    private static final List<Long> gameIds = ImmutableList.of(1000L, 2000L);
    private static final List<String> games = ImmutableList.of("test_gameName", "test_gameName2");
    private static final List<Set<String>> playerNamesInGames =
            ImmutableList.of(
                    ImmutableSet.of("test_playerName", "test_playerName2"),
                    ImmutableSet.of("test_playerName", "test_playerName3", "test_playerName4"));

    public void setup() {
        replayAll();
    }

    @Before
    public void autowireMocks() {
        ReflectionTestUtils.setField(gameService, "sessionDao", sessionDao);
        ReflectionTestUtils.setField(gameService, "playerInGameDao", playerInGameDao);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void test_add() {
        Game expected = buildGame(gameIdTest, gameNameTest);
        expect(sessionDao.add(expected)).andReturn(gameIdTest).once();
        setup();

        gameService.add(expected);
    }

    @Test
    public void test_update() {
        Game expected = buildGame(gameIdTest, gameNameTest);
        sessionDao.update(expected);
        expectLastCall().once();
        setup();

        gameService.update(expected);
    }

    @Test
    public void test_get() {
        Game expected = buildGame(gameIdTest, gameNameTest);
        expect(sessionDao.get(gameIdTest)).andReturn(expected).once();
        setup();

        Game actual = gameService.get(gameIdTest);
        assertEquals("The game returned is not correct.", expected, actual);
    }

    private void setupGetAllSessions() {
        List<PlayerInGame> playerInGames = new ArrayList<>();
        int len = games.size();
        for (int x = 0; x < len; ++x) {
            long gameId = gameIds.get(x);
            String gameName = games.get(x);
            Set<String> playerNames = playerNamesInGames.get(x);
            playerInGames.add(buildPlayerInGame(playerNameTest, gameId, gameName));
            expect(sessionDao.get(gameId)).andReturn(buildGame(gameId, gameName, playerNames)).once();
        }
        expect(playerInGameDao.getGamesForPlayer(playerNameTest)).andReturn(playerInGames).once();
    }

    @Test
    public void test_getAllSessions() {
        setupGetAllSessions();
        setup();
        List<Session> sessions = gameService.getAllSessions(playerNameTest);
        int len = games.size();
        for (int x = 0; x < len; ++x) {
            Session session = sessions.get(x);
            assertEquals(session.getSessionName(), games.get(x));
            List<String> players = new ArrayList<>(playerNamesInGames.get(x));
            List<String> actualPlayers = session.getPlayers();
            Collections.sort(players);
            Collections.sort(actualPlayers);
            assertEquals(session.getPlayers(), players);
            assertEquals(session.getSessionId(), gameIds.get(x));
        }
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

    private Game buildGame(Long id, String gameName, Set<String> playerNames) {
        Set<PlayerInGame> playerInGames =
                playerNames.stream().map(s -> buildPlayerInGame(s, id, gameName)).collect(Collectors.toSet());

        return Game.builder()
                .gameId(id)
                .gameName(gameName)
                .players(playerInGames)
                .build();
    }

    private PlayerInGame buildPlayerInGame(String playerName, long gameId, String gameName) {
        return PlayerInGame.builder()
                .player(buildPlayer(playerName))
                .game(buildGame(gameId, gameName))
                .build();
    }
}
