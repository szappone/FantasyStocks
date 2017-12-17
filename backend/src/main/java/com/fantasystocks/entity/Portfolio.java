package com.fantasystocks.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Portfolios")
@Getter
@Setter
@EqualsAndHashCode(exclude="playerInGame")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    @Access(AccessType.PROPERTY)
    private long portfolioId;

    @Column(name = "longs_ticker")
    @Fetch(FetchMode.SELECT)
    @ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)

    private List<String> longs;

    @Column(name = "shorts_ticker")
    @Fetch(FetchMode.SELECT)
    @ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)
    private List<String> shorts;

    @Column(name = "bench_ticker")
    @Fetch(FetchMode.SELECT)
    @ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)
    private List<String> bench;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "player_id"),
            @JoinColumn(name = "game_id")
    })
    private PlayerInGame playerInGame;


}
