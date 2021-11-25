package com.webapi.movieapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentCastDTO {
    private int movieRoleId;
    private int moviePeopleId;
}
