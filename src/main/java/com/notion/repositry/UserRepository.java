package com.notion.repositry;

import com.notion.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Override
    @Query("{id : ?0, active: true}")
    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    @Query("{email : ?0}")
    Optional<User> fU(String email);

    @Query("{email : ?0}")
    List<User> fAU(String email);
}
