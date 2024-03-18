package com.hotel.service.app.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Document(collection = "audits")
public class Audit implements Serializable {
    @Id
    private String id;
    private String action;
    private String serverName;
    private Date timestamp;
}
