package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.SeriesCast;
import com.webapi.movieapp.models.SeriesCastId;
import com.webapi.movieapp.repositories.SeriesCastRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SeriesCastService extends CrudJpaService<SeriesCast, SeriesCastId> {
    private final SeriesCastRepository seriesCastRepository;
    private final ModelMapper modelMapper;

    public SeriesCastService(SeriesCastRepository seriesCastRepository, ModelMapper modelMapper) {
        super(seriesCastRepository, modelMapper, SeriesCast.class);
        this.seriesCastRepository = seriesCastRepository;
        this.modelMapper = modelMapper;
    }

    public SeriesCast insertEntity(SeriesCast entity) {
        return seriesCastRepository.save(entity);
    }
}
