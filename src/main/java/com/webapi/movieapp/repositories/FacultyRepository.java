package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, String> {
}
