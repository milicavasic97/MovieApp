package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.MovieRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRoleRepository extends JpaRepository<MovieRole, Integer> {
}
