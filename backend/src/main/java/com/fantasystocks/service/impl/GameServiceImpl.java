package com.fantasystocks.service.impl;

import com.fantasystocks.controller.api.Session;
import com.fantasystocks.dao.model.GameDao;
import com.fantasystocks.dao.model.PlayerInGameDao;
import com.fantasystocks.dao.model.PortfolioDao;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.service.model.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
    @Autowired
    private GameDao sessionDao;
    @Autowired
    private PlayerInGameDao playerInGameDao;

    @Transactional
    @Override
    public Long add(Game game) {
        return sessionDao.add(game);
    }

    @Transactional
    @Override
    public void update(Game game) {
        sessionDao.update(game);
    }

    @Transactional
    @Override
    public Game get(long sessionID) {
        return sessionDao.get(sessionID);
    }

    @Transactional(readOnly = true )
    @Override
    public List<Session> getAllSessions(String playerName) {
        List<PlayerInGame> playerInGames = playerInGameDao.getGamesForPlayer(playerName);

        return playerInGames.stream().map(playerInGame -> {
            Game game = sessionDao.get(playerInGame.getGame().getGameId());
            Long sessionId = game.getGameId();
            String sessionName = game.getGameName();
            Map<String, Long> portfolios = new HashMap<>();
            List<String> players = game.getPlayers().stream()
                    .map(pig -> {
                        String pn = pig.getPlayer().getPlayerName();
                        //Long portfolioId = pig.getPortfolio().getPortfolioId();
                        //portfolios.putIfAbsent(pn, portfolioId);
                        return pn;
                    })
                    .collect(Collectors.toList());
            // TODO: Add matchups and PlayerScores.
            return Session.builder()
                    .players(players)
                    .portfolios(portfolios)
                    .sessionId(sessionId)
                    .sessionName(sessionName)
                    .build();
        }).collect(Collectors.toList());
    }
}
