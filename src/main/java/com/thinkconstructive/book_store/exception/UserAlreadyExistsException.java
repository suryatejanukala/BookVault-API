package com.thinkconstructive.book_store.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userName) {
        super("User already exists with username: " + userName);
    }
}
