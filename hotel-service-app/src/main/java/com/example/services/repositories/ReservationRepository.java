package com.example.services.repositories;

import com.example.services.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    /**
     *
     * @param hotelId
     * @return
     */
    public Reservation findByHotelId(String hotelId);

    /**
     *
     * @param userId
     * @return
     */

    public Reservation findByUserId(String userId);
}