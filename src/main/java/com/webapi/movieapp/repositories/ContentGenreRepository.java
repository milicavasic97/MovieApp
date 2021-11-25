package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.ContentGenre;
import com.webapi.movieapp.models.ContentGenreId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentGenreRepository extends JpaRepository<ContentGenre, ContentGenreId> {

    void deleteAllByContent_ContentId(Integer contentId);
    List<ContentGenre> findAllByContent_ContentId(Integer contentId);
}
