package com.webapi.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SeasonId implements Serializable {
    @Column(name = "seasonId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seasonId;
    @Column(name = "contentId", nullable = false)
    private Integer contentId;
}
