package com.caiotayota.cinema.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CinemaStatistics {
    
    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;
    
    
    
}
