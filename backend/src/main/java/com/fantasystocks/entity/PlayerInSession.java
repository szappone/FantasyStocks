package com.fantasystocks.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "PlayerInSession")
@Table(name = "Player_In_Session")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInSession {
    @EmbeddedId
    private PlayerInSessionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("PlayerId")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sessionId")
    private Session session;
}