package com.fantasystocks.entity.integration;

import com.fantasystocks.annotations.Integration;
import com.fantasystocks.config.TestConfig;
import com.fantasystocks.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.easymock.EasyMockSupport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Category(Integration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
public class IntegrationTestScaffold extends EasyMockSupport {
    @Autowired
    SessionFactory sessionFactory;
    Session session;
    Transaction tx;

    @Before
    public void setup() {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }

    @After
    public void teardown() {
        tx.commit();
        session.close();
    }

    Player buildPlayer(String playerName) {
        return Player.builder()
                .playerName(playerName)
                .sessions(new HashSet<>())
                .build();
    }

    Game buildGame(String gameName) {
        return Game.builder()
                .gameName(gameName)
                .players(new HashSet<>())
                .build();
    }

    Game buildGameWithPlayers(String gameName, Set<PlayerInGame> players) {
        return Game.builder()
                .gameName(gameName)
                .players(players)
                .players(new HashSet<>())
                .build();
    }
    
    Portfolio buildPortfolio(List<Stock> offense, List<Stock> defense, List<Stock> bench) {
        return Portfolio.builder()
                .bench(bench)
                .shorts(defense)
                .longs(offense)
                .build();
    }
}
