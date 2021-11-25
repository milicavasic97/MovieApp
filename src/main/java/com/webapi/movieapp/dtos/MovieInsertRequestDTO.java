package com.webapi.movieapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieInsertRequestDTO extends ContentRequestDTO {

    private List<ContentCastDTO> movieCastList;
}
