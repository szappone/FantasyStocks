package com.fantasystocks.controller;
import com.fantasystocks.config.TestConfig;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Matchup;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.modules.PriceCalculator;
import com.fantasystocks.service.model.MatchupService;
import com.fantasystocks.service.model.PortfolioService;
import com.fantasystocks.service.model.StockService;
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

import java.util.HashMap;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertNotNull;


@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class GetOneMatchupControllerTest extends EasyMockSupport {
    @TestSubject
    private GetOneMatchupController getOneMatchupController = new GetOneMatchupController();
    @Mock
    private MatchupService matchupService;
    @Mock
    private PortfolioService portfolioService;
    @Mock
    private StockService stockService;
    @Mock
    private PriceCalculator priceCalculator;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private static final long matchupIdTest = 1234L;

    @Before
    public void setupAutowiredMocks() {
        ReflectionTestUtils.setField(getOneMatchupController, "matchupService", matchupService);
        ReflectionTestUtils.setField(getOneMatchupController, "stockService", stockService);
        ReflectionTestUtils.setField(getOneMatchupController, "portfolioService", portfolioService);
        ReflectionTestUtils.setField(getOneMatchupController, "calc", priceCalculator);
    }

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void getOnePlayerValid_Test() {
        expect(matchupService.get(matchupIdTest)).andReturn(buildMatchup(matchupIdTest)).once();
        expect(portfolioService.get(anyString(), anyLong())).andReturn(new Portfolio()).anyTimes();
        expect(priceCalculator.PortfolioScores(anyObject())).andReturn(new HashMap<>()).anyTimes();

        replayAll();
        Object out = getOneMatchupController.getMatchup(httpServletRequest, httpServletResponse, matchupIdTest);
        assertNotNull(out);
    }

    @Test
    public void getOnePlayerInvalid_Test() {
        expect(matchupService.get(matchupIdTest)).andReturn(null).once();
        httpServletResponse.setStatus(404);
        expectLastCall().once();
        replayAll();
        Object out = getOneMatchupController.getMatchup(httpServletRequest, httpServletResponse, matchupIdTest);
        assertNotNull(out);
    }

    private Matchup buildMatchup(long matchupId) {
        Game game = Game.builder()
                .gameName("gameName")
                .gameId(matchupIdTest)
                .build();
        return Matchup.builder()
                .matchupId(matchupId)
                .game(game)
                .player1Name("player1")
                .player2Name("player2")
                .activeWeek(0L)
                .build();
    }
}
