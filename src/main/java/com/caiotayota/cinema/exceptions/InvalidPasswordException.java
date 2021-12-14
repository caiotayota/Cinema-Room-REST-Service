package com.caiotayota.cinema.exceptions;

public class InvalidPasswordException extends RuntimeException {
    
    public InvalidPasswordException() {
        super("The password is wrong!");
    }
}
