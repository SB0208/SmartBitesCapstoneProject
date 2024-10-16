package com.library.backend.repository;
import com.library.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends MongoRepository<User, String>  {
    User findByUsername(String username);
    User findByEmail(String email);
}
