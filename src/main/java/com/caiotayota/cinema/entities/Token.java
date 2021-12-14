package com.caiotayota.cinema.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Token {
    
    private UUID token;
    
    public Token() {
        this.token = UUID.randomUUID();
    }
    
    @Override
    public String toString() {
        return String.valueOf(token);
    }
    
    public String getToken() {
        return token.toString();
    }
}
