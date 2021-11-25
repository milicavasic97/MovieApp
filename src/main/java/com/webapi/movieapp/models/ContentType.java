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
@Table(name = "contentType")
public class ContentType implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contentTypeId;
    @Column(nullable = false, length = 20)
    private String name;

    @Override
    public Integer getId() {
        return contentTypeId;
    }

    @Override
    public void setId(Integer id) {
        contentTypeId = id;
    }
}
