package com.example.services.controllers;

import com.example.services.models.ReservationDto;
import com.example.services.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/reservations")
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping(value = "/reservations/{reservationId}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable String id) {
        ReservationDto reservationDto = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationDto);
    }

    @GetMapping(value = "/reservations/{hotelId}")
    public ResponseEntity<ReservationDto> getReservationByHotelId(@PathVariable String hotelId) {
        ReservationDto reservationDto = reservationService.getReservationByHotelId(hotelId);
        return ResponseEntity.ok(reservationDto);
    }

    @GetMapping(value = "/reservations/{userId}")
    public ResponseEntity<ReservationDto> getReservationByUserId(@PathVariable String userId) {
        ReservationDto reservationDto = reservationService.getReservationByUserId(userId);
        return ResponseEntity.ok(reservationDto);
    }

    @PostMapping(value = "/reservations")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @PutMapping(value = "/reservations/{reservationId}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable String id, @RequestBody ReservationDto reservationDto) {
        ReservationDto updatedReservation = reservationService.updateReservation(id, reservationDto);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping(value = "/reservations/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
