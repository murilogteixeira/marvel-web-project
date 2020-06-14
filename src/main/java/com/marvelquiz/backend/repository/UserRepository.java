package com.marvelquiz.backend.repository;

import java.util.Optional;

import com.marvelquiz.bean.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query(value = "SELECT * FROM user u WHERE u.username = ?", nativeQuery = true)
    Optional<User> findByUsername(String username);
    
}
