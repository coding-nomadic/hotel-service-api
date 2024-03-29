package com.hotel.service.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelNotFoundException(String message, String errorCode) {
        super(message);
    }
}