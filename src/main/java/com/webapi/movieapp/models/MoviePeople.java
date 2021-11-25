package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "moviePeople")
public class MoviePeople implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moviePeopleId;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false, length = 1)
    private String gender;

    @Override
    public Integer getId() {
        return moviePeopleId;
    }

    @Override
    public void setId(Integer id) {
        moviePeopleId = id;
    }
}
