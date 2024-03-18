package com.hotel.service.app.repositories;

import com.hotel.service.app.entities.RateCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateCardRepository extends MongoRepository<RateCard, String> {
    /**
     *
     * @param hotelId
     * @return
     */
    RateCard findByHotelId(String hotelId);

    /**
     *
     * @param hotelId
     * @return
     */
    boolean existsByHotelId(String hotelId);
}