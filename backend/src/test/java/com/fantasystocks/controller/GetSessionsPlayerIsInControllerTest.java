package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.service.model.GameService;
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
import java.util.ArrayList;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class GetSessionsPlayerIsInControllerTest extends EasyMockSupport {
    @TestSubject
    private GetSessionsPlayerIsInController getSessionsPlayerIsInController = new GetSessionsPlayerIsInController();
    @Mock
    private GameService gameService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private static final String playerNameTest = "playerName_test";

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(getSessionsPlayerIsInController, "gameService", gameService);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void getPortfolio_ValidTest() {
        expect(gameService.getAllSessions(playerNameTest)).andReturn(new ArrayList<>()).once();
        replayAll();

        Object out = getSessionsPlayerIsInController.getAll(httpServletRequest, httpServletResponse, playerNameTest);
        assertNotNull(out);
    }
}
