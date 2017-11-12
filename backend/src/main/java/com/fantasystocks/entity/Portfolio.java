package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Builder
@Entity
@Table(name = "Portfolios")
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolioID")
    private long portfolioId;

    @Column(name = "longs")
    private String[] longs;

    @Column(name = "shorts")
    private String[] shorts;

    @Column(name = "bench")
    private String[] bench;

}
