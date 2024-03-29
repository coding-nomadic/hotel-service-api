package com.hotel.service.app.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "users")
public class User {
    private String id;
    private String firstName;
    private String lastName;
    @Field("username")
    private String userName;
    private String email;
    private String contact;
    private String passWord;
    private boolean isAdmin;
    private Address address;
}
