package com.fantasystocks.integration;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.easymock.EasyMockSupport;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.Serializable;
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
        assertEquals("Player username is not correct.", username, player.getPlayerName());
    }
    
    @Test
    public void test_AddSessionToDB() {
        String gameName = "test_gameName";
        Game game = buildGame(gameName);
        Long id = (Long) session.save(game);

        Game gameDB = session.get(Game.class, id);
        assertEquals("The gameIDs do not match.", (long) id, gameDB.getGameId());
        assertEquals("The game names do not match.", gameName, gameDB.getGameName());
    }

    private Player buildPlayer(String playerName) {
        return Player.builder()
                .playerName(playerName)
                .build();
    }

    private Game buildGame(String gameName) {
        return Game.builder()
                .gameName(gameName)
                .build();
    }

    private Game buildGameTotal(Long sessionId, String gameName, Set<PlayerInGame> players) {
        return Game.builder()
                .gameName(gameName)
                .players(players)
                .build();
    }
}
