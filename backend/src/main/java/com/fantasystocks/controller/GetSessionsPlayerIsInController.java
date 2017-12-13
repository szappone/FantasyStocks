package com.fantasystocks.controller;

import com.fantasystocks.service.model.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@CrossOrigin
public class GetSessionsPlayerIsInController extends ControllerErrorHandler {
    @Autowired
    private GameService gameService;


    @ResponseBody
    @RequestMapping(value = "/sessions", params = "playerName", method = RequestMethod.GET)
    public Object getAll(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam("playerName") String playerName) {
        log.debug("/sessions?playerName="+playerName+ ". Getting sessions the following player is in: " + playerName);

        //Check to make sure that this player exists and if so return it
        return gameService.getAllSessions(playerName);
    }
}
