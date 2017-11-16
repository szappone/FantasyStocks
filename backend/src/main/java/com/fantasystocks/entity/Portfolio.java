package com.fantasystocks.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Portfolios")
@Getter
@Setter
@EqualsAndHashCode(exclude="playerInGame")
@Builder
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    @Access(AccessType.PROPERTY)
    private long portfolioId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "player_id"),
            @JoinColumn(name = "game_id")
    })
    private PlayerInGame playerInGame;

    @Builder.Default
    @OneToMany
    @JoinTable(name="offense_stocks")
    @Column(name = "offense")
    private List<Stock> offense = new ArrayList<>();

    @Builder.Default
    @OneToMany
    @JoinTable(name="defense_stocks")
    @Column(name = "defense")
     private List<Stock> defense = new ArrayList<>();

    @Builder.Default
    @OneToMany
    @JoinTable(name="bench_stocks")
    @Column(name = "bench")
    private List<Stock> bench = new ArrayList<>();
}
