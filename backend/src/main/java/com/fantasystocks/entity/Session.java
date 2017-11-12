package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sessions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private long sessionId;

    @NonNull
    @Column(name = "session_name")
    @Size(max = EntityStd.MAX_USER_CHARACTERS, message = EntityStd.SESSION_NAME_ERROR_MSG)
    private String sessionName;

    @NonNull
    @Builder.Default
    @OneToMany(
            mappedBy = "session",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PlayerInSession> players = new HashSet<>();

}
