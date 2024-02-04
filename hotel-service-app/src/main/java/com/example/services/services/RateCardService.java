package com.example.services.services;

import com.example.services.constants.ServiceConstants;
import com.example.services.entities.RateCard;
import com.example.services.exceptions.HotelNotFoundException;
import com.example.services.models.RateCardDto;
import com.example.services.repositories.RateCardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateCardService {

    private final RateCardRepository rateCardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RateCardService(RateCardRepository rateCardRepository, ModelMapper modelMapper) {
        this.rateCardRepository = rateCardRepository;
        this.modelMapper = modelMapper;
    }

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
            throw new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, "102");
        }
        RateCard rateCard = rateCardRepository.findById(id).orElseThrow(() -> new RuntimeException("Rate card not found"));
        rateCard.setDate(rateCardDto.getDate());
        rateCard.setPrice(rateCardDto.getPrice());
        rateCard.setRoomType(rateCardDto.getRoomType());
        RateCard updatedRateCard = rateCardRepository.save(rateCard);
        return modelMapper.map(updatedRateCard, RateCardDto.class);
    }

    public void deleteRateCard(String hotelId, String id) {
        if (!rateCardRepository.existsByHotelId(hotelId)) {
            throw new HotelNotFoundException(ServiceConstants.HOTEL_NOT_FOUND, "102");
        }
        rateCardRepository.deleteById(id);
    }
}
