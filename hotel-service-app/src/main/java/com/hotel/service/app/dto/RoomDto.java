package com.hotel.service.app.dto;

import com.hotel.service.app.constants.RoomType;
import lombok.Data;

@Data
public class RoomDto {
    private String id;
    private String hotelId;
    private String description;
    private RoomType roomType;
    private int floorNumber;
    private int roomNumber;
    private boolean isAvailable;
}
