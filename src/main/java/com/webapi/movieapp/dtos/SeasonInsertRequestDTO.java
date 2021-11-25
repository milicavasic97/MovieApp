package com.webapi.movieapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeasonInsertRequestDTO {

    private String name;
    private int seasonNumber;
    private int contentId;
    private List<ContentCastDTO> seriesCastList;
    private List<EpisodeRequestDTO> episodeList;
}
