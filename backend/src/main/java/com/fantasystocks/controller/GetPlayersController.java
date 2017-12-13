package com.fantasystocks.controller;

import com.fantasystocks.controller.api.GetPlayersResponse;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@CrossOrigin
public class GetPlayersController extends ControllerErrorHandler {
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
}
