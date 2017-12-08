package com.fantasystocks.entity.integration;

import com.fantasystocks.entity.*;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PortfoliosAndGameIntegrationTest extends IntegrationTestScaffold {
    private static ImmutableList<String> offenseTest = ImmutableList.of("GOOGL", "AMZN", "MSFT", "FB", "NVDA");
    private static List<Stock> offense = generateStocksFromSymbols(offenseTest);
    private static ImmutableList<String> defenseTest = ImmutableList.of("TSLA", "NKE", "AAPL", "CAT");
    private static List<Stock> defense = generateStocksFromSymbols(defenseTest);
    private static ImmutableList<String> benchTest = ImmutableList.of("SPY", "SPLG", "VTHR", "IBB", "IGM");
    private static List<Stock> bench = generateStocksFromSymbols(benchTest);

    private static final String gameName = "test_gameName";
    private static final List<String> players = ImmutableList.of("test_playerName1","test_playerName2","test_playerName3");


    private static List<Stock> generateStocksFromSymbols(List<String> symbols) {
        return symbols.stream()
                .map(s -> Stock.builder().ticker(s).build())
                .collect(Collectors.toList());
    }

    @Test
    public void test_addPortfolio() {
        Portfolio expected = buildPortfolio(offenseTest, defenseTest, benchTest);
        Long portfolioId = (Long) session.save(expected);

        Portfolio actual = session.get(Portfolio.class, portfolioId);
        assertEquals("The portfolios are not the same.", expected, actual);
    }

    @Test
    public void test_addGameWithPortfolios() {
        addPlayers(players);
        Game game = buildGame(gameName);
        Long gameId = (Long) session.save(game);
        /*addStocks(benchTest);
        addStocks(defenseTest);
        addStocks(offenseTest);*/
        Portfolio portfolio = Portfolio.builder()
                .bench(benchTest)
                .shorts(defenseTest)
                .longs(offenseTest)
                .build();
        Long portfolioId = (Long) session.save(portfolio);
        Player player1 = session.get(Player.class, players.get(0));
        PlayerInGame playerInGame = PlayerInGame.builder()
                .player(player1)
                .game(game)
                .build();
        PlayerInGameId pigId = (PlayerInGameId) session.save(playerInGame);
        PlayerInGame playerInGameSaved = session.get(PlayerInGame.class, pigId);
        playerInGameSaved.setPortfolio(portfolio);
        session.save(playerInGameSaved);

        PlayerInGame actual = session.get(PlayerInGame.class, pigId);
        assertEquals("The player is not right.", players.get(0), actual.getPlayer().getPlayerName());
        assertEquals("The game is not right.", gameId, (Long) actual.getGame().getGameId());
        assertEquals("The portfolio is not right.", portfolioId, (Long) actual.getPortfolio().getPortfolioId());
    }

    private void addPlayers(List<String> playerNames) {
        playerNames.forEach(p -> {
            Player player = buildPlayer(p);
            session.save(player);
        });
    }

    private void addStocks(List<String> stockTickers) {
        stockTickers.forEach(s -> session.save(Stock.builder().ticker(s).build()));
    }

    private List<Stock> buildStocks(List<String> stockTickers) {
        return stockTickers.stream()
                .map(s -> Stock.builder().ticker(s).build())
                .collect(Collectors.toList());
    }
}
