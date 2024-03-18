package com.hotel.service.app.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "hotels")
public class Hotel {
    @Id
    private String id;
    private String name;
    private Address address;
}
