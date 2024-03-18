package com.hotel.service.app.services;

import com.hotel.service.app.entities.Bill;
import com.hotel.service.app.entities.Hotel;
import com.hotel.service.app.entities.Payment;
import com.hotel.service.app.models.HotelDto;
import com.hotel.service.app.models.PaymentDto;
import com.hotel.service.app.repositories.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     *
     * @param paymentDto
     * @return
     */
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        Payment savedPayment = paymentRepository.save(payment);
        return modelMapper.map(savedPayment, PaymentDto.class);
    }
}
