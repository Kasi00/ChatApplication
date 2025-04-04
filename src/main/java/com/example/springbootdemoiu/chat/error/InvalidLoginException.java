package com.example.springbootdemoiu.chat.error;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
