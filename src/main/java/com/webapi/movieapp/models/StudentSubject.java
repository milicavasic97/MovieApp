package com.webapi.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studentSubject")
public class StudentSubject {

    @EmbeddedId
    @Column(name = "studentSubjectId")
    private StudentSubjectId studentSubjectId;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    private Student studentId;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subjectId")
    private Subject subjectId;

    private String grade;

//    @Id
//    @ManyToOne
//    @MapsId("studentId")
//    @JoinColumn(name = "studentId")
//    private Student studentId;
//    @Id
//    @ManyToOne
//    @MapsId("subjectId")
//    @JoinColumn(name = "subjectId")
//    private Subject subjectId;

}
