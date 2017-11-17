package com.fantasystocks.entity.integration;

import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInGame;
import org.hibernate.StatelessSession;
import org.hibernate.query.Query;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerAndGameIntegrationTest extends IntegrationTestScaffold {
    private static final String gameName = "test_gameName";
    private static final String playerName = "test_playerName";

    @Test
    public void test_AddPlayerToDB() {
        StatelessSession session = sessionFactory.openStatelessSession();
        session.insert(buildPlayer(playerName));

        Player player = (Player) session.get(Player.class, playerName);
        assertEquals("PlayerName is not correct.", playerName, player.getPlayerName());
        sessionFactory.close();
    }

    @Test
    public void test_AddGameToDB() {
        Game game = buildGame(gameName);
        Long id = (Long) session.save(game);

        Game gameDB = session.get(Game.class, id);
        assertEquals("The gameIDs do not match.", (long) id, gameDB.getGameId());
        assertEquals("The game names do not match.", gameName, gameDB.getGameName());
    }

    @Test
    public void test_AddGameWithPlayers() {
        Player player = buildPlayer(playerName);
        session.save(player);
        Game game = buildGame(gameName);
        Long gameId = (Long) session.save(game);

        PlayerInGame playerInGame = PlayerInGame.builder()
                .player(player)
                .game(game)
                .build();
        player.addSession(game, null);
        session.save(player);

        Game storedGame = session.byId(Game.class).load(gameId);
        assertEquals("Game names are not the same.", gameName, storedGame.getGameName());
        Player storedPlayer = session.byId(Player.class).load(playerName);
        assertEquals("PlayerName is not correct.", playerName, storedPlayer.getPlayerName());

        Query query = session
                .createQuery("FROM PlayerInGame p WHERE p.player.playerName=:pid AND p.game.gameId=:gid")
                .setParameter("pid", playerName)
                .setParameter("gid", gameId);
        PlayerInGame playerInGameStored = (PlayerInGame) query.getSingleResult();
        assertEquals("The PlayerInGame objects are not equal. ", playerInGame, playerInGameStored);
    }
}
