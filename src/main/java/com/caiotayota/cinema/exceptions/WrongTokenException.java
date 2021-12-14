package com.caiotayota.cinema.exceptions;

public class WrongTokenException extends RuntimeException{
    
    public WrongTokenException() {
        super("Wrong token!");
    }
}
