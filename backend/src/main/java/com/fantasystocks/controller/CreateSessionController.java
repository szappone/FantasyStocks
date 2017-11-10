package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreateSessionRequest;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInSession;
import com.fantasystocks.entity.Session;
import com.fantasystocks.service.model.PlayerInSessionService;
import com.fantasystocks.service.model.PlayerService;
import com.fantasystocks.service.model.SessionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j2
public class CreateSessionController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private PlayerInSessionService pisService;

    @ResponseBody
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public Object createSession(@RequestBody CreateSessionRequest body,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        log.info("/session. Adding session ... " + body.toString());

        //Check if all players already exist
        String[] playerNames = body.getPlayers();
        for (int i = 0; i < playerNames.length ;i++){
            Player p = playerService.get(playerNames[i]);
            if (p == null) {
                handleError(request, response, new Exception("Not all player names in session are registered as players"));
                return ResponseMessage.builder().message("Not all player names in session are registered as players").build();
            }
        }
        //Create and store session
        Session session = Session
                .builder()
                .sessionName(body.getSessionName())
                .players(playerNames)
                .build();
        sessionService.add(session);

        //Create and store PlayerInSession objects
        long sessionID = session.getSessionId();
        for (int i = 0; i < playerNames.length ;i++){
            PlayerInSession pis = PlayerInSession
                    .builder()
                    .sessionID(sessionID)
                    .playerName(playerNames[i])
                    .build();
            pisService.add(pis);

        }
        return session;
    }

    @ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);

    }
}
