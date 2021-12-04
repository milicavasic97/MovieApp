package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "contentComment")
public class ContentComment implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = true)
    private Integer replyId;

    @Column(nullable = true)
    private Date commentDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contentId")
    private Content content;

    @Override
    public Integer getId() {
        return commentId;
    }

    @Override
    public void setId(Integer id) {
        commentId = id;
    }
}
