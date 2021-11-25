package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.MoviePeopleRole;
import com.webapi.movieapp.models.MoviePeopleRoleId;
import com.webapi.movieapp.repositories.MoviePeopleRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MoviePeopleRoleService extends CrudJpaService<MoviePeopleRole, MoviePeopleRoleId> {
    private final MoviePeopleRoleRepository moviePeopleRoleRepository;
    private final ModelMapper modelMapper;


    public MoviePeopleRoleService(MoviePeopleRoleRepository moviePeopleRoleRepository,
                                  ModelMapper modelMapper) {
        super(moviePeopleRoleRepository, modelMapper, MoviePeopleRole.class);
        this.moviePeopleRoleRepository = moviePeopleRoleRepository;
        this.modelMapper = modelMapper;
    }
}
