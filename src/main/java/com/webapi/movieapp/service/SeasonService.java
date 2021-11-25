package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.Season;
import com.webapi.movieapp.repositories.SeasonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SeasonService extends CrudJpaService<Season, Integer> {
    private final SeasonRepository seasonRepository;
    private final ModelMapper modelMapper;

    public SeasonService(SeasonRepository seasonRepository, ModelMapper modelMapper) {
        super(seasonRepository, modelMapper, Season.class);
        this.seasonRepository = seasonRepository;
        this.modelMapper = modelMapper;
    }
}
