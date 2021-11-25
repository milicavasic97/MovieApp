package com.webapi.movieapp.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String name;
    private Integer yearOfStudy;
    private String faculty;
    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false)
    private City cityId;

//    @ManyToMany
//    @JoinTable
//    (
//            name = "student_subject",
//            joinColumns = @JoinColumn(name = "studentId"),
//            inverseJoinColumns = @JoinColumn(name = "subjectId")
//    )
//    private Set<Subject> subject;

}
