package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.MoviePeople;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePeopleRepository extends JpaRepository<MoviePeople, Integer> {
}
