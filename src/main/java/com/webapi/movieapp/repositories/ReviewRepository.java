package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Review;
import com.webapi.movieapp.models.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
    List<Review> findAllByUser_UserIdAndFavourite(Integer userId, Boolean favourite);
}
