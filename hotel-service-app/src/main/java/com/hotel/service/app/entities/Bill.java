package com.hotel.service.app.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bills")
public class Bill {
    @Id
    private String id;
    private double tax;
    private double amountToBePaid;
}
