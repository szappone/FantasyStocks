package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Builder
@Entity
@Table(name = "Sessions")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Session {
    private static final int MAX_USER_CHARACTERS = 30;
    private static final int MIN_USER_CHARACTERS = 5;
    private static final String USER_NAME_ERROR_MSG =
            "Session name must be between " + MIN_USER_CHARACTERS + " and " + MAX_USER_CHARACTERS + ".";
    private static final int MAX_NAME_CHARACTERS = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sessionID")
    private long sessionId;

    @NonNull
    @Column(name = "session_name")
    @Size(max = MAX_NAME_CHARACTERS, message = "Name is too long.")
    private String sessionName;

    @NonNull
    @Column(name = "players")
    @Size(max = MAX_NAME_CHARACTERS, message = "Name is too long.")
    private String[] players;

}
