package com.webapi.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContentGenreId implements Serializable {
    @Column(name = "contentId", nullable = false)
    private Integer contentId;
    @Column(name = "genreId", nullable = false)
    private Integer genreId;
}
