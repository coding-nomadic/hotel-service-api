package com.example.services.models;

import com.example.services.constants.ReservationStatus;
import com.example.services.constants.RoomType;
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
