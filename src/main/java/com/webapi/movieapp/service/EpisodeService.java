package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.Episode;
import com.webapi.movieapp.repositories.EpisodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService extends CrudJpaService<Episode, Integer> {
    private final EpisodeRepository episodeRepository;
    private final ModelMapper modelMapper;

    public EpisodeService(EpisodeRepository episodeRepository, ModelMapper modelMapper) {
        super(episodeRepository, modelMapper, Episode.class);
        this.episodeRepository = episodeRepository;
        this.modelMapper = modelMapper;
    }
}
