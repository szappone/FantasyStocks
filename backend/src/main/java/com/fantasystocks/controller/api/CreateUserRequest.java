package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
