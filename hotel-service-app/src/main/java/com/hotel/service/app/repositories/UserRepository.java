package com.hotel.service.app.repositories;

import com.hotel.service.app.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public boolean existsByUserName(String userName);

    public boolean existsByEmail(String email);

    public boolean existsByContact(String contact);
}
