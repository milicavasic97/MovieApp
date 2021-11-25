package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
