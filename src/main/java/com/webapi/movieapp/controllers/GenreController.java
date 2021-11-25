package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.GenreDTO;
import com.webapi.movieapp.models.ContentType;
import com.webapi.movieapp.models.Genre;
import com.webapi.movieapp.service.GenreService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAll(){
        return genreService.findAll(Genre.class);
    }

    @PostMapping("/save")
    public Genre insert(@RequestBody GenreDTO request) {
        return genreService.insert(request, Genre.class);
    }

    @PutMapping("/update")
    public Genre update(@RequestBody GenreDTO request) throws NotFoundException {
        return genreService.update(request.getGenreId(), request, Genre.class);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) throws NotFoundException {
        genreService.delete(id);
    }
}
