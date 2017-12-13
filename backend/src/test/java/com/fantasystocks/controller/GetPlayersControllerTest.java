package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.service.model.PlayerService;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class GetPlayersControllerTest extends EasyMockSupport {
    @TestSubject
    private GetPlayersController getPlayersController = new GetPlayersController();
    @Mock
    private PlayerService playerService;
    @Mock
    private HttpServletRequest httpServletRequest;

    private static final String playerNameTest = "playerName_test";

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(getPlayersController, "playerService", playerService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void getPlayers_Test() {
        expect(playerService.listPlayers()).andReturn(new ArrayList<>()).once();
        replayAll();
        Object out = getPlayersController.getPlayers(httpServletRequest);
        assertNotNull(out);
    }
}
