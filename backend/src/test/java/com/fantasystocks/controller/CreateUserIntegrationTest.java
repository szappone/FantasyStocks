package com.fantasystocks.controller;

import com.fantasystocks.config.AppConfig;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import java.util.List;

@Ignore
@Test
public class CreateUserIntegrationTest extends EasyMockSupport {
    @BeforeEach
    public void setup() {
        resetAll();
    }

    @AfterEach
    public void teardown() {
        verifyAll();
    }

    @Test
    public void test_addPlayers() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        PlayerService playerService = context.getBean(PlayerService.class);

        // Add Players
        playerService.add(new Player("David"));
        playerService.add(new Player("Sameer"));
        playerService.add(new Player("Paul"));

        // Get Players
        List<Player> persons = playerService.listPlayers();
        for (Player person : persons) {
            System.out.println("Player Name = "+person.getPlayerName());
            System.out.println();
        }

        context.close();
    }
}
