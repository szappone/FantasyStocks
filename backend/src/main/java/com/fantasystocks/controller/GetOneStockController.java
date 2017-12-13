package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetStockResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Stock;
import com.fantasystocks.modules.priceCalculator;
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
public class GetOneStockController extends ControllerErrorHandler {
    @Autowired
    private StockService stockService;

    @ResponseBody
    @RequestMapping(value = "/stocks/{stockID}", method = RequestMethod.GET)
    public Object getOneStock(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("stockID") String stockID) {
        log.info("Getting info for stock with stockID: {}", stockID);

        Stock stock = stockService.get(stockID);
        if (stock != null) {
             return GetStockResponse.builder()
                    .stockID(stockID)
                     .company_name(stock.getCompanyName())
                    .lastMondayPrice(priceCalculator.getMonday(stockID))
                    .todayPrice(priceCalculator.getCurrentDay(stockID))
                    .build();
        } else {
            return ResponseMessage.builder().message("That stock is not currently supported").build();
        }

    }
}
