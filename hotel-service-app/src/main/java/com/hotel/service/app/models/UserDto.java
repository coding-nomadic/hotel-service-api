package com.hotel.service.app.models;

import com.hotel.service.app.entities.Address;
import lombok.Data;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

@Data
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String contact;
    private boolean isAdmin;
    private Address address;
}
