package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.MoviePeopleDTO;
import com.webapi.movieapp.dtos.MovieRoleDTO;
import com.webapi.movieapp.service.MovieRoleService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-roles")
public class MovieRoleController {
    private final MovieRoleService movieRoleService;

    public MovieRoleController(MovieRoleService movieRoleService) {
        this.movieRoleService = movieRoleService;
    }

    @GetMapping("/get-one")
    public MovieRoleDTO getOne(@RequestParam Integer id) throws NotFoundException {
        return movieRoleService.findById(id, MovieRoleDTO.class);
    }

    @GetMapping
    public List<MovieRoleDTO> getAll(){
        return movieRoleService.findAll(MovieRoleDTO.class);
    }

    @PostMapping("/save")
    public MovieRoleDTO insert(@RequestBody MovieRoleDTO request) {
        return movieRoleService.insert(request, MovieRoleDTO.class);
    }

    @PutMapping("/update")
    public MovieRoleDTO update(@RequestBody MovieRoleDTO request) throws NotFoundException {
        return movieRoleService.update(request.getMovieRoleId(), request, MovieRoleDTO.class);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) throws NotFoundException {
        movieRoleService.delete(id);
    }

}
