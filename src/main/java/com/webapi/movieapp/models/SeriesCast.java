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
@Table(name = "seriesCast")
public class SeriesCast implements BaseEntity<SeriesCastId> {
    @EmbeddedId
    @Column(name = "seriesCastId")
    private SeriesCastId seriesCastId;

    @ManyToOne
    @MapsId("seasonId")
    @JoinColumn(name = "seasonId")
    private Season season;

    @ManyToOne
    @MapsId("moviePeopleRoleId")
    @JoinColumns({
            @JoinColumn(name = "moviePeopleId"),
            @JoinColumn(name = "movieRoleId")
    })
    private MoviePeopleRole moviePeopleRole;

    @Override
    public SeriesCastId getId() {
        return seriesCastId;
    }

    @Override
    public void setId(SeriesCastId id) {
        seriesCastId = id;
    }
}
