package com.ecom.global.repository;

import com.ecom.global.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmailAndPassword(String email, String password);

}
