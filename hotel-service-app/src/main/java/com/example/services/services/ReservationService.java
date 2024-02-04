package com.example.services.services;

import com.example.services.constants.ServiceConstants;
import com.example.services.entities.Reservation;
import com.example.services.exceptions.DuplicateReservationException;
import com.example.services.exceptions.ReservationNotFoundException;
import com.example.services.models.ReservationDto;
import com.example.services.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
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
            throw new DuplicateReservationException(ServiceConstants.DUPLICATE_RESERVATION_FOUND, "102");
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

    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
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
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(ServiceConstants.RESERVATION_NOT_FOUND, "102"));
    }

    private Reservation reservationByHotelId(String hotelId) {
        return reservationRepository.findByHotelId(hotelId);
    }

    private Reservation reservationByUserId(String userId) {
        return reservationRepository.findByUserId(userId);
    }
}
