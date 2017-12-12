package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.controller.api.CreatePlayerRequest;
import com.fantasystocks.controller.api.CreateSessionRequest;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.GameService;
import com.fantasystocks.service.model.PlayerService;
import com.google.common.collect.ImmutableList;
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

import java.util.List;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertNotNull;


@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class CreateSessionControllerTest extends EasyMockSupport {
    @TestSubject
    private CreateSessionController createSessionController = new CreateSessionController();
    @Mock
    private PlayerService playerService;
    @Mock
    private GameService gameService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private static final List<String> playerNamesTest = ImmutableList.of("player1_test, player2_test, player3_test");
    private static final String sessionName = "sessionNameTest";

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(createSessionController, "playerService", playerService);
        ReflectionTestUtils.setField(createSessionController, "gameService", gameService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    private void setupInvalid() {
        expect(playerService.get(anyObject())).andReturn(null);

        replayAll();
    }

    private void setupPlayerServiceValid(String playerName) {
        expect(playerService.get(playerName)).andReturn(null);
        playerService.add(anyObject());
        expectLastCall().once();

        replayAll();
    }

    @Test
    public void createPlayerControllerInvalid_Test() throws Exception {
        setupInvalid();
        CreateSessionRequest request = CreateSessionRequest.builder()
                .players(playerNamesTest)
                .sessionName(sessionName)
                .build();
        try {
            createSessionController.createSession(request, httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            assert (true);
        }
    }

    private Player buildPlayer(String playerName) {
        return Player.builder()
                .playerName(playerName)
                .build();
    }
}
