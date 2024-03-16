package com.github.rafinhalq.securityexample.repository;

import com.github.rafinhalq.securityexample.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
