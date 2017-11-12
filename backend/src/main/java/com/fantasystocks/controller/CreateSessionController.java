package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreateSessionRequest;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.service.model.GameService;
import com.fantasystocks.service.model.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@Slf4j
public class CreateSessionController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;

    @ResponseBody
    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public Object createSession(@RequestBody CreateSessionRequest body,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        log.info("/game. Adding game ... " + body.toString());

        //Check if all players already exist
        String[] playerNames = body.getPlayers();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNames.length ;i++){
            Player p = playerService.get(playerNames[i]);
            players.add(p);
            if (p == null) {
                response.setStatus(400);
                return ResponseMessage
                        .builder()
                        .message("Not all player names in game are registered as players")
                        .build();
            }
        }
        //Create and store game
        //TODO: properly enter players and portfolio into db.
        Game game = Game
                .builder()
                .gameName(body.getSessionName())
                //.players(/*new HashSet<>(players)*/ new HashSet<>())
                .build();
        gameService.add(game);

        //Create and store PlayerInGame objects

        /*
        TODO: Properly add playerInGame Portfolio.
        long sessionID = game.getGameId();
        for (int i = 0; i < playerNames.length ;i++){
            PlayerInGame pis = PlayerInGame
                    .builder()
                    .id(sessionID)
                    .playerName(playerNames[i])
                    .build();
            pisService.add(pis);
        }*/
        return game;
    }

    @ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }
}
