package com.example.services.services;

import com.example.services.entities.Bill;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    public Bill getBillByReservationId(String reservationId) {
        return new Bill();
    }
}
