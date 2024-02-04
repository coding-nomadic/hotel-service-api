package com.example.services.entities;

import com.example.services.constants.RoomType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection = "rates")
@Data
public class RateCard {
    @Id
    private String id;
    private String hotelId;
    private RoomType roomType;
    private Timestamp date;
    private double price;
}