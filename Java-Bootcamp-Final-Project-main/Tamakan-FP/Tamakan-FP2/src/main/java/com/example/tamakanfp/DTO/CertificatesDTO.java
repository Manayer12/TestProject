package com.example.tamakanfp.DTO;

import jakarta.persistence.Lob;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class CertificatesDTO {

    private Integer jobApp_id;
    private String name;
    private String type;
    @Lob
    private byte[] fileData;



}
