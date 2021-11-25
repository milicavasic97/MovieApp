package com.webapi.movieapp.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContentRequestDTO extends ContentBaseDTO {

    private List<Integer> genreIds;
}
