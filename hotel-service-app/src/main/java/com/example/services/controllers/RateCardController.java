package com.example.services.controllers;

import com.example.services.models.RateCardDto;
import com.example.services.services.RateCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RateCardController {

    private final RateCardService rateCardService;

    @Autowired
    public RateCardController(RateCardService rateCardService) {
        this.rateCardService = rateCardService;
    }

    @GetMapping
    public List<RateCardDto> getAllRateCards() {
        return rateCardService.getAllRateCards();
    }

    @GetMapping(value = "/rates/{hotelId}")
    public ResponseEntity<RateCardDto> getRateCardByHotelId(@PathVariable String hotelId) {
        RateCardDto rateCardDto = rateCardService.getRateCardByHotelId(hotelId);
        return rateCardDto != null ? ResponseEntity.ok(rateCardDto) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/rates")
    public ResponseEntity<RateCardDto> createRateCard(@RequestBody RateCardDto rateCardDto) {
        RateCardDto createdRateCard = rateCardService.createRateCard(rateCardDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRateCard);
    }

    @PutMapping(value = "/rates/{hotelId}/{rateId}")
    public ResponseEntity<RateCardDto> updateRateCard(@PathVariable String hotelId, @PathVariable String rateId, @RequestBody RateCardDto rateCardDto) {
        RateCardDto updatedRateCard = rateCardService.updateRateCard(hotelId, rateId, rateCardDto);
        return ResponseEntity.ok(updatedRateCard);
    }

    @DeleteMapping(value = "/rates/{hotelId}/{rateId}")
    public ResponseEntity<Void> deleteRateCard(@PathVariable String hotelId, @PathVariable String rateId) {
        rateCardService.deleteRateCard(hotelId, rateId);
        return ResponseEntity.noContent().build();
    }
}
