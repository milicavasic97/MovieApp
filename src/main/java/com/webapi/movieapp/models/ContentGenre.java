package com.webapi.movieapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "contentGenre")
public class ContentGenre implements BaseEntity<ContentGenreId> {
    @EmbeddedId
    @Column(name = "studentSubjectId")
    private ContentGenreId contentGenreId;

    @ManyToOne
    @MapsId("contentId")
    @JoinColumn(name = "contentId")
    private Content content;

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genreId")
    private Genre genre;

    @Override
    public ContentGenreId getId() {
        return contentGenreId;
    }

    @Override
    public void setId(ContentGenreId id) {
        contentGenreId = id;
    }
}
