package com.hotel.service.app.models;

import com.hotel.service.app.constants.ReservationStatus;
import com.hotel.service.app.constants.RoomType;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Data
public class ReservationDto {
    private String id;
    private String hotelId;
    private String userId;
    private Map<RoomType, Integer> roomsBooked;
    private ReservationStatus status;
    private Timestamp startDate;
    private Timestamp endDate;
}
