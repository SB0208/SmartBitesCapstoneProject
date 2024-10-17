package com.library.backend.repository;
import com.library.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface UserRepository extends MongoRepository<User, String>  {
    User findByEmail(String email);
}
