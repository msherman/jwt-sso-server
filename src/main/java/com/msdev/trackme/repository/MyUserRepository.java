package com.msdev.trackme.repository;

import com.msdev.trackme.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyUserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String userName);
    Optional<List<User>> findByIdIn(List<String> ids);
}
