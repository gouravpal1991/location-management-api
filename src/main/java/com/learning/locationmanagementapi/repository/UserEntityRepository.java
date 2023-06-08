package com.learning.locationmanagementapi.repository;

import com.learning.locationmanagementapi.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmailAndPassword(String email, String password);

    UserEntity findByEmail(String email);
}
