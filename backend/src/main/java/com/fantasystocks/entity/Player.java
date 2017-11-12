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
    private Set<PlayerInSession> sessions = new HashSet<>();

    public void addSession(Session session) {
        PlayerInSession playerInSession = PlayerInSession
                .builder()
                .player(this)
                .session(session)
                .build();
        sessions.add(playerInSession);
        session.getPlayers().add(playerInSession);
    }

    public void removeSession(Session session) {
        sessions.removeIf(playerInSession ->  {
            if (playerInSession.getSession().equals(session)) {
                playerInSession.getSession().getPlayers().remove(playerInSession);
                playerInSession.setPlayer(null);
                playerInSession.setSession(null);
                return true;
            }
            return false;
        });
    }


}
