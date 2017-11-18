package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetPlayersResponse;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.source.spi.PluralAttributeElementNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@CrossOrigin
public class GetPlayersController {
    @Autowired
    private PlayerService playerService;

    @ResponseBody
    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public GetPlayersResponse getPlayers(HttpServletRequest request) {
        log.info("/players. getPlayers() Called.");
        List<Player> players = playerService.listPlayers();
        List<String> playerNames = players.stream().map(Player::getPlayerName).collect(Collectors.toList());

        return GetPlayersResponse.builder().playerNames(playerNames).build();
    }

    //@ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
