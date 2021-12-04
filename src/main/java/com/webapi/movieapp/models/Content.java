package com.webapi.movieapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapi.movieapp.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "content")
public class Content implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contentId;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private Integer duration;
    @Column(nullable = true)
    private Date releaseDate;
    @Column(nullable = false)
    private Float rating;
    @Column(nullable = false, length = 400)
    private String coverLink;
    @Column(nullable = false, length = 400)
    private String trailerLink;
    @Column
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "countryId", nullable = false)
    private Country country;
    @ManyToOne
    @JoinColumn(name = "contentTypeId", nullable = false)
    private ContentType contentType;
    @ManyToOne
    @JoinColumn(name = "languageId", nullable = false)
    private Language language;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ContentGenre> contentGenres;
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ContentComment> contentComments;
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    @Override
    public Integer getId() {
        return contentId;
    }

    @Override
    public void setId(Integer id) {
        contentId = id;
    }
}
