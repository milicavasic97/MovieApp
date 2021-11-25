package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.ContentCommentDTO;
import com.webapi.movieapp.dtos.ReviewDTO;
import com.webapi.movieapp.dtos.SingleContentDTO;
import com.webapi.movieapp.models.Content;
import com.webapi.movieapp.dtos.ContentRequestDTO;
import com.webapi.movieapp.service.ContentCommentService;
import com.webapi.movieapp.service.ContentService;
import com.webapi.movieapp.service.ReviewService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/details")
    public SingleContentDTO getContentDetails(@RequestParam Integer contentId) throws NotFoundException {
        return contentService.getSingleContent(contentId);
    }

    @PutMapping("/mark-favourite")
    public ReviewDTO markFavourite(@RequestParam Integer contentId) throws NotFoundException {
        return reviewService.reviewContent(contentId, true, false, null);
    }

    @PutMapping("/remark-favourite")
    public ReviewDTO remarkFavourite(@RequestParam Integer contentId) throws NotFoundException {
        return reviewService.reviewContent(contentId, false, false, null);
    }

    @PutMapping("/rate")
    public ReviewDTO rateContent(@RequestParam Integer contentId, @RequestParam Integer rate) throws NotFoundException {
        return reviewService.reviewContent(contentId, false, true, rate);
    }

    @PostMapping("/comment")
    public ContentCommentDTO commentContent(@RequestBody ContentCommentDTO request) throws NotFoundException {
        if(!contentService.existsById(request.getContentId()))
            throw new NotFoundException("Content not found");
        return commentService.insert(request, ContentCommentDTO.class);
    }

    @GetMapping("/comments")
    public List<ContentCommentDTO> getContentComments(@RequestParam Integer contentId) {
        return commentService.findAllByContentId(contentId);
    }


}
