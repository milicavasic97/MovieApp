package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.dtos.ContentBaseDTO;
import com.webapi.movieapp.dtos.ReviewDTO;
import com.webapi.movieapp.models.Review;
import com.webapi.movieapp.models.ReviewId;
import com.webapi.movieapp.repositories.ReviewRepository;
import com.webapi.movieapp.security.models.AuthUserDetails;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService extends CrudJpaService<Review, ReviewId> {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final ContentService contentService;

    public ReviewService(ReviewRepository reviewRepository, ModelMapper modelMapper,
                         @Lazy ContentService contentService) {
        super(reviewRepository, modelMapper, Review.class);
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.contentService = contentService;
    }

    public ReviewDTO reviewContent(Integer contentId, Boolean mark, Boolean onlyRate,
                                   Integer rating) throws NotFoundException {
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReviewId reviewId = new ReviewId(authUserDetails.getUserId(), contentId);
        if (reviewRepository.existsById(reviewId)) {
            Review existingReview = reviewRepository.getById(reviewId);
            if(onlyRate)
                existingReview.setRating(rating);
            else
                existingReview.setFavourite(mark);
            return super.update(reviewId, existingReview, ReviewDTO.class);
        } else if(contentService.existsById(contentId)) {
            ReviewDTO newReview = new ReviewDTO(authUserDetails.getUserId(), contentId,
                    null, !onlyRate && mark);
            return super.insert(newReview, ReviewDTO.class);
        } else
            throw new NotFoundException("Content not found");
    }

    public List<ContentBaseDTO> getFavourites(String contentTypeName, Integer userId) {
        return reviewRepository.findAllByUser_UserIdAndFavourite(userId, true)
                .stream()
                .filter(o -> o.getContent().getContentType().getName().equals(contentTypeName))
                .map(o -> modelMapper.map(o, ContentBaseDTO.class))
                .collect(Collectors.toList());
    }
}
