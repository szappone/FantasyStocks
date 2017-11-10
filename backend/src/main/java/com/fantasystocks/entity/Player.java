package com.fantasystocks.entity;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Builder
@Entity
@Table(name = "Players")
@NoArgsConstructor
@RequiredArgsConstructor
public class Player {
    private static final int MAX_USER_CHARACTERS = 30;
    private static final int MIN_USER_CHARACTERS = 5;
    private static final String USER_NAME_ERROR_MSG =
            "Username must be between " + MIN_USER_CHARACTERS + " and " + MAX_USER_CHARACTERS + ".";
    private static final int MAX_NAME_CHARACTERS = 30;


    @NonNull
    @Column(name = "playerName", unique = true)
    @Size(max = MAX_USER_CHARACTERS, min = MIN_USER_CHARACTERS, message = USER_NAME_ERROR_MSG)
    @Id
    private String playerName;

}
