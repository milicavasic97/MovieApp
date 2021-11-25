package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "episode")
public class Episode implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer episodeId;

    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false)
    private Long duration;
    @Column(nullable = false)
    private Integer episodeNumber;

    @ManyToOne
//    @JoinColumns
//    ({
//        @JoinColumn(name = "contentId", nullable = false),
//        @JoinColumn(name = "seasonId", nullable = false)
//    })
    @JoinColumn(name = "seasonId")
    private Season season;


    @Override
    public Integer getId() {
        return episodeId;
    }

    @Override
    public void setId(Integer id) {
        episodeId = id;
    }
}
