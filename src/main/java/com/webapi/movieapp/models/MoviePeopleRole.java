package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "moviePeopleRole")
public class MoviePeopleRole implements BaseEntity<MoviePeopleRoleId> {
    @EmbeddedId
    @Column(name = "moviePeopleRoleId")
    private MoviePeopleRoleId moviePeopleRoleId;

    @ManyToOne
    @MapsId("movieRoleId")
    @JoinColumn(name = "movieRoleId")
    private MovieRole movieRole;

    @ManyToOne
    @MapsId("moviePeopleId")
    @JoinColumn(name = "moviePeopleId")
    private MoviePeople moviePeople;

    @Override
    public MoviePeopleRoleId getId() {
        return moviePeopleRoleId;
    }

    @Override
    public void setId(MoviePeopleRoleId moviePeopleRoleId) {
        this.moviePeopleRoleId = moviePeopleRoleId;
    }
}
