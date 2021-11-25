package com.webapi.movieapp.models;

import com.webapi.movieapp.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
public class Country implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 10)
    private String code;

    @Override
    public Integer getId() {
        return countryId;
    }

    @Override
    public void setId(Integer id) {
        countryId = id;
    }
}
