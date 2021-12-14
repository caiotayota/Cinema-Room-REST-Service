package com.caiotayota.cinema.repositories;

import com.caiotayota.cinema.entities.Seat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CinemaRepository {
    private static final int TOTAL_ROWS = 9;
    private static final int TOTAL_COLUMNS = 9;
    private static final int PREMIUM_SEAT_PRICE = 10;
    private static final int REGULAR_SEAT_PRICE = 8;
    private static final Map<String, Seat> PURCHASED_TICKETS;
    private static final List<Seat> SEATS;
    
    static {
        PURCHASED_TICKETS = new HashMap<>();
        SEATS = new ArrayList<>();
        for (int row = 1; row <= TOTAL_ROWS; row++) {
            for (int column = 1; column <= TOTAL_COLUMNS; column++) {
                SEATS.add(new Seat(
                        row, column,
                        row <= 4 ? PREMIUM_SEAT_PRICE : REGULAR_SEAT_PRICE
                        , true
                        )
                );
            }
        }
    }
    
    public int getTotalRows() {
        return TOTAL_ROWS;
    }
    
    public int getTotalColumns() {
        return TOTAL_COLUMNS;
    }
    
    public List<Seat> getAllSeats() {
        return SEATS;
    }
    
    public Seat getSeat(String token) {
        return PURCHASED_TICKETS.get(token);
    }
    
    public Map<String, Object> saveTicket(String token, Seat seat) {
        PURCHASED_TICKETS.put(token, seat);
        return Map.of("token", token, "ticket", seat);
    }
    
    public void removeTicket(String token) {
        PURCHASED_TICKETS.remove(token);
    }
    
    public boolean existsTicket(String token) {
        return PURCHASED_TICKETS.containsKey(token);
    }
    
    public int getIncome() {
        return PURCHASED_TICKETS.values()
                .stream()
                .mapToInt(Seat::getPrice)
                .sum();
    }
    
    public int getAvailableSeats() {
        return SEATS.size() - PURCHASED_TICKETS.size();
    }
    
    public int getPurchasedTickets() {
        return PURCHASED_TICKETS.size();
    }
}
