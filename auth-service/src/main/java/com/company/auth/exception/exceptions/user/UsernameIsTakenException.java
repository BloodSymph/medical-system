package com.company.auth.exception.exceptions.user;

public class UsernameIsTakenException extends RuntimeException {

    public UsernameIsTakenException(String message) {
        super(message);
    }

}
