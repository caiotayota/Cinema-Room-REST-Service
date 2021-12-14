package com.caiotayota.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    public ResponseEntity<CustomErrorResponse> customHandleTicketAlreadyPurchased(Exception e) {
        return new ResponseEntity<>(
                new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage())
                , HttpStatus.BAD_REQUEST
        );
    }
    
    @ExceptionHandler(SeatOutOfBoundException.class)
    public ResponseEntity<CustomErrorResponse> customHandleSeatOutOfBound(Exception e) {
        return new ResponseEntity<>(
                new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage())
                , HttpStatus.BAD_REQUEST
        );
    }
    
    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<CustomErrorResponse> customHandleWrongToken(Exception e) {
        return new ResponseEntity<>(
                new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage())
                , HttpStatus.BAD_REQUEST
        );
    }
    
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<CustomErrorResponse> customHandleInvalidPassword(Exception e) {
        return new ResponseEntity<>(
                new CustomErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage())
                , HttpStatus.UNAUTHORIZED
        );
    }
    
}