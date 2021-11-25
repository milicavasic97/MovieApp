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
public class SeriesCastId implements Serializable {
    @Column(name = "seasonId")
    private Integer seasonId;
    private MoviePeopleRoleId moviePeopleRoleId;
}
