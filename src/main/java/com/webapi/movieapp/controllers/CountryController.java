package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.CountryDTO;
import com.webapi.movieapp.service.CountryService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryDTO> getAll(){
        return countryService.findAll(CountryDTO.class);
    }

    @PostMapping("/save")
    public CountryDTO insert(@RequestBody CountryDTO request) {
        return countryService.insert(request, CountryDTO.class);
    }

    @PutMapping("/update")
    public CountryDTO update(@RequestBody CountryDTO request) throws NotFoundException {
        return countryService.update(request.getCountryId(), request, CountryDTO.class);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) throws NotFoundException {
        countryService.delete(id);
    }
}
