package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Stocks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @NonNull
    @Column(name = "ticker", unique = true)
    @Id
    private String ticker;

    @Column(name = "company_name")
    private String companyName;

}
