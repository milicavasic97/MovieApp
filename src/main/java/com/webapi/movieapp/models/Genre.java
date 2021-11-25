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
@Table(name = "genre")
public class Genre  implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;
    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "genre")
    List<ContentGenre> contentGenreList;

    @Override
    public Integer getId() {
        return genreId;
    }

    @Override
    public void setId(Integer id) {
        genreId = id;
    }
}
