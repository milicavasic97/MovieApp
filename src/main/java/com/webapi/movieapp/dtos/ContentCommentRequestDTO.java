package com.webapi.movieapp.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ContentCommentRequestDTO {
    private Integer commentId;
    private Integer userId;
    private Integer contentId;
    private String comment;
    private Integer replyId;
    private Date commentDate;
}
