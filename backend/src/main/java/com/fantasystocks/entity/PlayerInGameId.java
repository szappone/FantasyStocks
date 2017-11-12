package com.fantasystocks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInGameId implements Serializable {
    @Column(name = "player_id")
    private String playerId;

    @Column(name = "session_id")
    private Long sessionId;
}
