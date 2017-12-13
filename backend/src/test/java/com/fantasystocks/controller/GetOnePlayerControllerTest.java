package com.fantasystocks.controller;
import com.fantasystocks.config.TestConfig;
import com.fantasystocks.entity.Player;
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
import javax.servlet.http.HttpServletResponse;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertNotNull;


@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class GetOnePlayerControllerTest extends EasyMockSupport {
    @TestSubject
    private GetOnePlayerController getOnePlayerController = new GetOnePlayerController();
    @Mock
    private PlayerService playerService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private static final String playerName = "playerName_test";

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(getOnePlayerController, "playerService", playerService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void getOnePlayerValid_Test() {
        expect(playerService.get(playerName)).andReturn(new Player()).once();
        replayAll();
        Object out = getOnePlayerController.getOnePlayer(httpServletRequest, httpServletResponse, playerName);
        assertNotNull(out);
    }

    @Test
    public void getOnePlayerInvalid_Test() {
        expect(playerService.get(playerName)).andReturn(null).once();
        httpServletResponse.setStatus(400);
        expectLastCall().once();
        replayAll();
        Object out = getOnePlayerController.getOnePlayer(httpServletRequest, httpServletResponse, playerName);
        assertNotNull(out);
    }
}
