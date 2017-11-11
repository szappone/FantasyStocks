package com.fantasystocks.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Builder
@Entity
@Table(name = "PlayerInSession")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class PlayerInSession {
    private static final int MAX_USER_CHARACTERS = 30;
    private static final int MIN_USER_CHARACTERS = 5;
    private static final String USER_NAME_ERROR_MSG =
            "Username must be between " + MIN_USER_CHARACTERS + " and " + MAX_USER_CHARACTERS + ".";
    private static final int MAX_NAME_CHARACTERS = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pisID")
    private long pisId;

    @NonNull
    @Column(name = "playerName")
    @Size(max = MAX_USER_CHARACTERS, min = MIN_USER_CHARACTERS, message = USER_NAME_ERROR_MSG)
    private String playerName;

    @NonNull
    @Column(name = "sessionID")
    private long sessionID;


}
