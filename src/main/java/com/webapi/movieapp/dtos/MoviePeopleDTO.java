package com.webapi.movieapp.dtos;

import lombok.Data;
import java.util.Date;

@Data
public class MoviePeopleDTO {
    private int moviePeopleId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
}
