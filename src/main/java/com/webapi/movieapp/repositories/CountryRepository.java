package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
