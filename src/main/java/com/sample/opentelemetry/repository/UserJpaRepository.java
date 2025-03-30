package com.sample.opentelemetry.repository;

import com.sample.opentelemetry.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
}
