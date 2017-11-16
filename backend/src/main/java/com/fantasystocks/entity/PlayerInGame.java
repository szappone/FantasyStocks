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
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId
    private Game game;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Portfolio portfolio;
}