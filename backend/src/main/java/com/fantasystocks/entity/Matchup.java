package com.fantasystocks.entity;

import lombok.*;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;

@Entity
@Table(name = "Matchup")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matchup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchup_id")
    @Access(AccessType.PROPERTY)
    private long matchupId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @Column(name = "player1Name")
    private String player1Name;

    @Column(name = "player2Name")
    private String player2Name;

    @Column(name = "p1Score")
    private long p1Score;

    @Column(name = "p2Score")
    private long p2Score;

    @Column(name = "activeWeek")
    private long activeWeek;
}
