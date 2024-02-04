package com.example.services.models;

import com.example.services.entities.Address;
import lombok.Builder;
import lombok.Data;

@Data
public class HotelDto {
    private String id;
    private String name;
    private AddressDto address;
}
