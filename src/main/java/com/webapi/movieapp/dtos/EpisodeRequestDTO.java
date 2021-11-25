package com.webapi.movieapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeRequestDTO {

    private String name;
    private int duration;
    private int episodeNumber;
    private int seasonId;
}
