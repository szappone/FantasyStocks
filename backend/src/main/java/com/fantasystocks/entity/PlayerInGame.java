package com.fantasystocks.entity;

import lombok.*;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Game game;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Portfolio portfolio;
}