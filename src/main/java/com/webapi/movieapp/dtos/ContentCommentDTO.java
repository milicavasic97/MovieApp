package com.webapi.movieapp.dtos;

import lombok.Data;

@Data
public class ContentCommentDTO {
    private Integer commentId;
    private Integer userId;
    private Integer contentId;
    private String comment;
    private Integer replyId;
}
