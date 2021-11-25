package com.webapi.movieapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Integer userId;
    private Integer contentId;
    private Integer rating;
    private Boolean favourite;
}
