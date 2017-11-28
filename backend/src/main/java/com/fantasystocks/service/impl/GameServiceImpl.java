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

    @Transactional(readOnly = true)
    @Override
    public List<Session> getAllSessions(String playerName) {
        List<PlayerInGame> playerInGames = playerInGameDao.getGamesForPlayer(playerName);

        return playerInGames.stream().map(playerInGame -> {
            long sessionId = playerInGame.getGame().getGameId();
            return createSessionAPI(sessionId);
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Session getSessionAPI(long sessionId) {
        return createSessionAPI(sessionId);
    }

    private Session createSessionAPI(long sessionId) {
        Game game = sessionDao.get(sessionId);
        String gameName = game.getGameName();
        Session session = Session.builder()
                .sessionId(sessionId)
                .sessionName(gameName)
                .players(new ArrayList<>())
                .portfolios(new HashMap<>())
                .matchupIds(new ArrayList<>())
                .playerScores(new HashMap<>())
                .build();

        List<PlayerInGame> playerInGames = sessionDao.getAllPlayerInGame(sessionId);
        playerInGames.forEach(playerInGame -> {
            String playerName = playerInGame.getPlayer().getPlayerName();
            session.getPlayers().add(playerName);
            Long portfolioId = playerInGame.getPortfolio().getPortfolioId();
            session.getPortfolios().putIfAbsent(playerName, portfolioId);
        });

        return session;
    }
}
