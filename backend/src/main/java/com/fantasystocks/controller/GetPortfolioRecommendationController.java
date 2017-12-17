package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetPortfolioResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.model.PortfolioService;
import com.fantasystocks.modules.DuringGamePlayRecommender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@CrossOrigin
public class GetPortfolioRecommendationController extends ControllerErrorHandler {
    @Autowired
    private PortfolioService portfolioService;

    @ResponseBody
    @RequestMapping(value = "/portfolio/recommended/{portfolioID}", method = RequestMethod.GET)
    public Object GetPortfolioRecommendation(HttpServletRequest request,
                                     HttpServletResponse response,
                                    @PathVariable("portfolioID") long portfolioID) {
        log.debug("Getting portfolio recommendation with portfolioID: {}", portfolioID);

        Portfolio portfolioExists = portfolioService.get(portfolioID);
        if (portfolioExists != null){
            return DuringGamePlayRecommender.sendRecs(portfolioExists);
        } else {
            response.setStatus(404);
            return ResponseMessage.builder().message("Portfolio with that portfolioID does not exist").build();
        }
    }
}
