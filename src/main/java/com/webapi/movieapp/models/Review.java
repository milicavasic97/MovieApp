package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "review")
public class Review implements BaseEntity<ReviewId> {
    @EmbeddedId
    @Column(name = "reviewId")
    private ReviewId reviewId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @MapsId("contentId")
    @JoinColumn(name = "contentId")
    private Content content;

    @Column(nullable = true)
    private Integer rating;

    @Column(nullable = true)
    private Boolean favourite;

    @Override
    public ReviewId getId() {
        return reviewId;
    }

    @Override
    public void setId(ReviewId reviewId) {
        this.reviewId = reviewId;
    }
}
