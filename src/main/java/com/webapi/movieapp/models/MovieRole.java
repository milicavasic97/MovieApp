package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "movieRole")
public class MovieRole implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieRoleId;
    @Column(nullable = false, length = 20)
    private String name;

    @Override
    public Integer getId() {
        return movieRoleId;
    }

    @Override
    public void setId(Integer id) {
        movieRoleId = id;
    }
}
