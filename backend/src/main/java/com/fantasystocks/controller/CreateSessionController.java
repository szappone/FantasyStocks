package com.fantasystocks.controller;

import com.fantasystocks.controller.api.CreateSessionRequest;
import com.fantasystocks.controller.api.ResponseMessage;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.service.model.GameService;
import com.fantasystocks.service.model.PlayerService;
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
        List<String> playerNames = body.getPlayers();
        List<Player> players = verifyPlayers(playerNames);
        //Create a new game
        Game game = Game
                .builder()
                .gameName(body.getSessionName())
                .build();
        gameService.add(game);
        // Add Players to game.
        players.forEach(player -> playerService.addToSession(player, game));

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

        //TODO: Properly get all session info (player objects + portfolios).
        return null;
    }

    private List<Player> verifyPlayers(List<String> playerNames) throws PlayerNotFoundException {
        List<Player> players = new ArrayList<>();
        for (String pn: playerNames) {
            Player p = playerService.get(pn);
            if (p == null)
                throw new PlayerNotFoundException("");
            players.add(p);
        }
        return players;
    }

    //@ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(400);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseMessage handlePlayerNotFound(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " threw " + ex);
        response.setStatus(404);
        return ResponseMessage.builder().message(GameErrMsgs.PLAYER_NOT_FOUND).build();
    }

    private static class PlayerNotFoundException extends Exception {
        public PlayerNotFoundException(String message) {
            super(message);
        }
    }
}
