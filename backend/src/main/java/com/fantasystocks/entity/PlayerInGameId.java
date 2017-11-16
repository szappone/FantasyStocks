package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInGameId implements Serializable {
    private String player;
    private Long game;
}
