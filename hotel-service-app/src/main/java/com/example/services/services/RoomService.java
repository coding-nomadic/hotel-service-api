package com.example.services.services;

import com.example.services.constants.ServiceConstants;
import com.example.services.entities.Room;
import com.example.services.exceptions.HotelNotFoundException;
import com.example.services.exceptions.RoomNotFoundException;
import com.example.services.models.RoomDto;
import com.example.services.repositories.HotelRepository;
import com.example.services.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    public List<RoomDto> getAllRooms(String hotelId) {
        validateHotelExists(hotelId);
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(this::mapToRoomDto).collect(Collectors.toList());
    }

    public RoomDto getRoomById(String hotelId, String id) {
        validateHotelExists(hotelId);
        Room room = getRoomOrThrow(id);
        return mapToRoomDto(room);
    }

    public RoomDto createRoom(String hotelId, RoomDto roomDto) {
        roomDto.setHotelId(hotelId);
        validateHotelExists(hotelId);
        Room room = modelMapper.map(roomDto, Room.class);
        Room savedRoom = roomRepository.save(room);
        return mapToRoomDto(savedRoom);
    }

    public RoomDto updateRoom(String hotelId, String id, RoomDto roomDto) {
        roomDto.setHotelId(hotelId);
        validateHotelExists(hotelId);
        Room existingRoom = getRoomOrThrow(id);
        updateRoomFields(existingRoom, roomDto);
        Room updatedRoom = roomRepository.save(existingRoom);
        return mapToRoomDto(updatedRoom);
    }

    public void deleteRoom(String hotelId, String id) {
        validateHotelExists(hotelId);
        roomRepository.deleteById(id);
    }

    private RoomDto mapToRoomDto(Room room) {
        return modelMapper.map(room, RoomDto.class);
    }

    private Room getRoomOrThrow(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(ServiceConstants.ROOM_NOT_FOUND, "102"));
    }

    private void validateHotelExists(String hotelId) {
        hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, "102"));
    }

    private void updateRoomFields(Room room, RoomDto roomDto) {
        room.setId(roomDto.getId());
        room.setHotelId(roomDto.getHotelId());
        room.setRoomType(roomDto.getRoomType());
        room.setAvailable(roomDto.isAvailable());
        room.setDescription(roomDto.getDescription());
        room.setFloorNumber(roomDto.getFloorNumber());
        room.setRoomNumber(roomDto.getRoomNumber());
    }
}
