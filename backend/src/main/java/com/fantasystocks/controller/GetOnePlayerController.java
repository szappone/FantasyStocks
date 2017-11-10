package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreatePlayerRequest;
import com.fantasystocks.controller.api.ResponseMessage;
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
public class GetOnePlayerController {
    @Autowired
    private PlayerService playerService;

    @ResponseBody
    @RequestMapping(value = "/player", params = "name", method = RequestMethod.GET)
    public Object getPlayer( HttpServletRequest request,
                                     HttpServletResponse response, @RequestParam("name") String playerName) {
        log.info("/player?name="+playerName+ ". Getting player: " + playerName);

        //Check to make sure that this player exists and if so return it
        Player checkPlayerExists = playerService.get(playerName);
        if (checkPlayerExists != null) {
            return checkPlayerExists;
        } else {
            handleError(request, response, new Exception("Player does not exist"));
            return ResponseMessage.builder().message("Player does not exist").build();
        }
    }

    @ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
