package com.fantasystocks.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Portfolios")
@Data
@Builder
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private long portfolioId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private PlayerInGame playerInGame;

    @Builder.Default
    @ElementCollection
    @Column(name = "offense")
    private List<Stock> offense = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @Column(name = "defense")
     private List<Stock> defense = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @Column(name = "bench")
    private List<Stock> bench = new ArrayList<>();
}
