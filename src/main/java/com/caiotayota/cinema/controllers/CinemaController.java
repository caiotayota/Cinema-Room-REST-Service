package com.caiotayota.cinema.controllers;

import com.caiotayota.cinema.entities.Cinema;
import com.caiotayota.cinema.entities.CinemaStatistics;
import com.caiotayota.cinema.entities.Seat;
import com.caiotayota.cinema.entities.Token;
import com.caiotayota.cinema.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {
    
    private final CinemaService cinemaService;
    
    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }
    
    @GetMapping("/seats")
    public Cinema getCinemaInfo() {
        return cinemaService.getCinemaInfo();
    }
    
    @PostMapping(path = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> purchaseTicket(@RequestBody Seat seatToBook) {
        return cinemaService.generateTicket(seatToBook);
    }
    
    @PostMapping(path = "/return", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Seat> returnTicket(@RequestBody Token token) {
        return cinemaService.returnTicket(token);
    }
    
    @PostMapping(path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public CinemaStatistics getStatistics(@RequestParam(required = false) String password) {
        return cinemaService.getStatistics(password);
    }
}
