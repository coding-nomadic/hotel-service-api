package com.hotel.service.app.controllers;

import com.hotel.service.app.models.RoomDto;
import com.hotel.service.app.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/hotels/{hotelId}/rooms")
    public List<RoomDto> getAllRooms(String hotelId) {
        return roomService.getAllRooms(hotelId);
    }

    @GetMapping(value = "/hotels/{hotelId}/rooms/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable String hotelId, @PathVariable String id) {
        RoomDto room = roomService.getRoomById(hotelId, id);
        return room != null ? ResponseEntity.ok(room) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/hotels/{hotelId}/rooms")
    public ResponseEntity<RoomDto> createRoom(@PathVariable String hotelId, @RequestBody RoomDto roomDto) {
        RoomDto createdRoom = roomService.createRoom(hotelId, roomDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @PutMapping(value = "/hotels/{hotelId}/rooms/{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable String hotelId, @PathVariable String id, @RequestBody RoomDto roomDto) {
        RoomDto updatedRoom = roomService.updateRoom(hotelId, id, roomDto);
        return updatedRoom != null ? ResponseEntity.ok(updatedRoom) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/hotels/{hotelId}/rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String hotelId, @PathVariable String id) {
        roomService.deleteRoom(hotelId, id);
        return ResponseEntity.noContent().build();
    }
}
