package com.example.services.models;

import com.example.services.constants.RoomType;
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
