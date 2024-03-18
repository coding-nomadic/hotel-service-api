package com.hotel.service.app.controllers;

import com.hotel.service.app.models.ReservationDto;
import com.hotel.service.app.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

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

    @GetMapping(value = "/cancel/{reservationId}")
    public ResponseEntity<Void>cancelReservation(@PathVariable String id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/complete/{reservationId}")
    public ResponseEntity<Void>completeReservation(@PathVariable String id) {
        reservationService.completedReservation(id);
        return ResponseEntity.ok().build();
    }
}
