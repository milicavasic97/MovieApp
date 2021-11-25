package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.MoviePeopleRole;
import com.webapi.movieapp.models.MoviePeopleRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePeopleRoleRepository extends JpaRepository<MoviePeopleRole, MoviePeopleRoleId> {
}
