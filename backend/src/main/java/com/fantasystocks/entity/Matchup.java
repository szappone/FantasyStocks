package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Matchup")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matchup {
    @Id
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchup_id")
    private long matchupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Game game;

    @Column(name = "player1Name")
    private String player1Name;

    @Column(name = "player1Name")
    private String player2Name;

    @Column(name = "p1Score")
    private long p1Score;

    @Column(name = "p2Score")
    private long p2Score;

    @Column(name = "activeWeek")
    private long activeWeek;
}
