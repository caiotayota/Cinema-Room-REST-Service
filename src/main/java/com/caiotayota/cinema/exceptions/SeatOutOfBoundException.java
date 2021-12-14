package com.caiotayota.cinema.exceptions;

public class SeatOutOfBoundException extends RuntimeException{
    
    public SeatOutOfBoundException() {
        super("The number of a row or a column is out of bounds!");
    }
}
