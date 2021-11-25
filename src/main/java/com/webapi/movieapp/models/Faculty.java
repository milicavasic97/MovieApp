package com.webapi.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Locale;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @Column(unique = true, name = "facultyId", nullable = false)
    private String facultyId;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId() {
        this.facultyId = UUID.randomUUID().toString().toUpperCase(Locale.ROOT);
    }
}
