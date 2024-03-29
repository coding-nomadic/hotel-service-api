package com.hotel.service.app.services;

import com.hotel.service.app.constants.ReservationStatus;
import com.hotel.service.app.constants.ServiceConstants;
import com.hotel.service.app.entities.Reservation;
import com.hotel.service.app.exceptions.DuplicateReservationException;
import com.hotel.service.app.exceptions.ReservationNotFoundException;
import com.hotel.service.app.dto.ReservationDto;
import com.hotel.service.app.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationDto> getAllReservations() {
        final List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ReservationDto getReservationById(String id) {
        Reservation reservation = getReservationOrThrow(id);
        return convertToDto(reservation);
    }

    public ReservationDto getReservationByHotelId(String hotelId) {
        Reservation reservation = reservationByHotelId(hotelId);
        return convertToDto(reservation);
    }

    public ReservationDto getReservationByUserId(String userId) {
        Reservation reservation = reservationByUserId(userId);
        return convertToDto(reservation);
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {
        if (reservationRepository.existsById(reservationDto.getId())) {
            throw new DuplicateReservationException(ServiceConstants.DUPLICATE_RESERVATION_FOUND, ServiceConstants.ERROR_CODE);
        }
        Reservation reservation = reservationRepository.save(convertToEntity(reservationDto));
        return convertToDto(reservation);
    }

    public ReservationDto updateReservation(String id, ReservationDto reservationDto) {
        Reservation reservation = getReservationOrThrow(id);
        updateReservationFields(reservation, reservationDto);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return convertToDto(updatedReservation);
    }

    public void cancelReservation(String id) {
        Optional<Reservation> reservation=reservationRepository.findById(id);
        reservation.ifPresent(value -> value.setStatus(ReservationStatus.CANCELLED));
    }
    public void completedReservation(String id) {
        Optional<Reservation> reservation=reservationRepository.findById(id);
        reservation.ifPresent(value -> value.setStatus(ReservationStatus.COMPLETED));
    }

    private ReservationDto convertToDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    private Reservation convertToEntity(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }

    private void updateReservationFields(Reservation reservation, ReservationDto reservationDto) {
        reservation.setHotelId(reservationDto.getHotelId());
        reservation.setUserId(reservationDto.getUserId());
        reservation.setRoomsBooked(reservationDto.getRoomsBooked());
        reservation.setStatus(reservationDto.getStatus());
        reservation.setStartDate(reservationDto.getStartDate());
        reservation.setEndDate(reservationDto.getEndDate());
    }

    private Reservation getReservationOrThrow(String id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(ServiceConstants.RESERVATION_NOT_FOUND, ServiceConstants.ERROR_CODE));
    }

    private Reservation reservationByHotelId(String hotelId) {
        return reservationRepository.findByHotelId(hotelId);
    }

    private Reservation reservationByUserId(String userId) {
        return reservationRepository.findByUserId(userId);
    }
}
