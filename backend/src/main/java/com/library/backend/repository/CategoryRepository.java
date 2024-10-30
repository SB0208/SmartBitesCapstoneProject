package com.library.backend.repository;
import com.library.backend.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface CategoryRepository extends MongoRepository<Category, String> {
}
