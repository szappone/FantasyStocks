package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetMatchupResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Matchup;
import com.fantasystocks.service.model.MatchupService;
import com.fantasystocks.service.model.PortfolioService;
import com.fantasystocks.modules.priceCalculator;
import com.fantasystocks.service.model.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.Map;

@Controller
@Slf4j
@CrossOrigin
public class GetOneMatchupController extends ControllerErrorHandler {
    @Autowired
    private MatchupService matchupService;
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private StockService stockService;

    @ResponseBody
    @RequestMapping(value = "/matchups/{matchupID}", method = RequestMethod.GET)
    public Object getMatchup(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable long matchupID) {
        log.info("Getting info for matchup with matchupID: {}", matchupID);

        //Check to make sure that this player exists and if so return it
        Matchup checkMatchupExists = matchupService.get(matchupID);
        if (checkMatchupExists != null) {
            Map<String, Double> p1scores = priceCalculator.PortfolioScores(portfolioService.get(checkMatchupExists.getPlayer1Name(), checkMatchupExists.getGame().getGameId()), stockService);
            Map<String, Double> p2scores = priceCalculator.PortfolioScores(portfolioService.get(checkMatchupExists.getPlayer2Name(), checkMatchupExists.getGame().getGameId()), stockService);
            return GetMatchupResponse.builder()
                    .player1Name(checkMatchupExists.getPlayer1Name())
                    .player2Name(checkMatchupExists.getPlayer2Name())
                    .gameID(checkMatchupExists.getGame().getGameId())
                    .matchupId(checkMatchupExists.getMatchupId())
                    .p1Score(p1scores)
                    .p2Score(p2scores)
                    .build();
        } else {
            response.setStatus(404);
            return ResponseMessage.builder().message("Matchup doems not exist").build();
        }
    }
}
