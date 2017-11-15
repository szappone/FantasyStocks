package com.fantasystocks.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PlayerInGame")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PlayerInGameId.class)
public class PlayerInGame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_in_game_id")
    private long playerInGameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @Column(name = "played_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @Column(name = "game_id")
    private Game game;

    @OneToOne(cascade=CascadeType.ALL)
    private Portfolio portfolio;
}