package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.*;
import com.webapi.movieapp.security.models.AuthUserDetails;
import com.webapi.movieapp.service.ContentService;
import com.webapi.movieapp.service.GenreService;
import com.webapi.movieapp.service.ReviewService;
import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final GenreService genreService;
    private final ContentService contentService;
    private final ReviewService reviewService;

    public MovieController(GenreService genreService, ContentService contentService, ReviewService reviewService) {
        this.genreService = genreService;
        this.contentService = contentService;
        this.reviewService = reviewService;
    }

    @GetMapping("/by-category")
    public List<ContentBaseDTO> getByCategory() {
        return contentService.getAllByContentTypeName("MOVIE");
    }

    @GetMapping("/by-release-date")
    public List<ContentBaseDTO> getByReleaseDate() {
        return contentService.getAllByReleaseDate("MOVIE");
    }

    @GetMapping("/by-rating")
    public List<ContentBaseDTO> getByRating() {
        return contentService.getAllByRating("MOVIE");
    }

    @GetMapping("/by-genre")
    public List<ContentBaseDTO> getMoviesByCategory(@RequestParam Integer genreId, @RequestParam(required = false) Integer number) throws NotFoundException {
        return contentService.getByGenreId("MOVIE", genreId, number);
    }

    @GetMapping("/favourites")
    public List<ContentBaseDTO> getFavourites() {
        return reviewService.getFavourites("MOVIE",
                ((AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
    }

    @PostMapping("/save")
    public ContentBaseDTO saveMovie(@RequestBody MovieInsertRequestDTO request) {
        return contentService.saveMovie(request);
    }
}
