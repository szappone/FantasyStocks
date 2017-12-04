package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreatePlayerRequest;
import com.fantasystocks.controller.api.GetPortfolioResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.model.PlayerService;
import com.fantasystocks.service.model.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@CrossOrigin
public class PostPortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @ResponseBody
    @RequestMapping(value = "/portfolio/{portfolioID}", method = RequestMethod.POST)
    public Object postPortfolio(@RequestBody GetPortfolioResponse body,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                    @RequestParam() long portfolioID) {
        log.debug("Updating portfolio with portfolioID: {}", portfolioID);

        Portfolio portfolioExists = portfolioService.get(portfolioID);
        if (portfolioExists != null && portfolioExists.getBench() == null && portfolioExists.getLongs() == null && portfolioExists.getShorts() == null){
            //Check if portfolio is composed of valid stockIDs
            /*Portfolio updatedPortfolio = Portfolio.builder()
                    .longs(body.getLongs())
                    .shorts(body.getShorts())
                    .bench(body.getBench())
                    .build();
            portfolioService.add(updatedPortfolio);*/
            return body;
        }

        return ResponseMessage.builder().message("Can't post portfolio to portfolioID which has not yet been mapped in a game").build();

    }

    //@ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
