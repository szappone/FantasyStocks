package com.fantasystocks.service;

import com.fantasystocks.dao.model.PlayerInGameDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.service.impl.PlayerInGameServiceImpl;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.expectLastCall;

@RunWith(EasyMockRunner.class)
public class PlayerInGameServiceImplTest extends EasyMockSupport {
    @TestSubject
    PlayerInGameServiceImpl playerInGameService = new PlayerInGameServiceImpl();
    @Mock
    PlayerInGameDao playerInGameDao;

    private static final String playerNameTest = "test_playerName";
    private static final String gameNameTest = "test_gameName";
    private static final Long gameIdTest = 1234L;

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void test_add() {
        PlayerInGame playerInGame = buildPlayerInGame(playerNameTest, gameIdTest, gameNameTest);
        playerInGameDao.add(playerInGame);
        expectLastCall().andVoid().once();
        replayAll();

        playerInGameService.add(playerInGame);
    }

    @Test
    public void test_remove() {
        PlayerInGame playerInGame = buildPlayerInGame(playerNameTest, gameIdTest, gameNameTest);
        playerInGameDao.remove(playerInGame);
        expectLastCall().andVoid().once();
        replayAll();

        playerInGameService.remove(playerInGame);
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

    private PlayerInGame buildPlayerInGame(String playerName, long gameId, String gameName) {
        return PlayerInGame.builder()
                .player(buildPlayer(playerName))
                .game(buildGame(gameId, gameName))
                .build();
    }
}
