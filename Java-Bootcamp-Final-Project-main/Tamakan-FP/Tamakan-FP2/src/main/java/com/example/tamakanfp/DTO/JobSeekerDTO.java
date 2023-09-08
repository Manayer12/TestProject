package com.example.tamakanfp.DTO;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class JobSeekerDTO {


    private Integer User_id;


    private String username;


    private String password;


    private String email;


    private String role;


    private String name;


    private String phoneNumber;


    private String gender;


    private String city;

    private String address;


    private Integer age;






}
