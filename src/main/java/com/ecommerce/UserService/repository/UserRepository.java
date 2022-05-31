package com.ecommerce.UserService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.UserService.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

}
