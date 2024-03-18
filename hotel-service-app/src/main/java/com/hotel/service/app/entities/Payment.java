package com.hotel.service.app.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "payments")
public class Payment {
    @Id
    private int id;
    private String modeOfPayment;
    private String status;
}
