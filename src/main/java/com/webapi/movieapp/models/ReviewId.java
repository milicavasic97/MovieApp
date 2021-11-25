package com.webapi.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReviewId implements Serializable {
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "contentId")
    private Integer contentId;
}
