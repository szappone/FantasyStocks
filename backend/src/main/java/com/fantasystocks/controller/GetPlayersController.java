package com.fantasystocks.controller;

import com.fantasystocks.entity.Player;
import com.fantasystocks.service.impl.PlayerServiceImpl;
import com.fantasystocks.service.model.PlayerService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Log4j2
public class GetPlayersController {
    @Autowired
    private PlayerService playerService;

    @ResponseBody
    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<Player> getPlayers(HttpServletRequest request) {
        log.info("/players. getPlayers() Called.");
        return playerService.listPlayers();
    }

    @ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
