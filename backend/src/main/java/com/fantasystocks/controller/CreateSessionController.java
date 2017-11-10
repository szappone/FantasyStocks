package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreateSessionRequest;
import com.fantasystocks.entity.Session;
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

    @ResponseBody
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public Session createSession(@RequestBody CreateSessionRequest body,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        log.info("/session. Adding session ... " + body.toString());
        Session session = Session
                .builder()
                .sessionName(body.getSessionName())
                .build();

        String[] playerNames = body.getPlayers();
        for (int i = 0; i < playerNames.length ;i++){

        }
        sessionService.add(session);
        return session;
    }

    @ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
