package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.*;
import com.webapi.movieapp.models.Content;
import com.webapi.movieapp.security.models.AuthUserDetails;
import com.webapi.movieapp.service.ContentCommentService;
import com.webapi.movieapp.service.ContentService;
import com.webapi.movieapp.service.ReviewService;
import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/contents")
public class ContentController {
    private final ContentService contentService;
    private final ReviewService reviewService;
    private final ContentCommentService commentService;


    public ContentController(ContentService contentService, ReviewService reviewService,
                             ContentCommentService commentService) {
        this.contentService = contentService;
        this.reviewService = reviewService;
        this.commentService = commentService;
    }

    @PostMapping("/save")
    public Content save(@RequestBody ContentRequestDTO request){
        request.setActive(true);
        return contentService.insert(request);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer contentId) throws NotFoundException {
        contentService.delete(contentId);
    }

    @GetMapping("/details")
    public SingleContentDTO getContentDetails(@RequestParam Integer contentId) throws NotFoundException {
        return contentService.getSingleContent(contentId);
    }

    @GetMapping("")
    public List<SingleContentDTO> getAll() throws NotFoundException {
        return contentService.findAll(SingleContentDTO.class);
    }

    @PostMapping("/mark-favourite")
    public ReviewDTO markFavourite(@RequestBody MarkFavouriteDTO request) throws NotFoundException {
        return reviewService.reviewContent(request.getContentId(), request.isFavourite(), false, null);
    }

    @PutMapping("/rate")
    public ReviewDTO rateContent(@RequestParam Integer contentId, @RequestParam Integer rate) throws NotFoundException {
        return reviewService.reviewContent(contentId, false, true, rate);
    }

    @PostMapping("/comment")
    public ContentCommentDTO commentContent(@RequestBody ContentCommentRequestDTO request) throws NotFoundException {
        if(!contentService.existsById(request.getContentId()))
            throw new NotFoundException("Content not found");
        request.setUserId(((AuthUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
        request.setCommentDate(new Date(System.currentTimeMillis()));
        return commentService.insert(request, ContentCommentDTO.class);
    }

    @GetMapping("/comments")
    public List<ContentCommentDTO> getContentComments(@RequestParam Integer contentId) {
        return commentService.findAllByContentId(contentId);
    }

    @GetMapping("/favourites")
    public List<ContentBaseDTO> getFavourites() {
        return reviewService.getAllFavourites(((AuthUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
    }

    @GetMapping("/by-genre")
    public List<ContentBaseDTO> getMoviesByGenre(@RequestParam Integer genreId, @RequestParam(required = false) Integer number) throws NotFoundException {
        return contentService.getAllByGenreId(genreId, number);
    }


}
