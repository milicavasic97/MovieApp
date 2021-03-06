package com.webapi.movieapp.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ContentCommentDTO {
    private Integer commentId;
    private Integer userId;
    private UserDTO user;
    private Integer contentId;
    private String comment;
    private Integer replyId;
    private Date commentDate;
}
