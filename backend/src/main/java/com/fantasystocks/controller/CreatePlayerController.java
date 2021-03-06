package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreatePlayerRequest;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@CrossOrigin
public class CreatePlayerController extends ControllerErrorHandler {
    @Autowired
    private PlayerService playerService;

    @ResponseBody
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public Object createPlayer(@RequestBody CreatePlayerRequest body,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        log.debug("/player. Adding player ... " + body.toString());

        //Check to make sure that this player name is not already in use
        Player checkPlayerExists = playerService.get(body.getPlayerName());
        if (checkPlayerExists != null) {
            response.setStatus(400);
            return ResponseMessage
                    .builder()
                    .message("Player already exists with that player name")
                    .build();
        }
        Player player = Player
                .builder()
                .playerName(body.getPlayerName())
                .build();

        playerService.add(player);
        return player;
    }
}
