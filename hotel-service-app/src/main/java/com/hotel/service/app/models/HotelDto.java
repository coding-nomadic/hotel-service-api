package com.hotel.service.app.models;

import lombok.Data;

@Data
public class HotelDto {
    private String id;
    private String name;
    private AddressDto address;
}
