package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.*;
import com.webapi.movieapp.repositories.MovieCastRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieCastService extends CrudJpaService<MovieCast, MovieCastId> {
    private final MovieCastRepository movieCastRepository;
    private final ModelMapper modelMapper;

    public MovieCastService(MovieCastRepository movieCastRepository, ModelMapper modelMapper) {
        super(movieCastRepository, modelMapper, MovieCast.class);
        this.movieCastRepository = movieCastRepository;
        this.modelMapper = modelMapper;
    }

    public MovieCast insertEntity(MovieCastId key, Content content, MoviePeople moviePeople,
                                  MovieRole movieRole) {
        return movieCastRepository.save(new MovieCast(key, content,
                new MoviePeopleRole(key.getMoviePeopleRoleId(), movieRole, moviePeople)));
    }
}
