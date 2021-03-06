package com.webapi.movieapp.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer userId;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
}
