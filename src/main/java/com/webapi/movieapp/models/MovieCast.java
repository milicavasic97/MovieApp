package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movieCast")
public class MovieCast implements BaseEntity<MovieCastId> {
    @EmbeddedId
    @Column(name = "movieCastId")
    private MovieCastId movieCastId;

    @ManyToOne
    @MapsId("contentId")
    @JoinColumn(name = "contentId")
    private Content content;

    @ManyToOne
    @MapsId("moviePeopleRoleId")
    @JoinColumns({
            @JoinColumn(name = "moviePeopleId"),
            @JoinColumn(name = "movieRoleId")
    })
    private MoviePeopleRole moviePeopleRole;

    @Override
    public MovieCastId getId() {
        return movieCastId;
    }

    @Override
    public void setId(MovieCastId movieCastId) {
        this.movieCastId = movieCastId;
    }
}
