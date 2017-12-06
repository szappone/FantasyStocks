package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreatePlayerRequest;
import com.fantasystocks.controller.api.GetPortfolioResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.entity.Stock;
import com.fantasystocks.service.model.PlayerService;
import com.fantasystocks.service.model.PortfolioService;
import com.fantasystocks.service.model.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@CrossOrigin
public class PostPortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private StockService stockService;

    @ResponseBody
    @RequestMapping(value = "/portfolio/{portfolioID}", method = RequestMethod.POST)
    public Object postPortfolio(@RequestBody GetPortfolioResponse body,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                    @PathVariable("portfolioID") long portfolioID) {
        log.debug("First stock update for portfolio with portfolioID: {}", portfolioID);

        Portfolio portfolioExists = portfolioService.get(portfolioID);
        if (portfolioExists != null){
            //Check if portfolio is composed of valid stockIDs
            List<Stock> longs = new ArrayList<>();
            List<Stock> shorts = new ArrayList<>();
            List<Stock> bench = new ArrayList<>();
            for (int i = 0; i < 3; i++){
                longs.add(stockService.get(body.getLongs().get(i)));
                shorts.add(stockService.get(body.getShorts().get(i)));
                bench.add(stockService.get(body.getBench().get(i)));
            }
            portfolioExists.setBench(bench);
            portfolioExists.setLongs(longs);
            portfolioExists.setShorts(shorts);
            portfolioService.update(portfolioExists);
            return body;
        } else {
            return ResponseMessage.builder().message("Can't post portfolio to portfolioID which has not yet been mapped in a game").build();
        }
    }

    //@ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
