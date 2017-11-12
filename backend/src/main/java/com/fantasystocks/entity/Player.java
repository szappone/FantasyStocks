package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Players")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @NonNull
    @Column(name = "username", unique = true)
    @Size(max = EntityStd.MAX_USER_CHARACTERS,  min = EntityStd.MIN_USER_CHARACTERS,
          message = EntityStd.USER_NAME_ERROR_MSG)
    @Id
    private String username;

    @NonNull
    @Builder.Default
    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PlayerInGame> sessions = new HashSet<>();

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
