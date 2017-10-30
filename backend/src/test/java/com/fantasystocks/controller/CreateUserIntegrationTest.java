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
        playerService.add(new Player("David", "Miller", "davmill", "david.miller@example.com"));
        playerService.add(new Player("Sameer", "Singh", "mc_dicko","sameer.singh@example.com"));
        playerService.add(new Player("Paul", "Smith", "paul_smith","paul.smith@example.com"));

        // Get Players
        List<Player> persons = playerService.listPlayers();
        for (Player person : persons) {
            System.out.println("Id = "+person.getId());
            System.out.println("First Name = "+person.getFirstName());
            System.out.println("Last Name = "+person.getLastName());
            System.out.println("Email = "+person.getEmail());
            System.out.println();
        }

        context.close();
    }
}
