package com.webapi.movieapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentBaseDTO {
    private Integer contentId;
    private String  title;
    private Integer year;
    private Integer duration;
    private Date releaseDate;
    private Float rating;
    private String coverLink;
    private String trailerLink;
    private Integer countryId;
    private Integer contentTypeId;
    private Integer languageId;
    private boolean active;
}
