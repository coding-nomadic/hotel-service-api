package com.hotel.service.app.repositories;

import com.hotel.service.app.entities.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String> {

}
