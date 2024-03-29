package com.hotel.service.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DuplicateReservationException extends RuntimeException {

    public DuplicateReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateReservationException(String message, String errorCode) {
        super(message);
    }
}