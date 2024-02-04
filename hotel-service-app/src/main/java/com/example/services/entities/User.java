package com.example.services.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    private String id;
    private String name;
    private String emailId;
    private String phoneNo;
}
