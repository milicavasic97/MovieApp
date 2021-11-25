package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "language")
public class Language implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer languageId;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 10)
    private String code;

    @Override
    public Integer getId() {
        return languageId;
    }

    @Override
    public void setId(Integer id) {
        languageId = id;
    }
}
