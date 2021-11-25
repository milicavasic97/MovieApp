package com.webapi.movieapp.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MoviePeopleRoleId implements Serializable {
    @Column(name = "movieRoleId", nullable = false)
    private Integer movieRoleId;
    @Column(name = "moviePeopleId", nullable = false)
    private Integer moviePeopleId;
}
