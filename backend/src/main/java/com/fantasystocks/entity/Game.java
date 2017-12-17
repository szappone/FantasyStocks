package com.fantasystocks.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Games")
@Data
@EqualsAndHashCode(exclude = "players")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private long gameId;

    @NonNull
    @Column(name = "game_name")
    @Size(max = EntityStd.MAX_NAME_CHARACTERS, message = EntityStd.SESSION_NAME_ERROR_MSG)
    private String gameName;

    @NonNull
    @Builder.Default
    @OneToMany( mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PlayerInGame> players = new HashSet<>();

    @Column(name = "current_week")
    private int currentWeek;

}
