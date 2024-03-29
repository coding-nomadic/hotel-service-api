package com.hotel.service.app.exceptions;


import com.hotel.service.app.dto.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // handle specific exceptions
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
        logger.info(exception.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleHotelNotFoundException(HotelNotFoundException exception, WebRequest webRequest) {
        logger.info(exception.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleReservationNotFoundException(ReservationNotFoundException exception, WebRequest webRequest) {
        logger.info(exception.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllException(Exception exception, WebRequest webRequest) {
        logger.info(exception.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateReservationException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateReservationException(DuplicateReservationException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // handle specific exceptions
    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorDetails> handleMethodNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
        logger.info(exception.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }
}