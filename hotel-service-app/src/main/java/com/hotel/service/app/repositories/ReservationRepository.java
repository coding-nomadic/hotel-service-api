package com.hotel.service.app.repositories;

import com.hotel.service.app.constants.ReservationStatus;
import com.hotel.service.app.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

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