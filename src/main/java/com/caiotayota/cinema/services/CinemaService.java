package com.caiotayota.cinema.services;

import com.caiotayota.cinema.entities.Cinema;
import com.caiotayota.cinema.entities.CinemaStatistics;
import com.caiotayota.cinema.entities.Seat;
import com.caiotayota.cinema.entities.Token;
import com.caiotayota.cinema.exceptions.InvalidPasswordException;
import com.caiotayota.cinema.exceptions.SeatOutOfBoundException;
import com.caiotayota.cinema.exceptions.TicketAlreadyPurchasedException;
import com.caiotayota.cinema.exceptions.WrongTokenException;
import com.caiotayota.cinema.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CinemaService {
    
    private static final String PASSWORD = "super_secret";
    
    private final CinemaRepository cinemaRepo;
    
    @Autowired
    public CinemaService(CinemaRepository seatRepo) {
        this.cinemaRepo = seatRepo;
    }
    
    public Cinema getCinemaInfo() {
        return new Cinema(cinemaRepo.getTotalRows(), cinemaRepo.getTotalColumns(), cinemaRepo.getAllSeats());
    }
    
    public Map<String, Object> generateTicket(Seat seatToBook) {
        if (!existsSeat(seatToBook))
            throw new SeatOutOfBoundException();
    
        Seat seat = cinemaRepo
                .getAllSeats()
                .stream()
                .filter(s -> s.getRow() == seatToBook.getRow()
                        && s.getColumn() == seatToBook.getColumn()
                        && s.isAvailable())
                .findAny()
                .orElseThrow(TicketAlreadyPurchasedException::new);
    
        seat.setAvailable(false);
        return cinemaRepo.saveTicket(new Token().toString(), seat);
        
    }
    
    public Map<String, Seat> returnTicket(Token token) {
    
        if (!cinemaRepo.existsTicket(token.getToken()))
            throw new WrongTokenException();
    
        Seat seatToSetAvailable = cinemaRepo.getSeat(token.getToken());
    
        Seat seat = cinemaRepo
                .getAllSeats()
                .stream()
                .filter(s -> s.getRow() == seatToSetAvailable.getRow()
                        && s.getColumn() == seatToSetAvailable.getColumn())
                .findAny()
                .orElseThrow(WrongTokenException::new);
    
        seat.setAvailable(true);
        cinemaRepo.removeTicket(token.getToken());
        return Map.of("returned_ticket", seat);
    }
    
    
    public boolean existsSeat(Seat seat) {
        return seat.getRow() <= cinemaRepo.getTotalRows()
                && seat.getColumn() <= cinemaRepo.getTotalColumns()
                && seat.getRow() > 0
                && seat.getColumn() > 0;
    }
    
    public CinemaStatistics getStatistics(String password){
        if (!isValidPassword(password))
            throw new InvalidPasswordException();
        
        return CinemaStatistics.builder()
                .currentIncome(cinemaRepo.getIncome())
                .numberOfAvailableSeats(cinemaRepo.getAvailableSeats())
                .numberOfPurchasedTickets(cinemaRepo.getPurchasedTickets())
                .build();
    }
    
    public boolean isValidPassword(String password) {
        return PASSWORD.equals(password);
    }
}
