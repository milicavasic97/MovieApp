package com.webapi.movieapp.dtos;

import lombok.Data;

@Data
public class CountryDTO {
    private Integer countryId;
    private String name;
    private String code;
}
