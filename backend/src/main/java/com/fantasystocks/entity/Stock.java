package com.fantasystocks.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Stocks")
@Data
@Builder
public class Stock {
    @NonNull
    @Column(name = "ticker", unique = true)
    @Id
    private String ticker;

    @Column(name = "company_name")
    @Size(max = EntityStd.MAX_NAME_CHARACTERS)
    private String companyName;

}
