package com.hotel.service.app.services;

import com.hotel.service.app.constants.ServiceConstants;
import com.hotel.service.app.entities.RateCard;
import com.hotel.service.app.exceptions.HotelNotFoundException;
import com.hotel.service.app.models.RateCardDto;
import com.hotel.service.app.repositories.RateCardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateCardService {

    @Autowired
    private RateCardRepository rateCardRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RateCardDto> getAllRateCards() {
        List<RateCard> rateCards = rateCardRepository.findAll();
        return rateCards.stream().map(rateCard -> modelMapper.map(rateCard, RateCardDto.class)).collect(Collectors.toList());
    }

    public RateCardDto getRateCardByHotelId(String hotelId) {
        RateCard rateCard = rateCardRepository.findByHotelId(hotelId);
        return modelMapper.map(rateCard, RateCardDto.class);
    }

    public RateCardDto createRateCard(RateCardDto rateCardDto) {
        RateCard rateCard = rateCardRepository.save(modelMapper.map(rateCardDto, RateCard.class));
        return modelMapper.map(rateCard, RateCardDto.class);
    }

    public RateCardDto updateRateCard(String hotelId, String id, RateCardDto rateCardDto) {
        if (!rateCardRepository.existsByHotelId(hotelId)) {
            throw new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, ServiceConstants.ERROR_CODE);
        }
        RateCard rateCard = rateCardRepository.findById(id).orElseThrow(() -> new RuntimeException(ServiceConstants.RATE_CARD_NOT_FOUND));
        rateCard.setDate(rateCardDto.getDate());
        rateCard.setPrice(rateCardDto.getPrice());
        rateCard.setRoomType(rateCardDto.getRoomType());
        RateCard updatedRateCard = rateCardRepository.save(rateCard);
        return modelMapper.map(updatedRateCard, RateCardDto.class);
    }

    public void deleteRateCard(String hotelId, String id) {
        if (!rateCardRepository.existsByHotelId(hotelId)) {
            throw new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, ServiceConstants.ERROR_CODE);
        }
        rateCardRepository.deleteById(id);
    }
}
