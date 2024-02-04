package com.example.services.services;

import com.example.services.constants.ServiceConstants;
import com.example.services.entities.Address;
import com.example.services.entities.Hotel;
import com.example.services.exceptions.HotelNotFoundException;
import com.example.services.models.HotelDto;
import com.example.services.repositories.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    public HotelDto createHotel(HotelDto hotelDto) {
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        Hotel savedHotel = hotelRepository.save(hotel);
        return modelMapper.map(savedHotel, HotelDto.class);
    }

    public List<HotelDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    public HotelDto updateHotel(String id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, "102"));
        updateHotelFields(hotel, hotelDto);
        Hotel updatedHotel = hotelRepository.save(hotel);
        return modelMapper.map(updatedHotel, HotelDto.class);
    }

    public void deleteHotel(String id) {
        hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, "102"));
        hotelRepository.deleteById(id);
    }

    private void updateHotelFields(Hotel hotel, HotelDto hotelDto) {
        hotel.setName(hotelDto.getName());
        Address address = modelMapper.map(hotelDto.getAddress(), Address.class);
        hotel.setAddress(address);
    }
}
