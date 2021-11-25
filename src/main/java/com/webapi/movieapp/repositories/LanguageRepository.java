package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
