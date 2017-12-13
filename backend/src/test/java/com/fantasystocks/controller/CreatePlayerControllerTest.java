package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.controller.api.CreatePlayerRequest;
import com.fantasystocks.controller.api.ResponseMessage;
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
public class CreatePlayerControllerTest extends EasyMockSupport {
    @TestSubject
    private CreatePlayerController createPlayerController = new CreatePlayerController();
    @Mock
    private PlayerService playerService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private static final String playerNameTest = "test_playerName";

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(createPlayerController, "playerService", playerService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    private void setupPlayerServiceInvalid(String playerName) {
        expect(playerService.get(playerName)).andReturn(buildPlayer(playerName));
        httpServletResponse.setStatus(400);
        expectLastCall().once();

        replayAll();
    }

    private void setupPlayerServiceValid(String playerName) {
        expect(playerService.get(playerName)).andReturn(null);
        playerService.add(anyObject());
        expectLastCall().once();

        replayAll();
    }

    @Test
    public void createPlayerControllerInvalid_Test() {
        setupPlayerServiceInvalid(playerNameTest);
        CreatePlayerRequest createPlayerRequest = CreatePlayerRequest.builder()
                .playerName(playerNameTest)
                .build();
        ResponseMessage responseMessage = (ResponseMessage)
                createPlayerController.createPlayer(createPlayerRequest, httpServletRequest, httpServletResponse);
        assertNotNull(responseMessage);
    }

    @Test
    public void createPlayerControllerValid_Test() {
        setupPlayerServiceValid(playerNameTest);
        CreatePlayerRequest createPlayerRequest = CreatePlayerRequest.builder()
                .playerName(playerNameTest)
                .build();
        Player responsePlayer = (Player)
                createPlayerController.createPlayer(createPlayerRequest, httpServletRequest, httpServletResponse);
        assertNotNull(responsePlayer);
    }

    private Player buildPlayer(String playerName) {
        return Player.builder()
                .playerName(playerName)
                .build();
    }
}
