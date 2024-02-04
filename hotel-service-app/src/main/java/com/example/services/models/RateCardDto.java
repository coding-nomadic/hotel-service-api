package com.example.services.models;

import com.example.services.constants.RoomType;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class RateCardDto {
    private String id;
    private String hotelId;
    private RoomType roomType;
    private Timestamp date;
    private double price;
}
