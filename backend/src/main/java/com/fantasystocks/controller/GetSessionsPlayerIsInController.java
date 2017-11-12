package com.fantasystocks.controller;

import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Game;
import com.fantasystocks.service.model.PlayerInGameService;
import com.fantasystocks.service.model.GameService;
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
public class GetSessionsPlayerIsInController {
    @Autowired
    private PlayerInGameService pisService;
    @Autowired
    private GameService gameService;

    @ResponseBody
    @RequestMapping(value = "/sessions", params = "playerName", method = RequestMethod.GET)
    public Object getAll( HttpServletRequest request,
                                     HttpServletResponse response, @RequestParam("playerName") String playerName) {
        log.debug("/sessions?playerName="+playerName+ ". Getting sessions the following player is in: " + playerName);

        //Check to make sure that this player exists and if so return it
        List<Long> allSessionIds = pisService.getAll(playerName);
        List<Game> allGames = new ArrayList<>();
        if (allSessionIds.size() == 0) {
            response.setStatus(404);
            return ResponseMessage.builder().message("Player currently not in any sessions").build();
        } else {
            for (int i = 0; i < allSessionIds.size(); i++){
                allGames.add(gameService.get(allSessionIds.get(i)));
            }
        }
        return allGames;
    }

    @ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
