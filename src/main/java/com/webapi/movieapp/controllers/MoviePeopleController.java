package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.LanguageDTO;
import com.webapi.movieapp.dtos.MoviePeopleDTO;
import com.webapi.movieapp.service.MoviePeopleService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-people")
public class MoviePeopleController {
    private final MoviePeopleService moviePeopleService;

    public MoviePeopleController(MoviePeopleService moviePeopleService) {
        this.moviePeopleService = moviePeopleService;
    }

    @GetMapping("/get-one")
    public MoviePeopleDTO getOne(@RequestParam Integer id) throws NotFoundException {
        return moviePeopleService.findById(id, MoviePeopleDTO.class);
    }

    @GetMapping
    public List<MoviePeopleDTO> getAll(){
        return moviePeopleService.findAll(MoviePeopleDTO.class);
    }

    @PostMapping("/save")
    public MoviePeopleDTO insert(@RequestBody MoviePeopleDTO request) {
        return moviePeopleService.insert(request, MoviePeopleDTO.class);
    }

    @PutMapping("/update")
    public MoviePeopleDTO update(@RequestBody MoviePeopleDTO request) throws NotFoundException {
        return moviePeopleService.update(request.getMoviePeopleId(), request, MoviePeopleDTO.class);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) throws NotFoundException {
        moviePeopleService.delete(id);
    }

}
