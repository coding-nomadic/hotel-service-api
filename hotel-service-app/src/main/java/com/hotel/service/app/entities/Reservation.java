package com.hotel.service.app.entities;

import com.hotel.service.app.constants.ReservationStatus;
import com.hotel.service.app.constants.RoomType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Map;

@Data
@Document(collection = "reservations")
public class Reservation {
    @Id
    private String id;
    private String hotelId;
    private String userId;
    private Map<RoomType, Integer> roomsBooked;
    private ReservationStatus status;
    private Timestamp startDate;
    private Timestamp endDate;
}
