package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Content;
import com.webapi.movieapp.models.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {

    List<Content> findAllByContentTypeOrderByRatingDesc(ContentType contentType);

    List<Content> findAllByContentTypeOrderByReleaseDateDesc(ContentType contentType);
}
