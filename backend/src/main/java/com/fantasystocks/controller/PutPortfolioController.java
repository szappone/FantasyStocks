package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetPortfolioResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.model.PortfolioService;
import com.fantasystocks.service.model.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@CrossOrigin
public class PutPortfolioController extends ControllerErrorHandler {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private StockService stockService;

    @ResponseBody
    @RequestMapping(value = "/portfolio/{portfolioID}", method = RequestMethod.PUT)
    public Object putPortfolio(@RequestBody GetPortfolioResponse body,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("portfolioID") long portfolioID) {
        log.debug("Updating portfolio with portfolioID: {}", portfolioID);

        Portfolio portfolioExists = portfolioService.get(portfolioID);
        if (portfolioExists == null) {
            response.setStatus(400);
            return ResponseMessage.builder().message("Can't update portfolio to portfolioID which has not yet been mapped in a game").build();
        } else if (portfolioExists.getBench().size() == 0 && portfolioExists.getLongs().size() == 0 && portfolioExists.getShorts().size() == 0) {
            response.setStatus(400);
            return ResponseMessage.builder().message("Can't update a portfolio which has never had a first draft").build();
        } else {
            portfolioExists.setBench(body.getBench());
            portfolioExists.setLongs(body.getLongs());
            portfolioExists.setShorts(body.getShorts());
            portfolioService.update(portfolioExists);
            body.setPortfolioID(portfolioID);
            return body;
        }
    }
}
