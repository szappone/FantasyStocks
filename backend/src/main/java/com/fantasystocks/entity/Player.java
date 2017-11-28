package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Players")
@Data
@EqualsAndHashCode(exclude = "sessions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @NonNull
    @Column(name = "player_name", unique = true)
    @Id
    @Access(AccessType.PROPERTY)
    private String playerName;

    @NonNull
    @Builder.Default
    @OneToMany( mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PlayerInGame> sessions = new HashSet<>();

    public void addSession(Game game) {
        PlayerInGame playerInGame = PlayerInGame
                .builder()
                .player(this)
                .game(game)
                .portfolio(Portfolio.builder().build())
                .build();
        sessions.add(playerInGame);
        game.getPlayers().add(playerInGame);
    }

    //TODO: Properly add portfolio info.
    public void addSession(Game game, Portfolio portfolio) {
        PlayerInGame playerInGame = PlayerInGame
                .builder()
                .player(this)
                .game(game)
                .build();
        sessions.add(playerInGame);
        game.getPlayers().add(playerInGame);
    }

    public void removeSession(Game game) {
        sessions.removeIf(playerInSession ->  {
            if (playerInSession.getGame().equals(game)) {
                playerInSession.getGame().getPlayers().remove(playerInSession);
                playerInSession.setPlayer(null);
                playerInSession.setGame(null);
                return true;
            }
            return false;
        });
    }
}
