package com.fantasystocks.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "Players")
public class Player {
    private static final int MAX_USER_CHARACTERS = 30;
    private static final int MIN_USER_CHARACTERS = 5;
    private static final String USER_NAME_ERROR_MSG =
            "Username must be between " + MIN_USER_CHARACTERS + " and " + MAX_USER_CHARACTERS + ".";
    private static final int MAX_NAME_CHARACTERS = 30;

    @Id
    @GeneratedValue
    @Column(name = "PID")
    private long id;

    @NonNull
    @Column(name = "first_name")
    @Size(max = MAX_NAME_CHARACTERS, message = "Name is too long.")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    @Size(max = MAX_NAME_CHARACTERS, message = "Name is too long.")
    private String lastName;

    @NonNull
    @Column(name = "user_name", unique = true)
    @Size(max = MAX_USER_CHARACTERS, min = MIN_USER_CHARACTERS, message = USER_NAME_ERROR_MSG)
    private String username;

    @NonNull
    @Column(name = "email", unique = true)
    @Email
    private String email;
}
