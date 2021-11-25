package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.dtos.GenreDTO;
import com.webapi.movieapp.models.Genre;
import com.webapi.movieapp.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class GenreService extends CrudJpaService<Genre, Integer> {
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public GenreService(GenreRepository genreRepository, ModelMapper modelMapper) {
        super(genreRepository, modelMapper, Genre.class);
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    public List<GenreDTO> getAllByContentTypeName(String contentTypeName) {
        return genreRepository.findAll()
                .stream()
                .filter(o -> o.getContentGenreList().stream().anyMatch(o1 -> o1.getContent().getContentType().getName().equals(contentTypeName)))
                .map(o -> modelMapper.map(o, GenreDTO.class))
                .collect(Collectors.toList());
    }

}
