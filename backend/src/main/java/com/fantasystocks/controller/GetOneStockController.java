package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetPlayerResponse;
import com.fantasystocks.controller.api.GetStockResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.modules.priceCalculator;
import com.fantasystocks.service.model.PlayerService;
import com.fantasystocks.service.model.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@Slf4j
@CrossOrigin
public class GetOneStockController {

    @ResponseBody
    @RequestMapping(value = "/stocks/{stockID}", method = RequestMethod.GET)
    public Object getOnePlayer(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("stockID") String stockID) {
        log.info("Getting info for stock with stockID: {}", stockID);

        String[] allStocks = {"AAPL", "AMZN", "GM", "TRIP", "AMT","LUK","GOOG","IBM","WDC", "C"};
        if (ArrayUtils.contains(allStocks, stockID)) {
             return GetStockResponse.builder()
                    .stockID(stockID)
                    .lastMondayPrice(priceCalculator.getMonday(stockID))
                    .todayPrice(priceCalculator.getCurrentDay(stockID))
                    .build();
        } else {
            return ResponseMessage.builder().message("That stock is not currently supported").build();
        }

    }

    //@ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
