package com.hotel.service.app.entities;

import com.hotel.service.app.constants.RoomType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
    private String hotelId;
    private String description;
    private RoomType roomType;
    private int floorNumber;
    private int roomNumber;
    private boolean isAvailable;
}
