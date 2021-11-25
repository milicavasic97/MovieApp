package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.MovieRole;
import com.webapi.movieapp.repositories.MovieRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieRoleService extends CrudJpaService<MovieRole, Integer> {
    private final MovieRoleRepository movieRoleRepository;
    private final ModelMapper modelMapper;

    public MovieRoleService(MovieRoleRepository movieRoleRepository, ModelMapper modelMapper) {
        super(movieRoleRepository, modelMapper, MovieRole.class);
        this.movieRoleRepository = movieRoleRepository;
        this.modelMapper = modelMapper;
    }
}
