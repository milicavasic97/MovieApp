package com.webapi.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StudentSubjectId implements Serializable {
    @Column(name = "subjectId", nullable = false)
    private Integer subjectId;
    @Column(name = "studentId", nullable = false)
    private Integer studentId;
}
