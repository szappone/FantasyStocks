package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetPlayerResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
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
public class GetAllStocksController {
    @Autowired
    private StockService stockService;

    @ResponseBody
    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    public Object getAllStocks(HttpServletRequest request,
                               HttpServletResponse response) {
        log.info("Getting all stocks");

        return stockService.listStockIDs();
    }
}
