package com.example.tamakanfp.DTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class JobProviderDTO {

    private Integer User_id;


    private String username;


    private String password;


    private String email;


    private String role;


    private String name;


    private String phoneNumber;


    private String city;

    private String address;


    private String sector;


    private String license;

    private String Status;


}
