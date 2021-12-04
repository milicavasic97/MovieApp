package com.webapi.movieapp.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SingleContentDTO extends ContentBaseDTO {
    private LanguageDTO language;
    private CountryDTO country;
    private ContentTypeDTO contentType;
    private boolean favourite;
    private List<GenreDTO> genres;
    private List<ContentCommentDTO> contentComments;
}
