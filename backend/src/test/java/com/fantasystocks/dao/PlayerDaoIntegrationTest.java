package com.fantasystocks.dao;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.dao.model.PlayerInGameDao;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class PlayerDaoIntegrationTest extends EasyMockSupport{
    @Autowired
    PlayerInGameDao playerInGameDao;

    @Before
    void setup() {
        replayAll();
    }

    @After
    void teardown() {
        verifyAll();
    }

    @Test
    public void test_AddPlayerInSession() {
    }
}
