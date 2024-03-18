package com.hotel.service.app.controllers;

import com.hotel.service.app.models.PaymentDto;
import com.hotel.service.app.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @PostMapping(path = "/payments")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        final PaymentDto savedPaymentDto=paymentService.createPayment(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPaymentDto);
    }
}
