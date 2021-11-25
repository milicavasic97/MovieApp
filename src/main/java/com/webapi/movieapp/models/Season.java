package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "season")
public class Season implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seasonId;

    @ManyToOne
    @JoinColumn(name = "contentId", nullable = false)
    private Content content;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private Integer seasonNumber;

    @Override
    public Integer getId() {
        return seasonId;
    }

    @Override
    public void setId(Integer id) {
        seasonId = id;
    }
}
