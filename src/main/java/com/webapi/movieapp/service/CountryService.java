package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.ContentType;
import com.webapi.movieapp.models.Country;
import com.webapi.movieapp.models.Faculty;
import com.webapi.movieapp.repositories.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService extends CrudJpaService<Country, Integer> {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        super(countryRepository, modelMapper, Country.class);
        this.countryRepository = countryRepository;
    }
}
