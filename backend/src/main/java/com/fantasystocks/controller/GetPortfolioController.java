package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetPortfolioResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.entity.Stock;
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
public class GetPortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @ResponseBody
    @RequestMapping(value = "/portfolio/{portfolioID}", method = RequestMethod.GET)
    public Object getPortfolio(HttpServletRequest request,
                                     HttpServletResponse response,
                                    @PathVariable("portfolioID") long portfolioID) {
        log.debug("Getting portfolio with portfolioID: {}", portfolioID);

        Portfolio portfolioExists = portfolioService.get(portfolioID);
        if (portfolioExists != null){
            return GetPortfolioResponse.builder()
                    .bench(portfolioExists.getBench())
                    .longs(portfolioExists.getLongs())
                    .shorts(portfolioExists.getShorts())
                    .portfolioID(portfolioExists.getPortfolioId())
                    .build();
        } else {
            return ResponseMessage.builder().message("Portfolio with that portfolioID does not exist").build();
        }
    }

    //@ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
