package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User findUserByUsername(String username);

    Optional<User> findByEmail(String email);
}
