package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.dtos.ContentCommentDTO;
import com.webapi.movieapp.exceptions.NotAuthorizedException;
import com.webapi.movieapp.models.ContentComment;
import com.webapi.movieapp.repositories.ContentCommentRepository;
import com.webapi.movieapp.security.models.AuthUserDetails;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentCommentService extends CrudJpaService<ContentComment, Integer> {
    private final ContentCommentRepository contentCommentRepository;
    private final ModelMapper modelMapper;

    public ContentCommentService(ContentCommentRepository contentCommentRepository, ModelMapper modelMapper) {
        super(contentCommentRepository, modelMapper, ContentComment.class);
        this.contentCommentRepository = contentCommentRepository;
        this.modelMapper = modelMapper;
    }

    public List<ContentCommentDTO> findAllByContentId(Integer contentId) {
        return contentCommentRepository.findAllByContent_ContentId(contentId)
                .stream()
                .map(o -> modelMapper.map(o, ContentCommentDTO.class))
                .sorted(Comparator.comparing(ContentCommentDTO::getCommentDate))
                .collect(Collectors.toList());
    }

    public ContentCommentDTO editComment(ContentCommentDTO contentCommentDTO) throws NotFoundException, NotAuthorizedException {
        ContentComment contentComment = super.findEntityById(contentCommentDTO.getCommentId());

        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUserDetails.getUserId().equals(contentComment.getUser().getUserId())) {
            contentCommentDTO.setUserId(authUserDetails.getUserId());
            return super.update(contentCommentDTO.getCommentId(), contentCommentDTO, ContentCommentDTO.class);
        }
        else
            throw new NotAuthorizedException("User is not authorized");
    }

    public void deleteComment(Integer commentId) throws NotFoundException, NotAuthorizedException {
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ContentComment contentComment = super.findEntityById(commentId);
        if(authUserDetails.getUserId().equals(contentComment.getUser().getUserId()))
            deleteCommentAndAllWithReplyId(commentId);
        else
            throw new NotAuthorizedException("User is not authorized");
    }

    private void deleteCommentAndAllWithReplyId(Integer commentId) throws NotFoundException {
        List<ContentComment> commentsWithReplyId = contentCommentRepository.findAllByReplyId(commentId);
        for(var comment : commentsWithReplyId)
            deleteCommentAndAllWithReplyId(comment.getCommentId());

        super.delete(commentId);
     }
}
