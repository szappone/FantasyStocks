package com.fantasystocks;

import com.fantasystocks.config.AppConfig;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.impl.PlayerServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class app {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        PlayerServiceImpl playerService = context.getBean(PlayerServiceImpl.class);

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
