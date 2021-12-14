package com.caiotayota.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cinema {
    
    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;
}
