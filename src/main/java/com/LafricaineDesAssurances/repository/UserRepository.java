package com.LafricaineDesAssurances.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.LafricaineDesAssurances.model.user;


public interface UserRepository extends JpaRepository<user, Long> {
    boolean existsByEmail(String email);
    user findByEmail(String email);
}
