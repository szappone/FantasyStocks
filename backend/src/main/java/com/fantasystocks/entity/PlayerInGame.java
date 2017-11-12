package com.fantasystocks.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "PlayerInGame")
@Table(name = "Player_In_Session")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInGame {
    @EmbeddedId
    private PlayerInGameId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("PlayerId")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sessionId")
    private Game game;

    @OneToOne(mappedBy = "portfolio", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Portfolio portfolio;
}