package com.hotel.service.app.services;

import com.hotel.service.app.constants.ServiceConstants;
import com.hotel.service.app.entities.Address;
import com.hotel.service.app.entities.Hotel;
import com.hotel.service.app.exceptions.HotelNotFoundException;
import com.hotel.service.app.models.HotelDto;
import com.hotel.service.app.repositories.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ModelMapper modelMapper;

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
                .orElseThrow(() -> new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, ServiceConstants.ERROR_CODE));
        updateHotelFields(hotel, hotelDto);
        Hotel updatedHotel = hotelRepository.save(hotel);
        return modelMapper.map(updatedHotel, HotelDto.class);
    }

    public void deleteHotel(String id) {
        hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, ServiceConstants.ERROR_CODE));
        hotelRepository.deleteById(id);
    }

    private void updateHotelFields(Hotel hotel, HotelDto hotelDto) {
        hotel.setName(hotelDto.getName());
        Address address = modelMapper.map(hotelDto.getAddress(), Address.class);
        hotel.setAddress(address);
    }
}
