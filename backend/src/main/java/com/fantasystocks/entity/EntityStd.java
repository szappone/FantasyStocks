package com.fantasystocks.entity;

class EntityStd {
    static final int MAX_USER_CHARACTERS = 30;
    static final int MIN_USER_CHARACTERS = 5;
    static final String USER_NAME_ERROR_MSG =
            "Username must be between " + MIN_USER_CHARACTERS + " and " + MAX_USER_CHARACTERS + ".";

    static final int MAX_NAME_CHARACTERS = 30;
    static final String SESSION_NAME_ERROR_MSG = 
            "Session name must be shorter than " + MAX_NAME_CHARACTERS + " characters.";
}
