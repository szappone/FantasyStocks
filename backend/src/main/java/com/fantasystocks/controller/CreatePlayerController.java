package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreatePlayerRequest;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j2
public class CreatePlayerController {
    @Autowired
    private PlayerService playerService;

    @ResponseBody
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public Player createPlayer(@RequestBody CreatePlayerRequest body,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        log.info("/player. Adding player ... " + body.toString());
        Player player = Player
                .builder()
                .playerName(body.getPlayerName())
                .build();

        playerService.add(player);
        return player;
    }

    @ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
