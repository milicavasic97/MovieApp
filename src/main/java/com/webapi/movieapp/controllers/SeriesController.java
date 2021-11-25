package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.*;
import com.webapi.movieapp.service.ContentService;
import com.webapi.movieapp.service.GenreService;
import com.webapi.movieapp.service.ReviewService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    private final GenreService genreService;
    private final ContentService contentService;
    private final ReviewService reviewService;

    public SeriesController(GenreService genreService, ContentService contentService,
                            ReviewService reviewService) {
        this.genreService = genreService;
        this.contentService = contentService;
        this.reviewService = reviewService;
    }

    @GetMapping("/by-category")
    public List<ContentBaseDTO> getCategories() {
        return contentService.getAllByContentTypeName("SERIES");
    }

    @GetMapping("/by-release-date")
    public List<ContentBaseDTO> getByReleaseDate() {
        return contentService.getAllByReleaseDate("SERIES");
    }

    @GetMapping("/by-rating")
    public List<ContentBaseDTO> getByRating() {
        return contentService.getAllByRating("SERIES");
    }

    @GetMapping("/by-genre")
    public List<ContentBaseDTO> getMoviesByCategory(@RequestParam Integer genreId, @RequestParam(required = false) Integer number) throws NotFoundException {
        return contentService.getByGenreId("SERIES", genreId, number);
    }

    @GetMapping("/favourites")
    public List<ContentBaseDTO> getFavourites(@RequestParam Integer userId) {
        return reviewService.getFavourites("SERIES", userId);
    }

    @PostMapping("/save")
    public ContentBaseDTO saveSeries(@RequestBody SeriesInsertRequestDTO request) {
        return contentService.saveSeries(request);
    }
}
