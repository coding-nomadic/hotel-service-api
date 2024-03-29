package com.hotel.service.app.dto;

import com.hotel.service.app.entities.Address;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private String email;
    private String contact;
    private boolean isAdmin;
    private Address address;
}
