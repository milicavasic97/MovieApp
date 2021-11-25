package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.MoviePeople;
import com.webapi.movieapp.repositories.MoviePeopleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MoviePeopleService extends CrudJpaService<MoviePeople, Integer> {
    private final MoviePeopleRepository moviePeopleRepository;
    private final ModelMapper modelMapper;

    public MoviePeopleService(MoviePeopleRepository moviePeopleRepository, ModelMapper modelMapper) {
        super(moviePeopleRepository, modelMapper, MoviePeople.class);
        this.moviePeopleRepository = moviePeopleRepository;
        this.modelMapper = modelMapper;
    }
}
