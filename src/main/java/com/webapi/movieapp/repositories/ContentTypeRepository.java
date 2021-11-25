package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentTypeRepository extends JpaRepository<ContentType, Integer> {
    ContentType getByName(String name);
}
