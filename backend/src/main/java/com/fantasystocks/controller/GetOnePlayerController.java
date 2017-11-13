package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetPlayerResponse;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class GetOnePlayerController {
    @Autowired
    private PlayerService playerService;

    @ResponseBody
    @RequestMapping(value = "/players", params = "playerName", method = RequestMethod.GET)
    public Object getOnePlayer(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam String playerName) {
        log.info("Getting info for player with playerName: {}", playerName);

        //Check to make sure that this player exists and if so return it
        Player checkPlayerExists = playerService.get(playerName);
        if (checkPlayerExists != null) {
            return GetPlayerResponse.builder()
                    .playeName(checkPlayerExists.getPlayerName())
                    .build();
        } else {
            response.setStatus(400);
            return ResponseMessage.builder().message("Player does not exist").build();
        }
    }

    //@ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
