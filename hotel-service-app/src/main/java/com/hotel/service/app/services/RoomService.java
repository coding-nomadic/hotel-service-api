package com.hotel.service.app.services;

import com.hotel.service.app.constants.ServiceConstants;
import com.hotel.service.app.entities.Room;
import com.hotel.service.app.exceptions.HotelNotFoundException;
import com.hotel.service.app.exceptions.RoomNotFoundException;
import com.hotel.service.app.models.RoomDto;
import com.hotel.service.app.repositories.HotelRepository;
import com.hotel.service.app.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ModelMapper modelMapper;

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
                .orElseThrow(() -> new RoomNotFoundException(ServiceConstants.ROOM_NOT_FOUND, ServiceConstants.ERROR_CODE));
    }

    private void validateHotelExists(String hotelId) {
        hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, ServiceConstants.ERROR_CODE));
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
