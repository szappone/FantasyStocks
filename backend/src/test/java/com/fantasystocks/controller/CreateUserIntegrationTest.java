package com.fantasystocks.controller;

import com.fantasystocks.config.AppConfig;
import com.fantasystocks.service.model.PlayerService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

@Ignore
@Test
public class CreateUserIntegrationTest extends EasyMockSupport {
    @Before
    public void setup() {
        resetAll();
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void test_addPlayers() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        PlayerService playerService = context.getBean(PlayerService.class);


        context.close();
    }
}
