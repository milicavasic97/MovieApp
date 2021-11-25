package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.ContentComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentCommentRepository extends JpaRepository<ContentComment, Integer> {
    List<ContentComment> findAllByContent_ContentId(Integer contentId);
    List<ContentComment> findAllByReplyId(Integer replyId);
}
