package com.fantasystocks.integration;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import lombok.extern.slf4j.Slf4j;
import org.easymock.EasyMockSupport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@Slf4j
public class PlayerAndGameIntegrationTest extends EasyMockSupport{
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void setup() {
        session = sessionFactory.openSession();
        replayAll();
    }

    @After
    public void teardown() {
        session.close();
        verifyAll();
    }

    @Test
    public void test_AddPlayerToDB() {
        String username = "test_username";
        session.save(buildPlayer(username));

        Player player = session.get(Player.class, username);
        assertEquals("Player username is not correct.", username, player.getUsername());
    }

    @Test
    public void test_AddSessionToDB() {
        String gameName = "test_gameName";
        Long gameId = 1234L;
        session.save(buildSession(gameId, gameName));

        Game game = session.get(Game.class, gameId);
        assertEquals("The gameIDs do not match.", (long) gameId, game.getGameId());
        assertEquals("The game names do not match.", gameName, game.getGameName());
    }

    private Player buildPlayer(String username) {
        return Player.builder()
                .username(username)
                .build();
    }

    private Game buildSession(Long sessionId, String gameName) {
        return Game.builder()
                .gameId(sessionId)
                .gameName(gameName)
                .build();
    }

    private Game buildSessionTotal(Long sessionId, String gameName, Set<PlayerInGame> players) {
        return Game.builder()
                .gameName(gameName)
                .players(players)
                .build();
    }
}
