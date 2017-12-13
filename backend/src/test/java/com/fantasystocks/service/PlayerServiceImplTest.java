package com.fantasystocks.service;

import com.fantasystocks.dao.model.PlayerDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.impl.PlayerServiceImpl;
import com.google.common.collect.ImmutableList;
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
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(EasyMockRunner.class)
public class PlayerServiceImplTest extends EasyMockSupport {
    @TestSubject
    private PlayerServiceImpl playerService = new PlayerServiceImpl();
    @Mock
    private PlayerDao playerDao;

    private static final String playerNameTest = "test_playerName";
    private static final String gameNameTest = "test_gameName";
    private static final Long gameIdTest = 1234L;
    private static final List<String> players = ImmutableList.of("test_playerName", "test_playerName2");

    @Before
    public void autowireMocks() {
        ReflectionTestUtils.setField(playerService, "playerDao", playerDao);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void test_add() {
        Player expected = buildPlayer(playerNameTest);
        playerDao.add(expected);
        expectLastCall().once();
        replayAll();

        playerService.add(expected);
    }

    @Test
    public void test_update() {
        Player expected = buildPlayer(playerNameTest);
        playerDao.update(expected);
        expectLastCall().once();
        replayAll();

        playerService.update(expected);
    }

    @Test
    public void test_addToSession() {
        Player expected = buildPlayer(playerNameTest);
        Game game = buildGame(gameIdTest, gameNameTest);
        playerDao.addToSession(expected, game);
        expectLastCall().once();
        replayAll();

        playerService.addToSession(expected, game);
    }

    @Test
    public void test_get() {
        Player expected = buildPlayer(playerNameTest);
        expect(playerService.get(playerNameTest)).andReturn(expected);
        replayAll();

        Player actual = playerService.get(playerNameTest);
        assertEquals(expected, actual);
    }

    @Test
    public void test_listPlayers() {
        expect(playerService.listPlayers()).andReturn(new ArrayList<>()).once();
        replayAll();

        List<Player> players = playerService.listPlayers();
        assertNotNull(players);
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
